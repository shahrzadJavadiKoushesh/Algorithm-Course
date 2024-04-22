import java.util.Scanner;

public class MineGame {

    public static int minCriticalPoints(int n, int m, int k, char[][] board) {
        // dp[i][j] represents minimum critical points starting from (i, j)
        int[][] dp = new int[n][m];

        // Initialize dp for the last row (cannot exit from here)
        for (int j = 0; j < m; j++) {
            dp[n - 1][j] = (board[n - 1][j] == '#') ? 0 : Integer.MAX_VALUE;
        }

        // Fill dp bottom-up
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '#') {
                    dp[i][j] = 0; // Cannot start from a mine
                } else {
                    int minCritical = Integer.MAX_VALUE;
                    // Check both left and right neighbors (if valid)
                    if (j > 0) {
                        minCritical = Math.min(minCritical, dp[i][j - 1]);
                    }
                    if (j < m - 1) {
                        minCritical = Math.min(minCritical, dp[i][j + 1]);
                    }
                    // Add 1 since current cell might be critical
                    dp[i][j] = minCritical + 1;

                    // Can we deactivate a mine to create an escape route?
                    if (k > 0) {
                        // Deactivate a mine (including all rows)
                        if (i > 0 || j > 0 || j < m - 1) {
                            dp[i][j] = Math.min(dp[i][j], minCritical);
                        } else if (k >= 2) { // Special case: single cell on first row
                            dp[i][j] = Math.min(dp[i][j], 1);
                        }
                    }
                }
            }
        }

        // Count critical points (all points where dp is MAX_VALUE)
        int criticalPoints = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    criticalPoints++;
                }
            }
        }

        return criticalPoints;
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        int n = 3;
        int m = 3;
        int k = 2;
        char[][] board = {{'#', '.', '#' }, {'#', '.', '#'}, {'#', '.', '#'}};

//        for (int i = 0; i < n; i++) {
//            board[i] = scanner.nextLine().toCharArray();
//        }
        System.out.println(minCriticalPoints(n, m, k, board)); // Output: 2
    }
}
