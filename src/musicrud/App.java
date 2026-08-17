package musicrud;

import javax.security.auth.login.LoginContext;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.HomeController;
import controller.LoginController;
import view.HomeView;
import view.LoginView;

public class App {
    public static void main(String[] args) {

        // Add tiny ui look improvements
        JFrame.setDefaultLookAndFeelDecorated(true);

        SwingUtilities.invokeLater(() -> {

            // LoginView loginView = new LoginView();
            // LoginController loginController = new LoginController(loginView);
            // loginView
            // .setVisible(true);

            SwingUtilities.invokeLater(() -> {

                HomeView homeView = new HomeView();
                new HomeController(homeView);
                homeView.setVisible(true);

            });
        });

    }
}
