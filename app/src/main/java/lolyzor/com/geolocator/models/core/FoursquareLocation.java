package lolyzor.com.geolocator.models.core;

import java.util.List;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class FoursquareLocation {
    private String id;
    private String name;
    private Location location;
    private List<Category> category;
    private boolean verified;
    private Stats stats;
    private Specials specials;
    private HereNow hereNow;
    private String json;
    public static String LOCATION_KEY_NAME = "location_lat_lng";

    public FoursquareLocation(String id, String name, Location location, List<Category> category, boolean verified, Stats stats, Specials specials, HereNow hereNow) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.category = category;
        this.verified = verified;
        this.stats = stats;
        this.specials = specials;
        this.hereNow = hereNow;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public List<Category> getCategory() {
        return category;
    }

    public boolean isVerified() {
        return verified;
    }

    public Stats getStats() {
        return stats;
    }

    public Specials getSpecials() {
        return specials;
    }

    public HereNow getHereNow() {
        return hereNow;
    }


    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return name;
    }
}
