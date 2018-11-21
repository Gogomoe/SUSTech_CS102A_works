import java.util.ArrayList;
import java.util.Comparator;

public class MaximumFinder {

    public static String maximum(ArrayList<String> a, Comparator<String> c) {
//        return Collections.max(a, c);
        if (a == null || a.isEmpty()) {
            return null;
        }
        String max = a.get(0);
        for (String s : a) {
            if (c.compare(s, max) > 0) {
                max = s;
            }
        }
        return max;
    }

    public static Object maximum(Object[] a, Measurer m) {
        if (a == null || a.length == 0) {
            return null;
        }
        Object max = a[0];
        double maxv = m.measure(max);

        for (Object o : a) {
            if (m.measure(o) > maxv) {
                maxv = m.measure(o);
                max = o;
            }
        }
        return max;
    }

    public static void testMaximumString() {
        ArrayList<String> list = new ArrayList<>();
        list.add("foo");
        list.add("bar");
        list.add("baz");
        list.add("qux");
        list.add("quux");
        list.add("corge");
        list.add("uier");

        String str = maximum(list, Comparator.comparingInt(String::length));
        System.out.println("maximum is " + str);
    }

    public static void testMaximumRectangle() {
        Rectangle[] arr = {
                new Rectangle(0, 0, 5, 8),
                new Rectangle(0, 0, 2, 5),
                new Rectangle(0, 0, 1, 9),
                new Rectangle(0, 0, 6, 7),
                new Rectangle(0, 0, 2, 18)
        };

        Rectangle rect = (Rectangle) maximum(arr, o -> {
            Rectangle r = (Rectangle) o;
            return r.getWidth() * r.getHeight();
        });

        System.out.println("maximum is " + rect);
    }

}
