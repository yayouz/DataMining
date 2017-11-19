package frequentItems;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Construct {
	private HashMap <HashSet<String>,Integer> allItemSet;
	
	public Construct(){
		this.allItemSet=new HashMap <HashSet<String>,Integer>();
	}
	
	public  HashMap <HashSet<String>,Integer> generate(HashMap <HashSet<String>,Integer> itemList){
		HashSet<String> Set=new HashSet<String>();
		Set <HashSet<String>> KeySet=itemList.keySet();
		List <HashSet<String>> KeyList=new ArrayList<HashSet<String>>(KeySet);
		for(int id1=0;id1<KeySet.size()-1;id1++ ){
			for(int id2=id1+1;id2<KeySet.size();id2++){
				Set=new HashSet<String>();
				Set.addAll(KeyList.get(id1));
				Set.addAll(KeyList.get(id2));
				allItemSet.put(Set,0);

			}
		}
		return allItemSet;
	}
}
