import java.util.Scanner;

public class Q4 {

    private static Scanner scanner = new Scanner(System.in);

    private static int[][] triangle = new int[34][];

    public static void main(String[] args) {
        triangle[1] = new int[]{1};
        for (int i = 2; i <= 33; i++) {
            triangle[i] = new int[i];
            triangle[i][0] = 1;
            for (int j = 1; j < i - 1; j++) {
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
            }
            triangle[i][i - 1] = 1;
        }

        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            printTriangle();
        }
    }

    private static void printTriangle() {
        int row = scanner.nextInt();
        for (int i = 0; i < triangle[row].length; i++) {
            System.out.print(triangle[row][i] + " ");
        }
        System.out.println();
    }


}
