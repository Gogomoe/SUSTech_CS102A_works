import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q2 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
    }

    private static void process() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List[] rows = new List[n];
        for (int i = 0; i < n; i++) {
            int size = scanner.nextInt();
            rows[i] = new ArrayList<Integer>(size);
            for (int j = 0; j < size; j++) {
                rows[i].add(scanner.nextInt());
            }
        }
        for (int i = 0; i < m; i++) {
            int k = scanner.nextInt();
            int r = scanner.nextInt();
            List<Integer> list = ((List<Integer>) rows[r]);
            if (k == 0) {
                list.removeIf(it -> it == 0);
            } else {
                for (int j = 0; j < list.size(); j++) {
                    list.set(j, list.get(j) + k);
                }
            }
        }
        for (List row : rows) {
            for (Object o : row) {
                System.out.print(o + " ");
            }
            System.out.println();
        }
    }
}
