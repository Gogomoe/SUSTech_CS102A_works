import static java.lang.Math.abs;

public class PrintDiamond {

    public static void main(String[] args) {
        for (int i = -9; i <= 9; i++) {
            for (int j = -9; j <= 9; j++) {
                if (abs(i) + abs(j) > 9) {
                    System.out.print("  ");
                } else {
                    System.out.printf("%d ", abs(j));
                }
            }
            System.out.println();
        }
    }
}
