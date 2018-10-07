public class Kary {

    public static void main(String[] args) {
        long i = Long.parseLong(args[0]);
        int k = Integer.parseInt(args[1]);

        System.out.println(Long.toString(i, k).toUpperCase());
    }

}
