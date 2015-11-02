/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

/**
 *
 * @author cvielma
 */
public class BaseConversion {

    public static long convertToBase(final int num, final int base) {
        long result = 0;
        int temp = num;
        int count = 0;
        while (temp != 0) {
            int remainder = temp % base;
            temp = temp / base;
            result = Math.round(Math.pow(10, count) * remainder) + result;
            count++;
        }
        
        return result;   
    }
    
    public static long convertFromBase(final int num, final int fromBase) {
        long result = 0;
        int count = 0;
        String iter = "" + num;
        for(int i = iter.length(); i > 0; i--) {
            int temp = Integer.parseInt(iter.substring(i-1, i));
            result += Math.round(Math.pow(fromBase, count) * temp);
            count++;
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(convertToBase(1, 2));
        System.out.println(convertToBase(2, 2));
        System.out.println(convertToBase(3, 2));
        System.out.println(convertToBase(10, 2));
        System.out.println(convertToBase(11, 2));
        System.out.println(convertToBase(11, 16));
        System.out.println(convertToBase(13, 6));
        System.out.println(convertToBase(12, 6));
        
        System.out.println("Reverse");
        System.out.println(convertFromBase(21, 6));
        System.out.println(convertFromBase(1, 2));
        System.out.println(convertFromBase(10, 2));
        System.out.println(convertFromBase(1011, 2));
        
        
    }
    
}
