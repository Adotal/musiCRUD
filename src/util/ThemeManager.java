package util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ThemeManager {

    public enum Theme { LIGHT, DARK }

    private static ThemeManager instance;
    private Theme currentTheme = Theme.LIGHT;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private ThemeManager() {}

    public static synchronized ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }

    public Theme getCurrentTheme() {
        return currentTheme;
    }

    public void setTheme(Theme theme) {
        if (this.currentTheme != theme) {
            Theme oldTheme = this.currentTheme;
            this.currentTheme = theme;
            pcs.firePropertyChange("theme", oldTheme, theme);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
}