import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BruteCollinearPoints {
    private final Point[] points;
    private int c;

    public BruteCollinearPoints(Point[] ppoints) {
        if (ppoints == null) throw new IllegalArgumentException();
        this.points = new Point[ppoints.length];
        for (int i = 0; i < ppoints.length; i++) {
            if (ppoints[i] == null) throw new IllegalArgumentException();
            this.points[i] = ppoints[i];
        }

        Arrays.sort(this.points, Comparator.naturalOrder());
        for (int i = 1; i < this.points.length; i++) {
            if (this.points[i].compareTo(this.points[i - 1]) == 0) throw new IllegalArgumentException();
        }


    }

    public int numberOfSegments() {       // the number of line segments
        return c;
    }

    public LineSegment[] segments() {
        ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
        c = 0;

        for (int i = 0; i < points.length - 3; i++) {
            Point start = points[i];
            for (int j = i + 1; j < points.length - 2; j++) {
                double slopeij = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length - 1; k++) {
                    double slopejk = points[j].slopeTo(points[k]);
                    if (Double.compare(slopeij, slopejk) != 0) continue;
                    for (int v = k + 1; v < points.length; v++) {
                        double slopekl = points[k].slopeTo(points[v]);
                        Point end = points[v];
                        if (Double.compare(slopeij, slopejk) == 0 && Double.compare(slopejk, slopekl) == 0) {
                            LineSegment a = new LineSegment(start, end);
                            ls.add(a);

                        }
                    }
                }
            }
        }

        c = ls.size();
        LineSegment[] lineSegArr = new LineSegment[ls.size()];
        for (int i = 0; i < ls.size(); i++)
            lineSegArr[i] = ls.get(i);
        return lineSegArr;
    }


    public static void main(String[] args) {
        Point p1 = new Point(10000, 0);
        Point p2 = new Point(8000, 2000);
        Point p3 = new Point(2000, 8000);
        Point p4 = new Point(0, 10000);
        Point p5 = new Point(20000, 0);
        Point p6 = new Point(18000, 2000);
        Point p7 = new Point(2000, 18000);
        Point p8 = new Point(10000, 20000);
        Point p9 = new Point(30000, 0);
        Point p10 = new Point(0, 30000);
        Point p11 = new Point(20000, 10000);
        Point p12 = new Point(13000, 0);
        Point p13 = new Point(11000, 3000);
        Point p14 = new Point(5000, 12000);
        Point p15 = new Point(9000, 6000);
        Point[] points = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15};
        BruteCollinearPoints b = new BruteCollinearPoints(points);
        LineSegment[] s = b.segments();
        for (int i = 0; i < s.length; i++) System.out.println(s[i]);
    }
}

