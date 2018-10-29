import java.util.ArrayList;
import java.util.Scanner;

public class P1001 {

    private static int[] fib = new int[10];

    public static void main(String[] args) {
        for (int i = 0; i < fib.length; i++) {
            fib[i] = fib(i);
        }
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            decompose(scanner.nextInt());
        }
    }

    private static void decompose(int num) {
        if (num < 1 || num > 100) {
            System.out.println("Invalid Number");
            return;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 9; i >= 0; i--) {
            if (fib[i] <= num) {
                list.add(fib[i]);
                num -= fib[i];
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    private static int fib(int i) {
        if (i == 0) {
            return 1;
        } else if (i == 1) {
            return 2;
        }
        return fib(i - 1) + fib(i - 2);
    }

}
