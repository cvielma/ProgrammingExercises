/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

/**
 * 2.7) Implement a function to check if a linked list is a palindrome
 */
public class PalindromeLinkedList2d7 {

    public static class MyList {

        int value;
        MyList next;

        public MyList(final int value) {
            this.value = value;
        }

        public void add(final int value) {
            MyList cursor = this;
            while (cursor.next != null) {
                cursor = cursor.next;
            }
            cursor.next = new MyList(value);
        }

        public String toString() {
            return value + ", " + next;
        }
    }

    public static boolean isPalindrome(final MyList list) {
        MyList reverse = reverseList(list);
        MyList cursor = list;

        while (cursor != null && reverse != null) {
            if (cursor.value != reverse.value) {
                return false;
            }
            cursor = cursor.next;
            reverse = reverse.next;
        }

        return true;

    }

    public static MyList reverseList(final MyList list) {
        if (list == null) {
            return null;
        }

        MyList result = reverseList(list.next);
        if (result == null) {
            return new MyList(list.value);
        } else {
            result.add(list.value);
        }

        return result;

    }

    public static MyList createList(final int[] array) {
        MyList result = null;
        for (int i = 0; i < array.length; i++) {
            if (result == null) {
                result = new MyList(array[i]);
            } else {
                result.add(array[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MyList palindrome = createList(new int[]{1, 2, 3, 4, 3, 2, 1});
        MyList palindrome2 = createList(new int[]{1, 2, 3, 4, 4, 3, 2, 1});
        MyList notPalindrome = createList(new int[]{1, 2, 1, 4, 1, 3, 5, 1});

        System.out.println(notPalindrome);
        System.out.println(reverseList(notPalindrome));


        System.out.println(isPalindrome(palindrome));
        System.out.println(isPalindrome(palindrome2));
        System.out.println(isPalindrome(notPalindrome));
        
        System.out.println(7/2);
    }
}
