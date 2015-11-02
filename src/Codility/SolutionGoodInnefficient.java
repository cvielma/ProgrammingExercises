/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Codility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class SolutionGoodInnefficient {
    
    //determines if it is matematically possibly that substr appears at least 
    //n times in S
    public boolean canOccur(int n, String substr, String S){
        if(substr.length()*3 <= S.length())
            return true;        
        return false;
    }    
    
    //counts how many times does substr appear in S
    //if n>0 it stops checking after n occurrences have been found.
    public int countOccurs(int n, String substr, String S){
        if(substr==null || S==null)
            return 0;
        
        int occurs = 0;
        n = n>=0? n: S.length();
        for(int i=0; i <= S.length()-substr.length() && occurs<n; i+=substr.length()){
            String temp = S.substring(i, i+substr.length()); 
            if(substr.equals(temp))
                occurs++;        
        }
        
        return occurs;
    }
            
    public int solution(String S) {
        /** From the largest prefix to the min prefix. 
         *  If we find one match that's it.
         */
        if(S == null)
            return -1;
        
        for(int i = S.length()/2 +1; i>0; i--){ 
            String beg = S.substring(0, i);
            if(canOccur(3, beg, S) && S.endsWith(beg)){
                //this way we do not search all the string but just a fraction
                int occurs = countOccurs(1, beg, S.substring(i, S.length()-beg.length())); 
                if(occurs>=1)
                    return beg.length();                
            }
        }
        return 0;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        String path = "";
        boolean test = true;
        if(test){ 
            path = "/home/cvielma/Documents/Development/ToGoogle/ToGoogle/src/codeeval/tests/testCodibility.txt";;
        }
        else {path=args[0];}
        SolutionGoodInnefficient s = new SolutionGoodInnefficient();
        Scanner sc = new Scanner(new File(path));
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            //System.out.println(line+"="+checkEmail(line));
            System.out.println(s.solution(line));
        }
    }
    
}
