package frequentItemsV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class filter {
	private HashMap <HashSet<Integer>,Integer> FrequentItemSet;
	
	public filter(){
		this.FrequentItemSet=new HashMap <HashSet<Integer>,Integer>();
	}
	
	public HashMap <HashSet<Integer>,Integer> filte(double support, HashMap <HashSet<Integer>,Integer> Set,ArrayList<ArrayList<Integer>> basketList )
	{	
		double threshold=support*basketList.size();
		ArrayList<Integer> singleBasket=new ArrayList<Integer>();
		Boolean exist1=true; 
		int frequency=0;
		int basketSize=basketList.size();
		for(HashSet<Integer> item: Set.keySet()){
			frequency=0;
			//System.out.println("the feature of set is : "+item.items+" frequency is: "+item.frequency);
			for(int id=0;id<basketSize;id++){
				singleBasket=basketList.get(id);
				exist1=true;  
				Iterator <Integer> It=item.iterator();
				for(int n=0;n<item.size();n++){
					Integer itemTemp=It.next();
					if(!singleBasket.contains(itemTemp)){
						exist1=false;
					}
				}
				if(exist1==true){
					frequency=frequency+1;
				}
				
			}
			//System.out.println("the feature of set is : "+item+" new frequency is: "+frequency);
			if(frequency>threshold){
				this.FrequentItemSet.put(item, frequency);
			}
		}
		
		return this.FrequentItemSet;
	}
	
	public HashMap <Integer,Integer> filteLevel1(double  support,Integer basketSize,HashMap <Integer,Integer> ItemSet ){
		double  threshold=support*basketSize;
		//System.out.println("support is: "+support+" basket size is: "+basketSize+" threshold is: "+threshold);
		HashMap <Integer,Integer> FrequentItemSetLevel1=new HashMap <Integer,Integer>();
		for(Integer name:ItemSet.keySet()){
			if(ItemSet.get(name)>threshold){
				FrequentItemSetLevel1.put(name, ItemSet.get(name));
			}
		}
		return FrequentItemSetLevel1;
	}
}
