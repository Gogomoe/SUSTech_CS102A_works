public class MedianOf5 {

    public static void main(String[] args) {

        int[] a = {
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1]),
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3]),
                Integer.parseInt(args[4])
        };

        int[] b = new int[3];
        int ib = 0;

        if (product(a[0], a[1], a[2], a[3], a[4]) > 0) {
            b[ib] = a[0];
            ib++;
        }
        if (product(a[1], a[0], a[2], a[3], a[4]) > 0) {
            b[ib] = a[1];
            ib++;
        }
        if (product(a[2], a[0], a[1], a[3], a[4]) > 0) {
            b[ib] = a[2];
            ib++;
        }
        if (product(a[3], a[0], a[1], a[2], a[4]) > 0) {
            b[ib] = a[3];
            ib++;
        }
        if (product(a[4], a[0], a[1], a[2], a[3]) > 0) {
            b[ib] = a[4];
        }

        if ((b[1] - b[0]) * (b[2] - b[0]) < 0) {
            System.out.println(b[0]);
        } else if ((b[0] - b[1]) * (b[2] - b[1]) < 0) {
            System.out.println(b[1]);
        } else {
            System.out.println(b[2]);
        }
    }

    private static int product(int base, int a0, int a1, int a2, int a3) {
        return (a0 - base) * (a1 - base) * (a2 - base) * (a3 - base);
    }
}
