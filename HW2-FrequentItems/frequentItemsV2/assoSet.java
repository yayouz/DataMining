package frequentItemsV2;


import java.util.HashSet;

public class assoSet {
	private HashSet<Integer> assoKeyMap;
	private HashSet<Integer> assoItemMap;
	private double c;
	public assoSet(HashSet<Integer> assoKeyMap,HashSet<Integer>  assoItemMap,double c){
		this.assoKeyMap=assoKeyMap;
		this.assoItemMap= assoItemMap;
		this.c=c;
	}
	
	public HashSet<Integer> getKey(){
		return assoKeyMap;
	}
	
	public HashSet<Integer> getItem(){
		return assoItemMap;
	}
	
	public double getConfidence(){
		return c;
	}
	public String getString(){
		return "The association feature is "+assoKeyMap+" -> "+assoItemMap+", c is: "+c;
	}
	
}
