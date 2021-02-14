package apiTests.pojos.placePojos;

public class Location {

      private  double lat;
      private double lng;



    public Location(double latitude, double longitude) {
        this.lat = latitude;
        this.lng = longitude;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
