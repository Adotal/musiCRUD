package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import util.AppColors;
import util.Fonts;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class HomeView extends JFrame {

    private JMenuBar jMenuBar;
    private JMenu jMenuOptions, jMenuAbout;
    private JMenuItem jMenuItemExit, jMenuItemAuthor, jMenuItemGithub, jMenuItemToggleTheme, jMenuItemLogOut;
    // Tabs (one for each table)
    private JTabbedPane tabbedPane;

    public HomeView() {
        super();
        init();
    }

    public void init() {

        // Stylize TabbedPane
        UIManager.put("TabbedPane.focus", new Color(0, 0, 0, 0));
        UIManager.put("TabbedPane.selectedTabPadAreaHeight", 0);
        // Cambia el color de la pestaña seleccionada (ponemos el gris oscuro)
        UIManager.put("TabbedPane.selected", new Color(33, 37, 41));
        // Cambia el color del fondo de la barra de pestañas
        UIManager.put("TabbedPane.background", new Color(52, 58, 64));
        // Cambia el color del borde feo que rodea al contenido
        UIManager.put("TabbedPane.contentAreaColor", new Color(33, 37, 41));

        // Aplicar a los elementos de la lista (JMenuItem)
        UIManager.put("MenuItem.selectionBackground", AppColors.BACKGROUND_WHITE);
        UIManager.put("MenuItem.selectionForeground", AppColors.TEXT_DARK);

        setLayout(new BorderLayout());
        setTitle("MusiCRUD");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 600);
        setLocationRelativeTo(null);

        // Initialize and add menubar
        jMenuBar = new JMenuBar();
        jMenuOptions = new JMenu("Opciones");
        jMenuAbout = new JMenu("Acerca de");
        jMenuItemToggleTheme = new JMenuItem("Cambiar tema");
        jMenuItemExit = new JMenuItem("Salir");
        jMenuItemAuthor = new JMenuItem("Autor");
        jMenuItemGithub = new JMenuItem("GitHub");
        jMenuItemLogOut = new JMenuItem("Cerrar sesión");

        jMenuOptions.add(jMenuItemToggleTheme);
        jMenuOptions.add(jMenuItemExit);
        jMenuOptions.add(jMenuItemLogOut);
        jMenuAbout.add(jMenuItemAuthor);
        jMenuAbout.add(jMenuItemGithub);

        jMenuBar.add(jMenuOptions);
        jMenuBar.add(jMenuAbout);

        setJMenuBar(jMenuBar);

        // Topbar style
        jMenuBar.setBackground(AppColors.BACKGROUND_VIOLET);
        jMenuBar.setBorder(BorderFactory.createLineBorder(new Color(52, 58, 64), 1));

        // Crear un arreglo para estilizar todos los menús y elementos rápido
        Object[] componentesMenu = {
                jMenuOptions, jMenuAbout,
                jMenuItemToggleTheme, jMenuItemExit, jMenuItemAuthor, jMenuItemGithub, jMenuItemLogOut
        };

        for (Object comp : componentesMenu) {
            if (comp instanceof JMenu) {
                JMenu m = (JMenu) comp;
                m.setForeground(AppColors.TEXT_WHITE);
                m.setFont(Fonts.HEADER_FONT);
            } else if (comp instanceof JMenuItem) {
                JMenuItem mi = (JMenuItem) comp;
                mi.setBackground(AppColors.BACKGROUND_VIOLET);
                mi.setForeground(AppColors.TEXT_WHITE);
                mi.setFont(Fonts.TEXT_FONT);
                mi.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12)); // Espaciado interno
            }
        }

        // Create the Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(Fonts.HEADER_FONT);
        tabbedPane.setBackground(AppColors.BACKGROUND_VIOLET);
        tabbedPane.setForeground(AppColors.TEXT_WHITE);

        tabbedPane.setBorder(BorderFactory.createLineBorder(AppColors.BLACK, 1));
        // Add the tabbed pane to the center of the main window
        add(tabbedPane, BorderLayout.CENTER);

        // Add Shorcuts to items
        KeyStroke keyStrokeF5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
        jMenuItemAuthor.setAccelerator(keyStrokeF5);

        KeyStroke keyStrokeSHIFTQ = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.SHIFT_DOWN_MASK);
        jMenuItemExit.setAccelerator(keyStrokeSHIFTQ);

        KeyStroke keyStrokeCTRLG = KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK);
        jMenuItemGithub.setAccelerator(keyStrokeCTRLG);

        KeyStroke keyStrokeALTT = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.ALT_DOWN_MASK);
        jMenuItemToggleTheme.setAccelerator(keyStrokeALTT);

        KeyStroke keyStrokeCTRO = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        jMenuItemLogOut.setAccelerator(keyStrokeCTRO);
    }

    // Setters to add events
    public void addExitListener(ActionListener actionListener) {
        jMenuItemExit.addActionListener(actionListener);
    }

    public void addAuthorListener(ActionListener actionListener) {
        jMenuItemAuthor.addActionListener(actionListener);

    }

    public void addOpenGitHubListener(ActionListener actionListener) {
        jMenuItemGithub.addActionListener(actionListener);
    }

    public void addThemeToggleListener(ActionListener actionListener) {
        jMenuItemToggleTheme.addActionListener(actionListener);
    }

    public void addLogoutListener(ActionListener actionListener) {
        jMenuItemLogOut.addActionListener(actionListener);
    }

    // Getter to controller

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

}