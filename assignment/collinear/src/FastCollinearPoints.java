import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lines;

    public FastCollinearPoints(Point[] points) {
        argumentCheck(points);
        int pointsNum = points.length;
        if (pointsNum < 4) {
            lines = new ArrayList<>();
            return;
        }
        points = Arrays.copyOf(points, pointsNum);
        Point[] newArr = new Point[pointsNum - 1];
        lines = new ArrayList<>();
        Arrays.sort(points);

        for (int i = 0; i < pointsNum; i++) {
            Point p = points[i];

            // copy points array without p, and sort by slope
            System.arraycopy(points, 0, newArr, 0, i);
            System.arraycopy(points, i + 1, newArr, i, pointsNum - i - 1);
            Arrays.sort(newArr, p.slopeOrder());

            double slope = p.slopeTo(newArr[0]);
            int times = 1;
            for (int j = 1; j < newArr.length; j++) {
                if (slope == p.slopeTo(newArr[j])) {
                    times++;
                } else {
                    if (times >= 3) {
                        // check if p is the smallest point
                        Point min = min(newArr, j - times, j - 1);
                        Point max = max(newArr, j - times, j - 1);
                        if (p.compareTo(min) < 0) {
                            lines.add(new LineSegment(p, max));
                        }
                    }
                    times = 1;
                    slope = p.slopeTo(newArr[j]);
                }
            }

            if (times >= 3) {
                // check if p is the smallest point
                Point min = min(newArr, pointsNum - times - 1, pointsNum - 2);
                Point max = max(newArr, pointsNum - times - 1, pointsNum - 2);
                if (p.compareTo(min) < 0) {
                    lines.add(new LineSegment(p, max));
                }
            }
        }
    }

    private Point min(Point[] points, int start, int end) {
        Point min = points[start];
        for (int i = start  + 1; i <= end; i++) {
            if (min.compareTo(points[i]) > 0) {
                min = points[i];
            }
        }
        return min;
    }

    private Point max(Point[] points, int start, int end) {
        Point max = points[start];
        for (int i = start + 1; i <= end; i++) {
            if (max.compareTo(points[i]) < 0) {
                max = points[i];
            }
        }

        return max;
    }

    public int numberOfSegments() {
        return lines.size();
    }

    public LineSegment[] segments() {
        return lines.toArray(new LineSegment[lines.size()]);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
