import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {
    private final Point[] points;
    private Point[] tempPoints;

    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        for (Point point : points)
            if (point == null)
                throw new IllegalArgumentException();

        this.points = points.clone();

        Arrays.sort(this.points);
        for (int i = 0; i < this.points.length - 1; i++)
            if (this.points[i].compareTo(this.points[i + 1]) == 0)
                throw new IllegalArgumentException();

        // finds all line segments containing 4 or more points
        this.tempPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            tempPoints[i] = points[i];
        }

        if (this.points.length < 4) {
            this.lineSegments = new LineSegment[0];
            return;
        }

        ArrayList<Point> pointArrayList;
        ArrayList<Point> markedPoints = new ArrayList<Point>();
        ArrayList<Double> markedSlope = new ArrayList<Double>();
        ArrayList<LineSegment> lineSegmentArrayList = new ArrayList<LineSegment>();

        for (int i = 0; i < this.points.length; i++) {
            Point p = this.points[i];
            Arrays.sort(tempPoints, p.slopeOrder());

            int j = 1;
            do {
                pointArrayList = new ArrayList<Point>();
                pointArrayList.add(p);
                pointArrayList.add(tempPoints[j]);

                double slope = p.slopeTo(tempPoints[j]);

                for (int k = j + 1; k < tempPoints.length; k++) {
                    if (slope == p.slopeTo(tempPoints[k])) {
                        pointArrayList.add(tempPoints[k]);
                    }
                    else {
                        break;
                    }
                }

                if (pointArrayList.size() >= 4) {
                    Collections.sort(pointArrayList);

                    boolean visited = false;
                    for (int q = 0; q < markedPoints.size(); q++) {
                        if (markedPoints.get(q).compareTo(pointArrayList.get(0)) == 0
                                && markedSlope.get(q) == slope) {
                            // Already visited
                            visited = true;
                            break;
                        }
                    }

                    if (!visited) {
                        // printPointArrayList(pointArrayList);

                        markedPoints.add(pointArrayList.get(0));
                        markedSlope.add(slope);

                        lineSegmentArrayList.add(
                                new LineSegment(
                                        pointArrayList.get(0),
                                        pointArrayList.get(pointArrayList.size() - 1)
                                )
                        );
                    }

                }

                j = j + pointArrayList.size() - 1;
            } while (j < tempPoints.length - 1);
        }

        this.lineSegments = new LineSegment[lineSegmentArrayList.size()];
        for (int i = 0; i < lineSegmentArrayList.size(); i++) {
            this.lineSegments[i] = lineSegmentArrayList.get(i);
        }

    }

    // private void printPointArrayList(ArrayList<Point> pal) {
    //     StdOut.println("Start");
    //     for (Point point : pal) {
    //         StdOut.println(point);
    //     }
    //     StdOut.println("End");
    //
    // }

    public int numberOfSegments() {        // the number of line segments
        return this.lineSegments.length;
    }

    public LineSegment[] segments() {                // the line segments
        return this.lineSegments.clone();
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StdOut.println(collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}