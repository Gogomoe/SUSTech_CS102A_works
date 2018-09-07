import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DragonCurve {

    private enum Turn {
        L, R;

        static {
            L.reverse = R;
            R.reverse = L;
        }

        Turn reverse;

        public Turn reverse() {
            return reverse;
        }
    }

    private List<Turn> turns = new ArrayList<>();

    private DragonCurve(List<Turn> previous) {
        List<Turn> reverse = new ArrayList<>(previous);
        Collections.reverse(reverse);
        reverse = reverse.stream()
                .map(Turn::reverse)
                .collect(Collectors.toList());

        turns.addAll(previous);
        turns.add(Turn.L);
        turns.addAll(reverse);
    }

    @Override
    public String toString() {
        return "F" + turns.stream()
                .map(Object::toString)
                .map(it -> it + "F")
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {

        List<Turn> n0 = new ArrayList<>();
        DragonCurve d1 = new DragonCurve(n0);
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
