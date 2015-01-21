package lolyzor.com.geolocator.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class HttpEngine {
    public ResponseObject makeRequest(String url) {
        return new ResponseObject(200, "");
    }

    public static ResponseObject makeGetRequest(String url, HashMap<String, String> data){
        return getJSONResponse(url);
    }

    private static ResponseObject getJSONResponse(String url){
        try {
            URL url1 = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("responseCode = " + responseCode);
            InputStream inputStream = httpURLConnection.getInputStream();
            String contentAsString = readIt(inputStream);
            System.out.println("contentAsString = " + contentAsString);
            return new ResponseObject(responseCode,contentAsString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
}
