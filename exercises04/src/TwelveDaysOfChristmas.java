public class TwelveDaysOfChristmas {

    public static void main(String[] args) {
        System.out.println("On the first day of Christmas, ");
        System.out.println("My true love gave to me ");
        System.out.println("a partridge in a pear tree.");
        System.out.println();

        for (int i = 2; i <= 12; i++) {
            System.out.print("On the ");
            ordinal(i);
            System.out.println("My true love gave to me");
            sentence(i);
            System.out.println();
        }
    }

    private static void ordinal(int n) {
        switch (n) {
            case 1:
                System.out.println("first");
                break;
            case 2:
                System.out.println("second");
                break;
            case 3:
                System.out.println("third");
                break;
            case 4:
                System.out.println("forth");
                break;
            case 5:
                System.out.println("fifth");
                break;
            case 6:
                System.out.println("sixth");
                break;
            case 7:
                System.out.println("seventh");
                break;
            case 8:
                System.out.println("eighth");
                break;
            case 9:
                System.out.println("ninth");
                break;
            case 10:
                System.out.println("tenth");
                break;
            case 11:
                System.out.println("eleventh");
                break;
            case 12:
                System.out.println("twelfth");
                break;
        }

    }

    private static void sentence(int n) {
        switch (n) {
            case 12:
                System.out.print("Twelve drummers drumming,\n");
            case 11:
                System.out.print("Eleven pipers piping,\n");
            case 10:
                System.out.print("Ten lords a-leaping,\n");
            case 9:
                System.out.print("Nine ladies dancing,\n");
            case 8:
                System.out.print("Eight maids a-milking,\n");
            case 7:
                System.out.print("Seven swans a-swimming,\n");
            case 6:
                System.out.print("Six geese a-laying,\n");
            case 5:
                System.out.print("Five golden rings,\n");
            case 4:
                System.out.print("Four calling birds,\n");
            case 3:
                System.out.print("Three French hens,\n");
            case 2:
                System.out.print("Two turtle doves,\n");
            case 1:
                System.out.println("And a partridge in a pear tree.");
        }

    }
}
