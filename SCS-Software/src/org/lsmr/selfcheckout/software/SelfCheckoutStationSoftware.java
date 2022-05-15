package org.lsmr.selfcheckout.software;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.EmptyException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;



//This is the class that boots up and then has a gui.

public class SelfCheckoutStationSoftware {
	
	private SelfCheckoutStation scs;
	private TestDatabase db;			//in an actual system this would connect to a db or something
	private ElectronicScaleSoftware ess;
	private BarcodeScannerSoftware bss;
	private BanknoteSlotSoftware banknoteSlotSoftware;
	private CoinSlotSoftware coinSlotSoftware;
	
	//self checkout station software
	//NOTE: Any objects that are not primitive types are passed to other classes by reference. 
	//Thus, passing the following vars to the other classes will give us an updated vars.
	private ArrayList<ItemInfo> itemsScanned;
	private final double weightThreshold = 10;
	private BigDecimal[] amountPaid;
	private int bagsUsed;
	private int maximumBags;
	private BigDecimal priceOfBags;
	private ReturnChangeSoftware returnChangeSoftware;
	private BigDecimal amountReturned;

	public SelfCheckoutStationSoftware(SelfCheckoutStation scs) throws SimulationException, OverloadException {
		this.itemsScanned = new ArrayList<ItemInfo>();
		this.amountPaid = new BigDecimal[1];
		this.amountPaid[0] = BigDecimal.ZERO;		
		this.bagsUsed = 0;
		this.maximumBags = 10;
		this.priceOfBags = new BigDecimal("0.05");

		this.scs = scs;
		this.db = new TestDatabase();
		this.ess = new ElectronicScaleSoftware();
		this.bss = new BarcodeScannerSoftware(db, ess, itemsScanned, weightThreshold);
		this.banknoteSlotSoftware = new BanknoteSlotSoftware(this.amountPaid);

		this.coinSlotSoftware = new CoinSlotSoftware(this.amountPaid);
		this.returnChangeSoftware = new ReturnChangeSoftware(scs);

		
		//attach the ess and bss to the selfcheckout hardware
		this.scs.mainScanner.attach(bss);
		this.scs.baggingArea.attach(ess);	
		this.scs.banknoteValidator.attach(banknoteSlotSoftware);
		this.scs.coinValidator.attach(coinSlotSoftware);
		
		//create new change class 
	}
	
	public BigDecimal total() {
		BigDecimal total = BigDecimal.ZERO;
		for (ItemInfo i : itemsScanned) {
			total = total.add(i.price);
		}
		
		total = total.add(priceOfBags.multiply(new BigDecimal(Integer.toString(bagsUsed))));
		
		return total;
	}
	
	public BigDecimal getAmountReturned() {
		return amountReturned;
	}
	
		/**  Purpose: return the correct amount of change to the user, giving them the highest denominations first. 
	 *	 @param totalChange is the amount of change that should be provided to the user.
	 *	 @return void 
	 * */
	public void returnChange(BigDecimal totalChange) throws OverloadException, EmptyException, DisabledException {
		// create a local variable to be updated whenever a coin/banknote is emitted. 
		BigDecimal amountToBeReturned = totalChange;
		amountReturned = BigDecimal.ZERO;
		// dispense coin/banknote while there is still change left to give to the customer 
		// Case 1: change to be returned is greater than a toonie - emit banknotes.
		while(amountToBeReturned.compareTo(BigDecimal.valueOf(5)) > 0) { 
			int counter = returnChangeSoftware.getBanknoteDenomination().size() - 1; 
			while (counter > -1) {
				// store the banknote in a variable. 
				int removed = returnChangeSoftware.getBanknoteDenomination().get(counter);
				// check to see if the amount to be returned is greater than or equal to the denomination
				// because if it is not then we cannot give that note to the customer. 
				if(amountToBeReturned.compareTo(BigDecimal.valueOf(removed)) >= 0 ) {
					// get the dispenser that holds the denomination we need and emit the banknote. 
					this.scs.banknoteDispensers.get(removed).emit();
					// remove the banknote so we can release another one. 
					this.scs.banknoteOutput.removeDanglingBanknote();
					// update amountToBeReturned
					amountToBeReturned = amountToBeReturned.subtract(BigDecimal.valueOf(removed));
					amountReturned = amountReturned.add(BigDecimal.valueOf(removed));
					// break to start again at the highest denomination. 
					break;
				}
				counter--;
			}
		}
		// dispense coin/banknote while there is still change left to give to the customer 
		// Case 2: change to be returned is equal to or less than a $5 - emit coins. 
		while(amountToBeReturned.compareTo(BigDecimal.valueOf(5)) < 0) { 
			if (amountToBeReturned.compareTo(BigDecimal.valueOf(0)) != 0) {
				int counter = this.scs.coinDenominations.size() - 1; 
				while (counter > -1) {
					// store the coin in a variable 
					BigDecimal removed = this.scs.coinDenominations.get(counter);
					// check to see if the amount to be returned is greater than or equal to the denomination
					// because if it is not then we cannot give that coin to the customer. 
					if(amountToBeReturned.compareTo(removed) >= 0) {
						// get the dispenser that holds the denomination we need and emit the banknote. 
						this.scs.coinDispensers.get(removed).emit();
						// update amountToBeReturned
						amountToBeReturned = amountToBeReturned.subtract(removed);
						amountReturned = amountReturned.add(removed);
						// break to start again at the highest denomination.
						break;
					}
					// update amountToBeReturned based on the nearest $0.05
					if (amountToBeReturned.compareTo(BigDecimal.valueOf(0.02)) <= 0) {
						amountToBeReturned = BigDecimal.ZERO;
					} else if (amountToBeReturned.compareTo(BigDecimal.valueOf(0.05)) < 0) {
						amountToBeReturned = BigDecimal.valueOf(0.05);
					}
					counter--;
				}
				
			} else {
				break;
			}
		}
		
		// check to see if amountLeft is equal to 0, if it is, the correct change has been dispensed and notify the user.
		if(amountToBeReturned.compareTo(BigDecimal.ZERO) == 0) {
			System.out.println("Please take your change: $" + amountReturned);
		}
	}
	
