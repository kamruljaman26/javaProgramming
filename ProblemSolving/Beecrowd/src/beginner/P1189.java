package beginner;

import java.util.Scanner;

public class P1189 {
    public static void main(String[] args) {
        double[][] arr = new double[4][4];

        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();

        // init all index values
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = sc.nextDouble();
            }
        }

        // find sum
        double sum = 0;
        int counter = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr.length / 2 <= i) break;

            // first phase
            for (int j = 0; j < i; j++) {
                counter++;
                sum += arr[i][j];
            }
        }

        for (int i = arr.length - 1; i > 1; i--) {
            // first phase
            if (arr.length / 2 <= i) break;
            for (int j = 0; j < i; j++) {
                counter++;
                sum += arr[i][j];
            }
        }

        if (in.equalsIgnoreCase("S")) {
        }
        if (in.equalsIgnoreCase("M")) {
        }
    }
}
