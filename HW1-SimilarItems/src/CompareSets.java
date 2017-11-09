import java.util.HashSet;
import java.util.Set;

public class CompareSets {

	public CompareSets() {
		
	}
	
	public double ComputeJaccard(Set<Integer> s1, Set<Integer> s2) {
		if (s1.isEmpty() && s2.isEmpty()) return 0;
		
		// intersection of set 1 and 2
		Set<Integer> tmp1 = new HashSet<Integer>(s1);
		tmp1.retainAll(s2);
		int intersect = tmp1.size();
		
		// union of set 1 and 2
		Set<Integer> tmp2 = new HashSet<Integer>(s1);
		tmp2.addAll(s2);
		int union = tmp2.size();
		
		// calculate Jaccard similarity
		return (double) intersect/union;
	}
}
