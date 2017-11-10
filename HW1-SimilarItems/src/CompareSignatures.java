package similarItems;

import java.util.List;

public class CompareSignatures {

	public CompareSignatures(){
		super();
	}
	
	public double similar(List<Integer> s1, List<Integer> s2){
		if (s1.isEmpty() && s2.isEmpty()) return 0;
		int intersect=0;
		for(int i=0;i<s1.size();i++){
			if(s1.get(i)==s2.get(i)){
			intersect+=1;
			}
		}
		
		return (double)intersect/s1.size();
		
	}
	
}
