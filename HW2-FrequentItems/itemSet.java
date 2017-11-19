package frequentItems;

import java.util.Set;
import java.util.HashSet;

public class itemSet {
	public Set<String> items;
	public Integer frequency;
	
	public itemSet(){
		this.items=new HashSet<String>();
		this.frequency=0;
	}
	
	public void add(String itemName,Integer freq){
		this.items.add(itemName);
		this.frequency=freq;
	}
}
