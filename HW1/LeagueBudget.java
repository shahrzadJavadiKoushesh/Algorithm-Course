import java.util.*;

public class LeagueBudget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        PriorityQueue<Player> players = new PriorityQueue<>(Comparator.comparingInt(Player::getSalary).reversed());
        ArrayList <Player> playersToRemove = new ArrayList<>();
        TreeSet<Object> salariesLeft = new TreeSet<>();

        for (int s = n - 1; s >= 0; s--) {
            int k = scanner.nextInt();
            if (k != 0) {
                for (int i = 0; i < k; i++) {
                    int salary = scanner.nextInt();
                    int seasons = scanner.nextInt();
                    Player newPlayer = new Player(salary, seasons);
                    players.add(newPlayer);
                }

                salariesLeft.clear();
                for (Player player : players) {
//                    System.out.println("player " + player.salary + " seasons left + " player.remainingSeasons);
//                    System.out.println("s " + s);
                    if (player.remainingSeasons == 1) {
                        playersToRemove.add(player);
                        salariesLeft.add(player.salary);
                    }
                    player.remainingSeasons--;
                }
                players.remove(playersToRemove);


                Player leaving = players.poll();
                players.remove(leaving);
//                System.out.println("will leave: ");
//                System.out.println(leaving.salary);
                salariesLeft.add(leaving.salary);
//                System.out.println(salariesLeft);

                String result = salariesLeft.toString();
                result = result.replace(",", "");
                result = result.substring(1, result.length()-1);
                System.out.println(result);
            }

            if (salariesLeft.isEmpty()){
                System.out.println();
            }

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
