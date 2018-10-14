import java.util.Arrays;
import java.util.Scanner;

/**
 * refer to "poj 2456"
 */
public class A2Q6 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            seats();
        }
    }

    private static void seats() {
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        assert n <= 1000;

        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = scanner.nextInt();
        }
        Arrays.sort(seats);

        int minStep = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            minStep = Math.min(minStep, seats[i + 1] - seats[i]);
        }
        int maxStep = (seats[n - 1] - seats[0]) / (k - 1);

        int mid;
        int answer = 0;
        while (minStep <= maxStep) {
            mid = (minStep + maxStep) / 2;
            if (countPlaced(seats, mid) >= k) {
                answer = mid;
                minStep = mid + 1;
            } else {
                maxStep = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private static int countPlaced(int[] seats, int step) {
        int last = seats[0];
        int count = 1;
        for (int i = 1; i < seats.length; i++) {
            if (seats[i] - last >= step) {
                count++;
                last = seats[i];
            }
        }
        return count;
    }

}