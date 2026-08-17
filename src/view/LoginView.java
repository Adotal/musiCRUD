package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import util.AppColors;
import util.Fonts;

public class LoginView extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnSubmit;

    public LoginView() {
        super();

        // Window Configuration
        setTitle("Sign Up");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 480);
        setLocationRelativeTo(null);

        // Main background panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(AppColors.BACKGROUND_WHITE);

        // Inner content card
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(AppColors.CARD);
        cardPanel.setBorder(new EmptyBorder(35, 40, 35, 40));

        // Title
        JLabel title = new JLabel("Create Account");
        title.setFont(Fonts.TITLE_FONT);
        title.setForeground(AppColors.TEXT_DARK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardPanel.add(title);

        cardPanel.add(Box.createVerticalStrut(25));

        // Email Field Group
        txtEmail = createInputField(cardPanel, "EMAIL ADDRESS", false);
        cardPanel.add(Box.createVerticalStrut(20));

        // Name Field Group
        txtPassword = (JPasswordField) createInputField(cardPanel, "PASSWORD", true);
        cardPanel.add(Box.createVerticalStrut(23));

        // Primary Button
        btnSubmit = createPrimaryButton("Get Started");
        cardPanel.add(btnSubmit);

        mainPanel.add(cardPanel);
        add(mainPanel);
    }

    // HELPER: Generates a labeled input field and adds it to the target panel
    private JTextField createInputField(JPanel container, String labelText, boolean isPassword) {
        JLabel label = new JLabel(labelText);
        label.setFont(Fonts.TEXT_FONT);
        label.setForeground(AppColors.MUTED);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(label);

        container.add(Box.createVerticalStrut(6));

        // If is password or textField
        JTextField input = isPassword ? new JPasswordField() : new JTextField();

        input.setFont(Fonts.HEADER_FONT);
        input.setPreferredSize(new Dimension(280, 40));
        input.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        input.setAlignmentX(Component.LEFT_ALIGNMENT);
        input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.BORDER, 1),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        container.add(input);

        return input;
    }

    // HELPER: Generates a styled modern flat button with mouse hover effects
    private JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(Fonts.HEADER_FONT);
        button.setBackground(AppColors.PRIMARY);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(280, 45));
        button.setMaximumSize(new Dimension(Short.MAX_VALUE, 45));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(AppColors.PRIMARY.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(AppColors.PRIMARY);
            }
        });

        return button;
    }

    // Getters for controller
    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    // Setter of button event
    public void addSubmitListener(ActionListener actionListener) {
        btnSubmit.addActionListener(actionListener);
    }

}