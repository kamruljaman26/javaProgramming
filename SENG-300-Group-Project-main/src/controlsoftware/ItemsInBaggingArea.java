package controlsoftware;

import controlsoftware.exceptions.InvalidItemException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * This class will hold all items in bagging area
 */
public class ItemsInBaggingArea {

    // hold items
    private ArrayList<ItemProduct> items;
    private BigDecimal totalPrice;

    // default constructor
    public ItemsInBaggingArea() {
        this.items = new ArrayList<>();
        totalPrice=new BigDecimal(0);
    }

    // add products
    public void addItem(ItemProduct item, BigDecimal price) throws InvalidItemException {
        if (item != null) {
            // add item
            items.add(item);
            // update total price
            totalPrice = totalPrice.add(price);
        } else {
            // null will invalid item, throw an error
            throw new InvalidItemException();
        }
    }

    // return all added items
    public ArrayList<ItemProduct> getItems(){
        return items;
    }

    // is the bag is empty?
    public boolean isEmpty(){
        return items.isEmpty();
    }

    // get total price
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
