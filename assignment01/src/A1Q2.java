import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class A1Q2 {
    public static void main(String[] args) {
        Supplier<IntStream> sup = () -> Arrays.stream(args)
                .mapToInt(Integer::parseInt);
        System.out.println(sup.get().max().getAsInt());
        System.out.println(sup.get().min().getAsInt());
        System.out.printf("%.2f", sup.get().average().getAsDouble());
    }
}
