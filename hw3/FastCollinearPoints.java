import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private final Point[] points;
    private int c = 0;
    //    private int here;
    private ArrayList<Point[]> seen = new ArrayList<Point[]>();

    public FastCollinearPoints(Point[] ppoints) {
        if (ppoints == null) throw new IllegalArgumentException();

        this.points = new Point[ppoints.length];
        for (int i = 0; i < ppoints.length; i++) {
            if (ppoints[i] == null) throw new IllegalArgumentException();
            this.points[i] = ppoints[i];
        }

        Arrays.sort(points, Comparator.naturalOrder());
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) throw new IllegalArgumentException();
        }

    }

    // finds all line segments containing 4 or more points
    public int numberOfSegments() {
        return c;
    }

    public LineSegment[] segments() {
        ArrayList<LineSegment> ls = new ArrayList<LineSegment>();

        for (int i = 0; i < points.length; i++) {
//            here = i;
//            for (int j = 0; j < points.length; j++) {
//                points[j].setSlwithorigin(points[j].slopeTo(points[i]));
//            }
            Point origin = points[i];
            Point[] tempPoints = new Point[points.length - 1];
            for (int t = 0; t < points.length - 1; t++) {
                if (t < i) tempPoints[t] = points[t];
                if (t >= i) tempPoints[t] = points[t + 1];
            }

            Arrays.sort(tempPoints, Comparator.naturalOrder());
//            Arrays.sort(tempPoints, new Comparator<Point>() {
//                @Override
//                public int compare(Point o1, Point o2) {
//                    return Double.compare(o1.getSlwithorigin(), o2.getSlwithorigin());
//                }
//            });
            Arrays.sort(tempPoints, origin.slopeOrder());


            int k = 1;
            int counter = 1;

            while (k < tempPoints.length) {
                if (Double.compare(origin.slopeTo(tempPoints[k]), origin.slopeTo(tempPoints[k - 1])) == 0) {
                    counter++;
                    if (k == tempPoints.length - 1 && counter >= 3) {
                        createSegment(tempPoints[k - counter + 1], tempPoints[k], origin, ls);
//                        addToLineSegment(ls, a);
                    }
                } else {
                    if (counter >= 3) {
//                        Point start = tempPoints[k - counter];
//                        Point end = tempPoints[k - 1];
//                        if (origin.compareTo(start) < 0) {
//                            start = origin;
//                        }
//                        if (origin.compareTo(end) > 0) {
//                            end = origin;
//                        }
//                        LineSegment a = new LineSegment(start, end);
//                        ls.push(a);

                        createSegment(tempPoints[k - counter], tempPoints[k - 1], origin, ls);
//                        addToLineSegment(ls, a);

                    }
                    counter = 1;

                }
                k++;
            }
        }

        LineSegment[] lineSegArr = new LineSegment[ls.size()];
        for (int i = 0; i < ls.size(); i++) lineSegArr[i] = ls.get(i);
        return lineSegArr;
    }


    private void addToLineSegment(ArrayList<LineSegment> ar, LineSegment linesegment, Point start, Point end) {
        boolean found = false;

        for (int i = 0; i < ar.size(); i++) {
//            if (ar.get(i).toString().equals(linesegment.toString())) {
            Point[] seg = {start, end};
            if (seen.get(i)[0] == start && seen.get(i)[1] == end) {
                found = true;
                break;
            }
        }
        if (!found) {
            ar.add(linesegment);
            Point[] seg = {start, end};
            seen.add(seg);
            c++;
        }

    }

//}

    private LineSegment createSegment(Point start, Point end, Point origin, ArrayList<LineSegment> ls) {
        if (origin.compareTo(start) < 0) {
            start = origin;
        }
        if (origin.compareTo(end) > 0) {
            end = origin;
        }
        LineSegment a = new LineSegment(start, end);
        addToLineSegment(ls, a, start, end);
        return a;
    }


    public static void main(String[] args) {
        Point[] points = {new Point(18000, 13000), new Point(18000, 23000), new Point(18000, 26000), new Point(18000, 27000)};
        FastCollinearPoints f = new FastCollinearPoints(points);
        LineSegment[] s = f.segments();
        for (int i = 0; i < s.length; i++) System.out.println(s[i]);

    }
}
