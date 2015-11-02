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
public class MainPermutations {

    static int depth = 0;

    public static void getSortedStrPerm(String initial) {
        List<String> l = new ArrayList<>();
        for(int i=0; i<initial.length();i ++){
            l.add(initial.substring(i,i+1));
        }
        Collections.sort(l, String.CASE_INSENSITIVE_ORDER);
        
        //System.out.println(Arrays.toString(l.toArray()));
        
        StringBuilder sb = new StringBuilder();
        for(String s: l){
            sb.append(s);
        }
        getStrPerm(0, sb.toString().toCharArray());
    }

    private static void getStrPerm(int fixed, char[] initial) {

//        System.out.println(addTabs() + "-ENTER-\n" + addTabs() + "fixed=" + fixed);
//        System.out.println("initial="+Arrays.toString(initial));
        if (fixed < initial.length) {
            if (initial.length - fixed == 2) {
                String reg = new String(initial);
                String perm = new String(swap(initial, initial.length - 2, initial.length - 1));
                System.out.print(reg);
                if (!reg.equals(perm)) {
                    System.out.print(",");
                    System.out.print(perm);
                    swap(initial, initial.length - 2, initial.length - 1); //return to original;
                }

            } else if (initial.length - fixed > 2) {
                fixed++;
                char[] temp = Arrays.copyOf(initial, initial.length);
                for (int i = fixed - 1; i < temp.length; i++) {
//                    System.out.println(addTabs()+"initial="+Arrays.toString(initial));
                    if(i < temp.length - 1 && temp[fixed-1] == temp[i+1]){continue;}
                    
                    getStrPerm(fixed, temp);
                    
                    if (i < temp.length - 1) {
                        System.out.print(",");
                        temp = swap(temp, fixed - 1, i + 1);                        
                    }

                }
            }
//            System.out.println("\n" + addTabs() + "*EXIT**");
        }
    }

    private static char[] swap(char[] initial, int i, int j) {
        char temp = initial[j];
        initial[j] = initial[i];
        initial[i] = temp;
        return initial;
    }

    public static void main(String[] args) throws FileNotFoundException {
        boolean test = false;
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
            getSortedStrPerm(line);
            if (input.hasNextLine()) {
                System.out.println("");
            }
        }
    }

    private static String addTabs() {
        String result = "";
        for (int i = 0; i < depth; i++) {
            result += "\t";
        }
        return result;
    }    
    
}