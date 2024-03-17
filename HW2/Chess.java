import java.util.*;

public class Chess {
    public static int shortestPath(Set<Integer> validSquares, int source_x, int source_y, int dest_x, int dest_y) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        int source = source_x * 10000 + source_y;
        int dest = dest_x * 10000 + dest_y;

        queue.add(source);
        visited.add(source);

        int[] dx = {0, 1, 0, -1, -1, 1, -1, 1};
        int[] dy = {1, 0, -1, 0, 1, -1, -1, 1};

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (current == dest) {
                    return steps;
                }

                int x = current / 10000;
                int y = current % 10000;

                boolean bothEvenOrOdd = (x % 2 == y % 2);

                for (int j = 0; j < (bothEvenOrOdd ? 8 : 4); j++) {
                    int newX = x + dx[j];
                    int newY = y + dy[j];

                    int newCoord = newX * 10000 + newY;

                    if (validSquares.contains(newCoord) && !visited.contains(newCoord)) {
                        queue.add(newCoord);
                        visited.add(newCoord);
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int source_x = scanner.nextInt();
        int source_y = scanner.nextInt();
        int dest_x = scanner.nextInt();
        int dest_y = scanner.nextInt();

        int n = scanner.nextInt();
        Set<Integer> validSquares = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int r = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            for (int j = a; j <= b; j++) {
                validSquares.add(r * 10000 + j);
            }
        }

        int shortestPathLength = shortestPath(validSquares, source_x, source_y, dest_x, dest_y);
        System.out.println(shortestPathLength);
    }
}
