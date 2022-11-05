import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final Point[] points;

    private LineSegment[] lineSegments;


    public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
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


        ArrayList<LineSegment> lineSegmentArrayList = new ArrayList<LineSegment>();
        Point[] tempPoints = new Point[4];

        for (int i = 0; i < this.points.length; i++) {
            for (int j = i + 1; j < this.points.length; j++) {
                for (int k = j + 1; k < this.points.length; k++) {
                    for (int ll = k + 1; ll < this.points.length; ll++) {
                        double slopeIJ = this.points[i].slopeTo(this.points[j]);
                        double slopeIK = this.points[i].slopeTo(this.points[k]);
                        double slopeIL = this.points[i].slopeTo(this.points[ll]);

                        if (slopeIJ == slopeIK && slopeIJ == slopeIL) {
                            tempPoints[0] = this.points[i];
                            tempPoints[1] = this.points[j];
                            tempPoints[2] = this.points[k];
                            tempPoints[3] = this.points[ll];

                            Arrays.sort(tempPoints);

                            lineSegmentArrayList.add(
                                    new LineSegment(
                                            tempPoints[0],
                                            tempPoints[3]
                                    ));
                        }
                    }
                }
            }
        }

        this.lineSegments = new LineSegment[lineSegmentArrayList.size()];
        for (int i = 0; i < lineSegmentArrayList.size(); i++) {
            this.lineSegments[i] = lineSegmentArrayList.get(i);
        }
    }

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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.println(collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}