package vendingMachine;

public enum Money {

	TEN_CENT("c",(float)0.1),TWENTY_CENT("c",(float)0.2),FIFTY_CENT("c",(float)0.5), ONE_DOLLAR("$",1),
	TWENTY_DOLLAR("$",20),FIFTY_DOLLAR("$",50);
	;
	
	private String currency;
	private float value;
	
	private Money(String currency, float value)
	{
		this.currency = currency;
		this.value = value;
	}
	public String getCurrency()
	{
		return this.currency;
	}
	public float getValue()
	{
		return this.value;
	}


}
