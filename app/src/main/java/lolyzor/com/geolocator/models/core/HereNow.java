package lolyzor.com.geolocator.models.core;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class HereNow {
    private int count;
    private String summary;

    public HereNow(int count, String summary) {
        this.count = count;
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public int getCount() {
        return count;
    }
}
