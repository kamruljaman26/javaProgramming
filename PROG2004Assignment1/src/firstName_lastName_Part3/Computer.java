package firstName_lastName_Part3;

public abstract class Computer {

    // computer properties
    private String make;
    private String model;
    private double ram;
    private int hdd;

    // default constructor
    public Computer() {
    }

    /**
     * constructor with all parameter
     *
     * @param make  computer manufacturer
     * @param model computer model
     * @param ram   computer ram
     * @param hdd   computer hard disk
     */
    public Computer(String make, String model, double ram, int hdd) {
        this.make = make;
        this.model = model;
        this.ram = ram;
        this.hdd = hdd;
    }

    // get the computer make
    public String getMake() {
        return make;
    }

    // set the computer make
    public void setMake(String make) {
        this.make = make;
    }

    // get computer model
    public String getModel() {
        return model;
    }

    // set computer model
    public void setModel(String model) {
        this.model = model;
    }

    // get computer ram
    public double getRam() {
        return ram;
    }

    // set computer ram
    public void setRam(double ram) {
        this.ram = ram;
    }

    // get computer hdd
    public int getHdd() {
        return hdd;
    }

    // set computer hdd
    public void setHdd(int hdd) {
        this.hdd = hdd;
    }

    // print computer details, need to implement
    public abstract void printDetails();
}
