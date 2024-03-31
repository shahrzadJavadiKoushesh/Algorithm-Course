import java.util.*;

public class Fuel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();

        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        int[] minCosts = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        dfs(1, graph, minCosts, visited, 0);

        System.out.println("Min Costs:");
        for (int i = 1; i <= n; i++) {
            System.out.println("Node " + i + ": " + minCosts[i]);
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < q; i++) {
            int targetNode = scanner.nextInt();
            minCost = Math.min(minCost, minCosts[targetNode]);
        }

        System.out.println("Minimum Cost to Visit Targets: " + minCost);
    }

    static void dfs(int node, Map<Integer, List<Edge>> graph, int[] minCosts, boolean[] visited, int cost) {
        System.out.println("Visiting Node " + node + " with cost " + cost);
        visited[node] = true;
        minCosts[node] = cost;

        for (Edge child : graph.getOrDefault(node, new ArrayList<>())) {
            System.out.println(child.node);
            System.out.println("cost: " + cost);
            if (!visited[child.node]) {
                dfs(child.node, graph, minCosts, visited, cost + child.cost);
            }
        }
    }

    static class Edge {
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
