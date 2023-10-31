package gnu.kawa.swingviews;

import gnu.kawa.models.Paintable;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class SwingPaintable extends JPanel {
    Dimension dim;
    Paintable paintable;

    public SwingPaintable(Paintable paintable2) {
        Dimension dimension;
        Paintable paintable3 = paintable2;
        this.paintable = paintable3;
        Rectangle2D rect = paintable3.getBounds2D();
        int h = (int) Math.ceil(rect.getHeight());
        new Dimension((int) Math.ceil(rect.getWidth()), h);
        this.dim = dimension;
    }

    public void paint(Graphics g) {
        this.paintable.paint((Graphics2D) g);
    }

    public Dimension getPreferredSize() {
        return this.dim;
    }
}
