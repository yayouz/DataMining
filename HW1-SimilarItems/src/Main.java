//package HW1-imilarItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		String route="src/dataset/";
		// Shingling test		
		Shingling s = new Shingling();
		//String text1 = "abdabc";
		//String text2 = "abd ch eg s dc";
		//String text3 = "abd ch eg s gf";
		//String text4 = "";
		String text1 = readFile(route+"bathroom_bestwestern_hotel_sfo.1.gold");
		String text2 = readFile(route+"bathroom_bestwestern_hotel_sfo.2.gold");
		String text3 = readFile(route+"location_holiday_inn_london.1.gold");
		String text4 = readFile(route+"location_holiday_inn_london.2.gold");
		String text5 = readFile(route+"comfort_honda_accord_2008.1.gold");
		String text6 = readFile(route+"comfort_honda_accord_2008.2.gold");
		
		HashMap<Integer,String> shingle1 = s.ShingleHash(text1, 4);
		HashMap<Integer,String> shingle2 = s.ShingleHash(text2, 4);
		HashMap<Integer,String> shingle3 = s.ShingleHash(text3, 4);
		HashMap<Integer,String> shingle4 = s.ShingleHash(text4, 4);
		HashMap<Integer,String> shingle5 = s.ShingleHash(text5, 4);
		HashMap<Integer,String> shingle6 = s.ShingleHash(text6, 4);
		
		System.out.println("--- Shingle hash values of text 1 ---");
		System.out.println(shingle1);
		System.out.println("--- Shingle hash values of text 2 ---");
		System.out.println(shingle2);
		System.out.println("--- Shingle hash values of text 3 ---");
		System.out.println(shingle3);
		System.out.println("--- Shingle hash values of text 4 ---");
		System.out.println(shingle4); 
		System.out.println("--- Shingle hash values of text 5 ---");
		System.out.println(shingle5); 
		System.out.println("--- Shingle hash values of text 6 ---");
		System.out.println(shingle6); 
		
		// CompareSets test
		Set<Integer> set1 = shingle1.keySet();
		Set<Integer> set2 = shingle2.keySet();
		Set<Integer> set3 = shingle3.keySet();
		Set<Integer> set4 = shingle4.keySet();
		Set<Integer> set5 = shingle5.keySet();
		Set<Integer> set6 = shingle6.keySet();

		System.out.println("--- CompareSets test 1 ---");
		System.out.println(new CompareSets().ComputeJaccard(set1, set2));
		System.out.println("--- CompareSets test 2 ---");
		System.out.println(new CompareSets().ComputeJaccard(set1, set3));
		System.out.println("--- CompareSets test 3 ---");
		System.out.println(new CompareSets().ComputeJaccard(set1, set4));
		System.out.println("--- CompareSets test 4 ---");
		System.out.println(new CompareSets().ComputeJaccard(set1, set5));
		System.out.println("--- CompareSets test 5 ---");
		System.out.println(new CompareSets().ComputeJaccard(set1, set6));
		
