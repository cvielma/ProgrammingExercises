package Other;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cvielma
 */
public class StringsPermutation {
    
    public static List<String> permutations(String test) {
        if(test.length() == 1) {
            return Arrays.asList(new String[]{test});
        } else if (test.length() == 2) {
            return Arrays.asList(new String[]{
                test.substring(0,1) + test.substring(1,2), 
                test.substring(1,2) + test.substring(0,1)
            });
        } else {
            Set<String> result = new HashSet<>();
            for(int i = 0; i < test.length(); i++) {
                String ch = test.substring(i, i+1);
                for(String s : permutations(test.substring(0, i) +
                    test.substring(i+1, test.length()))) {
                    result.add(ch + s);
                }
            }
            
            return new ArrayList(result);
            
        }
        
    }
    
    public static void main(String[] args) {
        List<String> result = permutations("aaa");
        for (String str : result) {
            System.out.println(str);
        }
        
        System.out.println("Total: " + result.size());
    }
    
}
