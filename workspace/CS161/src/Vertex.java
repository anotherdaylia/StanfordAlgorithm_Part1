
public class Vertex {
	int vertex;
	int fv;
	boolean explored;
	
	public Vertex(int v, int f, boolean e){
		this.vertex = v;
		this.fv = f;
		this.explored = e;
	}
	
	public Vertex(int v, boolean e){
		this.vertex = v;
		this.fv = 0;
		this.explored = e;
	}
	
	public Vertex(){
		
	}
	
}
