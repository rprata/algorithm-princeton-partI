import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {

        private LineSegment lineSegments[];

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        for (int i = 0; i < sortedPoints.length - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        final int N = points.length;
        List<LineSegment> list = new LinkedList<LineSegment>();
        for (int a = 0; a < N - 3; a++) {
            Point pA = sortedPoints[a];
            for (int b = a + 1; b < N - 2; b++) {
                Point pB = sortedPoints[b];
                double slopeAB = pA.slopeTo(pB);
                for (int c = b + 1; c < N - 1; c++) {
                    Point pC = sortedPoints[c];
                    double slopeAC = pA.slopeTo(pC);
                    if (slopeAB == slopeAC) {
                        for (int d = c + 1; d < N; d++) {
                            Point pD = sortedPoints[d];
                            double slopeAD = pA.slopeTo(pD);
                            if (slopeAB == slopeAD) {
                                list.add(new LineSegment(pA, pD));
                            }
                        }
                    }
                }
            }
        }
        lineSegments = list.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
