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

        int modifiedIndice = 0;

        PriorityQueue<SalonSeat> pq = new PriorityQueue<>(new SeatComparator());
        for (int s = 0; s < k; s++) {
            if (s != 0){
                pq.addAll(calculateDistance(seats[modifiedIndice], modifiedIndice));
            } else {
                for (int i = 0; i < n; i++) {
                    pq.addAll(calculateDistance(seats[i], i));
                }
            }
//            System.out.println("queue at iteration " + (s + 1));
//            for (SalonSeat seat : pq) {
//                System.out.println(seat.row + " " + seat.col + " " + seat.distance);
//            }
            SalonSeat seat = pq.poll();
//            System.out.println("to poll");
            System.out.println(seat.row + " " + seat.col);
            seats[seat.row - 1] = seats[seat.row - 1].substring(0, seat.col - 1) + '#' + seats[seat.row - 1].substring(seat.col);
//            System.out.println(seats[seat.row - 1]);
            modifiedIndice = seat.row - 1;
            int finalModifiedIndice = modifiedIndice;
            pq.removeIf(obj -> obj.row == finalModifiedIndice + 1);
//            System.out.println("changed: " + modifiedIndice);
        }
    }

    public static PriorityQueue<SalonSeat> calculateDistance(String seats, int row) {
        int leftDistance;
        int rightDistance;
        int finalDistance;

        char[] seatsArr = seats.toCharArray();
        PriorityQueue<SalonSeat> salonSeats = new PriorityQueue<>(new SeatComparator());

        for (int i = 0; i < seatsArr.length; i++) {
            if (seatsArr[i] != '#') {
                leftDistance = Integer.MAX_VALUE;
                rightDistance = Integer.MAX_VALUE;
//                System.out.println("i: " + i);
                // right
                if (seatsArr[i] == '.' && i < seatsArr.length - 1) {
                    String fromHere = seats.substring(i);
                    if (fromHere.indexOf('#') != -1) {
//                        System.out.println(seats);
//                        System.out.println();
//                        System.out.println(fromHere);
                        rightDistance = (fromHere.substring(0, fromHere.indexOf('#'))).length();
                    }
//                    System.out.println("right distance " + " : " + rightDistance);
                }
                // left
                if (seatsArr[i] == '.' && i > 0) {
                    String fromHere = seats.substring(0, i + 1);
//                    System.out.println("from here " + fromHere);
                    if (fromHere.contains("#")) {
                        leftDistance = fromHere.substring(fromHere.lastIndexOf('#'), i).length();
//                        System.out.println("left distance " + " : " + leftDistance);
                    }
                }
                finalDistance = Math.min(leftDistance, rightDistance);
            } else {
                finalDistance = 0;
            }
            salonSeats.add(new SalonSeat(row + 1, i + 1, finalDistance));
        }
//        System.out.println("array salon seats:");
//        for (SalonSeat salonSeat : salonSeats) {
//            System.out.println(salonSeat.row + " " + salonSeat.col + " " + salonSeat.distance);
//        }
        return salonSeats;
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
