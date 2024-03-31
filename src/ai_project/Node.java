package ai_project;



public class Node {

	private City currentCity;
	private City sourceCity;
	private boolean known;
	private double distance;

	public Node(City currentCity, City sourceCity, boolean known, double distance) {
		super();
		this.currentCity = currentCity;
		this.sourceCity = sourceCity;
		this.known = known;
		this.distance = distance;
	}

	public City getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(City currentCity) {
		this.currentCity = currentCity;
	}

	public City getSourceCity() {
		return sourceCity;
	}

	public void setSourceCity(City sourceCity) {
		this.sourceCity = sourceCity;
	}

	public boolean isKnown() {
		return known;
	}

	public void setKnown(boolean known) {
		this.known = known;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	
}
