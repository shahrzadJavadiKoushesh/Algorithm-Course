import java.util.*;

class Node implements Comparable<Node> {
    int node;
    long distance;

    Node(int node, long distance) {
        this.node = node;
        this.distance = distance;
    }

    public int compareTo(Node other) {
        return Long.compare(this.distance, other.distance);
    }
}

public class MmdCar {
    private static List<List<Node>> graph;
    private static final long INF = Long.MAX_VALUE / 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            long w = scanner.nextLong();
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        long[] distFromStart = dijkstra(0, n);
        long[] distFromEnd = dijkstra(n - 1, n);

        long maxSum = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (distFromStart[i] < INF && distFromEnd[i] < INF) {
                maxSum = Math.max(maxSum, distFromStart[i] + distFromEnd[i]);
            }
        }

        System.out.println(maxSum);

    }

    private static long[] dijkstra(int source, int n) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.node;
            long currentDist = current.distance;

            if (currentDist > dist[u]){
                continue;
            }

            for (Node neighbor : graph.get(u)) {
                int v = neighbor.node;
                long weight = neighbor.distance;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        return dist;
    }
}
