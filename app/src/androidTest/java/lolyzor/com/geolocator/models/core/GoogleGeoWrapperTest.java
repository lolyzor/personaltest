package lolyzor.com.geolocator.models.core;

import junit.framework.TestCase;

import lolyzor.com.geolocator.network.GoogleGeoWrapper;

public class GoogleGeoWrapperTest extends TestCase {

    public void testGeoLocate() throws Exception {
        GoogleGeoWrapper googleGeoWrapper = new GoogleGeoWrapper();
        //google rejects access
        assertEquals("277 Bedford Avenue, Brooklyn, NY 11211, USA", googleGeoWrapper.geoLocate("40.714224,-73.961452"));
    }
}