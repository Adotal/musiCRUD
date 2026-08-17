package controller;

import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import database.AlbumDAO;
import database.ArtistAlbumDAO;
import database.ArtistDAO;
import database.GenreDAO;
import model.Album;
import model.Artist;
import model.ArtistAlbum;
import model.Genre;
import view.ArtistAlbumTabView;

public class ArtistAlbumController {

    private final ArtistAlbumTabView view;
    private final ArtistAlbumDAO artistAlbumDAO = new ArtistAlbumDAO();
    private final ArtistDAO artistDAO = new ArtistDAO();
    private final AlbumDAO albumDAO = new AlbumDAO();

    public ArtistAlbumController(ArtistAlbumTabView view) {

        this.view = view;

        // Retrieve data for JComboBox
        populateDropdowns();

        // Retrieve and print data from DB
        loadTableData();

        // Attach listeners to the view components
        initListeners();
    }

    public void populateDropdowns() {

        // Load Genres into JComboBox
        List<Artist> artist = artistDAO.getAll();
        DefaultComboBoxModel<Artist> artistModel = new DefaultComboBoxModel<>();
        // Class::staticMethod (equals to x -> Class.staticMethod(x)). (IDE Refactor)
        artist.forEach(artistModel::addElement);
        view.getCbArtist().setModel(artistModel);

        // Load Albums into JComboBox
        List<Album> albums = albumDAO.getAll();
        DefaultComboBoxModel<Album> albumModel = new DefaultComboBoxModel<>();
        albums.forEach(albumModel::addElement);
        view.getCbAlbum().setModel(albumModel);
    }

    private void initListeners() {

        // Click on every row (register) of table
        onRowSelection();

        // Create button click
        view.getCrudPanel().addBtnCreateListener(e -> onCreate());
        view.getCrudPanel().addBtnUpdateListener(e -> onUpdate());
        view.getCrudPanel().addBtnDeleteListener(e -> onDelete());

        // Clear button click
        view.getCrudPanel().addBtnClearListener(e -> onClear());

    }

    private void onRowSelection() {

        JTable table = view.getCrudPanel().getTable();
        DefaultTableModel model = view.getCrudPanel().getTableModel();

        table.getSelectionModel().addListSelectionListener(event -> {
            // Avoid doble execution and verify there is a selected row
            if (event.getValueIsAdjusting() || table.getSelectedRow() == -1) {
                return;
            }

            // Convert visual index to real model index (useful for filtering, ordering)
            int modelRow = table.convertRowIndexToModel(table.getSelectedRow());

            // Iterate over fields
            view.getCrudPanel().getFieldsMap().forEach((fieldName, textField) -> {

                // Search dinamicly the colum index by name
                int columnIndex = table.getColumnModel().getColumnIndex(fieldName);

                // Obtain value from tableModel
                Object value = model.getValueAt(modelRow, columnIndex);

                // Assign value to respective JComponent of FormField
                view.getCrudPanel().setFieldValue(fieldName, value);
            });
        });
    }

    private void loadTableData() {

        // Retrieve object used in view
        DefaultTableModel model = view.getCrudPanel().getTableModel();
        // Clear table
        model.setRowCount(0);

        // Fetch data from database
        List<ArtistAlbum> artistAlbumList = artistAlbumDAO.getAll();

        for (ArtistAlbum artistAlbum : artistAlbumList) {
            model.addRow(new Object[] {
                    artistAlbum.getArtist().getId(),
                    artistAlbum.getArtist().getArtisticName(),
                    artistAlbum.getAlbum().getId(),
                    artistAlbum.getAlbum().getTitle(),
            });
        }
    }

    private void onCreate() {

        int resp = JOptionPane.showConfirmDialog(view, "¿Seguro de agregar el registro?", "Confirmar creación",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        // If selected Yes in JOptionPane
        if (0 == resp) {
            // Extract selected FK objects directly from CrudPanel
            Artist selectedArtist = view.getCrudPanel().getSelectedComboObject("Artista");
            Album selectedAlbum = view.getCrudPanel().getSelectedComboObject("Álbum");

            ArtistAlbum artistAlbum = new ArtistAlbum(selectedArtist, selectedAlbum);
            artistAlbumDAO.insert(artistAlbum);

            // Fetch and populate table again
            loadTableData();
            view.getCrudPanel().clearFields();

            // Select last item (the just created)
            int lastRow = view.getCrudPanel().getTable()
                    .convertRowIndexToView(view.getCrudPanel().getTableModel().getRowCount()
                            - 1);
            view.getCrudPanel().getTable().setRowSelectionInterval(lastRow, lastRow);
        }
    }

    private void onUpdate() {

        int resp = JOptionPane.showConfirmDialog(view, "¿Seguro de actualizar el registro?", "Confirmar actualización",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        // If selected Yes in JOptionPane
        if (0 == resp) {

            // Extract selected FK objects directly from CrudPanel
            Artist selectedArtist = view.getCrudPanel().getSelectedComboObject("Artista");
            Album selectedAlbum = view.getCrudPanel().getSelectedComboObject("Álbum");

            int selectedRow = view.getCrudPanel().getTable().getSelectedRow();
            int id = Integer.parseInt(view.getCrudPanel().getTable().getValueAt(selectedRow, 0).toString());

            // Retrieve old relation
            String artistIdString = view.getCrudPanel().getTableModel().getValueAt(selectedRow, 0).toString();
            String albumIdString = view.getCrudPanel().getTableModel().getValueAt(selectedRow, 2).toString();
            int artistId = Integer.parseInt(artistIdString);
            int albumId = Integer.parseInt(albumIdString);

            ArtistAlbum oldArtistAlbum = new ArtistAlbum(artistId, albumId);

            ArtistAlbum artistAlbum = new ArtistAlbum(selectedArtist, selectedAlbum);
            artistAlbumDAO.update(oldArtistAlbum, artistAlbum);

            // Fetch and populate table again
            loadTableData();
            view.getCrudPanel().clearFields();
        }
    }

    private void onDelete() {

        // If no register row is selected
        if (view.getCrudPanel().getTable().getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(view, "No hay registro seleccionado para eliminar", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int resp = JOptionPane.showConfirmDialog(view, "¿Seguro de eliminar el registro?", "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        // If selected Yes in JOptionPane
        if (0 == resp) {

            int selectedRow = view.getCrudPanel().getTable().getSelectedRow();
            int id = Integer.parseInt(view.getCrudPanel().getTable().getValueAt(selectedRow, 0).toString());

            // Retrieve relation
            String artistIdString = view.getCrudPanel().getTableModel().getValueAt(selectedRow, 0).toString();
            String albumIdString = view.getCrudPanel().getTableModel().getValueAt(selectedRow, 2).toString();

            int artistId = Integer.parseInt(artistIdString);
            int albumId = Integer.parseInt(albumIdString);

            // Delete the model from DB
            artistAlbumDAO.delete(new ArtistAlbum(artistId, albumId));

            // Fetch data again
            loadTableData();

        }
    }

    private void onClear() {
        view.getCrudPanel().clearFields();
        view.getCrudPanel().getTable().clearSelection();
    }
}
