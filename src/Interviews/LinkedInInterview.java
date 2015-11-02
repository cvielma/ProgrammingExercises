/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interviews;

/**
 *
 * @author cvielma
 */
public class LinkedInInterview {

    public static class Point {

        public int x, y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "{x: " + x + ", y: " + y + " }";
        }
    }

    public static Point searchMatrix(final int[][] input, final int target) {
        if (input == null) {
            return new Point(-1, -1);
        }
        return searchMatrix(input, target, 0, input.length);
    }

    public static Point searchMatrix(final int[][] input, final int target, final int startRow, final int endRow) {
        if (startRow >= endRow || input == null || input.length == 0) {
            return new Point(-1, -1);
        }

        int middleRow = (startRow + endRow) / 2;
        int rowLow = input[middleRow][0];
        int rowHi = input[middleRow][input[middleRow].length - 1];

        if (target < rowLow) {
            return searchMatrix(input, target, startRow, middleRow - 1);
        } else if (target > rowHi) {
            return searchMatrix(input, target, middleRow + 1, endRow);
        } else if (target == rowLow) {
            return new Point(middleRow, 0);
        } else if (target == rowHi) {
            return new Point(middleRow, input[middleRow].length - 1);
        } else { // target > rowLow && target < rowHi
            int pos = binarySearch(input[middleRow], target, 0, input[middleRow].length);
            if (pos < 0) {
                return new Point(-1, -1);
            }
            return new Point(middleRow, pos);
        }
    }

    public static int binarySearch(final int[] input, final int target, final int start, final int end) {
        if (start >= end || input == null || input.length == 0) {
            return -1;
        }

        int middle = (start + end) / 2;
        if (input[middle] == target) {
            return middle;
        } else {
            if (target < input[middle]) {
                return binarySearch(input, target, start, middle - 1);
            } else {
                return binarySearch(input, target, middle + 1, end);
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] input = new int[5][4];
        input[0] = new int[]{-3, -2, -1, 0};
        input[1] = new int[]{1, 3, 5, 7};
        input[2] = new int[]{10, 11, 16, 20};
        input[3] = new int[]{23, 30, 34, 50};
        input[4] = new int[]{61, 70, 75, 80};
       
        System.out.println(searchMatrix(input, -4)); // -1
        System.out.println(searchMatrix(input, 3)); // 1,1
        System.out.println(searchMatrix(input, 80)); //4, 3
        System.out.println(searchMatrix(input, -3)); //0,0
        System.out.println(searchMatrix(input, 62)); // -1, -1
                
    }
}
