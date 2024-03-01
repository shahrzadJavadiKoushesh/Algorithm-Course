import java.util.*;

public class LeagueBudget {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<List> salariesLeft = new ArrayList<>();
        Map<Integer, List> playersInfo = new HashMap<>();

        if (n == 0) {
            System.out.println();
        }
        for (int s = 1; s <= n; s++) {
            salariesLeft.clear();
            int k = scanner.nextInt();

            while (k > 0){
                long salary = scanner.nextLong();
                int seasons = scanner.nextInt();
                if (playersInfo.containsKey(seasons + s)) {
                    List salaries = playersInfo.get(seasons + s);
                    salaries.add(salary);
                } else {
                    List<Long> salaries = new ArrayList<>();
                    salaries.add(salary);
                    playersInfo.put(seasons + s, salaries);
                }

                k--;
            }
            playersInfo.get(s + 1);
            if (playersInfo.get(s + 1) != null && !playersInfo.get(s + 1).isEmpty()) {
                salariesLeft.add(playersInfo.get(s + 1));
            }
            playersInfo.remove(s + 1);

//                                removing the highest salary
            ArrayList<Long> allIntegers = new ArrayList<>();
            for (List sublist : playersInfo.values()) {
                allIntegers.addAll(sublist);
            }

            if (!allIntegers.isEmpty()) {
                long max = Collections.max(allIntegers);
                playersInfo.forEach((key, v) -> v.removeIf(i -> i.equals(max)));
                Long[] temp = new Long[1];
                temp[0] = (max);
                salariesLeft.add(List.of(temp));
            }

            ArrayList<Integer> allSalaries = new ArrayList<>();
            for (List sublist : salariesLeft) {
                allSalaries.addAll(sublist);
            }

            Collections.sort(allSalaries);
            String result = allSalaries.toString().substring(1, allSalaries.toString().length() - 1);
            result = result.replaceAll(",", "");
            System.out.println(result);
        }
    }
}

