package lolyzor.com.geolocator.models.core;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class Icon {
    private String prefix;
    private String suffix;

    public Icon(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }
}
