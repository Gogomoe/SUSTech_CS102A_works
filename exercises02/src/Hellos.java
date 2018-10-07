import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class Hellos {

    public static void main(String[] args) {
        for (int i = 1; i <= Integer.parseInt(args[0]); i++) {
            Map<Integer, String> map = new HashMap();
            map.put(1, "st");
            map.put(2, "nd");
            map.put(3, "rd");

            String postfix = map.getOrDefault(i % 10, "th");
            if (i > 10 && i < 20) {
                postfix = "th";
            }
            System.out.println(MessageFormat.format("{0}{1} hello", i, postfix));


        }
    }

}
