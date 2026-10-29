/**
 * Time Complexity:  O(n logk)  where n is the length of the array nums
 * Space Complexity: O(k)
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        Queue<Integer> minHeap = new PriorityQueue<>(
                (a, b )->  frequency.get(a) - frequency.get(b)
        );

        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        for(int num : frequency.keySet()){
            minHeap.offer(num);

            if(minHeap.size() > k){
                minHeap.poll();
            }
        }



        int[] result = new int[k];

        for(int i=0; i<k; i++){
            result[i] =  minHeap.poll();
        }

        return result;

    }
}

/**
 * Time Complexity:  O(n)  where n is the length of the array nums
 * Space Complexity: O(n)
 */

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        Map<Integer, List<Integer>> frequencyToNum = new HashMap<>();

        for (int num : frequency.keySet()) {
            int freq = frequency.get(num);

            frequencyToNum.putIfAbsent(freq, new ArrayList<>());
            frequencyToNum.get(freq).add(num);

            min = Math.min(min, freq);
            max = Math.max(max, freq);

        }

        int[] result = new int[k];
        int count = 0;
        for (int i = max; i >= min && count < k; i--) {
            if (!frequencyToNum.containsKey(i))
                continue;

            List<Integer> numbers = frequencyToNum.get(i);
            for(int number : numbers){
                result[count] = number;
                count++;
            }

        }

        return result;

    }
}