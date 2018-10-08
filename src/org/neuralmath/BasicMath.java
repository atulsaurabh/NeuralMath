/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuralmath;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.neuralmath.exception.MatrixDimentionMismathException;
import org.neuralmath.exception.VectorSizeMismatchException;

/**
 *
 * @author atul_saurabh
 */
public class BasicMath {

    public static double exp(double value) {
        return Math.exp(value);
    }

    public static double sigmoid(double z) {
        return Math.round(Math.exp(z) / (1 + Math.exp(z)));
    }

    public static double step(double num) {
        if (num > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static double step(double num, double pivot) {
        if (num >= pivot) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}
