import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class BetweenPasses {
	private int index;
	private HashMap<Integer, Integer> freItemMap;
	private int[] freItems;
	private HashMap<HashSet<Integer>, Integer> secfreItemMap;
	
	// initialization for singleton (first pass)
	public BetweenPasses(int[] singleton, int s, 
			HashMap<Integer, Integer> fwd_map) {
		index = 1;
		freItemMap = new HashMap<Integer, Integer>();
		freItems = new int[singleton.length];
		
		for (int i = 0; i < singleton.length; i++) {
			if (singleton[i] >= s) {
				freItems[i] = index++;
				freItemMap.put(fwd_map.get(i), singleton[i]);
			}
		}
	}
	
	// initialization for doubleton (second pass)
	public BetweenPasses(int[] doubleton, int s, 
			Map<Integer, HashSet<Integer>> fwd_map) {
		index = 1;
		secfreItemMap = new HashMap<HashSet<Integer>, Integer>();
		freItems = new int[doubleton.length];
		
		for (int i = 0; i < doubleton.length; i++) {
			if (doubleton[i] >= s) {
				freItems[i] = index++;
				secfreItemMap.put(fwd_map.get(i), doubleton[i]);
			}
		}
	}
	
	public int[] GetFreItemTable() {
		return freItems;
	}	
	
	public HashMap<Integer, Integer> GetFreItemMap() {
		return freItemMap;
	}	
	
	public HashMap<HashSet<Integer>, Integer> GetSecFreItemMap() {
		return secfreItemMap;
	}	
}
