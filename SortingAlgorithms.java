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

        public static void mergeSort(int[] arr, int low, int high) {
            if (low < high) {
                int mid = (low + high) / 2;
                System.out.println("Sorting range: low=" + low + ", mid=" + mid + ", high=" + high);
                mergeSort(arr, low, mid);
                mergeSort(arr, mid + 1, high);
                merge(arr, low, mid, high);
            }
        }
    
        // Merge function to combine two sorted arrays
        public static void merge(int[] arr, int low, int mid, int high) {
            int nL = mid - low + 1;
            int nR = high - mid;
            int[] left = new int[nL];
            int[] right = new int[nR];
    
            for (int i = 0; i < nL; i++) {
                left[i] = arr[low + i];
            }
            for (int i = 0; i < nR; i++) {
                right[i] = arr[mid + i + 1];
            }
    
            int i = 0, j = 0, k = low;
    
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

            TopDownMergeSort.mergeSort(arr, 0 , arr.length -1);

            System.out.println("\nSorted Array:");
            TopDownMergeSort.printArray(arr);
        }
}
