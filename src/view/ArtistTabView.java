package view;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Artist;
import model.Genre;
import util.ui.FormField;

public class ArtistTabView extends JPanel {

        private final String[] artistColumns = {
                        "ID", "Nombre artístico", "Nombre", "Apellidos", "País de origen"
        };

        private CrudPanel crudPanel;

        public ArtistTabView() {
                super();
                init();
        }

        public void init() {

                setLayout(new BorderLayout());

                FormField[] fields = new FormField[] {
                                new FormField(artistColumns[1], new JTextField()),                                
                                new FormField(artistColumns[2], new JTextField()),                                                        
                                new FormField(artistColumns[3], new JTextField()),
                                new FormField(artistColumns[4], new JTextField()),
                };

                crudPanel = new CrudPanel("Álbumes", artistColumns, fields);
                add(crudPanel, BorderLayout.CENTER);

        }

        // Getter for controller
        public CrudPanel getCrudPanel() {
                return crudPanel;
        }

}
