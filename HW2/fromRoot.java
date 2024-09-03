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

    static class Value implements Comparable<Value> {
        int hd;
        int vd;

        public Value(int hd, int vd) {
            this.hd = hd;
            this.vd = vd;
        }

        @Override
        public int compareTo(Value other) {
            return Integer.compare(this.hd, other.hd);
        }
    }

    public static Map<Value, Integer> topView(TreeNode root) {
        Map<Value, Integer> map = new TreeMap<>();

        if (root == null) {
            return map;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0, 0));
        int maxHd = 0, minHd = 0;
        Map<Integer, Integer> vds = new HashMap<>();
        while (!queue.isEmpty()) {

            Pair current = queue.poll();
            TreeNode node = current.node;
            int hd = current.hd;
            int vd = current.vd;

            if (hd > maxHd) {
                map.put(new Value(hd, vd), node.val);
                vds.put(hd, vd);
                maxHd = hd;
            } else if (hd == maxHd && vds.isEmpty()) {
                // Just for root
                map.put(new Value(hd, vd), node.val);
                vds.put(0, 0);
            } else if (hd == maxHd) {
                if (vd <= vds.get(hd)) {
                    map.put(new Value(hd, vd), node.val);
                    vds.put(hd, vd);
                }
            } else if (hd < minHd) {
                map.put(new Value(hd, vd), node.val);
                minHd = hd;
            }

            if (node.left != null) {
                queue.offer(new Pair(node.left, hd - 1, vd + 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, hd + 1, vd + 1));
                ;
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
        for (Integer anss : result.values()) {
            System.out.print(anss + " ");
        }

    }
}
