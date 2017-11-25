package streamingData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		String path = "/Users/txzhao/Desktop/HW3-StreamingData/streamingData/Douban-dataset/data/edges.csv";
		FileInputStream fis = new FileInputStream(path);
		int time=0;
		int M=5000;
		ArrayList<Integer> allCounts = new ArrayList<Integer>();
		Triest triest=new Triest(time,M);
        Scanner scanner = new Scanner(fis);
        scanner.useDelimiter("\\n");
        while(scanner.hasNextLine()){
        		Scanner lineInt = new Scanner(scanner.nextLine());
        		lineInt.useDelimiter(",");
            ArrayList <Integer>node=new ArrayList<Integer>();
            while(lineInt.hasNextInt()) {
        		node.add(lineInt.nextInt());
           }
            Edge edge=new Edge(node.get(0),node.get(1));
            triest.streaming(edge);
            allCounts.add(triest.getGlobalCount());
            System.out.println("global count: "+triest.getGlobalCount());
            //System.out.println("local count: "+triest.getLocalCount());
            lineInt.close();
        }
        
        scanner.close();
        
        // save global count in a csv file for plots and evaluation
        File csvFile = new File("/Users/txzhao/Desktop/counts.csv");
        try (PrintWriter csvWriter = new PrintWriter(new FileWriter(csvFile));){
          for(int count : allCounts){
            csvWriter.println(count);
          }
        } catch (IOException e) {
            //Handle exception
            e.printStackTrace();
        }
	}
}