	private void promptForBags() {
		while (true) {
			Scanner scanner = new Scanner(System.in);  
		    System.out.print("How many bags did you use? ");
		    String numOfBagsRaw = scanner.nextLine().strip(); 
		    try {
		    	this.bagsUsed = Integer.parseInt(numOfBagsRaw);
		    	if (bagsUsed > maximumBags || bagsUsed < 0) {
		    		System.err.println("Please enter a valid number");
		    		continue;
		    	}
		    	break;
		    }catch (Exception e) {
		    	System.err.println("Please enter a valid number of bags!");
		    	continue;
		    }
		}
	}
	
	private void print(BigDecimal total) {

		int widthOfReceipt = 60;
		int spaceBetweenPriceAndDesc = 3;
		
		String header = String.format("%32s\n%s\n%-4s%56s\n%-4s%56s", "START OF THE RECEIPT",
				"------------------------------------------------------------",
                "Item", "Price", "----", "----\n");
         for(char c : header.toCharArray()) {scs.printer.print(c);}
         System.out.println(header);
         
         for (ItemInfo i : itemsScanned) {
        	 //creates 60 white spaced string
        	 String receiptLine = "";
        	 
        	 int descSpaceLength = widthOfReceipt - (i.price.toString().length() + spaceBetweenPriceAndDesc);
        	 
        	 String description = "";
        	 if (i.description.length() > descSpaceLength) {
        		 description = i.description.substring(0, descSpaceLength);
        		 String whitespace = new String(new char[spaceBetweenPriceAndDesc]).replace("\0", " ");
        		 receiptLine = description.substring(0, description.length()) + whitespace + i.price.toString() + "\n";
        		 for(char c : receiptLine.toCharArray()) {scs.printer.print(c);}
        		 System.out.print(receiptLine);
        	 }else {
        		 int whitespaceLength = widthOfReceipt - i.description.length() - i.price.toString().length() - ("\n".length());
        		 String whitespace = new String(new char[whitespaceLength]).replace("\0", " ");
        		 receiptLine = i.description + whitespace + i.price.toString() + '\n';
        		 for(char c : receiptLine.toCharArray()) {scs.printer.print(c);}
        		 System.out.print(receiptLine);
        	 }
         }
         
        String totalLine = "Total: " + total;
        for(char c : totalLine.toCharArray()) {scs.printer.print(c);}
        System.out.println(totalLine);
        
        String cashLine = "Cash: " + this.amountPaid[0];
        for(char c : cashLine.toCharArray()) {scs.printer.print(c);}
        System.out.println(cashLine);
        
        String changeLine = "Change: " + getAmountReturned();
        for(char c : changeLine.toCharArray()) {scs.printer.print(c);}
        System.out.println(changeLine);
    }
	
	public void checkout() {
		promptForBags();
		
		BigDecimal total = total();
		BigDecimal totalChange = this.amountPaid[0].subtract(total);
		if (total.compareTo(this.amountPaid[0]) <= 0) {
			try {
				returnChange(totalChange);
			} catch (OverloadException | EmptyException | DisabledException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			System.out.println("Amount paid is greater than total. Printing receipt");
			print(total);
			resetVars();
		} else {
			System.out.println("Insuficient funds to complete checkout!");
		}
	}
	
	
	public boolean checkBaggingAreaAll()
	{
		double expectedWeight = 0;
		double sensitivity = 5;
		double currentWeight = this.ess.getCurrentWeight();

		
		for (ItemInfo i : itemsScanned) {
			expectedWeight += i.weight;
		}
		
		// If expected weight is not within sensitivity threshold of actual weight
		if (expectedWeight > currentWeight + sensitivity || expectedWeight < currentWeight - sensitivity)
		{
			System.out.println("Please place all items in the bagging area");
			// If bagging area is not as it should be return false
			return false;
		}
		return true;
		
	}
	
	public boolean checkBaggingAreaItem(ItemInfo item)
	{
		double itemWeight = item.weight;
		double sensitivity = 5;
		double currentWeight = this.ess.getCurrentWeight();
		double previousWeight = this.ess.getWeightAtLastEvent();
		
		
		// If expected weight is not within sensitivity threshold of actual weight
		if (previousWeight + itemWeight > currentWeight + sensitivity || previousWeight + itemWeight < currentWeight - sensitivity)
		{
			System.out.println("Please place last item in the bagging area");
			// If bagging area is not as it should be return false
			return false;
		}
		return true;
	}

	
	public void resetVars() {
		this.itemsScanned = new ArrayList<ItemInfo>();
		this.amountPaid[0] = BigDecimal.ZERO;
		this.bagsUsed = 0;
	}

	
	
	
	public void startUpGUI() {
		//does nothing for now
	}
	
}
