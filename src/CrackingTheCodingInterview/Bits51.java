/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

/**
 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class Bits51 {
    
    
    public static int insertIn(int n, int m, int i, int j){
        int mask = 1;
        System.out.println("n ='"+Integer.toBinaryString(n)+"'");
        System.out.println("m ='"+Integer.toBinaryString(m)+"'");
        for(int k = i; k<j; k++){
            mask = mask << 1 | 1;
        }
        
        for(int k = 0; k<i; k++){
            mask = mask << 1;
            m = m<<1;
        }
        
        System.out.println("mask ='"+Integer.toBinaryString(mask)+"'");
        n = n&~mask;
        
        System.out.println("~mask ='"+Integer.toBinaryString(~mask)+"'");
        
        System.out.println("new n ='"+Integer.toBinaryString(n)+"'");
        
        
        
        System.out.println("new m ='"+Integer.toBinaryString(m)+"'");
        
        return n | m;
    }
    
    public static void main(String[] args) {
      int n = Integer.MAX_VALUE;
      int m = 5;
        System.out.println(Integer.toBinaryString(~2));
      System.out.println("\n---------------\nn="+n+", m="+m);
      System.out.println("result ='"+Integer.toBinaryString(insertIn(n,m,3,5))+"'");
    }
}
