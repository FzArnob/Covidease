package kawa;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import gnu.mapping.OutPort;
import java.awt.Component;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ReplPaneOutPort extends OutPort {
    ReplDocument document;
    String str;
    AttributeSet style;
    TextPaneWriter tout;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReplPaneOutPort(kawa.ReplDocument r11, java.lang.String r12, javax.swing.text.AttributeSet r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r0
            kawa.TextPaneWriter r5 = new kawa.TextPaneWriter
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = r1
            r8 = r3
            r6.<init>(r7, r8)
            r6 = r1
            r7 = r2
            r8 = r3
            r4.<init>(r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.ReplPaneOutPort.<init>(kawa.ReplDocument, java.lang.String, javax.swing.text.AttributeSet):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    ReplPaneOutPort(kawa.TextPaneWriter r11, kawa.ReplDocument r12, java.lang.String r13, javax.swing.text.AttributeSet r14) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r0
            r6 = r1
            r7 = 1
            r8 = 1
            r9 = r3
            gnu.text.Path r9 = gnu.text.Path.valueOf(r9)
            r5.<init>(r6, r7, r8, r9)
            r5 = r0
            java.lang.String r6 = ""
            r5.str = r6
            r5 = r0
            r6 = r1
            r5.tout = r6
            r5 = r0
            r6 = r2
            r5.document = r6
            r5 = r0
            r6 = r4
            r5.style = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.ReplPaneOutPort.<init>(kawa.TextPaneWriter, kawa.ReplDocument, java.lang.String, javax.swing.text.AttributeSet):void");
    }

    public void write(String str2, MutableAttributeSet style2) {
        flush();
        this.document.write(str2, style2);
        setColumnNumber(1);
    }

    public synchronized void write(Component component) {
        MutableAttributeSet mutableAttributeSet;
        Component c = component;
        synchronized (this) {
            new SimpleAttributeSet();
            MutableAttributeSet style2 = mutableAttributeSet;
            StyleConstants.setComponent(style2, c);
            write(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, style2);
        }
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void print(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r3 = r1
            boolean r3 = r3 instanceof java.awt.Component
            if (r3 == 0) goto L_0x000f
            r3 = r0
            r4 = r1
            java.awt.Component r4 = (java.awt.Component) r4
            r3.write(r4)
        L_0x000e:
            return
        L_0x000f:
            r3 = r1
            boolean r3 = r3 instanceof gnu.kawa.models.Paintable
            if (r3 == 0) goto L_0x0037
            javax.swing.text.SimpleAttributeSet r3 = new javax.swing.text.SimpleAttributeSet
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()
            r2 = r3
            r3 = r2
            java.lang.String r4 = "$ename"
            java.lang.String r5 = "Paintable"
            r3.addAttribute(r4, r5)
            r3 = r2
            java.lang.Object r4 = kawa.ReplPane.PaintableAttribute
            r5 = r1
            r3.addAttribute(r4, r5)
            r3 = r0
            java.lang.String r4 = " "
            r5 = r2
            r3.write(r4, r5)
            goto L_0x000e
        L_0x0037:
            r3 = r1
            boolean r3 = r3 instanceof gnu.kawa.models.Viewable
            if (r3 == 0) goto L_0x005f
            javax.swing.text.SimpleAttributeSet r3 = new javax.swing.text.SimpleAttributeSet
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()
            r2 = r3
            r3 = r2
            java.lang.String r4 = "$ename"
            java.lang.String r5 = "Viewable"
            r3.addAttribute(r4, r5)
            r3 = r2
            java.lang.Object r4 = kawa.ReplPane.ViewableAttribute
            r5 = r1
            r3.addAttribute(r4, r5)
            r3 = r0
            java.lang.String r4 = " "
            r5 = r2
            r3.write(r4, r5)
            goto L_0x000e
        L_0x005f:
            r3 = r0
            r4 = r1
            super.print((java.lang.Object) r4)
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.ReplPaneOutPort.print(java.lang.Object):void");
    }
}
