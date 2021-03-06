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

public class Inversions {
	
	public static long sortAndCount(int[] a, int start, int end){
		// if the length of Array is 1
		if ( end - start <= 0){
			return 0;
		}
			
		int mid = (start + end)/2;
		
		// sort and count the left half
		long b = sortAndCount(a, start, mid );
		
		// sort and count the right half
		long c = sortAndCount(a, mid+1, end );
		
		// merge and count the split pair
		long d = mergeAndCount(a, start, end);
		
		return b + c + d;
		
	}
	
	public static long mergeAndCount(int[] a, int start, int end){
		long inversions = 0;
		int[] aux = new int[a.length];
		
		int mid = start + (end - start)/2;
		int i = start;
		int j = mid+1;
		
		// copy a[] to aux[]
		for (int k = start; k <= end; k++) {
            aux[k] = a[k]; 
        }
		
		// merge aux[] back to a[]
		for(int k = start; k < end+1; k++){
			if ((i < mid+1) && (j >= end+1 || aux[i] < aux[j])){
				a[k] = aux[i];
				i++;
			}else {
				a[k] = aux[j];
				j++;
				inversions = inversions + (mid + 1 -i);
			}
		}
		
		return inversions;
		
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
		
		System.out.println("before sort:" + Arrays.toString(a));
		//System.out.println();
		long sorted = sortAndCount(a, 0, a.length-1);
		System.out.println("after sort:" + Arrays.toString(a));
		System.out.println("Inversions: " + sorted);
			
	}

}
