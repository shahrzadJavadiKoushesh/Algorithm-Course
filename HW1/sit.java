import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class sit {
    public static void main(String[] args) throws IOException {
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
            SalonSeat seat = pq.poll();
            System.out.println(seat.row + " " + seat.col);
            seats[seat.row - 1] = seats[seat.row - 1].substring(0, seat.col - 1) + '#' + seats[seat.row - 1].substring(seat.col);
            modifiedIndice = seat.row - 1;
            int finalModifiedIndice = modifiedIndice;
            pq.removeIf(obj -> obj.row == finalModifiedIndice + 1);
        }
    }

    public static PriorityQueue<SalonSeat> calculateDistance(String seats, int row) {
        int leftDistance;
        int rightDistance;
        int finalDistance;

        PriorityQueue<SalonSeat> salonSeats = new PriorityQueue<>(new SeatComparator());
        PriorityQueue<SalonSeat> curRow = new PriorityQueue<>(new SeatComparator());

        int [] occupiedIndexes = IntStream.range(0, seats.length()).filter(i -> seats.charAt(i) == '#').toArray();

        if (seats.contains(".")){
            for (int i = 0; i < seats.length(); i++) {
                if (seats.charAt(i) != '#') {
                    int [] result = findElement(occupiedIndexes, i);
                    if (result[0] == -1){
                        leftDistance = Integer.MAX_VALUE;
                    } else {
                        leftDistance = i - result[0];
                    }
                    if (result[1] == -1){
                        rightDistance = Integer.MAX_VALUE;
                    } else {
                        rightDistance = result[1] - i;
                    }
                    finalDistance = Integer.min(leftDistance, rightDistance);
                    curRow.add(new SalonSeat(row + 1, i + 1, finalDistance));
                }
            }
            salonSeats.add(curRow.poll());
        }
        return salonSeats;
    }

    public static int[] findElement(int [] occupiedIndexes, int index){
        int leftIndex = -1;
        int rightIndex = -1;
        int low = 0;
        int high = occupiedIndexes.length - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            if (occupiedIndexes[mid] == index){
                leftIndex = rightIndex = mid;
                break;
            } else if (occupiedIndexes[mid] < index) {
                leftIndex = mid;
                low = mid + 1;
            } else {
                rightIndex = mid;
                high = mid - 1;
            }
        }

        if (leftIndex == -1) {
            leftIndex = high;
        }
        if (rightIndex == -1) {
            rightIndex = low;
        }

        int leftElement = leftIndex >= 0 ? occupiedIndexes[leftIndex] : -1;
        int rightElement = rightIndex < occupiedIndexes.length ? occupiedIndexes[rightIndex] : -1;

        return new int[]{leftElement, rightElement};
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
