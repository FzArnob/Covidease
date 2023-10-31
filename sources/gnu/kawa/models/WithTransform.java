package gnu.kawa.models;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class WithTransform implements Paintable {
    Paintable paintable;
    AffineTransform transform;

    public WithTransform(Paintable paintable2, AffineTransform transform2) {
        this.paintable = paintable2;
        this.transform = transform2;
    }

    public void paint(Graphics2D graphics2D) {
        Graphics2D graphics = graphics2D;
        AffineTransform saved = graphics.getTransform();
        try {
            graphics.transform(this.transform);
            this.paintable.paint(graphics);
            graphics.setTransform(saved);
        } catch (Throwable th) {
            Throwable th2 = th;
            graphics.setTransform(saved);
            throw th2;
        }
    }

    public Rectangle2D getBounds2D() {
        return this.transform.createTransformedShape(this.paintable.getBounds2D()).getBounds2D();
    }

    public Paintable transform(AffineTransform tr) {
        AffineTransform affineTransform;
        WithTransform withTransform;
        new AffineTransform(this.transform);
        AffineTransform combined = affineTransform;
        combined.concatenate(tr);
        new WithTransform(this.paintable, combined);
        return withTransform;
    }
}
