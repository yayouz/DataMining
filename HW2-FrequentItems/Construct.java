package frequentItems;


import java.util.HashMap;

public class Construct {
	private HashMap <Integer,itemSet> allItemSet;
	
	public Construct(){
		this.allItemSet=new HashMap <Integer,itemSet>();
	}
	
	public  HashMap <Integer,itemSet> generate(HashMap <Integer,itemSet> item){
		itemSet Set=new itemSet();
		int idNew=1;
		for(int id1=1;id1<item.size();id1++){
			for(int id2=id1+1;id2<=item.size();id2++){
				Set=new itemSet();
				Set.items.addAll(item.get(id1).items);
				Set.items.addAll(item.get(id2).items);
				System.out.println(idNew+": "+Set.items);
				allItemSet.put(idNew, Set);
				idNew++;
			}
		}
		return allItemSet;
	}
}
