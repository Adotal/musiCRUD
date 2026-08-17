package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Album;
import model.Genre;
import model.Song;

public class SongDAO extends DatabaseConnection {

    public SongDAO() {
        super();
    }

    public void insert(Song a) {

        try {
            
            PreparedStatement ps;
            
            // TODA VALIDACIÓN VA AQUÍ

            ps = getConnection().prepareStatement(
                    "INSERT INTO song(genre_id, album_id, title, lyrics, duration, release_date) values(?,?,?,?,?,?)");
            ps.setInt(1, a.getGenre().getId());
            ps.setInt(2, a.getAlbum().getId());
            ps.setString(3, a.getTitle());
            ps.setString(4, a.getLyrics());
            ps.setString(5, a.getDuration());
            ps.setString(6, a.getReleaseDate());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(SongDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public void deleteById(int id) {

        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement("DELETE FROM song WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(SongDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public void update(Song a) {
        try {

            PreparedStatement ps;
            // TODA VALIDACIÓN VA AQUÍ
            ps = getConnection().prepareStatement(
                    "UPDATE song SET genre_id=?, album_id=?, title=?, lyrics=?, duration=?, release_date=? WHERE id=?");
            ps.setInt(1, a.getGenre().getId());
            ps.setInt(2, a.getAlbum().getId());
            ps.setString(3, a.getTitle());
            ps.setString(4, a.getLyrics());
            ps.setString(5, a.getDuration());
            ps.setString(6, a.getReleaseDate());
            ps.setInt(5, a.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(SongDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public ArrayList<Song> getAll() {
        try {

            // Contenedor de resultados
            ArrayList<Song> songs = new ArrayList<Song>();

            PreparedStatement ps;
            ps = getConnection()
                    .prepareStatement(
                            "SELECT song_id, song_title, lyrics, duration, song_release_date, genre_id, genre_name, album_id, album_title FROM full_song_view");
            ;

            ResultSet rs;
            rs = ps.executeQuery();

            // Ejecutar rs.next() justo después de la consulta coloca el cursor en la
            // primera fila
            while (rs.next()) {
                // Map Genre
                Genre genre = new Genre();
                genre.setId(rs.getInt("genre_id"));
                genre.setName(rs.getString("genre_name"));

                // Map Album
                Album album = new Album();
                album.setId(rs.getInt("album_id"));
                // album.setDiscography(rs.getInt("discography"));
                album.setTitle(rs.getString("album_title"));
                // album.setReleaseDate(rs.getString("album_release_date"));
                // album.setImageUrl(rs.getString("image_url"));

                // Map Song with nested Genre and Album
                Song song = new Song();
                song.setId(rs.getInt("song_id"));
                song.setTitle(rs.getString("song_title"));
                song.setLyrics(rs.getString("lyrics"));
                song.setDuration(rs.getString("duration"));
                song.setReleaseDate(rs.getString("song_release_date"));
                song.setGenre(genre);
                song.setAlbum(album);

                songs.add(song);
            }
            return songs;

        } catch (SQLException ex) {
            System.getLogger(SongDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }

    }

    public Song getSongById(int id) {
        String sql = "SELECT song_id, song_title, lyrics, duration, song_release_date, genre_id, genre_name, album_id, album_title FROM full_song_view WHERE id = ?";
        Song song = null;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Map Genre
                Genre genre = new Genre();
                genre.setId(rs.getInt("genre_id"));
                genre.setName(rs.getString("genre_name"));

                // Map Album
                Album album = new Album();
                album.setId(rs.getInt("album_id"));
                // album.setDiscography(rs.getInt("discography"));
                album.setTitle(rs.getString("album_title"));
                // album.setReleaseDate(rs.getString("album_release_date"));
                // album.setImageUrl(rs.getString("image_url"));

                // Map Song with nested Genre and Album
                song.setId(rs.getInt("song_id"));
                song.setTitle(rs.getString("song_title"));
                song.setLyrics(rs.getString("lyrics"));
                song.setDuration(rs.getString("duration"));
                song.setReleaseDate(rs.getString("song_release_date"));
                song.setGenre(genre);
                song.setAlbum(album);
            }

        } catch (SQLException ex) {
            System.getLogger(SongDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }
        return song;
    }

}
