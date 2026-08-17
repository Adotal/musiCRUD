package controller;

import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import database.SongDAO;
import model.Genre;
import model.Song;
import view.SongTabView;

public class SongController {

    private final SongTabView view;
    private final SongDAO dao;

    public SongController(SongTabView view) {

        this.view = view;
        dao = new SongDAO();

        // Retrieve and print data from DB
        loadTableData();

        // Attach listeners to the view components
        initListeners();
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
                String textValue = (value != null) ? value.toString() : "";

                // Assign value to textField
                view.getCrudPanel().setFieldValue(fieldName, textValue);
            });
        });
    }

    private void loadTableData() {

        // Retrieve object used in view
        DefaultTableModel model = view.getCrudPanel().getTableModel();
        // Clear table
        model.setRowCount(0);

        // Fetch data from database
        List<Song> songsList = dao.getAll();

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
        int resp = JOptionPane.showConfirmDialog(view, "¿Seguro de agregar el registro?", "Confirmar creación",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        // If selected Yes in JOptionPane
        if (0 == resp) {

            // Make a object from
            Song newSong = new Song(

                1, 1,
                // "Género", "Álbum", "", "", "", ""
                view.getCrudPanel().getFieldValue("Título"),
                view.getCrudPanel().getFieldValue("Letras URL"),
                view.getCrudPanel().getFieldValue("Duración"),
                view.getCrudPanel().getFieldValue("Fecha de lanzamiento")

            );

            dao.insert(newSong);
        }

        // Fetch data again
        loadTableData();

        // Select last item (just created)
        int lastRow = view.getCrudPanel().getTable().convertRowIndexToView(view.getCrudPanel().getTableModel().getRowCount() - 1);
        view.getCrudPanel().getTable().setRowSelectionInterval(lastRow, lastRow);

    }

    private void onUpdate() {

        int resp = JOptionPane.showConfirmDialog(view, "¿Seguro de actualizar el registro?", "Confirmar actualización",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        // If selected Yes in JOptionPane
        if (0 == resp) {
            dao.update(new Song(
                    1, 1, "1", "1", "00:00:01", "2007-01-01"));
        }

        // Fetch data again
        loadTableData();

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
            dao.deleteById(id);

            // Fetch data again
            loadTableData();

        }
    }

    private void onClear() {
        view.getCrudPanel().clearFields();
        view.getCrudPanel().getTable().clearSelection();
    }
}
