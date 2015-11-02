/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Find the K closest points to the origin in 2D plane, given an array containing N points. You can assume K is much smaller than N and N is very large.
 * http://www.careercup.com/question?id=4751976126480384
 */
public class KClosestPoints {

    public static class Point {

        int x, y;

        public Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public double distanceToOrigin() {
            return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }

        public String toString() {
            return "(" + this.x + ", " + y + ")"; //[" + distanceToOrigin() + "]";
        }
    }

    public static class ClosestComparator implements Comparator<Point> {

        public static final double epsilon = 0.0001;

        public int compare(final Point p1, final Point p2) {
            if (p1 != null && p2 != null) {
                double diff = p1.distanceToOrigin() - p2.distanceToOrigin();
                if (Double.compare(diff, 0.0) < 0) {
                    return -1;
                } else if (diff >= 0.0 && diff <= epsilon) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                if (p1 != null) {
                    return 1;
                } else if (p2 != null) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    public static List<Point> closestPoints(final Point[] points, final int k) {
        List<Point> result = new LinkedList<>();
        Arrays.sort(points, new ClosestComparator());
        for (int i = 0; i < k; i++) {
            result.add(points[i]);
        }

        return result;
    }

    public static Point[] init() {
        List<Point> init = new LinkedList<>();
        init.add(new Point(-5, 4));
        init.add(new Point(-3, 1));
        init.add(new Point(-1, 1));
        init.add(new Point(-2, -2));
        init.add(new Point(-1, -1));
        init.add(new Point(6, -5));
        init.add(new Point(2, -3));
        init.add(new Point(1, -2));
        init.add(new Point(5, 6));
        init.add(new Point(4, 2));
        init.add(new Point(3, 4));
        init.add(new Point(3, 2));
        init.add(new Point(2, 3));
        init.add(new Point(0, 3));
        init.add(new Point(2, 0));
        init.add(new Point(1, 2));
        init.add(new Point(1, 1));
        return init.toArray(new Point[]{});
    }

    public static void main(String[] args) {
    
        System.out.println(closestPoints(init(), 3)); // (1, 1), (-1, 1) and (-1, -1)

        System.out.println(closestPoints(init(), 7)); // (1, 1), (-1, 1), (-1, -1), (2, 0), (1, -2), (-2, -2), (1, 2)
        
        System.out.println(closestPoints(init(), 1)); // (1,1), (-1, 1) or (-1, -1)
        
        System.out.println(closestPoints(init(), 16)); // All but (6,6)
        
    }
}