package Sprites;

import biuoop.DrawSurface;
import java.awt.Color;

import Geometry.Point;
import Geometry.Line;
import Accessories.Velocity;
import Collections.GameEnvironment;
import Accessories.CollisionInfo;
import Interfaces.Sprite;
import OverallGame.Game;

/**
 * Represents a ball in 2-dimensional space.
 */
public class Ball implements Sprite {

    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private static final double THRESHOLD = 1e-5;


    /**
     * Constructs a ball with the specified center, radius, and color.
     *
     * @param center   the center point of the ball
     * @param radius   the radius of the ball
     * @param color    the color of the ball
     * @param velocity the velocity of the ball
     * @param gameEnvironment the game environment that the ball is playing on
     */
    public Ball(Point center, int radius, Color color, Velocity velocity, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = velocity;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Gets the x-coordinate of the center of the ball.
     *
     * @return the x-coordinate of the center
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets the y-coordinate of the center of the ball.
     *
     * @return the y-coordinate of the center
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Gets the size (diameter) of the ball.
     *
     * @return the size of the ball
     */
    public int getSize() {
        return radius * 2;
    }

    /**
     * Gets the color of the ball.
     * @return the color of the ball
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the game environment that the ball is playing on.
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), radius);
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), radius);

    }

    /**
     * moves one step.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball with specified changes in the x and y directions.
     *
     * @param dx the change in the x direction
     * @param dy the change in the y direction
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Compute the trajectory of the ball.
     * This trajectory represents the path the ball would take without any collisions.
     * @return a Geometry.Line representing the trajectory of the ball
     */
    public Line computeTrajectory() {
        double distance = 1;
        double endX = center.getX() + velocity.getDx() * distance;
        double endY = center.getY() + velocity.getDy() * distance;
        Point endPoint = new Point(endX, endY);

        return new Line(center, endPoint);
    }

    /**
     * Moves the ball one step according to its velocity, ensuring it stays within the boundaries of the screen.
     */
    public void moveOneStep() {
        Line trajectory = this.computeTrajectory();
        // storing in a variable for computation reasons
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.center = this.velocity.applyToPoint(this.center);
            return;
        }
        velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);
        this.center = this.velocity.applyToPoint(this.center);

    }

    /**
     * Method to set the current color to another color.
     * @param c the color we want to change to
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Method to remove this ball from the game .
     * @param game the game we want to remove the ball from
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

}
