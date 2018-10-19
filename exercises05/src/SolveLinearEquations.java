import java.util.Scanner;

public class SolveLinearEquations {

    public static double[] linearEquation(double[][] a, double[] b) {
        double dividend = a[0][0] * a[1][1] - a[1][0] * a[0][1];
        if (dividend == 0) {
            return null;
        }
        return new double[]{
                (b[0] * a[1][1] - b[1] * a[0][1]) / dividend,
                (b[1] * a[0][0] - b[0] * a[1][0]) / dividend
        };
    }

    public static void test() {
        double[][] a = new double[2][2];
        double[] b = new double[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a00, a01, a10, a11, b0, b1: ");
        a[0][0] = scanner.nextDouble();
        a[0][1] = scanner.nextDouble();
        a[1][0] = scanner.nextDouble();
        a[1][1] = scanner.nextDouble();
        b[0] = scanner.nextDouble();
        b[1] = scanner.nextDouble();

        double[] res = linearEquation(a, b);
        if (res == null) {
            System.out.println("The equation has no solution");
        } else {
            System.out.printf("x is %f and y is %f", res[0], res[1]);
        }
    }

}
