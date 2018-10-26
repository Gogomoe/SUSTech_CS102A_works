import java.util.Scanner;

public class Q3 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            centralize();
        }
    }

    private static void centralize() {
        int rows = scanner.nextInt();
        int max = Integer.MIN_VALUE;
        int[] columns = new int[rows];
        for (int i = 0; i < rows; i++) {
            columns[i] = scanner.nextInt();
            max = Math.max(columns[i], max);
        }
        for (int i = 0; i < rows; i++) {
            int space = (max - columns[i]) / 2;
            for (int j = 0; j < space; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j < columns[i]; j++) {
                System.out.print(scanner.nextInt() + " ");
            }
            System.out.println();
        }
    }
}
