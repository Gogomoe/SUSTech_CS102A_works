import java.util.Arrays;
import java.util.Scanner;

public class ReverseArray {

    public static void main(String[] args) {
        String[] strs = new Scanner(System.in).nextLine().split(" ");
        int len = strs.length;
        for (int i = 0; i < strs.length / 2; i++) {
            String tmp = strs[i];
            strs[i] = strs[len - i - 1];
            strs[len - i - 1] = tmp;
        }
        System.out.println(Arrays.toString(strs));
    }
}
