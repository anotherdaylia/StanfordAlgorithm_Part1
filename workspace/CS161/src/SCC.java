import java.io.*;
import java.util.*;


public class SCC {
	
	public List<Integer> vertices = new LinkedList<Integer>();
	public static HashMap<Integer, ArrayList<Integer>> Grev = new HashMap<Integer, ArrayList<Integer>>();
	public HashMap<Integer, ArrayList<Integer>> G = new HashMap<Integer, ArrayList<Integer>>();
	 
	public void readAdjlist(String str) throws IOException{
		BufferedReader rd = new BufferedReader(new FileReader(str));
		while(true){
		     String line = rd.readLine();
		     if (line == null) break;
		     
		     String[] linearr = line.split("\\s+");
		     vertices.add(Integer.valueOf(linearr[0]));
		     
		     ArrayList<Integer> neighbor = new ArrayList<Integer>();
		     for(int i = 1; i < linearr.length; i++){
		    	 neighbor.add(Integer.valueOf(linearr[i]));
		     }
				
		     Grev.put(Integer.valueOf(linearr[0]), neighbor);  
		}
		rd.close();	
//		Iterator<Integer> it = Grev.get(9).iterator();
//		while (it.hasNext()){
//			System.out.println("print vetex: " + it.next());
//		}
	}
	
	public void reverseG(){
		for (int v : vertices){
			ArrayList<Integer> neighbor = Grev.get(v);
			for(int n : neighbor){
				if (G.get(n) == null){
					ArrayList<Integer> neighborRev = new ArrayList<Integer>();
					neighborRev.add(v);
					G.put(n, neighborRev);
				}else{
					G.get(n).add(v);
				}
			}
		}
		
		for (int v : G.get(9)){
			System.out.println("print reversed vetex: " + v);
		}
	}
	
	// the number of vertices that have been fully explored
	public int t = 0;
	public ArrayList<Integer> explored = new ArrayList<Integer>();
	public HashMap<Integer, Integer> fv = new HashMap<Integer, Integer>();
	
	// the vertex from which the last DGS call was invoked
	public int s; 
	public ArrayList<Integer> scclist = new ArrayList<Integer>();
	
	public void DFSLoop(HashMap<Integer, ArrayList<Integer>> G){
		for (int i = vertices.size(); i > 0; i--){
			if (!explored.contains(i)){
				s = i;
				DFS(G, i);
			}
		}
		
	}
	
	public void DFS(HashMap<Integer, ArrayList<Integer>> G, int i){
		explored.add(i);
		ArrayList<Integer> neighbor = G.get(i);
		for (int j : neighbor){
			if (!explored.contains(j)){
				DFS(G,j);
			}
		}
		
		t++;
		fv.put(i, t);
	}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SCC scc = new SCC();
		scc.readAdjlist("TestSCC.txt");
		scc.reverseG();
		
	}

}
