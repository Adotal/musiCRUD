package model;

public class Album {

    private int id;
    private int discography;
    private String title;
    private String releaseDate;
    private String imageUrl;

    public Album(){
        
    }

    public Album(int id, int discography, String title, String releaseDate, String imageUrl) {
        this.id = id;
        this.discography = discography;
        this.title = title;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscography() {
        return discography;
    }

    public void setDiscography(int discography) {
        this.discography = discography;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return title; // Displayed in JComboBox
    }

}
