import java.util.Scanner;

public class P1000 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            find();
        }
    }

    private static void find() {
        int n = scanner.nextInt();
        int sum = 0;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
            sum += arr[i];
        }
        float average = (float) sum / n;
        int count = 0;
        int left = 0;
        int right = n - 1;
        while ((arr[left] + arr[right]) / 2f <= average) {
            left++;
        }
        while (left < right) {
            while ((arr[left] + arr[right]) / 2f > average && left < right) {
                right--;
            }
            count += n - right - 1;
            left++;
        }
        while (left < n) {
            count += n - left - 1;
            left++;
        }
        System.out.println(count);
    }

}
