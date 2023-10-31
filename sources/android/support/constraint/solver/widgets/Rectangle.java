package android.support.constraint.solver.widgets;

public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f10x;

    /* renamed from: y */
    public int f11y;

    public Rectangle() {
    }

    public void setBounds(int x, int y, int width2, int height2) {
        this.f10x = x;
        this.f11y = y;
        this.width = width2;
        this.height = height2;
    }

    /* access modifiers changed from: package-private */
    public void grow(int i, int i2) {
        int w = i;
        int h = i2;
        this.f10x -= w;
        this.f11y -= h;
        this.width += 2 * w;
        this.height += 2 * h;
    }

    /* access modifiers changed from: package-private */
    public boolean intersects(Rectangle rectangle) {
        Rectangle bounds = rectangle;
        return this.f10x >= bounds.f10x && this.f10x < bounds.f10x + bounds.width && this.f11y >= bounds.f11y && this.f11y < bounds.f11y + bounds.height;
    }

    public boolean contains(int i, int i2) {
        int x = i;
        int y = i2;
        return x >= this.f10x && x < this.f10x + this.width && y >= this.f11y && y < this.f11y + this.height;
    }

    public int getCenterX() {
        return (this.f10x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f11y + this.height) / 2;
    }
}
