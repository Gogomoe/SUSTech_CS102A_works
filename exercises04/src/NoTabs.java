public class NoTabs {

    public static String noTabs(String s, int tabPosition) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char[] arr = s.toCharArray();
        for (char ch : arr) {
            i++;
            if (ch == '\t') {
                sb.append(nblank(tabPosition - i + 1));
                i = 8;
            } else {
                sb.append(ch);
            }
            if (i == 8) {
                i = 0;
            }
        }
        return sb.toString();
    }

    private static String nblank(int amount) {
        StringBuilder blank = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            blank.append(" ");
        }
        return blank.toString();
    }

}
