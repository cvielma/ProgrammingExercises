/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

/**
 *
 * @author cvielma
 */
public class MaxProduct {

    public static int maxProduct(int[] A) {

        if (A == null || A.length == 0) {
            return 0;
        }

        int max = A[0];
        int min = A[0];
        int result = A[0];

        for (int i = 1; i < A.length; i++) {
            int currMaxProd = max * A[i];
            int currMinProd = min * A[i];
            max = Math.max(Math.max(currMaxProd, currMinProd), A[i]);
            min = Math.min(Math.min(currMaxProd, currMinProd), A[i]);

            if (max > result) {
                result = max;
            }
        }

        return result;
    }

    public static void main(String[] args) {
//        System.out.println(maxProduct(new int[]{-2, -3, 7}));

        System.out.println(maxProduct(new int[]{-4, -3, -2}));

    }
}
