public class TwelveDaysOfChristmas {

    public static void main(String[] args) {
        System.out.println("On the first day of Christmas, ");
        System.out.println("My true love gave to me ");
        System.out.println("a partridge in a pear tree.");
        System.out.println();

        for (int i = 2; i <= 12; i++) {
            System.out.println("On the " + ordinal(i) + " day of Christmas, ");
            System.out.println("My true love gave to me");
            System.out.println(sentence(i));
            System.out.println();
        }
    }

    private static String ordinal(int n) {
        switch (n) {
            case 1:
                return "first";
            case 2:
                return "second";
            case 3:
                return "third";
            case 4:
                return "forth";
            case 5:
                return "fifth";
            case 6:
                return "sixth";
            case 7:
                return "seventh";
            case 8:
                return "eighth";
            case 9:
                return "ninth";
            case 10:
                return "tenth";
            case 11:
                return "eleventh";
            case 12:
                return "twelfth";
        }
        throw new IllegalArgumentException("the mouth should between 1 to 12");

    }

    private static String sentence(int n) {
        switch (n) {
            case 1:
                return "And a partridge in a pear tree.";
            case 2:
                return "Two turtle doves,\n" + sentence(n - 1);
            case 3:
                return "Three French hens,\n" + sentence(n - 1);
            case 4:
                return "Four calling birds,\n" + sentence(n - 1);
            case 5:
                return "Five golden rings,\n" + sentence(n - 1);
            case 6:
                return "Six geese a-laying,\n" + sentence(n - 1);
            case 7:
                return "Seven swans a-swimming,\n" + sentence(n - 1);
            case 8:
                return "Eight maids a-milking,\n" + sentence(n - 1);
            case 9:
                return "Nine ladies dancing,\n" + sentence(n - 1);
            case 10:
                return "Ten lords a-leaping,\n" + sentence(n - 1);
            case 11:
                return "Eleven pipers piping,\n" + sentence(n - 1);
            case 12:
                return "Twelve drummers drumming,\n" + sentence(n - 1);
        }

        throw new IllegalArgumentException("the mouth should between 1 to 12");
    }
}
