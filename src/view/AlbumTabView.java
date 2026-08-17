package view;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Album;
import model.Genre;
import util.ui.FormField;

public class AlbumTabView extends JPanel {

        private final String[] albumColumns = {
                        "ID", "ID discografía", "Título", "Fecha de lanzamiento", "Imagen URL"
        };

        private CrudPanel crudPanel;

        public AlbumTabView() {
                super();
                init();
        }

        public void init() {

                setLayout(new BorderLayout());

                FormField[] fields = new FormField[] {
                                new FormField(albumColumns[1], new JTextField()),                                
                                new FormField(albumColumns[2], new JTextField()),                                                        
                                new FormField(albumColumns[3], new JTextField()),
                                new FormField(albumColumns[4], new JTextField()),
                };

                crudPanel = new CrudPanel("Álbumes", albumColumns, fields);
                add(crudPanel, BorderLayout.CENTER);

        }

        // Getter for controller
        public CrudPanel getCrudPanel() {
                return crudPanel;
        }

}
