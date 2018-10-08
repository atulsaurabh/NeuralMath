/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.conversion;

/**
 *
 * @author Suyojan
 */
public class Conversion
{
     public static final double centiMeterToMeter(double centimeter)
     {
         return centimeter/100.00;
     }
     
     public static final double meterToCentimeter(double meter)
     {
         return meter * 100.00;
     }
     
     public static final double centigradeToFahrenheit(double centigrade)
     {
         return centigrade * (9.00/5.00)+32.00;
     }
     
     public static final double fahrenheitToCentigrade(double fahrenheit)
     {
         return (fahrenheit - 32.00)*(5.00/32.00);
     }
}
