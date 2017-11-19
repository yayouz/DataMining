package frequentItems;

import java.util.HashMap;
import java.util.Iterator;


public class filter {
	private HashMap <Integer,itemSet> FrequentItemSet;
	
	public filter(){
		this.FrequentItemSet=new HashMap <Integer,itemSet>();
	}
	
	public HashMap <Integer,itemSet> filte(Integer support,HashMap <Integer,itemSet> Set,HashMap <Integer,basket> basketList )
	{	
		int newId=1;
		Boolean exist=true; 
		itemSet item=new itemSet();
		for(Integer idItem: Set.keySet()){
			item=Set.get(idItem);
			//System.out.println("the feature of set is : "+item.items+" frequency is: "+item.frequency);
			for(Integer idBasket:basketList.keySet()){
				exist=true; 
				Iterator <String> It=Set.get(idItem).items.iterator();
				for(int n=0;n<Set.get(idItem).items.size();n++){
					if(!basketList.get(idBasket).items.contains(It.next())){
						exist=false;
					}
				}
				if(exist==true){
					item.frequency=item.frequency+1;
				}
			}
			//System.out.println("the feature of set is : "+item.items+" new frequency is: "+item.frequency);
			if(item.frequency>support){
				FrequentItemSet.put(newId, item);
				newId++;
			}
		}
		
		return this.FrequentItemSet;
	}
	
	public HashMap <String,Integer> filteLevel1(Integer support,HashMap <String,Integer> ItemSet ){
		HashMap <String,Integer> FrequentItemSetLevel1=new HashMap <String,Integer>();
		for(String name:ItemSet.keySet()){
			if(ItemSet.get(name)>support){
				FrequentItemSetLevel1.put(name, ItemSet.get(name));
			}
		}
		return FrequentItemSetLevel1;
	}
}
