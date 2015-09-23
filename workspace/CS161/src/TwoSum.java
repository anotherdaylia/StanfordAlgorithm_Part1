import java.io.*;
import java.util.*;

public class TwoSum {
	
	public static ArrayList<Long> numbers = new ArrayList<Long>();
	
	public void readFile(String str) throws IOException{
		BufferedReader rd = new BufferedReader(new FileReader(str));
		while(true){
		     String line = rd.readLine();
		     if (line == null) break;
		     numbers.add(Long.valueOf(line));
		}
		rd.close();	
	}
	
	public int countTwoSum(ArrayList<Long> numbers, int a, int b){
		HashSet<Integer> set = new HashSet<Integer>();

		for(int t=a; t<=b; t++){
			HashMap<Long, Long> map = new HashMap<Long, Long>();
			
			for(long n : numbers){
				if (map.containsKey(t-n)){
					if(map.get(t-n) == null){
						map.put(t-n, n);
						set.add(t);
					}
				}else{
					map.put(n, null);
				}
			}
		}
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
