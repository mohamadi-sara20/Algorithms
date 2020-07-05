import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        double denominator = that.x - this.x;
        double numerator = that.y - this.y;
        if (numerator == 0 && denominator == 0) return Double.NEGATIVE_INFINITY;
        else if (denominator == 0) return Double.POSITIVE_INFINITY;
        else if (numerator == 0) return 0;
        else return numerator / denominator;
    }

    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y == that.y) {
            if (this.x < that.x) return -1;
            else if (this.x > that.x) return 1;
            else return 0;
        }
        return 1;
    }

    public Comparator<Point> slopeOrder() {
        return (o1, o2) -> Double.compare(slopeTo(o1), slopeTo(o2));
    }


    public String toString() {
        return "(" + x + ", " + y + ")";
    }


}
