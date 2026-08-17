package view;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Genre;
import util.ui.FormField;

public class GenreTabView extends JPanel {

        private final String[] genreColumns = {
                        "ID", "Nombre",
        };

        private CrudPanel crudPanel;

        public GenreTabView() {
                super();
                init();
        }

        public void init() {

                setLayout(new BorderLayout());

                FormField[] fields = new FormField[] {
                                new FormField(genreColumns[1], new JTextField()),
                };

                crudPanel = new CrudPanel("Géneros", genreColumns, fields);
                add(crudPanel, BorderLayout.CENTER);

        }

        // Getter for controller
        public CrudPanel getCrudPanel() {
                return crudPanel;
        }

}
