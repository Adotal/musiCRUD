package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Album;

public class AlbumDAO extends DatabaseConnection {

    public AlbumDAO() {
        super();
    }

    public void insert(Album a) {

        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement(
                    "INSERT INTO album(discography_id, title, release_date, image_url) values(?,?,?,?)");
            ps.setInt(1, a.getDiscography());
            ps.setString(2, a.getTitle());
            ps.setString(3, a.getReleaseDate());
            ps.setString(4, a.getImageUrl());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(AlbumDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public void deleteById(int id) {

        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement("DELETE FROM album WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(AlbumDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public void update(Album a) {
        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement(
                    "UPDATE album SET discography_id=?, title=?, release_date=?, image_url=?  WHERE id=?");
            ps.setInt(1, a.getDiscography());
            ps.setString(2, a.getTitle());
            ps.setString(3, a.getReleaseDate());
            ps.setString(4, a.getImageUrl());
            ps.setInt(5, a.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(AlbumDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public ArrayList<Album> getAll() {
        try {

            // Contenedor de resultados
            ArrayList<Album> albums = new ArrayList<Album>();

            PreparedStatement ps;
            ps = getConnection()
                    .prepareStatement("SELECT id, discography_id, title, release_date, image_url FROM album");

            ResultSet rs;
            rs = ps.executeQuery();

            // Ejecutar rs.next() justo después de la consulta coloca el cursor en la
            // primera fila
            while (rs.next()) {
                albums.add(
                        new Album(
                                rs.getInt("id"),
                                rs.getInt("discography_id"),
                                rs.getString("title"),
                                rs.getString("release_date"),
                                rs.getString("image_url")));
            }
            return albums;
        } catch (SQLException ex) {
            System.getLogger(AlbumDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }

    }

    public Album getAlbumById(int id) {
        String sql = "SELECT albumic_name, name, lastnames, country_of_origin FROM album WHERE id = ?";
        Album album = null;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                album = new Album(
                        id,
                        rs.getInt("discography"),
                        rs.getString("title"),
                        rs.getString("release_date"),
                        rs.getString("image_url"));
            }
        } catch (SQLException ex) {
            System.getLogger(AlbumDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }
        return album;
    }

}
