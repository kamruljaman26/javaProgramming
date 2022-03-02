import java.lang.*;
import java.io.*;

// Modify the display content to suit your purposes...
public class menu {
    private static final String TITLE =
            "\nCO3325 Data Compression coursework\n" +
                    "   by FAMILYNAME-firstname_SRN\n\n" +
                    "\t********************\n" +
                    "\t1. Assignment 1 \n" +
                    "\t2. Assignment 2 \n" +
                    "\t0. Exit \n" +
                    "\t********************\n" +
                    "Please input a single digit (0-2):\n";

    public menu() {
        int selected = -1;
        while (selected != 0) {
            System.out.println(TITLE);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            try {
                selected = Integer.parseInt(in.readLine());
                switch (selected) {
                    case 1:
                        assignment1();
                        break;
                    case 2:
                        assignment2();
                        break;
                    case 0:
                        break;
                }
            } catch (Exception ex) {
                System.out.println("error");
            }
        } // end while
        System.out.println("Bye!");
    }

    // Modify the types of the methods to suit your purposes...
    private void assignment1() {
        // all example string/test data
        String long1 = "this_is_xx_an_example_and_another_example",
                long2 = "this is xx an example and another example",
                short1 = "xxx_n_example_xx",
                short2 = "xxx n example xx";

        Assignment1 a = new Assignment1();

        // print longest match
        System.out.println("\n********** Assignment 1 **********");
        System.out.println("Longest Match: " + a.findLongestMatch(long2, short2));
        System.out.println("All Matches: " + a.displayAllLongestMatches(long2, short2));
        System.out.println("Replaced: " + a.replaceLongestMatched(long2, short2)+"\n");
    }

    private void assignment2() {
        // all example string/test data
        String word1 = "ababcbababaa", word2 = "this is xx an example and another example";

        // print longest match
        System.out.println("\n********** Assignment 2 **********");
        System.out.println("Data: "+ word1);
        System.out.println("Encoded: "+ Assignment2.compress(word1));
        System.out.println("Decncoded: "+ Assignment2.decompress(Assignment2.compress(word1)));
        System.out.println("Data: "+ word2);
        System.out.println("Encoded: "+ Assignment2.compress(word2));
        System.out.println("Decncoded: "+ Assignment2.decompress(Assignment2.compress(word2)));
    }

    public static void main(String[] args) {
        new menu();
    }
}
