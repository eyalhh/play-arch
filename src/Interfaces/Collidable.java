package Interfaces;

import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Accessories.Velocity;

/**
 * The Interfaces.Collidable interface represents objects that can collide with other objects in a game.
 * Implementing classes must provide methods to get their collision rectangle and to handle collisions.
 */
public interface Collidable {

    /**
     * Returns the collision rectangle representing this collidable object.
     *
     * @return the collision rectangle of this collidable object
     */
    Rectangle getCollisionRectangle();

    /**
     * Determines the new velocity of an object after colliding with this collidable object
     * at a specified collision point with the current velocity.
     * @param hitter the ball
     * @param collisionPoint  the point of collision with this collidable object
     * @param currentVelocity the current velocity of the object
     * @return the new velocity of the object after collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
