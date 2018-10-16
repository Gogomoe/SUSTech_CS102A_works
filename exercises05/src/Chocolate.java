import java.util.Scanner;

public class Chocolate {

    public static void main(String[] args) {
        int[] arr = new int[]{
                12, 24, 8, 22, 15, 4, 8, 6,
                new Scanner(System.in).nextInt()
        };
        System.out.println("Enter x: " + arr[8]);
        int i = 0;
        Outer:
        while (true) {
            i++;
            int last = 0;
            for (int j = 0; j < arr.length; j++) {
                if (isOdd(arr[j])) {
                    arr[j]--;
                }
                int toGive = arr[j] / 2;
                arr[j] = toGive + last;
                last = toGive;
            }
            arr[0] += last;
            for (int j = 1; j < arr.length; j++) {
                if (arr[0] != arr[j]) {
                    continue Outer;
                }
            }
            break;
        }
        System.out.printf("After looping %d times, each kid get %d chocolates.", i, arr[0]);
    }

    private static boolean isOdd(int i) {
        return (i & 1) == 1;
    }

}
