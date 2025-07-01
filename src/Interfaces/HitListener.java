package Interfaces;

import Sprites.Block;
import Sprites.Ball;

/**
 * Interface for hit listener objects.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block who is being hit.
     * @param hitter the ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
