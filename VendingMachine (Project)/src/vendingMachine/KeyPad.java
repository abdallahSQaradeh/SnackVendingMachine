package vendingMachine;

public class KeyPad {

	private Machine machine;
	
	public KeyPad(Machine machine)
	{
		this.machine = machine;
	}
	public boolean pickNumber(int number)
	{
		if(number>=1 && number<=25) {
		if(((Machine) machine).setSelectedItem(number))
			
		return true;
		else return false;
		}
		else {
			
				System.out.println("Please enter a valid number 1 - 25");
			return false;
		}
	}
}
