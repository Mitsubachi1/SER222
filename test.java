public class test {

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
}