import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;
import java.util.List;

public class KdTree {

    private Node root;
    private int size;

    private enum Separator {
        VERTICAL,
        HORIZONTAL
    };

    private static class Node {

        private final Separator separator;
        private final RectHV rect;
        private final Point2D p;
        private Node leftBottom;
        private Node rightTop;

        Node(Point2D p, Separator separator, RectHV rect) {
            this.p = p;
            this.separator = separator;
            this.rect = rect;
        }

        public Separator nextSeparator() {
            return separator == Separator.VERTICAL ?
                    Separator.HORIZONTAL : Separator.VERTICAL;
        }

        public RectHV rectLeftBottom() {
            return separator == Separator.VERTICAL
                    ? new RectHV(rect.xmin(), rect.ymin(), p.x(), rect.ymax())
                    : new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p.y());
        }

        public RectHV rectRightTop() {
            return separator == Separator.VERTICAL
                    ? new RectHV(p.x(), rect.ymin(), rect.xmax(), rect.ymax())
                    : new RectHV(rect.xmin(), p.y(), rect.xmax(), rect.ymax());
        }

        public boolean isRightOrTop(Point2D q) {
            return (separator == Separator.HORIZONTAL && p.y() > q.y())
                    || (separator == Separator.VERTICAL && p.x() > q.x());
        }
    }

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null || !(p instanceof Point2D)) {
            throw new IllegalArgumentException();
        }

        if (root == null) {
            root = new Node(p, Separator.VERTICAL, new RectHV(0, 0, 1, 1));
            size++;
            return;
        }

        Node prev = null;
        Node curr = root;
        do {
            if (curr.p.equals(p)) {
                return;
            }
            prev = curr;
            curr = curr.isRightOrTop(p) ? curr.leftBottom : curr.rightTop;
        } while (curr != null);

        if (prev.isRightOrTop(p)) {
            prev.leftBottom = new Node(p, prev.nextSeparator(), prev.rectLeftBottom());
        } else {
            prev.rightTop = new Node(p, prev.nextSeparator(), prev.rectRightTop());
        }
        size++;
    }

    public boolean contains(Point2D p) {
        if (p == null || !(p instanceof Point2D)) {
            throw new IllegalArgumentException();
        }

        Node node = root;

        while (node != null) {
            if (node.p.equals(p)) {
                return true;
            }
            node = node.isRightOrTop(p) ? node.leftBottom : node.rightTop;
        }
        return false;
    }

    private void draw(Node node) {
        if (node != null) {
            node.p.draw();
            draw(node.leftBottom);
            draw(node.rightTop);
        }
    }

    public void draw() {
        draw(root);
    }

    private void add(Node node, RectHV rect, List<Point2D> results) {
        if (node == null) {
            return;
        }
        if (rect.contains(node.p)) {
            results.add(node.p);
            add(node.leftBottom, rect, results);
            add(node.rightTop, rect, results);
            return;
        }

        if (node.isRightOrTop(new Point2D(rect.xmin(), rect.ymin()))) {
            add(node.leftBottom, rect, results);
        }

        if (!node.isRightOrTop(new Point2D(rect.xmax(), rect.ymax()))) {
            add(node.rightTop, rect, results);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null || !(rect instanceof RectHV)) {
            throw new IllegalArgumentException();
        }
        List<Point2D> results = new LinkedList<>();
        add(root, rect, results);
        return results;
    }

    private Point2D nearest(Point2D target, Point2D closest, Node node) {
        if (node == null) {
            return closest;
        }

        double closestDist = closest.distanceTo(target);
        if (node.rect.distanceTo(target) < closestDist) {
            double nodeDist = node.p.distanceTo(target);
            if (nodeDist < closestDist) {
                closest = node.p;
            }
            if (node.isRightOrTop(target)) {
                closest = nearest(target, closest, node.leftBottom);
                closest = nearest(target, closest, node.rightTop);
            } else {
                closest = nearest(target, closest, node.rightTop);
                closest = nearest(target, closest, node.leftBottom);
            }
        }
        return closest;
    }

    public Point2D nearest(Point2D p) {
        if (p == null || !(p instanceof Point2D)) {
            throw new IllegalArgumentException();
        }
        return isEmpty() ? null : nearest(p, root.p, root);
    }

    public static void main(String[] args) {

    }
}
