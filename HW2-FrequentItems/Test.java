package frequentItems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		String filename="d:/data/T10I4D100K.dat";
		
		filter ItemFilter=new filter();
		Construct cons=new Construct();
		//initial basketList//
		List<HashSet<String>> basketList;
		basketList = initialBasket(filename);
		double support=0.02;		
		System.out.println("The basket size is: "+basketList.size()+" support is: "+support);
		//initial All items set//
		HashMap <String,Integer> itemSet=initialItemSet(basketList);
		System.out.println(itemSet.size());
		
		HashMap <String,Integer> frequentSingleItem=ItemFilter.filteLevel1(support,basketList.size(),itemSet);
		
		
		//using itemSet instead of itemName//
		HashMap <HashSet<String>,Integer> frequentItemSet=new HashMap <HashSet<String>,Integer>();
		for(String name:frequentSingleItem.keySet()){
			HashSet<String> Set=new HashSet<String>();
			Set.add(name);
			frequentItemSet.put(Set,frequentSingleItem.get(name));
		}
		///Test1//
		System.out.println("The number of frequent single itemsets is: "+frequentItemSet.size());
		/*for(HashSet<String> set:frequentItemSet.keySet()){
				System.out.println(set+" : "+frequentItemSet.get(set));
	    }*/
		
		///generate two item set///
		System.out.println("---------total pairs of items consist of frequent single item------");
		HashMap <HashSet<String>,Integer> PairItems=cons.generate(frequentItemSet);
		System.out.println("The number of pair items set is: "+ PairItems.size());
		
		///Test2//
		//System.out.println("The number of pair set is: "+ CountedPairItems.size());
		/*for(HashSet<String> set:PairItems.keySet()){		
			System.out.println(set+" : "+PairItems.get(set));
	  	}*/
		System.out.println("---------frequent paired item set------");
		HashMap <HashSet<String>,Integer> frequentPairItem=ItemFilter.filte(support, PairItems,basketList);
		for(HashSet<String> set:frequentPairItem.keySet()){
			System.out.println(set+" : "+frequentPairItem.get(set));
		}
		
	}
	
	public static List<HashSet<String>> initialBasket(String fileName) {
		List<HashSet<String>> basketList=new ArrayList<HashSet<String>>();
		HashSet<String> singleBasket=new HashSet<String>();
		File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	singleBasket=addItem(tempString);
            	basketList.add(singleBasket);

            }
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return basketList;
    }
	
	public static HashSet<String> addItem(String Allitems){
		HashSet<String> singleBasket=new HashSet<String>();
		for (String item: Allitems.split(" ")){
			singleBasket.add(item);
        }
		return singleBasket;
	}
	
	public static HashMap <String,Integer> initialItemSet(List<HashSet<String>> basketList) {
		HashMap <String,Integer> itemSet=new HashMap <String,Integer>();
		
		for(int id=0;id<basketList.size();id++){
			for(String item:basketList.get(id)){
				if(!itemSet.containsKey(item)){					
					itemSet.put(item,1);
				}
				else{
					itemSet.put(item,itemSet.get(item)+1);
				}
			}
		}
		return itemSet;
	}
}
