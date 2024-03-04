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
            for (int j = 0; j < m; j++) {
                seats[i][j] = row.charAt(j);
            }
        }

        for (int s = 0; s < k; s++) {
            PriorityQueue<SalonSeat> pq = new PriorityQueue<>(new SeatComparator());
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (seats[i][j] == '.') {
                        int distance = calculateDistance(seats, i, j);
//                        System.out.println("distance for seat " + (i+1) + " " + (j+1) + " is " + distance);
                        pq.add(new SalonSeat(i + 1, j + 1, distance));
                    }
                }
            }
//            System.out.println("queue at iteration " + (s+1));
//            for (SalonSeat seat : pq){
//                System.out.println(seat.row + " " + seat.col + " " + seat.distance);
//            }
            SalonSeat seat = pq.poll();
            System.out.println(seat.row + " " + seat.col);
            seats[seat.row - 1][seat.col - 1] = '#';
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    System.out.print(seats[i][j]);
//                }
//                System.out.println();
//            }
        }
    }

    public static int calculateDistance(char[][] seats, int row, int col) {
        if (seats[row][col] == '#'){
            return 0;
        }

        int leftDistance = Integer.MAX_VALUE;
        int rightDistance = Integer.MAX_VALUE;
        int finalDistance = 0;

        // left
        for (int i = col - 1; i >= 0; i--) {
            if (seats[row][i] == '#') {
                leftDistance = col;
                break;
            }
        }

        // right
        for (int i = col + 1; i < seats[row].length; i++) {
            if (seats[row][i] == '#') {
                rightDistance = i - col;
                break;
            }
        }

//        System.out.println("right distance for row " + row + " col " + col + " : " + rightDistance);
//        System.out.println("left distance for row " + row + " col " + col + " : " + leftDistance);
        finalDistance = Math.min(leftDistance, rightDistance);
//        System.out.println(finalDistance);
        return finalDistance;
    }

    static class SalonSeat {
        int row, col, distance;

        public SalonSeat(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    static class SeatComparator implements Comparator<SalonSeat> {
        @Override
        public int compare(SalonSeat s1, SalonSeat s2) {
            if (s1.distance == s2.distance) {
                if (s1.row == s2.row) {
                    return Integer.compare(s1.col, s2.col);
                }
                return Integer.compare(s1.row, s2.row);
            }
            return Integer.compare(s2.distance, s1.distance);
        }
    }
}
