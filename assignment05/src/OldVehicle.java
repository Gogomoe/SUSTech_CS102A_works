public class OldVehicle extends Vehicle implements Insurance {

    public OldVehicle(String name, double basicPrice) {
        super(name, basicPrice);
    }

    @Override
    public double getRental(int days) {
        if (days < 3) {
            return getBasicPrice();
        }
        return getBasicPrice() + (days - 3) * getBasicPrice() * 0.2;
    }

    @Override
    public String toString() {
        return "Old " + super.toString();
    }

    @Override
    public void InsuanceDescription() {
        System.out.println("Purchased insurance");
    }
}
