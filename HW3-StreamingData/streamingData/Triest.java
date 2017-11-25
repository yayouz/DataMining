package streamingData;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Triest {
	List<Edge> sampleSet;
	int t;
	int M;
	int tau;
	Hashtable<Integer, Set<Integer>> neighbor;
	Hashtable<Integer, Integer> tau_c;
	Random rdn;
	
	public Triest(int time,int M){
		sampleSet=new ArrayList<Edge>();
		this.t=time;
		this.M=M;
		this.tau = 0;
		this.neighbor = new Hashtable<Integer, Set<Integer>>();
		this.tau_c = new Hashtable<Integer, Integer>();
		rdn=new Random();
	}
	
	public List<Edge> streaming(Edge e){
		this.t++;	
		int n1 = e.getNode1();
		int n2 = e.getNode2();
		System.out.println("The new edge is : "+n1+" , "+n2);
		UpdateCounters(0, e);
		if(sampleEdge(e)){
			sampleSet.add(e);
			if (neighbor.containsKey(n1)) {
				Set<Integer> tmpSet = neighbor.get(n1);
				tmpSet.add(n2);
				neighbor.put(n1, tmpSet);
			}
			else {
				Set<Integer> tmpSet = new HashSet<Integer>();
				tmpSet.add(n2);
				neighbor.put(n1, tmpSet);
				tau_c.put(n1, 0);
			}
			
			if (neighbor.containsKey(n2)) {
				Set<Integer> tmpSet = neighbor.get(n2);
				tmpSet.add(n1);
				neighbor.put(n2, tmpSet);
			}
			else {
				Set<Integer> tmpSet = new HashSet<Integer>();
				tmpSet.add(n1);
				neighbor.put(n2, tmpSet);
				tau_c.put(n2, 0);
			}
		}
		return sampleSet;
	}
	
	public boolean sampleEdge(Edge e){
		if(this.t<=this.M){
			return true;
		}else if(FlipBiasedCoin((double)M/(double)t)){
			Edge randomEdge=(sampleSet.get(rdn.nextInt(sampleSet.size())));
			System.out.println("removed edge: "+randomEdge.getNode1()+" , "+randomEdge.getNode2());
			sampleSet.remove(randomEdge);
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean FlipBiasedCoin(double p){
		//Random localran=new Random();
		//double localrdn=localran.nextDouble();
		double localrdn=ThreadLocalRandom.current().nextDouble();
		//System.out.println("The random number is :"+localrdn+" p is: "+p);
		if(localrdn<p) //if large than p is heads
			return true;
		else
			return false;
		
	}
	
	public void UpdateCounters(int op, Edge e) {
		Set<Integer> intersect = new HashSet<Integer>();
		if (neighbor.containsKey(e.getNode1()) && neighbor.containsKey(e.getNode2())) {
			intersect = new HashSet<Integer>(neighbor.get(e.getNode1()));
			Set<Integer> tmp_n2 = neighbor.get(e.getNode2());
			intersect.retainAll(tmp_n2);
		}
		double delta = Math.max(1.0, (double) ((t - 1)*(t - 2))/(double) (M*(M - 1)));
		
		for (int c : intersect) {
			int tmp_c = tau_c.get(c);
			int tmp_1 = tau_c.get(e.getNode1());
			int tmp_2 = tau_c.get(e.getNode2());
			
			if (op == 0) {
				tau += delta;
				tmp_c += delta;
				tmp_1 += delta;
				tmp_2 += delta;
				
				tau_c.put(c, tmp_c);
				tau_c.put(e.getNode1(), tmp_1);
				tau_c.put(e.getNode2(), tmp_2);
			}
			// this part practically unused in Triest-IMPR, could be erased
			else {
				tau--;
				tau_c.put(c, --tmp_c);
				tau_c.put(e.getNode1(), --tmp_1);
				tau_c.put(e.getNode2(), --tmp_2);
			}
			
		}
		
	}
	
	public int getGlobalCount() {
		return tau;
	}
	
	public Hashtable<Integer, Integer> getLocalCount() {
		return tau_c;
	}
}
