package lolyzor.com.geolocator.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lolyzor.com.geolocator.network.HttpEngine;
import lolyzor.com.geolocator.network.ResponseObject;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class GoogleGeoWrapper {
    private final String GOOGLE_API_KEY = "AIzaSyD2cx3CKYyecBPTCJIO19QHm1tLwx1nPbQ";
    private final String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json?";

    public String geoLocate(String location) {
        String finalURL = BASE_URL +
                "&latlng=" + location +
                "&key=" + GOOGLE_API_KEY;
        ResponseObject responseObject  = HttpEngine.makeGetRequest(finalURL, null);
        String json = responseObject.getResponseString();
        return parseJson(json);
    }

    private String parseJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray results = jsonObject.getJSONArray("results");
            JSONObject oneAddress = results.getJSONObject(0);
            return oneAddress.getString("formatted_address");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
