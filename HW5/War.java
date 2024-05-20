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

        int s = tupleList.length;

        quickSort(tupleList, 0, s - 1);
        for (int[] tuple : tupleList) {
            if (tuple[0] > k) {
                break;
            }
            k += tuple[1] - tuple[0];
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
            int p = partition(array, low, high);
            quickSort(array, low, p - 1);
            quickSort(array, p + 1, high);
        }
    }
}
