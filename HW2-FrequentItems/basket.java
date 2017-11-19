package frequentItems;

import java.util.ArrayList;
import java.util.List;


public class basket {
   public List<String> items;
   public Integer ID;
   
   public basket(){
	   this.items=new ArrayList<String>();
	   this.ID=0;
   }
	
	public void addItem(Integer id,String Allitems){
		for (String item: Allitems.split(" ")){
			this.items.add(item);
        }
		this.ID=id;
	}
}
