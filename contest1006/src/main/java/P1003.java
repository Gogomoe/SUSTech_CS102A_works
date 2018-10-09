import java.util.Scanner;

public class P1003 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.println(scanner.nextInt() + scanner.nextInt());
        }
    }
}
