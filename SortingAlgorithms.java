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

    public static void main(String[] args) {
        int[] bong = { 21, 16, 3, 7, 23, 12 };
        InsertionSort.insertionSort(bong);
    }
}

