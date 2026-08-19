package controller;

import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.AlbumDAO;
import database.GenreDAO;
import database.SongDAO;
import model.Album;
import model.Genre;
import model.Song;
import view.SongTabView;

public class SongController {

    private final SongTabView view;
    private final SongDAO songDAO = new SongDAO();
    private final GenreDAO genreDAO = new GenreDAO();
    private final AlbumDAO albumDAO = new AlbumDAO();

    public SongController(SongTabView view) {

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
        List<Genre> genres = genreDAO.getAll();
        DefaultComboBoxModel<Genre> genreModel = new DefaultComboBoxModel<>();
        // Class::staticMethod (equals to x -> Class.staticMethod(x)). (IDE Refactor)
        genres.forEach(genreModel::addElement);
        view.getCbGenre().setModel(genreModel);

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
        List<Song> songsList = songDAO.getAll();

        for (Song song : songsList) {
            model.addRow(new Object[] {
                    song.getId(),
                    song.getGenre().getName(),
                    song.getAlbum().getTitle(),
                    song.getTitle(),
                    song.getLyrics(),
                    song.getDuration(),
                    song.getReleaseDate()
            });
        }
    }

    private void onCreate() {

        // Retrieve all current data values
        Genre selectedGenre = view.getCrudPanel().getSelectedComboObject("Género");
        Album selectedAlbum = view.getCrudPanel().getSelectedComboObject("Álbum");
        String title = view.getCrudPanel().getTextFieldValue("Título");
        String lyrics = view.getCrudPanel().getTextFieldValue("Letras URL");
        String duration = view.getCrudPanel().getTextFieldValue("Duración");
        String releaseDate = view.getCrudPanel().getTextFieldValue("Fecha de lanzamiento YYYY-MM-DD");

        // If empty field
        if (selectedGenre == null || selectedAlbum == null || title.isBlank() || lyrics.isBlank() ||
                duration.isEmpty() || releaseDate.isBlank()) {
            JOptionPane.showMessageDialog(view,
                    "Por favor, complete los campos obligatorios.",
                    "Campos Incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int resp = JOptionPane.showConfirmDialog(view, "¿Seguro de agregar el registro?", "Confirmar creación",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        // If not selected Yes in JOptionPane, finish
        if (resp != JOptionPane.YES_OPTION) {
            return;
        }

        // Try to insert in DB
        try {

            Song song = new Song(0, selectedGenre, selectedAlbum, title, lyrics, duration, releaseDate);

            // Execute database query
            songDAO.insert(song);

            // If succes on insert, reload data to get new register
            loadTableData();
            view.getCrudPanel().clearFields();

            // Select last item (the just created)
            int lastRowIndex = view.getCrudPanel().getTableModel().getRowCount() - 1;
            if (lastRowIndex >= 0) {
                int lastRow = view.getCrudPanel().getTable().convertRowIndexToView(lastRowIndex);
                view.getCrudPanel().getTable().setRowSelectionInterval(lastRow, lastRow);
            }

            // Success message
            JOptionPane.showMessageDialog(view, "Registro guardado exitosamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            // Inform user on database failure
            JOptionPane.showMessageDialog(view,
                    "Error al guardar el registro en la base de datos:\n" + ex.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void onUpdate() {

        // Retrieve all current data values
        Genre selectedGenre = view.getCrudPanel().getSelectedComboObject("Género");
        Album selectedAlbum = view.getCrudPanel().getSelectedComboObject("Álbum");
        String title = view.getCrudPanel().getTextFieldValue("Título");
        String lyrics = view.getCrudPanel().getTextFieldValue("Letras URL");
        String duration = view.getCrudPanel().getTextFieldValue("Duración");
        String releaseDate = view.getCrudPanel().getTextFieldValue("Fecha de lanzamiento YYYY-MM-DD");

        // If empty field
        if (selectedGenre == null || selectedAlbum == null || title.isBlank() || lyrics.isBlank() ||
                duration.isEmpty() || releaseDate.isBlank()) {
            JOptionPane.showMessageDialog(view,
                    "No es posible actualizar con campos vacío",
                    "Campos Incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int resp = JOptionPane.showConfirmDialog(view, "¿Seguro de actualizar el registro?", "Confirmar actualización",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        // If not selected Yes in JOptionPane, finish
        if (resp != JOptionPane.YES_OPTION) {
            return;
        }

        int selectedRow = view.getCrudPanel().getTable().getSelectedRow();
        int id = Integer.parseInt(view.getCrudPanel().getTable().getValueAt(selectedRow, 0).toString());
        Song song = new Song(id, selectedGenre, selectedAlbum, title, lyrics, duration, releaseDate);
        songDAO.update(song);

        // Fetch and populate table again
        loadTableData();
        view.getCrudPanel().clearFields();

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
            // Delete the model from DB
            songDAO.deleteById(id);

            // Fetch data again
            loadTableData();

        }
    }

    private void onClear() {
        view.getCrudPanel().clearFields();
        view.getCrudPanel().getTable().clearSelection();
    }
}
