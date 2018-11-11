public class Test1 {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(0, 1, 2, 2, 100, 150, 100);
        Rectangle r2 = new Rectangle(3, 1, 3, 1, -1, 350, 100);
        Rectangle r3 = new Rectangle(2, 2);
        Rectangle r4 = new Rectangle(3, -1, 2, 2);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
    }
}