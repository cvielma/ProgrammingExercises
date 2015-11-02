/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TopCoder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Telephone Words
PROBLEM People in the United States often give others their telephone num-
ber as a word representing the seven-digit number after the area code. For
example, if my telephone number were 866-2665, I could tell people my number
is “TOOCOOL,” instead of the hard-to-remember seven-digit number. Note
that many other possibilities (most of which are nonsensical) can represent 866-
2665. You can see how letters correspond to numbers on a telephone keypad in
Figure 7-2.

Write a function that takes a seven-digit telephone number and prints out all of
the possible “words” or combinations of letters that can represent the given num-
ber. Because the 0 and 1 keys have no letters on them, you should change only
the digits 2–9 to letters. You’ll be passed an array of seven integers, with each ele-
ment being one digit in the number. You may assume that only valid phone num-
bers will be passed to your function. You can use the helper function
char getCharKey( int telephoneKey, int place )
which takes a telephone key (0–9) and a place of either 1, 2, 3 and returns the
character corresponding to the letter in that position on the specified key. For
example, GetCharKey(3,2) will return ‘E’ because the telephone key 3 has the
letters “DEF” on it and ‘E’ is the second letter.

 */
public class PhoneWords {

    static Map<Integer, char[]> map;
           
    
    public static void initMap() {
        map = new HashMap<Integer, char[]>();
        map.put(1, new char[] {'1'});
        map.put(2, new char[] {'A', 'B', 'C'});
        map.put(3, new char[] {'D', 'E', 'F'});
        map.put(4, new char[] {'G', 'H', 'I'});
        map.put(5, new char[] {'J', 'K', 'L'});
        map.put(6, new char[] {'M', 'N', 'O'});
        map.put(7, new char[] {'P', 'Q', 'R'});
        map.put(8, new char[] {'S', 'T', 'W'});
        map.put(9, new char[] {'X', 'Y', 'Z'});
        map.put(0, new char[] {'0'});
    }
    
    public static List<String> createStringFromNum(final int[] digits) {
        initMap();
        return createStringFromNum(digits, 0, "");
    }

    public static List<String> createStringFromNum(final int[] digits, final int index, final String partial) {
        List<String> result = new LinkedList<>();
        if (index >= digits.length) {
            result.add(partial);
            return result;
        }

        for (int i = 1; i <= 3; i++) {
            String c = "" + getCharKey(digits[index], i);
            List<String> temp = createStringFromNum(digits, index + 1, partial + c);
            result.addAll(temp);
            if (digits[index] == 1 || digits[index] == 0) {
                return result;
            }
        }

        return result;
    }
    
    public static char getCharKey(final int number, final int pos) {
        return map.get(number)[pos-1];
    }
    
    public static void main(String[] args) {
        List<String> result1 = createStringFromNum(new int[] {8,6,6,2,6,6,5});
        System.out.println("Result1: " + result1.size());
        System.out.println(result1);
        System.out.println(result1.contains("TOOCOOL"));
        
        System.out.println("------------------");
        List<String> result2 = createStringFromNum(new int[] {8,1,6,0,6,6,5});
        System.out.println("Result2: " + result2.size());
        System.out.println(result2);
        System.out.println(result2.contains("TOOCOOL"));
        
    }
}
