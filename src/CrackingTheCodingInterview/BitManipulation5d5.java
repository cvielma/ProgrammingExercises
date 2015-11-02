/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

/**
 * Write a function to determine the number of bits required to convert integer A to integer B.
 * Example:
 *  input: 31, 14
 *  output: 2
 * @author cvielma
 */
public class BitManipulation5d5 {

    public static int countBits(final int num) {
        int count = 0;
        int n = num;
        for (int i = 0; i < 32; i++) {
            int result = n & 1;
            if (result == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    public static int bitsToChange(final int num1, final int num2) {
        return Math.abs(countBits(num1) - countBits(num2));
    }

    public static int bitsToChangeGayle(final int a, final int b) {
        int count = 0;
        int c = a ^ b;
        while (c != 0) {
            System.out.println("C: " + c + ", " + Integer.toBinaryString(c));
            count++;
            c = c & (c - 1);
        }
        System.out.println("C: " + c + ", " + Integer.toBinaryString(c));
        return count;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(31));
        System.out.println(Integer.toBinaryString(14));
        System.out.println(bitsToChange(31, 14));

        System.out.println(bitsToChangeGayle(31, 14));
    }
}
