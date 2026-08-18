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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
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
import util.ui.FormField;
import util.ui.StyledJButton;

// Reusable Panel for each CRUD tab
public class CrudPanel extends JPanel {

    // Class to manage table data
    private DefaultTableModel tableModel;
    // UI table
    private JTable table;
    private JButton btnCreate, btnUpdate, btnDelete, btnClear;
    private Map<String, JComponent> fieldsMap = new HashMap<>();

    public CrudPanel(String title, String[] columns, FormField[] fields) {
        this(title, columns, fields, null);
    }

    public CrudPanel(String title, String[] columns, FormField[] fields, JComponent extraComponent) {

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
        JPanel formPanel = createDynamicForm(fields);
        JPanel buttonPanel = createButtonPanel();

        formPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);

        northPanel.add(lblTitle);
        northPanel.add(Box.createVerticalStrut(15));
        northPanel.add(formPanel);
        northPanel.add(Box.createVerticalStrut(15));
        northPanel.add(buttonPanel);

        // Insert extra component above buttons if provided
        if (extraComponent != null) {
            extraComponent.setAlignmentX(LEFT_ALIGNMENT);
            northPanel.add(Box.createVerticalStrut(15));
            northPanel.add(extraComponent);
        }

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
    private JPanel createDynamicForm(FormField[] fields) {

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

        for (int i = 0; i < fields.length; i++) {

            FormField field = fields[i];

            // Label (Left Column)
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.0;
            JLabel label = new JLabel(field.getLabel() + ":");
            label.setFont(Fonts.TEXT_FONT);
            label.setForeground(AppColors.TEXT_DARK);
            panel.add(label, gbc);

            // Text Field (Right Column)
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.weightx = 1.0;

            JComponent component = field.getComponent();
            component.setFont(Fonts.TEXT_FONT);
            // Gives text fields a taller, modern height
            component.setPreferredSize(new Dimension(component.getPreferredSize().width, 32));

            // Add component to hashMap
            fieldsMap.put(field.getLabel(), component);

            panel.add(component, gbc);
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

    // -----------Component Value Helpers---------------

    public String getTextFieldValue(String label) {
        JComponent comp = fieldsMap.get(label);
        if (comp instanceof JTextField) {
            JTextField textField = (JTextField) comp;
            return textField.getText().trim();
        }

        return "";
    }

    // Returns selected item of JComboBox, is Template Type> because it can return
    // any class
    public <T> T getSelectedComboObject(String label) {
        JComponent comp = fieldsMap.get(label);
        if (comp instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) comp;
            return (T) comboBox.getSelectedItem();
        }
        return null;
    }

    public void setFieldValue(String label, Object value) {
        JComponent comp = fieldsMap.get(label);
        if (comp instanceof JFormattedTextField) {
            JFormattedTextField ftf = (JFormattedTextField) comp;
            ftf.setValue(value != null ? value.toString() : null);
        } else if (comp instanceof JTextField) {
            JTextField textField = (JTextField) comp;
            textField.setText(value != null ? value.toString() : "");
        } else if (comp instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) comp;
            if (value == null)
                return;
            for (int i = 0; i < comboBox.getItemCount(); i++) {
                Object item = comboBox.getItemAt(i);
                if (item.toString().equalsIgnoreCase(value.toString())) {
                    comboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public void clearFields() {
        fieldsMap.values().forEach(comp -> {
            if (comp instanceof JFormattedTextField) {
                JFormattedTextField ftf = (JFormattedTextField) comp;
                ftf.setValue(null); // Resets mask to placeholders
            } else if (comp instanceof JTextField) {
                JTextField textField = (JTextField) comp;
                textField.setText("");
            } else if (comp instanceof JComboBox) {
                JComboBox comboBox = (JComboBox) comp;
                if (comboBox.getItemCount() > 0) {
                    comboBox.setSelectedIndex(0);
                }
            }
        });
    }
    // Getters for Controller Usage

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }

    public void addBtnCreateListener(ActionListener l) {
        btnCreate.addActionListener(l);
    }

    public void addBtnUpdateListener(ActionListener l) {
        btnUpdate.addActionListener(l);
    }

    public void addBtnDeleteListener(ActionListener l) {
        btnDelete.addActionListener(l);
    }

    public void addBtnClearListener(ActionListener l) {
        btnClear.addActionListener(l);
    }

    public Map<String, JComponent> getFieldsMap() {
        return fieldsMap;
    }
}
