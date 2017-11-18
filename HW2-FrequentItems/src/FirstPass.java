import java.util.ArrayList;
import java.util.HashMap;

public class FirstPass {
	private int numUnique;
	private HashMap<Integer, Integer> fwd_map;
	private HashMap<Integer, Integer> bck_map;
	
	public FirstPass(ArrayList<Integer> unList) {
		numUnique = unList.size();
		fwd_map = new HashMap<Integer, Integer>();
		bck_map = new HashMap<Integer, Integer>();
		int index = 0;
		for (int i : unList) {
			fwd_map.put(index, i);
			bck_map.put(i, index);
			index++;
		}
	}
	
	// get map: index -> hash value of items
	public HashMap<Integer, Integer> GetFwdMap() {
		return fwd_map;
	}
	
	// get map: hash value -> index of items
	public HashMap<Integer, Integer> GetBckMap() {
		return bck_map;
	}
	
	// count occurances
	public int[] FirstCount(ArrayList<ArrayList<Integer>> basketSet) {
		int[] count = new int[numUnique];
		
		for (ArrayList<Integer> basket : basketSet) {
			for (int item: basket) {
				count[bck_map.get(item)]++;
			}
		}

		return count;
	}

}
