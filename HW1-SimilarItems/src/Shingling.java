import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class Shingling {

	public Shingling() {
		
	}
	
	public HashSet<Integer> ShingleHash(String text, int k) {
		List<Integer> shingles = new ArrayList<>();
		text = text.replaceAll("[\t\n\r]", " ");
		
		// get a fixed window of string and store it in List
		for (int idx = 0; idx <= text.length() - k; idx++) {
			String cur_shingle = text.substring(idx, idx + k);
			shingles.add(cur_shingle.hashCode());
		}
		
		// sort List and put it into a Set
		Collections.sort(shingles);
		return new LinkedHashSet<>(shingles);
	}
	
}
