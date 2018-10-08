/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuralmath;

import java.util.Random;
import org.neuralmath.exception.MatrixNotConfortableForMultiplicationException;
import org.neuralmath.exception.VectorReshapeException;
import org.neuralmath.exception.VectorSizeMismatchException;

/**
 *
 * @author Admin
 */
public class Vector {

    private final double vect[];
    private final int size;

    public Vector(double[] vect) {
        this.vect = vect;
        this.size = vect.length;
    }

    public Vector(int size) {
        vect = new double[size];
        this.size = size;
    }

    public static Vector zero(int size) {
        Vector vector = new Vector(size);
        for (int i = 0; i < size; i++) {
            vector.setElement(i, 0.0);
        }
        return vector;
    }

    public static Vector one(int size) {
        Vector vector = new Vector(size);
        for (int i = 0; i < size; i++) {
            vector.setElement(i, 1.0);
        }
        return vector;
    }

    public Vector add(Vector v) throws VectorSizeMismatchException {
        if (this.size != v.size) {
            throw new VectorSizeMismatchException();
        } else {
            Vector newVector = new Vector(size);
            for (int i = 0; i < size; i++) {
                newVector.setElement(i, this.getElement(i) + v.getElement(i));
            }
            return newVector;
        }
    }

    public Vector sub(Vector v) throws VectorSizeMismatchException {
        if (this.size != v.size) {
            throw new VectorSizeMismatchException();
        } else {
            Vector newVector = new Vector(size);
            for (int i = 0; i < size; i++) {
                newVector.setElement(i, this.getElement(i) - v.getElement(i));
            }
            return newVector;
        }
    }

    public Vector minus() {
        Vector minusVector = new Vector(this.size);
        for (int i = 0; i < size; i++) {
            minusVector.setElement(i, this.getElement(i) * -1);
        }
        return minusVector;
    }

    public double magnitude() {
        double sqrDst = 0;
        for (int i = 0; i < size; i++) {
            sqrDst += (this.getElement(i) * this.getElement(i));
        }
        return Math.sqrt(sqrDst);
    }

    public double euclideanDistance(Vector v) throws VectorSizeMismatchException {
        double dist = 0;
        if (v.size != size) {
            throw new VectorSizeMismatchException();
        } else {
            for (int i = 0; i < size; i++) {
                double diff = this.getElement(i) - v.getElement(i);
                double sqrDist = diff * diff;
                dist += sqrDist;
            }
            return Math.sqrt(dist);
        }
    }

    public double dot(Vector v) throws VectorSizeMismatchException {
        if (v.size != size) {
            throw new VectorSizeMismatchException();
        } else {
            double dotProduct = 0;
            for (int i = 0; i < size; i++) {
                dotProduct += this.getElement(i) * v.getElement(i);
            }

            return dotProduct;
        }
    }

    public void setElement(int loc, double value) {
        vect[loc] = value;
    }

    public double getElement(int loc) {
        return vect[loc];
    }

    public void display() {
        System.out.print("[ ");
        for (int i = 0; i < size; i++) {
            System.out.print(getElement(i) + " ");
        }
        System.out.print("]");

    }

    public int getSize() {
        return this.size;
    }

    public Vector normalize() {
        Vector vect = new Vector(size);
        for (int i = 0; i < size; i++) {
            vect.setElement(i, this.getElement(i) / this.max());
        }
        return vect;
    }

    public double max() {
        double max = this.getElement(0);
        for (int i = 0; i < this.size; i++) {
            if (max < this.getElement(i)) {
                max = this.getElement(i);
            }
        }
        return max;
    }

    public double min() {
        double min = this.getElement(0);
        for (int i = 0; i < this.size; i++) {
            if (min > this.getElement(i)) {
                min = this.getElement(i);
            }
        }
        return min;
    }

    public static Vector random(int size) {
        Random random = new Random();
        Vector v = Vector.zero(size);

        for (int i = 0; i < size; i++) {
            random.setSeed(System.currentTimeMillis());
            v.setElement(i, random.nextGaussian());
        }

        return v;
    }

    public Matrix vectorToMatrix(int rows, int cols) throws VectorReshapeException {
        if (rows * cols != this.size) {
            throw new VectorReshapeException();
        } else {
            Matrix matrix = new Matrix(rows, cols);
            int k = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix.setElement(i, j, this.getElement(k));
                    k++;
                }
            }
            return matrix;
        }
    }

    public double sum() {
        double result = 0.0;
        for (double d : vect) {
            result += d;
        }
        return result;
    }

    public Vector multiply(Matrix matrix) throws MatrixNotConfortableForMultiplicationException {
        if (matrix.getRow() != this.size) {
            throw new MatrixNotConfortableForMultiplicationException();
        } else {
            double result = 0;
            int x = 0;
            Vector vector = new Vector(matrix.getCol());
            for (int k = 0; k < matrix.getCol(); k++) {
                for (int l = 0; l < matrix.getRow(); l++) {
                    result += this.getElement(l) * matrix.getElement(l, k);
                }

                vector.setElement(x, result);
                result = 0;
                x++;
            }
            return vector;
        }
    }

    public Vector add(double d) {
        Vector v = new Vector(this.size);

        for (int i = 0; i < v.size; i++) {
            v.setElement(i, this.getElement(i) + d);
        }
        return v;
    }

    public Vector mul(double d) {
        Vector v = new Vector(this.size);

        for (int i = 0; i < v.size; i++) {
            v.setElement(i, this.getElement(i) * d);
        }
        return v;
    }

    public Vector dotDivide(Vector v) throws VectorSizeMismatchException {
        if (v.size != this.size) {
            throw new VectorSizeMismatchException();
        } else {
            Vector newVec = new Vector(this.size);
            for (int i = 0; i < this.size; i++) {
                newVec.setElement(i, this.getElement(i) / v.getElement(i));
            }

            return newVec;
        }

    }

    public Vector pow(double p) {
        Vector vector = new Vector(this.size);
        for (int i = 0; i < vector.size; i++) {
            vector.setElement(i, Math.pow(this.getElement(i), p));
        }
        return vector;
    }

    public static Vector exp(Vector v) {
        Vector newVector = new Vector(v.getSize());
        for (int i = 0; i < v.getSize(); i++) {
            newVector.setElement(i, Math.exp(v.getElement(i)));
        }

        return newVector;
    }

    public static Vector sigmoid(Vector z) {
        Vector eZ = exp(z);
        Vector onePlus = eZ.add(1);
        Vector result = null;
        try {
            result = eZ.dotDivide(onePlus);
        } catch (VectorSizeMismatchException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Vector sigDerivative(Vector v) {
        Vector eZ = exp(v);
        Vector onePlus = eZ.add(1);
        Vector square = onePlus.pow(2);
        Vector result = null;
        try {
            result = eZ.dotDivide(square);
        } catch (VectorSizeMismatchException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Vector concat(Vector v1, Vector v2) {
        Vector v = new Vector(v1.getSize() + v2.getSize());
        for (int i = 0; i < v.getSize(); i++) {
            if (i < v1.getSize()) {
                v.setElement(i, v1.getElement(i));
            } else {
                v.setElement(i, v2.getElement(i - v1.getSize()));
            }
        }
        return v;
    }
}
