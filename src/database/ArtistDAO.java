package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Artist;

public class ArtistDAO extends DatabaseConnection {

    public ArtistDAO() {
        super();
    }

    public void insert(Artist a) {

        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement(
                    "INSERT INTO artist(artistic_name, name, lastnames, country_of_origin) values(?,?,?,?)");
            ps.setString(1, a.getArtisticName());
            ps.setString(2, a.getName());
            ps.setString(3, a.getLastnames());
            ps.setString(4, a.getCountryOfOrigin());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(ArtistDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public void deleteById(int id) {

        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement("DELETE FROM artist WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(ArtistDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public void update(Artist a) {
        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement(
                    "UPDATE artist SET artistic_name=?, name=?, lastnames=?, country_of_origin=?  WHERE id=?");
            ps.setString(1, a.getArtisticName());
            ps.setString(2, a.getName());
            ps.setString(3, a.getLastnames());
            ps.setString(4, a.getCountryOfOrigin());
            ps.setInt(5, a.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(ArtistDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public ArrayList<Artist> getAll() {
        try {

            // Contenedor de resultados
            ArrayList<Artist> artists = new ArrayList<Artist>();

            PreparedStatement ps;
            ps = getConnection()
                    .prepareStatement("SELECT id, artistic_name, name, lastnames, country_of_origin FROM artist");

            ResultSet rs;
            rs = ps.executeQuery();

            // Ejecutar rs.next() justo después de la consulta coloca el cursor en la
            // primera fila
            while (rs.next()) {
                artists.add(
                        new Artist(
                                rs.getInt("id"),
                                rs.getString("artistic_name"),
                                rs.getString("name"),
                                rs.getString("lastnames"),
                                rs.getString("country_of_origin")));
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

}