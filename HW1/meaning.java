import java.util.*;

public class meaning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size1 = scanner.nextInt();
        int size2 = scanner.nextInt();

        int[] array1 = new int[size1];
        for (int i = 0; i < size1; i++) {
            array1[i] = scanner.nextInt();
        }

        int[] array2 = new int[size2];
        for (int i = 0; i < size2; i++) {
            array2[i] = scanner.nextInt();
        }

        Arrays.sort(array1);
        Arrays.sort(array2);


        int index1 = 0;
        int index2 = 0;
        long min_cost = 0;

        while (index1 < size1 && index2 < size2) {
            if (array1[index1] == array2[index2]) {
                index1++;
                index2++;
            } else if (array1[index1] < array2[index2]) {
                min_cost += array1[index1];
                index1++;
            } else {
                min_cost += array2[index2];
                index2++;
            }
        }

        while (index1 < size1) {
            min_cost += array1[index1];
            index1++;
        }

        while (index2 < size2) {
            min_cost += array2[index2];
            index2++;
        }

        System.out.println(min_cost);
    }
}
