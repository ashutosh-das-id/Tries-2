/**
 * Time Complexity:  O(N * n ^ L) where N is the given words, n is the number of words that starts with the prefix and L is the length of each word.
 * Space Complexity: O(N * L ) for tries.
 *
 *
 *Given an array of unique strings words, return all the word squares you can build from words.
 * The same word from words can be used multiple times. You can return the answer in any order.
 * A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).
 *
 */
class Solution {
    List<List<String>> result = new ArrayList<>();
    TrieNode root = new TrieNode();

    class TrieNode {
        List<String> words;
        TrieNode[] children;

        public TrieNode() {
            words = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                TrieNode tn = new TrieNode();
                curr.children[ch - 'a'] = tn;
            }
            curr = curr.children[ch - 'a'];
            curr.words.add(word);
        }
    }

    List<String> startsWithPrefix(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[ch - 'a'];
        }

        return curr.words;
    }

    public List<List<String>> wordSquares(String[] words) {
        List<String> temp = new ArrayList<>();
        for (String word : words) {
            insert(word);
        }
        for (String word : words) {
            temp.add(word);
            helper(temp);
            temp.remove(temp.size() - 1);
        }
        return result;

    }

    void helper(List<String> temp) {
        if (temp.size() == temp.get(0).length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        StringBuilder sb = new StringBuilder();
        int idx = temp.size();
        for (String word : temp) {
            sb.append(word.charAt(idx));
        }

        List<String> words = startsWithPrefix(sb.toString());

        for (String word : words) {
            temp.add(word);
            helper(temp);
            temp.remove(temp.size() - 1);
        }
    }

}