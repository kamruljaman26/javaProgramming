package controlsoftware.payments;

import controlsoftware.Checkout;
import controlsoftware.ItemsInBaggingArea;

public abstract class PaymentMethod {
    public abstract void processPayment(ItemsInBaggingArea items);
}
