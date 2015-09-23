import java.io.*;
import java.util.*;


public class SCC2 {
	
	final static int NUM = 875714;
	//final static int NUM = 9;
	public static Vertex[] graph = new Vertex[NUM];
	public static Vertex[] graphRev = new Vertex[NUM];
	
	public int t = 0; // the number of vertices that have been fully explored
	public Vertex leader = null; // the vertex from which the last DGS call was invoked
	public static List<Integer> sizeList = new ArrayList<Integer>();
	
	public void reverseGraph(Vertex[] graph){
		
		for ( int i = 0; i < NUM; i++){
			Vertex vi = graph[i];
			for(int n : vi.neighbor){
				int fn = graph[n-1].fv; // n's fv
				graphRev[fn-1].addEdge(vi.fv); // use vi's processing time as vertex
			}
		}
	}
	
	
	public void DFSLoop1(Vertex[] graph){
		
		for (int i = NUM - 1; i >= 0; i--){
			Vertex vi = graph[i];
			if (vi.explored == false){
				DFS1(graph, vi);
			}
		}
	}
	
	public void DFS1(Vertex[] graph, Vertex vi){
		Stack<Vertex> stack = new Stack<Vertex>();
		stack.push(vi);
		
		vi.explored = true;
		
		while(!stack.isEmpty()) {
			vi = stack.peek();
		
			if(vi.neighbor.size() != 0){
				
				int n = 0;
				while(n < vi.neighbor.size()) {
					Vertex vj = graph[vi.neighbor.get(n) - 1];
					if (vj.explored == false){
						vj.explored = true;
						stack.push(vj);
						vi = vj;
						n = 0;
					}else {
						// advance n until we find an unexplored neighbor of vi 
						n++;
					}
				}
			}
			// Bug fixed: have to pop to end the loop
			// was included this part inside if clause 
			vi = stack.pop();
			t++;
			vi.fv = t;
			graphRev[t-1] = new Vertex(vi.vertex, vi.fv);
				
		}
	}
	
	public void DFSLoop2(Vertex[] graphRev){
		for (int i = NUM - 1; i >= 0; i--){
			
			Vertex vi = graphRev[i];
			if (vi.explored == false){
				leader = vi;
				ArrayList<Vertex> scc = new ArrayList<Vertex>();
				DFS2(graphRev, vi, scc);
				sizeList.add(scc.size());
			}
		}
	}
	
	public void DFS2(Vertex[] graphRev, Vertex vi, List<Vertex> scc){
		Stack<Vertex> stack = new Stack<Vertex>();
		stack.push(vi);
		
		vi.explored = true;
		scc.add(vi);
		
		while(!stack.isEmpty()) {
			vi = stack.peek();
		
			if(vi.neighbor.size() != 0){
				
				int n = 0;
				while(n < vi.neighbor.size()) {
					Vertex vj = graphRev[vi.neighbor.get(n) - 1];
					
					if (vj.explored == false){
						vj.explored = true;
						stack.push(vj);
						scc.add(vj);
						vi = vj;
						n = 0;
					}else {
						n++;
					}
				}
			}
			vi = stack.pop();
			vi.leader = leader.vertex;
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
		     
		}
		rd.close();	
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SCC2 scc2 = new SCC2();
		//scc2.readAdjlist("TestSCC.txt");
		scc2.readAdjlist("SCC.txt");
		
		scc2.DFSLoop1(graph);
		scc2.reverseGraph(graph);
		scc2.DFSLoop2(graphRev);
		
// 		Test to print graph
//		for(int i=0; i<9; i++){
//			System.out.println(graphRev[i]);
//		}
		
		Collections.sort(sizeList);
		for(int i = sizeList.size() - 1; i >= sizeList.size() - 5; i--){
			if (sizeList.get(i) == null){
				System.out.println("0");
			}else{
				System.out.println(sizeList.get(i));
			}
			
		}
	}

}
