public class CountingPrimes {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        if (n > 10_000_000) {
            n = 10_000_000;
            System.out.println("n is greater than 10 million");
        }
        boolean[] notPrimes = new boolean[n + 1];
        System.out.println(2);
        for (int i = 3; i <= n; i += 2) {
            if (notPrimes[i]) {
                continue;
            }
            System.out.println(i);
            for (int j = 2 * i; j <= n; j += i) {
                notPrimes[j] = true;
            }
        }
    }

}
