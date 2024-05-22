import java.util.*;

class Edge implements Comparable<Edge> {
    long src, dest, weight;

    public Edge(long src, long dest, long weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge compareEdge) {
        return (int) (this.weight - compareEdge.weight);
    }
}

public class Graph {
    long vertices, edges;
    Edge[] edge;
    long[] parent;
    long[] rank;

    Graph(long v, long e) {
        vertices = v;
        edges = e;
        edge = new Edge[(int) e];
        parent = new long[(int) (v+1)];
        rank = new long[(int) (v+1)];
        for (int i = 0; i <= v; ++i) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    long find(long i) {
        if (parent[(int) i] != i)
            parent[(int) i] = find(parent[(int) i]);
        return parent[(int) i];
    }

    void union(long x, long y) {
        long xRoot = find(x);
        long yRoot = find(y);

        if (rank[(int) xRoot] < rank[(int) yRoot]) {
            parent[(int) xRoot] = yRoot;
        } else if (rank[(int) xRoot] > rank[(int) yRoot]) {
            parent[(int) yRoot] = xRoot;
        } else {
            parent[(int) yRoot] = xRoot;
            rank[(int) xRoot]++;
        }
    }

    long kruskalMST(int favEdgeIndex) {
        Arrays.sort(edge);
        long weightSum = edge[favEdgeIndex].weight;
        union(edge[favEdgeIndex].src, edge[favEdgeIndex].dest);

        for (int i = 0; i < edges; ++i) {
            if (i != favEdgeIndex) {
                long x = find(edge[i].src);
                long y = find(edge[i].dest);

                if (x != y) {
                    weightSum += edge[i].weight;
                    union(x, y);
                }
            }
        }

        long root = find(1);
        for (int i = 2; i <= vertices; i++) {
            if (find(i) != root) {
                return -1; // Not all vertices are connected
            }
        }

        return weightSum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();

        Graph graph = new Graph(n, m);
        Edge[] allEdges = new Edge[(int) m];

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int w = scanner.nextInt();
            allEdges[i] = new Edge(x, y, w);
        }

        long favEdgeIndex = scanner.nextInt() - 1;
        System.arraycopy(allEdges, 0, graph.edge, 0, (int) m);

        // find the correct index of the favorite edge after sorting
        Arrays.sort(allEdges);
        for (int i = 0; i < m; i++) {
            if (allEdges[i].src == graph.edge[(int) favEdgeIndex].src &&
                    allEdges[i].dest == graph.edge[(int) favEdgeIndex].dest &&
                    allEdges[i].weight == graph.edge[(int) favEdgeIndex].weight) {
                favEdgeIndex = i;
                break;
            }
        }

        long minimumWeight = graph.kruskalMST((int) favEdgeIndex);
        System.out.println(minimumWeight);

    }
}