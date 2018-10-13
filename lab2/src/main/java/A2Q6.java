import java.util.Arrays;
import java.util.Scanner;

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

        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = scanner.nextInt();
        }
        Arrays.sort(seats);

        int dis = n - k + 1;
        int maxDistance = 0;
        for (int i = 0; i < n - dis; i++) {
            maxDistance = Math.max(maxDistance, seats[i + dis] - seats[i]);
        }
        System.out.println(maxDistance);
    }


}
