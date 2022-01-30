import java.lang.*;
import java.io.*;

// Modify the display content to suit your purposes...
public class menu {
    private static final String TITLE =
            "\nCO3325 Data Compression coursework\n" +
                    "   by FAMILYNAME-firstname_SRN\n\n" +
                    "\t********************\n" +
                    "\t0. Declaration: Sorry but part of the program was copied from the Internet! \n" +
                    "\t2. Question 2 \n" +
                    "\t3. Question 3 \n" +
                    "\t4. no attempt \n" +
                    "\t0. Exit \n" +
                    "\t********************\n" +
                    "Please input a single digit (0-4):\n";

    menu() {
        int selected = -1;
        while (selected != 0) {
            System.out.println(TITLE);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            // selected = Integer.parseInt(in.readLine());
            try {
                selected = Integer.parseInt(in.readLine());
                switch (selected) {
                    case 1:
                        q1();
                        break;
                    case 2:
                        q2();
                        break;
                    case 3:
                        q3();
                        break;
                    case 4:
                        q4();
                        break;
                }
            } catch (Exception ex) {
                System.out.println("error");
            }
        } // end while
        System.out.println("Bye!");
    }

    // Modify the types of the methods to suit your purposes...
    private void q1() {
        System.out.println("in q1");
    }

    private void q2() {
        System.out.println("in q2");
    }

    private int q3() {
        System.out.println("in q3");
        return 1;
    }

    private boolean q4() {
        System.out.println("in q4");
        return true;
    }

    public static void main(String[] args) {
        new menu();
    }
}
