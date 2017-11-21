package frequentItemsV2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Association {

	private List <assoSet> assoItemMap;
	
	public  Association(){
		this.assoItemMap=new ArrayList < assoSet>();
	}
	
	public List < assoSet> Confidence(double s,double c,
					HashMap<Integer, Integer> freItemMap,
					HashMap <HashSet<Integer>,Integer> secfreItemMap,
					HashMap <HashSet<Integer>,Integer> thirfreItemMap){
		for(HashSet<Integer> set:secfreItemMap.keySet()){
			for(Integer i:set){
				if(((double)secfreItemMap.get(set)/freItemMap.get(i))>=c){
					HashSet<Integer> SetTemp=new HashSet<Integer>();
					SetTemp.addAll(set);
					SetTemp.remove(i);
					HashSet<Integer> key=new HashSet<Integer>();
					key.add(i);
					assoItemMap.add(new assoSet(key,SetTemp,((double)secfreItemMap.get(set)/freItemMap.get(i))) );
					//System.out.println("The association feature is "+key+" -> "+SetTemp+" c is: "+((double)secfreItemMap.get(set)/freItemMap.get(i)));	
				}
			}
		}
		for(HashSet<Integer> set:thirfreItemMap.keySet()){
			for(Integer i:set){
				HashSet<Integer> SetTemp=new HashSet<Integer>();
				SetTemp.addAll(set);
				HashSet<Integer> key=new HashSet<Integer>();
				key.add(i);
				SetTemp.remove(i);
				if(((double)thirfreItemMap.get(set)/freItemMap.get(i))>=c){
					assoItemMap.add(new assoSet(key,SetTemp,((double)thirfreItemMap.get(set)/freItemMap.get(i))));
					//System.out.println("The association feature is "+key+" -> "+SetTemp+" c is: "+((double)thirfreItemMap.get(set)/freItemMap.get(i)));	
				}

				if(((double)thirfreItemMap.get(set)/secfreItemMap.get(SetTemp))>=c){
					assoItemMap.add(new assoSet(SetTemp,key,((double)thirfreItemMap.get(set)/secfreItemMap.get(SetTemp))));
					//System.out.println("The association feature is "+SetTemp+" -> "+key+" c is: "+((double)thirfreItemMap.get(set)/ secfreItemMap.get(SetTemp)));
				}
			}
		}
		return assoItemMap;
	}
}
