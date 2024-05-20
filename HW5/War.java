import java.util.Scanner;

public class War {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);

        int[][] tupleList = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] inputedLine = scanner.nextLine().split(" ");
            int need = Integer.parseInt(inputedLine[0]);
            int gain = Integer.parseInt(inputedLine[1]);
            if (need < gain) {
                tupleList[i][0] = need;
                tupleList[i][1] = gain;
            }
        }

        quickSort(tupleList, 0, n - 1);
        for (int[] x : tupleList) {
            if (x[0] > k) {
                break;
            }
            k += x[1] - x[0];
        }
        System.out.println(k);
    }

    private static int partition(int[][] array, int low, int high) {
        int pivot = array[high][0];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j][0] <= pivot) {
                i++;
                int[] temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int[] temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    private static void quickSort(int[][] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
}
