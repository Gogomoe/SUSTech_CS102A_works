import java.util.*;
import java.util.stream.Collectors;

public class DragonCurveWithGraph {

    private static Map<Integer, DragonCurveWithGraph> cache = new HashMap<>();

    private enum Turn {
        L, R;

        public Turn reverse() {
            switch (this) {
                case L:
                    return R;
                case R:
                    return L;
            }
            throw new RuntimeException("WTF??");
        }

    }

    private List<Turn> turns = new ArrayList<>();

    private DragonCurveWithGraph(int n) {
        if (n == 0) {
            return;
        }
        DragonCurveWithGraph last = DragonCurveWithGraph.getN(n - 1);
        List<Turn> reverse = new ArrayList<>(last.turns);
        Collections.reverse(reverse);
        reverse = reverse.stream()
                .map(Turn::reverse)
                .collect(Collectors.toList());
        turns.addAll(last.turns);
        turns.add(Turn.L);
        turns.addAll(reverse);
    }


    private enum Direction {
        UP, DOWN, LEFT, RIGHT;

        public Direction left() {
            switch (this) {
                case UP:
                    return LEFT;
                case LEFT:
                    return DOWN;
                case DOWN:
                    return RIGHT;
                case RIGHT:
                    return UP;
            }
            throw new RuntimeException("WTF??");
        }

        public Direction right() {
            switch (this) {
                case UP:
                    return RIGHT;
                case RIGHT:
                    return DOWN;
                case DOWN:
                    return LEFT;
                case LEFT:
                    return UP;
            }
            throw new RuntimeException("WTF??");
        }

        public void go(Point p) {
            switch (this) {
                case UP:
                    p.y -= 1;
                    break;
                case DOWN:
                    p.y += 1;
                    break;
                case LEFT:
                    p.x -= 1;
                    break;
                case RIGHT:
                    p.x += 1;
                    break;
            }
        }

        public String paintHead() {
            switch (this) {
                case UP:
                    return "│";
                case DOWN:
                    return "│";
                case LEFT:
                    return "─";
                case RIGHT:
                    return "─";
            }
            throw new RuntimeException("WTF??");
        }

        public String paintLeft() {
            switch (this) {
                case UP:
                    return "┐";
                case DOWN:
                    return "└";
                case LEFT:
                    return "┌";
                case RIGHT:
                    return "┘";
            }
            throw new RuntimeException("WTF??");
        }

        public String paintRight() {
            switch (this) {
                case UP:
                    return "┌";
                case DOWN:
                    return "┘";
                case LEFT:
                    return "└";
                case RIGHT:
                    return "┐";
            }
            throw new RuntimeException("WTF??");
        }
    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ')';
        }
    }

    private void print() {


        class Painter {
            private Point p = new Point(16, 6);
            private String[][] map = new String[18][18];
            private Direction d = Direction.RIGHT;
            private Deque<Turn> deque = new ArrayDeque<>(turns);

            private void paint() {
                map[p.y][p.x] = d.paintHead();

                while (!deque.isEmpty()) {
                    d.go(p);

                    Turn turn = deque.pollFirst();
                    if (turn == Turn.L) {
                        map[p.y][p.x] = d.paintLeft();
                        d = d.left();
                    } else {
                        map[p.y][p.x] = d.paintRight();
                        d = d.right();
                    }
                    d.go(p);

                    map[p.y][p.x] = d.paintHead();
                }
                for (String[] aMap : map) {
                    for (String anAMap : aMap) {
                        System.out.print(Objects.requireNonNullElse(anAMap, " "));
                    }
                    System.out.println();
                }
            }
        }
        new Painter().paint();
    }

    @Override
    public String toString() {
        return turns.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    private static DragonCurveWithGraph getN(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        DragonCurveWithGraph d = new DragonCurveWithGraph(n);
        cache.put(n, d);
        return d;
    }


    public static void main(String[] args) {
        DragonCurveWithGraph.getN(0).print();
        System.out.println("=========== 0 ============");
        DragonCurveWithGraph.getN(1).print();
        System.out.println("=========== 1 ============");
        DragonCurveWithGraph.getN(2).print();
        System.out.println("=========== 2 ============");
        DragonCurveWithGraph.getN(3).print();
        System.out.println("=========== 3 ============");
        DragonCurveWithGraph.getN(4).print();
        System.out.println("=========== 4 ============");
        DragonCurveWithGraph.getN(5).print();
        System.out.println("=========== 5 ============");
    }
}
