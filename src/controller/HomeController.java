package controller;

import javax.swing.JOptionPane;

import view.HomeView;
import view.SongTabView;

public class HomeController {
    
    
    private HomeView view;
    

    public HomeController(HomeView view) {
        this.view = view;


        initTabs();

        // Attach listeners to the view components
        initListeners();
    }

    private void initTabs() {
        

        // Add songs tab
        SongTabView songTabView = new SongTabView();
        SongController  songController = new SongController(songTabView);
        view.getTabbedPane().addTab("Canciones", songTabView);

        
    }

    private void initListeners() {

        view.addExitListener(e -> System.exit(0));
        view.addThemeToggleListener(e -> System.out.println("Changing theme..."));        
        view.addOpenGitHubListener(e-> {
            System.out.println("Opening GitHub link...");
        });
        view.addAuthorListener(e -> {
            JOptionPane.showMessageDialog(view, "Adro Yael Ornelas Ornelas");
        });

    }
}
