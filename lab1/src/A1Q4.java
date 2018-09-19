import java.util.Arrays;

import static java.lang.Math.pow;

public class A1Q4 {

    public static void main(String[] args) {
        double[] ds = Arrays.stream(args)
                .mapToDouble(Double::parseDouble)
                .toArray();
        System.out.println(pow(ds[0] - ds[3], 2) + pow(ds[1] - ds[4], 2) <= pow(ds[2], 2));
    }

}
