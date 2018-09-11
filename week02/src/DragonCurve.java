public class DragonCurve {

    public static void main(String[] args) {
        String d0 = "F";

        String d1 = d0 + "L" + d0;
        String d1r = d0 + "R" + d0;

        String d2 = d1 + "L" + d1r;
        String d2r = d1 + "R" + d1r;

        String d3 = d2 + "L" + d2r;
        String d3r = d2 + "R" + d2r;

        String d4 = d3 + "L" + d3r;
        String d4r = d3 + "R" + d3r;

        String d5 = d4 + "L" + d4r;

        System.out.println(d0);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
        System.out.println(d4);
        System.out.println(d5);
    }
}
