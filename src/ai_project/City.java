package ai_project;


import java.util.ArrayList;


import javafx.scene.shape.Circle;

public class City {

	private double positionX;
	private double positionY;
	
	private String name;
	private Circle circle;
	private ArrayList<Adjacent> adjacents = new ArrayList<>();;

	public City(double positionX, double positionY, String name, Circle circle) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
		this.name = name;
		this.circle = circle;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}


	public ArrayList<Adjacent> getAdjacent() {
		return adjacents;
	}

	public void setAdjacent(ArrayList<Adjacent> adjacent) {
		this.adjacents = adjacent;
	}


	@Override
	public String toString() {
		return name ;
	}

	public String fullToString() {
		return "City [coordinateX=" + positionX + ", coordinateY=" + positionY + ", name=" + name + ", circle="
				+ circle + ", adjacent=" + adjacents + "]";

	}
}
