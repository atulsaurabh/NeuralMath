/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralmath;

import org.neuralmath.Matrix;
import org.neuralmath.exception.MatrixDimentionMismathException;
import org.neuralmath.exception.MatrixNotConfortableForMultiplicationException;
import org.neuralmath.exception.VectorReshapeException;
import org.neuralmath.exception.VectorSizeMismatchException;
import org.neuralnetwork.ThreeLayerNeuralNetwork;

/**
 *
 * @author Admin
 */
public class NeuralMath {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws VectorSizeMismatchException, MatrixNotConfortableForMultiplicationException,MatrixDimentionMismathException,VectorReshapeException
    {
        // TODO code application logic here
        
        double [][] v1 = new double[][]{{3,5},{5,1},{10,2}};
        double [][] v2 = new double[][]{{2,4},{8,10},{14,16},{20,22}};
        Matrix mat2 = new Matrix(v2);
        Matrix mat1=new Matrix(v1);        
        ThreeLayerNeuralNetwork network=new ThreeLayerNeuralNetwork(2, 3, 2);
        network.train(mat1, mat2);
       
        
    }
    
}
