package vendingMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class SnackVendingMachineTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Scanner input = new Scanner(System.in);
    Machine machine = new Machine();
	KeyPad keypad = new KeyPad(machine);
	Monitor monitor = new Monitor(machine);
	
	while(true)
	{
		System.out.println("Enter a number between 1 -25 to choose your favorite snack");
		keypad.pickNumber(input.nextInt());
		System.out.println("Do you want to purchase this snack?[1] or choose another one?[2]");
		int number = input.nextInt();
		if(number==1)
		{
			
			
			while(true)
			{
				machine.acceptedMoney();
				int value = input.nextInt();
				if(machine.ValidateInputForInsertingMoney(value))
				{
				Snack snack = machine.insertMoney(Money.values()[value-1]);
				if(snack==null)
				{
					monitor.displayInSufficentAmountOfMoneyEntered();
				}else if(snack.getName()=="")
				{
					monitor.displayInSufficentAmountOfMoneyToChange();
					monitor.dispalyRefund();
				}else {
					monitor.displaySnack(snack);
					monitor.displayChanges();
					break;
				}
				
				//value = input.nextInt();
				}
				else 
				{
					System.out.println("Please select a valid value between 1 and " + 25);
					 value = input.nextInt();
				}
				
			}

		}else if(number==2)
		{
			continue;
		}
	}

	}
	

}
