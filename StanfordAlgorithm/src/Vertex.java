
public class Vertex {
	int vertex;
	int fv;
	boolean explored;
	
	public Vertex(int v, int fv, boolean explored) {
		this.vertex = v;
		this.fv = fv;
		this.explored = explored;
	}
	
	public Vertex(int v, boolean explored) {
		this.vertex = v;
		this.fv = 0;
	}
	
	public Vertex() {
	}
}
