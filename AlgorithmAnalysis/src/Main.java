import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Algorithm algo = new Algorithm();

    // datatype
    private static class SortData {
        int acTime;
        int dcTime;

        public SortData(int acTime, int dcTime) {
            this.acTime = acTime;
            this.dcTime = dcTime;
        }
    }

    // Create int array from file
    public static int[] createArrFile(String file) throws FileNotFoundException {
        BufferedReader bf = new BufferedReader(new FileReader(file));
        Scanner sc = new Scanner(bf);
        int size = sc.nextInt();
        int[] arr = new int[size];
        int idx = 0;
        while (sc.hasNext()) {
            if (idx >= size) break;
            arr[idx] = sc.nextInt();
            idx++;
        }
        return arr;
    }

    // test bubble sort
    public static SortData testBubble(int[] arr) {
        // Ascending
        long startTime = System.currentTimeMillis();
        algo.bubbleSort(arr.clone(), true);
        long stopTime = System.currentTimeMillis();
        int acTime = (int) ((stopTime - startTime));

        // Descending
        startTime = System.currentTimeMillis();
        algo.bubbleSort(arr.clone(), false);
        stopTime = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        int dcTime = (int) ((stopTime - startTime));

        // return both time as a datatype
        return new SortData(acTime, dcTime);
    }

    // test bubble sort
    public static SortData testMerge(int[] arr) {
        // Ascending
        long startTime = System.currentTimeMillis();
//        arr = new int[]{2, 41, 45, -23, 2, 42, 532, 5335, 23, 24};
        algo.mergeSort(arr.clone(), true);
        long stopTime = System.currentTimeMillis();
        int acTime = (int) ((stopTime - startTime));

        // Descending
        startTime = System.currentTimeMillis();
        algo.bubbleSort(arr.clone(), false);
        stopTime = System.currentTimeMillis();
        int dcTime = (int) ((stopTime - startTime));
        // return both time as a datatype
        return new SortData(acTime, dcTime);
    }

    // test bubble sort
    public static SortData testQuick(int[] arr) {
        // Ascending
        long startTime = System.currentTimeMillis();
//        arr = new int[]{2, 41, 45, -23, 2, 42, 532, 5335, 23, 24};
        algo.quickSort(arr, true);
        long stopTime = System.currentTimeMillis();
        int acTime = (int) ((stopTime - startTime));
//        System.out.println(Arrays.toString(arr));

        // Descending
        startTime = System.currentTimeMillis();
        algo.quickSort(arr, false);
        stopTime = System.currentTimeMillis();
        int dcTime = (int) ((stopTime - startTime));
//        System.out.println(Arrays.toString(arr));

        // return both time as a datatype
        return new SortData(acTime, dcTime);
    }

    // format string
    // we are showing the output in millisecond so can analyze much better
    public static String format(String name, SortData sd) {
        return String.format("%s: = Ascending : %d millisecond, Descending : %d millisecond", name, sd.acTime, sd.dcTime);
    }

    /**
     * Main
     *
     * @param args cmd
     */
    public static void main(String[] args) {
        try {
            for (int i = 1; i <= 3; i++) {

                // init array
                String fileName = "input" + i + ".txt";
//                System.out.println(fileName);
                int[] arrFile = createArrFile(fileName);

                // Bubble Sort
                SortData sdBubble = testBubble(arrFile);
                System.out.println(format("Bubble Sort", sdBubble));

                // Merge Sort
                SortData sdMerge = testMerge(arrFile.clone());
                System.out.println(format("Merge Sort", sdMerge));

                // Quick Sort
                SortData sdQuick = testQuick(arrFile.clone());
                System.out.println(format("Quick Sort", sdQuick));

                // print best
                String acBest, dcBest;
                if (sdBubble.acTime < sdMerge.acTime && sdBubble.acTime < sdQuick.acTime) {
                    acBest = "Bubble Sort";
                } else if (sdMerge.acTime < sdQuick.acTime) {
                    acBest = "Merge Sort";
                } else {
                    acBest = "Quick Sort";
                }
                if (sdBubble.dcTime < sdMerge.dcTime && sdBubble.dcTime < sdQuick.dcTime) {
                    dcBest = "Bubble Sort";
                } else if (sdMerge.dcTime < sdQuick.dcTime) {
                    dcBest = "Merge Sort";
                } else {
                    dcBest = "Quick Sort";
                }

                System.out.printf("Best Sorting: = Ascending : %s, Descending : %s\n\n", acBest, dcBest);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
