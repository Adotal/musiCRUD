package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ArtistDAO;
import model.Artist;
import view.ArtistTabView;

public class ArtistController {

    private final ArtistTabView view;
    private final ArtistDAO albumDAO = new ArtistDAO();

    public ArtistController(ArtistTabView view) {

        this.view = view;

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
        List<Artist> albumsList = albumDAO.getAll();

        for (Artist album : albumsList) {
            model.addRow(new Object[] {
                    album.getId(),
                    album.getArtisticName(),
                    album.getName(),
                    album.getLastnames(),
                    album.getCountryOfOrigin()
            });
        }
    }

    private void onCreate() {

        int resp = JOptionPane.showConfirmDialog(view, "¿Seguro de agregar el registro?", "Confirmar creación",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        // If selected Yes in JOptionPane
        if (0 == resp) {

            String artisiticName = view.getCrudPanel().getTextFieldValue("Nombre artístico");
            String name = view.getCrudPanel().getTextFieldValue("Nombre");
            String lastnames = view.getCrudPanel().getTextFieldValue("Apellidos");
            String countryOfOrigin = view.getCrudPanel().getTextFieldValue("País de origen");

            Artist album = new Artist(0, artisiticName, name, lastnames, countryOfOrigin);
            albumDAO.insert(album);

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
            String artisiticName = view.getCrudPanel().getTextFieldValue("Nombre artístico");
            String name = view.getCrudPanel().getTextFieldValue("Nombre");
            String lastnames = view.getCrudPanel().getTextFieldValue("Apellidos");
            String countryOfOrigin = view.getCrudPanel().getTextFieldValue("País de origen");

            int selectedRow = view.getCrudPanel().getTable().getSelectedRow();
            int id = Integer.parseInt(view.getCrudPanel().getTable().getValueAt(selectedRow, 0).toString());

            Artist album = new Artist(id, artisiticName, name, lastnames, countryOfOrigin);
            albumDAO.update(album);

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
            // Delete the model from DB
            albumDAO.deleteById(id);

            // Fetch data again
            loadTableData();

        }
    }

    private void onClear() {
        view.getCrudPanel().clearFields();
        view.getCrudPanel().getTable().clearSelection();
    }
}
