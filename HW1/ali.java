import java.util.*;

//
public class ali {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        while (n > 0) {
            int num = scanner.nextInt();
            if (num >= 0){
                numbers.add(num);
            }
            switch (num) {
                case -1:
                    removeFirstEven(numbers);
                    break;
                case -2:
                    removeFirstOdd(numbers);
                    break;
                case -3:
                    removeLastEven(numbers);
                    break;
                case -4:
                    removeLastOdd(numbers);
                    break;
            }
            n--;
        }
        for (Integer number : numbers) {
            System.out.print(number + " ");

        }
    }

    private static void removeFirstEven(ArrayList<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) % 2 == 0) {
                array.remove(i);
                break;
            }
        }
    }

    private static void removeFirstOdd(ArrayList<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) % 2 != 0) {
                array.remove(i);
                break;
            }
        }
    }

    private static void removeLastEven(ArrayList<Integer> array) {
        for (int i = array.size() - 1; i >= 0; i--) {
            if (array.get(i) % 2 == 0) {
                array.remove(i);
                break;
            }
        }
    }

    private static void removeLastOdd(ArrayList<Integer> array) {
        for (int i = array.size() - 1; i >= 0; i--) {
            if (array.get(i) % 2 != 0) {
                array.remove(i);
                break;
            }
        }
    }
}
