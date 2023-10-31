package gnu.kawa.swingviews;

import gnu.lists.SeqPosition;
import javax.swing.text.Position;

/* compiled from: SwingContent */
class GapPosition extends SeqPosition implements Position {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GapPosition(gnu.lists.CharBuffer r8, int r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r0
            r4 = r1
            r5 = r2
            r6 = r2
            if (r6 == 0) goto L_0x000e
            r6 = 1
        L_0x000a:
            r3.<init>(r4, r5, r6)
            return
        L_0x000e:
            r6 = 0
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.swingviews.GapPosition.<init>(gnu.lists.CharBuffer, int):void");
    }

    public int getOffset() {
        return nextIndex();
    }
}
