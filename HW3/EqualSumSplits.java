import java.util.Scanner;

public class BalancedSubseq {

    // Function to count the number of ways to split the array into two parts with equal sums
    public static int countWays(int[] arr) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

//        System.out.println("total sum: " + totalSum);

        boolean[] dp = new boolean[totalSum + 1];
        dp[0] = true;

// To store the count of subsets with a particular sum
        int[] subsetCount = new int[totalSum + 1];
        subsetCount[0] = 1;

        for (int num : arr) {
            for (int j = totalSum; j >= num; j--) {
                if (dp[j - num]) {
                    dp[j] = true;
                    subsetCount[j] += subsetCount[j - num];
                }
            }
        }

        int count = 0;
// Iterate through all possible sums
        for (int i = 1; i <= totalSum; i++) {
            if (dp[i] && subsetCount[i] % 2 == 0) {
                count++;
            }
        }

        return count;
    }

    // Main method to test the countWays function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int [] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println(countWays(arr)/2);
    }
}