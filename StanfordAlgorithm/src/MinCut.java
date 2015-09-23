/**
 * The file contains the adjacency list representation of a simple undirected graph. 
 * There are 200 vertices labeled 1 to 200. 
 * The first column in the file represents the vertex label, 
 * and the particular row (other entries except the first column) tells all the vertices that 
 * the vertex is adjacent to. So for example, the 6th row looks like : "6	155	56	52	120	......". 
 * This just means that the vertex with label 6 is adjacent to (i.e., shares an edge with) 
 * the vertices with labels 155,56,52,120,......,etc
 * 
 * Your task is to code up and run the randomized contraction algorithm for the min cut problem 
 * and use it on the above graph to compute the min cut (i.e., the minimum-possible number of crossing edges). 
 * (HINT: Note that you'll have to figure out an implementation of edge contractions. 
 * Initially, you might want to do this naively, creating a new graph from the old every time there's an edge 
 * contraction. But you should also think about more efficient implementations.) 
 * (WARNING: As per the video lectures, please make sure to run the algorithm many times with 
 * different random seeds, and remember the smallest cut that you ever find.) 
 * 
 * Write your numeric answer in the space provided. 
 * So e.g., if your answer is 5, just type 5 in the space provided.
 * 
 * 07-2015
 */
import java.io.*;
import java.util.*;

public class MinCut {
	
	public int min = 199;
	
	public List<Integer> verticesSave = new LinkedList<Integer>();
	public HashMap<Integer, ArrayList<Integer>> adjlistSave = new HashMap<Integer, ArrayList<Integer>>();
	
	public List<Integer> vertices;
	public HashMap<Integer, ArrayList<Integer>> adjlist = new HashMap<Integer, ArrayList<Integer>>();
	
	public void countMinCuts(int n) throws IOException{
		MinCut minCut = new MinCut();
		minCut.readAdjList();
		
		for(int i = 1; i <= n; i++){
			minCut.createCopy();
			minCut.contract();
			int thisCut = minCut.countEdges();
			if (min > thisCut){
				min = thisCut;
			}
			System.out.println("The " + i + "th try: " + thisCut + " Min cut: " + min);
		}
	}
	
	public void readAdjList() throws IOException{
		BufferedReader rd = new BufferedReader(new FileReader("TestMinCut.txt"));
		while(true){
		     String line = rd.readLine();
		     if (line == null) break;
		     
		     String[] linearr = line.split("\\s+");
		     verticesSave.add(Integer.valueOf(linearr[0]));
		     
		     ArrayList<Integer> neighbor = new ArrayList<Integer>();
		     for(int i = 1; i < linearr.length; i++){
		    	 neighbor.add(Integer.valueOf(linearr[i]));
		     }
				
		     adjlistSave.put(Integer.valueOf(linearr[0]), neighbor);  
		}
		rd.close();	
	}
	
	public void createCopy(){
		vertices = new LinkedList<Integer>(verticesSave);
		
		for(int i = 0; i < verticesSave.size(); i++){
			int vertex = verticesSave.get(i);
			
			ArrayList<Integer> neighbor2;
			neighbor2 = new ArrayList<Integer>(adjlistSave.get(vertex));
			adjlist.put(vertex, neighbor2);
		}	
		
//		Iterator<Integer> it = adjlist2.get(5).iterator();
//		while (it.hasNext()){
//			System.out.println(it.next());
//		}		
		
	}
	
	public void contract(){
		while(vertices.size() > 2){
			mergeVertex();
		}
	}
	
	public void mergeVertex(){
		int[] edge = pickEdge();
		int v = edge[0];
		int u = edge[1];
		
		// attach u's list to v
		for (int i = 0; i < adjlist.get(u).size(); i++){
			adjlist.get(v).add(adjlist.get(u).get(i));
		}
		
		// scan u's neighbor's list and replace u to v
		ArrayList<Integer> uNeighbor = adjlist.get(u);
		for(int i = 0; i < uNeighbor.size(); i++){
			// get neighbor's vertex #
			int n = uNeighbor.get(i);
			
			// get neighbor's list
			ArrayList<Integer> nlist = adjlist.get(n);
			
			// replace u to v
			for (int j = 0; j < nlist.size(); j++){
				if (nlist.get(j).equals(u)){
					nlist.set(j, v);
				}
			}
		}
		
		//test
//		System.out.print("v's list before remove self loop: ");
//		Iterator<Integer> it = adjlist.get(v).iterator();
//		while (it.hasNext()){
//			System.out.print(it.next() + " ");
//		}
//		System.out.println("");
		
		// remove self loop in v's list
		ArrayList<Integer> vNeighbor = adjlist.get(v);
		while (vNeighbor.remove((Object)v));
		
		
		//test
//		System.out.print("v's list after removing self loop: ");
//		it = adjlist.get(v).iterator();
//		while (it.hasNext()){
//			System.out.print(it.next() + " ");
//		}
//		System.out.println("");
		
		// remove u's list
		adjlist.remove(u);
		vertices.remove(vertices.indexOf(u));
		
	}
	
	public int[] pickEdge(){
		Random randomGenerator = new Random();
		
		int vIndex = randomGenerator.nextInt(vertices.size() - 1);
		int v = vertices.get(vIndex);
		
		ArrayList<Integer> temp = adjlist.get(v);
		while(temp == null || temp.size() == 0 ){
			vIndex = randomGenerator.nextInt(vertices.size() - 1);
			v = vertices.get(vIndex);
			temp = adjlist.get(v);
		}
		
		int uIndex = randomGenerator.nextInt(adjlist.get(v).size() - 1);
	    int u =  adjlist.get(v).get(uIndex);
	    
	    return new int[] {v, u};
	}
	
	public int countEdges(){
		int v = vertices.get(0);
		return adjlist.get(v).size();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MinCut minCut = new MinCut();
		minCut.countMinCuts(10);

	}


}
