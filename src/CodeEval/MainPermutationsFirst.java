package CodeEval;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package codeeval;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Write a program to print out all the permutations of a string in alphabetical
 * order.
 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class MainPermutationsFirst {

    public static Collection<String> getSortedStrPerm(String initial) {
        List<String> sorted = new ArrayList<String>(); 
        sorted.addAll(getStrPerm(initial.toCharArray()));
        Collections.sort(sorted, String.CASE_INSENSITIVE_ORDER);
        return sorted;
    }

    private static Set<String> getStrPerm(char[] initial) {
        Set<String> result = new HashSet<>();
//        System.out.println("initial="+Arrays.toString(initial));
        
        if (initial.length == 2) {
            result.add(new String(initial));            
            result.add(new String(swap(initial, 0, 1)));
            
        } else if (initial.length > 2) {
            for (int i = 0; i < initial.length; i++) {
                Set<String> perms = getStrPerm(Arrays.copyOfRange(initial, 1, initial.length));
                for (String s : perms) {
//                    System.out.println("i="+i+", s="+s+", initial[0]="+initial[0]);
                    result.add(initial[0] + s);
                }
//                System.out.println("initial before swap="+Arrays.toString(initial));
                if(i<initial.length-1){
                    initial = swap(initial, 0, i+1);
                }
//                System.out.println("initial after swap="+Arrays.toString(initial));
            }
        }


        return result;
    }

    private static char[] swap(char[] initial, int i, int j) {
        char temp = initial[j];
        initial[j] = initial[i];
        initial[i] = temp;
        return initial;
    }

    public static void main(String[] args) throws FileNotFoundException {
        boolean test = true;
        String filepath;
        if (test) {
            filepath = "/home/cvielma/Documents/Development/ToGoogle/ToGoogle/src/codeeval/tests/testStringPerms.txt";
        } else {
            filepath = args[0];
        }
        Scanner input = new Scanner(new File(filepath));

        while (input.hasNextLine()) {
            String line = input.nextLine();
            StringBuilder sb = new StringBuilder();
            Collection<String> perms = getSortedStrPerm(line);
            for (Iterator<String> i = perms.iterator(); i.hasNext();) {
                sb.append(i.next());
                if (i.hasNext()) {
                    sb.append(",");
                }
            }

            System.out.println(sb.toString());
        }
    }
}
