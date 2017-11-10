package similarItems;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MinHashing {
	private Integer MinHash;
	private text set1;
	private text set2;
	private Set<Integer> AllShingle;
	public MinHashing(Set<Integer> s1,Set<Integer> s2){
		MinHash=0;
		this.set1=new text(s1);
		this.set2=new text(s2);
		this.AllShingle=new HashSet<Integer>(s1);
		AllShingle.addAll(s2);
	}
	
	//From Sets to Boolean Matrices
	private void BooleanSet(){
		
		int union = AllShingle.size(); 
		for(Integer key : AllShingle){
			if(set1.s.contains(key)){
				set1.addBoolean(1);
			}else{
				set1.addBoolean(0);
			}
			if(set2.s.contains(key)){
				set2.addBoolean(1);
			}else{
				set2.addBoolean(0);
			}
			
		}
	}
	
	//MinHashing using Permutations
	public HashMap<Integer,text> minhashFunction (int k){
		int union=AllShingle.size();
		Iterator temp;
	
		int index;
		List<Integer> random=new ArrayList<Integer>();
		BooleanSet();
		HashMap<Integer,text> Matrix=new HashMap<Integer,text>();
		for(int i=0;i<union;i++){
			random.add(i);
		}
		for(int i=0;i<k;i++){
			
			for(int j=0;j<union;j++){
				index=random.get(j);
				if(set1.get(index)==1){
					set1.addSignature(j+1);
					break;
				}
			}
			
			for(int j=0;j<union;j++){
				index=random.get(j);
				if(set2.get(index)==1){
					set2.addSignature(j+1);
					break;
				}
			}
			Collections.shuffle(random);
		}
		Matrix.put(1,set1);
		Matrix.put(2,set2);
		return Matrix;
	}
	
}
