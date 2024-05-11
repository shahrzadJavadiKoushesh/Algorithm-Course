import java.util.*;

public class EqualSumSplits {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        System.out.println("Number of ways to split the array into two parts with equal sums: " + countWays(arr));
    }

    public static int countWays(int[] arr) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

// dp[i] will be true if a subset with sum i can be formed
        boolean[] dp = new boolean[totalSum + 1];
        dp[0] = true;

// Populate the dp array
        for (int num : arr) {
            for (int i = totalSum; i >= num; i--) {
                dp[i] |= dp[i - num];
            }
        }

        int count = 0;
// Check for each sum if it can be formed by two different subsets
        for (int sum = 1; sum <= totalSum / 2; sum++) {
            if (dp[sum] && dp[totalSum - sum]) {
// If a subset with this sum exists, check if another subset with the same sum exists
                if (hasTwoDistinctSubsets(arr, sum)) {
                    count++;
// Print the subsets for debugging
                    printSubsets(arr, sum);
                }
            }
        }

        return count;
    }

    // Helper method to check if there are two distinct subsets with the given sum
    private static boolean hasTwoDistinctSubsets(int[] arr, int targetSum) {
// Use a 2D boolean array to track which elements have been used
        boolean[][] used = new boolean[2][arr.length];
        return findTwoSubsets(arr, 0, targetSum, used, 0);
    }

    // Recursive helper method to find two distinct subsets with the given sum
    private static boolean findTwoSubsets(int[] arr, int index, int remainingSum, boolean[][] used, int subsetIndex) {
        if (remainingSum == 0) {
// Found one subset, look for the second one
            if (subsetIndex == 0) {
                return findTwoSubsets(arr, 0, remainingSum, used, 1);
            } else {
                return true; // Found both subsets
            }
        }
        if (index == arr.length) {
            return false;
        }
// Try to include the current element if it hasn't been used in either subset
        if (!used[0][index] && !used[1][index] && arr[index] <= remainingSum) {
            used[subsetIndex][index] = true;
            if (findTwoSubsets(arr, index + 1, remainingSum - arr[index], used, subsetIndex)) {
                return true;
            }
            used[subsetIndex][index] = false;
        }
// Exclude the current element
        return findTwoSubsets(arr, index + 1, remainingSum, used, subsetIndex);
    }

    // Helper method to print subsets with a given sum for debugging
    private static void printSubsets(int[] arr, int targetSum) {
        System.out.println("Subsets with sum " + targetSum + ":");
        printSubsetsHelper(arr, 0, targetSum, new ArrayList<>(), new boolean[arr.length]);
    }

    private static void printSubsetsHelper(int[] arr, int index, int remainingSum, List<Integer> currentSubset, boolean[] used) {
        if (remainingSum == 0) {
            System.out.println(currentSubset);
            return;
        }
        if (index == arr.length || remainingSum < 0) {
            return;
        }
// Skip elements that have already been used
        if (!used[index]) {
// Include the current element
            used[index] = true;
            currentSubset.add(arr[index]);
            printSubsetsHelper(arr, index + 1, remainingSum - arr[index], currentSubset, used);
// Exclude the current element
            currentSubset.remove(currentSubset.size() - 1);
            used[index] = false;
        }
        printSubsetsHelper(arr, index + 1, remainingSum, currentSubset, used);
    }
}