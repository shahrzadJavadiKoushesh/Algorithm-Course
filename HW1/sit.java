import java.util.*;

public class sit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        char[][] seats = new char[n][m];

        for (int i = 0; i < n; i++) {
            String row = scanner.next();
            seats[i] = row.toCharArray();
        }

        for (int i = 0; i < k; i++) {
            int[] bestSeat = findBestSeat(seats);
            int row = bestSeat[0];
            int col = bestSeat[1];

            seats[row][col] = '#';

            System.out.println((row + 1) + " " + (col + 1));
        }
    }


    public static int[] findBestSeat(char[][] seats) {
        int[] bestSeat = new int[2];
        int maxDistance = -1;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == '.') {
                    int distance = getDistanceToNearestPerson(seats, i, j);
//                    System.out.println("distance for row " + (i+1) + " col " + (j+1) + " : " + distance);
                    if (distance > maxDistance || (distance == maxDistance && (i < bestSeat[0] || (i == bestSeat[0] && j < bestSeat[1])))) {
                        maxDistance = distance;
                        bestSeat[0] = i;
                        bestSeat[1] = j;
                    }
                }
            }
        }

        return bestSeat;
    }


    public static int getDistanceToNearestPerson(char[][] seats, int row, int col) {
        if (seats[row][col] == '#'){
            return 0;
        }

        int leftDistance = Integer.MAX_VALUE;
        int rightDistance = Integer.MAX_VALUE;

        // left
        for (int i = col - 1; i >= 0; i--) {
            if (seats[row][i] == '#') {
                leftDistance = col;
                break;
            }
        }
//        System.out.println(leftDistance);

        // right
        for (int i = col + 1; i < seats[row].length; i++) {
            if (seats[row][i] == '#') {
                rightDistance = i - col;
                break;
            }
        }
//        System.out.println(rightDistance);

        if (leftDistance > rightDistance){
//            System.out.println("distance for row " + row + " col " + col + " : " + rightDistance);
            return rightDistance;
        }
        else{
//            System.out.println("distance for row " + row + " col " + col + " : " + leftDistance);
            return leftDistance;
        }
    }
}
