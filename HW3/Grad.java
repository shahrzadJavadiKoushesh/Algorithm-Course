import java.util.Arrays;
import java.util.Scanner;

public class Grad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] charWeights = scanner.nextLine().split(" ");
        int[] weights = new int[26];

        for (int i = 0; i < 26; i++) {
            weights[i] = getCharWeight(charWeights[i]) * (i + 1);
        }

        int sum = 0;
        StringBuilder lcsResults = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String[] texts = scanner.nextLine().split(" ");
            String s1 = texts[0];
            String s2 = texts[1];
            int m = s1.length();
            int p = s2.length();
            String[] result = lcsAlgorithm(s1, s2, m, p, weights);
            sum += Integer.parseInt(result[0]);
            lcsResults.append(result[1]).append(" ");
        }

        System.out.println(sum + " " + lcsResults);
    }

    public static int getCharWeight(String weight) {
        int charSum = 0;
        for (char digit : weight.toCharArray()) {
            charSum += Character.getNumericValue(digit);
        }
        return charSum;
    }

    public static int getTextWeight(String text, int[] charWeights) {
        int textSum = 0;
        for (char c : text.toCharArray()) {
            int index = c - 'a';
            textSum += charWeights[index];
        }
        return textSum;
    }

    public static String[] lcsAlgorithm(String s1, String s2, int m, int p, int[] charWeights) {
        int[][] L = new int[m + 1][p + 1];
        int maxCost = 0;
        int index = 0;
        int minI = 0;
        int minJ = 0;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= p; j++) {
                if (i == 0 || j == 0) {
                    L[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    int charIndex = s2.charAt(j - 1) - 'a';
                    int newWeight = L[i - 1][j - 1] + charWeights[charIndex];
                    L[i][j] = newWeight;
                    if (newWeight > maxCost) {
                        maxCost = newWeight;
                        minI = i;
                        minJ = j;
                        index++;
                    }
                } else {
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                }
            }
        }

        int i = minI;
        int j = minJ;
        String[] lcsAlgo = new String[index + 1];
        Arrays.fill(lcsAlgo, "");
        lcsAlgo[index] = "";
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcsAlgo[index] = String.valueOf(s1.charAt(i - 1));
                i--;
                j--;
                index--;
            } else if (L[i - 1][j] > L[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String[]{String.valueOf(getTextWeight(s1, charWeights) + getTextWeight(s2, charWeights) - (maxCost * 2)), String.join("", lcsAlgo)};
    }
}
