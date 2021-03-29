package vendingMachine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javafx.util.Pair;


public class Inventory<T,M> {

	private Map<T,M> inventory = new HashMap();
	private float totalAmount=0;
	public Integer getQuantity(T item){
        Integer value = ((Pair<M, Integer>) inventory.get(item)).getValue();
        return value == null? null : value ;
    }
   
    public void add(T item){
        Pair<M,Integer> currentItem = (Pair<M, Integer>) inventory.get(item);
        Integer newValue = currentItem.getValue()+1;
        inventory.put(item, (M) new Pair(currentItem.getKey(), newValue));
    }
   
    public void deduct(T item, int val) {
        if (hasItem(item) && getQuantity(item)>0) {
        	Pair<M,Integer> currentItem = (Pair<M, Integer>) inventory.get(item);
            Integer newValue = currentItem.getValue()-val;
            inventory.put(item, (M) new Pair(currentItem.getKey(), newValue));
           
        }
    }
 
    
    public M getItem(T key)
    {
    	if(hasItem(key))
    	{
    		return (M) this.inventory.get(key);
    	}
    	return null;
    }
   
    public boolean hasItem(T key){
        return this.inventory.containsKey(key) ;
    }
   
    public void clear(){
        inventory.clear();
    }

    public void put(T item, Pair<M,Integer> pair) {
        inventory.put(item, (M) pair);
    }
	
}
