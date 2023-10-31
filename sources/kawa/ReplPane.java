package kawa;

import gnu.lists.CharBuffer;
import gnu.mapping.OutPort;
import gnu.text.QueueReader;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;

public class ReplPane extends JTextPane implements KeyListener {
    public static final Object PaintableAttribute;
    public static final String PaintableElementName = "Paintable";
    public static final Object ViewableAttribute;
    public static final String ViewableElementName = "Viewable";
    ReplDocument document;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReplPane(kawa.ReplDocument r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = r1
            r3.document = r4
            r3 = r1
            r4 = r0
            r3.pane = r4
            r3 = r1
            r6 = r3
            r3 = r6
            r4 = r6
            int r4 = r4.paneCount
            r5 = 1
            int r4 = r4 + 1
            r3.paneCount = r4
            r3 = r0
            r4 = r0
            r3.addKeyListener(r4)
            r3 = r0
            r4 = r1
            r3.addFocusListener(r4)
            r3 = r0
            javax.swing.text.EditorKit r3 = r3.getEditorKit()
            r2 = r3
            r3 = r0
            r4 = r1
            int r4 = r4.outputMark
            r3.setCaretPosition(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.ReplPane.<init>(kawa.ReplDocument):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.swing.text.EditorKit createDefaultEditorKit() {
        /*
            r5 = this;
            r0 = r5
            kawa.ReplEditorKit r1 = new kawa.ReplEditorKit
            r4 = r1
            r1 = r4
            r2 = r4
            r3 = r0
            r2.<init>(r3)
            r0 = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.ReplPane.createDefaultEditorKit():javax.swing.text.EditorKit");
    }

    public void removeNotify() {
        ReplPane.super.removeNotify();
        ReplDocument replDocument = this.document;
        int i = replDocument.paneCount - 1;
        int i2 = i;
        replDocument.paneCount = i;
        if (i2 == 0) {
            this.document.close();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void enter() {
        String str;
        StringBuilder sb;
        int pos = getCaretPosition();
        CharBuffer b = this.document.content.buffer;
        int len = b.length() - 1;
        this.document.endMark = -1;
        if (pos >= this.document.outputMark) {
            int lineAfterCaret = b.indexOf(10, pos);
            if (lineAfterCaret == len) {
                if (len <= this.document.outputMark || b.charAt(len - 1) != 10) {
                    this.document.insertString(len, "\n", (AttributeSet) null);
                } else {
                    lineAfterCaret--;
                }
            }
            this.document.endMark = lineAfterCaret;
            QueueReader queueReader = this.document.in_r;
            QueueReader queueReader2 = queueReader;
            synchronized (queueReader) {
                try {
                    this.document.in_r.notifyAll();
                    if (pos <= lineAfterCaret) {
                        setCaretPosition(lineAfterCaret + 1);
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        QueueReader queueReader3 = queueReader2;
                        throw th2;
                    }
                }
            }
        } else {
            int lineBefore = pos == 0 ? 0 : 1 + b.lastIndexOf(10, pos - 1);
            Element el = this.document.getCharacterElement(lineBefore);
            int lineAfter = b.indexOf(10, pos);
            if (el.getAttributes().isEqual(ReplDocument.promptStyle)) {
                lineBefore = el.getEndOffset();
            }
            if (lineAfter < 0) {
                new StringBuilder();
                str = sb.append(b.substring(lineBefore, len)).append(10).toString();
            } else {
                str = b.substring(lineBefore, lineAfter + 1);
            }
            setCaretPosition(this.document.outputMark);
            this.document.write(str, ReplDocument.inputStyle);
            if (this.document.in_r != null) {
                QueueReader append = this.document.in_r.append((CharSequence) str, 0, str.length());
            }
        }
    }

    public MutableAttributeSet getInputAttributes() {
        return ReplDocument.inputStyle;
    }

    public void keyPressed(KeyEvent keyEvent) {
        KeyEvent e = keyEvent;
        if (e.getKeyCode() == 10) {
            enter();
            e.consume();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public OutPort getStdout() {
        return this.document.out_stream;
    }

    public OutPort getStderr() {
        return this.document.err_stream;
    }

    static {
        Object obj;
        Object obj2;
        new String(ViewableElementName);
        ViewableAttribute = obj;
        new String(PaintableElementName);
        PaintableAttribute = obj2;
    }
}
