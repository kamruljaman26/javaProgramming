package controllers;

import java.util.Map;
import org.lsmr.selfcheckout.*;
import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.observers.*;
import org.lsmr.selfcheckout.products.*;
import java.util.ArrayList;

public class ScanAndBag implements ElectronicScaleObserver, BarcodeScannerObserver{
	
	private double latestItemWeight;	// Keeps track of the latest item's weight that is placed on the scale
	private double scannedItemWeight;	// Keeps track of the latest item's weight that has been scanned
	private double latestScaleWeight;	// Keeps track of the scale's latest known weight 
	private double scaleSensitivity;	// Keeps track of the scale sensitivity
//	private boolean overloaded;
//	private boolean excessiveRemoved;
	private int sensitiveWeights;		// Keeps track of the total weight that has been placed on the scale since weightChanged()
	private BarcodeScanner theScanner;	// Reference to the scanner
	private Map <Barcode,BarcodedProduct> hashMapProduct;	// HashMap of Products
	private Map <Barcode,BarcodedItem> hashMapItem;			// HashMap of Items
	private ArrayList<Product> theProducts = new ArrayList<Product>();	// An arrayList that keeps track of scanned products
	private ArrayList<Item> theItems = new ArrayList<Item>();	// An arrayList that keeps track of scanned items
	
	// The constructor, the checkout station and the respective hashMaps for the Items and Products should be passed into it
	public ScanAndBag(SelfCheckoutStation theStation, Map <Barcode, BarcodedProduct> productMap,
			Map <Barcode, BarcodedItem> itemMap)
	{
		this.theScanner = theStation.scanner;
		this.scannedItemWeight = 0;
		theStation.scanner.attach(this);
		theStation.scale.attach(this);
		this.sensitiveWeights = 0;
		this.scaleSensitivity = theStation.scale.getSensitivity();
//		this.excessiveRemoved = false;
		this.hashMapItem = itemMap;
		this.hashMapProduct = productMap;
		try {
			this.latestScaleWeight = theStation.scale.getCurrentWeight();
//			this.overloaded = false;
		} catch (OverloadException e) {
			this.theScanner.disable();
//			this.overloaded = true;
		}
		
	}
	
	

	// Whenever a new item is to be scanned, this method should be called by the class 
	// observing scans to check whether or not the previous item has been checked or not.
//	public void checkBaggingArea(double weight) throws OverloadException
//	{
//		if (overloaded)
//			this.theScanner.disable();
//		else if (excessiveRemoved) {
//			this.excessiveRemoved = false;
//			this.theScanner.enable();
//		}
			
//		else if (weight < scaleSensitivity) {
//			sensitiveWeights += weight;
//			this.theScanner.enable();
//		}
//		else if (latestItemWeight == weight) {
//			this.theScanner.enable();
//		}
//		else 
//			this.theScanner.disable();		
		
//	}
	
	// This method is only a place holder for an idea and does not have proper implementation.
	// Not intended for use(for now).
//	public boolean checkItemPlaced(Item item, ElectronicScale theScale) throws OverloadException, InterruptedException
//	{
//		
//		if (isItemPlaced(item))
//		{
//			System.out.println("The item is in the bagging area");
//			return true;
//		}
//		else
//		{
//			TimeUnit.SECONDS.sleep(5);
//			if(!isItemPlaced(item)) {
//				System.out.println("Please place the item in the bagging area!");
//				return false;
//			}
//			return true;
//		}
//	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		
		System.out.println("The scale has been enabled");
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		
		System.out.println("The scale has been disabled");
		
	}

	// Whenever an item is added to the scale and it is the latest scanned item 
	// this method will enable the scanner so that further scans can be made.
	@Override
	public void weightChanged(ElectronicScale scale, double weightInGrams)
	{
		if(latestScaleWeight < weightInGrams)
		{
			// An item is added
			latestItemWeight = (weightInGrams - (latestScaleWeight + sensitiveWeights));
			sensitiveWeights = 0;
			latestScaleWeight = weightInGrams;
			
			double epsilon = 0.0001;
			if((Math.abs(latestItemWeight - scannedItemWeight) < epsilon))
			{
				this.theScanner.enable();
			}
			else {
				this.theScanner.disable();
			}
		}
		else {
			// Case where an item is removed
		}
	}

	@Override
	public void overload(ElectronicScale scale) {
		
//		this.overloaded = true;
		this.theScanner.disable();
		System.out.println("Excessive weight has been placed, please remove!");
		
	}

	// Whenever the Item causing overload is removed from the scale this method
	// will enable the scanner so that new scans can be made.
	@Override
	public void outOfOverload(ElectronicScale scale) {
		
//		this.overloaded = false;
		this.theScanner.enable();
		System.out.println("The excessive weight has been removed, you can continue scanning.");
		
	}


	// Whenever a barcode is scanned this method will fetch the corresponding item and product information.
	// With the item data, weight will be acquired so that it can be used to evaluate whether or not an item
	// is placed on the scale. Product information will be passed to an arrayList to enable further use by
	// other methods. This class will also disable the scanner if the scanned item weight is not less than the
	// sensitivity so that a new item is not scanned before the requirements conserning the scale are not met.
	
	@Override
	public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
		
		BarcodedItem item1 = hashMapItem.get(barcode);
		BarcodedProduct product1 = hashMapProduct.get(barcode);
		theProducts.add(product1);
		theItems.add(item1);
		scannedItemWeight = item1.getWeight();
		
		if (scannedItemWeight <= scaleSensitivity)
		{
			sensitiveWeights += scannedItemWeight;
		}
		else {
			this.theScanner.disable();
		}
		
	}

	// Get method so that other classes can get the scanned products.
	public ArrayList<Product> getTheProducts()
	{
		return theProducts;
	}
	
	// Get method so that other classes can get the scanned items.
	public ArrayList<Item> getTheItems()
	{
		return theItems;
	}


}
