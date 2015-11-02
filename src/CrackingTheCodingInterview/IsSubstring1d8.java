/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

public class IsSubstring1d8 {

	public static boolean isRotation(final String s1, final String s2) {
		final String s1s1 = s1 + s1;
		return isSubstring(s1s1, s2);
	}

	public static boolean isSubstring(final String s1, final String s2) {
		return s1.contains(s2);
	}

	public static void main(final String args[]) {
	
		System.out.println(isRotation("waterbottle", "erbottlewat"));
		System.out.println(isRotation("ab", "ba"));
		System.out.println(isRotation("abc", "cba"));	
                System.out.println(isRotation("waterbottle", "wa"));//wrong
	
	}

}
