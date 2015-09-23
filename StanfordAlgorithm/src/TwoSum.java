/**
 * The goal of this problem is to implement a variant of the 2-SUM algorithm 
 * (covered in the Week 6 lecture on hash table applications).
 * The file contains 1 million integers, both positive and negative (there might be some repetitions!).
 * This is your array of integers, with the ith row of the file specifying the ith entry of the array.
 * 
 * Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) 
 * such that there are distinct numbers x,y in the input file that satisfy x+y=t. 
 * (NOTE: ensuring distinctness requires a one-line addition to the algorithm from lecture.)
 * 
 * Write your numeric answer (an integer between 0 and 20001) in the space provided.
 * 
 * OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing your own hash table for it. 
 * For example, you could compare performance under the chaining and open addressing approaches 
 * to resolving collisions.
 */
import java.io.*;
import java.util.*;

public class TwoSum {
	
	public static ArrayList<Long> numbers = new ArrayList<Long>();
	
	public void readFile(String str) throws IOException{
		BufferedReader rd = new BufferedReader(new FileReader(str));
		//BufferedReader rd = new BufferedReader(new FileReader("algo1-programming_prob-2sum.txts"));
		while(true){
		     String line = rd.readLine();
		     if (line == null) break;
		     numbers.add(Long.valueOf(line));
		}
		rd.close();	
		
//		Iterator<Long> it = numbers.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
	}
	
	public int countTwoSum(ArrayList<Long> numbers, int a, int b){
		int count = 0;
		HashSet<Integer> set = new HashSet<Integer>();

		for(int t=a; t<=b; t++){
			HashMap<Long, Long> map = new HashMap<Long, Long>();
			
			for(long n : numbers){
				if (map.containsKey(t-n)){
					if(map.get(t-n) == null){
						map.put(t-n, n);
						System.out.println("t=" + t + ", n=" + n + ", t-n="+ (t-n));
						count++;
						set.add(t);
					}
				}else{
					map.put(n, null);
				}
			}
		}
		System.out.println("total count = " + count);
		return set.size();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TwoSum twosum = new TwoSum();
		twosum.readFile("algo1-programming_prob-2sum.txt");
		//twosum.readFile("Test2Sum.txt");
		int t = twosum.countTwoSum(numbers, -10000, 10000);
		System.out.println( "# of t: " + t);
	}

}
