package view;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Album;
import model.Genre;
import util.ui.FormField;

public class SongTabView extends JPanel {

        private final String[] songColumns = {
                        "ID", "Género", "Álbum", "Título", "Letras URL", "Duración", "Fecha de lanzamiento"
        };
        // private final String[] songFields = {
        // "Género", "Álbum", "Título", "Letras URL", "Duración", "Fecha de lanzamiento"
        // };

        private JComboBox<Genre> cbGenre = new JComboBox<>();
        private JComboBox<Album> cbAlbum = new JComboBox<>();

        private CrudPanel crudPanel;

        public SongTabView() {
                super();
                init();
        }

        public void init() {

                setLayout(new BorderLayout());

                FormField[] fields = new FormField[] {
                                new FormField("Género", cbGenre),
                                new FormField("Álbum", cbAlbum),
                                new FormField("Título", new JTextField()),
                                new FormField("Letras URL", new JTextField()),
                                new FormField("Duración", new JTextField()),
                                new FormField("Fecha de lanzamiento", new JTextField())
                };

                crudPanel = new CrudPanel("Canciones", songColumns, fields);
                add(crudPanel, BorderLayout.CENTER);

        }

        // Getter for controller
        public CrudPanel getCrudPanel() {
                return crudPanel;
        }

        public JComboBox<Genre> getCbGenre() {
                return cbGenre;
        }

        public JComboBox<Album> getCbAlbum() {
                return cbAlbum;
        }

}
