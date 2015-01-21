package lolyzor.com.geolocator.network;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class ResponseObject {
    private int resultCode;
    private String jsonResult;

    public ResponseObject(int resultCode, String jsonResult) {
        this.resultCode = resultCode;
        this.jsonResult = jsonResult;
    }

    public int getResponseCode() {
        return resultCode;
    }

    public String getResponseString() {
        return jsonResult;
    }
}
