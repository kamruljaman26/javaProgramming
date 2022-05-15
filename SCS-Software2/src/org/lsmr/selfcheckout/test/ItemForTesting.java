// w22 SENG 300 Assignment 3
//
//- David Garcia        30107235
//- Alexanna Little     30106236
//- Harsh Patil         30125049
//- Quyanna Campbell    30038925

package iter1tests;

import org.lsmr.selfcheckout.*;
import org.lsmr.selfcheckout.devices.*;
/**
 * Abstract base class of items for sale, each with a particular weight.
 */
public class ItemForTesting extends Item{

	/**
	 * Constructs an itemfortesting with the indicated weight.
	 * 
	 * @param weightInGrams
	 *            The weight of the item.
	 * @throws SimulationException
	 *             If the weight is &le;0.
	 */
	public ItemForTesting(double weightInGrams) {
		super(weightInGrams);
	}



}
