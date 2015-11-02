package CrackingTheCodingInterview;





/**
1.5) Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabccccaaa would become a2b1c5a3. If the compressed string would not become smaller than the original string, your method should return the original string.

aabccccaaa
a2b1c5a3
*/

public class StringCompressor1d5 {

	public static String compress(final String original) {
		StringBuilder sb = new StringBuilder();
		int count = 1;
		char previous = '\0';
		for (char current : original.toCharArray()) { // Throw exception?
			if (previous != '\0') {
				if (current == previous) {
					count++;					
				} else {
					sb.append("").append(previous).append("").append(count);
                                        previous = current;
					count = 1;				
				}
			} else {
				previous = current;			
			}
		}
                sb.append("").append(previous).append("").append(count);
		String result = sb.toString();
		if (result.length() >= original.length()) {
			return original;
		} else {
			return result;
		}
	}
        
        public static void main(String[] args) {
        
            System.out.println("1)" + StringCompressor1d5.compress("aabccccaaa")); //a2b1c4a3
            System.out.println("2)" + StringCompressor1d5.compress("")); //""
            System.out.println("3)" + StringCompressor1d5.compress("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); //a30
            System.out.println("4)" + StringCompressor1d5.compress("abcdefgh-aaabbbccc")); //a1b1c1d1e1f1g1h1-1a3b3c3
            System.out.println("5)" + StringCompressor1d5.compress("\0"));//\0
    }
                


}


/* Original
 * 
 * public class StringCompressor {

	public static String compress(final String original) {
		StringBuilder sb = new StringBuilder();
		int count = 1;
		char previous = '';
		for (char current : original.toCharArray()) { // Throw exception?
			if (previous != '') {
				if (current == previous) {
					count++;					
				} else {
					sb.append(current + count);
					count = 1;				
				}
			} else {
				previous = current;			
			}
		}

		String result = sb.toString();
		if (result.length() >= original) {
			return original;
		} else {
			return result;
		}
	}


}
 */