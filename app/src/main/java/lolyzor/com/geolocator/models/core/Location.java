package lolyzor.com.geolocator.models.core;

import java.util.List;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class Location {
    private String address;
    private String crossStreet;
    private double latitude;
    private double longitude;
    private int distance;
    private String cc;
    private String city;
    private List<String> formattedAddress;

    public Location(String address, String crossStreet, double latitude, double longitude, int distance, String cc, String city, List<String> formattedAddress) {
        this.address = address;
        this.crossStreet = crossStreet;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.cc = cc;
        this.city = city;
        this.formattedAddress = formattedAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getDistance() {
        return distance;
    }

    public String getCc() {
        return cc;
    }

    public String getCity() {
        return city;
    }

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }
}
