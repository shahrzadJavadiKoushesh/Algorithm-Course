import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class fromRoot {
    static class Pair {
        TreeNode node;
        int hd;
        int vd;

        public Pair(TreeNode node, int hd, int vd) {
            this.node = node;
            this.hd = hd;
            this.vd = vd;
        }
    }

    static class Value implements Comparable<Value>{
        int hd;
        int vd;

        public Value(int hd, int vd){
            this.hd = hd;
            this.vd = vd;
        }

        public int getHd() {
            return hd;
        }

        public int getVd() {
            return vd;
        }

        @Override
        public int compareTo(Value other) {
            return Integer.compare(this.hd, other.hd);
        }
    }

    public static Map<Value, Integer> topView(TreeNode root) {
        Map<Value, Integer> map = new TreeMap<>();

        if (root == null){
            return map;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0, 0));

        while (!queue.isEmpty()) {

            Pair current = queue.poll();
            TreeNode node = current.node;
            int hd = current.hd;
            int vd = current.vd;

            boolean foundHd = map.keySet().stream().anyMatch(value -> value.getHd() == hd);
            boolean foundVdAndHd = map.keySet().stream().anyMatch(value -> value.getVd() == vd && value.getHd() == hd);
            if (!foundHd){
                map.put(new Value(hd, vd), node.val);
            } else {
                if (hd > 0 && foundVdAndHd){
                    Value toRemove = new Value(hd, vd);
                    map.put(new Value(hd, vd), node.val);
                }
            }

            if (node.left != null) {
                queue.offer(new Pair(node.left, hd - 1, vd + 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, hd + 1, vd + 1));;
            }
        }

        return map;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        TreeNode[] nodes = new TreeNode[n + 1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new TreeNode(i);
        }

        for (int i = 1; i < n; i++) {
            int v = scanner.nextInt();
            int u = scanner.nextInt();
            char c = scanner.next().charAt(0);
            if (c == 'L') {
                nodes[u].left = nodes[v];
            } else {
                nodes[u].right = nodes[v];
            }
        }

        Map<Value, Integer> result = topView(nodes[1]);
        String ans = result.values().toString();

        System.out.println(ans.substring(1, ans.length() - 1).replaceAll(",", ""));
    }
}
