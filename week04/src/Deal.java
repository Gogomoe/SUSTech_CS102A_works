import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Deal {

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        Random rnd = new Random();

        String[] suits = new String[]{"\u2660", "\u2661", "\u2662", "\u2663"};
        String[] nums = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        Set<String> used = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            used.clear();

            while (used.size() < 5) {
                String suit = suits[rnd.nextInt(4)];
                String card = nums[rnd.nextInt(13)];

                boolean isAdd = used.add(suit + card);
                if (isAdd) {
                    System.out.println(suit + card);
                }
            }
            
            System.out.println();
        }
    }
}
