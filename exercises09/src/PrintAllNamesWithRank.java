import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintAllNamesWithRank {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("babynames.txt"));
        List<Name> list = new ArrayList<>();

        while (scanner.hasNextInt()) {
            scanner.nextInt();

            readName(scanner, list);
            readName(scanner, list);
        }

        list.stream().sorted().forEach(System.out::println);
        scanner.close();
    }


    private static void readName(Scanner scanner, List<Name> names) {
        names.add(new Name(scanner.next(), scanner.nextInt()));
        scanner.next("\\d\\.\\d+");
    }

    static class Name implements Comparable {
        String name;
        int population;

        public Name(String name, int population) {
            this.name = name;
            this.population = population;
        }

        @Override
        public int compareTo(Object o) {
            return -(this.population - ((Name) o).population);
        }

        @Override
        public String toString() {
            return name + " " + population;
        }
    }
}
