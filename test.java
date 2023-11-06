/*public class test {

    public static void main(String[] c) {
        int n;

        for (int i = 0; i < n; i++) {
            for (int j = i + 10; j < n; j++) {
                System.out.print("");
            }
        }

        for (int i = 1; i < n; i *= 3) {
            for (int j = 0; j < n; j++) {
                System.out.print("");
            }
        }

        int address = 0, step = 100;
        while (address < n) {
            while (step * 2 < n) {
                System.out.print("");
                step *= 2;
            }
            step = 100;
            address += 10;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 10; j < n; j++) {
                System.out.print("");
            }
        }
    }

}

public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
    int i = lo, j = mid+1;
    for(int k = lo; k <= hi; k++){
        aux[k]= a[k];
    }
    for (int k = lo; k <= hi; k++){
        if (i > mid){a[k] = aux[j++];}
        else if (j > hi){a[k] = aux[i++];}
        else if (less(aux[j], aux[i])){a[k] = aux[j++];}
        else {a[k] = aux[i++];}
    }
}*/
public class test {

    public static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            //System.out.println("Sorting range: low=" + low + ", mid=" + mid + ", high=" + high);
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
        printArray(arr);
    }
    // Helper function to print an array
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = { 8,9,17,4,3,20,25,5 };

        System.out.println("Original Array:");
        printArray(arr);

        mergeSort(arr, 0 , arr.length -1);

        System.out.println("\nSorted Array:");
        printArray(arr);
    }

}