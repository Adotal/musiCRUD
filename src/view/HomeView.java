package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class HomeView extends JFrame {

    private JMenuBar jMenuBar;
    private JMenu jMenuOptions, jMenuAbout;
    private JMenuItem jMenuItemExit, jMenuItemAuthor, jMenuItemGithub, jMenuItemToggleTheme;
    // Tabs (one for each table)
    private JTabbedPane tabbedPane;

    public HomeView() {
        super();
        init();
    }

    public void init() {

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

        jMenuOptions.add(jMenuItemToggleTheme);
        jMenuOptions.add(jMenuItemExit);
        jMenuAbout.add(jMenuItemAuthor);
        jMenuAbout.add(jMenuItemGithub);

        jMenuBar.add(jMenuOptions);
        jMenuBar.add(jMenuAbout);

        setJMenuBar(jMenuBar);

        // Create the Tabbed Pane
        tabbedPane = new JTabbedPane();
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

    // Getter to controller

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

}