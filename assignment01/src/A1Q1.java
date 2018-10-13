import static java.lang.Math.PI;

public class A1Q1 {

    public static void main(String[] args) {
        int r = Integer.parseInt(args[0]);
        double s = r * r * PI;
        double p = r * 2 * PI;
        System.out.printf("%.2f\n", s);
        System.out.printf("%.2f\n", p);
    }

}
