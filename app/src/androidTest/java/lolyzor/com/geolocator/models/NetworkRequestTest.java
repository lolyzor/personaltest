package lolyzor.com.geolocator.models;

import junit.framework.TestCase;

import lolyzor.com.geolocator.network.HttpEngine;
import lolyzor.com.geolocator.network.ResponseObject;

public class NetworkRequestTest extends TestCase {
    public void testRequestResponseCode(){
        HttpEngine httpEngine = new HttpEngine();
        ResponseObject responseObject = httpEngine.makeRequest("url");
        assertEquals(200, responseObject.getResponseCode());
    }

    public void testRequestResponseString(){
        HttpEngine httpEngineWrapper = new HttpEngine();
        ResponseObject responseObject = httpEngineWrapper.makeRequest("url");
        assertEquals("test", responseObject.getResponseString());
    }
}