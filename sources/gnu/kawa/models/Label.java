package gnu.kawa.models;

import com.google.appinventor.components.common.PropertyTypeConstants;
import java.io.Serializable;

public class Label extends Model implements Viewable, Serializable {
    String text;

    public Label() {
    }

    public Label(String text2) {
        this.text = text2;
    }

    public void makeView(Display display, Object where) {
        display.addLabel(this, where);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text2) {
        this.text = text2;
        notifyListeners(PropertyTypeConstants.PROPERTY_TYPE_TEXT);
    }
}
