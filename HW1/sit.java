import java.util.*;

public class sit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // row
        int m = scanner.nextInt();  // column
        int k = scanner.nextInt();

        String[] seats = new String[n];
        for (int i = 0; i < n; i++) {
            seats[i] = scanner.next();
        }

        for (int s = 0; s < k; s++) {
            PriorityQueue<SalonSeat> pq = new PriorityQueue<>(new SeatComparator());
            for (int i = 0; i < n; i++) {
                int distance = calculateDistance(seats[i], m);
                System.out.println(distance);
//                pq.add(new SalonSeat(i + 1, j + 1, distance));
//
            }
            System.out.println("queue at iteration " + (s + 1));
            for (SalonSeat seat : pq) {
                System.out.println(seat.row + " " + seat.col + " " + seat.distance);
            }
            SalonSeat seat = pq.poll();
            System.out.println(seat.row + " " + seat.col);
//            seats[seat.row - 1].charAt(seat.col - 1) = '#';
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(seats[i].charAt(j));
                }
                System.out.println();
            }
        }
    }

    public static int calculateDistance(String seats, int m) {
        int leftDistance = Integer.MAX_VALUE;
        int rightDistance = Integer.MAX_VALUE;
        int finalDistance = 0;

        char [] seatsArr = seats.toCharArray();
        System.out.println(seatsArr);

        if (seats.contains("#")){
            for (int i = 0; i < seatsArr.length; i++) {
                System.out.println("i: " + i);
                // right
                if (seatsArr[i] == '.' && i < m - 1){
                    String fromHere = seats.substring(i);
                    System.out.println(fromHere);
                    rightDistance = (fromHere.substring(0, fromHere.indexOf('#'))).length();
                    System.out.println("right distance "  + " : " + rightDistance);
                }
                // left
                if (seatsArr[i] == '.' && i > 0){
                    String fromHere = seats.substring(0, i+1);
                    System.out.println(fromHere);
                    if (fromHere.contains("#")){
                        leftDistance = fromHere.substring(fromHere.lastIndexOf('#'), i).length();
                        System.out.println("left distance "  + " : " + leftDistance);
                    }
                }
                finalDistance = Math.min(leftDistance, rightDistance);
                System.out.println("Final: ");
                System.out.println(finalDistance);
            }
        }
        else{

            finalDistance = Integer.MAX_VALUE;
        }

       
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

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
