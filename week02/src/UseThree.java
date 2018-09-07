import java.text.MessageFormat;

public class UseThree {
    public static void main(String[] args) {
        System.out.print(MessageFormat.format("Hi {2}, {1}, and {0}.", args[0], args[1], args[2]));
    }
}

