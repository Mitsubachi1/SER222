//package edu.ser222.m01_02;
package Module_1;
/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 * 
 * 
 * @author Angel Chiquito, Ruben Acuna
 * @version 10/13/23
 */

public class CompletedMatrix implements Matrix {

    private final int[][] matrix; // for constructor, using int

    public CompletedMatrix(int[][] matrix) { // constructor
        if (matrix == null) {
            throw new IllegalArgumentException(); // edgecase null matrix
        }
        this.matrix = new int[matrix.length][]; //copy input matrix to make immutable
        for(int y = 0; y < matrix.length; y++){
            this.matrix[y] = new int[matrix[y].length]; //process length
            for(int x = 0; x < matrix[y].length; x++){
                this.matrix[y][x] = matrix[y][x]; //copy matrix
            }

        }
    }

    @Override
    public int getElement(int y, int x) { // y is row x is column
        if ((x >= 0 && x < matrix[0].length) && (y >= 0 && y < matrix.length)) {
            return matrix[y][x];
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int getRows() {
        return matrix.length;
    }

    @Override
    public int getColumns() {
        if (matrix.length == 0) // edgecase 0x0
            return 0;
        return matrix[0].length;
    }

    @Override
    public Matrix scale(int scalar) {
        int rows = getRows();
        int columns = getColumns();
        int[][] newMatrix = new int[rows][columns]; // new matrix using same size with get function
        for (int y = 0; y < getRows(); y++) {
            for (int x = 0; x < getColumns(); x++) {
                newMatrix[y][x] = matrix[y][x] * scalar; // scaling function
            }
        }
        return new CompletedMatrix(newMatrix);
    }

    @Override
    public Matrix plus(Matrix other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        if ((other.getColumns() != getColumns()) || (other.getRows() != getRows())) {
            throw new RuntimeException();
        }
        int rows = getRows();
        int columns = getColumns();
        int[][] newMatrix = new int[rows][columns]; // new matrix using same size with get function
        for (int y = 0; y < getRows(); y++) {
            for (int x = 0; x < getColumns(); x++) {
                newMatrix[y][x] = matrix[y][x] + other.getElement(y, x); // scaling function
            }
        }
        return new CompletedMatrix(newMatrix);
    }

    @Override
    public Matrix minus(Matrix other) {
        if (other == null) { // null matrix exception
            throw new IllegalArgumentException();
        }
        if ((other.getColumns() != getColumns()) || (other.getRows() != getRows())) {
            throw new RuntimeException();
        }
        int rows = getRows();
        int columns = getColumns();
        int[][] newMatrix = new int[rows][columns]; // new matrix using same size with get function
        for (int y = 0; y < getRows(); y++) {
            for (int x = 0; x < getColumns(); x++) {
                newMatrix[y][x] = matrix[y][x] - other.getElement(y, x); // scaling function
            }
        }
        return new CompletedMatrix(newMatrix);
    }

    @Override
    public Matrix multiply(Matrix other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        int[][] result = new int[getRows()][other.getColumns()]; // new matrix
        for (int y = 0; y < getRows(); y++) { // set y to be row
            for (int x = 0; x < other.getColumns(); x++) { // set x to be the given matrix to use for multiplying
                int sum = 0;
                for (int k = 0; k < getColumns(); k++) { // let k be the original matrix, column var
                    sum += matrix[y][k] * other.getElement(k, x);
                }
                result[y][x] = sum;
            }
        }
        return new CompletedMatrix(result);
    }

    @Override
    public String toString() {
        StringBuilder stringMatrix = new StringBuilder();
        for (int y = 0; y < matrix.length; y++) { // row
            for (int x = 0; x < matrix[y].length; x++) { // column
                stringMatrix.append(matrix[y][x]); // append [row][column]
                if (x < matrix[y].length - 1) {
                    stringMatrix.append(" "); // space in between
                }
            }
            stringMatrix.append("\n"); // newline once done with row
        }
        return stringMatrix.toString(); //
    }

    @Override
    public boolean equals(Object other) {
        if ((other == null) || (!(other instanceof CompletedMatrix))) { // check for null and same instance
            return false;
        }

        CompletedMatrix given = (CompletedMatrix) other; // if good then cast and check dimensions
        if (this.getRows() != given.getRows() || this.getColumns() != given.getColumns()) {
            return false;
        }
        for (int y = 0; y < this.getRows(); y++) { // check all elements
            for (int x = 0; x < this.getColumns(); x++) {
                if (this.getElement(y, x) != given.getElement(y, x)) {
                    return false; // on mistmatch return false
                }
            }
        }

        return true; // if all good return true
    }

    /**
     * Entry point for matrix testing.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // These tests show sample usage of the matrix, and some basic ideas for
        // testing. They are not comprehensive.

        int[][] data1 = new int[0][0];
        int[][] data2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int[][] data3 = { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } };
        int[][] data4 = { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } };
        int[][] data5 = { { 1, 4, 7 }, { 2, 5, 8 } };

        Matrix m1 = new CompletedMatrix(data1);
        Matrix m2 = new CompletedMatrix(data2);
        Matrix m3 = new CompletedMatrix(data3);
        Matrix m4 = new CompletedMatrix(data4);
        Matrix m5 = new CompletedMatrix(data5);

        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());

        // check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        // test equals
        System.out.println("m2==null: " + m2.equals(null)); // false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX")); // false
        System.out.println("m2==m1: " + m2.equals(m1)); // false
        System.out.println("m2==m2: " + m2.equals(m2)); // true
        System.out.println("m2==m3: " + m2.equals(m3)); // false
        System.out.println("m3==m4: " + m3.equals(m4)); // true

        // test operations (valid)
        System.out.println("m1 + m1:\n" + m1.plus(m1));
        System.out.println("m1 + m1:\n" + m1.plus(m1));
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        System.out.println("3 * m5:\n" + m5.scale(3));

        // not tested... multiply(). you know what to do.

        // test operations (invalid)
        // System.out.println("m1 + m2" + m1.plus(m2));
        // System.out.println("m1 + m5" + m1.plus(m5));
        // System.out.println("m1 - m2" + m1.minus(m2));

    }
}