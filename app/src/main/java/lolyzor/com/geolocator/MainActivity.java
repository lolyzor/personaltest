package lolyzor.com.geolocator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import lolyzor.com.geolocator.services.SearchFourSquare;


public class MainActivity extends ActionBarActivity implements LocationListener{
    private LocationManager locationManager;
    private boolean locationTrackingEnabled;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            goToLocationListActivity(null);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLocationListener();
    }

    private void initLocationListener() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(SearchFourSquare.FOURSQUARE_SEARCH_INTENT_ID));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void searchLocation(View v){
        EditText locationNameEditText = (EditText) findViewById(R.id.location_name);
        String keyword = locationNameEditText.getText().toString();
        startSearchService(true, keyword);
    }

    private void startSearchService(boolean normalSearch, String data) {
        Intent intent = new Intent(this, SearchFourSquare.class);
        intent.putExtra(SearchFourSquare.LOCATION_KEY_NAME,data);
        intent.putExtra(SearchFourSquare.SEARCH_TYPE_KEY_NAME,normalSearch);
        startService(intent);
    }

    public void getCurrentLocation(View v){
        locationTrackingEnabled = true;
        requestSingleLocationUpdate();
    }

    private void requestSingleLocationUpdate() {
        Criteria criteria = new Criteria();
        locationManager.requestSingleUpdate(criteria,this,null);
        System.out.println("MainActivity.requestSingleLocationUpdate");
    }

    public void goToLocationListActivity(View v){
        Intent intent = new Intent(this,LocationListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLocationChanged(Location location) {
        System.out.println("MainActivity.onLocationChanged");
        System.out.println("location = " + location);
        startSearchService(false,location.getLongitude()+","+location.getLatitude());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
