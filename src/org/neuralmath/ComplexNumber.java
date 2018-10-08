/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuralmath;

/**
 *
 * @author Suyojan
 */
public class ComplexNumber 
{
   private final double real;
   private final double imaginary;
   
   public ComplexNumber()
   {
       real = 0.00;
       imaginary=0.00;
   }
   
   public ComplexNumber(final double real,final double imaginary)
   {
       this.real=real;
       this.imaginary=imaginary;
   }
   
   public double real()
   {
       return this.real;
   }
   
   public double imaginary()
   {
       return this.imaginary;
   }
   
   public ComplexNumber add(ComplexNumber complexNumber)
   {
       return new ComplexNumber(this.real+complexNumber.real(), this.imaginary+complexNumber.imaginary());
   }
   
   public ComplexNumber sub(ComplexNumber complexNumber)
   {
       return new ComplexNumber(this.real-complexNumber.real(), this.imaginary-complexNumber.imaginary());
   }
}
