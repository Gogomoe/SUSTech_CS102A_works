import java.text.MessageFormat;
import java.util.Arrays;

public class UniformRandomNumbers {

    public static void main(String[] args) {

        double[] nums = new double[5];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.random();
        }

        double min = Arrays.stream(nums).min().getAsDouble();
        double max = Arrays.stream(nums).max().getAsDouble();
        double average = Arrays.stream(nums).average().getAsDouble();

        System.out.println(MessageFormat.format("average : {0}", average));
        System.out.println(MessageFormat.format("minimum : {0}", min));
        System.out.println(MessageFormat.format("maximum : {0}", max));
    }

}
