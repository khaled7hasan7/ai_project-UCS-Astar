package ai_project;



public class street_distance {
	

	private City city1;
	private City city2;
	private double cost;
	
	
	public street_distance(City city1, City city2, double cost) {
		super();
		this.city1 = city1;
		this.city2 = city2;
		this.cost = cost;
	}
	public City getCity1() {
		return city1;
	}
	public void setCity1(City city1) {
		this.city1 = city1;
	}
	public City getCity2() {
		return city2;
	}
	public void setCity2(City city2) {
		this.city2 = city2;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "street_distance [city1=" + city1 + ", city2=" + city2 + ", cost=" + cost + "]";
	}
	
	

}
