package Other;


import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class TestStringOrdering {

    public static void main(String[] args) {
        char[] test = "abcdefABCDEF12345!,.CBDa A befbA".toCharArray();
        Arrays.sort(test);
        System.out.println(Arrays.toString(test));
    }
}
