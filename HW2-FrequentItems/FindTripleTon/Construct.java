package frequentItemsV2;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Construct {
	private HashMap <HashSet<Integer>,Integer> allItemSet;
	
	public Construct(){
		this.allItemSet=new HashMap <HashSet<Integer>,Integer>();
	}
	
	public  HashMap<HashSet<Integer>, Integer> generate(HashMap<HashSet<Integer>, Integer> itemList,int level){
		HashSet<Integer> Set=new HashSet<Integer>();
		Set <HashSet<Integer>> KeySet=itemList.keySet();
		List <HashSet<Integer>> KeyList=new ArrayList<HashSet<Integer>>(KeySet);
		for(int id1=0;id1<KeySet.size()-1;id1++ ){
			for(int id2=id1+1;id2<KeySet.size();id2++){
				Set=new HashSet<Integer>();
				Set.addAll(KeyList.get(id1));
				Set.addAll(KeyList.get(id2));
				//System.out.println("level "+level+" elements"+id1+" & "+id2+" :"+Set);
				if(Set.size()==level){
					allItemSet.put(Set,0);
				}
				else{
					for(Integer rm:KeyList.get(id1)){
						Set=new HashSet<Integer>();
						Set.addAll(KeyList.get(id1));
						Set.addAll(KeyList.get(id2));
						Set.remove(rm);
						//System.out.println(" remove"+rm+" : "+Set);
						allItemSet.put(Set,0);
						
					}
					for(Integer rm:KeyList.get(id2)){
						Set=new HashSet<Integer>();
						Set.addAll(KeyList.get(id1));
						Set.addAll(KeyList.get(id2));
						Set.remove(rm);
						//System.out.println("level "+level+" remove"+rm+" : "+Set);
						allItemSet.put(Set,0);
						//System.out.println(" allItemSet has "+allItemSet);
					}
					
				}

			}
		}
		return allItemSet;
	}
}