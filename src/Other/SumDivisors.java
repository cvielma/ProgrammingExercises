/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.ArrayList;
import java.util.List;

/**
 * write an algorithm to find sum of numbers which are smaller than N and divisible by 3 or 5

Example:
N = 9 => 3 + 5 + 6 = 14
N = 10 => 3 + 5 + 6 + 9 = 23
 */
public class SumDivisors {

    public static int sumDivisors(final int n) {
        List<Integer> temp = new ArrayList<>();
        int i = 1;
        while (i * 3 < n) {
            temp.add(i * 3);
            i++;
        }

        i = 1;
        while (i * 5 < n) {
            int val = i * 5;
            if (val % 3 != 0) {
                temp.add(val);
            }
            i++;
        }

        int sum = 0;
        for (int val : temp) {
            sum += val;
        }

        return sum;
    }
    
    public static int SumOfNumber(int N){
        N--;
	int divisibleBy3 = N/3;
	divisibleBy3 = (divisibleBy3 * (divisibleBy3 + 1) /2)*3;

	int divisibleBy5 = N/5;
	divisibleBy5 = (divisibleBy5 * (divisibleBy5 + 1) /2)*5;

	int divisibleBy15 = N/15;
	divisibleBy15 = (divisibleBy15 * (divisibleBy15 + 1) /2)*15;
	
	return divisibleBy3 + divisibleBy5 - divisibleBy15;
}

    public static void main(String[] args) {
        System.out.println(sumDivisors(9));
        System.out.println(sumDivisors(10));
        
        System.out.println(SumOfNumber(9));
        System.out.println(SumOfNumber(10));
        
    }
}
