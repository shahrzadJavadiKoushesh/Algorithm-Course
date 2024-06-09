import java.util.*;

public class LikedSubsequences {
    static int LikedSubsequences(int[] sequence, int l, int h, int d) {
        int n = sequence.length;
        int likedCount = 0;

        for (int i = 1; i < (1 << n); i++) {
            int sum = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int length = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {  // jth number in subseq
                    int num = sequence[j];
                    sum += num;
                    min = Math.min(min, num);
                    max = Math.max(max, num);
                    length++;
                }
            }

            if (length >= 2 && sum >= l && sum <= h && (max - min) >= d) {
                likedCount++;
            }
        }
        return likedCount;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int l = scanner.nextInt();
        int h = scanner.nextInt();
        int d = scanner.nextInt();

        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt();
        }

        System.out.println(LikedSubsequences(sequence, l, h, d));
    }
}
