public class SortingAlgorithms {

    public class BubbleSort { // O(N^2)
        public static void bubbleSort(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }

    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            for (int b = 0; b < arr.length; b++) {
                System.out.print(arr[b] + ",");
            }
            System.out.println("");
        }
    }

    public class InsertionSort {
        public static void insertionSort(int[] arr) {
            int n = arr.length;
            for (int i = 1; i < n; i++) {
                int key = arr[i];
                int j = i - 1;
                while (j >= 0 && arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = key;
                for (int b = 0; b < arr.length; b++) {
                    System.out.print(arr[b] + ",");
                }
                System.out.println("");
            }
        }

    }

    public class TopDownMergeSort {

        // Merge Sort function
        public static void mergeSort(int[] arr) {
            int n = arr.length;
            if (n < 2) {
                return; // Array is already sorted
            }

            int mid = n / 2;
            int[] left = new int[mid];
            int[] right = new int[n - mid];

            // Populate left and right subarrays
            for (int i = 0; i < mid; i++) {
                left[i] = arr[i];
            }
            for (int i = mid; i < n; i++) {
                right[i - mid] = arr[i];
            }

            // Recursively sort left and right subarrays
            mergeSort(left);
            mergeSort(right);

            // Merge the sorted subarrays
            merge(arr, left, right);
        }

        // Merge function to combine two sorted arrays
        public static void merge(int[] arr, int[] left, int[] right) {
            int nL = left.length;
            int nR = right.length;
            int i = 0, j = 0, k = 0;

            while (i < nL && j < nR) {
                if (left[i] <= right[j]) {
                    arr[k++] = left[i++];
                } else {
                    arr[k++] = right[j++];
                }
            }

            while (i < nL) {
                arr[k++] = left[i++];
            }

            while (j < nR) {
                arr[k++] = right[j++];
            }
        }

        // Helper function to print an array
        public static void printArray(int[] arr) {
            for (int value : arr) {
                System.out.print(value + " ");
            }
            System.out.println();
        }


    }
        public static void main(String[] args) {
            int[] arr = { 2, 13, 16, 3, 7, 23, 12, 25 };

            System.out.println("Original Array:");
            TopDownMergeSort.printArray(arr);

            TopDownMergeSort.mergeSort(arr);

            System.out.println("\nSorted Array:");
            TopDownMergeSort.printArray(arr);
        }
}
