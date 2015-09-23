
public class Score {
	int vertex;
	long score;
	
	public Score(int v, long s) {
		this.vertex = v;
		this.score = s;
	}
	
	public Score() {
	}
	
	@Override
	public boolean equals(Object o){
		Score o2 = (Score) o;
		if (this.vertex == o2.vertex) {
			return true;
		} else {
			return false;
		}
	}
}
