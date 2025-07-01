package Accessories;

import Geometry.Point;

/**
 * Represents a velocity in 2-dimensional space.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a velocity with the specified change in the x and y directions.
     *
     * @param dx the change in the x direction
     * @param dy the change in the y direction
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the x component of the velocity.
     *
     * @return the x component of the velocity
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets the y component of the velocity.
     *
     * @return the y component of the velocity
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets the velocity components to the given dx and dy.
     *
     * @param dx the new x component of the velocity
     * @param dy the new y component of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the speed of the velocity (the distance of dx, dy).
     * @return the speed of the velocity
     */
    public double getSpeed() {
        Point p = new Point(dx, dy);
        Point o = new Point(0, 0);
        return p.distance(o);
    }

    /**
     * Takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     *
     * @param p the point to apply the velocity to
     * @return a new point with the velocity applied
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + dx;
        double newY = p.getY() + dy;
        return new Point(newX, newY);
    }

    /**
     * Creates a new Accessories.Velocity instance from an angle and a speed.
     *
     * @param angle the angle in degrees (0 being up, 90 being right, etc.)
     * @param speed the speed of the velocity
     * @return a new Accessories.Velocity instance based on the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Convert angle from degrees to radians
        double radians = Math.toRadians(angle - 90);
        // Calculate dx and dy based on the angle and speed
        double dx = speed * Math.cos(radians);
        double dy = speed * Math.sin(radians);
        return new Velocity(dx, dy);
    }
}
