import java.util.*;

/*
Test Date

9.27,6.61,3,7.18,209,144,247
3.4,4.0,4.5,4.5,100,100,100
-2.89,7.61,5.57,5.06
8.43,6.66
7.42,0.68,2.07,2.93,78,39,165
6.70,1.54,3.52,3.12
1.14,0.76,2.37,3.07,236,105,137
1.06,-0.80,3.03,2.66,132,120,273
3.01,-9.08,2.76,2.36,138,148,119
-0.51,-4.99,6.42,5.65
-4.36,8.70,3.03,2.63,16,138,221
9.40,2.41,6.47,4.90

 */
public class RectangleTest {

    public static void main(String[] args) {

        List<Rectangle> rects = readRectangles();

        Rectangle fistValidRect = null;

        for (Rectangle rect : rects) {
            if (rect.isValid()) {
                if (fistValidRect == null) {
                    fistValidRect = rect;
                } else if (!rect.intersect(fistValidRect)) {
                    System.out.println(rect);
                }
            }
        }

    }

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
