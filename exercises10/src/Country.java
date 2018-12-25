import java.util.Comparator;

public class Country implements Comparable<Country> {

    public static Comparator<Country> createComparatorByName(final boolean increasing) {
        int i = increasing ? 1 : -1;
        return (a, b) -> i * a.name.compareTo(b.name);
    }

    public static Comparator<Country> createComparatorByArea(final boolean increasing) {
        int i = increasing ? 1 : -1;
        return (a, b) -> i * Double.compare(a.area, b.area);
    }

    /**
     * Constructs a country.
     *
     * @param aName  the name of the country
     * @param anArea the area of the country
     */
    public Country(String aName, double anArea) {
        name = aName;
        area = anArea;
    }

    /**
     * Gets thename ofthe country.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the area of the country. @return the area
     */
    public double getArea() {
        return area;
    }

    private String name;
    private double area;

    /**
     * Compares two countries by area.
     *
     * @param other the other country
     * @return a negative number if this country has a smaller area than otherCountry, 0 if the areas are the same a positive number otherwise
     */
    public int compareTo(Country other) {
        if (area < other.area) return -1;
        if (area > other.area) return 1;
        return 0;
    }
}


