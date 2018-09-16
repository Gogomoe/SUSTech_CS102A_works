import java.util.Random;

public class RollLoadedDie {

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 6, 6};
        System.out.println(data[new Random().nextInt(8)]);
    }

}
