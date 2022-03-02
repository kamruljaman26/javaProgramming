import java.util.*;

public class Assignment1 {
    
    /*
     * Find the longest substring match in 2 string
     */
    private int[][] buildMatrix(String longS, String shortS) {
        int m = longS.length();
        int n = shortS.length();
        char[] X = longS.toCharArray();
        char[] Y = shortS.toCharArray();
        int[][] matrix = new int[m + 1][n + 1];

        // init the matrix
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    matrix[i][j] = 0;
                } else if (X[i - 1] == Y[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }

        return matrix;
    }

    /**
     * find all matched substring from created matrix
     */
    private ArrayList<String> findAllMatches(String longS, String shortS) {
        // store all sub strings from matrix
        Set<String> subStrings = new HashSet<>();
        int[][] matrix = buildMatrix(longS, shortS);

        // iterate matrix and find all unique matches
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {

                // if the index value is == 1
                if (matrix[i][j] == 1) {

                    int ref1 = j - 1, ref2 = 0; // ref index value for matched string
                    int tempi = i, tempj = j; // temporary i and j for find match start and end index

                    // find the end index
                    while (tempi + 1 <= matrix.length
                            && tempj + 1 <= matrix[tempi].length
                            && matrix[tempi][tempj] > 0) {
                        ref2 = tempj; // set/update end index
                        tempi += 1;
                        tempj += 1;
                    }

                    // ref1 & ref2 both should be greater than 0
                    // ref1 cat be same to ref2,
                    // and we will not consider 1 char as a match, here we are ignoring it
                    if (ref1 > 0 && ref2 > 0 && ref1 != ref2 && ref2 - ref1 != 1) {
                        subStrings.add(shortS.substring(ref1, ref2).trim());
                    }
                }
            }
        }

        // convert the set to array and return
        // sort the list based on string length
        ArrayList<String> allMatches = new ArrayList<>(subStrings);
        allMatches.sort((o1, o2) -> o2.length() - o1.length());
        return allMatches;
    }

    /**
     * find all matches and sort by length and return an arraylist
     */
    public String findLongestMatch(String longS, String shortS) {
        ArrayList<String> allMatches = findAllMatches(longS, shortS);
        return allMatches.get(0);
    }

    /**
     * Print all matches
     */
    public String displayAllLongestMatches(String longS, String shortS) {
        ArrayList<String> allMatches = findAllMatches(longS, shortS);
        return allMatches.toString();
    }

    /**
     * replace the match in long string
     */
    public String replaceLongestMatched(String longS, String shortS) {
        String longestMatch = findLongestMatch(longS, shortS);
        return longS.replace(longestMatch, "");
    }

/*    public static void main(String[] args) {
        String long1 = "this_is_xx_an_example_and_another_example",
                long2 = "this is xx an example and another example",
                short1 = "xxx_n_example_xx",
                short2 = "xxx n example xx";

        Assignment1 a = new Assignment1();
        int[][] ints = a.buildMatrix(long2, short2);
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.printf("%d, ", ints[i][j]);
            }
            System.out.println();
        }
    }*/
}
