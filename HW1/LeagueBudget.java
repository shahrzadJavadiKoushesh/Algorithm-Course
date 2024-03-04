import java.util.*;

public class LeagueBudget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        PriorityQueue<Player> players = new PriorityQueue<>(Comparator.comparingInt(Player::getSalary).reversed());
        TreeSet<Object> salariesLeft = new TreeSet<>();

        for (int s = 0; s < n; s++) {
            int k = scanner.nextInt();
            for (int i = 0; i < k; i++) {
                int salary = scanner.nextInt();
                int seasons = scanner.nextInt();
                players.add(new Player(salary, seasons));
            }

            salariesLeft.clear();

            Iterator<Player> iterator = players.iterator();
            while (iterator.hasNext()) {
                Player player = iterator.next();
                player.remainingSeasons--;
                if (player.remainingSeasons == 0) {
                    salariesLeft.add(player.salary);
                    iterator.remove();
                }
            }

            Player leaving = players.poll();
            if (leaving != null){
                salariesLeft.add(leaving.salary);
            }

            String result = salariesLeft.toString();
            result = result.replace(",", "");
            result = result.substring(1, result.length() - 1);
            System.out.println(result);

        }
    }

    static class Player {
        int salary;
        int remainingSeasons;

        public Player(int salary, int seasons) {
            this.salary = salary;
            this.remainingSeasons = seasons;
        }

        public int getSalary() {
            return salary;
        }
    }
}
