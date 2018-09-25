import java.util.Arrays;
import java.util.Scanner;

public class DistanceBetweenVectors {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double[] d1 = readVector(sc);
        double[] d2 = readVector(sc);

        double sum = 0;

        for (int i = 0; i < d1.length; i++) {
            sum += Math.pow(d1[i] - d2[i], 2);
        }

        System.out.println(Math.sqrt(sum));

    }

    private static double[] readVector(Scanner sc) {
        return Arrays.stream(sc.nextLine().split(" "))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

}
