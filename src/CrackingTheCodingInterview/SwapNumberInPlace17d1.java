/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

/**
 *
 * @author cvielma
 */
public class SwapNumberInPlace17d1 {

    public static void swap(int a, int b) {
        System.out.println("Initial a: " + a + ", b: " + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("Final a: " + a + ", b: " + b);

    }

    public static void main(String[] args) {

        swap(5, 20);
    }
}
