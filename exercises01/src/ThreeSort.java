public class ThreeSort {

    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        int max1 = Math.max(a, b);
        int min1 = Math.min(a, b);

        int max = Math.max(max1, c);
        int min2 = Math.min(max1, c);

        int middle = Math.max(min1, min2);
        int min = Math.min(min1, min2);

        System.out.println(max + " " + middle + " " + min);
    }
}
