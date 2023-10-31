package gnu.kawa.swingviews;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.kawa.models.Label;
import gnu.kawa.models.Model;
import gnu.kawa.models.ModelListener;
import javax.swing.JLabel;

/* compiled from: SwingDisplay */
class SwingLabel extends JLabel implements ModelListener {
    Label model;

    public SwingLabel(Label label) {
        Label model2 = label;
        this.model = model2;
        String text = model2.getText();
        if (text != null) {
            SwingLabel.super.setText(text);
        }
        model2.addListener((ModelListener) this);
    }

    public void modelUpdated(Model model2, Object key) {
        Model model3 = model2;
        if (key == PropertyTypeConstants.PROPERTY_TYPE_TEXT && model3 == this.model) {
            SwingLabel.super.setText(this.model.getText());
        }
    }

    public void setText(String str) {
        String text = str;
        if (this.model == null) {
            SwingLabel.super.setText(text);
        } else {
            this.model.setText(text);
        }
    }
}
