package Sprites;

import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import Interfaces.HitListener;
import Accessories.Velocity;
import Interfaces.Sprite;
import Interfaces.Collidable;
import Interfaces.HitNotifier;
import OverallGame.Game;
/**
 * Represents a block in a game, implementing the Interfaces.Collidable interface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;

    private Rectangle rect;
    private Color color;
    private static final double THRESHOLD = 1e-5;
    /**
     * Constructs a Sprites.Block with the specified rectangle.
     *
     * @param rect the rectangle representing the block
     * @param color the color of the block
     */
    public Block(Rectangle rect, Color color) {
        hitListeners = new ArrayList<>();
        this.rect = rect;
        this.color = color;
    }

    /**
     * Returns the collision rectangle representing this block.
     *
     * @return the collision rectangle of this block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * Draws the block on the draw surface.
     * @param d the drawing surface that the block will be drawn on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());


    }


    /**
     * Determines the new velocity of an object after hitting this block at a given collision point
     * with the current velocity.
     *
     * @param collisionPoint  the point of collision with the block
     * @param currentVelocity the current velocity of the object
     * @return the new velocity of the object after collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (!this.ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        if ((Math.abs(collisionPoint.getX() - rect.getUpperLeft().getX()) < THRESHOLD
                || Math.abs(collisionPoint.getX() - rect.getUpperRight().getX()) < THRESHOLD)
                && (Math.abs(collisionPoint.getY() - rect.getUpperLeft().getY()) < THRESHOLD
                || Math.abs(collisionPoint.getY() - rect.getBottomLeft().getY()) < THRESHOLD)
        ) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (Math.abs(collisionPoint.getX() - rect.getUpperLeft().getX()) < THRESHOLD
                || Math.abs(collisionPoint.getX() - rect.getUpperRight().getX()) < THRESHOLD) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (Math.abs(collisionPoint.getY() - rect.getUpperLeft().getY()) < THRESHOLD
                || Math.abs(collisionPoint.getY() - rect.getBottomLeft().getY()) < THRESHOLD) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        return currentVelocity;
    }


    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Boolean method to compare the color of this Block and a ball.
     * @param ball the ball we want to compare its color
     * @return true is the colors match and false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color == ball.getColor();
    }

    /**
     * method to remove this block from the game.
     * @param game the game
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Method to notify hit event to all the registered hit listeners.
     * @param hitter the ball that hit this block
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener listener : listeners) {
            listener.hitEvent(this, hitter);
        }
    }

    /**
     * Adds h1 as a listener to hit events.
     *
     * @param h1 the hitListener
     */
    @Override
    public void addHitListener(HitListener h1) {
        hitListeners.add(h1);
    }

    /**
     * Removes h1 from the list of listeners to hit events.
     *
     * @param h1 the hitListener
     */
    @Override
    public void removeHitListener(HitListener h1) {
        hitListeners.remove(h1);
    }

    /**
     * Method to get the color of this block.
     * @return the color of this block
     */
    public Color getColor() {
        return this.color;
    }
}
