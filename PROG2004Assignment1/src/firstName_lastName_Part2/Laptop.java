package firstName_lastName_Part2;

import java.util.ArrayList;
import java.util.Iterator;

public class Laptop extends Computer{

    // 2 instance variable
    private double displaySize;
    private boolean isTouchDisplay;

    public Laptop() {
    }

    public Laptop(String make, String model, double ram, int hdd, double displaySize, boolean isTouchDisplay) {
        super(make, model, ram, hdd);
        this.displaySize = displaySize;
        this.isTouchDisplay = isTouchDisplay;
    }

    public double getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(double displaySize) {
        this.displaySize = displaySize;
    }

    public boolean isTouchDisplay() {
        return isTouchDisplay;
    }

    public void setTouchDisplay(boolean touchDisplay) {
        isTouchDisplay = touchDisplay;
    }

    @Override
    public void printDetails(){
        System.out.println("Laptop Details: ");
        super.printDetails();
        System.out.printf("Display Size: %s in, Is have touch display: %s",displaySize,isTouchDisplay);
    }
}
