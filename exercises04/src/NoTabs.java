public class NoTabs {

    public static String noTabs(String s, int tabPosition) {
        StringBuilder blank = new StringBuilder();
        for (int i = 0; i < tabPosition; i++) {
            blank.append(" ");
        }
        return s.replace("\t", blank);
    }

}
