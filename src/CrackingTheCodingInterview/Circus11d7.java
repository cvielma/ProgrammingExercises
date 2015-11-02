/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cvielma
 */
public class Circus11d7 {

    public static class Person implements Comparable<Person> {

        int height, weight;

        public Person(final int height, final int weight) {
            this.height = height;
            this.weight = weight;
        }
                
        public int compareTo(final Person p) {
            if (this.height < p.height && this.weight < p.weight) {
                return -1;
            } else if (this.height > p.height && this.weight > p.weight) {
                return 1;
            } else {
                return 0;
            }
        }
        
        public String toString() {
            return "(" + height + ", " + weight +")";
        }
    }

    public static List<Person> createPeopleTower(final List<Person> list) {
        Collections.sort(list);
        List<Person> result = new LinkedList<>();

        for (Person p : list) {
            if (result.isEmpty()) {
                result.add(p);
            } else {
                if (p.compareTo(result.get(0)) > 0) {
                    result.add(p);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Person> list = new LinkedList<>();
        list.add(new Person(65, 100));
        list.add(new Person(70, 150));
        list.add(new Person(56, 90));
        list.add(new Person(75, 190));
        list.add(new Person(60, 95));
        list.add(new Person(68, 110));
        
        System.out.println(createPeopleTower(list));
    }
}
