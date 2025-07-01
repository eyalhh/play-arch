package Geometry;
/**
 * Represents a point in a 2-dimensional space.
 */
public class Point {
    private final double x;
    private final double y;
    private static final double THRESHOLD = 1e-5;

    /**
     * Constructs a point with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point
     * @return {@code true} if the points are equal, {@code false} otherwise
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.x) < THRESHOLD && Math.abs(this.y - other.y) < THRESHOLD;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}
