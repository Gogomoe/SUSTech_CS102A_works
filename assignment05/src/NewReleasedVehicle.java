public class NewReleasedVehicle extends Vehicle implements Insurance {

    private Energy energy;

    public NewReleasedVehicle(String name, double basicPrice, boolean chargeable, boolean oiling) {
        super(name, basicPrice);
        /*
        TODO 这里题目是不是搞错了
        题目里 chargeable 是 GAS, oiling 是 ELECTRIC
         */
        if (chargeable && oiling) {
            energy = Energy.HYBRID;
        } else if (chargeable) {
            energy = Energy.ELECTRIC;
        } else if (oiling) {
            energy = Energy.GAS;
        }
    }

    @Override
    public double getRental(int days) {
        if (days < 4) {
            return getBasicPrice();
        }
        return getBasicPrice() + (days - 4) * getBasicPrice() * 0.3;
    }

    @Override
    public String toString() {
        return "New " + super.toString() + energy.getDesc();
    }

    @Override
    public void InsuanceDescription() {
        System.out.println("Purchased high-value insurance");
    }
}
