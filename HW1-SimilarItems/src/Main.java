import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		// Shingling test
		Shingling s = new Shingling();
		String text1 = "abcabc";
		String text2 = "abd ch eg s dc";
		String text3 = "abd ch	eg s dc";
		String text4 = "";
		
		System.out.println("--- Shingle hash values of text 1 ---");
		System.out.println(s.ShingleHash(text1, 2));
		System.out.println("--- Shingle hash values of text 2 ---");
		System.out.println(s.ShingleHash(text2, 4));
		System.out.println("--- Shingle hash values of text 3 ---");
		System.out.println(s.ShingleHash(text3, 4));
		System.out.println("--- Shingle hash values of text 3 ---");
		System.out.println(s.ShingleHash(text4, 4)); 
		
		// CompareSets test
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
		Set<Integer> set3 = new HashSet<Integer>();
		Set<Integer> set4 = new HashSet<Integer>();
		Set<Integer> set5 = new HashSet<Integer>();

		int[] a1 = {1, 3, 5, 7}; int[] a2 = {1, 3, 4, 9}; int[] a3 = {0, 5}; int[] a4 = {}; int[] a5 = {};
		set1 = Arrays.stream(a1).boxed().collect(Collectors.toSet());
		set2 = Arrays.stream(a2).boxed().collect(Collectors.toSet());
		set3 = Arrays.stream(a3).boxed().collect(Collectors.toSet());
		set4 = Arrays.stream(a4).boxed().collect(Collectors.toSet());
		set5 = Arrays.stream(a5).boxed().collect(Collectors.toSet());

		System.out.println("--- CompareSets test 1 ---");
		System.out.println(new CompareSets().ComputeJaccard(set1, set2));
		System.out.println("--- CompareSets test 2 ---");
		System.out.println(new CompareSets().ComputeJaccard(set1, set3));
		System.out.println("--- CompareSets test 3 ---");
		System.out.println(new CompareSets().ComputeJaccard(set1, set4));
		System.out.println("--- CompareSets test 4 ---");
		System.out.println(new CompareSets().ComputeJaccard(set4, set5));

	}

}
