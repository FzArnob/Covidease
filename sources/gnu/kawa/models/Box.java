package gnu.kawa.models;

import java.io.Serializable;

public abstract class Box extends Model implements Viewable, Serializable {
    Viewable cellSpacing;
    Viewable[] components;
    int numComponents;

    public abstract int getAxis();

    public Box() {
    }

    public Viewable getCellSpacing() {
        return this.cellSpacing;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCellSpacing(java.lang.Object r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r4 = r1
            boolean r4 = r4 instanceof gnu.math.IntNum
            if (r4 != 0) goto L_0x000c
            r4 = r1
            boolean r4 = r4 instanceof java.lang.Integer
            if (r4 == 0) goto L_0x003a
        L_0x000c:
            r4 = r1
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            r2 = r4
            r4 = r0
            int r4 = r4.getAxis()
            if (r4 != 0) goto L_0x002f
            java.awt.Dimension r4 = new java.awt.Dimension
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r2
            r7 = 0
            r5.<init>(r6, r7)
        L_0x0025:
            r3 = r4
            r4 = r0
            r5 = r3
            gnu.kawa.models.Spacer r5 = gnu.kawa.models.Spacer.rigidArea(r5)
            r4.cellSpacing = r5
        L_0x002e:
            return
        L_0x002f:
            java.awt.Dimension r4 = new java.awt.Dimension
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = 0
            r7 = r2
            r5.<init>(r6, r7)
            goto L_0x0025
        L_0x003a:
            r4 = r0
            r5 = r1
            gnu.kawa.models.Viewable r5 = (gnu.kawa.models.Viewable) r5
            r4.cellSpacing = r5
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.models.Box.setCellSpacing(java.lang.Object):void");
    }

    public final int getComponentCount() {
        return this.numComponents;
    }

    public final Viewable getComponent(int i) {
        return this.components[i];
    }

    public void add(Viewable viewable) {
        Viewable component = viewable;
        Viewable[] arr = this.components;
        int n = this.numComponents;
        if (n == 0) {
            Viewable[] viewableArr = new Viewable[4];
            Viewable[] arr2 = viewableArr;
            this.components = viewableArr;
        } else if (arr.length <= n) {
            this.components = new Viewable[(2 * n)];
            System.arraycopy(arr, 0, this.components, 0, n);
            Viewable[] arr3 = this.components;
        }
        this.components[n] = component;
        this.numComponents = n + 1;
    }

    public void makeView(Display display, Object where) {
        display.addBox(this, where);
    }
}
