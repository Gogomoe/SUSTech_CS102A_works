import java.util.Scanner;

public class Chocolate {

    public static void main(String[] args) {
        System.out.print("Enter x: ");
        int[] arr = new int[]{
                12, 24, 8, 22, 15, 4, 8, 6,
                new Scanner(System.in).nextInt()
        };
        System.out.println();

        int times = 0;
        while (!allValuesAreSame(arr)) {
            times++;
            int last = 0;
            for (int j = 0; j < arr.length; j++) {
                int half = arr[j] / 2;
                arr[j] = half + last;
                last = half;
            }
            arr[0] += last; // the first child get chocolate from the last
        }

        System.out.printf("After looping %d times, each kid get %d chocolates.", times, arr[0]);
    }

    private static boolean allValuesAreSame(int[] arr) {
        for (int j = 1; j < arr.length; j++) {
            if (arr[0] != arr[j]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOdd(int i) {
        return (i & 1) == 1;
    }

}
