import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterString {

    public static String[] filter(String[] a, Filter f) {
        List<String> list = new ArrayList<>();
        for (String s : a) {
            if (f.accept(s)) {
                list.add(s);
            }
        }
        return list.toArray(String[]::new);
    }

    public static void testFilterString() {
        String[] arr = {
                "foo", "bar", "baz",
                "qux", "quux", "corge", "uier"
        };

        String[] res = filter(arr, it -> it.length() <= 3);
        System.out.println(Arrays.toString(res));
    }

}
