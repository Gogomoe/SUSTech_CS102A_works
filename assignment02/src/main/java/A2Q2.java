import java.util.Scanner;

public class A2Q2 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            product();
        }
    }

    private static void product() {
        int n = scanner.nextInt();
        long product = 1;
        for (int i = 0; i < n; i++) {
            long index = scanner.nextLong();
            if (isOdd(index)) {
                product *= value(index);
            }
        }
        System.out.println(product);
    }

    private static boolean isOdd(long index) {
        return (index & 1) == 1;
    }

    private static int[] series = new int[]{9, 3, 5, 7, 2, 9, 3, 5, 7, 2};

    private static int value(long index) {
        return series[(int) ((index - 1) % 10)];
    }

}
