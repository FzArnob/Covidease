package gnu.kawa.swingviews;

import gnu.kawa.models.Model;
import gnu.kawa.models.ModelListener;
import javax.swing.Box;

/* compiled from: SwingDisplay */
class SwingBox extends Box implements ModelListener {
    gnu.kawa.models.Box model;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SwingBox(gnu.kawa.models.Box r10, gnu.kawa.models.Display r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r6 = r0
            r7 = r1
            int r7 = r7.getAxis()
            r6.<init>(r7)
            r6 = r1
            r7 = r0
            r6.addListener((gnu.kawa.models.ModelListener) r7)
            r6 = r1
            gnu.kawa.models.Viewable r6 = r6.getCellSpacing()
            r3 = r6
            r6 = r1
            int r6 = r6.getComponentCount()
            r4 = r6
            r6 = 0
            r5 = r6
        L_0x001f:
            r6 = r5
            r7 = r4
            if (r6 >= r7) goto L_0x003d
            r6 = r5
            if (r6 <= 0) goto L_0x002f
            r6 = r3
            if (r6 == 0) goto L_0x002f
            r6 = r3
            r7 = r2
            r8 = r0
            r6.makeView(r7, r8)
        L_0x002f:
            r6 = r1
            r7 = r5
            gnu.kawa.models.Viewable r6 = r6.getComponent(r7)
            r7 = r2
            r8 = r0
            r6.makeView(r7, r8)
            int r5 = r5 + 1
            goto L_0x001f
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.swingviews.SwingBox.<init>(gnu.kawa.models.Box, gnu.kawa.models.Display):void");
    }

    public void modelUpdated(Model model2, Object key) {
    }
}
