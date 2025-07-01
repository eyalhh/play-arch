package Geometry;

import java.util.List;

/**
 * Represents a line segment in 2-dimensional space.
 */
public class Line {
    private final Point start;
    private final Point end;
    private static final double THRESHOLD = 1e-5;

    /**
     * Constructs a line segment with the specified start and end points.
     *
     * @param start the start point of the line segment
     * @param end   the end point of the line segment
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line segment with the specified coordinates of start and end points.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line segment.
     *
     * @return the length of the line segment
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line segment.
     *
     * @return the middle point of the line segment
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line segment.
     *
     * @return the start point of the line segment
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line segment.
     *
     * @return the end point of the line segment
     */
    public Point end() {
        return end;
    }

    /**
     * Checks if this line segment intersects with another line segment.
     *
     * @param other the other line segment
     * @return {@code true} if the line segments intersect, {@code false} otherwise
     */
    public boolean isIntersecting(Line other) {
        Point start1 = this.start();
        Point end1 = this.end();
        Point start2 = other.start();
        Point end2 = other.end();
        if (((isPointOnLine(start2) && other.isPointOnLine(start1))
                || (isPointOnLine(end2) && other.isPointOnLine(end1))
                || (isPointOnLine(start2) && other.isPointOnLine(end1))
                || (isPointOnLine(end2) && other.isPointOnLine(start1))
                && intersectionWith(other) == null)) {
            return true;

        }

        return intersectionWith(other) != null;

    }

    /**
     * Checks if this line segment intersects with two other line segments.
     *
     * @param other1 the first other line segment
     * @param other2 the second other line segment
     * @return {@code true} if this line segment intersects with both other line segments, {@code false} otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * Returns the intersection point if this line segment intersects with another line segment,
     * and {@code null} otherwise.
     *
     * @param other the other line segment
     * @return the intersection point if the line segments intersect, {@code null} otherwise
     */
    public Point intersectionWith(Line other) {



        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = other.start().getX();
        double y3 = other.start().getY();
        double x4 = other.end().getX();
        double y4 = other.end().getY();

        // Slopes
        double firstSlope = (Math.abs(x2 - x1) <= THRESHOLD) ? Double.POSITIVE_INFINITY : (y2 - y1) / (x2 - x1);
        double secondSlope = (Math.abs(x4 - x3) <= THRESHOLD) ? Double.POSITIVE_INFINITY : (y4 - y3) / (x4 - x3);

        // Check if lines are parallel
        if (Math.abs(firstSlope - secondSlope) <= THRESHOLD) {
            // Parallel lines that are not coincident
            return null;
        }

        if (start().equals(other.end())) {
            return start();
        }
        if (end().equals(other.start())) {
            return end();
        }
        if (start().equals(other.start())) {
            return start();
        }
        if (end().equals(other.end())) {
            return end();
        }

        // Calculate intersection point
        double intersectionX;
        if (Math.abs(x2 - x1) <= THRESHOLD) {
            intersectionX = x1;  // Geometry.Line 1 is vertical
        } else if (Math.abs(x4 - x3) <= THRESHOLD) {
            intersectionX = x3;  // Geometry.Line 2 is vertical
        } else {
            intersectionX = (firstSlope * x1 - secondSlope * x3 + y3 - y1) / (firstSlope - secondSlope);
        }
        double intersectionY;
        if (intersectionX == x1) {
            intersectionY = secondSlope * (intersectionX - x3) + y3;
        } else {
            intersectionY = firstSlope * (intersectionX - x1) + y1;
        }
        Point intersection = new Point(intersectionX, intersectionY);

        // Check if intersection point lies on both lines
        if (isPointOnLine(intersection) && other.isPointOnLine(intersection)) {
            return intersection;
        }

        return null;
    }
    /**
     * Checks if this line segment is equal to another line segment.
     *
     * @param other the other line segment
     * @return {@code true} if the line segments are equal, {@code false} otherwise
     */
    public boolean equals(Line other) {
        return (start.equals(other.start) && end.equals(other.end))
                || (start.equals(other.end) && end.equals(other.start));
    }

    /**
     * Checks if a given point lies on this line segment.
     *
     * @param point the point to check
     * @return {@code true} if the point lies on the line segment, {@code false} otherwise
     */
    public boolean isPointOnLine(Point point) {
        double distanceStartToPoint = start.distance(point);
        double distanceEndToPoint = end.distance(point);
        double lineLength = start.distance(end);
        return Math.abs(distanceStartToPoint + distanceEndToPoint - lineLength) < THRESHOLD;
    }


    /**
     * method to get the closest intersection to the start of the line.
     * @param rect the rectangle
     * @return the closest intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        if (points.isEmpty()) {
            return null;
        }
        int i = 0;
        int counter = 0;
        double smallestDis = this.start.distance(points.get(i));
        while (i < points.size()) {
            if (this.start.distance(points.get(i)) < smallestDis) {
                counter = i;
                smallestDis = this.start.distance(points.get(i));
            }
            i++;
        }
        return points.get(counter);
    }
}