//		HashMap<Integer,text> Matrix=new MinHashing(set1,set2).minhashFunction(50);
//		System.out.println("--- Signature test 1 ---");
//		System.out.println("Signature for text1"+Matrix.get(1).signatures );
//		System.out.println("Signature for text2"+Matrix.get(2).signatures );
//		
//		System.out.println("Similarity of signature for text1 and text2: "
//						+new CompareSignatures().similar(Matrix.get(1).signatures, Matrix.get(2).signatures) );
//		
//
//		Matrix=new MinHashing(set1,set3).minhashFunction(50);
//		System.out.println("--- Signature test 2 ---");
//		System.out.println("Signature for text1"+Matrix.get(1).signatures );
//		System.out.println("Signature for text3"+Matrix.get(2).signatures );
//		
//		System.out.println("Similarity of signature for text1 and text3: "
//						+new CompareSignatures().similar(Matrix.get(1).signatures, Matrix.get(2).signatures) );
//		
//		Matrix=new MinHashing(set1,set4).minhashFunction(50);
//		System.out.println("--- Signature test 3 ---");
//		System.out.println("Signature for text1"+Matrix.get(1).signatures );
//		System.out.println("Signature for text4"+Matrix.get(2).signatures );
//		
//		System.out.println("Similarity of signature for text1 and text4: "
//						+new CompareSignatures().similar(Matrix.get(1).signatures, Matrix.get(2).signatures) );
//	
//		Matrix=new MinHashing(set1,set5).minhashFunction(50);
//		System.out.println("--- Signature test 4 ---");
//		System.out.println("Signature for text1"+Matrix.get(1).signatures );
//		System.out.println("Signature for text5"+Matrix.get(2).signatures );
//		
//		System.out.println("Similarity of signature for text1 and text5: "
//						+new CompareSignatures().similar(Matrix.get(1).signatures, Matrix.get(2).signatures) );
//	
//		Matrix=new MinHashing(set1,set6).minhashFunction(50);
//		System.out.println("--- Signature test 5 ---");
//		System.out.println("Signature for text1"+Matrix.get(1).signatures );
//		System.out.println("Signature for text6"+Matrix.get(2).signatures );
//		
//		System.out.println("Similarity of signature for text1 and text6: "
//						+new CompareSignatures().similar(Matrix.get(1).signatures, Matrix.get(2).signatures) );
		
		
		Set<Integer> AllShingle = new HashSet<Integer>(set1);
		AllShingle.addAll(set2); AllShingle.addAll(set3); AllShingle.addAll(set4); AllShingle.addAll(set5); AllShingle.addAll(set6);
		
		Random rand = new Random();
		List<Integer> a = new ArrayList<Integer>();
		List<Integer> b = new ArrayList<Integer>();
		int n = 20;
		
		for (int i = 0; i < n; i++) {
			a.add(rand.nextInt(AllShingle.size()));
			b.add(rand.nextInt(AllShingle.size()));
		}
		
		RandomMinHash rmh1 = new RandomMinHash(n, AllShingle, set1, a, b);
		RandomMinHash rmh2 = new RandomMinHash(n, AllShingle, set2, a, b);
		RandomMinHash rmh3 = new RandomMinHash(n, AllShingle, set3, a, b);
		RandomMinHash rmh4 = new RandomMinHash(n, AllShingle, set4, a, b);
		RandomMinHash rmh5 = new RandomMinHash(n, AllShingle, set5, a, b);
		RandomMinHash rmh6 = new RandomMinHash(n, AllShingle, set6, a, b);
		
		System.out.println("Signature for text1: "+rmh1.Signature());
		System.out.println("Signature for text2: "+rmh2.Signature());
		System.out.println("Signature for text3: "+rmh3.Signature());
		System.out.println("Signature for text4: "+rmh4.Signature());
		System.out.println("Signature for text5: "+rmh5.Signature());
		System.out.println("Signature for text6: "+rmh6.Signature());
	
		System.out.println("Similarity of signature for text1 and text2: "
				+new CompareSignatures().similar(rmh1.Signature(), rmh2.Signature()) );
		System.out.println("Similarity of signature for text1 and text3: "
				+new CompareSignatures().similar(rmh1.Signature(), rmh3.Signature()) );
		System.out.println("Similarity of signature for text1 and text4: "
				+new CompareSignatures().similar(rmh1.Signature(), rmh4.Signature()) );
		System.out.println("Similarity of signature for text1 and text5: "
				+new CompareSignatures().similar(rmh1.Signature(), rmh5.Signature()) );
		System.out.println("Similarity of signature for text1 and text6: "
				+new CompareSignatures().similar(rmh1.Signature(), rmh6.Signature()) );
		
	}
	
	public static String readFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        String FullString="";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
            	FullString+=tempString;
                line++;
            }
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return FullString;
    }

}
