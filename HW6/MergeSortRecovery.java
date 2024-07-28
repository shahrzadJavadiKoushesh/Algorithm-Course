import java.util.*;

public class MergeSortRecovery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of words
        int n = scanner.nextInt();
        scanner.nextLine();

        // Read the words
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextLine();
        }

        // Read the binary string
        String binaryString = scanner.nextLine();

        // Sort words based on custom criteria
        Arrays.sort(words, new CustomComparator());

        // Reconstruct the original order
        String[] originalOrder = recoverOriginalOrder(words, binaryString);

        // Print the original order
        for (String word : originalOrder) {
            System.out.println(word);
        }

        scanner.close();
    }

    static class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            boolean aHasMosi = a.toLowerCase().contains("mosi");
            boolean bHasMosi = b.toLowerCase().contains("mosi");

            if (aHasMosi && !bHasMosi) return -1;
            if (!aHasMosi && bHasMosi) return 1;
            return a.compareTo(b);
        }
    }

    public static String[] recoverOriginalOrder(String[] words, String binaryString) {
        int n = words.length;
        String[] result = new String[n];
        int[] indexWrapper = {0}; // Wrapper for index to keep track of current position in binary string
        mergeSortReconstruct(words, binaryString.toCharArray(), 0, n - 1, result, indexWrapper);
        return result;
    }

    public static void mergeSortReconstruct(String[] words, char[] binaryString, int left, int right, String[] result, int[] indexWrapper) {
        if (left == right) {
            result[left] = words[left];
            return;
        }

        int mid = (left + right) / 2;

        // Recurse for left half
        mergeSortReconstruct(words, binaryString, left, mid, result, indexWrapper);

        // Recurse for right half
        mergeSortReconstruct(words, binaryString, mid + 1, right, result, indexWrapper);

        // Merge left and right halves according to the binary string
        mergeReconstruct(result, left, mid, right, binaryString, indexWrapper);
    }

    public static void mergeReconstruct(String[] result, int left, int mid, int right, char[] binaryString, int[] indexWrapper) {
        List<String> merged = new ArrayList<>();
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (binaryString[indexWrapper[0]] == '0') {
                merged.add(result[i]);
                i++;
            } else {
                merged.add(result[j]);
                j++;
            }
            indexWrapper[0]++;
        }

        // Copy the remaining elements from the left half, if any
        while (i <= mid) {
            merged.add(result[i]);
            i++;
        }

        // Copy the remaining elements from the right half, if any
        while (j <= right) {
            merged.add(result[j]);
            j++;
        }

        // Copy merged result back to the original array
        for (int idx = left; idx <= right; idx++) {
            result[idx] = merged.get(idx - left);
        }
    }
}
