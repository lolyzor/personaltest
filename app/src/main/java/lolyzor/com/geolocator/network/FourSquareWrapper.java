package lolyzor.com.geolocator.network;

import java.util.List;

import lolyzor.com.geolocator.models.FourSquareVenueParser;
import lolyzor.com.geolocator.models.core.FoursquareLocation;
import lolyzor.com.geolocator.network.HttpEngine;
import lolyzor.com.geolocator.network.ResponseObject;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class FourSquareWrapper {
    private final String FSQ_CLIENT_ID = "ZWVRN3VABTX5DSEDTS1TRNXJTQ2SMZCB22U1ESSKPIQ5EHUW";
    private final String FSQ_CLIENT_SECRET = "35PMN100EXVRQ0BI2EPCL4IBNFKLURC2UAMVF5VZCBRWAHEB";
    private final String BASE_URL = "https://api.foursquare.com/v2/venues/search?";

    public List<FoursquareLocation> searchLocation(String location) {
        ResponseObject responseObject = searchFourSquareLL(location);
        FourSquareVenueParser fourSquareVenueParser = new FourSquareVenueParser(responseObject.getResponseString());
        return fourSquareVenueParser.getParsedLocations();
    }

    public List<FoursquareLocation> searchNear(String locationName) {
        ResponseObject responseObject = searchFourSquareNear(locationName);
        FourSquareVenueParser fourSquareVenueParser = new FourSquareVenueParser(responseObject.getResponseString());
        return fourSquareVenueParser.getParsedLocations();
    }


    private ResponseObject searchFourSquareLL(String location){
        String finalURL = BASE_URL +
                "ll=" + location +
                "&client_id=" + FSQ_CLIENT_ID +
                "&client_secret=" + FSQ_CLIENT_SECRET +
                "&v=" + "20150119";
        return HttpEngine.makeGetRequest(finalURL, null);
    }

    private ResponseObject searchFourSquareNear(String locationName){
        String finalURL = BASE_URL +
                "near=" + locationName +
                "&client_id=" + FSQ_CLIENT_ID +
                "&client_secret=" + FSQ_CLIENT_SECRET +
                "&v=" + "20150119";
        return HttpEngine.makeGetRequest(finalURL, null);
    }

    public List<FoursquareLocation> search(boolean normalSearch, String location) {
        if(normalSearch){
            return searchNear(location);
        }
        else{
            return searchLocation(location);
        }
    }
}
