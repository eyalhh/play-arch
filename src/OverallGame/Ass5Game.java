package OverallGame;

/**
 * The OverallGame.Ass3Game class contains the main method to start the game.
 * It initializes a OverallGame.Game instance and runs the game.
 */
public class Ass5Game {

    /**
     * Main method to start the game.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
