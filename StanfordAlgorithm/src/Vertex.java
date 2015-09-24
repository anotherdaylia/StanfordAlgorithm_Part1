import java.util.*;

public class Vertex {
	int vertex;
	int fv = -1;
	int leader = -1;
	boolean explored = false;
	LinkedList<Integer> neighbor = new LinkedList<Integer>();
	
	public Vertex(int v) {
		this.vertex = v;
	}
	
	public Vertex(int v, int fv) {
		this.vertex = v;
		this.fv = fv;
	}
	
	public void addEdge(Integer vertex) {
		 neighbor.add(vertex);
	}
	
	public void removeEdge(Integer vertex) {
		neighbor.remove(vertex);
	}
	
	@Override
    public String toString() {
            return "leader:" + leader + ", time:" + fv + ", explored: " + explored + ", edge:" + neighbor;
    }
}
