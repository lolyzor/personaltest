package lolyzor.com.geolocator.models.core;

import junit.framework.TestCase;

import java.util.List;

import lolyzor.com.geolocator.network.FourSquareWrapper;

public class FourSquareWrapperTest extends TestCase {

    public void testSearchLocations() throws Exception {
        FourSquareWrapper fourSquareWrapper = new FourSquareWrapper();
        List<FoursquareLocation> locations = fourSquareWrapper.searchLocation("location");
        assertEquals(0,locations.size());
    }
}