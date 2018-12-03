public class Motorbike extends Vehicle {

    private int deposit;

    public Motorbike(String name, double basicPrice, int deposit) {
        super(name, basicPrice);
        this.deposit = deposit;
    }

    @Override
    public double getRental(int days) {
        int k = (days - 1) / 5 + 1;
        return k * getBasicPrice() + deposit;
    }

    @Override
    public String toString() {
        return "Motorbike " + super.toString();
    }
}
