import java.util.Scanner;

public class LargestBlock {

    public static void main(String[] args) {
        System.out.print("Enter the number of rows in the matrix: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("Enter the matrix row by row: ");
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int maxSize = 0;
        int y = 0;
        int x = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                int size = countSubmatrix(matrix, i, j);
                if (size > maxSize) {
                    y = i;
                    x = j;
                    maxSize = size;
                }
            }
        }
        System.out.printf("The maximum square submatrix is at (%d, %d) with size %d", x, y, maxSize);

    }

    private static int countSubmatrix(int[][] matrix, int y, int x) {
        int size = 2;
        Outer:
        while (true) {
            int nx = x + size - 1;
            int ny = y + size - 1;
            if (!(nx < matrix.length && ny < matrix.length)) {
                break;
            }
            for (int i = x; i <= nx; i++) {
                if (matrix[ny][i] == 0) {
                    break Outer;
                }
            }
            for (int i = y; i <= ny; i++) {
                if (matrix[i][nx] == 0) {
                    break Outer;
                }
            }

            size++;
        }
        return size - 1;
    }
}
