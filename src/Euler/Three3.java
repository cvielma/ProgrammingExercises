/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Euler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cvielma
 */
public class Three3 {

    public static long maxPrimeFactorOf(final long num) {
        long sqrt = Math.round(Math.sqrt(num));
        for (long i = num; i > 1; i--) {
            if (num % i == 0 && isPrime(i)) {
                return i;
            }
        }
        return 1l;       
    }
    
    public static boolean isPrime(final long num) {
        if (num <= 3) {
            return num > 1;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        double sqrt = Math.floor(Math.sqrt(num));
        for (int i = 5; i <= sqrt; i+=6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(maxPrimeFactorOf(7));
        System.out.println(maxPrimeFactorOf(14));
        System.out.println(maxPrimeFactorOf(13195));
        System.out.println(maxPrimeFactorOf(600851475143l));
    }
}
