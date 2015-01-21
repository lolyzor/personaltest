package lolyzor.com.geolocator.services;

import android.app.IntentService;
import android.content.Intent;

import lolyzor.com.geolocator.network.GoogleGeoWrapper;
import lolyzor.com.geolocator.models.core.FoursquareLocation;

/**
 * Created by Abdurahman on 1/21/2015.
 */
public class SearchGoogleGEOData extends IntentService {
    public static final String GOOGLE_GEO_DATA_READY_INTENT_ID = "SearchGoogleGEOData";
    public static final String ADDRESS_KEY = "full_address";

    public SearchGoogleGEOData() {
        super("SearchGoogleGEOData");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String location = intent.getStringExtra(FoursquareLocation.LOCATION_KEY_NAME);
        GoogleGeoWrapper googleGeoWrapper = new GoogleGeoWrapper();
        String fullAddress = googleGeoWrapper.geoLocate(location);
        Intent sendAddressBroadcast = new Intent(GOOGLE_GEO_DATA_READY_INTENT_ID);
        sendAddressBroadcast.putExtra(ADDRESS_KEY,fullAddress);
        sendBroadcast(sendAddressBroadcast);
    }
}
