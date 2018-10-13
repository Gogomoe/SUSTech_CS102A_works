public class A1Q5 {

    public static void main(String[] args) {
        String reverse = new StringBuilder(args[0]).reverse().toString();
        System.out.println(Integer.parseInt(reverse));
    }
}
