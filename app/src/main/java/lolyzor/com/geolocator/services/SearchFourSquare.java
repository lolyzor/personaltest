package lolyzor.com.geolocator.services;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import lolyzor.com.geolocator.models.FourSquareLocationDAO;
import lolyzor.com.geolocator.network.FourSquareWrapper;
import lolyzor.com.geolocator.models.core.FoursquareLocation;

/**
 * Created by Abdurahman on 1/20/2015.
 */
public class SearchFourSquare extends IntentService {
    public static final String LOCATION_KEY_NAME = "location";
    public static final String FOURSQUARE_SEARCH_INTENT_ID = "SearchFourSquare";
    public static final String SEARCH_TYPE_KEY_NAME = "normalSearch";

    public SearchFourSquare() {
        super("SearchLocations");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String location = intent.getStringExtra(LOCATION_KEY_NAME);
        boolean isNormalSearch = intent.getBooleanExtra(SEARCH_TYPE_KEY_NAME, false);
        FourSquareWrapper fourSquareWrapper = new FourSquareWrapper();
        List<FoursquareLocation> locations = fourSquareWrapper.search(isNormalSearch, location);
        FourSquareLocationDAO fourSquareLocationDAO = new FourSquareLocationDAO(getApplicationContext());
        fourSquareLocationDAO.open();
        fourSquareLocationDAO.emptyTable();
        fourSquareLocationDAO.insertLocations(locations);
        fourSquareLocationDAO.close();
        sendDoneNotification();
    }

    private void sendDoneNotification() {
        Intent sendAddressBroadcast = new Intent(FOURSQUARE_SEARCH_INTENT_ID);
        sendBroadcast(sendAddressBroadcast);
    }
}
