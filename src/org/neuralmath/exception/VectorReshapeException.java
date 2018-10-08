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
public class VectorReshapeException extends Exception
{

    public VectorReshapeException() {
        super("Vector can not be converted to matrix as size does not match");
    }

    public VectorReshapeException(String message) {
        super(message);
    }
          
}
