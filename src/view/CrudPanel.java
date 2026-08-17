package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import util.AppColors;
import util.Fonts;
import util.ui.StyledJButton;

// Reusable Panel for each CRUD tab
public class CrudPanel extends JPanel {

    // Class to manage table data
    private DefaultTableModel tableModel;
    // UI table
    private JTable table;
    private JButton btnCreate, btnUpdate, btnDelete, btnClear;
    private Map<String, JComponent> fieldsMap = new HashMap<>();

    public CrudPanel(String title, String[] columns, String[] fieldLabels) {
        setLayout(new BorderLayout());
        setBackground(AppColors.BACKGROUND_WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Initialize empty table model
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Read-only cells
            }
        };

        // NORTH: Form + Action Buttons Panel
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(new EmptyBorder(0, 0, 25, 0));

        // Main View Title
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(Fonts.TITLE_FONT);
        lblTitle.setForeground(AppColors.TEXT_DARK);
        lblTitle.setAlignmentX(LEFT_ALIGNMENT);

        // Generate dynamic components
        JPanel formPanel = createDynamicForm(fieldLabels);
        JPanel buttonPanel = createButtonPanel();

        formPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);

        northPanel.add(lblTitle);
        northPanel.add(Box.createVerticalStrut(15));
        northPanel.add(formPanel);
        northPanel.add(Box.createVerticalStrut(15));
        northPanel.add(buttonPanel);

        add(northPanel, BorderLayout.NORTH);

        // CENTER: Table View Setup
        table = new JTable(tableModel);
        table.setFont(Fonts.TEXT_FONT);
        table.setRowHeight(30); // Space between rows
        table.setSelectionBackground(new Color(230, 242, 250));
        table.setSelectionForeground(AppColors.TEXT_DARK);
        table.setShowGrid(true); // Modern borderless look
        table.setIntercellSpacing(new Dimension(0, 0));

        // Table Header Styling
        JTableHeader header = table.getTableHeader();
        header.setFont(Fonts.HEADER_FONT);
        header.setBackground(Color.WHITE);
        header.setForeground(AppColors.TEXT_DARK);
        header.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230)));
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(scrollPane, BorderLayout.CENTER);
    }

    // Dynamically builds the form using GridBagLayout
    private JPanel createDynamicForm(String[] labels) {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        // Custom Titled Border with larger font
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
                "Administrar Registro");
        titledBorder.setTitleFont(Fonts.HEADER_FONT);
        titledBorder.setTitleColor(AppColors.TEXT_DARK);
        panel.setBorder(BorderFactory.createCompoundBorder(titledBorder, new EmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Extra spacing between inputs
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < labels.length; i++) {
            // Label (Left Column)
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.0;
            JLabel label = new JLabel(labels[i] + ":");
            label.setFont(Fonts.TEXT_FONT);
            label.setForeground(AppColors.TEXT_DARK);
            panel.add(label, gbc);

            // Text Field (Right Column)
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.weightx = 1.0;
            JTextField textField = new JTextField(20);

            // Fill hasmap of form
            fieldsMap.put(labels[i], textField);

            textField.setFont(Fonts.TEXT_FONT);
            // Gives text fields a taller, modern height
            textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 32));
            panel.add(textField, gbc);
        }

        return panel;
    }

    // Standard Button Row
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        btnCreate = new StyledJButton("Crear", AppColors.PRIMARY, Color.WHITE);
        btnUpdate = new StyledJButton("Actualizar", AppColors.GREEN, Color.WHITE);
        btnDelete = new StyledJButton("Eliminar", AppColors.DANGER, Color.WHITE);
        btnClear = new StyledJButton("Limpiar campos", AppColors.GRAY, AppColors.TEXT_DARK);

        panel.add(btnCreate);
        panel.add(Box.createHorizontalStrut(8));
        panel.add(btnUpdate);
        panel.add(Box.createHorizontalStrut(8));
        panel.add(btnDelete);
        // Max space betwen btnDelete and btnClear
        panel.add(Box.createHorizontalGlue());
        panel.add(btnClear);

        return panel;
    }

    // Getters for Controller Usage
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }

    public void addBtnCreateListener(ActionListener actionListener){
        btnCreate.addActionListener(actionListener);
    }

    public void addBtnUpdateListener(ActionListener actionListener){
        btnUpdate.addActionListener(actionListener);
    }
    public void addBtnDeleteListener(ActionListener actionListener){
        btnDelete.addActionListener(actionListener);
    }

    public void addBtnClearListener(ActionListener actionListener){
        btnClear.addActionListener(actionListener);
    }

    public String getFieldValue(String label) {
        JTextField field = fieldsMap.get(label);
        return field != null ? field.getText().trim() : "";
    }

    public void setFieldValue(String label, String value) {
        if (!fieldsMap.containsKey(label))
            return;
        JTextField field = fieldsMap.get(label);
        field.setText(value);
    }

    public Map<String, JTextField> getFieldsMap() {
        return fieldsMap;
    }

    public void clearFields() {
        fieldsMap.values().forEach(field -> field.setText(""));
    }
}
