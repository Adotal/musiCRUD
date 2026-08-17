package controller;

import java.awt.Desktop;
import java.net.URI;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import view.AlbumTabView;
import view.ArtistAlbumTabView;
import view.ArtistTabView;
import view.GenreTabView;
import view.HomeView;
import view.SongTabView;

public class HomeController {

    private HomeView view;
    private SongController songController;
    private AlbumController albumController;
    private ArtistController artistController;
    private GenreController genreController;
    private ArtistAlbumController artistAlbumController;

    public HomeController(HomeView view) {
        this.view = view;

        initTabs();

        // Attach listeners to the view components
        initListeners();
    }

    private void initTabs() {

        // Songs tab
        SongTabView songTabView = new SongTabView();
        songController = new SongController(songTabView);
        view.getTabbedPane().addTab("Canciones", songTabView);

        // AlbumTab
        AlbumTabView albumTabView = new AlbumTabView();
        albumController = new AlbumController(albumTabView);
        view.getTabbedPane().addTab("Álbumes", albumTabView);

        // Artist
        ArtistTabView artistTabView = new ArtistTabView();
        artistController = new ArtistController(artistTabView);
        view.getTabbedPane().addTab("Artistas", artistTabView);

        // Genre
        GenreTabView genreTabView = new GenreTabView();
        genreController = new GenreController(genreTabView);
        view.getTabbedPane().addTab("Genéros", genreTabView);

        // Relation artist-album
        ArtistAlbumTabView artistAlbumTabView = new ArtistAlbumTabView();
        artistAlbumController = new ArtistAlbumController(artistAlbumTabView);
        view.getTabbedPane().addTab("Artistas-Álbumes", artistAlbumTabView);

        JTabbedPane tabbedPane = view.getTabbedPane();
        tabbedPane.addChangeListener(e -> {
            // Obtain tab title
            int selectedIndex = tabbedPane.getSelectedIndex();
            String tabTitle = tabbedPane.getTitleAt(selectedIndex);

            // If access to the tab, reload ComboBox (for if a new FK was added)
            if ("Canciones".equals(tabTitle)) {

                songController.populateDropdowns();
            }
        });

    }

    private void initListeners() {

        view.addExitListener(e -> System.exit(0));
        view.addThemeToggleListener(e -> System.out.println("Changing theme..."));
        view.addOpenGitHubListener(e -> {

            int resp = JOptionPane.showConfirmDialog(view, "Redirigir al GitHub Fuente del proyecto");

            if (0 == resp) {
                openURL("https://github.com/Adotal/musiCRUD");
            }
        });
        view.addAuthorListener(e -> {
            JOptionPane.showMessageDialog(view, "Adro Yael Ornelas Ornelas");
        });

    }

    public static void openURL(String urlString) {
        try {
            // Try to use standard Java method if supported
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(urlString));
            } else {
                // Linux alternative using xdg-open
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(new String[] { "xdg-open", urlString });
            }
        } catch (Exception ex) {
            // If xdg-open fails
            try {
                Runtime.getRuntime().exec(new String[] { "fallback-platform-command", urlString });
                System.getLogger("Could'nt open URL");
            } catch (Exception e) {
                ex.printStackTrace();
            }
        }
    }
}
