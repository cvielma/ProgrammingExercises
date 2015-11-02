/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author cvielma
 */
/**
 * Set substraction: Implement a function that takes the set-wise subtraction of two sorted sets of integers. ie A = {1, 2, 3}, B = {3, 4, 5} => A - B = {1, 2}. There can be duplicates, in which case all duplicates should be removed should there be an occurrence in B. IE: {1, 2, 3, 3, 3} - {2, 3} = {1}
 */
public class SetSubstraction {

    public static int[] substract(final int[] a, final int[] b) {
        List<Integer> result = new ArrayList<>();
        for (int i : a) {
            int pos = Arrays.binarySearch(b, i);
            if (pos < 0) {
                result.add(i);
            }
        }

        int[] res = new int[result.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(substract(new int[]{1,2,3}, new int[]{3,4,5})));
        System.out.println(Arrays.toString(substract(new int[]{1,2,3,3,3,4}, new int[]{2,3})));
        
    }
    
}
