import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PrintAllNamesWithRank {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("babynames.txt"));
        List<Name> list = new ArrayList<>();

        while (scanner.hasNextInt()) {
            scanner.nextInt();

            readName(scanner, list, "boy");
            readName(scanner, list, "girl");
        }

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "  " + list.get(i));
        }
        scanner.close();
    }


    private static void readName(Scanner scanner, List<Name> names, String gender) {
        names.add(new Name(scanner.next(), scanner.nextInt(), gender));
        scanner.next("\\d\\.\\d+");
    }

    static class Name implements Comparable {
        String name;
        int population;
        String gender;

        public Name(String name, int population, String gender) {
            this.name = name;
            this.population = population;
            this.gender = gender;
        }

        @Override
        public int compareTo(Object o) {
            return -(this.population - ((Name) o).population);
        }

        @Override
        public String toString() {
            return name + "  " + population + "  " + gender;
        }
    }
}
