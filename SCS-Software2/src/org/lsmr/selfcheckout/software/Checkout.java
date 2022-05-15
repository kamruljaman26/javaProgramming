package controllers;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import java.math.BigDecimal;

import java.util.ArrayList;

public class checkout {

	private BigDecimal totalcost;
	private SelfCheckoutStation scs;
	private BigDecimal totalchange;
	private Paycoin paycoin;
	private PayWithBanknote paybanknote;
	private ArrayList<BarcodedProduct> products;

	
	//Constructor: this class is assuming a test file will create an instance of it
	//and give the total cost of the cart from ScanItem along with the relevant scs
	//to simulate a customer pressing a checkout button. When implemented, the list of
	//items should be passed as well
	public checkout (SelfCheckoutStation scs, ArrayList<BarcodedProduct> products){
		
		this.scs = scs;
		
		BigDecimal costadded = new BigDecimal(0.00);
		
		for (int i = 0; i < products.size(); i++) {
			
			costadded = costadded.add(products.get(i).getPrice());
			
		}
		
		this.totalcost = costadded;
		
		this.products = products;
		
	}
	
	//should be called by test file to simulate customer choosing to pay with banknotes
	public void choseBanknote () {
		
		if (paycoin == null) {
		
			paybanknote = new PayWithBanknote(scs, totalcost);
			
		}
				
	}
	
	//should be called by the test file to simulate customer choosing to pay with coin
	public void choseCoin() {
		
		if (paybanknote == null) {
			
			paycoin = new Paycoin(scs, totalcost);
			
		}
		
	}
	
	//should be called by the test file to simulate customer choosing to cancel payment
	public void cancelPayment() {
		paycoin = null;
		paybanknote = null;
	}
	
	//should be called by the test file to simulate customer wanting to finish payment
	public void finishPayment() {
		
		if (paycoin != null) {
			
			BigDecimal changeDue = paycoin.changeowed();
			
			if (changeDue.compareTo(BigDecimal.ZERO) <= 0) {
				
				BigDecimal changeBack = changeDue.abs();
				

				System.out.println(changeDue);
				
				printReceipt(scs, totalcost, changeBack, "Coins", products);
				
			} else {
				
				System.out.println("you have not paid enough yet!");
			}
			
			
		} else if (paybanknote != null) {
			
			BigDecimal changeDue = paybanknote.remainingAmountToBePaid();
			
			if (changeDue.compareTo(BigDecimal.ZERO) <= 0) {
				
				BigDecimal changeBack = changeDue.abs();
				
				printReceipt(scs, totalcost, changeBack, "Banknotes", products);
				
			} else {
				
				System.out.println("you have not paid enough yet!");
				
			}
			
		}
	}
	
	//receipt printing method. Does not handle receipt hardware-related problems i.e paper/ink shortage
	private void printReceipt(SelfCheckoutStation scs, BigDecimal totalcost, BigDecimal totalchange, String payType, ArrayList<BarcodedProduct> products){
		
		
		ArrayList<String> items = new ArrayList<String>();
		
		for (int i = 0; i < products.size(); i++) {
			items.add((products.get(i)).getDescription() + " $" + (products.get(i).getPrice()));
			
		}
		
		BigDecimal paid = totalcost.add(totalchange);
		
		items.add("Total: " + totalcost.toPlainString());
		items.add("Paid: " + paid.toPlainString());
		items.add("Paid_with: " + payType);
		items.add("Change: " + totalchange.toPlainString());
		
		for (int i = 0; i < items.size(); i++) {
			
			scs.printer.print('\n');
			
			for (int j = 0; j < items.get(i).length(); j++) {
				
				scs.printer.print(items.get(i).charAt(j));
			}
			
			scs.printer.print('\n');
			
		}
		scs.printer.cutPaper();
	}
}
