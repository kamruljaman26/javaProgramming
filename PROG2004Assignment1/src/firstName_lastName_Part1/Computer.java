package firstName_lastName_Part1;

public class Computer {

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

    // print computer details
    public void printDetails() {
        // print computer
        System.out.printf("Make:%s, Model:%s, Ram:%.2f GB, Hdd:%d  GB\n",
                make, model, ram, hdd);
    }

    // Main method for test
    public static void main(String[] args) {
        Computer computer1 = new Computer();
        computer1.setMake("");
        computer1.setModel("");
        computer1.setRam(16);
        computer1.setHdd(1000);
        Computer computer2 = new Computer("Intel","D12S45",8.0,500);

        // print computer
        computer2.printDetails();

        // print computer1
        computer1.printDetails();
        //update
        computer1.setHdd(100);
        computer1.printDetails();
    }
}
