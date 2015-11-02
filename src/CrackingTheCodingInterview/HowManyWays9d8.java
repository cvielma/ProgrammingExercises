/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

/**
 *
 * @author cvielma
 */
public class HowManyWays9d8 {

    public static long count = 0;
    
    public static int howManyWays(final int cents, final int base, final int[] dp) {
        count++;
        if (cents < base) {
            return 0;
        } else if (cents == base) {
            return 1;
        } else {
                return howManyWays(cents - base, 1, dp)
                        + howManyWays(cents - base, 5, dp)
                        + howManyWays(cents - base, 10, dp)
                        + howManyWays(cents - base, 25, dp);

        }
    }

    public static int howManyWays(final int cents) {
        final int[] dp = new int[cents+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        count = 0;
        return howManyWays(cents, 0, dp);
    }
    
    public static void main(String[] args) {
//        System.out.println(howManyWays(1));
//        System.out.println(howManyWays(5));
//        System.out.println(howManyWays(10));
//        System.out.println(howManyWays(25));
//        howManyWays(1);
//        System.out.println(count);
//        howManyWays(5);
//        System.out.println(count);
//        howManyWays(10);
//        System.out.println(count);
//        howManyWays(15);
//        System.out.println(count);
//        howManyWays(20);
//        System.out.println(count);
//        howManyWays(25);
//        System.out.println(count);
//        howManyWays(30);
//        System.out.println(count);
//        howManyWays(35);
//        System.out.println(count);
//        howManyWays(40);
//        System.out.println(count);
//        howManyWays(45);
//        System.out.println(count);
//        howManyWays(50);
//        System.out.println(count);
//        howManyWays(55);
//        System.out.println(count);
//        howManyWays(60);
//        System.out.println(count);
        howManyWays(66);
        System.out.println(count);
        
    }
    
}
