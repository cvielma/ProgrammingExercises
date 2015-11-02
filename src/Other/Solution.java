package Other;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Solution {
    
    private static int countSubstring(String s){
        Set<String> set = new HashSet();
        
        for(int i =0; i<=s.length(); i++){
            for(int j=i+1; j<=s.length(); j++){
                String temp = s.substring(i, j);
                if(!set.contains(temp))
                    set.add(temp);                
            }
        }        
        return set.size();
    }
    
    
    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            System.out.println(countSubstring(sc.next()));
        }
    }
}