import static java.lang.Math.*;

public class A1Q3 {

    public static void main(String[] args) {
        double l = Double.parseDouble(args[0]);
        System.out.printf("%.2f\n", 3 / (4 * tan(toRadians(180.0 / 3))) * pow(l, 2));
        System.out.printf("%.2f\n", 4 / (4 * tan(toRadians(180.0 / 4))) * pow(l, 2));
        System.out.printf("%.2f\n", 5 / (4 * tan(toRadians(180.0 / 5))) * pow(l, 2));
        System.out.printf("%.2f\n", 6 / (4 * tan(toRadians(180.0 / 6))) *pow(l, 2));
    }

}
