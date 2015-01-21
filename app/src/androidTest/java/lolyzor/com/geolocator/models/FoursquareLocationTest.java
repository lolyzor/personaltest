package lolyzor.com.geolocator.models;

import junit.framework.TestCase;

import java.util.ArrayList;

import lolyzor.com.geolocator.models.core.Category;
import lolyzor.com.geolocator.models.core.FoursquareLocation;
import lolyzor.com.geolocator.models.core.HereNow;
import lolyzor.com.geolocator.models.core.Icon;
import lolyzor.com.geolocator.models.core.Location;
import lolyzor.com.geolocator.models.core.Specials;
import lolyzor.com.geolocator.models.core.Stats;

public class FoursquareLocationTest extends TestCase {


    public void testObjectCreation(){
        Location location = new Location("some","",0.0,0.0,0,"BA","Ze",new ArrayList<String>());
        Icon icon = new Icon("url",".png");
        Category category = new Category("1", "name","names","na",icon,false);
        Stats stats = new Stats(10,20,1);
        Specials specials = new Specials(0);
        HereNow hereNow = new HereNow(4,"5 ppl here");
        FoursquareLocation foursquareLocation = new FoursquareLocation("1", "Zurnal",
                location, null, true, stats, specials, hereNow);
        assertEquals(foursquareLocation.getHereNow(),hereNow);
    }

}