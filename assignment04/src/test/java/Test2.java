public class Test2 {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(0, 0, 2, 2, 100, 50, 100);
        Rectangle r2 = new Rectangle(1.5, 2, 3, 1, -1, 350, 100);
        Rectangle r3 = new Rectangle(1.6, -1, 1, 3, 50, 100, 200);
        Rectangle r4 = new Rectangle(1, 1, 1, 2, 180, 100, 40);
        System.out.println(r1.intersect(r2));
        System.out.println(r1.intersect(r3));
        System.out.println(r1.intersect(r4));
        System.out.println(Rectangle.intersect(r3, r4));
    }
}