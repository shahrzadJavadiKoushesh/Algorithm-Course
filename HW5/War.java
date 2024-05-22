import java.util.*;

public class War {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextInt();
        long k = scanner.nextInt();

        long[][] sources = new long[(int) n][2];
        for (int i = 0; i < n; i++) {
            sources[i][0] = scanner.nextInt();
            sources[i][1] = scanner.nextInt();
        }

        System.out.println(maximizeEnergy(sources, k));
    }

    public static long maximizeEnergy(long[][] sources, long initialEnergy) {

        Arrays.sort(sources, Comparator.comparingInt(a -> (int) a[0]));

        long maxEnergy = initialEnergy;
        for (long[] source : sources) {
            long energyLost = source[0];
            long energyGained = source[1];

            if (maxEnergy >= energyLost && energyGained > energyLost) {
                maxEnergy -= energyLost;
                maxEnergy += energyGained;
            }
        }

        return maxEnergy;
    }
}