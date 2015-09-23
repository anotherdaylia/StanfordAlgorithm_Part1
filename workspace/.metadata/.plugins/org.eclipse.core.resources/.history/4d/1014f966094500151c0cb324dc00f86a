
import java.util.*;

public class MergeSort {
	
	// Array: int[] a
	// start index: int start
	// end index: int end
	public static int[] mergeSort(int[] a, int start, int end){
		// if the length of Array is 1
		if ( end - start == 0){
			//System.out.println("before copy array: " + Arrays.toString(copyArray(a, start, end)));
			//return copyArray(a, start, end);
			return new int[] {a[start]};
		}
			
		int mid = (start + end)/2;
		int[] b = mergeSort(a, start, mid );
		//System.out.println("b: " + Arrays.toString(b));
		
		int[] c = mergeSort(a, mid+1, end );
		//System.out.println("c: " + Arrays.toString(c));
		
		int[] d = merge(b, c);
		//System.out.println("d: " + Arrays.toString(b));
		return d;
		
	}
	
	// left half is b[]
	// right half is c[]
	public static int[] merge(int[] b, int[] c){
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
			}
		}
		
		return d;
	}
	
	
	public static int[] copyArray(int[] a, int start, int end){
		int[] arr = new int[end-start+1];
		int i = start;
		
		for(int k = 0; k < arr.length; k++){
			arr[k] = a[i];
			i++;
		}

		return arr;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {9, 1, 3, 4, 5, 6, 77};
		int length = a.length;
		System.out.println("before sort:" + Arrays.toString(a) + " length: " + length);
		
		int[] sorted = mergeSort(a, 0, length-1);
		System.out.println("after sort:" + Arrays.toString(sorted));
		
		// test merge()
		/*int[] b = {3, 5, 7, 8};
		int[] c = {2};
		int[] d = merge(b, c);
		System.out.println("Merge:" + Arrays.toString(d));*/
		
		// test copyArray()
		//System.out.println("Copy Array:" + Arrays.toString(copyArray(a,3,3)));
		
	}

}
