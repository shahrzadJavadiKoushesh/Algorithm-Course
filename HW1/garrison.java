import java.util.*;

public class garrison {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int q = scanner.nextInt();
        int n = scanner.nextInt();
        int[] soldiers = new int[n];
        for (int i = 0; i < n; i++) {
            soldiers[i] = scanner.nextInt();
        }

        for (int i = 0; i < q; i++) {
            int m = scanner.nextInt();
            int k = scanner.nextInt();

            for (int j = 0; j < n; j += m) {
                if (j + m <= n) {
                    rotateGroup(soldiers, j, j + m - 1, k);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(soldiers[i] + " ");
        }
    }

    private static void rotateGroup(int[] soldiers, int start, int end, int k) {
        int length = end - start + 1;
        k %= length;

        for (int i = 0; i < k; i++) {
            int temp = soldiers[end];
            for (int j = end; j > start; j--) {
                soldiers[j] = soldiers[j - 1];
            }
            soldiers[start] = temp;
        }
    }
}
