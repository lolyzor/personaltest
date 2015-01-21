package lolyzor.com.geolocator.network;

import junit.framework.TestCase;

public class HttpEngineTest extends TestCase {

    public void testMakeRequest() throws Exception {

    }

    public void testMakePostRequest() throws Exception {
        HttpEngine httpEngine = new HttpEngine();
        assertNotNull(httpEngine.makeGetRequest("http://www.google.com", null));
    }

    public void testReadIt() throws Exception {

    }
}