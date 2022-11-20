import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private class Node {
        Node left;
        Node right;
        Point2D point;
    }

    private Node head = null;
    private int sizeOfTree = 0;

    // construct an empty set of points
    public KdTree() {

    }

    // is the set empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of points in the set
    public int size() {
        return sizeOfTree;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        if (isEmpty()) {
            head = new Node();

            head.left = null;
            head.right = null;
            head.point = p;
            sizeOfTree++;

            return;
        }
        if (contains(p))
            return;

        sizeOfTree++;


        // Not empty
        Node pNode = null, node = head;
        boolean isVertical = true;
        boolean isLeft = true;

        while (node != null) {
            pNode = node;

            if (isVertical) {
                if (p.y() < node.point.y()) {
                    node = node.left;
                    isLeft = true;
                }
                else {
                    node = node.right;
                    isLeft = false;
                }
            }
            else {
                if (p.x() < node.point.x()) {
                    node = node.left;
                    isLeft = true;
                }
                else {
                    node = node.right;
                    isLeft = false;
                }
            }

            isVertical = !isVertical;
        }

        Node newNode = new Node();
        newNode.left = null;
        newNode.right = null;
        newNode.point = p;

        if (isLeft)
            pNode.left = newNode;
        else
            pNode.right = newNode;

    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        Node node = head;
        boolean isVertical = true;

        while (node != null) {
            if (node.point.equals(p))
                return true;

            if (isVertical) {
                if (p.y() < node.point.y()) {
                    node = node.left;
                }
                else {
                    node = node.right;
                }
            }
            else {
                if (p.x() < node.point.x()) {
                    node = node.left;
                }
                else {
                    node = node.right;
                }
            }

            isVertical = !isVertical;
        }


        return false;
    }

    // draw all points to standard draw
    public void draw() {
        // StdDraw.setPenColor(0, 0, 255);
        // StdDraw.setPenRadius(0.05);

        draw(head, "0");
    }

    private void draw(Node node, String text) {
        if (node == null)
            return;

        draw(node.left, text + "L");
        StdDraw.point(node.point.x(), node.point.y());
        StdDraw.text(node.point.x() + 0.05, node.point.y() + 0.05, text);
        draw(node.right, text + "R");
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();

        Queue<Point2D> queue = new Queue<Point2D>();

        range(rect, head, queue, true);
        return queue;
    }

    private void range(RectHV rect, Node node, Queue<Point2D> queue, boolean isVertical) {
        if (node == null)
            return;

        if (rect.contains(node.point))
            queue.enqueue(node.point);

        if (isVertical) {
            if (node.point.y() >= rect.ymin()) {
                range(rect, node.left, queue, !isVertical);
            }
            if (node.point.y() <= rect.ymax()) {
                range(rect, node.right, queue, !isVertical);
            }
        }
        else {
            if (node.point.x() >= rect.xmin()) {
                range(rect, node.left, queue, !isVertical);
            }
            if (node.point.x() <= rect.xmax()) {
                range(rect, node.right, queue, !isVertical);
            }
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        // return nearest(p, head, true);
        if (head == null)
            return null;
        Point2D resultPoint = head.point;


        for (Point2D q : range(new RectHV(0, 0, 1, 1))) {
            if (p.distanceSquaredTo(q) < p.distanceSquaredTo(resultPoint)) {
                resultPoint = q;
            }
        }

        return resultPoint;

    }

    // private Point2D nearest(Point2D p, Node node, boolean isVertical) {
    //     if (node == null)
    //         return null;
    //
    //     double distCurrent = p.distanceSquaredTo(node.point);
    //     Point2D leftPoint = null;
    //     // nearest(p, node.left, !isVertical);
    //     Point2D rightPoint = null;
    //     // nearest(p, node.right, !isVertical);
    //
    // }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        // KdTree kdTree = new KdTree();
        // kdTree.insert(new Point2D(0.7, 0.2));
        // kdTree.insert(new Point2D(0.5, 0.4));
        // kdTree.insert(new Point2D(0.2, 0.3));
        // kdTree.insert(new Point2D(0.4, 0.7));
        // kdTree.insert(new Point2D(0.9, 0.6));
        //
        // // Initial StdDraw
        // StdDraw.setXscale(0, 1);
        // StdDraw.setYscale(0, 1);
        //
        // kdTree.draw();
        //
        // // StdOut.println(kdTree.contains(new Point2D(0.25, 0.25)));
        // // StdOut.println(kdTree.contains(new Point2D(.35, .25)));
        // // StdOut.println(kdTree.contains(new Point2D(.35, .75)));
        //
        // RectHV rectHV = new RectHV(0.27, 0.59, 0.31, 0.76);
        //
        //
        // StdDraw.setPenColor(255, 0, 0);
        // StdDraw.setPenRadius(0.01);
        // StdDraw.rectangle(rectHV.xmin() / 2 + rectHV.xmax() / 2,
        //                   rectHV.ymin() / 2 + rectHV.ymax() / 2,
        //                   rectHV.xmax() / 2 - rectHV.xmin() / 2,
        //                   rectHV.ymax() / 2 - rectHV.ymin() / 2
        // );
        //
        // for (Point2D p : kdTree.range(rectHV)) {
        //     StdOut.println(p);
        // }
        //
        // StdOut.println("NEAREST");
        // StdOut.println(kdTree.nearest(new Point2D(0.51, 0)));
    }
}