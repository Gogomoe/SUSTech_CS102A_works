public class Checksum {

    public static void main(String[] args) {
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            int digit = args[0].charAt(i) - '0';

            if (i % 2 == 0) {
                sum += digit;
            } else {
                sum += f(digit);
            }
        }

        int checksum = (0 - sum % 10) % 10;

        System.out.println(args[0] + checksum);
    }

    private static int f(int i) {
        return (i * 2) / 10 + (i * 2) % 10;
    }

}
