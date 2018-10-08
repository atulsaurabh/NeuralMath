/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuralmath;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.neuralmath.BasicMath.exp;
import org.neuralmath.exception.MatrixDimentionMismathException;
import org.neuralmath.exception.MatrixNotConfortableForMultiplicationException;
import org.neuralmath.exception.MatrixVectorDimensionMismatchException;
import org.neuralmath.exception.VectorReshapeException;

/**
 * This class performs various operation on 2d matrix
 *
 * @author atul saurabh
 * @since 1.0
 * @version 1.0
 *
 */
public class Matrix {

    private final int row;
    private final int col;

    private final double[][] mat;

    /**
     * Create a matrix with 2d double array
     *
     * @param mat It is a 2d double array for constructing a matrix object.
     */
    public Matrix(double[][] mat) {
        this.mat = mat;
        this.row = mat.length;
        this.col = mat[0].length;
    }

    /**
     * Create a 2d matrix object containing zeros only
     *
     * @param row The row of the matrix
     * @param col The column of the matrix
     *
     */
    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        mat = new double[row][col];
    }

    /**
     * This is a static method, creating a matrix having specified number of
     * rows and columns. Initializes the matrix with random numbers.
     *
     * @param rows Number of rows in the matrix
     * @param cols Number of columns in the matrix
     * @return A {@link Matrix} initialized with random values.
     */
    public static Matrix random(int rows, int cols) {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        Matrix matrix = Matrix.zeros(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                random.setSeed(System.currentTimeMillis());
                matrix.setElement(i, j, random.nextGaussian());
            }
        }
        return matrix;
    }

    /**
     * Adding a constant to each element of the matrix.
     *
     * @param scalar The constant to be added.
     * @return A new {@link Matrix}.
     */
    public Matrix add(double scalar) {
        Matrix matrix = new Matrix(this.row, this.col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix.setElement(i, j, this.getElement(i, j) + scalar);
            }
        }

        return matrix;
    }

    /**
     * Multiply each elements of the matrix with the specified constant
     *
     * @param scalar The constant for multiplication
     * @return A new {@link Matrix}
     */
    public Matrix product(double scalar) {
        Matrix matrix = new Matrix(this.row, this.col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix.setElement(i, j, this.getElement(i, j) * scalar);
            }
        }

        return matrix;
    }

    /**
     * Multiply a vector to the current matrix, if matrix and vector are
     * comfortable for the operation. A vector is comfortable for matrix
     * multiplication if and only if column of matrix must equal to size of the
     * vector.
     *
     * @param vect The specified vector for multiplication
     * @return A new {@link  Vector}
     * @throws MatrixVectorDimensionMismatchException This exception is thrown
     * if number of column in the matrix mismatches with the size of the vector.
     */
    public Vector product(Vector vect) throws MatrixVectorDimensionMismatchException {
        if (this.col != vect.getSize()) {
            throw new MatrixVectorDimensionMismatchException();
        } else {
            Vector productVector = new Vector(row);
            double result = 0.0;
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    result += this.getElement(i, j) * vect.getElement(j);
                }
                productVector.setElement(i, result);
                result = 0;
            }

            return productVector;
        }
    }

    /**
     * Change the element of the matrix at specified position.
     *
     * @param row The row number for element change.
     * @param col The column number for element change.
     * @param value The new value to put at [row,col] position.
     */
    public void setElement(int row, int col, double value) {
        this.mat[row][col] = value;
    }

    /**
     * Retrieve the value from specified position
     *
     * @param row The row number
     * @param col The column number
     * @return A element at position [row,col].
     */
    public double getElement(int row, int col) {
        return this.mat[row][col];
    }

    /**
     * Create a matrix of size [row x col] in which all element is zero.
     *
     * @param rows The number of rows.
     * @param cols The number of columns.
     * @return A {@link Matrix} of size [row x col] containing all zeros.
     */
    public static Matrix zeros(int rows, int cols) {
        Matrix zeroMatrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                zeroMatrix.setElement(i, j, 0.0);
            }
        }
        return zeroMatrix;
    }

    /**
     * Create a matrix of size [row x col] in which all element is 1.
     *
     * @param rows The number of rows.
     * @param cols The number of columns.
     * @return A {@link Matrix} of size [row x col] containing all ones.
     */
    public static Matrix ones(int rows, int cols) {
        Matrix zeroMatrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                zeroMatrix.setElement(i, j, 1.0);
            }
        }
        return zeroMatrix;
    }

    /**
     *
     * @return
     */
    public Matrix transpose() {
        Matrix matrix = new Matrix(col, row);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                matrix.setElement(i, j, this.getElement(j, i));
            }
        }
        return matrix;
    }

    /**
     *
     */
    public void display() {
        System.out.println("[");

        for (int i = 0; i < row; i++) {
            System.out.print("[ ");
            for (int j = 0; j < col; j++) {
                System.out.print(this.getElement(i, j) + " ");
            }
            System.out.print(" ]");
            System.out.println();
        }
        System.out.println("]");
    }

    /**
     *
     * @param matrix
     * @return
     * @throws MatrixNotConfortableForMultiplicationException
     */
    public Matrix product(Matrix matrix) throws MatrixNotConfortableForMultiplicationException {
        if (this.col != matrix.row) {
            throw new MatrixNotConfortableForMultiplicationException();
        } else {
            Matrix mulMat = new Matrix(this.row, matrix.col);
            double sum = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < matrix.col; j++) {
                    for (int k = 0; k < col; k++) {
                        sum += this.getElement(i, k) * matrix.getElement(j, k);
                    }
                    mulMat.setElement(i, j, sum);
                    sum = 0;
                }
            }
            return mulMat;
        }

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Vector matrixToVector() {
        Vector vect = new Vector(row * col);
        int k = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                vect.setElement(k, this.getElement(i, j));
                k++;
            }
        }
        return vect;
    }

    public Matrix multiply(Matrix matrix) throws MatrixDimentionMismathException {
        if (this.col != matrix.col || this.row != matrix.row) {
            throw new MatrixDimentionMismathException();
        } else {
            Matrix mul = new Matrix(this.row, this.col);
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    mul.setElement(i, j, this.getElement(row, col) * matrix.getElement(row, col));
                }
            }
            return mul;
        }
    }

    public Matrix divide(Matrix matrix) throws MatrixDimentionMismathException {
        if (this.col != matrix.col || this.row != matrix.row) {
            throw new MatrixDimentionMismathException();
        } else {
            Matrix div = new Matrix(this.row, this.col);
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    div.setElement(i, j, this.getElement(i, j) / matrix.getElement(i, j));
                }
            }
            return div;
        }
    }

    public Matrix pow(int pow) {
        Matrix powMatrix = new Matrix(this.row, this.col);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                powMatrix.setElement(i, j, Math.pow(this.getElement(i, j), pow));
            }
        }
        return powMatrix;
    }

    public Matrix reshape(int rows, int cols) throws VectorReshapeException {
        Vector vect = this.matrixToVector();
        Matrix matrix = vect.vectorToMatrix(rows, cols);
        return matrix;
    }

    public Vector rowVector(int i) throws MatrixDimentionMismathException {
        if (i > row) {
            throw new MatrixDimentionMismathException("Invalid Row ");
        } else {
            Vector vector = new Vector(col);
            for (int k = 0; k < col; k++) {
                vector.setElement(k, this.getElement(i, k));
            }
            return vector;
        }
    }

    public static Matrix exp(Matrix mat) {
        Matrix matrix = new Matrix(mat.getRow(), mat.getCol());
        for (int i = 0; i < mat.getRow(); i++) {
            for (int j = 0; j < mat.getCol(); j++) {
                matrix.setElement(i, j, Math.exp(mat.getElement(i, j)));
            }
        }
        return matrix;
    }

    public static Matrix sigmoid(Matrix z) {
        Matrix eZ = exp(z);
        Matrix onePlus = eZ.add(1);
        Matrix sigResult = null;
        try {
            sigResult = eZ.divide(onePlus);
        } catch (MatrixDimentionMismathException e) {
            e.printStackTrace();
        }
        return sigResult;

    }

    public static Matrix sigDerivative(Matrix matrix) {
        Matrix eZ = exp(matrix);
        Matrix onePlus = eZ.add(1);

        Matrix sqrMatrix = onePlus.pow(2);
        Matrix result = null;
        try {
            result = eZ.divide(sqrMatrix);
        } catch (MatrixDimentionMismathException ex) {
            Logger.getLogger(BasicMath.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Matrix step(Matrix num) {
        Matrix matrix = Matrix.zeros(num.getRow(), num.getCol());
        for (int i = 0; i < num.getRow(); i++) {
            for (int j = 0; j < num.getCol(); j++) {
                if (num.getElement(i, j) > 0) {
                    matrix.setElement(i, j, 1.0);
                }
            }
        }

        return matrix;

    }

    public static Matrix step(Matrix num, Matrix pivot) throws MatrixDimentionMismathException {
        if (num.getRow() != pivot.getRow() || num.getCol() != num.getCol()) {
            throw new MatrixDimentionMismathException();
        } else {
            Matrix matrix = Matrix.zeros(num.getRow(), num.getCol());
            for (int i = 0; i < num.getRow(); i++) {
                for (int j = 0; j < num.getCol(); j++) {
                    if (num.getElement(i, j) >= pivot.getElement(i, j)) {
                        matrix.setElement(i, j, 1.0);
                    }
                }
            }

            return matrix;
        }

    }

}
