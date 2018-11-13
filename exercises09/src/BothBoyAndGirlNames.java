import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BothBoyAndGirlNames {


    public static final String SPLITTER = "  ";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("babynames.txt"));
        Set<String> boys = new HashSet<>();
        Set<String> girls = new HashSet<>();

        while (scanner.hasNextInt()) {
            scanner.nextInt();

            readName(scanner, boys);
            readName(scanner, girls);
        }

        boys.retainAll(girls);
        boys.forEach(System.out::println);

        scanner.close();
    }


    private static void readName(Scanner scanner, Set<String> names) {
        names.add(scanner.next());
        scanner.nextInt();
        scanner.next("\\d\\.\\d+");
    }
}
