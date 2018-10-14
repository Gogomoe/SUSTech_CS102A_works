import java.util.Scanner;

public class A2Q4 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            findMax();
        }
    }

    private static void findMax() {
        int n = scanner.nextInt();
        long maxSum = 0;
        long currSum = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int it = scanner.nextInt();
            currSum += it;
            maxSum = Math.max(maxSum, currSum);
            maxValue = Math.max(maxValue, it);
            if (currSum < 0) {
                currSum = 0;
            }
        }
        if (maxSum == 0) {
            maxSum = maxValue;
        }
        System.out.println(maxSum);
    }

}
