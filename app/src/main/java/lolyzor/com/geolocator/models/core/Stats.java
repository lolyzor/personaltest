package lolyzor.com.geolocator.models.core;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class Stats {
    private int checkinsCount;
    private int usersCount;
    private int tipCount;

    public Stats(int checkinsCount, int usersCount, int tipCount) {
        this.checkinsCount = checkinsCount;
        this.usersCount = usersCount;
        this.tipCount = tipCount;
    }

    public int getCheckinsCount() {
        return checkinsCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public int getTipCount() {
        return tipCount;
    }
}
