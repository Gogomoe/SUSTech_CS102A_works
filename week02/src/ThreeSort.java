import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThreeSort {

    public static void main(String[] args) {
        int[] ints = Arrays.stream(args)
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(ints);
        List<String> strings = Arrays.stream(ints)
                .mapToObj(Integer::toString)
                .collect(Collectors.toList());

        System.out.println(String.join(" ", strings));
    }
}
