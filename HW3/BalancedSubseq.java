class Solution {
    public int countPartitionWays(int[] nums) {
        return countSubSetSum(nums, 0, 0, 0);
    }

    static int countSubSetSum(int[] nums, int index, int sum1, int sum2) {
        if (index == nums.length) {
            if (sum1 == sum2) {
                return 1;
            }
            return 0;
        }

        // Include current element in the first subset
        int ways1 = countSubSetSum(nums, index + 1, sum1 + nums[index], sum2);

        // Include current element in the second subset
        int ways2 = countSubSetSum(nums, index + 1, sum1, sum2 + nums[index]);

        // Total ways by considering both possibilities
        return ways1 + ways2;
    }
}

public class BalancedSubseq {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 2, 1, 5};
        System.out.println(solution.countPartitionWays(nums));
    }
}
