package vendingMachine;

public class Snack {

	
	
	private String name;
	private double price;
	
	public Snack(String name, double price) {
		this.name = name;
		this.price = price;
		
	}
	public String getName()
	{
		return name;
		
	}
	public double getPrice()
	{
		return price;
	}
	
}
