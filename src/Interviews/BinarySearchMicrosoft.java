/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interviews;

/**
 *
 * @author cvielma
 */
public class BinarySearchMicrosoft {

    public static int binarySearch(final int[] array, final int key) {
        if (array == null) {
            return -1;
        }

        return binarySearch(array, key, 0, array.length - 1);
    }

    public static int binarySearch(final int[] array, final int key, final int start, final int end) {
        if (end < start) {
            return -1;
        }

        int middleIndex = (start + end) / 2;
        int middleValue = array[middleIndex];

        if (middleValue == key) {
            int tempIndex = binarySearch(array, key, start, middleIndex - 1);

            return tempIndex == -1 ? middleIndex : tempIndex;
        } else {
            if (key < middleValue) {
                return binarySearch(array, key, start, middleIndex - 1);
            } else {
                return binarySearch(array, key, middleIndex + 1, end);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{0, 3, 5, 7, 7, 7, 7, 9, 10}, 7));
    }
}
