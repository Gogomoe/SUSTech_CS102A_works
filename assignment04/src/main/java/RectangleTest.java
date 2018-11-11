import org.jetbrains.annotations.NotNull;

import java.util.*;

/*
Test Date

-10,-10,1,2
0,0,3,3,100,100,100
0,1,2,5,130,22,444
5,5,-3,-5
5,5,2,2,30,100,250
-3,5,5,10,40,20,330
2,3

TODO: 这里测试样例和答案好像有问题
 */
public class RectangleTest {

    public static void main(String[] args) {

        List<Rectangle> rects = readRectangles();

        Rectangle fistValidRect = null;

        for (Rectangle rect : rects) {
            if (rect.isValid()) {
                if (fistValidRect == null) {
                    fistValidRect = rect;
                    System.out.println(rect);
                } else if (!rect.intersect(fistValidRect)) {
                    System.out.println(rect);
                }
            }
        }

    }

    @NotNull
    public static List<Rectangle> readRectangles() {
        System.out.println("Please press Ctrl + D to end input");

        Scanner scanner = new Scanner(System.in);
        List<Rectangle> rects = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            Rectangle rect = parseRectangle(line, parts);
            rects.add(rect);
        }
        return rects;
    }

    @NotNull
    private static Rectangle parseRectangle(String line, String[] parts) {
        if (parts.length == 2) {
            double[] position = getPosition(parts);

            return new Rectangle(position[0], position[1]);

        } else if (parts.length == 4) {
            double[] position = getPosition(parts);
            double[] size = getSize(parts);

            return new Rectangle(position[0], position[1], size[0], size[1]);

        } else if (parts.length == 7) {
            double[] position = getPosition(parts);
            double[] size = getSize(parts);
            int[] color = getColor(parts);

            return new Rectangle(position[0], position[1], size[0], size[1],
                    color[0], color[1], color[2]);

        } else {
            throw new InputMismatchException("input data require 2,4,7 arguments per line: " + line);
        }
    }

    private static double[] getSize(String[] parts) {
        return splitPartsAsDouble(parts, 2, 4);
    }

    private static double[] getPosition(String[] parts) {
        return splitPartsAsDouble(parts, 0, 2);
    }

    private static int[] getColor(String[] parts) {
        return splitPartsAsInt(parts, 4, 7);
    }

    private static double[] splitPartsAsDouble(String[] parts, int from, int to) {
        return Arrays.stream(Arrays.copyOfRange(parts, from, to))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    private static int[] splitPartsAsInt(String[] parts, int from, int to) {
        return Arrays.stream(Arrays.copyOfRange(parts, from, to))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
