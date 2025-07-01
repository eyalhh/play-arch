package Listeners;

import Interfaces.HitListener;
import Sprites.Block;
import Sprites.Ball;
import OverallGame.Game;
import Accessories.Counter;
/**
 * A BlockRemover is in charge of removing blocks from the game.
 * as well as keeping count of the number of blocks that remain
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor for BlockRemover.
     * @param game the Game
     * @param remainingBlocks the Counter
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Method to remove from the game the blocks that are hit.
     * and also to remove this listener from the block that is being removed
     * @param beingHit the Block that is being hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
