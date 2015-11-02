/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/surrounded-regions/
 * @author cvielma
 */
public class Surrounded {

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        // Left and Right borders
        for (int i = 1; i < board.length - 1; i++) {
            if (board[i][0] == 'O') {
                checkSurrounded(board, i, 0);
            }

            if (board[i][board[i].length - 1] == 'O') {
                checkSurrounded(board, i, board[i].length - 1);
            }
        }
        
        // Top and Bottom borders
        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == 'O') {
                checkSurrounded(board, 0, j);
            }

            if (board[board.length - 1][j] == 'O') {
                checkSurrounded(board, board.length - 1, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'Z') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void checkSurrounded(char[][] board, int row, int col) {
        Queue<Integer> rows = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        rows.add(row);
        cols.add(col);
        board[row][col] = 'Z';
        while (!rows.isEmpty()) {
            int i = rows.poll();
            int j = cols.poll();
            

            if (j - 1 >= 0 && board[i][j -1] == 'O') { //left
                rows.add(i);
                cols.add(j - 1);
                board[i][j - 1] = 'Z';
            }

            if (j + 1 < board[i].length && board[i][j + 1] == 'O') { //right
                rows.add(i);
                cols.add(j + 1);
                board[i][j + 1] = 'Z';
            }
            
            if (i - 1 >= 0 && board[i - 1][j] == 'O') { //top
                rows.add(i - 1);
                cols.add(j);
                board[i - 1][j] = 'Z';
            }
            
            if (i + 1 < board.length && board[i + 1][j] == 'O') { //bottom
                rows.add(i + 1);
                cols.add(j);
                board[i + 1][j] = 'Z';
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'O', 'O', 'O'}, {'O', 'O', 'O'}, {'O', 'O', 'O'}};
        System.out.println(Arrays.deepToString(board));
        Surrounded sur = new Surrounded();
        sur.solve(board);
        System.out.println(Arrays.deepToString(board));
    }

}
