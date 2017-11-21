package frequentItemsV2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class DataReader {
	private String path;
	private Set<Integer> unique;

	public DataReader(String path) {
		this.path = path;
		unique = new TreeSet<>();
	}
	
	// read data line by line and count unique items
	public ArrayList<ArrayList<Integer>> Read() throws FileNotFoundException{
		ArrayList<ArrayList<Integer>> basketSet = new ArrayList<ArrayList<Integer>>();
		FileInputStream fis = new FileInputStream(path);
        Scanner scanner = new Scanner(fis);
        
        scanner.useDelimiter("\\n");
        while(scanner.hasNextLine()){
        		ArrayList<Integer> basket = new ArrayList<Integer>();
            Scanner lineInt = new Scanner(scanner.nextLine());
            lineInt.useDelimiter("\\s");
            
            while(lineInt.hasNextInt()) {
            		int curInt = lineInt.nextInt();
            		basket.add(curInt);
            		unique.add(curInt);
            }
            basketSet.add(basket);
            lineInt.close();
        }
        
        scanner.close();
        return basketSet;
	}
	
	public Set<Integer> getUnique(){
		return unique;
	}

}