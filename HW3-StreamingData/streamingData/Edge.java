package streamingData;

public class Edge {
   private int node1;
   private int node2;
   public Edge(int n1,int n2){
	   this.node1=n1;
	   this.node2=n2;	   
   }
   
   public int getNode1(){
	   return node1;
   }
   
   public int getNode2(){
	   return node2;
   }
}
