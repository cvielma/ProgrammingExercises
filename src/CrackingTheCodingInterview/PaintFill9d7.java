/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PaintFill9d7 {

    public enum Color {

        RED, BLUE, YELLOW, GREEN, BLANK, BLACK;
    }

    public static class Point {

        int x, y;

        public Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Screen {

        Color[][] screen;

        public void setScreen(Color[][] screen) {
            this.screen = screen;
        }
        
        public Color getColorAt(final Point p) {
            return screen[p.x][p.y];
        }

        public List<Point> getAdjacents(final Point p) {
            List<Point> result = new LinkedList<>();
            if (p.x >= 0 && p.x < screen.length && p.y >= 0 && p.y < screen[0].length) {
                if (p.x - 1 >= 0) {
                    result.add(new Point(p.x - 1, p.y));
                }
                if (p.x + 1 < screen.length) {
                    result.add(new Point(p.x + 1, p.y));
                }
                if (p.y - 1 >= 0) {
                    result.add(new Point(p.x, p.y - 1));
                }
                if (p.y + 1 < screen[0].length) {
                    result.add(new Point(p.x, p.y + 1));
                }
            }
            return result;
        }

        public void setColorAt(final Point p, final Color color) {
            screen[p.x][p.y] = color;
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < screen.length; i++) {
                for (int j = 0; j < screen[0].length; j++) {
                    sb.append(screen[i][j]);
                    if (j != screen[0].length -1) {
                        sb.append(",");
                    }
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    public static Screen paintFill(final Screen screen, final Point p, final Color newColor) {
        return paintFill(screen, p, screen.getColorAt(p), newColor);

    }
    
    public static Screen paintFillIter(final Screen screen, final Point p, final Color newColor) {
        return paintFillIter(screen, p, screen.getColorAt(p), newColor);

    }

    public static Screen paintFill(final Screen screen, final Point p, final Color oldColor, final Color newColor) {
        screen.setColorAt(p, newColor);
        List<Point> adjacents = screen.getAdjacents(p);
        for (Point a : adjacents) {
            if (screen.getColorAt(a) == oldColor) {
                paintFill(screen, a, oldColor, newColor);
            }
        }
        return screen;
    }

    public static Screen paintFillIter(final Screen screen, final Point p, final Color oldColor, final Color newColor) {
        Queue<Point> discovered = new LinkedList<>();
        discovered.add(p);
        while (!discovered.isEmpty()) {
            Point temp = discovered.poll();
            if (screen.getColorAt(temp) == oldColor) {
                screen.setColorAt(temp, newColor);
                discovered.addAll(screen.getAdjacents(temp));
            }
        }
        return screen;
    }

    public static void main(String[] args) {
        Color[][] screenM = new Color[][] {
            {Color.BLANK, Color.BLANK, Color.GREEN, Color.BLANK},
            {Color.BLANK, Color.BLANK, Color.GREEN, Color.BLANK},
            {Color.GREEN, Color.BLANK, Color.BLANK, Color.BLANK},
            {Color.GREEN, Color.GREEN, Color.BLANK, Color.BLANK}};
        Screen screen = new Screen();
        screen.setScreen(screenM);
        
        System.out.println(screen);
        
        Point p = new Point(1, 2);
        System.out.println("----------");
        System.out.println(paintFill(screen, p, Color.BLUE));
        
        System.out.println("---------Iter---------");
        screenM = new Color[][] {
            {Color.BLANK, Color.BLANK, Color.GREEN, Color.BLANK},
            {Color.BLANK, Color.BLANK, Color.GREEN, Color.BLANK},
            {Color.GREEN, Color.BLANK, Color.BLANK, Color.BLANK},
            {Color.GREEN, Color.GREEN, Color.BLANK, Color.BLANK}};
        screen = new Screen();
        screen.setScreen(screenM);
        
        System.out.println(screen);
        
        p = new Point(1, 2);
        System.out.println("----------");
        System.out.println(paintFillIter(screen, p, Color.BLUE));
        
        
    
    }
}
