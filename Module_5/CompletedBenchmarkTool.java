package edu.ser222.m02_01; //server
//package Module_5; //local

import java.util.Random;
import java.text.DecimalFormat;
/*
 *This program benchmarks all sorting methodslemme check

 * 
 * Completion time: 10 hours
 *
 * @author Angel Chiquito, Acuna, Sedgewick
 * @version 10/30/23
 */

public class CompletedBenchmarkTool implements BenchmarkTool {

    /***************************************************************************
     * START - SORTING UTILITIES, DO NOT MODIFY (FROM SEDGEWICK) *
     **************************************************************************/

    public static void insertionSort(Comparable[] a) {
        int N = a.length;

        for (int i = 1; i < N; i++) {
            // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
    }

    public static void shellsort(Comparable[] a) {
        int N = a.length;
        int h = 1;

        while (h < N / 3)
            h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...

        while (h >= 1) {
            // h-sort the array.
            for (int i = h; i < N; i++) {
                // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h = h / 3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /***************************************************************************
     * END - SORTING UTILITIES, DO NOT MODIFY *
     **************************************************************************/

    // ! TODO: implement interface methods.

    public Integer[] generateTestDataBinary(int size) {
        Integer[] result = new Integer[size];
        int mid = size / 2;
        for (int i = 0; i < mid; i++) {
            result[i] = 0;
        }
        for (int i = mid; i < size; i++) {
            result[i] = 1;
        }
        return result;

    }

    /**
     * Generates an array of integers where half the data is 0s, half the
     * remainder is 1s, half the reminder is 2s, half the reminder is 3s, and so
     * forth.
     * 
     * @param size number of elements in the array.
     * @return generated test set.
     */
    public Integer[] generateTestDataHalves(int size) {
        Integer[] result = new Integer[size];
        int i = 0;
        int mid = size / 2;
        int remainder = 0;
        for (i = 0; i < mid; i++) { // set first half to 0
            result[i] = 0;
        }
        remainder++; // now remainder at 1 proceeds to increase as more halving occurs
        while (i < size - 1) {
            mid = (int) (Math.ceil((size - mid) / 2.0) + i);
            // distance from end and mid divided by 2, then proceed to the for loop
            for (int j = i; j < mid; j++) {
                result[j] = remainder;
                i++;
            }
            remainder++;
            if (i == size - 1) { // to offload the last element
                result[i] = remainder;
                i++;
            }

        }
        return result;
    }

    /**
     * Generates an array of integers where half the data is 0s, and half random
     * int values. All values will be positive.
     * 
     * @param size
     * @return
     */
    public Integer[] generateTestDataHalfRandom(int size) {
        Integer[] result = new Integer[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            if (i < size / 2) {
                result[i] = 0;
            } else {
                result[i] = random.nextInt(Integer.MAX_VALUE);
            }
        }

        return result;
    }

    /**
     * Computes the double formula value for two run times.
     * 
     * @param t1 first time
     * @param t2 second time
     * @return b value
     */
    public double computeDoublingFormula(double t1, double t2) {
        double b = Math.log(t2 / t1) / Math.log(2);
        return b;
    }

    /**
     * Computes an empirical b value for insertion sort by running it on a pair
     * of inputs and using the doubling formula.
     * 
     * @param small small test data array
     * @param large large test data array. twice the same of small array.
     * @return b value
     */
    public double benchmarkInsertionSort(Integer[] small, Integer[] large) {
        Stopwatch smallTest = new Stopwatch();
        insertionSort(small);
        double t1 = smallTest.elapsedTime();
        Stopwatch largeTest = new Stopwatch();
        insertionSort(large);
        double t2 = largeTest.elapsedTime();
        return computeDoublingFormula(t1, t2);
    }

    /**
     * Computes an empirical b value for shellsort sort by running it on a pair
     * of inputs and using the doubling formula.
     * 
     * @param small small test data array
     * @param large large test data array. twice the same of small array.
     * 
     * @return b value
     */
    public double benchmarkShellsort(Integer[] small, Integer[] large) {
        Stopwatch smallTest = new Stopwatch();
        shellsort(small);
        double t1 = smallTest.elapsedTime();
        Stopwatch largeTest = new Stopwatch();
        shellsort(large);
        double t2 = largeTest.elapsedTime();
        return computeDoublingFormula(t1, t2);
    }

    /**
     * Runs the two sorting algorithms on the three types of test data to
     * produce six different b values. B values are displayed to the user.
     * 
     * @param size size of benchmark array. to be doubled later.
     */
    public void runBenchmarks(int size) {
        Integer[] smallArrayBin = generateTestDataBinary(size);
        Integer[] largeArrayBin = generateTestDataBinary(size * 2);
        Integer[] smallArrayHalf = generateTestDataHalves(size);
        Integer[] largeArrayHalf = generateTestDataHalves(size * 2);
        Integer[] smallArrayRandom = generateTestDataHalfRandom(size);
        Integer[] largeArrayRandom = generateTestDataHalfRandom(size * 2);

        // Run the sorting algorithms and compute b values
        double b1 = benchmarkInsertionSort(smallArrayBin, largeArrayBin);
        double b2 = benchmarkInsertionSort(smallArrayHalf, largeArrayHalf);
        double b3 = benchmarkInsertionSort(smallArrayRandom, largeArrayRandom);
        double b4 = benchmarkShellsort(smallArrayBin, largeArrayBin);
        double b5 = benchmarkShellsort(smallArrayHalf, largeArrayHalf);
        double b6 = benchmarkShellsort(smallArrayRandom, largeArrayRandom);
        System.out.println("Insertion    Shellsort");
        System.out.printf("Bin    %.3f %.3f\n", b1, b4);
        System.out.printf("Half    %.3f %.3f\n", b2, b5);
        System.out.printf("RanInt %.3f %.3f\n", b3, b6);
    }

    public static void main(String args[]) {
        BenchmarkTool me = new CompletedBenchmarkTool();
        int size = 200000; // 4096

        // NOTE: feel free to change size here. all other code must go in the
        // methods.
        me.runBenchmarks(size);
        // debug stuff
        /*
         * Integer[] smallArray = me.generateTestDataHalves(size);
         * Integer[] largeArray = me.generateTestDataHalfRandom(size * 2);
         * for (int i = 0; i < size; i++) {
         * System.out.print(smallArray[i]);
         * // System.out.print(largeArray);
         * }
         */
    }
}