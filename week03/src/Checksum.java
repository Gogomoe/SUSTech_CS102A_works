public class Checksum {

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i <= 8; i++) {
            sum += (i + 2) * (args[0].charAt(8 - i) - '0');
        }
        int remain = (11 - sum % 11) % 11;
        String postfix = remain == 10 ? "x" : remain + "";
        System.out.println(args[0] + postfix);
    }

}
