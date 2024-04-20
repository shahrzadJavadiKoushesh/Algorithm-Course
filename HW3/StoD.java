import java.util.Arrays;
import java.util.Scanner;

public class StoD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input
        int n = scanner.nextInt();
        int[] paceBoosting = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            paceBoosting[i] = scanner.nextInt();
        }

        // Output
        System.out.println(minMovesToTarget(n, paceBoosting));

        scanner.close();
    }

    public static int minMovesToTarget(int n, int[] paceBoosting) {
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Initialize starting point and booster squares
        dp[1][1] = 0;
        for (int i = 1; i <= n; i++) {
            dp[paceBoosting[i]][i] = 0;
        }

        // Propagate costs
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i > 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
                if (j > 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
        }

        return dp[n][n];
    }
}
