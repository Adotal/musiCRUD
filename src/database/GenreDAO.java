package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Genre;

public class GenreDAO extends DatabaseConnection {

    public GenreDAO() {
        super();
    }

    public void insert(Genre g) throws SQLException {

        PreparedStatement ps;
        // TODA VALIDACIÓN VA AQUÍ
        ps = getConnection().prepareStatement("INSERT INTO genre(name) values(?)");
        ps.setString(1, g.getName());
        ps.executeUpdate();

    }

    public void deleteById(int id) {

        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement("DELETE FROM genre WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(GenreDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public void update(Genre g) {
        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement("UPDATE genre SET name=? WHERE id=?");
            ps.setString(1, g.getName());
            ps.setInt(2, g.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(GenreDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public ArrayList<Genre> getAll() {
        try {

            // Contenedor de resultados
            ArrayList<Genre> genres = new ArrayList<Genre>();

            PreparedStatement ps;
            ps = getConnection().prepareStatement("SELECT id, name FROM genre");

            ResultSet rs;
            rs = ps.executeQuery();

            // Ejecutar rs.next() justo después de la consulta coloca el cursor en la
            // primera fila
            while (rs.next()) {
                genres.add(
                        new Genre(
                                rs.getInt("id"),
                                rs.getString("name")));
            }
            return genres;
        } catch (SQLException ex) {
            System.getLogger(GenreDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }

    }

    public Genre getGenreById(int id) {
        String sql = "SELECT name FROM genre WHERE id = ?";
        Genre genre = null;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                genre = new Genre(id, rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.getLogger(GenreDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }
        return genre;
    }

}
