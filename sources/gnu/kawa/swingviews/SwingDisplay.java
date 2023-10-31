package gnu.kawa.swingviews;

import gnu.kawa.models.Box;
import gnu.kawa.models.Button;
import gnu.kawa.models.Display;
import gnu.kawa.models.DrawImage;
import gnu.kawa.models.Label;
import gnu.kawa.models.Model;
import gnu.kawa.models.Paintable;
import gnu.kawa.models.Spacer;
import gnu.kawa.models.Text;
import gnu.kawa.models.Window;
import java.awt.Component;
import java.awt.Container;
import java.util.WeakHashMap;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class SwingDisplay extends Display {
    private static WeakHashMap documents = null;
    static final SwingDisplay instance;

    public SwingDisplay() {
    }

    static {
        SwingDisplay swingDisplay;
        new SwingDisplay();
        instance = swingDisplay;
    }

    public static Display getInstance() {
        return instance;
    }

    public Window makeWindow() {
        SwingFrame swingFrame;
        new SwingFrame((String) null, (JMenuBar) null, (Object) null);
        SwingFrame window = swingFrame;
        window.display = this;
        return window;
    }

    public void addButton(Button model, Object where) {
        Object obj;
        new SwingButton(model);
        addView(obj, where);
    }

    public void addLabel(Label model, Object where) {
        Object obj;
        new SwingLabel(model);
        addView(obj, where);
    }

    public void addImage(DrawImage model, Object where) {
        Object obj;
        Icon icon;
        new ImageIcon(model.getImage());
        new JLabel(icon);
        addView(obj, where);
    }

    public void addText(Text text, Object where) {
        Object obj;
        Text model = text;
        new JTextField(getSwingDocument(model), model.getText(), 50);
        addView(obj, where);
    }

    static synchronized Document getSwingDocument(Text text) {
        Document document;
        AbstractDocument.Content content;
        Document document2;
        WeakHashMap weakHashMap;
        Text model = text;
        synchronized (SwingDisplay.class) {
            if (documents == null) {
                new WeakHashMap();
                documents = weakHashMap;
            }
            Object existing = documents.get(model);
            if (existing != null) {
                document2 = (Document) existing;
            } else {
                new SwingContent(model.buffer);
                new PlainDocument(content);
                Document doc = document;
                Object put = documents.put(model, doc);
                document2 = doc;
            }
        }
        return document2;
    }

    public void addBox(Box model, Object where) {
        Object obj;
        new SwingBox(model, this);
        addView(obj, where);
    }

    public void addSpacer(Spacer spacer, Object where) {
        Object obj;
        Spacer model = spacer;
        new Box.Filler(model.getMinimumSize(), model.getPreferredSize(), model.getMaximumSize());
        addView(obj, where);
    }

    public void addView(Object view, Object where) {
        Component add = ((Container) where).add((Component) view);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.awt.event.ActionListener makeActionListener(java.lang.Object r5) {
        /*
            r0 = r5
            r1 = r0
            boolean r1 = r1 instanceof java.awt.event.ActionListener
            if (r1 == 0) goto L_0x000b
            r1 = r0
            java.awt.event.ActionListener r1 = (java.awt.event.ActionListener) r1
            r0 = r1
        L_0x000a:
            return r0
        L_0x000b:
            gnu.kawa.swingviews.ProcActionListener r1 = new gnu.kawa.swingviews.ProcActionListener
            r4 = r1
            r1 = r4
            r2 = r4
            r3 = r0
            gnu.mapping.Procedure r3 = (gnu.mapping.Procedure) r3
            r2.<init>(r3)
            r0 = r1
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.swingviews.SwingDisplay.makeActionListener(java.lang.Object):java.awt.event.ActionListener");
    }

    public Model coerceToModel(Object obj) {
        Model model;
        Component component;
        Model model2;
        Object component2 = obj;
        if (component2 instanceof Component) {
            new ComponentModel((Component) component2);
            return model2;
        } else if (!(component2 instanceof Paintable)) {
            return super.coerceToModel(component2);
        } else {
            new SwingPaintable((Paintable) component2);
            new ComponentModel(component);
            return model;
        }
    }
}
