import java.io.*;
import java.util.*;


public class SCC2 {
	
	public static List<Vertex> vertices = new LinkedList<Vertex>();
	public static HashMap<Integer, ArrayList<Integer>> Grev = new HashMap<Integer, ArrayList<Integer>>();
	public static HashMap<Integer, ArrayList<Integer>> G = new HashMap<Integer, ArrayList<Integer>>();
	 
	public void reverseGraph(HashMap<Integer, ArrayList<Integer>> Grev){
		for (Vertex v : vertices){
			
			ArrayList<Integer> neighbor = Grev.get(v.vertex);
			for(int n : neighbor){
				if (G.get(n) == null){
					ArrayList<Integer> neighborRev = new ArrayList<Integer>();
					neighborRev.add(v.fv);
					G.put(vertices.get(n).fv, neighborRev);
				}else{
					G.get(n).add(v.vertex);
				}
			}
			
			v.vertex = v.fv;
		}
		
//		for(int i=1; i<=9; i++){
//			for (int v : G.get(i)){
//				System.out.print(i + "'s neighbor: " + v + ", ");
//				System.out.println();
//			}
//		}
		
	}
	
	// the number of vertices that have been fully explored
	public int t = 0;
	public static List<Vertex> fvertices = new ArrayList<Vertex>();
	public void DFSLoop1(HashMap<Integer, ArrayList<Integer>> G){
		for (int i = vertices.size()-1; i >= 0; i--){
			
			Vertex vi = vertices.get(i);
			if (vi.explored == false){
				//s = vi;
				DFS1(G, vi);
			}
		}
	}
	
	public void DFS1(HashMap<Integer, ArrayList<Integer>> G, Vertex vi){
		vi.explored = true;
		
		ArrayList<Integer> neighbor = G.get(vi.vertex);
		for (int n : neighbor){
			Vertex vj = vertices.get(n-1);
			if (vj.explored == false){
				DFS1(G,vj);
			}
		}
		t++;
		vi.fv = t;
		fvertices.add(new Vertex(vi.vertex, vi.fv, false));
		//System.out.println("Vertex: " + vi.vertex + ", f(v): " + vi.fv);
	}
	
	// the vertex from which the last DGS call was invoked
	public Vertex s = null; 
	public List<ArrayList<Vertex>> scclist = new LinkedList<ArrayList<Vertex>>();
	
	public void DFSLoop2(HashMap<Integer, ArrayList<Integer>> G){
		for (int i = fvertices.size()-1; i >= 0; i--){
			
			Vertex vi = fvertices.get(i); //start from the largest
			if (vi.explored == false){
				s = vi;
				
				System.out.println("leader: " +s.fv);
				
				ArrayList<Vertex> scc = new ArrayList<Vertex>();
				scc.add(s);
				DFS2(G, vi, scc);
				scclist.add(scc);
				
			}
		}
	}
	
	public void DFS2(HashMap<Integer, ArrayList<Integer>> G, Vertex vi, List<Vertex> scc){
		vi.explored = true;
		
		ArrayList<Integer> neighbor = G.get(vi.vertex);
		for (int n : neighbor){
			Vertex vj = fvertices.get(n-1);
			if (vj.explored == false){
				DFS2(G, vj, scc);
			}
		}
	}
	
	public void readAdjlist(String str) throws IOException{
		List<Integer> vertexlist = new ArrayList<Integer>();
		
		BufferedReader rd = new BufferedReader(new FileReader(str));
		while(true){
		     String line = rd.readLine();
		     if (line == null) break;
		     
		     String[] linearr = line.split("\\s+");
		     
		     int v = Integer.valueOf(linearr[0]);
		     int u = Integer.valueOf(linearr[1]);
		     
		     
		     if (!vertexlist.contains(v)){
		    	 vertexlist.add(v);
		    	 vertices.add(new Vertex(v, 0, false));
		    	 ArrayList<Integer> neighbor = new ArrayList<Integer>();
		    	 neighbor.add(u);
		    	 Grev.put(v, neighbor);
		    	 
		     }else{
		    	 ArrayList<Integer> neighbor = Grev.get(v);
		    	 neighbor.add(u);
		     }
		}
		rd.close();	
		
//		for(int i=1; i<=9; i++){
//			Iterator<Integer> it = Grev.get(i).iterator();
//			while (it.hasNext()){
//				System.out.println("print vetex: " + i + ", neighbor: " + it.next());
//			}
//		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SCC2 scc2 = new SCC2();
		scc2.readAdjlist("TestSCC.txt");
		scc2.DFSLoop1(Grev);
		
//		Iterator<Vertex> itv = vertices.iterator();
//		while (itv.hasNext()){
//			Vertex v = itv.next();
//			System.out.println("print vertex: " + v.vertex + ", fv: " + v.fv);
//		}
//		System.out.println("");
		scc2.reverseGraph(Grev);
		scc2.DFSLoop2(G);
		//scc2.DFSLoop2(G);


//		Iterator<Vertex> itf = fvertices.iterator();
//		while (itf.hasNext()){
//			Vertex fv = itf.next();
//			System.out.println("print vertex: " + fv.vertex + ", fv: " + fv.fv);
//		}
		
		
	}

}
