package Listeners;

import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;
import OverallGame.Game;
import Accessories.Counter;
/**
 * BallRemover hit listener.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter availableBalls;

    /**
     * Constructor for BallRemover object.
     * @param game the game
     * @param availableBalls the counter of the available balls in the game
     */
    public BallRemover(Game game, Counter availableBalls) {
        this.game = game;
        this.availableBalls = availableBalls;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block who is being hit.
     * @param hitter   the ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        availableBalls.decrease(1);
        System.out.println(availableBalls.getValue());
    }
}
