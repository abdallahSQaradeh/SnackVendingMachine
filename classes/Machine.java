package vendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javafx.util.Pair;



public class Machine {

	private Inventory<Integer, Pair<Snack,Integer>> snacksInventory;
	private Inventory<Float, Pair<Money, Integer>> moneyInventory;
	private int selectedSnack;
	private float currentBalance=0;
	private float amountOfMoneyPurshased=0;
	private Map<Money,Integer> remainChanges ;
	private Map<Money,Integer> insertedMoney;
	private float remainAmout=0;
	
	public Machine() {
		this.snacksInventory = new Inventory();
		this.moneyInventory = new Inventory();
		initialize();
	}

	//----------Select Item ----------------
	public boolean setSelectedItem(int item)
	{
		if( this.snacksInventory.getItem(item).getValue()>0)
		{
			this.selectedSnack =item;
			displayItem(item);
			return true;
		}
		else {
			
			System.out.println("The amount of this snack is out");
			return false;
		}
		
		
	}
	//----------display desired snack ----------------
	public void displayItem(int item)
	{
		if(this.snacksInventory.hasItem(item))
		{
			System.out.println(this.snacksInventory.getItem(item).getKey().getName() + " - Price: "+
					this.snacksInventory.getItem(item).getKey().getPrice());
		}else
		{
			System.out.println("this item doesn't exist");
		}
	}
	
	//----------Total amount of Money In Inventory----------------
	private float totalAmountOfMoneyInventory()
	{
		//this.moneyInventory.getTotalAmount();
		float i=1;
		for(Money money: Money.values())
		{
			Pair<Money, Integer> moneyInventoryItem =this.moneyInventory.getItem(money.getValue());
			Integer quantity = (int) this.moneyInventory.getItem(money.getValue()).getKey().getValue();
            this.currentBalance += moneyInventoryItem.getValue() * quantity;
i++;
		
		}
		return this.currentBalance;
	}
	//----------Insert Money ----------------
	public Snack insertMoney(Money money)
	{
		
	      if(this.insertedMoney==null)
	      {
	    	  this.insertedMoney = new HashMap();
	      }
		  this.amountOfMoneyPurshased += money.getValue();
		  this.moneyInventory.add(money.getValue());
		  System.out.println("total money purchased " + this.amountOfMoneyPurshased + " $");
		  if(this.insertedMoney.containsKey(money.getValue()))
		  {
			  this.insertedMoney.put(money,this.insertedMoney.get(money) +1 );
		  }else {
			  this.insertedMoney.put(money,1 );
		  }
		  if(this.isInsertedMoneySufficent())
		  {
			  if(this.hasSufficentAmountOfMoneyToChange())
			  {
				  
				  return this.dispenseSnack();
			  }else
			  {
				  return new Snack("",0.0);
			  }
		  }
		  return null;
	
	}
	private void clearValues()
	{
		this.amountOfMoneyPurshased=0;
		this.selectedSnack=0;
		this.remainAmout=0;
	}
	//----------Validate Inserted Money ----------------
	public boolean isInsertedMoneySufficent()
	{
		if(this.amountOfMoneyPurshased>= this.snacksInventory.getItem(this.selectedSnack).getKey().getPrice())
		{
			return true;
		}
		return false;
	}
	//----------Display total ammount of Inserted Money ----------------
	public void displayAccomulatedAmountOfInsertedMoney()
	{
		System.out.println("Total amount of inserted Money is " + this.amountOfMoneyPurshased +"$");
	}
	//----------validate if there is a sufficent amount of money to change ----------------
 private boolean hasSufficentAmountOfMoneyToChange()
 {
	 boolean hasChange = true;
	 float price = (float) this.snacksInventory.getItem(this.selectedSnack).getKey().getPrice();
	 Map<Money, Integer> changes = getChange(this.amountOfMoneyPurshased - price);
	 if(changes!=null && this.remainAmout==0)
	 {
		 return true;
	 }
	 return false;
	 
	 
 }
 private Map<Money, Integer> getChange(float amount)
 {
	 this.remainAmout=amount;
	 float amountHolder= amount;
	 Map<Money,Integer> changes = new HashMap<>();
	 if(amountHolder>0)
	 {
		 
		 Money []money = Money.values();
		 for(int i=money.length-1;i>=0 ; i--)
		 {
			 int quantity =this.moneyInventory.getItem(money[i].getValue()).getValue();
			 while(amountHolder>= money[i].getValue() && quantity>0)
			 {
				 if(changes.containsKey(money[i]))
				 {
					 changes.replace(money[i], changes.get(money[i])+1);
					 
				 }else {
					 changes.put(money[i], 1);
				 }
				 
				 quantity -= money[i].getValue();
				 amountHolder -= money[i].getValue();
			 }
		 }
		
	 }
	
	 if(amountHolder==0 )
	 {
		 this.remainAmout=0;
		 for(Map.Entry<Money, Integer> entry : changes.entrySet())
		 {
			 this.moneyInventory.deduct(entry.getKey().getValue(),entry.getValue() );
		 }
		 
		 this.remainChanges = changes;
		return changes; 
	 }else
	 {
		 return null;
	 }
	 
	 
 }
 public void acceptedMoney()
 {
	 System.out.println("Snack Machine Accepts : ");
		int i=1;
		for(Money money:Money.values())
		{
			System.out.print("["+i+"] ");
		
				System.out.print((money.getCurrency()=="c"? money.getValue()*10 : money.getValue()) +money.getCurrency()+ (i==money.values().length? "": " - "));
			
			
			
			i++;
		}
		System.out.println();
 }
 public boolean ValidateInputForInsertingMoney(int value)
 {
	 if(value>=1 &&value<= Money.values() .length)
		{
			return true;
		}
	 return false;
 }


private Snack dispenseSnack()
{
	
	this.snacksInventory.deduct(this.selectedSnack, 1);
	return snacksInventory.getItem(this.selectedSnack).getKey();
}
protected void displayRemainChanges()
{
	System.out.println("Remain Changes");
	if(this.remainChanges.isEmpty())
	{
		System.out.println(0);
	}else
	{
	for(Map.Entry<Money, Integer> entry: this.remainChanges.entrySet())
	{
		System.out.println("[ Value " + entry.getKey().getValue() + " : Amount " + entry.getValue() + " : Currency "+entry.getKey().getCurrency() +" ]");
	}
	remainChanges.clear();
	}
}
//--------------Initialize -------
private void initialize()
{
	int i=1;
	for(Money money: Money.values())
	{
	
		this.moneyInventory.put((float)money.getValue(),new Pair(money,10));
		
	}
	 i=1;
	
	 Random r = new Random();
	for(i=1;i<=25;i++) {
		float random=1 + r.nextFloat() * (80 - 1);
		Snack snack = new Snack("Doretos"+i,Math.round(random*100)/100);
		this.snacksInventory.put(i, new Pair(snack,10));
		
	}
	this.totalAmountOfMoneyInventory();
}
public Map<Money,Integer> refund()
{
	Map refund = new HashMap(this.insertedMoney);
	this.insertedMoney.clear();
	clearValues();
	
	return refund;
}
}