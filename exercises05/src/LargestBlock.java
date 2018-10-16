import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
        Set<Integer> set = new HashSet<>();
        Outer:
        while (true) {
            for (int i = y; i < y + size; i++) {
                for (int j = x; j < x + size; j++) {
                    if (!(i < matrix.length && j < matrix.length)) {
                        break Outer;
                    }
                    set.add(matrix[i][j]);
                }
            }
            if (set.size() != 1) {
                break;
            }
            size++;
        }
        return size - 1;
    }
}
