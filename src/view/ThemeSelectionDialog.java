package view;

import util.ThemeManager.Theme;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ThemeSelectionDialog extends JDialog {

    private JRadioButton rbLight;
    private JRadioButton rbDark;
    private JButton btnApply;
    private JButton btnCancel;

    public ThemeSelectionDialog(Frame owner, Theme currentTheme) {
        super(owner, "Seleccionar Tema", true);
        initUI(currentTheme);
    }

    private void initUI(Theme currentTheme) {
        // Configuración de la ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        // Panel principal con márgenes limpios
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Panel de selección de tema (Opciones alineadas a la izquierda)
        JPanel radioPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        rbLight = new JRadioButton("Modo Claro", currentTheme == Theme.LIGHT);
        rbDark = new JRadioButton("Modo Oscuro", currentTheme == Theme.DARK);

        // Atajos de teclado (Mnemónicos)
        rbLight.setMnemonic('C');
        rbDark.setMnemonic('O');

        ButtonGroup group = new ButtonGroup();
        group.add(rbLight);
        group.add(rbDark);

        radioPanel.add(rbLight);
        radioPanel.add(rbDark);

        // Panel de botones (Aplicar y Cancelar alineados a la derecha)
        btnApply = new JButton("Aplicar");
        btnCancel = new JButton("Cancelar");
        
        // El botón Aplicar reacciona al presionar la tecla Enter
        getRootPane().setDefaultButton(btnApply);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnApply);

        // Comportamiento del botón Cancelar
        btnCancel.addActionListener(e -> dispose());

        // Ensamblado de componentes
        mainPanel.add(radioPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);

        // Ajusta el tamaño automáticamente según el contenido y centra
        pack();
        setLocationRelativeTo(getOwner());
    }

    public Theme getSelectedTheme() {
        return rbDark.isSelected() ? Theme.DARK : Theme.LIGHT;
    }

    public void addApplyListener(ActionListener listener) {
        btnApply.addActionListener(listener);
    }
}
