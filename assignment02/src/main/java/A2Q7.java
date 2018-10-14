import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class A2Q7 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            count();
        }
    }

    private static void count() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int s = scanner.nextInt();
        Deque<Integer> deque = new ArrayDeque<Integer>();
        Deque<Integer> wash = new ArrayDeque<Integer>();
        for (int i = 0; i < s; i++) {
            deque.add(scanner.nextInt());
        }
        for (int i = 0; i < n; i++) {
            int todo = scanner.nextInt();
            int wears = -1;
            for (int j = 0; j < todo; j++) {
                switch (scanner.next()) {
                    case "wash":
                        wash.add(deque.pollLast());
                        break;
                    case "buy":
                        deque.add(scanner.nextInt());
                        break;
                    case "wear":
                        wears = deque.pollLast();
                        System.out.println(wears + " " + deque.size());
                        break;
                }
            }
            wash.add(wears);
            deque.addAll(wash);
            wash.clear();
        }
    }

}
