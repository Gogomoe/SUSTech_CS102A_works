import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        exercise2(args);
    }

    // E21.1
    public static void exercise1() {
        HashSet<String> s1 = new LinkedHashSet<>();
        s1.add("George");
        s1.add("Jim");
        s1.add("John");
        s1.add("Blake");
        s1.add("Kevin");
        s1.add("Michael");
        HashSet<String> s2 = new LinkedHashSet<>();
        s2.add("George");
        s2.add("Katie");
        s2.add("Kevin");
        s2.add("Michelle");
        s2.add("Ryan");

        HashSet<String> union = clone(s1);
        union.addAll(s2);
        System.out.println("union: " + toString(union));

        HashSet<String> difference = clone(s1);
        difference.removeAll(s2);
        System.out.println("difference: " + toString(difference));

        HashSet<String> intersection = clone(s1);
        intersection.retainAll(s2);
        System.out.println("intersection: " + toString(intersection));
    }

    // E21.2
    public static void exercise2(String[] args) throws FileNotFoundException {
        TreeSet<String> words = new TreeSet<>();
        Scanner scanner = new Scanner(new File(args[0]));
        Pattern p = Pattern.compile("[A-Za-z-]+");
        while (scanner.hasNext()) {
            Matcher m = p.matcher(scanner.next());
            while (m.find()) {
                words.add(m.group());
            }
        }
        System.out.println("words: " + toString(words));
    }

    // E21.4
    public static void exercise4() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a file: ");
        String filename = input.nextLine();

        int vowels = 0;
        int consonants = 0;

        Set<Character> set = new HashSet<>();
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');


        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File " + filename + " does not exist");
            return;
        }

        FileReader reader = new FileReader(file);
        int i = reader.read();
        while (i != -1) {
            char c = (char) i;
            if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')) {
                if (set.contains(c)) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
            i = reader.read();
        }

        System.out.println("vowels count: " + vowels);
        System.out.println("consonants count: " + consonants);
    }

    public static <T> HashSet<T> clone(HashSet<T> set) {
        return new LinkedHashSet<>(set);
    }

    public static <T> String toString(Set<T> set) {
        return "{" + set.stream().map(String::valueOf).collect(Collectors.joining(", ")) + "}";
    }
}
