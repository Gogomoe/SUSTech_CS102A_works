import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

/*
  TODO 或许提交的时候需要删掉 NotNull
 */

public class Rectangle {

    private double x;
    private double y;
    private double width;
    private double height;

    private int red;
    private int green;
    private int blue;

    public Rectangle(double x, double y) {
        this(x, y, 1, 1);
    }

    public Rectangle(double x, double y, double width, double height) {
        this(x, y, width, height, 100, 100, 200);
    }

    /**
     * The {@code red} {@code green} and {@code blue} will be ranged in [0, 255]
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param red
     * @param green
     * @param blue
     */
    public Rectangle(double x, double y, double width, double height, int red, int green, int blue) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    /**
     * Return {@code true} if this Rectangle intersect with another
     *
     * @param other
     * @return {@code true} if this {@code Rectangle} intersect with another
     */
    public boolean intersect(@NotNull Rectangle other) {
        /*
         * TODO: 边界重合算不算相交？
         */
        double left1 = x - width / 2;
        double right1 = x + width / 2;
        double upper1 = y + height / 2;
        double lower1 = y - height / 2;

        double left2 = other.x - other.width / 2;
        double right2 = other.x + other.width / 2;
        double upper2 = other.y + other.height / 2;
        double lower2 = other.y - other.height / 2;

        return (!(right1 < left2) && !(left1 > right2)) &&
                (!(upper1 < lower2) && !(lower1 > upper2));

//        Equivalent method
//
//        if ((right1 < left2 || left1 > right2) ||
//                (upper1 < lower2 || lower1 > upper2)) {
//            return false;
//        }
//        return true;
    }

    /**
     * Return {@code true} if two {@code Rectangle}s intersect with the other
     *
     * @param r1
     * @param r2
     * @return {@code true} if two {@code Rectangle}s intersect with the other
     */
    public static boolean intersect(@NotNull Rectangle r1, @NotNull Rectangle r2) {
        return r1.intersect(r2);
    }

    /**
     * Return {@code true} if this {@code Rectangle} is valid.
     *
     * <p>
     * Valid {@code Rectangle} means {@code x} {@code y} are in (-10,10)
     * and {@code width} {@code height} are in [0,5]
     * </p>
     *
     * @return {@code true} if this {@code Rectangle} is valid.
     */
    public boolean isValid() {
        return (0 <= width && width <= 5) && (0 <= height && height <= 5) &&
                (-10 < x && x < 10) && (-10 < y && y < 10);
    }

    /**
     * Return {@code true} if this whole {@code Rectangle} can be drawn in the screen (-10,10).
     *
     * @return {@code true} if this whole {@code Rectangle} can be drawn in the screen (-10,10).
     */
    public boolean isInBoundary() {
        double left = x - width / 2;
        double right = x + width / 2;
        double upper = y + height / 2;
        double lower = y - height / 2;
        return (-10 < left && right < 10) && (-10 < lower && upper < 10);
    }

    /**
     * Draw this {@code Rectangle} on screen if this {@link #isValid() is valid}.
     */
    public void draw() {
        if (!isValid()) {
            return;
        }
        StdDraw.setPenColor(new Color(red, green, blue));
        StdDraw.filledRectangle(x, y, width / 2, height / 2);

    }

    @Override
    public String toString() {
        return "Centre[" +
                "" + x +
                "," + y +
                "] Shape[" + width +
                "," + height +
                "] Color[" + red +
                "," + green +
                "," + blue +
                "]";
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getRed() {
        return red;
    }

    /**
     * The {@code red} {@code green} and {@code blue} will be ranged in [0, 255]
     *
     * @param red
     */
    public void setRed(int red) {
        this.red = colorBounds(red);
    }

    public int getGreen() {
        return green;
    }

    /**
     * The {@code red} {@code green} and {@code blue} will be ranged in [0, 255]
     *
     * @param green
     */
    public void setGreen(int green) {
        this.green = colorBounds(green);
    }

    public int getBlue() {
        return blue;
    }

    /**
     * The {@code red} {@code green} and {@code blue} will be ranged in [0, 255]
     *
     * @param blue
     */
    public void setBlue(int blue) {
        this.blue = colorBounds(blue);
    }

    private static int colorBounds(int color) {
        return max(0, min(255, color));
    }

}
