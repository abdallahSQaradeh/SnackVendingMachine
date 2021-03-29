
public class Inventory<T,M> {
	private Map<T,M> inventory ;
	private float totalAmount;
	public Integer getQuantity(T item);
    public void add(T item);
    public void deduct(T item, int val) ;
    public M getItem(T key)
    public boolean hasItem(T key);
    public void clear();
    public void put(T item, Pair<M,Integer> pair);
	
}

public class Snack {
	private String name;
	private double price;
	public Snack(String name, double price) ;
	public String getName();
	public double getPrice();
	
}
public enum Money {

	TEN_CENT("c",(float)0.1),
    TWENTY_CENT("c",(float)0.2),
    FIFTY_CENT("c",(float)0.5),
     ONE_DOLLAR("$",1),
	TWENTY_DOLLAR("$",20),
    FIFTY_DOLLAR("$",50);
	private String currency;
	private float value;
	private Money(String currency, float value);
	public String getCurrency();
	public float getValue();


}
public class Monitor {

	private Machine machine;
	public Monitor(Machine machine);
	public void displayInSufficentAmountOfMoneyEntered();
	public void dispalyRefund();
	public void displayInSufficentAmountOfMoneyToChange();
	public void displaySnack(Snack snack);
	public void displayChanges();
	
}

public class KeyPad {
	private Machine machine;
	public KeyPad(Machine machine);
	public boolean pickNumber(int number);
}
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
public Machine();
public boolean setSelectedItem(int item);
public void displayItem(int item);
private float totalAmountOfMoneyInventory();
public Snack insertMoney(Money money);
private void clearValues();
public boolean isInsertedMoneySufficent();
public void displayAccomulatedAmountOfInsertedMoney();private boolean hasSufficentAmountOfMoneyToChange();
private Map<Money, Integer> getChange(float amount);
public void acceptedMoney();
public boolean ValidateInputForInsertingMoney(int value);
private Snack dispenseSnack();
protected void displayRemainChanges();
private void initialize();
public Map<Money,Integer> refund();
}