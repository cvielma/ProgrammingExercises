/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

/**
* 5.3) Given a positive integer, print the next smallest and the next largest number that have the smae number of bits in their binary representation
*/ 

public class NextMaxMin5d3 { //BAD

	public static void printNextMaxMinBits(final int num) {
		int next = num & ~(getRightMostOne(num)) | ~(getLeftMostZero(num));
		int previous = num & ~(getLeftMostOne(num)) | ~(getRightMostZero(num));
                System.out.println("----------------------");
                print("Num:", num);
                print("Next:", next);
                print("Previous:", previous);
	}
        
        private static void print(final String name, final int num) {
            System.out.println(name + " (" + num + ") " + Integer.toBinaryString(num));
        }

	public static int getRightMostOne(final int num) {
		int count = 1;
		int result = 1;
                int temp = num;
		while ((temp & 1) != 1 || count <= 32) {
			count++;
                        temp = temp >> 1;
			result = result << 1;
		}
		return result;
	}

	public static int getRightMostZero(final int num) {
		int count = 1;
		int result = 1;
                int temp = num;
		while ((temp & 1) != 0 || count <= 32) {
			count++;
                        temp = temp >> 1;
			result = result << 1;
		}
		return result;
	}

	public static int getLeftMostOne(final int num) {
		int result = 1;
		int count = 1;
                int temp = num;
		while (temp != 0 || count <= 32) {
			count++;
			temp = temp >> 1;
			result = result << 1;
		}
		result = result >> 1;
		return result;
	}

	public static int getLeftMostZero(final int num) {
		int result = 1;
		int count = 1;
                int temp = num;
		while (num != 0 || count <= 32) {
			count++;
			temp = temp >> 1;
			result = result << 1;
		}
		return result;
	}
        
        public static void main(String[] args) {
        printNextMaxMinBits(4);
        printNextMaxMinBits(440);
        printNextMaxMinBits(10);
    }
}
