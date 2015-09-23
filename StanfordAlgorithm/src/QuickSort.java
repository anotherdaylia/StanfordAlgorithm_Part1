/**
 * The file contains all of the integers between 1 and 10,000 (inclusive, with no repeats) in unsorted order. 
 * The integer in the ith row of the file gives you the ith entry of an input array.
 * 
 * Your task is to compute the total number of comparisons used to sort the given input file by QuickSort. 
 * As you know, the number of comparisons depends on which elements are chosen as pivots, 
 * so we'll ask you to explore three different pivoting rules.
 * You should not count comparisons one-by-one. Rather, when there is a recursive call on a subarray of length m, 
 * you should simply add m−1 to your running total of comparisons. 
 * (This is because the pivot element is compared to each of the other m−1 elements in the subarray 
 * in this recursive call.)
 * 
 * WARNING: The Partition subroutine can be implemented in several different ways, 
 * and different implementations can give you differing numbers of comparisons. 
 * For this problem, you should implement the Partition subroutine exactly as it is described 
 * in the video lectures (otherwise you might get the wrong answer).
 * 
 * DIRECTIONS FOR THIS PROBLEM:
 * For the first part of the programming assignment, you should always use the first element 
 * of the array as the pivot element.
 * 
 * HOW TO GIVE US YOUR ANSWER:
 * Type the numeric answer in the space provided.
 * So if your answer is 1198233847, then just type 1198233847 in the space provided 
 * without any space / commas / other punctuation marks. 
 * You have 5 attempts to get the correct answer.
 * 
 * 07-2015
 */
import java.io.*;
import java.util.Arrays;

public class QuickSort {
	
	public static int comparison = 0;
	
	public static void quickSort(int[] arr){
		recQuickSort(arr, 0, arr.length-1);
	}
	
	public static void recQuickSort(int[] arr, int left, int right){
		if(right - left <=0 ){
			return;
		}else{
			comparison = comparison + right - left;
			int pivot = choosePivot(arr, left, right, 3);
			int partition = partition(arr, left, right, pivot);
			
			recQuickSort(arr, left, partition-1);
			recQuickSort(arr, partition+1, right);
		}
		
	}
	
	public static int choosePivot(int[] arr, int left, int right, int method){
		if (method == 1){
			return arr[left];
			
		}else if (method == 2){
			swap(arr, left, right);
			return arr[left];
			
		}else if (method == 3){
			int mid = (left + right)/2;
			int pivot;
			
			if (arr[left] <= arr[right]){
				if (arr[left] <= arr[mid]){
					if(arr[right] <= arr[mid]){
						pivot =  arr[right];
					}else{
						pivot = arr[mid];
					}
				}else{
					pivot = arr[left];
				}
			}else {
				if (arr[right] <= arr[mid]){
					if(arr[left] <= arr[mid]){
						pivot = arr[left];
					}else{
						pivot = arr[mid];
					}
				}else{
					pivot =  arr[right];
				}
			}

			if(pivot == arr[right]){
				swap(arr, left, right);
			}else if(pivot == arr[mid]){
				swap(arr, left, mid);
			}
			
			return pivot;
			
		}else{
			return arr[left];
		}
	}
	
	
	public static int partition(int[] arr, int leftPointer, int rightPointer, int pivot){
		//int pivot = arr[leftPointer];
		int i = leftPointer + 1;
		
		for(int j = leftPointer+1; j <= rightPointer; j++){
			if(arr[j] < pivot){
				swap(arr, j, i);
				i = i + 1;
			}
		}
		
		swap(arr, leftPointer, i-1);
		return i-1;
	}
	
	public static void swap(int[] array, int a, int b){
		int tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[10000];
		try {
			BufferedReader rd = new BufferedReader(new FileReader("QuickSort.txt"));
			
			int i = 0;
			while(true){
				String line = rd.readLine();
				if (line == null) break;
				arr[i] = Integer.valueOf(line);
				i++;
			}
			
			rd.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//System.out.print("Before Sort: " + Arrays.toString(arr));
		quickSort(arr);
		System.out.println("After Sort: " + Arrays.toString(arr)); 
		System.out.println("Comparison: " + comparison); 
		//1st 162085
		//2nd 164123
		//3rd 138382
		
		
	}

}
