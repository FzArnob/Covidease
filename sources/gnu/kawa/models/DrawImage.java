package gnu.kawa.models;

import gnu.mapping.WrappedException;
import gnu.text.Path;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class DrawImage extends Model implements Paintable, Serializable {
    String description;
    BufferedImage image;
    Path src;

    public DrawImage() {
    }

    public void makeView(Display display, Object where) {
        display.addImage(this, where);
    }

    /* access modifiers changed from: package-private */
    public void loadImage() {
        if (this.image == null) {
            try {
                this.image = ImageIO.read(this.src.openInputStream());
            } catch (Throwable th) {
                throw WrappedException.wrapIfNeeded(th);
            }
        }
    }

    public DrawImage(BufferedImage image2) {
        this.image = image2;
    }

    public void paint(Graphics2D graphics) {
        loadImage();
        boolean drawImage = graphics.drawImage(this.image, (AffineTransform) null, (ImageObserver) null);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.awt.geom.Rectangle2D getBounds2D() {
        /*
            r10 = this;
            r0 = r10
            r3 = r0
            r3.loadImage()
            r3 = r0
            java.awt.image.BufferedImage r3 = r3.image
            int r3 = r3.getWidth()
            r1 = r3
            r3 = r0
            java.awt.image.BufferedImage r3 = r3.image
            int r3 = r3.getHeight()
            r2 = r3
            java.awt.geom.Rectangle2D$Float r3 = new java.awt.geom.Rectangle2D$Float
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 0
            r6 = 0
            r7 = r1
            float r7 = (float) r7
            r8 = r2
            float r8 = (float) r8
            r4.<init>(r5, r6, r7, r8)
            r0 = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.models.DrawImage.getBounds2D():java.awt.geom.Rectangle2D");
    }

    public Paintable transform(AffineTransform tr) {
        Paintable paintable;
        new WithTransform(this, tr);
        return paintable;
    }

    public Image getImage() {
        loadImage();
        return this.image;
    }

    public Path getSrc() {
        return this.src;
    }

    public void setSrc(Path src2) {
        Path path = src2;
        this.src = path;
    }
}
