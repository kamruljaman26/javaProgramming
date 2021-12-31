/**
 * Some commonly used sorting Algorithm
 * All sorting algorithm can sort ascending or descending based on input parameter
 * isAscending or not!
 *
 * @author younus
 */
public class Algorithm {

    public void bubbleSort(int arr[], boolean isAscending) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (isAscending && arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } else if (!isAscending && arr[j] < arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    private void merge(int arr[], int l, int m, int r, boolean isAscending) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (isAscending) {
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] = R[j];
                    j++;
                }
            } else {
                if (L[i] >= R[j]) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] = R[j];
                    j++;
                }
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public void mergeSort(int arr[], int l, int r, boolean isAscending) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m, isAscending);
            mergeSort(arr, m + 1, r, isAscending);

            // Merge the sorted halves
            merge(arr, l, m, r, isAscending);
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public void mergeSort(int arr[], boolean isAscending) {
        mergeSort(arr, 0, arr.length - 1, isAscending);
    }


    // A utility function to swap two elements
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int partition(int arr[], int begin, int end, boolean isAscending) {
        int pivot = arr[getRandomNumber(begin, end)];
//        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j <= end - 1; j++) {
            if (isAscending && arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
            if (!isAscending && arr[j] >= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    public void quickSort(int arr[], int begin, int end, boolean isAscending) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end, isAscending);

            quickSort(arr, begin, partitionIndex - 1, isAscending);
            quickSort(arr, partitionIndex + 1, end, isAscending);
        }
    }

    public void quickSort(int[] arr, boolean isAscending) {
        int m = (arr.length) / 2;
        quickSort(arr, 0, arr.length - 1, isAscending);
    }
}
