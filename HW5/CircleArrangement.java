import java.util.Arrays;
import java.util.Scanner;

public class CircleArrangement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            colors[i] = scanner.nextInt();
        }

        Arrays.sort(colors);
        int maxDiff = Integer.MIN_VALUE;

        int[] arranged = new int[n];

        int left = 0;
        int right = n - 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                arranged[left++] = colors[i];
            } else {
                arranged[right--] = colors[i];
            }
        }

        for (int i = 0; i < n; i++) {
            int diff = Math.abs(arranged[i] - arranged[(i + 1) % n]);
            maxDiff = Math.max(maxDiff, diff);
        }

        System.out.println(maxDiff);
    }
}