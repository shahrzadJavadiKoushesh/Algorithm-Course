import java.util.*;

public class LeagueBudget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        PriorityQueue<Player> players = new PriorityQueue<>(Comparator.comparingInt(Player::getSalary).reversed());
        ArrayList <Player> playersToRemove = new ArrayList<>();
        List<Integer> salariesLeft = new ArrayList<>();


        for (int s = 0; s < n; s++) {
            int k = scanner.nextInt();

            for (int i = 0; i < k; i++) {
                int salary = scanner.nextInt();
                int seasons = scanner.nextInt();
                Player newPlayer = new Player(salary, seasons);
                players.add(newPlayer);
            }

//            System.out.println("New Players to add:");
//            for (Player newPlayer : newPlayers) {
//                System.out.println("salary: " + newPlayer.salary + " seasons: " + newPlayer.remainingSeasons);
//            }

//            System.out.println("adding new players");

//            System.out.println("All players after adding new players: ");
//            for (Player player : players) {
//                System.out.println("salary: " + player.salary + " seasons: " + player.remainingSeasons);
//            }
//            System.out.println("Fuck you " + players.size());

            salariesLeft.clear();
//            System.out.println("salariesLeft");
//            System.out.println(salariesLeft);

//            int maximum = players.get(0).salary;
            Player leaving = players.poll();
//            for (Player value : players) {
//                if (maximum < value.salary) {
//                    maximum = value.salary;
//                    leaving = value;
//                }
//            }
            players.remove(leaving);
//            System.out.println("will leave: ");
//            System.out.println(leaving.salary);
            salariesLeft.add(leaving.salary);

//            System.out.println(salariesLeft);

            for (Player player : players) {
                player.remainingSeasons--;
            }

//            System.out.println("after decreasing seasons:");
//            for (Player player : players) {
////                System.out.println("salary: " + player.salary + " seasons: " + player.remainingSeasons);
//            }

//            System.out.println("Number of players: " + players.size());
//            for (int i = 0; i < players.size(); i++) {
////                System.out.println(i);
//                if (players.get(i).remainingSeasons == 0){
////                    System.out.println("removing: " + players.get(i).salary + " index " + i);
//                    playersToRemove.add(players.get(i));
//                    salariesLeft.add(players.get(i).salary);
//                }
//            }

//            To Do
            for (Player player : players){
                if (player.remainingSeasons == 0){
                    playersToRemove.add(player);
                    salariesLeft.add(player.salary);
                }
            }

            players.remove(playersToRemove);

//            System.out.println("removing finished contracts at the end of season:");
//            for (Player player : players) {
////                System.out.println("salary: " + player.salary + " seasons: " + player.remainingSeasons);
//            }

            Collections.sort(salariesLeft);

            for (Integer salary : salariesLeft) {
                System.out.print(salary + " ");
            }
            System.out.println();
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
