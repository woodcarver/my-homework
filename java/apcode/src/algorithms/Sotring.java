package algorithms;

import java.util.*;

public class Sotring {
	public static <T> T[] quickSort(T[] arr){
		ArrayList<Integer> list = new ArrayList<Integer>();
		PriorityQueue pq;
		TreeSet tset;
		HashSet hset;
		
		//int[] arr={1，2，3，4};
		ArrayList<Integer> alist=(ArrayList<Integer>) Arrays.asList(arr);
		list = new ArrayList<Integer>(alist);
		return arr;
	}
	public static void main(String[] args){
//		Integer arr[]={1,2,3,4};
//		System.out.println((ArrayList<Integer>) Arrays.asList(arr));
//		Sotring.<Integer>quickSort(arr);
		int[][] matrix = {{1,2},{3,4}};
		System.out.println(matrix.length+","+matrix[0].length);
	}
}
