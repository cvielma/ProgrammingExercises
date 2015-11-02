/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interviews;

/**
 * Rotate String: http://www.glassdoor.com/Interview/Google-Software-Engineer-Interview-Questions-EI_IE9079.0,6_KO7,24.htm
 */
public class RotateString {

    public static String rotateString(String str, int rot) {
        int mod = rot % str.length();
        return str.substring(str.length() - mod, str.length()) + str.substring(0, str.length() - mod);
    }

    public static void main(String[] args) {
        System.out.println(rotateString("abcd", 1));
        System.out.println(rotateString("abcd", 2));
        System.out.println(rotateString("abcd", 3));
        System.out.println(rotateString("abcd", 4));
        System.out.println(rotateString("abcd", 5));
    }
}
