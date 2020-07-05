import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    private final TreeSet<Point2D> tree;

    public PointSET() {
        tree = new TreeSet<>();
    }                               // construct an empty set of points

    public boolean isEmpty() {
        return tree.isEmpty();
    }                      // is the set empty?

    public int size() {
        if (tree.isEmpty()) return 0;
        return tree.size();
    }                     // number of points in the set

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        tree.add(p);
    }            // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return tree.contains(p);
    }           // does the set contain point p?

    public void draw() {
        for (Point2D q : tree) {
            StdDraw.point(q.x(), q.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        ArrayList<Point2D> containedPoints = new ArrayList<>();
        for (Point2D q : tree) {
            if (rect.contains(q)) {
                containedPoints.add(q);
            }
        }
        return containedPoints;
    }          // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (tree == null) return null;
        if (tree.isEmpty()) return null;
        double d = p.distanceSquaredTo(tree.first());
        double pqd = p.distanceSquaredTo(tree.first());
        Point2D chosen = tree.first();
        for (Point2D q : tree) {
            pqd = p.distanceSquaredTo(q);
            if (pqd < d) {
                d = pqd;
                chosen = q;
            }
        }
        return chosen;
    }         // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
        PointSET s = new PointSET();
        System.out.println(s.size());
//        s.insert(new Point2D(0.25, 0.75));
//        s.insert(new Point2D(0.0, 1.0));
//        s.insert(new Point2D(1.0, 0.75));
//        s.insert(new Point2D(0.0, 1.0));
//        s.insert(new Point2D(0.75, 0.5));
//        s.insert(new Point2D(1.0, 0.0));
//        s.insert(new Point2D(0.5, 1.0));
//        s.insert(new Point2D(1.0, 0.0));
//        s.insert(new Point2D(0.25, 0.0));
//        s.insert(new Point2D(0.25, 1.0));
//        Point2D query = new Point2D(0.25, 1.0);
//        System.out.println(s.nearest(new Point2D(2, 2)));
//        System.out.println(s.nearest(query));
//        RectHV r = new RectHV(-1, -1, 11, 11);
//        System.out.println(s.range(r));



    }
}
