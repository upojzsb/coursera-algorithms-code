import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> tree = new TreeSet<Point2D>();

    // construct an empty set of points
    public PointSET() {

    }

    // is the set empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of points in the set
    public int size() {
        return tree.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        if (contains(p))
            return;

        tree.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        return tree.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        // StdDraw.setPenColor(0, 0, 255);
        // StdDraw.setPenRadius(0.05);

        for (Point2D p : tree) {
            // StdOut.println(p);
            StdDraw.point(p.x(), p.y());
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();

        Queue<Point2D> queue = new Queue<Point2D>();

        for (Point2D p : tree) {
            if (
                    p.x() <= rect.xmax() && p.x() >= rect.xmin() &&
                            p.y() <= rect.ymax() && p.y() >= rect.ymin()
            ) {
                queue.enqueue(p);
            }
        }

        return queue;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        // if (isEmpty())
        //     return null;

        Point2D targetPoint = null;
        double maxDistance = Double.POSITIVE_INFINITY;

        for (Point2D q : tree) {
            if (p.distanceSquaredTo(q) < maxDistance) {
                targetPoint = q;
                maxDistance = p.distanceSquaredTo(q);
            }
        }

        return targetPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        // PointSET pointSET = new PointSET();
        // pointSET.insert(new Point2D(0.5, 0.5));
        // pointSET.insert(new Point2D(0.15, 0.85));
        // pointSET.insert(new Point2D(0.55, 0.65));
        // pointSET.insert(new Point2D(0.25, 0.25));
        //
        // // Initial StdDraw
        // StdDraw.setXscale(0, 1);
        // StdDraw.setYscale(0, 1);
        //
        // pointSET.draw();
        //
        // StdOut.println(pointSET.nearest(new Point2D(0, 0)));
        //
        // RectHV rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        //
        // StdDraw.setPenColor(255, 0, 0);
        // StdDraw.setPenRadius(0.01);
        // StdDraw.rectangle(rectHV.xmin() / 2 + rectHV.xmax() / 2,
        //                   rectHV.ymin() / 2 + rectHV.ymax() / 2,
        //                   rectHV.xmax() / 2 - rectHV.xmin() / 2,
        //                   rectHV.ymax() / 2 - rectHV.ymin() / 2
        // );
        //
        // for (Point2D p : pointSET.range(rectHV)) {
        //     StdOut.println(p);
        // }


        // PointSET pointSET = new PointSET();
        // pointSET.insert(new Point2D(1.0, 1.0));
        // pointSET.insert(new Point2D(0.25, 0.75));
        // pointSET.insert(new Point2D(1.0, 0.25));
        // pointSET.insert(new Point2D(0.5, 0.0));
        // pointSET.insert(new Point2D(0.0, 1.0));
        // pointSET.insert(new Point2D(1.0, 0.0));
        // pointSET.insert(new Point2D(0.25, 0.25));
        // pointSET.insert(new Point2D(0.25, 0.5));
        // pointSET.insert(new Point2D(0.25, 0.25));
        // pointSET.insert(new Point2D(0.5, 1.0));
        //
        // pointSET.draw();
        //
        // StdOut.println(pointSET.nearest(new Point2D(0, 0)));
    }
}