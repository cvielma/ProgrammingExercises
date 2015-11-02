/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Add and delete intervals: Implement a data structure to support dynamic insertion, deletion of intervals. Overlapping intervals should be merged

see: http://en.wikipedia.org/wiki/Interval_tree
 */
public class Intervals {

    List<Interval> intervals = new ArrayList<>();

    public static class Interval {

        int min, max;

        public Interval(final int min, final int max) {
            this.min = min;
            this.max = max;
        }

        public boolean isInInterval(final int num) {
            return num >= this.min  && num <= this.max;
        }

        public boolean overlaps(final Interval t) {
            return this.isInInterval(t.min) || this.isInInterval(t.max) || t.isInInterval(this.min) || t.isInInterval(this.max);
        }

        public Interval merge(final Interval t) {
            if (overlaps(t)) {
                return new Interval(Math.min(this.min, t.min), Math.max(this.max, t.max));
            }
            return null;
        }

        public Interval intersect(final Interval t) {
            if (overlaps(t)) {
                return new Interval(Math.max(this.min, t.min), Math.min(this.max, t.max));
            }
            return null;
        }

        public List<Interval> substract(final Interval t) {
            List<Interval> result = new ArrayList<>();
            if (Intervals.containsFull(this, t)) {
                if (this.min != t.min) {
                    result.add(new Interval(this.min, t.min -1));
                }
                if (this.max != t.max) {
                    result.add(new Interval(t.max + 1, this.max));
                }
            }
            return result;
        }
        
        public String toString() {
            return this.min == this.max ? "{" + this.max + "}" : "{" + this.min + ", " + this.max + "}";
        }
    }

    public static boolean containsFull(final Interval container, final Interval containee) {
        return container.min <= containee.min && container.max >= containee.max;
    }

    public static boolean containsPartially(final Interval container, final Interval containee) {
        return (container.min <= containee.min && container.max >= containee.min)
                || (container.min <= containee.max && container.max >= containee.max);
    }

    public void insert(final Interval t) {
        Interval found = t;
        List<Interval> result = new ArrayList<>();
        for (int i = 0; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (curr.overlaps(found)) {
                found = found.merge(curr);
            } else {
                result.add(curr);
            }
        }
        result.add(found);
        intervals = result;
    }

    public void delete(final Interval t) {
        List<Interval> result = new ArrayList<>();
        for (Interval curr : intervals) {
            if (containsPartially(curr, t) || containsPartially(t, curr)) {
                if (!containsFull(t, curr)) {
                    result.addAll(curr.substract(curr.intersect(t)));
                }
            } else {
                result.add(curr);
            }
        }
        intervals = result;
    }

    public static void main(String[] args) {
        Intervals test = new Intervals();
        Interval t1 = new Interval(1, 3);
        Interval t2 = new Interval(5, 7);
        Interval t3 = new Interval(2, 4);
        Interval t4 = new Interval(2, 6);
        
        test.insert(t1);
        System.out.println(Arrays.toString(test.intervals.toArray()));
        test.insert(t2);
        System.out.println(Arrays.toString(test.intervals.toArray()));
        test.insert(t3);
        System.out.println(Arrays.toString(test.intervals.toArray()));
        test.insert(t4);
        System.out.println(Arrays.toString(test.intervals.toArray()));
        
        List<Interval> full = test.intervals;
        
        System.out.println("Delete");
        System.out.print("- " + t1 + ": ");
        test.delete(t1);
        System.out.println(Arrays.toString(test.intervals.toArray()));
        
        test.intervals = new ArrayList<>(full);
//        System.out.println(Arrays.toString(test.intervals.toArray()));
        System.out.print("- " + t2 + ": ");
        test.delete(t2);
        System.out.println(Arrays.toString(test.intervals.toArray()));
        
        test.intervals = new ArrayList<>(full);
//        System.out.println(Arrays.toString(test.intervals.toArray()));
        System.out.print("- " + t3 + ": ");
        test.delete(t3);
        System.out.println(Arrays.toString(test.intervals.toArray()));
        
        test.intervals = new ArrayList<>(full);
//        System.out.println(Arrays.toString(test.intervals.toArray()));
        System.out.print("- " + t4 + ": ");
        test.delete(t4);
        System.out.println(Arrays.toString(test.intervals.toArray()));
        
        
    
    }
}
