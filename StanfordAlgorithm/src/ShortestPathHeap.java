/**
 * IMPLEMENTATION NOTES: This graph is small enough that the straightforward O(mn) time implementation 
 * of Dijkstra's algorithm should work fine. OPTIONAL: For those of you seeking an additional challenge, 
 * try implementing the heap-based version. Note this requires a heap that supports deletions, 
 * and you'll probably need to maintain some kind of mapping between vertices and their positions in the heap.
 */
import java.io.IOException;
import java.util.*;

public class ShortestPathHeap extends ShortestPath {
	
	public long computePathHeap(int s, int t){
		List<Integer> v_xed = new LinkedList<Integer>();
		Map<Integer, Long> v_dist = new HashMap<Integer, Long>();
		
		Comparator<Score> comparator = new ScoreCompare();
		PriorityQueue<Score> heap = new PriorityQueue<Score>(6,comparator);
		
		if (s == t){ return 0;}
		
		//initialize
		v_xed.add(s);
		v_dist.put(s, 0L);
		
		for (int v : vertices){
			if (v == s){
				heap.offer(new Score(s,0));
			}else{
				heap.offer(new Score(v, Integer.MAX_VALUE));
				v_dist.put(v, Long.MAX_VALUE);
			}
		}
		
		Score w = null;
		
		while(v_xed.size() <= vertices.size()){
			
			w = heap.poll();
			while(heap.remove(w));
			
			v_xed.add(w.vertex);
			v_dist.put(w.vertex, w.score);
			
			ArrayList<Node> neighbor = adjlist.get(w.vertex);
			
			if (neighbor == null || neighbor.size() == 0){
				System.out.println("neighbor null?");
			}else {
				for (Node n : neighbor){
					if ( ! v_xed.contains(n.vertex)){ // do not check processed neighbor
						while(heap.remove(n));

						//System.out.println("n.vertex: " + n.vertex + ", n.distance: " + n.distance + ", w.score: " + w.score);
						long min_key = (long) Math.min(v_dist.get(n.vertex), (w.score + n.distance));
						v_dist.put(n.vertex, min_key);
						Score updated_n = new Score(n.vertex, min_key);
						heap.offer(updated_n);
						//System.out.println("updated_n.v: " + updated_n.vertex+ ", updated_n.score: " + updated_n.score);
					} 
				}
			}
			
			if (w.vertex == t) break;
		}
		
		return w.score;
	}
	
	class ScoreCompare implements Comparator<Score>{

		@Override
		public int compare(Score o1, Score o2) {
			// TODO Auto-generated method stub
			return (int) (o1.score - o2.score);
		}
		
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ShortestPathHeap pathheap = new ShortestPathHeap();
		//pathheap.readAdjlist("TestShortestPath2.txt");
		pathheap.readAdjlist("dijkstraData.txt");
		//System.out.println("final result: " + pathheap.computePathHeap(1, 37));
		int[] v = {7,37,59,82,99,115,133,165,188,197};
		for (int i : v ) {
			System.out.println("t = " + i + " dist = " + pathheap.computePathHeap(1, i));
		}
		
	}

}
