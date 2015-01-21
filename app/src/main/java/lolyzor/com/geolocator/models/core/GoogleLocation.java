package lolyzor.com.geolocator.models.core;

/**
 * Created by Abdurahman on 1/21/2015.
 */
public class GoogleLocation {
    private String formatted_address;

    public GoogleLocation(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getFormatted_address() {
        return formatted_address;
    }
}
