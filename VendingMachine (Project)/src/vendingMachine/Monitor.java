package vendingMachine;

import java.util.Map;

public class Monitor {

	private Machine machine;
	public Monitor(Machine machine) {
		this.machine = machine;
	}

	public void displayInSufficentAmountOfMoneyEntered() {
		System.out.println("Insufficent amount of money please insert more money");
		
	}
	public void dispalyRefund()
	{
		System.out.println("Don't forget your money");
		for(Map.Entry<Money, Integer> entry : machine.refund().entrySet())
		{
			System.out.println(entry.getKey().getValue() + " " + entry.getValue() + " " + entry.getKey().getCurrency());
		}
	}
	public void displayInSufficentAmountOfMoneyToChange() {
		System.out.println("Machine has Insufficent amount of money to return");
		
	}
	public void displaySnack(Snack snack) {
		System.out.println("Enjoy your Snack " + snack.getName());
		
	}
	public void displayChanges() {
		machine.displayRemainChanges();
		
	}
}
