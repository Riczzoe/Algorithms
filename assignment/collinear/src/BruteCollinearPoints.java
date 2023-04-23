import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] lines;

    public BruteCollinearPoints(Point[] points) {
        argumentCheck(points);
        ArrayList<LineSegment> linesList = new ArrayList<>();
        int pointsNum = points.length;
        if (pointsNum < 4) {
            lines = new LineSegment[0];
            return;
        }

        Point[] tmp = Arrays.copyOf(points, pointsNum);
        Arrays.sort(tmp);
        for (int i = 0; i < pointsNum; i++) {
            for (int j = i + 1; j < pointsNum; j++) {
                for (int k = j + 1; k < pointsNum; k++) {
                    for (int m = k + 1; m < pointsNum; m++) {
                        Point p = tmp[i];
                        Point q = tmp[j];
                        Point r = tmp[k];
                        Point s = tmp[m];

                        if (Double.compare(p.slopeTo(q), p.slopeTo(r)) == 0 && Double.compare(p.slopeTo(r),  p.slopeTo(s)) == 0) {
                            linesList.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }

        lines = linesList.toArray(new LineSegment[linesList.size()]);
    }

    public int numberOfSegments() {
        return lines.length;
    }

    public LineSegment[] segments() {
        return lines.clone();
    }

    private void argumentCheck(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        int pointsNum = points.length;

        for (int i = 0; i < pointsNum; i++) {
            Point point = points[i];
            // check for null points
            if (point == null) {
                throw new IllegalArgumentException();
            }

            // check for duplicate points
            for (int j = i - 1; j >= 0; j--) {
                if (point.compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
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
