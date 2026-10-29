/**
 * Time Complexity:  O(n * maxSize(p,w))  where n is the length of the queries, p is the length of pattern, and w is the length of a word in the queries
 * Space Complexity: O(1)
 */
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {

        List<Boolean> result = new ArrayList<>();

        int indexOfWord = 0;
        int indexOPattern = 0;

        int sizeOfArray = queries.length;
        int lengthOfPattern = pattern.length();

        for (int i = 0; i < sizeOfArray; i++) {
            String word = queries[i];
            int lengthOfWord = word.length();

            indexOfWord = 0;
            indexOPattern = 0;
            boolean match = true;
            while ((indexOfWord < lengthOfWord) && match) {
                char chWord = word.charAt(indexOfWord);

                if (indexOPattern == lengthOfPattern) {
                    if (Character.isUpperCase(chWord)) {
                        match = false;
                    }
                }

                else if (chWord == pattern.charAt(indexOPattern)) {
                    indexOPattern++;
                } else if (chWord != pattern.charAt(indexOPattern)) {
                    if (Character.isUpperCase(chWord)) {
                        match = false;
                    }
                }
                indexOfWord++;
            }

            if (indexOfWord == lengthOfWord && (indexOPattern == lengthOfPattern)) {
                result.add(true);
            } else {
                result.add(false);
            }

        }
        return result;
    }
}