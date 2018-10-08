/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuralnetwork;

import org.neuralmath.Matrix;
import org.neuralmath.BasicMath;
import org.neuralmath.Vector;
import org.neuralmath.exception.MatrixNotConfortableForMultiplicationException;

/**
 *
 * @author atul_saurabh
 */
public class ThreeLayerNeuralNetwork
{
    private int inputlayersize;
    private int outputlayersize;
    private int hiddenlayersize;
    private Matrix weightOneMatrix;
    private Matrix weightTwoMatrix;

    public ThreeLayerNeuralNetwork(int inputlayersize, int hiddenlayersize, int  outputlayersize) {
        this.inputlayersize = inputlayersize;
        this.outputlayersize = outputlayersize;
        this.hiddenlayersize = hiddenlayersize;
        weightOneMatrix=Matrix.random(inputlayersize, hiddenlayersize);
        weightTwoMatrix=Matrix.random(hiddenlayersize, outputlayersize);
    }
    
    
    public Vector forward(Matrix matrix)
    {
        try {
            Matrix z2 = matrix.product(weightOneMatrix);      
            Matrix a2 = Matrix.sigmoid(z2);
            Matrix z3 = a2.product(weightTwoMatrix);
            Matrix a3 = Matrix.sigmoid(z3);
            return a3.matrixToVector();
        } 
        catch (MatrixNotConfortableForMultiplicationException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public void train(Matrix input,Matrix realOutput)
    {
        for(int i=0;i<input.getRow();i++)
        {
            try {
                Vector v = input.rowVector(i);                                
                Vector I1 = v.multiply(weightOneMatrix);                
                Vector O1 = Vector.sigmoid(I1);                
                Vector I2 = O1.multiply(weightTwoMatrix);               
                Vector O2 = Vector.sigmoid(I2);
               // O2.display();
                Vector realOutputVector = realOutput.rowVector(i);
               // realOutputVector.display();
                Vector errorVector = O2.sub(realOutputVector);         
                
                Vector FPI2 = Vector.sigDerivative(I2);
                
                
            } catch (Exception e) 
            {
                e.printStackTrace();
            }
            
        }
    }
    
    
}
