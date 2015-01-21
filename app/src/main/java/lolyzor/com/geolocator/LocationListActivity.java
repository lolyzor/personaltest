package lolyzor.com.geolocator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import lolyzor.com.geolocator.models.FourSquareLocationDAO;
import lolyzor.com.geolocator.models.FourSquareVenueParser;
import lolyzor.com.geolocator.models.core.FoursquareLocation;


public class LocationListActivity extends ActionBarActivity {
    private List<FoursquareLocation> locations;
    private FourSquareLocationDAO fourSquareLocationDAO;
    private FourSquareVenueParser venueParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        initFourSquareDao();
        initList();
    }

    private void initFourSquareDao() {
        fourSquareLocationDAO = new FourSquareLocationDAO(this);
        venueParser = new FourSquareVenueParser(null);
    }

    private void initList() {
        ListView listView = (ListView) findViewById(R.id.list);
        locations = getAllLocations();
        ArrayAdapter<FoursquareLocation> arrayAdapter = new ArrayAdapter<FoursquareLocation>(
                this,
                android.R.layout.simple_list_item_1,
                locations
        );

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoursquareLocation foursquareLocation = locations.get(position);
                foursquareLocation = getFullSingleLocation(foursquareLocation);
                try{
                    String locationGEO = foursquareLocation.getLocation().getLatitude()
                            + ","  +
                            foursquareLocation.getLocation().getLongitude();
                    goToDetailsActivity(locationGEO);
                } catch (NullPointerException ex){
                    //data missing
                }
            }
        });
    }

    private void goToDetailsActivity(String locationGEO) {
        Intent intent = new Intent(this, LocationDetailsActivity.class);
        intent.putExtra(FoursquareLocation.LOCATION_KEY_NAME,locationGEO);
        startActivity(intent);
    }

    private List<FoursquareLocation> getAllLocations() {
        fourSquareLocationDAO.open();
        List<FoursquareLocation> locations = fourSquareLocationDAO.getAllLocations();
        fourSquareLocationDAO.close();
        return locations;
    }

    private FoursquareLocation getFullSingleLocation(FoursquareLocation foursquareLocation){
        fourSquareLocationDAO.open();
        FoursquareLocation updatedLocation = fourSquareLocationDAO.getSingleLocation(foursquareLocation.getId(), venueParser);
        fourSquareLocationDAO.close();
        return updatedLocation;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_location_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
