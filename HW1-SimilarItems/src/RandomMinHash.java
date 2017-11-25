import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RandomMinHash {
	private Integer n;
	private Integer n_shingles;
	private List<Integer> a;
	private List<Integer> b;
	private Set<Integer> AllShingle;
	private text set;
	
	public RandomMinHash(int n, Set<Integer> AllShingle, Set<Integer> s, List<Integer> a, List<Integer> b) {
		this.n = n;
		this.n_shingles = AllShingle.size();
		this.a = a;
		this.b = b;
		this.AllShingle = AllShingle;
		this.set = new text(s);
	}
	
	private void BooleanSet(){ 
		for(Integer key : AllShingle){
			if(set.s.contains(key)){
				set.addBoolean(1);
			}else{
				set.addBoolean(0);
			}
		}
	}
	
	public List<Integer> Signature() {
		List<Integer> sign = new ArrayList<Integer>();
		int row_id = 0;
		
		for (int i = 0; i < n; i++) sign.add(Integer.MAX_VALUE);
		BooleanSet();
		for (int c : set.Boolean) {
			if(c == 1) {
				for (int i = 0; i < n; i++) {
					int new_sign = Math.min(sign.get(i), ((a.get(i)*row_id + b.get(i)) % n_shingles));
					sign.set(i, new_sign);
				}
			}
			row_id++;
		}
		
		return sign;
	}

}
