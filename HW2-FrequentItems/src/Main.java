import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		double s = 0.01;
		String data_path = "/Users/txzhao/Desktop/HW2-FrequentItems/T10I4D100K.dat";
		
		// TEST: data read-in 
		DataReader dr = new DataReader(data_path);
		ArrayList<ArrayList<Integer>> basketSet = new ArrayList<ArrayList<Integer>>();
		basketSet = dr.Read();
		Set<Integer> un = dr.getUnique();
		
		// printout
		System.out.println("number of unique items: " + un.size());
		System.out.println("number of baskets: " + basketSet.size());
		System.out.println("------ data read-in done! ------");
		System.out.println();
		
		// TEST: first pass
		ArrayList<Integer> unList = new ArrayList<Integer>();
		unList.addAll(un);
		FirstPass fp = new FirstPass(unList);
		
		// printout
		System.out.println("Mapping index -> hashvalue of items:");
		System.out.println(fp.GetFwdMap());
		System.out.println("Mapping hashvalue -> index of items:");
		System.out.println(fp.GetBckMap());
		System.out.println("Counts of occurances:");
		System.out.println(Arrays.toString(fp.FirstCount(basketSet)));
		System.out.println("------ first pass done! ------");
		System.out.println();
		
		// TEST: between passes
		BetweenPasses bp = new BetweenPasses(fp.FirstCount(basketSet), 
				(int) Math.round(s*basketSet.size()), fp.GetFwdMap());
		int[] freItems = bp.GetFreItemTable();
		HashMap<Integer, Integer> freItemMap = bp.GetFreItemMap();
		int maxIdx = freItemMap.size();
		
		// printout
		System.out.println("frequent singletons (size: " + freItemMap.size() + "):");
		System.out.println(freItemMap);
		System.out.println("frequent-items table:");
		System.out.println(Arrays.toString(freItems));
		System.out.println("------ between passes done! ------");
		System.out.println();
		
		//TEST: second pass
		SecondPass sp = new SecondPass();
		int[] secondCount = sp.Count(basketSet, freItems, fp.GetBckMap(), maxIdx);
		HashMap<Integer, HashSet<Integer>> setMap = new CreateMap().GetMap(
				secondCount, freItems, maxIdx, fp.GetFwdMap());
		//System.out.println(setMap);
		BetweenPasses bp1 = new BetweenPasses(secondCount, 
				(int) Math.round(s*basketSet.size()), setMap);
		HashMap<HashSet<Integer>, Integer> secfreItemMap = bp1.GetSecFreItemMap();
		
		System.out.println("frequent doubletons (size: " + secfreItemMap.size() + "):");
		System.out.println(secfreItemMap);
		//System.out.println(Arrays.toString(secondCount));
		System.out.println("------ second pass done! ------");
		System.out.println();
		
		
	}

}
