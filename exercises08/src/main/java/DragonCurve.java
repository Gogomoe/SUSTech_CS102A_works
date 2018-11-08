import static java.lang.Math.*;

public class DragonCurve {
    public static void main(String[] args) {

        StdDraw.enableDoubleBuffering();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                    StdDraw.show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        int n = Integer.parseInt(args[0]);
        StdDraw.setCanvasSize(862, 862);     // default 512 X 512                                 )
        double step = n < 4 ? .2 : 4.0 / (n * n * n); // heuristic ??? :-)
        StdDraw.setPenRadius(step / 50);
        drawCurve(.5, .5, .0, step, dragonCurveOperations(n));
        System.out.printf("Enjoy the Dragon Curve %d!\n", n);
    }

    public static void drawCurve(double x, double y, double degree, double step, String ops) {
        double xFrom = x, yFrom = y, angle = degree;
        for (int i = 0; i < ops.length(); i++) {
            switch (ops.charAt(i)) {
                case 'F':
                    double xTo = xFrom + step * cos(toRadians(angle));
                    double yTo = yFrom + step * sin(toRadians(angle));
                    StdDraw.line(xFrom, yFrom, xTo, yTo);
                    xFrom = xTo;
                    yFrom = yTo;
                    break;
                case 'L':
                    angle = (angle + 270) % 360;
                    break;
                case 'R':
                    angle = (angle + 90) % 360;
                    break;
            }
        }
    }

    public static String dragonCurveOperations(int n) {
        String d = "F", r = "F";
        for (int i = 1; i <= n; i++) {
            String d_ = d, r_ = r;
            d = d_ + "L" + r_;
            r = d_ + "R" + r_;
        }
        return d;
    }
}
