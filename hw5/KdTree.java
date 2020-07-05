import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private Node root;
    private int recurCount;

    private static class Node {
        private final Point2D p;
        private Node left;
        private Node right;
        private boolean red;
        private RectHV rect;

        public Node(Point2D p, Node right, Node left, boolean col) {
            this.p = p;
            this.right = right;
            this.left = left;
            this.red = col;
        }

        public Node(Point2D p) {
            this.p = p;
            this.right = null;
            this.left = null;

        }

        public Node(Point2D p, boolean col) {
            this.p = p;
            this.right = null;
            this.left = null;
            this.red = col;
        }

        public boolean isRed() {
            return this.red;
        }
    }

    private KdTree(Node root) {
        this.root = root;
    }

    public KdTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return recurCount == 0;
    }                      // is the set empty?

    public int size() {
        return recurCount;
    }                     // number of points in the set

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Node n = recInsert(this.root, new Node(p), true, new RectHV(0, 0, 1, 1));
        if (this.root == null) this.root = n;
    }

    private Node recInsert(Node n, Node x, boolean col, RectHV rect) {

        if (x == null) throw new IllegalArgumentException();
        if (n == null) {
            recurCount++;
            Node m = new Node(x.p, col);
            m.rect = rect;
            return m;
        }
        if (x.p.compareTo(n.p) == 0) return n;

        Node result;
        if (n.isRed() && n.p.x() > x.p.x() || !n.isRed() && n.p.y() > x.p.y()) {
            result = recInsert(n.left, x, !col, getLeftRect(n));
            if (n.left == null) n.left = result;
        } else {
            result = recInsert(n.right, x, !col, getRightRect(n));
            if (n.right == null) n.right = result;
        }
        return result;
    }

    private RectHV getRightRect(Node n) {
        if (n == null) return null;
        if (n.right != null) return n.right.rect;
        if (n.isRed()) return new RectHV(n.p.x(), n.rect.ymin(), n.rect.xmax(), n.rect.ymax());
        return new RectHV(n.rect.xmin(), n.p.y(), n.rect.xmax(), n.rect.ymax());

    }

    private RectHV getLeftRect(Node n) {
        if (n == null) return null;
        if ((n.left != null)) return n.left.rect;
        if (n.isRed()) return new RectHV(n.rect.xmin(), n.rect.ymin(), n.p.x(), n.rect.ymax());
        return new RectHV(n.rect.xmin(), n.rect.ymin(), n.rect.xmax(), n.p.y());
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return recContains(this.root, new Node(p));
    }

    private boolean recContains(Node n, Node x) {
        if (x == null) throw new IllegalArgumentException();
        if (n == null) return false;
        if (x.p.compareTo(n.p) == 0) return true;
        if (n.isRed() && n.p.x() > x.p.x() || !n.isRed() && n.p.y() > x.p.y()) return recContains(n.left, x);
        return recContains(n.right, x);
    }

    public void draw() {
        RectHV rect = new RectHV(0, 0, 1, 1);
        draw(rect, this.root);
    }

    private void draw(RectHV rect, Node n) {
        if (n == null) return;
        RectHV leftRect = rect;
        RectHV rightRect = rect;
        StdDraw.setPenColor(0, 0, 0);
        StdDraw.filledCircle(n.p.x(), n.p.y(), 0.01);
        if (n.isRed()) {
            StdDraw.setPenColor(255, 0, 0);
            StdDraw.line(n.p.x(), rect.ymin(), n.p.x(), rect.ymax());
            rightRect = new RectHV(n.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
            leftRect = new RectHV(rect.xmin(), rect.ymin(), n.p.x(), rect.ymax());
        } else {
            StdDraw.setPenColor(0, 0, 255);
            StdDraw.line(rect.xmin(), n.p.y(), rect.xmax(), n.p.y());
            rightRect = new RectHV(rect.xmin(), n.p.y(), rect.xmax(), rect.ymax());
            leftRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), n.p.y());
        }
        draw(rightRect, n.right);
        draw(leftRect, n.left);
    }


    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        return prange(rect, this.root);
    }

    private Iterable<Point2D> prange(RectHV rect, Node n) {
        ArrayList<Point2D> containedPoints = new ArrayList<>();

        if (n == null) return containedPoints;
        if (!rect.intersects(n.rect)) return containedPoints;
        if (rect.contains(n.p)) containedPoints.add(n.p);

        Iterable<Point2D> a = prange(rect, n.right);
        for (Point2D p : a) {
            containedPoints.add(p);
        }
        a = prange(rect, n.left);
        for (Point2D p : a) {
            containedPoints.add(p);
        }
        return containedPoints;
    }

    public Point2D nearest(Point2D p) {
        if (isEmpty()) return null;
        return nearest(p, null, this.root);
    }

    private Point2D nearest(Point2D p, Point2D closest, Node n) {
        if (p == null) throw new IllegalArgumentException();
        if (n == null) return closest;
        if (closest == null) closest = n.p;
        if (p.distanceSquaredTo(closest) > p.distanceSquaredTo(n.p)) closest = n.p;
        if (n.isRed() && n.p.x() > p.x() || !n.isRed() && n.p.y() > p.y()) {
            if (n.left != null && n.left.rect.distanceSquaredTo(p) < p.distanceSquaredTo(closest))
                closest = nearest(p, closest, n.left);

            if (n.right != null && n.right.rect.distanceSquaredTo(p) < p.distanceSquaredTo(closest))
                closest = nearest(p, closest, n.right);
        } else {
            if (n.right != null && n.right.rect.distanceSquaredTo(p) < p.distanceSquaredTo(closest))
                closest = nearest(p, closest, n.right);

            if (n.left != null && n.left.rect.distanceSquaredTo(p) < p.distanceSquaredTo(closest))
                closest = nearest(p, closest, n.left);
        }
        return closest;
    }

    public static void main(String[] args) {

    }
}

