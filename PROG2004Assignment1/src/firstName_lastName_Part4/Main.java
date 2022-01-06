package firstName_lastName_Part4;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // create arraylist of laptop type
        ArrayList<Laptop> laptopsList = new ArrayList<>();

        // crate 2 laptop object
        Laptop laptop1 = new Laptop("Dell", "S23", 8.0, 500, 13,
                false);
        Laptop laptop2 = new Laptop();
        laptop2.setMake("LG");
        laptop2.setMake("A43");
        laptop2.setRam(8.0);
        laptop2.setHdd(1000);
        laptop2.setDisplaySize(13);
        laptop2.setTouchDisplay(true);

        // add to list
        laptopsList.add(laptop1);
        laptopsList.add(laptop2);

        // print using Iterator
        Iterator<Laptop> laptopIterator = laptopsList.iterator();
        while (laptopIterator.hasNext()) {
            laptopIterator.next().printDetails();
            System.out.println("");
        }
    }
}
