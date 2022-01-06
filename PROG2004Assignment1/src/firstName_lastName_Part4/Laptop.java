package firstName_lastName_Part4;

public class Laptop implements Computer {

    // 2 instance variable
    private String make = null;
    private String model = null;
    private double ram = 0.0;
    private int hdd = 0;
    private double displaySize;
    private boolean isTouchDisplay;

    public Laptop() {
    }

    public Laptop(String make, String model, double ram, int hdd, double displaySize, boolean isTouchDisplay) {
        this.make = make;
        this.model = model;
        this.ram = ram;
        this.hdd = hdd;
        this.displaySize = displaySize;
        this.isTouchDisplay = isTouchDisplay;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getRam() {
        return ram;
    }

    public void setRam(double ram) {
        this.ram = ram;
    }

    public int getHdd() {
        return hdd;
    }

    public void setHdd(int hdd) {
        this.hdd = hdd;
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
