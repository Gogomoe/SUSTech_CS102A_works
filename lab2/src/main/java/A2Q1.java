import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class A2Q1 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            sort();
        }
    }

    static class Pair implements Comparable {
        int value;
        int index;

        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Object o) {
            return value - ((Pair) o).value;
        }
    }

    private static void sort() {
        int n = scanner.nextInt();
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Pair(scanner.nextInt(), i));
        }
        Collections.sort(list);
        String answer = list.stream()
                .map(p -> p.index)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(answer);
    }

}
