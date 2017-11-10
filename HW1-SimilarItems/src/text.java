package similarItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class text {
	public List<Integer> Boolean;
	public List<Integer> signatures;
	public Set<Integer> s;
	public text(Set<Integer> s1){
		this.Boolean=new ArrayList<Integer>();
		this.signatures=new ArrayList<Integer>();
		this.s=s1;
	}
	public void addBoolean(Integer i){
		this.Boolean.add(i);
	}
	
	public void addSignature(Integer i){
		this.signatures.add(i);
	}
	
	public Integer get(Integer i){
		return this.Boolean.get(i);
	}
	
}
