import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {

    private TreeSet<Point2D> points;

    public PointSET() {
        points = new TreeSet<>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (p == null || !(p instanceof Point2D)) {
            throw new IllegalArgumentException();
        }
        if (!points.contains(p))
            points.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null || !(p instanceof Point2D)) {
            throw new IllegalArgumentException();
        }
        return points.contains(p);
    }

    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null || !(rect instanceof RectHV)) {
            throw new IllegalArgumentException();
        }

        Point2D minPoint = new Point2D(rect.xmin(), rect.ymin());
        Point2D maxPoint = new Point2D(rect.xmax(), rect.ymax());
        List<Point2D> pointsInRect = new LinkedList<>();

        for (Point2D p : points.subSet(minPoint, true, maxPoint, true)) {
            if (p.x() >= rect.xmin() && p.x() <= rect.xmax()) {
                pointsInRect.add(p);
            }
        }
        return pointsInRect;
    }

    public Point2D nearest(Point2D p) {

        if (p == null || !(p instanceof Point2D)) {
            throw new IllegalArgumentException();
        }

        Point2D next = points.ceiling(p);
        Point2D prev = points.floor(p);
        if (next == null && prev == null) {
            return null;
        }

        double distNext = next == null ? Double.MAX_VALUE : p.distanceTo(next);
        double distPrev = prev == null ? Double.MAX_VALUE : p.distanceTo(prev);
        double d = Math.min(distNext, distPrev);

        Point2D minPoint = new Point2D(p.x(), p.y() - d);
        Point2D maxPoint = new Point2D(p.x(), p.y() + d);
        Point2D nearest = next == null ? prev : next;

        for (Point2D candidate: points.subSet(minPoint, true, maxPoint, true)) {
            if (p.distanceTo(candidate) < p.distanceTo(nearest)) {
                nearest = candidate;
            }
        }
        return nearest;
    }

    public static void main(String[] args) {

    }
}
