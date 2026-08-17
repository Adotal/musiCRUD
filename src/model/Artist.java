package model;

public class Artist {

    private int id;
    private String artisticName;
    private String name;
    private String lastnames;
    private String countryOfOrigin;

    public Artist() {

    }

    public Artist(int id, String artisiticName, String name, String lastnames, String countryOfOrigin) {
        this.id = id;
        this.artisticName = artisiticName;
        this.name = name;
        this.lastnames = lastnames;
        this.countryOfOrigin = countryOfOrigin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtisticName() {
        return artisticName;
    }

    public void setArtisticName(String artisiticName) {
        this.artisticName = artisiticName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastnames() {
        return lastnames;
    }

    public void setLastnames(String lastnames) {
        this.lastnames = lastnames;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    @Override
    public String toString() {
        return artisticName; // Displayed in JComboBox
    }

}
