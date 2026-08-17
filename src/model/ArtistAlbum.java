package model;

public class ArtistAlbum {
    
    private Artist artist;
    private Album album;

    public ArtistAlbum(){
        
    }

    public ArtistAlbum(int artistId, int albumId) {
        this.artist = new Artist(artistId, null);        
        this.album = new Album(albumId, null);
    }
    public ArtistAlbum(Artist artist, Album album) {
        this.artist = artist;
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }

    
}
