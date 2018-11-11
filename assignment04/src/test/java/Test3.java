public class Test3 {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(-2, 1, 5, 5, 100, 150, 200);
        Rectangle r2 = new Rectangle(9, 1, 5, 5, 250, 100, 40);
        System.out.println(r1.isInBoundary());
        System.out.println(r2.isInBoundary());
    }
}