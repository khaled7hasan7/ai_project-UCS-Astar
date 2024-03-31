package ai_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class controller {

	private HashMap<String, Node> hashtab = new HashMap<String, Node>();
	int time;
	int space;
	private static final double EARTH_RADIUS = 6371;
	private ArrayList<City> citylist = new ArrayList<>();
	private ArrayList<Line> linelist = new ArrayList<>();
	private ObservableList<String> pathCitieslist = FXCollections.observableArrayList();
	private ArrayList<street_distance> costlist = new ArrayList<>();
	private ArrayList<citylong> citylat = new ArrayList<>();

	@FXML
	private Label tx_X;

	@FXML
	private Label tx_Y;

	@FXML
	private TextField tx_cost;

	@FXML
	private AnchorPane pane;

	@FXML
	private TextArea path;

	@FXML
	private ChoiceBox<String> searchType;

	@FXML
	private ChoiceBox<String> source;

	@FXML
	private TextField spaceView;

	@FXML
	private ChoiceBox<String> target;

	@FXML
	private TextField timeView;

	@FXML
	void initialize() throws Exception {

		source.setValue("null");
		target.setValue("null");
		searchType.setValue("null");

		readcityfiles();
		readdistancefile();
		createcircels();
		addAdjacents();
		readelong();

	}

	@FXML
	void colse(ActionEvent event) {
		System.exit(0);

	}

	@FXML
	void maousMoved(MouseEvent event) {

		// System.out.println("x" + event.getX());
		tx_X.setText("   X : " + event.getX());
		tx_Y.setText("   Y : " + event.getY());

	}

	@FXML
	void run(ActionEvent event) throws FileNotFoundException {

		if (source.getValue().equals("null")) {

			JOptionPane.showMessageDialog(null, " Please select a source City", "hint : ", JOptionPane.PLAIN_MESSAGE);

		} else if (target.getValue().equals("null")) {

			JOptionPane.showMessageDialog(null, " Please select a target City", "hint : ", JOptionPane.PLAIN_MESSAGE);

		} else if ((source.getValue()).equals((target.getValue()))) {

			JOptionPane.showMessageDialog(null, "You are already in the city", "hint!", JOptionPane.PLAIN_MESSAGE);

		} else if (searchType.getValue().equals("null")) {

			JOptionPane.showMessageDialog(null, " Please select a search Type", "hint!", JOptionPane.PLAIN_MESSAGE);

		}

		else {

			try {
				startSerchpath(CheckCityIfExists(source.getValue()), CheckCityIfExists(target.getValue()));

				for (int i = 0; i < linelist.size(); i++) {
					linelist.get(i).setStrokeWidth(1.5);

					pane.getChildren().add(linelist.get(i));

				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("rum method 111 line");
			}
		}

//		try {
//			
//			
//		}catch ( Exception e) {
//			
//			
//		}

	}

	public void readelong() throws FileNotFoundException {

		File file = new File("AirDistance.csv");

		Scanner in = new Scanner(file);

		while (in.hasNextLine()) {

			String line = in.nextLine();
			// System.out.println(line);

			String[] str = line.trim().replaceAll("\\s", " ").split("[ ]");

			citylat.add(new citylong(str[0], Double.parseDouble(str[1]), Double.parseDouble(str[2])));

		}

		in.close();

	}

	private void readcityfiles() throws FileNotFoundException {

		File file = new File("Cities.csv");

		Scanner in = new Scanner(file);

		searchType.getItems().add("A*");
		searchType.getItems().add("UCS");

		while (in.hasNextLine()) {

			String line = in.nextLine();
			// System.out.println(line);

			String[] spStr = line.trim().replaceAll("\\s", " ").split("[ ]");

			citylist.add(new City(Double.parseDouble(spStr[1]), Double.parseDouble(spStr[2]), spStr[0], new Circle()));

			target.getItems().add(spStr[0]);
			source.getItems().add(spStr[0]);

		}

		in.close();

	}

	private City CheckCityIfExists(String city) {

		for (int i = 0; i < citylist.size(); i++) {
			if (citylist.get(i).getName().equalsIgnoreCase(city)) {
				return citylist.get(i);
			}
		}
		return null;
	}

	public void readdistancefile() throws Exception {

		String filepath = "Roads.csv";
		File file = new File(filepath);
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().trim().replaceAll("\\s", " ").split("[ ]");

			costlist.add(new street_distance(CheckCityIfExists(line[0]), CheckCityIfExists(line[1]),
					Double.parseDouble(line[2])));

		}

		scanner.close();
	}

	int number = -5 ;
	
	public void createcircels() {

		for (int i = 0; i < citylist.size(); i++) {

			Circle circle = new Circle(5);

			circle.setCenterX(citylist.get(i).getPositionX());
			circle.setCenterY(citylist.get(i).getPositionY());
			circle.setFill(Color.SKYBLUE);

			Label label = new Label(citylist.get(i).getName());
			label.setLayoutX(citylist.get(i).getPositionX() - 15);
			label.setLayoutY(citylist.get(i).getPositionY());
			label.setTextFill(Color.BLACK);

			citylist.get(i).setCircle(circle);
			pane.getChildren().addAll(citylist.get(i).getCircle(), label);

			String name = citylist.get(i).toString();

			circle.setOnMouseClicked(e -> {
				circle.setFill(Color.BLUE);

				if (number < 0) {
					source.setValue(name);
					number *= -1;

				} else if (number > 0) {
					target.setValue(name);
					number *= -1;
				}

			});
			
			circle.setOnMouseEntered(e -> {
				circle.setFill(Color.RED);
			});
			circle.setOnMouseExited(e -> {
				circle.setFill(Color.SKYBLUE);
			});

//			
//			
//			boolean flag = true;
//			circle.setOnMouseClicked(e -> {
//
//				if (flag) {
//					
//					
//					
//
//				} else if (flag == false) {
//					
//				}
//			});

		}

	}

	

	public double calculateDistance(String current, String target) {

		for (int i = 0; i < citylat.size(); i++) {

			if (citylat.get(i).getCityname().equals(current)) {
				for (int j = 0; j < citylat.size(); j++) {

					if (citylat.get(j).getCityname().equals(target)) {

						double lat1 = citylat.get(i).getLat();
						double lon1 = citylat.get(i).getLongt();
						double lat2 = citylat.get(j).getLat();
						double lon2 = citylat.get(j).getLongt();

						double lat1Rad = Math.toRadians(lat1);
						double lon1Rad = Math.toRadians(lon1);
						double lat2Rad = Math.toRadians(lat2);
						double lon2Rad = Math.toRadians(lon2);

						double dLat = lat2Rad - lat1Rad;
						double dLon = lon2Rad - lon1Rad;

						double a = Math.pow(Math.sin(dLat / 2), 2)
								+ Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dLon / 2), 2);
						double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
						double distance = EARTH_RADIUS * c;

						return distance;
					}

				}
			}

		}
		return -5665.5;

	}

	public void startSerchpath(City sourceCity, City targetCity) {
		time = 0;
		space = 0;
		if (searchType.getValue().equals("UCS")) {
			UCS(sourceCity, targetCity);
		} else {
			AStar(sourceCity, targetCity);
		}

		for (int i = 0; i < linelist.size(); i++) {
			pane.getChildren().remove(linelist.get(i));
		}

		linelist.clear();

		pathCitieslist.clear();

		path.setText("");

		tx_cost.setText("0.0");
		timeView.setText("0");
		spaceView.setText("0");

		if (hashtab.get(targetCity.getName()).getDistance() != Double.POSITIVE_INFINITY
				&& hashtab.get(targetCity.getName()).getDistance() != 0) {
			shortestPath(sourceCity, targetCity);
			DecimalFormat df = new DecimalFormat("###.###");
			Node t = hashtab.get(targetCity.getName());
			tx_cost.setText(df.format(t.getDistance()) + " KM");
			timeView.setText(df.format(time));
			spaceView.setText(df.format(space));

			path.setText(sourceCity.getName() + " (source) -->");
			for (int i = pathCitieslist.size() - 1; i >= 0; i--) {
				if (i == 0) {
					path.appendText("\n" + pathCitieslist.get(i) + " (target)");
				} else {
					path.appendText("\n" + pathCitieslist.get(i) + " -->");
				}
			}
		} else {

			JOptionPane.showMessageDialog(null, "no path ", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void shortestPath(City sourceCity, City targetCity) {
		pathCitieslist.add(targetCity.getName());

		Node node = hashtab.get(targetCity.getName());

		if (node.getSourceCity() == null) {
			return;
		}
		if (node.getSourceCity() == sourceCity) {

			if (sourceCity != targetCity) {
				linelist.add(new Line(node.getSourceCity().getPositionX(), node.getSourceCity().getPositionY(),
						targetCity.getPositionX(), targetCity.getPositionY()));
			}
			return;
		}

		linelist.add(new Line(node.getSourceCity().getPositionX(), node.getSourceCity().getPositionY(),
				targetCity.getPositionX(), targetCity.getPositionY()));
		shortestPath(sourceCity, node.getSourceCity());
	}

	private void addAdjacents() {

		for (int i = 0; i < citylist.size(); i++) {
			for (int j = 0; j < costlist.size(); j++) {
				if (citylist.get(i).getName().equalsIgnoreCase(costlist.get(j).getCity1().getName())) {
					City c = costlist.get(j).getCity2();
					Adjacent n = new Adjacent(c, costlist.get(j).getCost());
					citylist.get(i).getAdjacent().add(n);
				} else if (citylist.get(i).getName().equalsIgnoreCase(costlist.get(j).getCity2().getName())) {
					City c = costlist.get(j).getCity1();
					Adjacent n = new Adjacent(c, costlist.get(j).getCost());
					citylist.get(i).getAdjacent().add(n);
				}
			}
		}

	}

	private void UCS(City source, City targetCity) {

		hashtab.clear();
		for (City i : citylist) {
			hashtab.put(i.getName(), new Node(i, null, false, Double.POSITIVE_INFINITY));
		}

		comparenode comp = new comparenode();
		Queue<Node> q = new PriorityQueue<Node>(10, comp);

		Node node = hashtab.get(source.getName());
		node.setDistance(0.0);
		node.setKnown(true);
		q.add(node);
		space = 1;
		time = 0;

		while (!q.isEmpty()) {
			Node temp = q.poll();
			temp.setKnown(true);
			if (temp.getCurrentCity() == targetCity) {
				time++;
				break;
			}
			ArrayList<Adjacent> adj = temp.getCurrentCity().getAdjacent();

			for (Adjacent c : adj) {
				Node t = hashtab.get(c.getCity().getName());
				q.remove(t);
				if (t.isKnown()) {
					time++;
					continue;
				}
				time++;

				double newDis = c.getDistance() + temp.getDistance();
				if (newDis < t.getDistance()) {
					t.setSourceCity(temp.getCurrentCity());
					t.setDistance(newDis);
				}
				q.add(t);
			}

			if (q.size() > space) {
				space = q.size();
			}

		}
	}
	
	private void AStar(City source, City targetCity) {
		hashtab.clear();
		for (City i : citylist) {
			hashtab.put(i.getName(), new Node(i, null, false, Double.POSITIVE_INFINITY));
		}

		AStarCompare comp = new AStarCompare();
		Queue<AStarCity> q = new PriorityQueue<AStarCity>(10, comp);

		AStarCity city = new AStarCity(hashtab.get(source.getName()), 0.0);
		city.getNode().setDistance(0.0);
		city.getNode().setKnown(true);
		city.setHeuristic(0.0);
		q.add(city);
		space = 1;
		time = 0;
		while (!q.isEmpty()) {
			AStarCity temp = q.poll();
			temp.getNode().setKnown(true);
			if (temp.getNode().getCurrentCity() == targetCity) {
				time++;
				break;
			}
			ArrayList<Adjacent> adj = temp.getNode().getCurrentCity().getAdjacent();

			for (Adjacent c : adj) {
				AStarCity t = new AStarCity(hashtab.get(c.getCity().getName()), null);
				Iterator<AStarCity> itr = q.iterator();

				while (itr.hasNext()) {
					AStarCity cit = itr.next();
					if (cit.getNode().equals(hashtab.get(c.getCity().getName()))) {
						q.remove(cit);
						break;
					}
				}
				if (t.getNode().isKnown()) {
					time++;
					continue;
				}
				time++;
				double newDis = c.getDistance() + temp.getNode().getDistance();
				if (newDis < t.getNode().getDistance()) {
					t.getNode().setSourceCity(temp.getNode().getCurrentCity());
					t.getNode().setDistance(newDis);
				}
			
				double costnum = calculateDistance(source.getName(), targetCity.getName());
				double heuristic = costnum;
				t.setHeuristic(heuristic);

				q.add(t);
			}
			if (q.size() > space) {
				space = q.size();
			}

		}
	}

	public class comparenode implements Comparator<Node> {

		@Override
		public int compare(Node node1, Node node2) {

			return (int) (node1.getDistance() - node2.getDistance());
		}

	}

	public class AStarCompare implements Comparator<AStarCity> {

		@Override
		public int compare(AStarCity city1, AStarCity city2) {

			return (int) (city1.getHeuristic() - city2.getHeuristic());
		}

	}

//	public double getDistance(double fromLatitude, double fromLongitude, double toLatitude, double toLongitude) {
//
//		// Convert latitudes nad longitude to radians
//		fromLatitude = Math.toRadians(fromLatitude);
//		fromLongitude = Math.toRadians(fromLongitude);
//		toLatitude = Math.toRadians(toLatitude);
//		toLongitude = Math.toRadians(toLongitude);
//
//		// Haversine formula
//		double term1Input = (toLatitude - fromLatitude) / 2;
//		double term1 = Math.pow(Math.sin(term1Input), 2);
//
//		double term2 = Math.cos(fromLatitude) * Math.cos(toLatitude);
//
//		double term3Input = (toLongitude - fromLongitude) / 2;
//		double term3 = Math.pow(Math.sin(term3Input), 2);
//
//		double combineTerms = term1 + term2 * term3;
//
//		double distance = EARTH_RADIUS * 2 * Math.asin(Math.sqrt(combineTerms));
//
//		return distance;
//
//	}

//	public double[] getLatLong(double xCoordinates, double yCoordinates) {
//
//		int geo_left = -180;
//		int geo_right = 180;
//		int geo_top = 80;
//		int geo_bottom = -80;
//
//		// dimensions of the canvas
//		int canvas_width = 1097;
//		int canvas_height = 626;
//
//		// get relative coordinate of pixel, should be on a scale of 0 to 1
//		double rel_x = yCoordinates / canvas_width;
//		double rel_y = xCoordinates / canvas_height;
//
//		// linear interpolate to find latitude and longitude
//		double latitude = geo_left + (geo_right - geo_left) * rel_x;
//		double longitude = geo_top + (geo_bottom - geo_top) * rel_y;
//
//		return new double[] { latitude, longitude };
//	}

}
