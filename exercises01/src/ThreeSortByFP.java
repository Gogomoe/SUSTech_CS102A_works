import java.util.Arrays;
import java.util.stream.Collectors;

public class ThreeSortByFP {

    public static void main(String[] args) {
        System.out.println(
                Arrays.stream(args)
                        .mapToInt(Integer::parseInt)
                        .sorted()
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }
}
