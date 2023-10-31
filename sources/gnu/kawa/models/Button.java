package gnu.kawa.models;

import com.google.appinventor.components.common.PropertyTypeConstants;
import java.awt.Color;

public class Button extends Model {
    Object action;
    Color background;
    boolean disabled;
    Color foreground;
    String text;
    Object width;

    public Button() {
    }

    public void makeView(Display display, Object where) {
        display.addButton(this, where);
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean disabled2) {
        boolean z = disabled2;
        this.disabled = z;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text2) {
        this.text = text2;
        notifyListeners(PropertyTypeConstants.PROPERTY_TYPE_TEXT);
    }

    public Object getAction() {
        return this.action;
    }

    public void setAction(Object action2) {
        Object obj = action2;
        this.action = obj;
    }

    public Color getForeground() {
        return this.foreground;
    }

    public void setForeground(Color fg) {
        this.foreground = fg;
        notifyListeners("foreground");
    }

    public Color getBackground() {
        return this.background;
    }

    public void setBackground(Color bg) {
        this.background = bg;
        notifyListeners("background");
    }
}
