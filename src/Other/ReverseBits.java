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
public class ReverseBits {
    
    public static int reverseBits(int n) {
        int result = 0;
        for(int i = 0; i < 32; i++) {
            int temp  = n & 1;
            result = result << 1;
            result = result | temp;
            n = n >> 1;
        }
        return result;
    }
    
    public static void main(String[] args) {
        int num = 43261596;
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toBinaryString(reverseBits(num)));
        System.out.println(reverseBits(num));
        
    }
}
