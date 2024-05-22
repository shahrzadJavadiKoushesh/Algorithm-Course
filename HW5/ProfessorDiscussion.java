import java.util.*;

public class ProfessorDiscussion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();

        int[] units = new int[n + 1];
        Map<Integer, Set<Integer>> agreeMap = new HashMap<>(); // agreeing professors
        Map<Integer, Set<Integer>> opposeMap = new HashMap<>(); // opposing professors

        for (int i = 1; i <= n; i++) {
            agreeMap.put(i, new HashSet<>(Collections.singletonList(i)));
            opposeMap.put(i, new HashSet<>(Collections.singletonList(i)));
        }

        for (int i = 0; i < q; i++) {
            String event = scanner.next();
            int x, y;
            switch (event) {
                case "U":
                    x = scanner.nextInt();
                    y = scanner.nextInt();
                    // event 1
                    agreeMap.get(x).addAll(agreeMap.get(y));
                    agreeMap.remove(y);
                    break;
                case "M":
                    x = scanner.nextInt();
                    y = scanner.nextInt();
                    // event 2
                    opposeMap.get(x).addAll(opposeMap.get(y));
                    opposeMap.remove(y);
                    break;
                case "A":
                    x = scanner.nextInt();
                    // event 3
                    int increase = agreeMap.get(x).size();
                    for (int subtopic : agreeMap.get(x)) {
                        units[subtopic] += increase;
                    }
                    break;
                case "Z":
                    x = scanner.nextInt();
                    // event 4
                    for (int subtopic : opposeMap.get(x)) {
                        units[subtopic] = 0;
                    }
                    break;
                case "Q":
                    x = scanner.nextInt();
                    // event 5
                    System.out.println(units[x]);
                    break;
            }
        }
    }
}