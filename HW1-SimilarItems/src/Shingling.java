package similarItems;

import java.util.HashMap;
import java.util.Random;

public class Shingling {

	public Shingling() {
		
	}
	
	public HashMap<Integer,String> ShingleHash(String text, int k) {
		HashMap<Integer,String> shingles = new HashMap< Integer,String>();
		text = text.replaceAll("[\t\n\r]", " ");
		System.out.println("original text is "+text+" text length is "+text.length());
		// get a fixed window of string and store it in List
		for (int idx = 0; idx <= text.length() - k; idx++) {
			String cur_shingle = text.substring(idx, idx + k);
			shingles.put(cur_shingle.hashCode(),cur_shingle);
		}
		
		// sort List and put it into a Set
		//Collections.sort(shingles);
		return shingles;
	}
	
}
