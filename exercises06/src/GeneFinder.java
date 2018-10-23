import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneFinder {

    public static String[] searchGenes(String dna) {
        Pattern p = Pattern.compile("(?<=ATG)((?:.{3})*?)(?=TAG|TAA|TGA)");
        Matcher m = p.matcher(dna);
        List<String> groups = new ArrayList<>();
        while (m.find()) {
            groups.add(m.group());
        }
        return groups.toArray(new String[0]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchGenes("ATAGATGCATAGCGCATAGCTAGATGTGCTAGC")));
    }
}
