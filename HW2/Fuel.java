import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        dfs(graph, minCosts, visited);

        System.out.println("Min Costs:");
        for (int i = 1; i <= n; i++) {
            System.out.println("Node " + i + ": " + minCosts[i]);
        }

        TreeSet<Integer> answers = new TreeSet<>();
        int minCost = 0;
        for (int i = 0; i < q; i++) {
            int targetNode = scanner.nextInt();
            answers.add(minCosts[targetNode]);
        }

//        System.out.println("answers:");
//        for (Integer item : answers) {
//            System.out.println(item);
//        }

        if (q > 1){
            answers.pollLast();
            for (Integer item : answers) {
                minCost += item;
            }
        }

        System.out.println(minCost);
    }

    static void dfs(Map<Integer, List<Edge>> graph, int[] minCosts, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            System.out.println("current node: " + currentNode);
            for (Edge child : graph.getOrDefault(currentNode, new ArrayList<>())) {
                if (!visited[child.node]) {
                    minCosts[child.node] = minCosts[currentNode] + child.cost;
                    visited[child.node] = true;
                    queue.offer(child.node);
                }
                System.out.println(Arrays.toString(queue.toArray()));
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
