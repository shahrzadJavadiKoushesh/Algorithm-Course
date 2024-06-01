import java.util.Scanner;

public class UniversityPlacement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int r = scanner.nextInt();
        int t = scanner.nextInt();

        double maxAverage = calculateMaxAverage(n, m, r, t);
        System.out.printf("%.6f", maxAverage);
    }

    public static double calculateMaxAverage(int n, int m, int r, int t) {
        long totalPositions = (long) (n - r + 1) * (m - r + 1);

        double average = 0.0;

        int universitiesPlaced = 0;
        for (int i = 0; i < n - r + 1 && universitiesPlaced < t; i++) {
            for (int j = 0; j < m - r + 1 && universitiesPlaced < t; j++) {
                long coverage = (long) r * r;
                average += (double) coverage / totalPositions;
                universitiesPlaced++;
            }
        }

        while (universitiesPlaced < t) {
            long coverage = (long) r * r;
            average += (double) coverage / totalPositions;
            universitiesPlaced++;
        }

        return average;
    }
}
