package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class SongTabView extends JPanel {

        private final String[] songColumns = {
                        "ID", "Género", "Álbum", "Título", "Letras URL", "Duración", "Fecha de lanzamiento"
        };
        private final String[] songFields = {
                        "Género", "Álbum", "Título", "Letras URL", "Duración", "Fecha de lanzamiento"
        };

        private CrudPanel crudPanel;

        public SongTabView() {
                super();
                init();
        }

        public void init() {

                setLayout(new BorderLayout());
                crudPanel = new CrudPanel("Canciones", songColumns, songFields);
                add(crudPanel, BorderLayout.CENTER);

        }

        // Getter for controller
        public CrudPanel getCrudPanel() {
                return crudPanel;
        }

}
