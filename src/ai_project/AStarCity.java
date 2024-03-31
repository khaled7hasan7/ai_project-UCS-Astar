package ai_project;

public class AStarCity {
	
	Node node;
    Double heuristic;
	public AStarCity(Node node, Double heuristic) {
		super();
		this.node = node;
		this.heuristic = heuristic;
	}
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public Double getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(Double heuristic) {
		this.heuristic = heuristic;
	}
	@Override
	public String toString() {
		return "AStarCity [node=" + node + ", heuristic=" + heuristic + "]";
	}
    
    
    
    

}
