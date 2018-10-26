import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Q1 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            find();
        }
    }

    private static void find() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean flag = false;
        Set[] columns = new Set[m];
        for (int i = 0; i < m; i++) {
            columns[i] = new HashSet<Integer>(n);
        }
        Set<Integer> row = new HashSet<>(m);
        for (int i = 0; i < n; i++) {
            row.clear();
            for (int j = 0; j < m; j++) {
                int it = scanner.nextInt();
                if (!row.add(it) || !columns[j].add(it)) {
                    flag = true;
                }
            }
        }
        System.out.println(flag);
    }
}
