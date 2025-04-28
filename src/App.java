import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.HashMap;

public class App {
    @SuppressWarnings("unchecked")
    public static Class<? extends PlayerAI>[] aiClasses = new Class[] {
        RandomAI.class,
        PlayWorstAI.class,
        PlayBestAI.class,
        WildLastAI.class,
        DrawUntilWildKeep.class,
    };

    public static FileLog log = new FileLog("log.txt");
    public static long repeats = 1000;

    public static void main(String[] args) throws Exception {
        HashMap<Class<?>, Integer> wins = new HashMap<>();
        for (Class<?> aiClass : aiClasses) {
            wins.put(aiClass, 0);
        }
        log.log("");
        log.log(new Date().toString());
        log.log("Doing " + repeats + " games for each combination of players.");
        log.log("");
        log.log("2 Players:");
        for (Class<?> player1Class : aiClasses) {
            for (Class<?> player2Class : aiClasses) {
                //for (Class<?> player3Class : aiClasses) {
                    //for (Class<?> player4Class : aiClasses) {
                        Player[] players = { 
                            new Player((PlayerAI) player1Class.getConstructor(new Class<?>[] {}).newInstance()),
                            new Player((PlayerAI) player2Class.getConstructor(new Class<?>[] {}).newInstance()),
                    //        new Player((PlayerAI) player3Class.getConstructor(new Class<?>[] {}).newInstance()),
                    //        new Player((PlayerAI) player4Class.getConstructor(new Class<?>[] {}).newInstance())
                        };
                        for (Player player : players) {
                            // System.out.println("Player: " + player.playerAI.getClass().getSimpleName());
                        }
                        for (int i = 0; i < repeats; i++) {
                            Game game = new Game(players);
                            while (!game.isGameOver) {
                                game.playMove();
                                //TimeUnit.MILLISECONDS.sleep(500);
                            }
                            Class<?> aiclass = players[game.winnerIndex].playerAI.getClass();
                            wins.put(aiclass, wins.get(aiclass) + 1);
                        }
                    //}
                //}
            }
        }
        long totalGames = 0;
        for (Class<?> aiClass : wins.keySet()) {
            totalGames += wins.get(aiClass);
        }

        for (Class<?> aiClass : wins.keySet()) {
            log.log(aiClass.getSimpleName() + ": " + wins.get(aiClass) + " wins (" + (double) wins.get(aiClass) / totalGames * 100 + "%)");
        }
        log.log("");

        log.log("3 Players:");
        for (Class<?> player1Class : aiClasses) {
            for (Class<?> player2Class : aiClasses) {
                for (Class<?> player3Class : aiClasses) {
                    //for (Class<?> player4Class : aiClasses) {
                        Player[] players = { 
                            new Player((PlayerAI) player1Class.getConstructor(new Class<?>[] {}).newInstance()),
                            new Player((PlayerAI) player2Class.getConstructor(new Class<?>[] {}).newInstance()),
                            new Player((PlayerAI) player3Class.getConstructor(new Class<?>[] {}).newInstance()),
                    //        new Player((PlayerAI) player4Class.getConstructor(new Class<?>[] {}).newInstance())
                        };
                        for (Player player : players) {
                            // System.out.println("Player: " + player.playerAI.getClass().getSimpleName());
                        }
                        for (int i = 0; i < repeats; i++) {
                            Game game = new Game(players);
                            while (!game.isGameOver) {
                                game.playMove();
                                //TimeUnit.MILLISECONDS.sleep(500);
                            }
                            Class<?> aiclass = players[game.winnerIndex].playerAI.getClass();
                            wins.put(aiclass, wins.get(aiclass) + 1);
                        }
                    //}
                }
            }
        }
        totalGames = 0;
        for (Class<?> aiClass : wins.keySet()) {
            totalGames += wins.get(aiClass);
        }

        for (Class<?> aiClass : wins.keySet()) {
            log.log(aiClass.getSimpleName() + ": " + wins.get(aiClass) + " wins (" + (double) wins.get(aiClass) / totalGames * 100 + "%)");
        }
        log.log("");

        log.log("4 Players:");
        for (Class<?> player1Class : aiClasses) {
            for (Class<?> player2Class : aiClasses) {
                for (Class<?> player3Class : aiClasses) {
                    for (Class<?> player4Class : aiClasses) {
                        Player[] players = { 
                            new Player((PlayerAI) player1Class.getConstructor(new Class<?>[] {}).newInstance()),
                            new Player((PlayerAI) player2Class.getConstructor(new Class<?>[] {}).newInstance()),
                            new Player((PlayerAI) player3Class.getConstructor(new Class<?>[] {}).newInstance()),
                            new Player((PlayerAI) player4Class.getConstructor(new Class<?>[] {}).newInstance())
                        };
                        for (Player player : players) {
                            // System.out.println("Player: " + player.playerAI.getClass().getSimpleName());
                        }
                        for (int i = 0; i < repeats; i++) {
                            Game game = new Game(players);
                            while (!game.isGameOver) {
                                game.playMove();
                                //TimeUnit.MILLISECONDS.sleep(500);
                            }
                            Class<?> aiclass = players[game.winnerIndex].playerAI.getClass();
                            wins.put(aiclass, wins.get(aiclass) + 1);
                        }
                    }
                }
            }
        }
        totalGames = 0;
        for (Class<?> aiClass : wins.keySet()) {
            totalGames += wins.get(aiClass);
        }

        for (Class<?> aiClass : wins.keySet()) {
            log.log(aiClass.getSimpleName() + ": " + wins.get(aiClass) + " wins (" + (double) wins.get(aiClass) / totalGames * 100 + "%)");
        }
        log.log("");
    }
    
    public static Class<? extends PlayerAI> getRandomClass() {
        return aiClasses[(int) (Math.random() * aiClasses.length)];
    }
}
