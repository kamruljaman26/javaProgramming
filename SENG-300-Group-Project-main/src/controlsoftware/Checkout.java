package controlsoftware;

import controlsoftware.exceptions.InvalidItemException;
import controlsoftware.payments.PayWithCoin;
import controlsoftware.payments.PaymentMethod;

import java.math.BigDecimal;

/**
 *  Checkout class will calculate
 */
public class Checkout {

	private ItemsInBaggingArea items;
	private PaymentMethod paymentMethod;

	public Checkout(ItemsInBaggingArea items, PaymentMethod paymentMethod) {
		this.items = items;
		this.paymentMethod = paymentMethod;
	}

	public void checkout() throws InvalidItemException {

		// handle exceptions
		if(items == null){
			throw new InvalidItemException();
		}else if(paymentMethod == null){
			throw new InvalidItemException();
		}

		// process to payment system, next process will handle in specific method class
		paymentMethod.processPayment(items);
	}
}
