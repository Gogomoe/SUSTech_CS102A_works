public class FunctionGrowth {

    public static void main(String[] args) {
        for (int n = 16; n <= 2048; n *= 2) {
            System.out.print(Math.log(n) + "\t");
            System.out.print(n + "\t");
            System.out.print(n * Math.log(n) / Math.log(Math.E) + "\t");
            System.out.print(Math.pow(n, 2) + "\t");
            System.out.print(Math.pow(n, 3) + "\t");
            System.out.print(Math.pow(2, n) + "\t");
            System.out.println();
        }
    }

}
