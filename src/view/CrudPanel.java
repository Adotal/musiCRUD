package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import util.ThemeManager;
import util.ui.FormField;
import util.ui.StyledJButton;

public class CrudPanel extends JPanel {

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton btnCreate, btnUpdate, btnDelete, btnClear;
    private Map<String, JComponent> fieldsMap = new HashMap<>();

    // Stored components for dynamic theme updates
    private JLabel lblTitle;
    private JPanel formPanel;
    private TitledBorder titledBorder;
    private JScrollPane scrollPane;

    public CrudPanel(String title, String[] columns, FormField[] fields) {
        this(title, columns, fields, null);
    }

    public CrudPanel(String title, String[] columns, FormField[] fields, JComponent extraComponent) {

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(new EmptyBorder(0, 0, 25, 0));

        lblTitle = new JLabel(title);
        lblTitle.setFont(Fonts.TITLE_FONT);
        lblTitle.setAlignmentX(LEFT_ALIGNMENT);

        formPanel = createDynamicForm(fields);
        JPanel buttonPanel = createButtonPanel();

        formPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);

        northPanel.add(lblTitle);
        northPanel.add(Box.createVerticalStrut(15));
        northPanel.add(formPanel);
        northPanel.add(Box.createVerticalStrut(15));
        northPanel.add(buttonPanel);

        if (extraComponent != null) {
            extraComponent.setAlignmentX(LEFT_ALIGNMENT);
            northPanel.add(Box.createVerticalStrut(15));
            northPanel.add(extraComponent);
        }

        add(northPanel, BorderLayout.NORTH);

        table = new JTable(tableModel);
        table.setFont(Fonts.TEXT_FONT);
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(0, 0));

        JTableHeader header = table.getTableHeader();
        header.setFont(Fonts.HEADER_FONT);
        header.setReorderingAllowed(false);

        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Apply theme initially and register listener
        applyTheme();
        ThemeManager.getInstance().addPropertyChangeListener(evt -> applyTheme());
    }

    // Dynamic color application
    public void applyTheme() {
        boolean isDark = ThemeManager.getInstance().getCurrentTheme() == ThemeManager.Theme.DARK;

        Color bg = isDark ? new Color(30, 30, 30) : AppColors.BACKGROUND_WHITE;
        Color cardBg = isDark ? new Color(42, 42, 42) : Color.WHITE;
        Color text = isDark ? new Color(240, 240, 240) : AppColors.TEXT_DARK;
        Color border = isDark ? new Color(70, 70, 70) : new Color(220, 224, 230);

        setBackground(bg);
        lblTitle.setForeground(text);

        formPanel.setBackground(cardBg);
        titledBorder.setTitleColor(text);
        titledBorder.setBorder(BorderFactory.createLineBorder(border, 1, true));

        // Update form labels
        for (Component c : formPanel.getComponents()) {
            if (c instanceof JLabel) {
                c.setForeground(text);
            }
        }

        // Update table colors
        table.setBackground(cardBg);
        table.setForeground(text);
        table.setGridColor(border);
        table.setSelectionBackground(isDark ? new Color(60, 80, 110) : new Color(230, 242, 250));
        table.setSelectionForeground(text);

        JTableHeader header = table.getTableHeader();
        header.setBackground(isDark ? new Color(50, 50, 50) : Color.WHITE);
        header.setForeground(text);

        scrollPane.setBorder(BorderFactory.createLineBorder(border));
        scrollPane.getViewport().setBackground(cardBg);

        repaint();
        revalidate();
    }

    private JPanel createDynamicForm(FormField[] fields) {
        JPanel panel = new JPanel(new GridBagLayout());

        titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
                "Administrar Registro");
        titledBorder.setTitleFont(Fonts.HEADER_FONT);
        panel.setBorder(BorderFactory.createCompoundBorder(titledBorder, new EmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < fields.length; i++) {
            FormField field = fields[i];

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.0;
            JLabel label = new JLabel(field.getLabel() + ":");
            label.setFont(Fonts.TEXT_FONT);
            panel.add(label, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.weightx = 1.0;

            JComponent component = field.getComponent();
            component.setFont(Fonts.TEXT_FONT);
            component.setPreferredSize(new Dimension(component.getPreferredSize().width, 32));

            fieldsMap.put(field.getLabel(), component);
            panel.add(component, gbc);
        }

        return panel;
    }

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
        panel.add(Box.createHorizontalGlue());
        panel.add(btnClear);

        return panel;
    }

    // Helper methods stay unchanged...
    public String getTextFieldValue(String label) {
        JComponent comp = fieldsMap.get(label);
        if (comp instanceof JTextField) {
            return ((JTextField) comp).getText().trim();
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    public <T> T getSelectedComboObject(String label) {
        JComponent comp = fieldsMap.get(label);
        if (comp instanceof JComboBox) {
            return (T) ((JComboBox<?>) comp).getSelectedItem();
        }
        return null;
    }

    public void setFieldValue(String label, Object value) {
        JComponent comp = fieldsMap.get(label);
        if (comp instanceof JFormattedTextField) {
            ((JFormattedTextField) comp).setValue(value != null ? value.toString() : null);
        } else if (comp instanceof JTextField) {
            ((JTextField) comp).setText(value != null ? value.toString() : "");
        } else if (comp instanceof JComboBox) {
            JComboBox<?> comboBox = (JComboBox<?>) comp;
            if (value == null) return;
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
                ((JFormattedTextField) comp).setValue(null);
            } else if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
            } else if (comp instanceof JComboBox) {
                JComboBox<?> comboBox = (JComboBox<?>) comp;
                if (comboBox.getItemCount() > 0) {
                    comboBox.setSelectedIndex(0);
                }
            }
        });
    }

    public DefaultTableModel getTableModel() { return tableModel; }
    public JTable getTable() { return table; }
    public void addBtnCreateListener(ActionListener l) { btnCreate.addActionListener(l); }
    public void addBtnUpdateListener(ActionListener l) { btnUpdate.addActionListener(l); }
    public void addBtnDeleteListener(ActionListener l) { btnDelete.addActionListener(l); }
    public void addBtnClearListener(ActionListener l) { btnClear.addActionListener(l); }
    public Map<String, JComponent> getFieldsMap() { return fieldsMap; }
}