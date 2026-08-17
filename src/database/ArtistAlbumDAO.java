package database;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Album;
import model.ArtistAlbum;
import model.Artist;
import model.Genre;

public class ArtistAlbumDAO extends DatabaseConnection {

    public ArtistAlbumDAO() {
        super();
    }

    public void insert(ArtistAlbum a) {

        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement(
                    "INSERT INTO artist_album(artist_id, album_id) values(?,?)");
            ps.setInt(1, a.getArtist().getId());
            ps.setInt(2, a.getAlbum().getId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(ArtistDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public void delete(ArtistAlbum a) {

        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement("DELETE FROM artist_album WHERE artist_id=? AND album_id=?");
            ps.setInt(1, a.getArtist().getId());
            ps.setInt(2, a.getAlbum().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(ArtistDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public void update(ArtistAlbum oldAlbumArtist, ArtistAlbum albumArtist) {
        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement(
                    "UPDATE artist_album SET artist_id=?, album_id=? WHERE artist_id=? AND album_id=?");

            ps.setInt(1, albumArtist.getArtist().getId());
            ps.setInt(2, albumArtist.getAlbum().getId());
            ps.setInt(3, oldAlbumArtist.getArtist().getId());
            ps.setInt(4, oldAlbumArtist.getAlbum().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(ArtistDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public ArrayList<ArtistAlbum> getAll() {
        try {

            // Contenedor de resultados
            ArrayList<ArtistAlbum> artists = new ArrayList<ArtistAlbum>();

            PreparedStatement ps;
            ps = getConnection()
                    .prepareStatement("SELECT artist_id, artist, album_id, album_title  FROM full_artist_album_view");

            ResultSet rs;
            rs = ps.executeQuery();

            // Ejecutar rs.next() justo después de la consulta coloca el cursor en la
            // primera fila
            while (rs.next()) {
                artists.add(
                        new ArtistAlbum(
                                new Artist(
                                        rs.getInt("artist_id"),
                                        rs.getString("artist")),
                                new Album(
                                        rs.getInt("album_id"),
                                        rs.getString("album_title"))));
            }
            return artists;
        } catch (SQLException ex) {
            System.getLogger(ArtistDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }

    }

    public Artist getArtistById(int id) {
        String sql = "SELECT artistic_name, name, lastnames, country_of_origin FROM artist WHERE id = ?";
        Artist artist = null;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                artist = new Artist(id,
                        rs.getString("artistic_name"),
                        rs.getString("name"),
                        rs.getString("lastnames"),
                        rs.getString("country_of_origin"));
            }
        } catch (SQLException ex) {
            System.getLogger(ArtistDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }
        return artist;
    }

    public ArrayList<Album> getAlbumsByArtist(int id) {

        String sql = "SELECT ab.id, ab.title FROM artist_album a INNER JOIN album ab ON a.album_id=ab.id WHERE a.artist_id = ?";
        ArrayList<Album> albumList = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                albumList.add(
                        new Album(
                                rs.getInt("id"),
                                rs.getString("title")));
            }
        } catch (SQLException ex) {
            System.getLogger(ArtistDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }
        return albumList;

    }

}