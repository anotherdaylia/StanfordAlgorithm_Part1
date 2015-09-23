import java.io.*;
import java.util.*;


public class Median {
	
	public static ArrayList<Integer> numbers = new ArrayList<Integer>();
	
	Comparator<Integer> maxComparator = new DescCompare();
	Comparator<Integer> minComparator = new AscCompare();
	PriorityQueue<Integer> heap_low = new PriorityQueue<Integer>(1, maxComparator);
	PriorityQueue<Integer> heap_high = new PriorityQueue<Integer>(1, minComparator);
	
	public void readFile(String str) throws IOException{
		BufferedReader rd = new BufferedReader(new FileReader(str));
		while(true){
		     String line = rd.readLine();
		     if (line == null) break;
		     numbers.add(Integer.valueOf(line));
		}
		rd.close();	
	}
	
	public int maintainMedian(int a){
		// insert and maintain balancing
		if (heap_low.peek() == null || a <= heap_low.peek()){
			heap_low.add(a);
			if(heap_low.size() - heap_high.size() >= 2){
				heap_high.add(heap_low.poll());
			}
		}else{
			heap_high.add(a);
			if(heap_high.size() - heap_low.size() >= 2){
				heap_low.add(heap_high.poll());
			}
		}
		
//		System.out.println("heap_low has:");
//		for (int l : heap_low){
//			System.out.print(l + ", ");
//		}
//		System.out.println("");
//		System.out.println("heap_high has:");
//		for (int h : heap_high){
//			System.out.print(h + ", ");
//		}
//		System.out.println("");
		
		// return median
		if(heap_low.size() == heap_high.size()){
			//System.out.println("equal: " + (heap_low.peek() + heap_high.peek())/2);
			return heap_low.peek();
		}else if (heap_low.size() > heap_high.size()){
			//System.out.println("from low: " + heap_low.peek());
			return heap_low.peek();
		}else if (heap_low.size() < heap_high.size()){
			//System.out.println("from high: " + heap_high.peek());
			return heap_high.peek();
		}
		
		return 0;
	}
	
	class DescCompare implements Comparator<Integer>{

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o2 - o1;
		}
		
	}
	
	class AscCompare implements Comparator<Integer>{

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o1 - o2;
		}
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Median median = new Median();
		median.readFile("Median.txt");
		//median.readFile("Test.txt");
		
		int result = 0;
		for (int n : numbers){
			result = result + median.maintainMedian(n);
			System.out.println("sum of medians: " + result);
		}
		result = result % 10000;
		
		System.out.println("result= " + result);
		
	}

}
