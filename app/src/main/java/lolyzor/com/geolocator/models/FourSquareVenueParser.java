package lolyzor.com.geolocator.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import lolyzor.com.geolocator.models.core.Category;
import lolyzor.com.geolocator.models.core.FoursquareLocation;
import lolyzor.com.geolocator.models.core.HereNow;
import lolyzor.com.geolocator.models.core.Icon;
import lolyzor.com.geolocator.models.core.Location;
import lolyzor.com.geolocator.models.core.Specials;
import lolyzor.com.geolocator.models.core.Stats;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class FourSquareVenueParser {
    private String jsonResponse;

    public FourSquareVenueParser(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public List<FoursquareLocation> getParsedLocations(){
        ArrayList<FoursquareLocation> locations  = new ArrayList <FoursquareLocation>();
        try {
            JSONObject parentJsonObject = new JSONObject(jsonResponse);
            JSONObject response = parentJsonObject.getJSONObject("response");
            JSONArray venues = response.getJSONArray("venues");
            for (int i = 0; i < venues.length(); i++) {
                JSONObject venue = venues.getJSONObject(i);
                FoursquareLocation foursquareLocation = getSingleFourSquareLocation(venue);
                foursquareLocation.setJson(venue.toString());
                locations.add(foursquareLocation);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public FoursquareLocation getSingleFourSquareLocation(JSONObject venue) throws JSONException {
        FoursquareLocation foursquareLocation = new FoursquareLocation(
                venue.getString("id"),
                venue.getString("name"),
                getLocationObject(venue),
                getCategoryObject(venue),
                venue.getBoolean("verified"),
                getStatsObject(venue),
                getSpecialsObject(venue),
                getHereNowObject(venue));
        return foursquareLocation;
    }

    public FoursquareLocation getSingleFourSquareLocation(String json){
        try {
            JSONObject venue = new JSONObject(json);
            FoursquareLocation foursquareLocation = new FoursquareLocation(
                    venue.getString("id"),
                    venue.getString("name"),
                    getLocationObject(venue),
                    getCategoryObject(venue),
                    venue.getBoolean("verified"),
                    getStatsObject(venue),
                    getSpecialsObject(venue),
                    getHereNowObject(venue));
            return foursquareLocation;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HereNow getHereNowObject(JSONObject venue) throws JSONException {
        JSONObject hereNowJson = venue.getJSONObject("hereNow");
        HereNow hereNow = new HereNow(
                hereNowJson.getInt("count"),
                hereNowJson.getString("summary")
        );
        return  hereNow;
    }

    private Specials getSpecialsObject(JSONObject venue) throws JSONException {
        JSONObject specialJson = venue.getJSONObject("specials");
        Specials specials = new Specials(
                specialJson.getInt("count")
        );
        return specials;
    }

    private Stats getStatsObject(JSONObject venue) throws JSONException {
        JSONObject statsJson = venue.getJSONObject("stats");
        Stats stats = new Stats(
                statsJson.getInt("checkinsCount"),
                statsJson.getInt("usersCount"),
                statsJson.getInt("tipCount")
        );
        return stats;
    }

    private List<Category> getCategoryObject(JSONObject venue) throws JSONException {
        JSONArray categories = venue.getJSONArray("categories");
        List<Category> categoryList = new ArrayList<Category>();
        for (int i = 0; i < categories.length(); i++) {
            JSONObject categoryJson = categories.getJSONObject(0);
            Category category = new Category(
                    categoryJson.getString("id"),
                    categoryJson.getString("name"),
                    categoryJson.getString("pluralName"),
                    categoryJson.getString("shortName"),
                    getIconObject(categoryJson),
                    categoryJson.getBoolean("primary")
            );
            categoryList.add(category);
        }
        return categoryList;
    }

    private Icon getIconObject(JSONObject categories) throws JSONException {
        JSONObject iconJson = categories.getJSONObject("icon");
        Icon icon = new Icon(
                iconJson.getString("prefix"),
                iconJson.getString("suffix")
        );
        return icon;
    }

    private Location getLocationObject(JSONObject venue) throws JSONException {
        JSONObject locationJsonObject = venue.getJSONObject("location");
        Location location = new Location(
                locationJsonObject.optString("address"),
                locationJsonObject.optString("crossStreet"),
                locationJsonObject.getDouble("lat"),
                locationJsonObject.getDouble("lng"),
                locationJsonObject.optInt("distance"),
                locationJsonObject.optString("cc"),
                locationJsonObject.optString("city"),
                null
        );
        return location;
    }
}
