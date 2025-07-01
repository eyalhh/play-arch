package Geometry;

import java.util.List;
import java.util.ArrayList;
/**
 * Represents a rectangle in a 2-dimensional space.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs a rectangle with the specified upper-left corner, width, and height.
     *
     * @param upperLeft the upper-left corner point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of intersection points between the rectangle and the given line.
     *
     * @param line the line to check for intersections with the rectangle
     * @return a list of intersection points (possibly empty)
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();


        Line topEdge = new Line(upperLeft, getUpperRight());
        Line bottomEdge = new Line(getBottomLeft(), getBottomRight());
        Line leftEdge = new Line(upperLeft, getBottomLeft());
        Line rightEdge = new Line(getUpperRight(), getBottomRight());

        // Add intersection points with each edge of the rectangle
        intersectionPoints.add(line.intersectionWith(topEdge));
        intersectionPoints.add(line.intersectionWith(bottomEdge));
        intersectionPoints.add(line.intersectionWith(leftEdge));
        intersectionPoints.add(line.intersectionWith(rightEdge));

        return intersectionPoints;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left corner point of the rectangle.
     *
     * @return the upper-left corner point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Returns the upper-right corner point of the rectangle.
     *
     * @return the upper-right corner point
     */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY());
    }

    /**
     * Returns the bottom-left corner point of the rectangle.
     *
     * @return the bottom-left corner point
     */
    public Point getBottomLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + height);
    }

    /**
     * Returns the bottom-right corner point of the rectangle.
     *
     * @return the bottom-right corner point
     */
    public Point getBottomRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

}
