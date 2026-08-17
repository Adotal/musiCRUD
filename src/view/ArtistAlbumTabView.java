package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Album;
import model.Artist;
import model.Genre;
import util.Fonts;
import util.ui.FormField;

public class ArtistAlbumTabView extends JPanel {

        private final String[] artistAlbumColumns = {
                        "ID Artista", "Artista", "ID Álbum", "Álbum",
        };

        private JComboBox<Artist> cbArtist;
        private JComboBox<Album> cbAlbum;

        private DefaultListModel albumsListModel;
        private JList<Album> albumList;

        private CrudPanel crudPanel;

        public ArtistAlbumTabView() {
                super();
                init();
        }

        public void init() {

                // Initialize objects
                cbArtist = new JComboBox<>();
                cbAlbum = new JComboBox<>();

                albumsListModel = new DefaultListModel<>();
                albumList = new JList<>();

                setLayout(new BorderLayout());

                FormField[] fields = new FormField[] {
                                new FormField("Artista", cbArtist),
                                new FormField("Álbum", cbAlbum),
                };

                // Create model for JList
                albumsListModel = new DefaultListModel<>();
                // Associate mode with JList
                JList<Album> albumList = new JList<>(albumsListModel);
                albumList.setFont(Fonts.TEXT_FONT);

                JScrollPane albumsScrollPane = new JScrollPane(albumList);
                albumsScrollPane.setFont(Fonts.TEXT_FONT);

                // Add a titled border around the scroll pane
                albumsScrollPane.setBorder(BorderFactory.createTitledBorder("Álbumes relacionados con el mismo artista"));

                crudPanel = new CrudPanel("Relación artistas y sus álbumes", artistAlbumColumns, fields, albumsScrollPane);

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

        public DefaultListModel getAlbumsListModel() {
                return albumsListModel;
        }

}
