import static java.lang.Math.pow;

public class A1Q6 {

    public static void main(String[] args) {
        double rate = (double) Integer.parseInt(args[0]) / 100;
        double d = 0.9972;
        int i = 0;
        while (true) {
            if (pow(d, i) <= 1 - rate) {
                break;
            }
            i++;
        }
        System.out.printf("%dy%dm", i / 12, i % 12);
    }
}
