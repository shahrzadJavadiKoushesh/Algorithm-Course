import java.util.*;

public class FriendsApp {
    static int[] parent;
    static int[] rank;
    static int[] size;
    static int[] minId;
    static int[] maxId;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        parent = new int[n + 1];
        rank = new int[n + 1];
        size = new int[n + 1];
        minId = new int[n + 1];
        maxId = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
            minId[i] = i;
            maxId[i] = i;
        }

        for (int i = 0; i < q; i++) {
            int type = scanner.nextInt();
            if (type == 1) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                union(x, y);
            } else if (type == 2) {
                int x = scanner.nextInt();
                printFriendInfo(x);
            }
        }
    }

    // Find the representative of the set that x is a part of.
    public static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) return;

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
            size[yRoot] += size[xRoot];
            minId[yRoot] = Math.min(minId[xRoot], minId[yRoot]);
            maxId[yRoot] = Math.max(maxId[xRoot], maxId[yRoot]);
        } else if (rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
            size[xRoot] += size[yRoot];
            minId[xRoot] = Math.min(minId[xRoot], minId[yRoot]);
            maxId[xRoot] = Math.max(maxId[xRoot], maxId[yRoot]);
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
            size[xRoot] += size[yRoot];
            minId[xRoot] = Math.min(minId[xRoot], minId[yRoot]);
            maxId[xRoot] = Math.max(maxId[xRoot], maxId[yRoot]);
        }
    }

    public static void printFriendInfo(int x) {
        int xRoot = find(x);
        System.out.println(size[xRoot] + " " + minId[xRoot] + " " + maxId[xRoot]);
    }
}