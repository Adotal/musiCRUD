package view;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Album;
import model.Artist;
import model.Genre;
import util.ui.FormField;

public class ArtistAlbumTabView extends JPanel {

        private final String[] artistAlbumColumns = {
                        "ID Artista", "Artista", "ID Álbum", "Álbum",
        };

        private JComboBox<Artist> cbArtist = new JComboBox<>();
        private JComboBox<Album> cbAlbum = new JComboBox<>();

        private CrudPanel crudPanel;

        public ArtistAlbumTabView() {
                super();
                init();
        }

        public void init() {

                setLayout(new BorderLayout());

                FormField[] fields = new FormField[] {
                                new FormField("Artista", cbArtist),
                                new FormField("Álbum", cbAlbum),
                };

                crudPanel = new CrudPanel("Relación artistas y sus álbumes", artistAlbumColumns, fields);
                add(crudPanel, BorderLayout.CENTER);

        }

        // Getter for controller
        public CrudPanel getCrudPanel() {
                return crudPanel;
        }

        public JComboBox<Artist> getCbArtist() {
                return cbArtist;
        }

        public JComboBox<Album> getCbAlbum() {
                return cbAlbum;
        }

}
