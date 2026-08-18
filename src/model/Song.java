package model;

public class Song {

    private int id;
    private Genre genre;
    private Album album;
    private String title;
    private String lyrics;
    private String duration;
    private String releaseDate;

    public Song() {

    }

    public Song(int id, Genre genre, Album album, String title, String lyrics, String duration, String date) {
        this.id = id;
        this.genre = genre;
        this.album = album;
        this.title = title;
        this.lyrics = lyrics;
        this.duration = duration;
        this.releaseDate = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // Overload methods
    public void setId(String id){
        this.id = Integer.parseInt(id);
    }

    public Genre getGenre() {
        return genre;
    }
    public void setGenre(int id, String name) {
        this.genre = new Genre(id, name);
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setAlbum(int id, String title) {
        this.album = new Album(id, title);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String date) {
        this.releaseDate = date;
    }

    @Override
    public String toString() {
        return title; // Displayed in JComboBox
    }

}
