import java.util.*;
import java.util.stream.Collectors;

public class DragonCurve {

    private static Map<String, String> reverseCharMap = new HashMap<>();

    static {
        reverseCharMap.put("L", "R");
        reverseCharMap.put("R", "L");
    }

    private List<String> turns = new ArrayList<>();

    private DragonCurve(List<String> previous) {
        List<String> reverse = new ArrayList<>(previous);
        Collections.reverse(reverse);
        reverse = reverse.stream()
                .map(reverseCharMap::get)
                .collect(Collectors.toList());

        turns.addAll(previous);
        turns.add("L");
        turns.addAll(reverse);
    }

    @Override
    public String toString() {
        return "F" + turns.stream()
                .map(it -> it + "F")
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        DragonCurve d1 = new DragonCurve(new ArrayList<>());
        DragonCurve d2 = new DragonCurve(d1.turns);
        DragonCurve d3 = new DragonCurve(d2.turns);
        DragonCurve d4 = new DragonCurve(d3.turns);
        DragonCurve d5 = new DragonCurve(d4.turns);

        System.out.println("F");
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
        System.out.println(d4);
        System.out.println(d5);
    }
}
