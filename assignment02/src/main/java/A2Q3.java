import java.util.Arrays;
import java.util.Scanner;

public class A2Q3 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            try {
                detect();
            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }
    }

    final private static int EXIT_LOOP = -1;

    private static void detect() {
        int n = scanner.nextInt();

        int[] state = new int[n];
        boolean[] haveRan = new boolean[n];

        for (int i = 0; i < n; i++) {
            state[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(haveRan, false);
            int j = i;
            while (state[j] != EXIT_LOOP) {
                if (haveRan[j]) {
                    System.out.println("true");
                    return;
                }
                haveRan[j] = true;
                j = state[j];
            }
        }
        System.out.println("false");
    }

}
