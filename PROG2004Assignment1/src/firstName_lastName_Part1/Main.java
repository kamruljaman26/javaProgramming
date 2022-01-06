package firstName_lastName_Part1;

public class Main {
    // Main method for test
    public static void main(String[] args) {
        Computer computer1 = new Computer();
        computer1.setMake("");
        computer1.setModel("");
        computer1.setRam(16);
        computer1.setHdd(1000);
        Computer computer2 = new Computer("Intel", "D12S45", 8.0, 500);

        // print computer
        computer2.printDetails();

        // print computer1
        computer1.printDetails();
        //update
        computer1.setHdd(100);
        computer1.printDetails();
    }
}
