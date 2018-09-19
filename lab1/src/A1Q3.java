import static java.lang.Math.pow;

public class A1Q3 {

    public static void main(String[] args) {
        double l = Double.parseDouble(args[0]);
        System.out.printf("%.2f\n", 0.433 * pow(l, 2));
        System.out.printf("%.2f\n", 1.00 * pow(l, 2));
        System.out.printf("%.2f\n", 1.72 * pow(l, 2));
        System.out.printf("%.2f\n", 2.598 * pow(l, 2));
    }

}
