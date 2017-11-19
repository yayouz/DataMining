package frequentItems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Test {
	
	public static void main(String[] args) {
		String filename="d:/data/T10I4D100K.dat";
		Integer support=3000;
		Construct cons=new Construct();
		filter ItemFilter=new filter();
		//initial basketList//
		HashMap <Integer,basket> basketList;
		basketList = initialBasket(filename);
		System.out.println(basketList.size());
		//initial All items set//
		HashMap <String,Integer> itemSet=initialItemSet(basketList);
		System.out.println(itemSet.size());
		
		HashMap <String,Integer> frequentSingleItem=ItemFilter.filteLevel1(support,itemSet);
		System.out.println("The number of frequent single itemsets is: "+frequentSingleItem.size());
		int i=1;
		
		//using itemSet instead of itemName//
		HashMap <Integer,itemSet> frequentItemSet=new HashMap <Integer,itemSet>();
		for(String name:frequentSingleItem.keySet()){
			itemSet Set=new itemSet();
			Set.add(name,frequentSingleItem.get(name));
			frequentItemSet.put(i,Set);
			i++;
		}
		///Test1//
		/*for(Integer id:frequentItemSet.keySet()){
				System.out.println(frequentItemSet.get(id).items+": "+frequentItemSet.get(id).frequency);
	    }*/
		
		///generate two item set///
		System.out.println("---------total pairs of items consist of frequent single item------");
		HashMap <Integer,itemSet> PairItems=cons.generate(frequentItemSet);
		

		System.out.println("---------frequent paired item set------");
		HashMap <Integer,itemSet> frequentPairItem=ItemFilter.filte(1000, PairItems,basketList);
		///Test2//
		System.out.println("The number of frequent pair set is: "+ frequentPairItem.size());
		for(Integer id:frequentPairItem.keySet()){
			System.out.println(frequentPairItem.get(id).items+": "+frequentPairItem.get(id).frequency);
		}
		
	}
	
	public static HashMap <Integer,basket> initialBasket(String fileName) {
		HashMap <Integer,basket> basketList=new HashMap <Integer,basket>();
        basket B=new basket();
		File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int id=1;
            while ((tempString = reader.readLine()) != null) {
            	B=new basket();
            	B.addItem(id, tempString);
            	basketList.put(id,B);
            	id++;
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
	
	public static HashMap <String,Integer> initialItemSet(HashMap <Integer,basket> basketList) {
		HashMap <String,Integer> itemSet=new HashMap <String,Integer>();
		
		for(basket B:basketList.values()){
			for(String item:B.items){
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
