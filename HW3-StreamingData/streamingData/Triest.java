package streamingData;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Triest {
	List <Edge> sampleSet;
	int t;
	int M;
	Random rdn;
	public Triest(int time,int M){
		sampleSet=new ArrayList<Edge>();
		this.t=time;
		this.M=M;
		rdn= new Random();
	}
	
	public List<Edge> streaming(Edge e){
		this.t++;	
		System.out.println("The new edge is : "+e.getNode1()+" , "+e.getNode2());
		if(sampleEdge(e)){
			sampleSet.add(e);
		}
		return sampleSet;
	}
	
	public boolean sampleEdge(Edge e){
		if(this.t<=this.M){
			return true;
		}else if(FlipBiasedCoin((double)M/t)){
			Edge randomEdge=(sampleSet.get(rdn.nextInt(sampleSet.size())));
			System.out.println("removed edge : "+randomEdge.getNode1()+" , "+randomEdge.getNode2());
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
		if(localrdn>p) //if large than p is heads
			return true;
		else
			return false;
		
	}
}
