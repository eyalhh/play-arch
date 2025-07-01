package Sprites;

import Geometry.Point;
import Geometry.Rectangle;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.Color;

import Accessories.Velocity;
import Interfaces.Sprite;
import Interfaces.Collidable;
import OverallGame.Game;
/**
 * The Sprites.Paddle class represents a paddle in a game.
 * It implements both the Interfaces.Sprite and Interfaces.Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {

    private Rectangle rect;
    private Color color;
    private double speed;
    private KeyboardSensor keyboard;

    /**
     * Constructs a paddle with the specified rectangle, speed, and color.
     *
     * @param rect   the rectangle representing the paddle's position and size
     * @param speed  the speed at which the paddle moves horizontally
     * @param color  the color of the paddle
     * @param kb the keyboard sensor of the gui
     */
    public Paddle(Rectangle rect, double speed, Color color, KeyboardSensor kb) {
        this.rect = rect;
        this.speed = speed;
        this.color = color;
        this.keyboard = kb;
    }

    /**
     * Moves the paddle left by the current speed.
     */
    public void moveLeft() {
        if (rect.getUpperLeft().getX() - speed <= 10) {
            rect = new Rectangle(new Point(790 - rect.getWidth(), rect.getUpperLeft().getY()),
                    rect.getWidth(), rect.getHeight());
            return;
        }
        rect = new Rectangle(new Point(rect.getUpperLeft().getX() - speed,
                rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
    }

    /**
     * Moves the paddle right by the current speed.
     */
    public void moveRight() {
        if (rect.getUpperRight().getX() + speed >= 790) {
            rect = new Rectangle(new Point(10, rect.getUpperLeft().getY()),
                    rect.getWidth(), rect.getHeight());
            return;
        }
        rect = new Rectangle(new Point(rect.getUpperLeft().getX() + speed,
                rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
    }

    /**
     * Updates the paddle's position based on keyboard input.
     * Moves the paddle left if the left key (a or left arrow) is pressed,
     * or moves the paddle right if the right key (d or right arrow) is pressed.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given draw surface.
     *
     * @param d the draw surface on which to draw the paddle
     */
    @Override
    public void drawOn(DrawSurface d) {
        Block block = new Block(rect, color);
        block.drawOn(d);
    }

    /**
     * Returns the collision rectangle representing the paddle.
     *
     * @return the collision rectangle of the paddle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * Determines the new velocity of an object after hitting the paddle at a given collision point
     * with the current velocity.
     *
     * @param collisionPoint  the point of collision with the paddle
     * @param currentVelocity the current velocity of the object
     * @return the new velocity of the object after collision with the paddle
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double paddleWidth = rect.getWidth();
        double paddleX = rect.getUpperLeft().getX();
        double segmentWidth = paddleWidth / 5;

        if (collisionPoint.getX() >= paddleX && collisionPoint.getX() < paddleX + segmentWidth) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        } else if (collisionPoint.getX() >= paddleX + segmentWidth
                && collisionPoint.getX() < paddleX + 2 * segmentWidth) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        } else if (collisionPoint.getX() >= paddleX + 2 * segmentWidth
                && collisionPoint.getX() < paddleX + 3 * segmentWidth) {
            return new Velocity(0, -currentVelocity.getSpeed());
        } else if (collisionPoint.getX() >= paddleX + 3 * segmentWidth
                && collisionPoint.getX() < paddleX + 4 * segmentWidth) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        } else if (collisionPoint.getX() >= paddleX + 4 * segmentWidth
                && collisionPoint.getX() <= paddleX + paddleWidth) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        return currentVelocity;
    }

    /**
     * Adds this paddle to the given game.
     * Registers the paddle as both a sprite and collidable object in the game.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
