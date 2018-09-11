import java.util.*;
import java.util.stream.Collectors;

public class DragonCurveByFP {

    private static Map<String, String> reverseCharMap = new HashMap<>();

    static {
        reverseCharMap.put("L", "R");
        reverseCharMap.put("R", "L");
    }

    private List<String> turns = new ArrayList<>();

    private DragonCurveByFP(List<String> previous) {
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
        DragonCurveByFP d1 = new DragonCurveByFP(new ArrayList<>());
        DragonCurveByFP d2 = new DragonCurveByFP(d1.turns);
        DragonCurveByFP d3 = new DragonCurveByFP(d2.turns);
        DragonCurveByFP d4 = new DragonCurveByFP(d3.turns);
        DragonCurveByFP d5 = new DragonCurveByFP(d4.turns);

        System.out.println("F");
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
        System.out.println(d4);
        System.out.println(d5);
    }
}
