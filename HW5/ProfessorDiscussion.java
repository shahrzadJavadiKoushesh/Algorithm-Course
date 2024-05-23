import java.util.*;

public class ProfessorDiscussion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();

        int[] units = new int[n + 1];
        Map<Integer, Set<Integer>> agreeMap = new HashMap<>();
        Map<Integer, Set<Integer>> opposeMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            agreeMap.put(i, new HashSet<>(List.of(i)));
            opposeMap.put(i, new HashSet<>(List.of(i)));
        }

        for (int i = 0; i < q; i++) {
            String event = scanner.next();
            int x, y;
            switch (event) {
                case "U":
                    x = scanner.nextInt();
                    y = scanner.nextInt();
                    if (agreeMap.containsKey(y)) {
                        agreeMap.get(x).addAll(agreeMap.get(y));
                        agreeMap.remove(y);
                    }
                    break;
                case "M":
                    x = scanner.nextInt();
                    y = scanner.nextInt();
                    if (opposeMap.containsKey(y)) {
                        opposeMap.get(x).addAll(opposeMap.get(y));
                        opposeMap.remove(y);
                    }
                    break;
                case "A":
                    x = scanner.nextInt();
                    if (agreeMap.containsKey(x)) {
                        for (int subtopic : agreeMap.get(x)) {
                            if (subtopic >= 1 && subtopic <= n) {
                                units[subtopic] += agreeMap.get(x).size();
                            }
                        }
                    }
                    break;
                case "Z":
                    x = scanner.nextInt();
                    if (opposeMap.containsKey(x)) {
                        for (int subtopic : opposeMap.get(x)) {
                            if (subtopic >= 1 && subtopic <= n) {
                                units[subtopic] = 0;
                            }
                        }
                    }
                    break;
                case "Q":
                    x = scanner.nextInt();
                    if (x >= 1 && x <= n) {
                        System.out.println(units[x]);
                    }
                    break;
            }
        }
    }
}