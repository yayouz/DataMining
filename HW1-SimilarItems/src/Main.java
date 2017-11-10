package similarItems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		// Shingling test
		Shingling s = new Shingling();
		String text1 = "abdabc";
		String text2 = "abd ch eg s dc";
		String text3 = "abd ch eg s gf";
		String text4 = "";
		
		HashMap<Integer,String> shingle1 = s.ShingleHash(text1, 3);
		HashMap<Integer,String> shingle2 = s.ShingleHash(text2, 3);
		HashMap<Integer,String> shingle3 = s.ShingleHash(text3, 3);
		HashMap<Integer,String> shingle4 = s.ShingleHash(text4, 3);
		
		System.out.println("--- Shingle hash values of text 1 ---");
		System.out.println(shingle1);
		System.out.println("--- Shingle hash values of text 2 ---");
		System.out.println(shingle2);
		System.out.println("--- Shingle hash values of text 3 ---");
		System.out.println(shingle3);
		System.out.println("--- Shingle hash values of text 4 ---");
		System.out.println(shingle4); 
		
		// CompareSets test
		Set<Integer> set1 = shingle1.keySet();
		Set<Integer> set2 = shingle2.keySet();
		Set<Integer> set3 = shingle3.keySet();
		Set<Integer> set4 = shingle4.keySet();
		Set<Integer> set5 = shingle4.keySet();
		/*int[] a1 = {1, 3, 5, 7}; int[] a2 = {1, 3, 4, 9}; int[] a3 = {0, 5}; int[] a4 = {}; int[] a5 = {};
		set1 = Arrays.stream(a1).boxed().collect(Collectors.toSet());
		set2 = Arrays.stream(a2).boxed().collect(Collectors.toSet());
		set3 = Arrays.stream(a3).boxed().collect(Collectors.toSet());
		set4 = Arrays.stream(a4).boxed().collect(Collectors.toSet());
		set5 = Arrays.stream(a5).boxed().collect(Collectors.toSet());*/

		System.out.println("--- CompareSets test 1 ---");
		System.out.println(new CompareSets().ComputeJaccard(set1, set2));
		System.out.println("--- CompareSets test 2 ---");
		System.out.println(new CompareSets().ComputeJaccard(set2, set3));
		System.out.println("--- CompareSets test 3 ---");
		System.out.println(new CompareSets().ComputeJaccard(set1, set4));
		System.out.println("--- CompareSets test 4 ---");
		System.out.println(new CompareSets().ComputeJaccard(set4, set5));
		
		HashMap<Integer,text> Matrix=new MinHashing(set1,set2).minhashFunction(10);
		System.out.println("--- Signature test 1 ---");
		System.out.println("Signature for text1"+Matrix.get(1).signatures );
		System.out.println("Signature for text2"+Matrix.get(2).signatures );
		
		System.out.println("Similarity of signature for text1 and text2: "
						+new CompareSignatures().similar(Matrix.get(1).signatures, Matrix.get(2).signatures) );
		

		Matrix=new MinHashing(set2,set3).minhashFunction(10);
		System.out.println("--- Signature test 1 ---");
		System.out.println("Signature for text1"+Matrix.get(1).signatures );
		System.out.println("Signature for text2"+Matrix.get(2).signatures );
		
		System.out.println("Similarity of signature for text2 and text3: "
						+new CompareSignatures().similar(Matrix.get(1).signatures, Matrix.get(2).signatures) );
	

	}

}
