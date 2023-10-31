package gnu.kawa.swingviews;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.kawa.models.Button;
import gnu.kawa.models.Model;
import gnu.kawa.models.ModelListener;
import java.awt.Color;
import javax.swing.JButton;

public class SwingButton extends JButton implements ModelListener {
    Button model;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SwingButton(gnu.kawa.models.Button r11) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r5 = r0
            r6 = r1
            java.lang.String r6 = r6.getText()
            r5.<init>(r6)
            r5 = r0
            gnu.kawa.swingviews.SwModel r6 = new gnu.kawa.swingviews.SwModel
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r1
            r7.<init>(r8)
            r5.setModel(r6)
            r5 = r0
            r6 = r1
            r5.model = r6
            r5 = r1
            java.lang.Object r5 = r5.getAction()
            r2 = r5
            r5 = r2
            if (r5 == 0) goto L_0x002e
            r5 = r0
            r6 = r2
            java.awt.event.ActionListener r6 = gnu.kawa.swingviews.SwingDisplay.makeActionListener(r6)
            r5.addActionListener(r6)
        L_0x002e:
            r5 = r1
            r6 = r0
            r5.addListener((gnu.kawa.models.ModelListener) r6)
            r5 = r1
            java.awt.Color r5 = r5.getForeground()
            r3 = r5
            r5 = r3
            if (r5 == 0) goto L_0x0041
            r5 = r0
            r6 = r3
            gnu.kawa.swingviews.SwingButton.super.setBackground(r6)
        L_0x0041:
            r5 = r1
            java.awt.Color r5 = r5.getBackground()
            r4 = r5
            r5 = r4
            if (r5 == 0) goto L_0x004f
            r5 = r0
            r6 = r4
            gnu.kawa.swingviews.SwingButton.super.setBackground(r6)
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.swingviews.SwingButton.<init>(gnu.kawa.models.Button):void");
    }

    public void setText(String str) {
        String text = str;
        if (this.model == null) {
            SwingButton.super.setText(text);
        } else {
            this.model.setText(text);
        }
    }

    public void setForeground(Color color) {
        Color fg = color;
        if (this.model == null) {
            SwingButton.super.setForeground(fg);
        } else {
            this.model.setForeground(fg);
        }
    }

    public void setBackground(Color color) {
        Color bg = color;
        if (this.model == null) {
            SwingButton.super.setBackground(bg);
        } else {
            this.model.setBackground(bg);
        }
    }

    public void modelUpdated(Model model2, Object obj) {
        Model model3 = model2;
        Object key = obj;
        if (key == PropertyTypeConstants.PROPERTY_TYPE_TEXT && model3 == this.model) {
            SwingButton.super.setText(this.model.getText());
        } else if (key == "foreground" && model3 == this.model) {
            SwingButton.super.setForeground(this.model.getForeground());
        } else if (key == "background" && model3 == this.model) {
            SwingButton.super.setBackground(this.model.getBackground());
        }
    }
}
