package lolyzor.com.geolocator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_VENUES = "venues";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SERVER_ID = "serverId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_JSON = "json";

    private static final String DATABASE_NAME = "venues.db";
    private static final int DATABASE_VERSION = 1;

    //sqlite stores varchar as text anyway
    private static final String DATABASE_CREATE = "create table "
            + TABLE_VENUES
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_SERVER_ID + " text not null, "
            + COLUMN_NAME + " text not null,"
            + COLUMN_JSON + " text not null"
            + ");";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENUES);
        onCreate(db);
    }
}
