import java.util.*;

public class MineGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] playgroundSizes = scanner.nextLine().split(" ");
        int numberOfChances = scanner.nextInt();
        scanner.nextLine();
        String[][] board = new String[3][];
        for (int row = 0; row < 3; row++) {
            board[row] = scanner.nextLine().split("");
        }
        List<List<Integer>> rowsMinesInfo = new ArrayList<>();

        for (String[] row : board) {
            List<Integer> rowInfo = new ArrayList<>();
            for (int i = 0; i < row.length; i++) {
                if (row[i].equals("#")) {
                    rowInfo.add(i);
                }
            }
            if (!rowInfo.isEmpty()) {
                rowsMinesInfo.add(rowInfo);
            }
        }

        int bestMax = 0;
        int bestRow = 0;
        for (int i = 0; i < numberOfChances; i++) {
            int bestRowIndex = 0;
            for (int j = 0; j < rowsMinesInfo.size(); j++) {
                List<Integer> row = rowsMinesInfo.get(j);
                int bestRowResult;
                if (row.size() == 1) {
                    bestRowResult = 1;
                    bestRowIndex = 0;
                } else if (row.size() == 2) {
                    bestRowResult = row.get(1) - row.get(0);
                    bestRowIndex = 0;
                } else {
                    if (row.get(1) - row.get(0) >= row.get(row.size() - 1) - row.get(row.size() - 2)) {
                        bestRowResult = row.get(1) - row.get(0);
                        bestRowIndex = 0;
                    } else {
                        bestRowResult = row.get(row.size() - 1) - row.get(row.size() - 2);
                        bestRowIndex = -1;
                    }
                }
                if (bestRowResult > bestMax) {
                    bestMax = bestRowResult;
                    bestRow = j;
                }
            }
            rowsMinesInfo.get(bestRow).remove(bestRowIndex);
            if (rowsMinesInfo.get(bestRow).isEmpty()) {
                rowsMinesInfo.remove(bestRow);
            }
            bestMax = 0;
            bestRow = 0;
        }
        System.out.println(criticalCell(rowsMinesInfo));
    }

    private static int criticalCell(List<List<Integer>> rowsMinesInfo) {
        int sum = 0;
        for (List<Integer> row : rowsMinesInfo) {
            if (row.size() == 1) {
                sum += 1;
            } else {
                sum += row.get(row.size() - 1) - row.get(0) - 1 + 2;
            }
        }
        return sum;
    }


}