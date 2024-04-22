import java.util.Scanner;

public class StoD {

    public static int minimumMovements(int n, int[] boosters) {
        int maxBoosters = 0;
        int maxRow = 0;

        for (int i = 0; i < n; i++) {
            if (boosters[i] >= maxRow) {
                maxBoosters++;
                maxRow = boosters[i];
            }
        }

        return 2 * n - 2 - maxBoosters;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] boosters = new int[n];

        for (int i = 0; i < n; i++) {
            boosters[i] = scanner.nextInt() - 1; // Adjust for 0-based indexing
        }

        int result = minimumMovements(n, boosters);
        System.out.println(result);
    }
}
