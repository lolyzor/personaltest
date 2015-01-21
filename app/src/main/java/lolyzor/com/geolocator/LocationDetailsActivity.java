package lolyzor.com.geolocator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import lolyzor.com.geolocator.models.core.FoursquareLocation;
import lolyzor.com.geolocator.services.SearchGoogleGEOData;


public class LocationDetailsActivity extends ActionBarActivity {
    private TextView fullAddressTextView;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String fullAddress = intent.getStringExtra(SearchGoogleGEOData.ADDRESS_KEY);
            updateTextField(fullAddress);
        }
    };

    private void updateTextField(String fullAddress) {
        fullAddressTextView.setText(fullAddress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(SearchGoogleGEOData.GOOGLE_GEO_DATA_READY_INTENT_ID));
        sendQueryToGoogle();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);
        getTextAddressTextView();
        sendQueryToGoogle();
    }

    private void sendQueryToGoogle() {
        String location = getIntent().getStringExtra(FoursquareLocation.LOCATION_KEY_NAME);
        Intent startGoogleQuery = new Intent(this, SearchGoogleGEOData.class);
        startGoogleQuery.putExtra(FoursquareLocation.LOCATION_KEY_NAME,location);
        startService(startGoogleQuery);
    }

    private void getTextAddressTextView() {
        fullAddressTextView = (TextView) findViewById(R.id.venue_address);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_location_details, menu);
        return true;
    }
}
