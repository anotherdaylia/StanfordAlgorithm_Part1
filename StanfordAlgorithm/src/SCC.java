/**
 * The file contains the edges of a directed graph. Vertices are labeled as positive integers from 1 to 875714. 
 * Every row indicates an edge, the vertex label in first column is the tail and the vertex label 
 * in second column is the head (recall the graph is directed, and the edges are directed from 
 * the first column vertex to the second column vertex). 
 * So for example, the 11th row looks liks : "2 47646". This just means that the vertex 
 * with label 2 has an outgoing edge to the vertex with label 47646
 * 
 * Your task is to code up the algorithm from the video lectures for computing strongly connected components (SCCs), 
 * and to run this algorithm on the given graph. 
 * 
 * Output Format: You should output the sizes of the 5 largest SCCs in the given graph, 
 * in decreasing order of sizes, separated by commas (avoid any spaces). 
 * So if your algorithm computes the sizes of the five largest SCCs to be 500, 400, 300, 200 and 100, 
 * then your answer should be "500,400,300,200,100". If your algorithm finds less than 5 SCCs, 
 * then write 0 for the remaining terms. Thus, if your algorithm computes only 3 SCCs whose sizes are 
 * 400, 300, and 100, then your answer should be "400,300,100,0,0".
 * 
 * WARNING: This is the most challenging programming assignment of the course. 
 * Because of the size of the graph you may have to manage memory carefully. 
 * The best way to do this depends on your programming language and environment, 
 * and we strongly suggest that you exchange tips for doing this on the discussion forums.
 */

import java.io.*;
import java.util.*;


public class SCC {
	
	final static int NUM = 875714;
	//final static int NUM = 9;
	//public static List<Vertex> vertices = new ArrayList<Vertex>(NUM);
	public static Vertex[] graph = new Vertex[NUM];
	public static Vertex[] graphRev = new Vertex[NUM];
	
	public void reverseGraph(Vertex[] graph){
		for ( int i = 0; i < NUM; i++){
			
			Vertex vi = graph[i];
			for(int n : vi.neighbor){
				int fn = graph[n-1].fv; // n's fv
				graphRev[fn-1].addEdge(vi.fv);
			}
		}
	}
	
	public int t = 0; // the number of vertices that have been fully explored
	public static List<Vertex> fvertices = new ArrayList<Vertex>(NUM);
	public void DFSLoop1(Vertex[] graph){
		
		for (int i = NUM - 1; i >= 0; i--){
			
			Vertex vi = graph[i];
			//System.out.println("i: " + (i+1) + ", vi: " + vi);
			if (vi.explored == false){
				//s = vi;
				DFS1(graph, vi);
			}
		}
	}
	
	public void DFS1(Vertex[] graph, Vertex vi){
		vi.explored = true;
		
		for (int n : vi.neighbor){ 
			Vertex vj = graph[n-1];
			//System.out.println("n: " + n + ", vj: " + vj);
			if (vj.explored == false){
				DFS1(graph,vj);
			}
		}
		
		t++;
		vi.fv = t;
		graphRev[t-1] = new Vertex(vi.vertex, vi.fv);
		fvertices.add(new Vertex(vi.vertex, vi.fv));
		//System.out.println("Vertex: " + vi.vertex + ", f(v): " + vi.fv);
	}
	
	
	public Vertex s = null; // the vertex from which the last DGS call was invoked
	public static List<Integer> sizeList = new ArrayList<Integer>();
	
	public void DFSLoop2(Vertex[] graphRev){
		
		for (int i = NUM - 1; i >= 0; i--){
			
			Vertex vi = graphRev[i];
			//System.out.println("i: " + (i+1) + ", vi: " + vi);
			if (vi.explored == false){
				s = vi;
				//System.out.println("Leader: " + vi.fv);
				ArrayList<Vertex> scc = new ArrayList<Vertex>();
				DFS2(graphRev, vi, scc);
				sizeList.add(scc.size());	
			}
		}
	}
	
	public void DFS2(Vertex[] graph, Vertex vi, List<Vertex> scc){
		vi.explored = true;
		scc.add(vi);
		
		//LinkedList<Integer> neighbor = vi.neighbor;
		for (int n : vi.neighbor){
			//System.out.println("n: "+ n + ", vi: " + vi);
			Vertex vj = graphRev[n-1];
			if (vj.explored == false){
				DFS2(graph, vj, scc);
			}
		}
	}
	
	public void readAdjlist(String str) throws IOException{
		// initialize ArrayList<Vertex> vertices
		for(int i = 1; i <= NUM; i++){
			Vertex v = new Vertex(i);
			graph[i-1] = v;
		}
		
		BufferedReader rd = new BufferedReader(new FileReader(str));
		while(true){
		     String line = rd.readLine();
		     if (line == null) break;
		     
		     String[] linearr = line.split("\\s+");
		     
		     int tail = Integer.valueOf(linearr[0]);
		     int head = Integer.valueOf(linearr[1]);
		     
		     graph[tail-1].addEdge(head);
		     
//		     if (graph[tail-1] == null){
//
//		    	 Vertex v = new Vertex(tail);
//		    	 v.addEdge(head);
//		    	 graph[tail-1] = v;
//		    	 
//		     }else{
//		    	 graph[tail-1].addEdge(head);
//		     }
		}
		rd.close();	
		
//		for(int i=0; i<9; i++){
//			System.out.println("index: " + (i+1) + ", vertex: " + graphRev[i]);
//		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SCC2 scc = new SCC2();
		//scc2.readAdjlist("TestSCC.txt");
		scc.readAdjlist("SCC.txt");
		
		scc.DFSLoop1(graph);
		scc.reverseGraph(graph);
		scc.DFSLoop2(graphRev);
		
//		for(int i=0; i<9; i++){
//			//System.out.println("index(fv): " + (i+1) + ", neighbor: " + graphRev[i].neighbor);
//			System.out.println(graphRev[i]);
//		}
		
		Collections.sort(sizeList);
		for(int i = sizeList.size() - 1; i >= sizeList.size() - 5; i--){
			System.out.println(sizeList.get(i));
		}
	}

}
