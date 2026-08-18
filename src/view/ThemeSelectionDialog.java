package view;

import util.ThemeManager.Theme;

import javax.swing.*;
import java.awt.*;

public class ThemeSelectionDialog extends JDialog {

    private JRadioButton rbLight;
    private JRadioButton rbDark;
    private JButton btnApply;

    public ThemeSelectionDialog(Frame owner, Theme currentTheme) {
        super(owner, "Seleccionar Tema", true);
        initUI(currentTheme);
    }

    private void initUI(Theme currentTheme) {
        setLayout(new BorderLayout(10, 10));
        setSize(280, 150);
        setLocationRelativeTo(getOwner());

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        rbLight = new JRadioButton("Claro", currentTheme == Theme.LIGHT);
        rbDark = new JRadioButton("Oscuro", currentTheme == Theme.DARK);

        ButtonGroup group = new ButtonGroup();
        group.add(rbLight);
        group.add(rbDark);

        radioPanel.add(rbLight);
        radioPanel.add(rbDark);

        btnApply = new JButton("Aplicar");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnApply);

        add(radioPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public Theme getSelectedTheme() {
        return rbDark.isSelected() ? Theme.DARK : Theme.LIGHT;
    }

    public void addApplyListener(java.awt.event.ActionListener listener) {
        btnApply.addActionListener(listener);
    }
}