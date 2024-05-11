public class TreeTraversal {
    static List<List<Integer>> adjList = new ArrayList<>();
    static int[][] dp;
    static int[] targetNodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        dp = new int[n + 1][2];
        targetNodes = new int[q + 1]; // Increase size by 1

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
            dp[i][0] = dp[i][1] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < n; i++) {
            int parent = sc.nextInt();
            int child = sc.nextInt();
            int cost = sc.nextInt();
            adjList.get(parent).add(child);
            adjList.get(child).add(parent);
            dp[parent][child] = dp[child][parent] = cost;
        }

        for (int i = 1; i <= q; i++) { // Start from index 1
            targetNodes[i] = sc.nextInt();
        }

        dfs(1, -1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    // Remaining code remains the same
}
