/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

/**
 *
 * @author cvielma
 */
public class DiscontinuosString {

    public static int countDiscMatches(final char[] input1, final int pos1, final char[] input2, final int pos2) {
        if (input1 == null || input2 == null
                || input1.length == 0 || input2.length == 0
                || input1.length - pos1 <= 0 || input2.length - pos2 <= 0
                || input1.length - pos1 > input2.length - pos2) {
            return 0;
        }
        
        if (input1[pos1] == input2[pos2]) {
            return countDiscMatches(input1, pos1+1, input2, pos2+1) + 1;
        }

        int remaining = (input2.length - pos2) - (input1.length - pos1);
        int result = 0;
        for (int i = pos2; i < remaining + pos2; i++) {
            result += countDiscMatches(input1, pos1, input2, i);                
        }

        return result;
    }

    public static int countDiscMatches(final String s1, final String s2) {
        return countDiscMatches(s1 == null ? null : s1.toCharArray(), 0, s2 == null ? null : s2.toCharArray(), 0);
    }
    
    public static void main(String[] args) {
        System.out.println(countDiscMatches("CAT", "CATAPULT"));
    }
    
}
