package util.ui;

import javax.swing.JComponent;

public class FormField {
    private final String label;
    private final JComponent component;

    public FormField(String label, JComponent component) {
        this.label = label;
        this.component = component;
    }

    public String getLabel() { return label; }
    public JComponent getComponent() { return component; }
}
