/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interviews;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Not showing exact question, but just resolution for confidentiality purposes
 * @author cvielma
 */
public class FacebookInterview {

    public static class Node {

        int x, y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int findLargestComponent(final int[][] matrix) {
        // TODO: matrix is null

        int max = 0;
        Node cursor = new Node(0, 0);

        while (cursor.x < matrix.length && cursor.y < matrix[0].length) { //TODO: check 
            cursor = findNext(cursor, matrix);
            if (cursor == null) {
                break;
            }
            int temp = findComponentSize(cursor, matrix);
            if (temp > max) {
                max = temp;
            }
        }

        return max;
    }

    public static Node findNext(final Node cursor, final int[][] matrix) {
        int col = cursor.y;
        int row = cursor.x;
        while (row < matrix.length) {
            while (col < matrix[0].length) {
                if (matrix[row][col] == 1) {
                    cursor.x = row;
                    cursor.y = col;
                    return cursor;
                }
                col++;
            }
            col = 0;
            row++;
        }
        return null;
    }

    public static int findComponentSize(final Node cursor, final int[][] matrix) {
        Queue<Node> discovered = new LinkedList<>();
        discovered.add(cursor);
        int count = 0;
        while (!discovered.isEmpty()) {
            Node curr = discovered.poll();
            matrix[curr.x][curr.y] = -1; // marking as visited
            for (Node adj : findAdjacents(curr, matrix)) {
                discovered.add(adj);
            }
            count++;
        }

        return count;
    }

    public static List<Node> findAdjacents(final Node cursor, final int[][] matrix) {
        List<Node> result = new LinkedList<>();

        if (cursor.x - 1 >= 0) { //top
            if (matrix[cursor.x - 1][cursor.y] == 1) {
                result.add(new Node(cursor.x - 1, cursor.y));
            }
        }

        if (cursor.y - 1 >= 0) { // left
            if (matrix[cursor.x][cursor.y - 1] == 1) {
                result.add(new Node(cursor.x, cursor.y - 1));
            }
        }

        if (cursor.x + 1 < matrix[0].length) { // bottom
            if (matrix[cursor.x + 1][cursor.y] == 1) {
                result.add(new Node(cursor.x + 1, cursor.y));
            }
        }

        if (cursor.y + 1 < matrix.length) { // right
            if (matrix[cursor.x][cursor.y + 1] == 1) {
                result.add(new Node(cursor.x, cursor.y + 1));
            }
        }

        return result;

    }

    public static int findLargestComponentSimple(final int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    int temp = getComponentSize(i, j, matrix);
                    if (temp > max) {
                        max = temp;
                    }
                }
            }
        }
        return max;
    }

    public static int getComponentSize(final int row, final int col, final int[][] matrix) {
        if (matrix[row][col] == 1) {
            matrix[row][col] = -1;
            int size = 1;
            if (row - 1 >= 0) {
                size += getComponentSize(row - 1, col, matrix);
            }            
            if (row + 1 < matrix.length) {
                size += getComponentSize(row + 1, col, matrix);
            }            
            if (col - 1 >= 0) {
                size += getComponentSize(row, col - 1, matrix);
            }            
            if (col + 1 < matrix[row].length) {
                size += getComponentSize(row, col + 1, matrix);
            }            
            return size;
        }        
        return 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {0, 1, 0, 0},
            {1, 1, 0, 1},
            {0, 0, 0, 1},
            {1, 0, 1, 1}};
        
        System.out.println(findLargestComponent(matrix));
        
        matrix = new int[][] {
            {0, 1, 0, 0},
            {1, 1, 0, 1},
            {0, 0, 0, 1},
            {1, 0, 1, 1}};
        System.out.println(findLargestComponentSimple(matrix));
        
        matrix = new int[][]{
            {1, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 1},
            {1, 0, 1, 0}};

        System.out.println("--------------");
        System.out.println(findLargestComponent(matrix));
        
        matrix = new int[][]{
            {1, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 1},
            {1, 0, 1, 0}};
        System.out.println(findLargestComponentSimple(matrix));
    }
}
