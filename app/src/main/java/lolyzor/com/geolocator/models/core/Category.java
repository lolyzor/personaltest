package lolyzor.com.geolocator.models.core;

/**
 * Created by Abdurahman on 1/17/2015.
 */
public class Category {
    private String id;
    private String name;
    private String pluralName;
    private String shortName;
    private Icon icon;
    private boolean primary;

    public Category(String id, String name, String pluralName, String shortName, Icon icon, boolean primary) {
        this.id = id;
        this.name = name;
        this.pluralName = pluralName;
        this.shortName = shortName;
        this.icon = icon;
        this.primary = primary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPluralName() {
        return pluralName;
    }

    public String getShortName() {
        return shortName;
    }

    public Icon getIcon() {
        return icon;
    }

    public boolean isPrimary() {
        return primary;
    }
}
