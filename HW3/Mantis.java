import java.util.Arrays;
import java.util.Scanner;

public class Mantis {

    public static int jump(int[] stonesArray) {
        int n = stonesArray.length;
        int[][] dp = new int[n][2]; // dp[i][j]: minimum jumps to reach stone i with last jump odd/even

// Initialize the dp array with maximum value
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

// Base case: last stone requires 0 jumps to reach itself
        dp[n - 1][0] = 0; // Last jump was even
        dp[n - 1][1] = 0; // Last jump was odd

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < 2; j++) { // j=0 for even, j=1 for odd
                int maxJump = stonesArray[i];
                for (int k = 1; k <= maxJump; k++) {
                    if (i + k < n) {
                        if (k % 2 == 0 && dp[i + k][1] != Integer.MAX_VALUE) { // Next jump is even
                            dp[i][0] = Math.min(dp[i][0], 1 + dp[i + k][1]);
                        } else if (k % 2 != 0 && dp[i + k][0] != Integer.MAX_VALUE) { // Next jump is odd
                            dp[i][1] = Math.min(dp[i][1], 1 + dp[i + k][0]);
                        }
                    }
                }
            }
        }

        int result = Math.min(dp[0][0], dp[0][1]);
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] stonesArray = new int[n];
        for (int i = 0; i < n; ++i) {
            stonesArray[i] = scanner.nextInt();
        }

        int minimum = jump(stonesArray);

        System.out.println(minimum);
    }
}
