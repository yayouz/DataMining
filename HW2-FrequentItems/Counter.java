package frequentItems;

import java.util.HashMap;
import java.util.Iterator;

public class Counter {
	public Integer frequency;
	HashMap <Integer,itemSet> count;
	public Counter(){
		this.frequency=0;
		this.count=new HashMap <Integer,itemSet>();
	}
	public HashMap <Integer,itemSet> count(HashMap <Integer,itemSet> set,HashMap <Integer,basket> basketList){
		Boolean exist=true; 
		itemSet item=new itemSet();
		for(Integer idItem: set.keySet()){
			item=set.get(idItem);
			//System.out.println("the feature of set is : "+item.items+" frequency is: "+item.frequency);
			for(Integer idBasket:basketList.keySet()){
				exist=true; 
				Iterator <String> It=set.get(idItem).items.iterator();
				for(int n=0;n<set.get(idItem).items.size();n++){
					if(!basketList.get(idBasket).items.contains(It.next())){
						exist=false;
					}
				}
				if(exist==true){
					item.frequency=item.frequency+1;
				}
			}
			//System.out.println("the feature of set is : "+item.items+" new frequency is: "+item.frequency);
			count.put(idItem, item);
		}
		return count;
	}
}
