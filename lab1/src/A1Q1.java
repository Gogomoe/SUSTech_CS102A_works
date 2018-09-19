import static java.lang.Math.PI;

public class A1Q1 {

    public static void main(String[] args) {
        int r = Integer.parseInt(args[0]);
        double p = r * 2 * PI;
        double s = r * r * PI;
        System.out.printf("%.2f\n", p);
        System.out.printf("%.2f\n", s);
    }

}
