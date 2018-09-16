public class PrintFivePerLine {

    public static void main(String[] args) {
        for (int i = 1000; i <= 2000; i++) {
            if (i % 5 == 4) {
                System.out.println(i);
            } else {
                System.out.print(i + " ");
            }
        }

        int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++) {
            System.out.println(f);
            f = f + g;
            g = f - g;
        }
        System.out.println("g = " + g);
        System.out.println("f = " + f);
    }

}
