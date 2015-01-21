package lolyzor.com.geolocator.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import lolyzor.com.geolocator.database.DatabaseHelper;
import lolyzor.com.geolocator.models.core.FoursquareLocation;

/**
 * Created by Abdurahman on 1/21/2015.
 */
public class FourSquareLocationDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {
            DatabaseHelper.COLUMN_NAME,
    };

    public FourSquareLocationDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }


    public void insertLocations(List<FoursquareLocation> locations){
        //database.beginTransaction();
        for (int i = 0; i < locations.size(); i++) {
            FoursquareLocation foursquareLocation = locations.get(i);
            insertSingleLocation(foursquareLocation);
        }
        //database.setTransactionSuccessful();
    }

    public void insertSingleLocation(FoursquareLocation location){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COLUMN_SERVER_ID, location.getId());
        contentValues.put(DatabaseHelper.COLUMN_NAME, location.getName());
        contentValues.put(DatabaseHelper.COLUMN_JSON, location.getJson());

        database.insert(DatabaseHelper.TABLE_VENUES, null, contentValues);
    }

    public List<FoursquareLocation> getAllLocations(){
        List<FoursquareLocation> locations = new ArrayList<FoursquareLocation>();

        Cursor cursor = database.query(
                DatabaseHelper.TABLE_VENUES,
                new String[]{DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_SERVER_ID},
                null, null, null, null, null);

        while(cursor.moveToNext()){
            FoursquareLocation  foursquareLocation = getSingleLocationNameOnly(cursor);
            locations.add(foursquareLocation);
        }
        cursor.close();

        return locations;
    }

    private FoursquareLocation getSingleLocationNameOnly(Cursor cursor) {
        String locationName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
        String serverId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SERVER_ID));
        FoursquareLocation location = new FoursquareLocation(serverId,
                locationName,
                null,
                null,
                false,
                null,
                null,
                null
        );
        return location;
    }

    public FoursquareLocation getSingleLocation(String serverId, FourSquareVenueParser fourSquareVenueParser) {
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_VENUES,
                new String[]{DatabaseHelper.COLUMN_SERVER_ID,DatabaseHelper.COLUMN_JSON},
                DatabaseHelper.COLUMN_SERVER_ID + " = " + "\"" + serverId + "\"",
                null,null,null,null
                );
        if(cursor.moveToNext()){
            FoursquareLocation location = getSingleFullLocation(cursor, fourSquareVenueParser);
            return location;
        }
        return null;
    }

    private FoursquareLocation getSingleFullLocation(Cursor cursor, FourSquareVenueParser fourSquareVenueParser) {
        String json = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_JSON));
        return fourSquareVenueParser.getSingleFourSquareLocation(json);
    }

    public void emptyTable() {
        database.delete(DatabaseHelper.TABLE_VENUES,null,null);
    }
}
