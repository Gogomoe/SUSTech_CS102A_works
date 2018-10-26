import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Q5 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            check();
        }
    }

    private static void check() {
        boolean flag = true;
        Set[] columns = new Set[9];
        Set[] squares = new Set[9];
        for (int i = 0; i < 9; i++) {
            columns[i] = new HashSet<Integer>(9);
            squares[i] = new HashSet<Integer>(9);
        }
        Set<Integer> row = new HashSet<>(9);
        for (int i = 0; i < 9; i++) {
            row.clear();
            for (int j = 0; j < 9; j++) {
                int it = scanner.nextInt();
                Set<Integer> square = squares[(i / 3) * 3 + j / 3];
                if (!row.add(it) || !columns[j].add(it) || !square.add(it)) {
                    flag = false;
                }
            }
        }
        String answer = flag ? "YES" : "NO";
        System.out.println(answer);
    }


}
