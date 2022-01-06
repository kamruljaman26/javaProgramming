package firstName_lastName_Part2;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    /**
     * • Declare an ArrayList with a type parameter of Laptop
     * • Add at least 2 laptops to the ArrayList
     * • Use an Iterator (java.util.Iterator) to loop through the laptops in the ArrayList
     *   and  print out the make and model. Please note that use of any other kind of loop
     *   will not  receive any marks.
     * • Check if the ArrayList contains a particular laptop
     * • Get a laptop from the ArrayList
     * • Remove a laptop from the ArrayList
     * • Print the size of the ArrayList
     * • Clear the ArrayList
     *
     * @param args cmd line arguments
     */
    public static void main(String[] args) {
        // create arraylist of laptop type
        ArrayList<Laptop> laptopsList = new ArrayList<>();

        // crate 2 laptop object
        Laptop laptop1 = new Laptop("Dell", "S23", 8.0, 500, 13,
                false);
        Laptop laptop2 = new Laptop("LG", "A43", 8.0, 1000, 13,
                true);
        Laptop laptop3 = new Laptop("Google", "A43", 8.0, 1000, 13,
                true);

        // add to list
        laptopsList.add(laptop1);
        laptopsList.add(laptop2);

        // print using Iterator
        Iterator<Laptop> laptopIterator = laptopsList.iterator();
        while (laptopIterator.hasNext()) {
            laptopIterator.next().printDetails();
            System.out.println("\n");
        }

        // find a laptop in the list
        System.out.println(laptopsList.contains(laptop1));
        System.out.println(laptopsList.contains(laptop3));
        System.out.println();

        // get a computer from list
        Computer computer = laptopsList.get(0);
        computer.printDetails();
        System.out.println("\n");

        // Remove a laptop from the ArrayList & print size
        laptopsList.remove(0);
        System.out.println("List Size: " + laptopsList.size());

        // clear and print size
        laptopsList.clear();
        System.out.println("List Size: " + laptopsList.size());

        // call 2nd method
        createAndPrint(new Computer("MI", "M123", 16.0, 2000));
    }

    /**
     * In the class that contains the main method, create a second method that takes a Computer as a parameter.
     * Write the code to print out the computers make and model using the  Computer accessor methods.
     * Then create an object of type Laptop and an object of type  Computer in the main method and
     * use the method you have just created to demonstrate  polymorphism.
     */
    public static void createAndPrint(Computer computer){

        System.out.println("\nInside 2nd Method");
        System.out.println("Computer: ");
        // print computer make and model
        System.out.println("Make: "+computer.getMake());
        System.out.println("Model: "+computer.getModel());

        Laptop laptop = new Laptop("MI", "M123", 16.0, 2000, 13,
                false);
        System.out.println("\nLaptop: ");
        System.out.println("Make: "+laptop.getMake());
        System.out.println("Model: "+laptop.getModel());
        System.out.println("Ram: "+laptop.getRam());
        System.out.println("HDD: "+laptop.getHdd());
        System.out.println("Display Size: "+laptop.getDisplaySize());
        System.out.println("Is have Touch Display? : "+laptop.isTouchDisplay());
    }
}
