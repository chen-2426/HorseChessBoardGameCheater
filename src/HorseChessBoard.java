import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author chenxi
 */
public class HorseChessBoard {
    private static int X = 6;
    private static int Y = 6;
    private static int[][] board = new int[Y][X];
    private static boolean[] boardCheck = new boolean[X * Y];
    private static boolean finished = false;

    public static void main(String[] args) {
        int x = 5;
        int y = 5;
        isFinished(board, x - 1, y - 1, 1);
        for (int[] rows : board) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();

        }
    }

    public static void sort(ArrayList<Point> points) {
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return nextPoint(o1.x, o1.y).size() - nextPoint(o2.x, o2.y).size();
            }
        });
    }

    public static ArrayList<Point> nextPoint(int x, int y) {
        ArrayList<Point> points = new ArrayList<>();
        if ((x - 1) >= 0 && (y - 2) >= 0) {
            points.add(new Point(x - 1, y - 2));
        }
        if ((x - 2) >= 0 && (y - 1) >= 0) {
            points.add(new Point(x - 2, y - 1));
        }
        if ((x + 1) < X && (y + 2) < Y) {
            points.add(new Point(x + 1, y + 2));
        }
        if ((x + 2) < X && (y + 1) < Y) {
            points.add(new Point(x + 2, y + 1));
        }
        if ((x + 1) < X && (y - 2) >= 0) {
            points.add(new Point(x + 1, y - 2));
        }
        if ((x + 2) < X && (y - 1) >= 0) {
            points.add(new Point(x + 2, y - 1));
        }
        if ((x - 1) >= 0 && (y + 2) < Y) {
            points.add(new Point(x - 1, y + 2));
        }
        if ((x - 2) >= 0 && (y + 1) < Y) {
            points.add(new Point(x - 2, y + 1));
        }
        return points;
    }

    public static void isFinished(int[][] Board, int x, int y, int step) {
        ArrayList<Point> points = nextPoint(x, y);
        sort(points);
        Board[y][x] = step;
        boardCheck[y * X + x] = true;
        while (!points.isEmpty()) {
            Point remove = points.remove(0);
            if (!boardCheck[remove.x + remove.y * X]) {
                isFinished(Board, remove.x, remove.y, step + 1);
            }
        }
        if (step < X * Y && !finished) {
            Board[y][x] = 0;
            boardCheck[y * X + x] = false;
        } else {
            finished = true;
        }
    }
}
