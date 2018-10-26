import java.util.Scanner;

public class Q6 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            check();
        }
    }

    private static void check() {
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        final int y = scanner.nextInt();
        final int x = scanner.nextInt();

        Point p = new Point(x, y);
        Direction go = Direction.R;
        int count = m * n;
        while (count != 0) {
            if (p.x >= 0 && p.x < n && p.y >= 0 && p.y < m) {
                System.out.print(matrix[p.y][p.x] + " ");
                count--;
            }
            p.add(go);
            int rx = p.x - x;
            int ry = p.y - y;
            if (rx == ry || (rx < 0 && rx == -ry) || (rx > 0 && ry == -rx + 1)) {
                go = go.turnRight();
            }
        }
        System.out.println();
    }

    enum Direction {
        U(0, -1), R(1, 0), D(0, 1), L(-1, 0);
        int x;
        int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Direction turnRight() {
            switch (this) {
                case U:
                    return R;
                case R:
                    return D;
                case D:
                    return L;
                case L:
                    return U;
            }
            throw new RuntimeException("This case is not exist");
        }
    }

    static class Point {

        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void add(Direction other) {
            x += other.x;
            y += other.y;
        }
    }


}
