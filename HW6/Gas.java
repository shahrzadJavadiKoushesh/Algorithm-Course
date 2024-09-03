import java.util.*;

public class Gas {
    static final int MAX = 1000000; 

    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Node {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices + 1];
            //initialize adjacency lists for all the vertices
            for (int i = 1; i <= vertices; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist[source].addFirst(edge); //for directed graph
        }

        public int[] dijkstra_GetMinDistances(int sourceVertex) {
            boolean[] SPT = new boolean[vertices + 1];
            //distance used to store the distance of vertex from a source
            int [] distance = new int[vertices + 1];

            for (int i = 1; i <= vertices; i++) {
                distance[i] = MAX;
            }

            PriorityQueue<Node> pq = new PriorityQueue<>(vertices, Comparator.comparingInt(node -> node.weight));
            distance[sourceVertex] = 0;
            pq.add(new Node(sourceVertex, 0));

            while (!pq.isEmpty()) {
                Node extractedNode = pq.remove();
                int extractedVertex = extractedNode.vertex;
                if (SPT[extractedVertex] == false) {
                    SPT[extractedVertex] = true;
                    LinkedList<Edge> list = adjacencylist[extractedVertex];
                    for (int i = 0; i < list.size(); i++) {
                        Edge edge = list.get(i);
                        int destination = edge.destination;
                        //only if edge destination is not present in SPT
                        if (SPT[destination] == false) {
                            int newKey = distance[extractedVertex] + edge.weight;
                            int currentKey = distance[destination];
                            if (currentKey > newKey) {
                                Node node = new Node(destination, newKey);
                                pq.add(node);
                                distance[destination] = newKey;
                            }
                        }
                    }
                }
            }
            return distance;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int l = scanner.nextInt();

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.addEdge(u, v, w);
            graph.addEdge(v, u, w); 
        }

        int[] distances = graph.dijkstra_GetMinDistances(1); // 1 is the capital

        int count = 0;
        for (int i = 2; i <= n; i++) {
            LinkedList<Edge> edges = graph.adjacencylist[i];
            for (Edge edge : edges) {
                if (distances[i] < l && distances[i] + edge.weight > l) {
                    count++;
                } else if (distances[edge.destination] < l && distances[edge.destination] + edge.weight > l) {
                    count++;
                }
            }
        }

        System.out.println(count / 2); 
    }
}
