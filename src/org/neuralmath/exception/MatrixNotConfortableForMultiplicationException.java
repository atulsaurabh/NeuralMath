/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuralmath.exception;

/**
 *
 * @author atul_saurabh
 */
public class MatrixNotConfortableForMultiplicationException extends Exception
{

    public MatrixNotConfortableForMultiplicationException() {
        super("Two Matrix are Not Comfortable for Multiplication");
    }

    public MatrixNotConfortableForMultiplicationException(String message) {
        super(message);
    }
    
    
    
}
