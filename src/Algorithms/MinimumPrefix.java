package Algorithms;


import java.util.LinkedList;
import java.util.List;

/**
*     Given a set of strings, return the smallest subset that contains prefixes for every string.
    If the list is ['foo', 'foog', 'food', 'asdf'] return ['foo', 'asdf']  
    
    http://www.glassdoor.com/Interview/Facebook-Software-Engineer-Interview-Questions-EI_IE40772.0,8_KO9,26_IP2.htm
*/
public class MinimumPrefix {

    public static List<String> findMinimumPrefix(final String[] input) {
        Trie myTrie = new Trie(input);

        return getAllPrefixes(myTrie);
    }

    public static List<String> getAllPrefixes(final Trie myTrie) {
        List<String> result = new LinkedList<>();

        TrieNode root = myTrie.getRoot();
        for (TrieNode child : root.getChildren()) {
            String curr = getFullPrefix(child);
            if (curr != null && !curr.isEmpty()) {
                result.add(curr);
            }
        }

        return result;
    }

    public static String getFullPrefix(final TrieNode child) {
        if (child == null) {
            return "";
        }
        
        if (child.terminates()) {
            return "" + child.getChar();
        }        

        List<TrieNode> children = child.getChildren();
        if (children == null || children.isEmpty() || children.size() > 1) {
            return "" + child.getChar();
        } else {
            return "" + child.getChar() + getFullPrefix(children.get(0));
        }

    }

    public static void main(String[] args) {
          String[] strings = new String[]{"foo", "foog", "food", "asdf"};
          System.out.println(findMinimumPrefix(strings));
    }
}
