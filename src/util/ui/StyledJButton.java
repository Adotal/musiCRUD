package util.ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 
import javax.swing.BorderFactory;
import javax.swing.JButton;

import util.Fonts;

public class StyledJButton extends JButton {

    public StyledJButton(String title, Color bg, Color fg) {
        setText(title);
        setFont(Fonts.HEADER_FONT);
        setBackground(bg);
        setForeground(fg);
        setFocusPainted(false);

        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        // Padding inside buttons
        setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(bg.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(bg);
            }
        });

    }

}
