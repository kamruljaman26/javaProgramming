package controlsoftware;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import controlsoftware.exceptions.InvalidItemException;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BarcodeScannerObserver;

public class ProcessScannedItem implements BarcodeScannerObserver {

    /**
     * Should be static so we can disable scan option from anywhere
     */
    private static boolean isEnabled = false;

    private BarcodeLookup lookup;
    private ElectronicScale scale;
    private double weightTolerance = 25; // The maximum weight in grams that an Item's weight can differ from its weight stored in the ItemProduct dictionary

    /**
     * We will add scanned item price into checkout and handle checkout process
     */
    private ItemsInBaggingArea itemsBag;

    public ItemsInBaggingArea getItems() {
        return itemsBag;
    }


    /* (Brody)
		DONT HAVE TO WORRY ABOUT:
			- the graphical user interface
			- **** products without barcodes ****
			- credit/debit cards
			- returning change to the customer
		
		Assuming there is a HashMap that acts as a dictionary for all the items that can be scanned via barcode.
		
		1) We are given a barcode from the barcode scanner
		
		2) Lookup Barcode in BarcodedProduct dictionary to get PRICE
			- Hashmap with its key = BARCODE and value = ItemProduct
				- ItemProduct is a wrapper class that combines BarcodedItem and BarcodedProduct objects into one
			- If barcode cannot be found in dictionary then notify the user via the touch screen and stop
		
		3) Get the price from BarcodedProduct
		
		4) Check if the product is priced per unit or by per KG
			- If by unit, just add the price to the customer's current total
			- Otherwise get the weight (in KG) of the item and multiply that by the product's price and add this to the customers total 
			
		5) Wait X number of seconds for some signal from the scale that the customer has placed the item in the bagging area
			- If X number of seconds pass without being signaled by the scale, block scanning and touch screen input until signal is received or employee override
		
		6) Done
	 */

    /*
     *  Have 1 bool to signal scale observer that we have just scanned something
     *
     *   Have 1 bool to inform us if the scale observer has
     */

    public ProcessScannedItem(BarcodeLookup lookup, ElectronicScale scale, Checkout checkout) {
        this.lookup = lookup;
        this.scale = scale;
        itemsBag = new ItemsInBaggingArea(); // init itemsBag
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
        isEnabled = true;
    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
        isEnabled = false;
    }

    public static void disabled() {
        isEnabled = false;
    }

    @Override
    public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
        if (isEnabled) {
            // Lookup Barcode in out lookup
            ItemProduct scannedItem = lookup.get(barcode);
            if (scannedItem != null) { //Item found in lookup, proceed
                BigDecimal scannedItemPrice = scannedItem.getPrice();
                double scannedItemWeight = scannedItem.getWeight();
                double scannedItemWeightInKG = scannedItemWeight / 1000; // Convert grams to KG
                if (scannedItem.isPerUnit()) {
                    //Item is priced per unit, since only one item can be scanned at once, just
                    //add the price of this item to the customer's total

//                    checkout.addToTotal(scannedItemPrice); //NOT IMPLEMENTED YET!!!

                    /*
                     * update: hold total price and item in item bag
                     */
                    try {
                        itemsBag.addItem(scannedItem,scannedItemPrice);
                    } catch (InvalidItemException e) {
                        e.printStackTrace();
                    }

                } else {
                    //Item is priced per KG, get the weight of item in KG and multiply by price,
                    //add this to customers total

//                    checkout.addToTotal(scannedItemPrice.multiply(new BigDecimal(scannedItemWeightInKG))); //NOT IMPLEMENTED YET!!!

                    /*
                     * update: hold total price and item in item bag
                     */
                    try {
                        itemsBag.addItem(scannedItem,
                                scannedItemPrice.multiply(new BigDecimal(scannedItemWeightInKG)));
                    } catch (InvalidItemException e) {
                        e.printStackTrace();
                    }
                }

                //Customer's total has been updated, now wait for the scanned item to be placed in the bagging area
                // Not sure if this is the best way to handle it VVV
                try {
                    double weightBefore = scale.getCurrentWeight(); // In grams
                    double targetWeight = weightBefore + scannedItemWeight; // What we expect the scale to read after placing the item on it
                    //Wait for 5 seconds
                    TimeUnit.SECONDS.sleep(5);

                    // while (bool) { wait for scale observer to set bool false when it is notified of a weight change }
                    // maybe the sleep could be interrupted if the scale observer updates bool

                    double weightAfter = scale.getCurrentWeight(); // In grams

                    // Check if the weight has increase by approximately the weight of the scanned item since we last checked
                    if ((weightAfter >= (targetWeight - weightTolerance)) && (weightAfter <= targetWeight + weightTolerance)) {
                        //Weight change is valid with the scanned item, we are done
                        return;
                    } else {
                        //Weight change is not valid, need to inform relevant observers and
                        //block input/scanning from user until issue is corrected
                    }
                } catch (OverloadException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // Not sure if this is the best way to handle it ^^^
                // Will have to look into possibly having the Scale Observer signal this class to inform it of a weight change
                // Instead of just sleeping for 5 seconds then re-checking
            } else {
                //Item not found in lookup
                //Report Error to touchscreen
                System.out.println("ERROR! COULD NOT FIND BARCODE IN LOOKUP!");
                return;
            }
        }
        return;
    }
}
