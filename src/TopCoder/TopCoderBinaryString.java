/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TopCoder;

import java.util.Arrays;

/**
 *
 * @author cvielma
 */
public class TopCoderBinaryString {
    private static final String NONE = "NONE";

    public static String[] decode(final String message) {
            if (message == null || "".equals(message)) return new String[]{NONE, NONE};
            String[] result = new String[2];
            result[0] = decode(0, message);
            result[1] = decode(1, message);
            System.out.println("{" + result[0] + "," + result[1] + "}");
            return result;
    }

    private static String decode(final int init, final String message) {
            int[] decoded = new int[message.length()];
            decoded[0] = init;
            for(int i = 0; i < message.length(); i++) {
                    int nextNum = Integer.parseInt(message.substring(i,i+1)) - decoded[i];
                    if (nextNum < 2) {
                            if (i < message.length() -1) {
                                    decoded[i+1] = nextNum;
                            }
                    } else {
                            return NONE;
                    }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < decoded.length; i++) {
                    sb.append(decoded[i]);
            }

            return sb.toString();

    }
    
    public static void main(String[] args) {
    
        System.out.println(Arrays.toString(decode("123210122")));
    }
  
}
