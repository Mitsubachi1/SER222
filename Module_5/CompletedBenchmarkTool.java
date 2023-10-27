//package edu.ser222.m02_01; //server
package Module_5; //local
/*
 *This program benchmarks all sorting methods
 * 
 * Completion time: (estimation of hours spent on this program)
 *
 * @author Angel Chiquito, Acuna, Sedgewick
 * @version 10/28/23
 */


public class CompletedBenchmarkTool implements BenchmarkTool {
    
    /***************************************************************************
     * START - SORTING UTILITIES, DO NOT MODIFY (FROM SEDGEWICK)               *
     **************************************************************************/
    
    public static void insertionSort(Comparable[] a) {
        int N = a.length;
        
        for (int i = 1; i < N; i++)
        {
            // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..          
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
    
    
    public static void shellsort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        
        while (h >= 1) {
            // h-sort the array.
            for (int i = h; i < N; i++) {
                // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                exch(a, j, j-h);
            }
            h = h/3;
        }
    }
    
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    
    /***************************************************************************
     * END - SORTING UTILITIES, DO NOT MODIFY                                  *
     **************************************************************************/

    //! TODO: implement interface methods.

    public Integer[] generateTestDataBinary(int size){
        int[size] result;
        int mid = size/2;
        for(int i = 0; i < mid; i++){
            result[i] = 0;
        }
        for(int i = mid; i < size; i++){
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
    public Integer[] generateTestDataHalves(int size){
        int[size] result;
        int mid = size/2;
//NOTE: half of a given array is 0, then for the other half, keep halving it and for each half you up the result of that
        return result;
    }
    
    /**
     * Generates an array of integers where half the data is 0s, and half random
     * int values. All values will be positive.
     * @param size
     * @return 
     */
    public Integer[] generateTestDataHalfRandom(int size);
    
    /**
     * Computes the double formula value for two run times.
     * 
     * @param t1 first time
     * @param t2 second time
     * @return b value
     */
    public double computeDoublingFormula(double t1, double t2);
    
    /**
     * Computes an empirical b value for insertion sort by running it on a pair
     * of inputs and using the doubling formula.
     * 
     * @param small small test data array
     * @param large large test data array. twice the same of small array.
     * @return b value
     */
    public double benchmarkInsertionSort(Integer[] small, Integer[] large);
    
    /**
     * Computes an empirical b value for shellsort sort by running it on a pair
     * of inputs and using the doubling formula.
     * @param small small test data array
     * @param large large test data array. twice the same of small array.
     * 
     * @return b value
     */
    public double benchmarkShellsort(Integer[] small, Integer[] large);
    
    /**
     * Runs the two sorting algorithms on the three types of test data to
     * produce six different b values. B values are displayed to the user.
     * 
     * @param size size of benchmark array. to be doubled later.
     */
    public void runBenchmarks(int size);

    public static void main(String args[]) {
        BenchmarkTool me = new CompletedBenchmarkTool();
        int size = 4096;
        
        //NOTE: feel free to change size here. all other code must go in the
        //      methods.
        
        me.runBenchmarks(size);
    }
}