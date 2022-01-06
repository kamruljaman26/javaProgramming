package firstName_lastName_Part3;

public class Laptop extends Computer {

    // 2 instance variable
    private double displaySize;
    private boolean isTouchDisplay;

    public Laptop() {}

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
    public void printDetails() {
        // print computer
        System.out.println("Laptop Details: ");
        System.out.printf("Make:%s, Model:%s, Ram:%.2f GB, Hdd:%d  GB\n", getMake(), getModel(), getRam(), getHdd());
        System.out.printf("Display Size: %s in, Is have touch display: %s\n", displaySize, isTouchDisplay);
    }
}
