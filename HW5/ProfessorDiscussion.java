import java.util.*;

public class ProfessorDiscussion {

    static int[] parentAgree;
    static int[] parentOppose;
    static int[] setSizeAgree;
    static int[] setSizeOppose;
    static int[] units;
    static boolean[] zeroed;

    static int findAgree(int x) {
        if (parentAgree[x] != x) {
            parentAgree[x] = findAgree(parentAgree[x]);
        }
        return parentAgree[x];
    }

    static int findOppose(int x) {
        if (parentOppose[x] != x) {
            parentOppose[x] = findOppose(parentOppose[x]);
        }
        return parentOppose[x];
    }

    static void unionAgree(int x, int y) {
        int xRoot = findAgree(x);
        int yRoot = findAgree(y);
        if (xRoot != yRoot) {
            parentAgree[yRoot] = xRoot;
            setSizeAgree[xRoot] += setSizeAgree[yRoot];
        }
    }

    static void unionOppose(int x, int y) {
        int xRoot = findOppose(x);
        int yRoot = findOppose(y);
        if (xRoot != yRoot) {
            parentOppose[yRoot] = xRoot;
            setSizeOppose[xRoot] += setSizeOppose[yRoot];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();

        units = new int[n + 1];
        zeroed = new boolean[n + 1];
        parentAgree = new int[n + 1];
        parentOppose = new int[n + 1];
        setSizeAgree = new int[n + 1];
        setSizeOppose = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parentAgree[i] = i;
            parentOppose[i] = i;
            setSizeAgree[i] = 1;
            setSizeOppose[i] = 1;
        }

        for (int i = 0; i < q; i++) {
            String event = scanner.next();
            int x, y;
            switch (event) {
                case "U" -> {
                    x = scanner.nextInt();
                    y = scanner.nextInt();
                    unionAgree(x, y); // Merge the sets for agreeing professors
                }
                case "M" -> {
                    x = scanner.nextInt();
                    y = scanner.nextInt();
                    unionOppose(x, y); // Merge the sets for opposing professors
                }
                case "A" -> {
                    x = scanner.nextInt();
                    int xRoot = findAgree(x);
                    if (!zeroed[xRoot]) {
                        for (int j = 1; j <= n; j++) {
                            if (findAgree(j) == xRoot) {
                                units[j] += setSizeAgree[xRoot];
                            }
                        }
                    }
                }
                case "Z" -> {
                    x = scanner.nextInt();
                    int xRoot = findOppose(x);
                    if (!zeroed[xRoot]) {
                        for (int j = 1; j <= n; j++) {
                            if (findOppose(j) == xRoot) {
                                units[j] = 0;
                            }
                        }
                        zeroed[xRoot] = true;
                    }
                }
                case "Q" -> {
                    x = scanner.nextInt();
                    int xRootAgree = findAgree(x);
                    int xRootOppose = findOppose(x);
                    if (!zeroed[xRootOppose]) {
                        System.out.println(units[x]);
                    } else {
                        System.out.println(0);
                    }
                }
            }
        }
    }
}