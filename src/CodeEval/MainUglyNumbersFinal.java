package CodeEval;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Once upon a time in a strange situation, people called a number ugly if it
 * was divisible by any of the one-digit primes (2, 3, 5 or 7). Thus, 14 is
 * ugly, but 13 is fine. 39 is ugly, but 121 is not. Note that 0 is ugly. Also
 * note that negative numbers can also be ugly: -14 and -39 are examples of such
 * numbers.
 *
 * One day on your free time, you are gazing at a string of digits, something
 * like:
 *
 * 123456
 *
 * You are amused by how many possibilities there are if you are allowed to
 * insert plus or minus signs between the digits. For example you can make:
 *
 * 1 + 234 - 5 + 6 = 236
 *
 * which is ugly. Or
 *
 * 123 + 4 - 56 = 71
 *
 * which is not ugly.
 *
 * It is easy to count the number of different ways you can play with the
 * digits: Between each two adjacent digits you may choose put a plus sign, a
 * minus sign, or nothing. Therefore, if you start with D digits there are
 * 3^(D-1) expressions you can make. Note that it is fine to have leading zeros
 * for a number. If the string is '01023', then '01023', '0+1-02+3' and '01-023'
 * are legal expressions.
 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class MainUglyNumbersFinal {

    private static Set<String> createOpers(String s) {


        Set<String> list = new HashSet<>();
//        if(s.length()==0){return list;}
        if (s.length() == 1) {
            list.add(s);
        }
        if (s.length() == 2) {
            list.addAll(add3opers(s.substring(0, 1), s.substring(1, 2)));
        }
        if (s.length() == 3) {
            Set<String> bc = createOpers(s.substring(1, 3));
            Set<String> ab = createOpers(s.substring(0, 2));
            list.addAll(addAllTo(s.substring(0, 1), bc));
            list.addAll(addAllTo(ab, s.substring(2, 3)));
        } else if (s.length() > 3) {
            for (int i = 0; i < s.length() - 1; i++) {
                String firstPart = s.substring(0, i + 1);
                String secondPart = s.substring(i + 1);
                Set<String> processed = createOpers(secondPart);
                list.addAll(addAllTo(firstPart, processed));
            }
        }

        return list;


    }

    public static byte isUgly(long i) {
        if (i % 2 == 0) {
            return 1;
        }
        if (i % 3 == 0) {
            return 1;
        }
        if (i % 5 == 0) {
            return 1;
        }
        if (i % 7 == 0) {
            return 1;
        }
        return 0;

    }

    public static byte isUglyExpression(String expression) {
        String[] sums = expression.split("\\+");
        long sum = 0;
        for (String s : sums) {
            String[] subs = s.split("\\-");
            if (subs.length > 0) {
                sum += Long.parseLong(subs[0]);
                for (int i = 1; i < subs.length; i++) {
                    sum -= Long.parseLong(subs[i]);
                }
            }
        }
        return isUgly(sum);
    }

    private static Set<String> add3opers(String s1, String s2) {
        Set<String> result = new HashSet<>();

        result.add(s1 + "+" + s2);
        result.add(s1 + "-" + s2);
        result.add(s1 + s2);
        return result;
    }

    private static Set<String> addAllTo(String main, Set<String> secondary) {
        Set<String> result = new HashSet<>();
        for (String s : secondary) {
            result.addAll(add3opers(main, s));
        }
        return result;
    }

    private static Set<String> addAllTo(Set<String> secondary, String main) {
        Set<String> result = new HashSet<>();
        for (String s : secondary) {
            result.addAll(add3opers(s, main));
        }
        return result;
    }

    public static int countUglyness(String input) {
        Set<String> results = createOpers(input);
        int count = 0;
        for (String s : results) {
            count += isUglyExpression(s);
        }
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File(args[0]));
        while (input.hasNextLine()) {
            String line = input.nextLine();

            Set<String> result = createOpers(line);
            //for (String s : result) {
            //System.out.println(s);
            //}
            System.out.println("SIZE=" + result.size());
            //System.out.println(countUglyness(line));
        }
    }
}
