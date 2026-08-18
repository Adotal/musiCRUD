package view;

import java.awt.BorderLayout;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import model.Album;
import model.Genre;
import util.ui.FormField;

public class SongTabView extends JPanel {

        private final String[] songColumns = {
                        "ID", "Género", "Álbum", "Título", "Letras URL", "Duración", "Fecha de lanzamiento"
        };

        private JComboBox<Genre> cbGenre = new JComboBox<>();
        private JComboBox<Album> cbAlbum = new JComboBox<>();

        private CrudPanel crudPanel;

        public SongTabView() {
                super();
                init();
        }

        public void init() {

                // Native JDK 11 Input Formatting
                JFormattedTextField txtDuration;
                JFormattedTextField txtReleaseDate;

                try {
                        // Forces HH:MM:SS format (e.g., 00:03:45)
                        MaskFormatter durationMask = new MaskFormatter("##:##:##");
                        durationMask.setPlaceholderCharacter('0');
                        txtDuration = new JFormattedTextField(durationMask);

                        // Forces YYYY-MM-DD format (e.g., 2026-08-17)
                        MaskFormatter dateMask = new MaskFormatter("####-##-##");
                        dateMask.setPlaceholderCharacter('_');
                        txtReleaseDate = new JFormattedTextField(dateMask);
                } catch (ParseException e) {
                        txtDuration = new JFormattedTextField();
                        txtReleaseDate = new JFormattedTextField();
                }

                setLayout(new BorderLayout());

                FormField[] fields = new FormField[] {
                                new FormField("Género", cbGenre),
                                new FormField("Álbum", cbAlbum),
                                new FormField("Título", new JTextField()),
                                new FormField("Letras URL", new JTextField()),
                                new FormField("Duración", txtDuration),
                                new FormField("Fecha de lanzamiento", txtReleaseDate)
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
