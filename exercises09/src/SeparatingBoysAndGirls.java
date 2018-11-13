import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SeparatingBoysAndGirls {

    public static final String SPLITTER = "  ";

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("babynames.txt"));
        PrintWriter boys = new PrintWriter("boynames.txt");
        PrintWriter girls = new PrintWriter("girlnames.txt");

        while (scanner.hasNextInt()) {
            int rank = scanner.nextInt();

            write(scanner, boys, rank);
            write(scanner, girls, rank);
        }

        scanner.close();
        boys.close();
        girls.close();
    }

    private static void write(Scanner scanner, PrintWriter printer, int rank) {
        printer.print(rank);
        printer.print(SPLITTER);
        printer.print(scanner.next());
        printer.print(SPLITTER);
        printer.print(scanner.nextInt());
        printer.print(SPLITTER);
        printer.print(scanner.next("\\d\\.\\d+"));
        printer.println();
    }
}
