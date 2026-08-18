package controller;

import java.awt.event.ItemEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import view.HomeView;
import view.LoginView;

public class LoginController {

    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;

        initListeners();
    }

    private void initListeners() {

        view.addSubmitListener(e -> submitLogin());
        view.addCheckBoxShowPasswordListener(e -> {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                view.getTxtPassword().setEchoChar((char) 0);
            } else {
                view.getTxtPassword().setEchoChar('.');

            }
        });

    }

    public void submitLogin() {

        String email = view.getTxtEmail().getText();
        String password = new String(view.getTxtPassword().getPassword());

        System.out.println(email + " " + password);

        if (email.equals("1") && password.equals("1")) {

            // Start new interface
            SwingUtilities.invokeLater(() -> {

                HomeView homeView = new HomeView();
                new HomeController(homeView);
                homeView.setVisible(true);

                // Close login
                view.dispose();

            });

        } else {
            JOptionPane.showMessageDialog(view, "Credenciales incorrectas", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
