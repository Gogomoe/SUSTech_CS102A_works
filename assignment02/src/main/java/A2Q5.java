import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A2Q5 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            summation();
        }
    }

    private static void summation() {
        int n = scanner.nextInt();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(scanner.nextInt());
        }

        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            switch (scanner.next()) {
                case "I":
                    arr.add(scanner.nextInt(), scanner.nextInt());
                    break;
                case "D":
                    arr.remove((Integer) scanner.nextInt());
                    break;
                case "S":
                    int sum = arr.subList(scanner.nextInt(), scanner.nextInt() + 1).stream()
                            .mapToInt(Integer::intValue)
                            .sum();
                    System.out.println(sum);
                    break;
            }
        }
    }


}
