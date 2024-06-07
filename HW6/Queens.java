import java.util.Scanner;

public class Queens {
    private static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();

        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String row = scanner.nextLine();
            board[i] = row.toCharArray();
        }

        placeQueens(board, 0, k);
        System.out.println(count);
    }

    private static void placeQueens(char[][] board, int row, int k) {
        if (k == 0) {
            count++;
            return;
        }
        if (row == board.length) {
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (board[row][col] == '.' && isSafe(board, row, col)) {
                board[row][col] = 'Q';
                placeQueens(board, row + 1, k - 1);
                board[row][col] = '.';
            }
        }
        placeQueens(board, row + 1, k);
    }

    private static boolean isSafe(char[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
            if (col - (row - i) >= 0 && board[i][col - (row - i)] == 'Q') return false;
            if (col + (row - i) < board.length && board[i][col + (row - i)] == 'Q') return false;
        }
        return true;
    }
}