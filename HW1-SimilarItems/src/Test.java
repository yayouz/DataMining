package similarItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.io.*;

public class Test{

	public static void main(String[] args) {
		String route="/dataset/";
		// Shingling test		
		Shingling s = new Shingling();
		String text1,text2;
		text1 = readFile(route+1+".gold");
		HashMap<Integer,String> shingle1 = s.ShingleHash(text1, 4);
		System.out.println("--- Shingle hash values of text "+1+" ---");
		System.out.println(shingle1);

		Set<Integer> set1 = shingle1.keySet();
		
		Set<Integer> AllShingle = new HashSet<Integer>(set1);
		
		Random rand = new Random();
		int n=100;
		List<Integer> a = new ArrayList<Integer>();
		List<Integer> b = new ArrayList<Integer>();
		
		for (int i = 0; i < n; i++) {
			int q=rand.nextInt(n);
			a.add(4*q+1);
			b.add(2*q+1);
		}
		
		for(int i=2;i<11;i++){
			text2 = readFile(route+i+".gold");
			
			HashMap<Integer,String> shingle2 = s.ShingleHash(text2, 4);
			System.out.println("--- Shingle hash values of text "+i+" ---");
			System.out.println(shingle2);
			
			Set<Integer> set2 = shingle2.keySet();
			
			System.out.println("--- CompareSets test set1 and set"+i+" ---");
			System.out.println(new CompareSets().ComputeJaccard(set1, set2));
			
			HashMap<Integer,text> Matrix=new MinHashing(set1,set2).minhashFunction(50);
			System.out.println("--- Signature test "+(i-1)+" ---");
			//System.out.println("Signature for text1"+Matrix.get(1).signatures );
			//System.out.println("Signature for text"+i+Matrix.get(2).signatures );
			System.out.println("Similarity of signature for text1 and text"+i+": "
							+new CompareSignatures().similar(Matrix.get(1).signatures, Matrix.get(2).signatures) );
			System.out.println();
		}
		
		System.out.println();
		System.out.println("-----Signature compare for RandomMinHash-----");
		System.out.println();
		
		RandomMinHash rmh1 = new RandomMinHash(n, AllShingle, set1, a, b);
		System.out.println("Signature2 for text"+1+": "+rmh1.Signature());
		for(int i=2;i<11;i++){
		String text = readFile(route+i+".gold");
		HashMap<Integer,String> shingle2 = s.ShingleHash(text, 4);
		Set<Integer> set = shingle2.keySet();
		RandomMinHash rmh = new RandomMinHash(n, AllShingle, set, a, b);
		System.out.println("Signature2 for text"+i+": "+rmh.Signature());
		System.out.println("Similarity of signature2 for text1 and text"+i+": "
				+new CompareSignatures().similar(rmh1.Signature(), rmh.Signature()));
		}

	}
	
	public static String readFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        String FullString="";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	FullString+=tempString;
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
