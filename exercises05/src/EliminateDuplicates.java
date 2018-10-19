import java.util.*;
import java.util.stream.Collectors;

public class EliminateDuplicates {

    public static int[] eliminateDuplicates(int[] list) {
        Set<Integer> set = new HashSet<>();
        List<Integer> newList = new ArrayList<>();
        for (int aList : list) {
            if (!set.contains(aList)) {
                set.add(aList);
                newList.add(aList);
            }
        }
        return newList.stream().mapToInt(it -> it).toArray();
    }

    private static void test() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ten numbers: ");
        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        String result = Arrays.stream(eliminateDuplicates(array))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("the distinct numbers are: " + result);
    }

}
