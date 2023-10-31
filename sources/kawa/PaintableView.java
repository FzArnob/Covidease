package kawa;

import gnu.kawa.models.Paintable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.View;

/* compiled from: ReplPane */
class PaintableView extends View {
    Rectangle2D bounds;

    /* renamed from: p */
    Paintable f260p;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PaintableView(Element elem, Paintable paintable) {
        super(elem);
        Paintable paintable2 = paintable;
        this.f260p = paintable2;
        this.bounds = paintable2.getBounds2D();
    }

    public void paint(Graphics g, Shape a) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle bounds2 = a.getBounds();
        AffineTransform saveTransform = g2.getTransform();
        Paint savePaint = g2.getPaint();
        try {
            g2.translate(bounds2.x, bounds2.y);
            g2.setPaint(Color.BLACK);
            this.f260p.paint(g2);
            g2.setTransform(saveTransform);
            g2.setPaint(savePaint);
        } catch (Throwable th) {
            Throwable th2 = th;
            g2.setTransform(saveTransform);
            g2.setPaint(savePaint);
            throw th2;
        }
    }

    public float getAlignment(int i) {
        int axis = i;
        switch (axis) {
            case 1:
                return 1.0f;
            default:
                return PaintableView.super.getAlignment(axis);
        }
    }

    public float getPreferredSpan(int i) {
        Throwable th;
        StringBuilder sb;
        int axis = i;
        switch (axis) {
            case 0:
                return (float) this.bounds.getWidth();
            case 1:
                return (float) this.bounds.getHeight();
            default:
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Invalid axis: ").append(axis).toString());
                throw th2;
        }
    }

    public Shape modelToView(int i, Shape shape, Position.Bias bias) throws BadLocationException {
        Throwable th;
        StringBuilder sb;
        int pos = i;
        Shape a = shape;
        Position.Bias bias2 = bias;
        int p0 = getStartOffset();
        int p1 = getEndOffset();
        if (pos < p0 || pos > p1) {
            Throwable th2 = th;
            new StringBuilder();
            new BadLocationException(sb.append(pos).append(" not in range ").append(p0).append(",").append(p1).toString(), pos);
            throw th2;
        }
        PaintableView r = a.getBounds();
        if (pos == p1) {
            r.x += r.width;
        }
        r.width = 0;
        return r;
    }

    public int viewToModel(float x, float f, Shape a, Position.Bias[] biasArr) {
        float f2 = f;
        Position.Bias[] bias = biasArr;
        Rectangle alloc = (Rectangle) a;
        if (x < ((float) (alloc.x + (alloc.width / 2)))) {
            bias[0] = Position.Bias.Forward;
            return getStartOffset();
        }
        bias[0] = Position.Bias.Backward;
        return getEndOffset();
    }
}
