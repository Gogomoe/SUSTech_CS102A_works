public class MyMath {

    public static int max3(int a1, int a2, int a3) {
        return Math.max(Math.max(a1, a2), a3);
    }

    public static boolean odd(boolean b1, boolean b2, boolean b3) {
        return (b1 && b2 && b3) ||
                (b1 && !b2 && !b3) ||
                (!b1 && b2 && !b3) ||
                (!b1 && !b2 && b3);
    }

    public static boolean majority(boolean b1, boolean b2, boolean b3) {
        return (b1 && b2) || (b2 && b3) || (b1 && b3);
    }

    public static boolean eq(int[] a1, int[] a2) {
        if (a1 == a2) {
            return true;
        }
        if (a1 == null || a2 == null || a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean areTriangular(double a, double b, double c) {
        return !(a + b >= c) && !(a + c >= b) && !(b + c >= a);
    }

    public static double sqrt(double n) {
        double k = 1.0;
        while (Math.abs(k * k - n) > 1e-9) {
            k = (k + n / k) / 2;
        }
        return k;
    }

    public static double lg(double n) {
        return Math.log(n) / Math.log(2);
    }

    public static int lg(int n) {
        int i = 0;
        while (n > 1) {
            n /= 2;
            i++;
        }
        return i;
    }

    public static int signum(int n) {
        return Integer.compare(n, 0);
    }

}
