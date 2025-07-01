package Listeners;

import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;
import Accessories.Counter;

/**
 * Listener to keep track of the player's score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor for ScoreTrackingListener.
     * @param scoreCounter the counter for the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block who is being hit.
     * @param hitter   the ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
