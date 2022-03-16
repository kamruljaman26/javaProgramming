package controlsoftware.payments;

import controlsoftware.Checkout;
import controlsoftware.ItemProduct;
import controlsoftware.ItemsInBaggingArea;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PayWithBanknote extends PaymentMethod{

    /*
     * method parameters can be change according to plan, we can change it
     * in PaymentMethod abstract class, also if need we can add common method for both
     * payment system.
     */

    /**
     * Handle Payment using BankNote
     * @param items hold all items
     */
    @Override
    public void processPayment(ItemsInBaggingArea items) {
        // required values
        BigDecimal totalPrice = items.getTotalPrice();
        ArrayList<ItemProduct> itemProducts = items.getItems();

        // todo: to required task for process payment

    }
}
