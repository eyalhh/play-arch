package Collections;

import Accessories.CollisionInfo;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * Collections.GameEnvironment represents the collection of all collidable objects in the game.
 * It manages adding collidables and finding the closest collision for a given trajectory.
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Constructs a Collections.GameEnvironment object with an empty collection of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Assumes an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory (line) of the moving object
     * @return Accessories.CollisionInfo object containing information about the closest collision,
     * or null if no collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestObject = null;
        double minDistance = Double.MAX_VALUE;

        List<Collidable> copyCollidables = new ArrayList<>(collidables);
        for (Collidable c : copyCollidables) {
            Rectangle rect = c.getCollisionRectangle();
            List<Point> intersectionPoints = rect.intersectionPoints(trajectory);

            for (Point p : intersectionPoints) {
                if (p == null) {
                    continue;
                }
                double distance = trajectory.start().distance(p);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint = p;
                    closestObject = c;
                }
            }
        }

        if (closestPoint == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestObject);

    }

    /**
     * Removing a collidable object from SpriteCollection.
     * @param c the collidable object we want to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
}