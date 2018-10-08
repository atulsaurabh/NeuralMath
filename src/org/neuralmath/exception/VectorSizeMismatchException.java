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
public class VectorSizeMismatchException extends Exception
{

    public VectorSizeMismatchException() {
    }
    
    public VectorSizeMismatchException(String message)
    {
        super(message);
    }
}
