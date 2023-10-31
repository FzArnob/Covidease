package gnu.kawa.swingviews;

import gnu.kawa.models.Display;
import gnu.kawa.models.Paintable;
import gnu.kawa.models.Viewable;
import gnu.kawa.models.Window;
import gnu.lists.AbstractSequence;
import gnu.lists.FString;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class SwingFrame extends JFrame implements Window {
    SwingDisplay display;

    public Display getDisplay() {
        return this.display;
    }

    public SwingFrame(String str, JMenuBar jMenuBar, Object obj) {
        LayoutManager layoutManager;
        String title = str;
        JMenuBar menubar = jMenuBar;
        Object contents = obj;
        if (title != null) {
            setTitle(title);
        }
        if (menubar != null) {
            setJMenuBar(menubar);
        }
        Container pane = getContentPane();
        new BoxLayout(pane, 0);
        pane.setLayout(layoutManager);
        addComponent(contents);
    }

    public void setContent(Object content) {
        Container container;
        new JPanel();
        setContentPane(container);
        addComponent(content);
        pack();
    }

    public void setMenuBar(Object menubar) {
        setJMenuBar((JMenuBar) menubar);
    }

    public void addComponent(Object obj) {
        Component component;
        Component component2;
        Object contents = obj;
        if ((contents instanceof FString) || (contents instanceof String)) {
            new JLabel(contents.toString());
            Component add = getContentPane().add(component);
        } else if (contents instanceof AbstractSequence) {
            AbstractSequence seq = (AbstractSequence) contents;
            int iter = seq.startPos();
            while (true) {
                int nextPos = seq.nextPos(iter);
                iter = nextPos;
                if (nextPos != 0) {
                    addComponent(seq.getPosPrevious(iter));
                } else {
                    return;
                }
            }
        } else if (contents instanceof Viewable) {
            ((Viewable) contents).makeView(getDisplay(), getContentPane());
        } else if (contents instanceof Paintable) {
            new SwingPaintable((Paintable) contents);
            Component add2 = getContentPane().add(component2);
        } else if (contents != null) {
            Component add3 = getContentPane().add((Component) contents);
        }
    }

    public void open() {
        pack();
        setVisible(true);
    }
}
