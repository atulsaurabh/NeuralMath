/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuralmath.exception;

/**
 *
 * @author Admin
 */
public class MatrixVectorDimensionMismatchException extends Exception
{

    public MatrixVectorDimensionMismatchException() 
    {
        super("The Dimension of Matrix and Vector Mismatches");
    }
    
    public MatrixVectorDimensionMismatchException(String message)
    {
        super(message);
    }
    
    
}
