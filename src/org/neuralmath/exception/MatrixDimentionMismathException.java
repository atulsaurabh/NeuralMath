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
public class MatrixDimentionMismathException extends Exception{
    
    public MatrixDimentionMismathException()
    {
       super("Matrix Dimention Mismatch ");
    }
    
    public MatrixDimentionMismathException(String message)
    {
        super(message);
    }
}
