import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountKeywords {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Java source file: ");
        String filename = input.nextLine();

        File file = new File(filename);
        if (file.exists()) {
            System.out.println("The number of keywords in " + filename + " is " + countKeywords(file));
        } else {
            System.out.println("File " + filename + " does not exist");
        }
    }

    private static String[] keywordString = {"abstract", "assert", "boolean", "break", "byte",
            "case", "catch", "char", "class", "const", "continue", "default",
            "do", "double", "else", "enum", "extends", "for", "final", "finally",
            "float", "goto", "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private", "protected",
            "public", "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "try", "void",
            "volatile", "while", "true", "false", "null"};

    private static Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));

    private static Pattern p = Pattern.compile("/\\*|//|\"");

    public static int countKeywords(File file) throws Exception {
        count = 0;
        inString = false;
        inParagraphComment = false;
        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            Scanner line = new Scanner(input.nextLine());
            inLineComment = false;
            while (line.hasNext()) {
                parse(line.next());
            }
        }
        return count;
    }

    private static int count = 0;
    private static boolean inString = false;
    private static boolean inLineComment = false;
    private static boolean inParagraphComment = false;

    private static void parse(String word) {
        if (inString) {
            if (word.contains("\"")) {
                inString = false;
                parse(word.substring(word.indexOf("\"") + 1));
            }
            return;
        }
        if (inParagraphComment) {
            if (word.contains("*/")) {
                inParagraphComment = false;
                parse(word.substring(word.indexOf("*/") + 1));
            }
            return;
        }
        if (inLineComment) {
            return;
        }
        Matcher m = p.matcher(word);
        if (m.find()) {
            String find = m.group();
            if (find.equals("//")) {
                parse(word.substring(0, m.start()));
                inLineComment = true;
                return;
            } else if (find.equals("/*")) {
                parse(word.substring(0, m.start()));
                inParagraphComment = true;
                return;
            } else if (find.equals("\"")) {
                parse(word.substring(0, m.start()));
                inString = true;
                return;
            }
        }
        if (keywordSet.contains(word)) {
            count++;
        }
    }

}