import java.util.HashMap;
import java.util.HashSet;

public class CreateMap {
	
	public CreateMap() {
		
	}

	// create map: secondCount -> hashvalue of item pairs
	public HashMap<Integer, HashSet<Integer>> GetMap(int[] secondCount, 
			int[] freItems, int n, HashMap<Integer, Integer> fwd_map) {
		HashMap<Integer, HashSet<Integer>> secfreItemMap = new HashMap<Integer, HashSet<Integer>>();
		
		for (int k = 0; k < secondCount.length; k++) {
			int i = n - 2;
			int row = 1;
			while (i < k) {
				row++;
				i += n - row;
			}
			int ii = getArrayIndex(freItems, row);
			int jj = getArrayIndex(freItems, n - i + k);
			HashSet<Integer> curKey = new HashSet<Integer>();
			curKey.add(fwd_map.get(ii)); curKey.add(fwd_map.get(jj));
			secfreItemMap.put(secondCount[k], curKey);
		}
		
		return secfreItemMap;
	}
	
	// help function for obtaining index given a value in an array
	public int getArrayIndex(int[] arr, int value) {

        int k = 0;
        for (int i = 0; i < arr.length; i++){

            if (arr[i] == value){
                k = i;
                break;
            }
        }
        return k;
	}

}
