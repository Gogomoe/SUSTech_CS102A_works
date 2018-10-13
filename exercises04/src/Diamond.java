public class Diamond {

    public static String diamond() {
        return diamond(8);
    }

    public static String diamond(int n) {
        return diamond(n, '*');
    }

    public static String diamond(int n, char flash) {
        return diamond(n, flash, ' ');
    }

    public static String diamond(int n, char flash, char blank) {
        StringBuilder sb = new StringBuilder();
        int height = 2 * n - 1;
        for (int y = 0; y < height; y++) {
            int width = height - Math.abs(y - n + 1);
            for (int x = 0; x < width; x++) {
                if (y < n - 1) {
                    if (x == n - 1 || x == width - 1 || x + y == n - 1) {
                        sb.append(flash);
                    } else {
                        sb.append(blank);
                    }
                } else if (y == n - 1) {
                    sb.append(flash);
                } else {
                    int start = y - n + 1;
                    if (x >= start && (x != n - 1 || y == height - 1)) {
                        sb.append(flash);
                    } else {
                        sb.append(blank);
                    }
                }

            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
