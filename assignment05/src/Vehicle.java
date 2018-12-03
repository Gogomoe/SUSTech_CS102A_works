

public abstract class Vehicle  {
	private String name;
	private double basicPrice;
	
	public Vehicle(String name, double basicPrice) {
		this.name = name;
		this.basicPrice = basicPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(double basicPrice) {
		this.basicPrice = basicPrice;
	}
	
	public abstract double getRental(int days);

	@Override
	public String toString() {
		return "Vehicle [" + name + ", " + basicPrice + "]";
	}
	
	
}
