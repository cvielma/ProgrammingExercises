/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Euler;

import java.util.HashSet;
import java.util.Set;

/**
 * 

If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.

 * @author cvielma
 */
public class One1 {

    public static int getSum(final int[] primes, final int max) {
	Set<Integer> result = new HashSet<>();
	for (int i : primes) {
		result.add(i);
		int next = 2;
		int product = next*i;
		while(product < max) {
			result.add(product);
			next++;
			product = next*i;
		}
	}

	int sum = 0;
	for (int i : result) {
		sum += i;
	}

	return sum;
    }
    
    public static void main(String[] args) {
        System.out.println(getSum(new int[]{3,5}, 10));
        System.out.println(getSum(new int[]{3,5}, 1000));
    }
    
}
