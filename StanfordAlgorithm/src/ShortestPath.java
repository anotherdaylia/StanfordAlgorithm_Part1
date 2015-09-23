/**
 * The file contains an adjacency list representation of an undirected weighted graph 
 * with 200 vertices labeled 1 to 200. Each row consists of the node tuples that are adjacent to 
 * that particular vertex along with the length of that edge. 
 * For example, the 6th row has 6 as the first entry indicating that this row corresponds to the vertex labeled 6. 
 * The next entry of this row "141,8200" indicates that there is an edge between vertex 6 and vertex 141 that 
 * has length 8200. The rest of the pairs of this row indicate the other vertices adjacent to 
 * vertex 6 and the lengths of the corresponding edges.
 * 
 * Your task is to run Dijkstra's shortest-path algorithm on this graph, 
 * using 1 (the first vertex) as the source vertex, and to compute the shortest-path distances 
 * between 1 and every other vertex of the graph. 
 * If there is no path between a vertex v and vertex 1, we'll define the shortest-path distance 
 * between 1 and v to be 1000000. 
 * 
 * You should report the shortest-path distances to the following ten vertices, 
 * in order: 7,37,59,82,99,115,133,165,188,197. You should encode the distances as 
 * a comma-separated string of integers. So if you find that all ten of these vertices 
 * except 115 are at distance 1000 away from vertex 1 and 115 is 2000 distance away, 
 * then your answer should be 1000,1000,1000,1000,1000,2000,1000,1000,1000,1000. 
 * Remember the order of reporting DOES MATTER, and the string should be in the same order 
 * in which the above ten vertices are given. Please type your answer in the space provided.
 */

import java.io.*;
import java.util.*;

public class ShortestPath {
	
	public List<Integer> vertices = new LinkedList<Integer>();
	public HashMap<Integer, ArrayList<Node>> adjlist = new HashMap<Integer, ArrayList<Node>>();
	 
	public void readAdjlist(String str) throws IOException{
		//BufferedReader rd = new BufferedReader(new FileReader("TestShortestPath.txt")); //0,7,9,20,20,11
		//BufferedReader rd = new BufferedReader(new FileReader("TestShortestPath2.txt")); // 0,3,4,5,6,10
		BufferedReader rd = new BufferedReader(new FileReader(str));
		while(true){
		     String line = rd.readLine();
		     if (line == null) break;
		     
		     String[] linearr = line.split("\\s+");
		     vertices.add(Integer.valueOf(linearr[0]));
		     
		     ArrayList<Node> neighbor = new ArrayList<Node>();
		     
		     for(int i = 1; i < linearr.length; i++){
		    	 String[] disarr = linearr[i].split(",");
		    	 //System.out.println("i: " + i);
		    	 //System.out.println("vertex: " + disarr[0] + ", distance: " + disarr[1]);
		    	 
		    	 Node node = new Node(Integer.valueOf(disarr[0]), Integer.valueOf(disarr[1]));
		    	 neighbor.add(node);
		     }
				
		     adjlist.put(Integer.valueOf(linearr[0]), neighbor); 
		}
		rd.close();	
//		Iterator<Node> it = adjlist.get(3).iterator();
//		while (it.hasNext()){
//			Node node = it.next();
//			System.out.println(node.vertex + ", " + node.distance);
//		}
	}
	

	/**
	 * 1. Among all edges (v, w) belongs to E with v belongs to X and w does not belong to X,
	 *    pick the one that minimizes (Score[v] + lvw)
	 * 2. Call the picked edge as (v*, w*)
	 * 3. add w* to X
	 * 4. set A[w*] := A[v*] + lv*w*
	 */
	public long computePath(int s, int t){
		// vertices that are processed
		List<Integer> v_xed = new LinkedList<Integer>();
		// computed shortest path (v, d)
		Map<Integer, Long> v_dist = new HashMap<Integer, Long>();
		
		if (s == t){ return 0;}
		
		//initialize
		v_xed.add(s);
		v_dist.put(s, 0L);
		
		Score w = new Score();
		
		while(v_xed.size() <= vertices.size()){
			ArrayList<Score> u_score = new ArrayList<Score>();
			
			//System.out.println(v_xed.size() + " iteration");
			
			for (int v : v_xed){
				ArrayList<Node> neighbor = adjlist.get(v); 
				Score u = new Score();
				
				/**
				 * 1. find shortest edge between v and neighbor 
				 *    -!! check what if neighbor is null and if min_edge is null
				 */
				long min_edge = Integer.MAX_VALUE;
				
				if (neighbor == null || neighbor.size() == 0){
					min_edge = 0;
				}else {
					for (Node n : neighbor){
						if ( ! v_xed.contains(n.vertex)){ // do not check processed neighbor
							if (n.distance < min_edge){
								min_edge = n.distance;
								u.vertex = n.vertex;
//								System.out.println("v: " + v);
//								System.out.println("n: " + n.vertex + ", min_edge: " + min_edge + ", u.vertex: " + u.vertex);
							} 
						} 
					}
				}
				
				// calculate vscore = (score[v] + lvw)
				long uscore = v_dist.get(v) + min_edge;
				
				u.score = uscore;
				u_score.add(u);			
				//System.out.println("u we put: u.vertex= " + u.vertex + ", A[u]= " + uscore);
				
			}
			
			// Pick the smallest A[w]
//			for (Score score : u_score){
//				System.out.println("available scores: " + score.score);
//			}
			
			int mink = Integer.MAX_VALUE;
			long min = Integer.MAX_VALUE;
			for (Score sc : u_score){
				if (sc.score < min){
					min = sc.score;
					mink = sc.vertex;
				}
			}
			w.vertex = mink;
			w.score = min;
			
//			System.out.println("w we pick to X: w.vertex= " + w.vertex + ", A[w]= " + w.score);
//			System.out.println("");
			
			// Add w to X
			v_xed.add(w.vertex);
			v_dist.put(w.vertex, w.score);			
			
			if (w.vertex == t) break;
		}
		
		return w.score;
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ShortestPath path = new ShortestPath();
		
		path.readAdjlist("dijkstraData.txt");
		
		int[] v = {7,37,59,82,99,115,133,165,188,197};
		for (int i : v ) {
			System.out.println("t = " + i + " dist = " + path.computePath(1, i));
		}
		
	}

}
