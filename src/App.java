import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        Player[] players = {
            new Player(new RandomAI()),
            new Player(new RandomAI()), 
            new Player(new RandomAI()),
        };
        for (Player player : players) {
            System.out.println("Player: " + player);
        }
        Game game = new Game(players);
        while (!game.isGameOver) {
            game.playMove();
            //TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
