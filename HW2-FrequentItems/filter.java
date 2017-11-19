package frequentItems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class filter {
	private HashMap <HashSet<String>,Integer> FrequentItemSet;
	
	public filter(){
		this.FrequentItemSet=new HashMap <HashSet<String>,Integer>();
	}
	
	public HashMap <HashSet<String>,Integer> filte(double support, HashMap <HashSet<String>,Integer> Set,List<HashSet<String>> basketList )
	{	
		double threshold=support*basketList.size();
		HashSet<String> singleBasket=new HashSet<String>();
		Boolean exist=true; 
		int frequency=0;
		
		for(HashSet<String> item: Set.keySet()){
			frequency=0;			//System.out.println("the feature of set is : "+item.items+" frequency is: "+item.frequency);
			for(int id=0;id<basketList.size();id++){
				singleBasket=basketList.get(id);
				exist=true; 
				Iterator <String> It=item.iterator();
				for(int n=0;n<item.size();n++){
					if(!singleBasket.contains(It.next())){
						exist=false;
					}
				}
				if(exist==true){
					frequency=frequency+1;
				}
			}
			//System.out.println("the feature of set is : "+item.items+" new frequency is: "+item.frequency);
			if(frequency>threshold){
				this.FrequentItemSet.put(item, frequency);
			}
		}
		
		return this.FrequentItemSet;
	}
	
	public HashMap <String,Integer> filteLevel1(double  support,Integer basketSize,HashMap <String,Integer> ItemSet ){
		double  threshold=support*basketSize;
		//System.out.println("support is: "+support+" basket size is: "+basketSize+" threshold is: "+threshold);
		HashMap <String,Integer> FrequentItemSetLevel1=new HashMap <String,Integer>();
		for(String name:ItemSet.keySet()){
			if(ItemSet.get(name)>threshold){
				FrequentItemSetLevel1.put(name, ItemSet.get(name));
			}
		}
		return FrequentItemSetLevel1;
	}

}
