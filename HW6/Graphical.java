import java.util.*;

public class Graphical {

    static List<List<Edge>> graph = new ArrayList<>();
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int t = scanner.nextInt();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        int[] tests = new int[t];
        for (int i = 0; i < t; i++) {
            tests[i] = scanner.nextInt();
        }

        for (int x : tests) {
            findCentralVertex(x);
        }
    }

    static void findCentralVertex(int x) {

        long[] sumDistances = new long[x + 1];
        boolean allConnected = true;

        for (int i = 1; i <= x; i++) {
            long[] dist = dijkstra(i, x);
            long sum = 0;
            for (int j = 1; j <= x; j++) {
                if (dist[j] == INF) {
                    allConnected = false;
                    break;
                }
                sum += dist[j];
            }
            sumDistances[i] = sum;
        }

        if (!allConnected) {
            System.out.println("0");
            return;
        }

        int centralVertex = 1;
        long minSum = sumDistances[1];
        for (int i = 2; i <= x; i++) {
            if (sumDistances[i] < minSum) {
                minSum = sumDistances[i];
                centralVertex = i;
            }
        }

        System.out.println(centralVertex + " " + minSum);
    }

    static long[] dijkstra(int start, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (v <= n && dist[u] != INF && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, (int) dist[v]));
                }
            }
        }

        return dist;
    }

    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex, distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}
