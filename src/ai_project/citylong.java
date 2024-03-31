package ai_project;

public class citylong {
	
	String cityname  ;
	double lat ;
	double longt ;
	public citylong(String cityname, double lat, double longt) {
		super();
		this.cityname = cityname;
		this.lat = lat;
		this.longt = longt;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLongt() {
		return longt;
	}
	public void setLongt(double longt) {
		this.longt = longt;
	}
	
	

}
