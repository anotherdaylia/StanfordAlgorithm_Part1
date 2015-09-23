/**
 * This file contains all of the 100,000 integers between 1 and 100,000 (inclusive) in some order, 
 * with no integer repeated.
 * Your task is to compute the number of inversions in the file given, 
 * where the ith row of the file indicates the ith entry of an array.
 * 
 * Because of the large size of this array, you should implement the 
 * fast divide-and-conquer algorithm covered in the video lectures. 
 * The numeric answer for the given input file should be typed in the space below.
 * So if your answer is 1198233847, then just type 1198233847 in the space provided 
 * without any space / commas / any other punctuation marks. 
 * You can make up to 5 attempts, and we'll use the best one for grading.
 * (We do not require you to submit your code, so feel free to use any programming 
 * language you want --- just type the final numeric answer in the following space.)
 * 
 * 07-2015
 */
import java.io.*;
import java.util.*;

public class Inversions2 {
	
	public static long count = 0;
	
	public static int[] mergeSort(int[] a, int start, int end){
		// if the length of Array is 1
		if ( end - start == 0){
			return new int[] {a[start]};
		}
			
		int mid = (start + end)/2;
		
		// sort and count the left half
		int[] b = mergeSort(a, start, mid );
		
		// sort and count the right half
		int[] c = mergeSort(a, mid+1, end );
		
		// merge and count the split pair
		int[] d = mergeAndCount(b, c);
		return d;
		
	}
	
	// left half is b[] and right half is c[]
	public static int[] mergeAndCount(int[] b, int[] c){
		int length = b.length + c.length;
		int[] d = new int[length];
		int i = 0;
		int j = 0;
		
		for(int k = 0; k < length; k++){
			if (i < b.length && (j >= c.length || b[i] < c[j])){
				d[k] = b[i];
				i++;
			}else {
				d[k] = c[j];
				j++;
				count = count + (b.length - i);
			}
		}
		
		return d;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[100000];
		
		BufferedReader rd;
		try {
			rd = new BufferedReader(new FileReader("IntegerArray.txt"));
			for (int i = 0; i < 100000; i++){
				String line = rd.readLine();
				a[i] = Integer.parseInt(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[] sorted = mergeSort(a, 0, a.length-1);
		System.out.println("after sort:" + Arrays.toString(sorted));
		System.out.println("Inversions: " + count);
		
		
	}

}
