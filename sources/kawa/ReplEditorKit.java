package kawa;

import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

/* compiled from: ReplPane */
class ReplEditorKit extends StyledEditorKit {
    ViewFactory factory;
    final ReplPane pane;
    ViewFactory styledFactory = ReplEditorKit.super.getViewFactory();

    public ReplEditorKit(ReplPane replPane) {
        ViewFactory viewFactory;
        ReplPane pane2 = replPane;
        this.pane = pane2;
        final ReplPane replPane2 = pane2;
        new ViewFactory(this) {
            final /* synthetic */ ReplEditorKit this$0;

            {
                this.this$0 = r6;
            }

            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public javax.swing.text.View create(javax.swing.text.Element r11) {
                /*
                    r10 = this;
                    r0 = r10
                    r1 = r11
                    r4 = r1
                    java.lang.String r4 = r4.getName()
                    r2 = r4
                    r4 = r2
                    java.lang.String r5 = "Viewable"
                    if (r4 != r5) goto L_0x001a
                    kawa.ReplEditorKit$1$1 r4 = new kawa.ReplEditorKit$1$1
                    r9 = r4
                    r4 = r9
                    r5 = r9
                    r6 = r0
                    r7 = r1
                    r5.<init>(r6, r7)
                    r0 = r4
                L_0x0019:
                    return r0
                L_0x001a:
                    r4 = r2
                    java.lang.String r5 = "Paintable"
                    if (r4 != r5) goto L_0x003a
                    r4 = r1
                    javax.swing.text.AttributeSet r4 = r4.getAttributes()
                    r3 = r4
                    kawa.PaintableView r4 = new kawa.PaintableView
                    r9 = r4
                    r4 = r9
                    r5 = r9
                    r6 = r1
                    r7 = r3
                    java.lang.Object r8 = kawa.ReplPane.PaintableAttribute
                    java.lang.Object r7 = r7.getAttribute(r8)
                    gnu.kawa.models.Paintable r7 = (gnu.kawa.models.Paintable) r7
                    r5.<init>(r6, r7)
                    r0 = r4
                    goto L_0x0019
                L_0x003a:
                    r4 = r0
                    kawa.ReplEditorKit r4 = r4.this$0
                    javax.swing.text.ViewFactory r4 = r4.styledFactory
                    r5 = r1
                    javax.swing.text.View r4 = r4.create(r5)
                    r0 = r4
                    goto L_0x0019
                */
                throw new UnsupportedOperationException("Method not decompiled: kawa.ReplEditorKit.C12431.create(javax.swing.text.Element):javax.swing.text.View");
            }
        };
        this.factory = viewFactory;
    }

    public ViewFactory getViewFactory() {
        return this.factory;
    }
}
