package frequentItemsV2;

import java.util.ArrayList;
import java.util.HashMap;

public class SecondPass {

	public SecondPass() {
		
	}
	
	// count occurance for frequent item pairs; store counts using triangular array
	public int[] Count(ArrayList<ArrayList<Integer>> basketSet, 
			int[] freItems, HashMap<Integer, Integer> bckMap, int n) {
		
		if (n < 2) return null;
		
		int[] itemPairs = new int[(n - 1)*n/2];
		for (ArrayList<Integer> basket : basketSet) {
			ArrayList<Integer> curFreItems = new ArrayList<Integer>();
			for (int item : basket) {
				if (freItems[bckMap.get(item)] != 0) curFreItems.add(item);
			}
			
			for (int i = 0; i < curFreItems.size(); i++) {
				for (int j = i + 1; j < curFreItems.size(); j++) {
					int ii = freItems[bckMap.get(curFreItems.get(i))];
					int jj = freItems[bckMap.get(curFreItems.get(j))];
					itemPairs[(ii - 1)*(2*n - ii)/2 + jj - ii - 1]++;
				}
			}
		}
		
		return itemPairs;
	}
}