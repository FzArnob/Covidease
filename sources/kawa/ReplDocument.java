package kawa;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.kawa.swingviews.SwingContent;
import gnu.lists.CharBuffer;
import gnu.mapping.Environment;
import gnu.mapping.Future;
import gnu.text.QueueReader;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class ReplDocument extends DefaultStyledDocument implements DocumentListener, FocusListener {
    static Style blueStyle = styles.addStyle("blue", (Style) null);
    public static Style defaultStyle = styles.addStyle("default", (Style) null);
    public static Style inputStyle = styles.addStyle("input", (Style) null);
    static Style promptStyle = styles.addStyle("prompt", (Style) null);
    public static Style redStyle = styles.addStyle("red", (Style) null);
    public static StyleContext styles;
    Object closeListeners;
    SwingContent content;
    public int endMark;
    Environment environment;
    final ReplPaneOutPort err_stream;
    final GuiInPort in_p;
    final QueueReader in_r;
    Language language;
    int length;
    final ReplPaneOutPort out_stream;
    public int outputMark;
    JTextPane pane;
    int paneCount;
    Future thread;

    public interface DocumentCloseListener {
        void closed(ReplDocument replDocument);
    }

    static {
        StyleContext styleContext;
        new StyleContext();
        styles = styleContext;
        StyleConstants.setForeground(redStyle, Color.red);
        StyleConstants.setForeground(blueStyle, Color.blue);
        StyleConstants.setForeground(promptStyle, Color.green);
        StyleConstants.setBold(inputStyle, true);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReplDocument(gnu.expr.Language r11, gnu.mapping.Environment r12, boolean r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r0
            gnu.kawa.swingviews.SwingContent r5 = new gnu.kawa.swingviews.SwingContent
            r9 = r5
            r5 = r9
            r6 = r9
            r6.<init>()
            r6 = r1
            r7 = r2
            r8 = r3
            r4.<init>(r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.ReplDocument.<init>(gnu.expr.Language, gnu.mapping.Environment, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ReplDocument(gnu.kawa.swingviews.SwingContent r14, gnu.expr.Language r15, gnu.mapping.Environment r16, boolean r17) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r0
            r6 = r1
            javax.swing.text.StyleContext r7 = styles
            r5.<init>(r6, r7)
            r5 = r0
            r6 = 0
            r5.outputMark = r6
            r5 = r0
            r6 = -1
            r5.endMark = r6
            r5 = r0
            r6 = 0
            r5.length = r6
            r5 = r0
            r6 = r1
            r5.content = r6
            gnu.expr.ModuleBody.exitIncrement()
            r5 = r0
            r6 = r0
            r5.addDocumentListener(r6)
            r5 = r0
            r6 = r2
            r5.language = r6
            r5 = r0
            kawa.ReplDocument$1 r6 = new kawa.ReplDocument$1
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r0
            r7.<init>(r8)
            r5.in_r = r6
            r5 = r0
            kawa.ReplPaneOutPort r6 = new kawa.ReplPaneOutPort
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r0
            java.lang.String r9 = "/dev/stdout"
            javax.swing.text.Style r10 = defaultStyle
            r7.<init>(r8, r9, r10)
            r5.out_stream = r6
            r5 = r0
            kawa.ReplPaneOutPort r6 = new kawa.ReplPaneOutPort
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r0
            java.lang.String r9 = "/dev/stderr"
            javax.swing.text.Style r10 = redStyle
            r7.<init>(r8, r9, r10)
            r5.err_stream = r6
            r5 = r0
            kawa.GuiInPort r6 = new kawa.GuiInPort
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r0
            gnu.text.QueueReader r8 = r8.in_r
            java.lang.String r9 = "/dev/stdin"
            gnu.text.Path r9 = gnu.text.Path.valueOf(r9)
            r10 = r0
            kawa.ReplPaneOutPort r10 = r10.out_stream
            r11 = r0
            r7.<init>(r8, r9, r10, r11)
            r5.in_p = r6
            r5 = r0
            kawa.ReplDocument$2 r6 = new kawa.ReplDocument$2
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r0
            r9 = r2
            r10 = r4
            r7.<init>(r8, r9, r10)
            r7 = r3
            r8 = r0
            kawa.GuiInPort r8 = r8.in_p
            r9 = r0
            kawa.ReplPaneOutPort r9 = r9.out_stream
            r10 = r0
            kawa.ReplPaneOutPort r10 = r10.err_stream
            gnu.mapping.Future r6 = gnu.mapping.Future.make(r6, r7, r8, r9, r10)
            r5.thread = r6
            r5 = r0
            gnu.mapping.Future r5 = r5.thread
            r5.start()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.ReplDocument.<init>(gnu.kawa.swingviews.SwingContent, gnu.expr.Language, gnu.mapping.Environment, boolean):void");
    }

    public synchronized void deleteOldText() {
        Throwable th;
        int lineBefore;
        synchronized (this) {
            try {
                String str = getText(0, this.outputMark);
                if (this.outputMark <= 0) {
                    lineBefore = 0;
                } else {
                    lineBefore = str.lastIndexOf(10, this.outputMark - 1) + 1;
                }
                remove(0, lineBefore);
            } catch (BadLocationException e) {
                BadLocationException ex = e;
                Throwable th2 = th;
                new Error(ex);
                throw th2;
            }
        }
    }

    public void insertString(int pos, String str, AttributeSet style) {
        Throwable th;
        try {
            ReplDocument.super.insertString(pos, str, style);
        } catch (BadLocationException e) {
            BadLocationException ex = e;
            Throwable th2 = th;
            new Error(ex);
            throw th2;
        }
    }

    public void write(String str, AttributeSet style) {
        Runnable runnable;
        final String str2 = str;
        final AttributeSet attributeSet = style;
        new Runnable(this) {
            final /* synthetic */ ReplDocument this$0;

            {
                this.this$0 = r7;
            }

            public void run() {
                boolean moveCaret = this.this$0.pane != null && this.this$0.pane.getCaretPosition() == this.this$0.outputMark;
                this.this$0.insertString(this.this$0.outputMark, str2, attributeSet);
                this.this$0.outputMark += str2.length();
                if (moveCaret) {
                    this.this$0.pane.setCaretPosition(this.this$0.outputMark);
                }
            }
        };
        SwingUtilities.invokeLater(runnable);
    }

    public void checkingPendingInput() {
        Runnable runnable;
        new Runnable(this) {
            final /* synthetic */ ReplDocument this$0;

            {
                this.this$0 = r5;
            }

            /* JADX INFO: finally extract failed */
            public void run() {
                int inputStart = this.this$0.outputMark;
                if (inputStart <= this.this$0.endMark) {
                    CharBuffer b = this.this$0.content.buffer;
                    int lineAfter = b.indexOf(10, inputStart);
                    if (lineAfter == this.this$0.endMark) {
                        this.this$0.endMark = -1;
                    }
                    if (inputStart == this.this$0.outputMark) {
                        this.this$0.outputMark = lineAfter + 1;
                    }
                    if (this.this$0.in_r != null) {
                        QueueReader queueReader = this.this$0.in_r;
                        QueueReader queueReader2 = queueReader;
                        synchronized (queueReader) {
                            try {
                                QueueReader append = this.this$0.in_r.append((CharSequence) b, inputStart, lineAfter + 1);
                                this.this$0.in_r.notifyAll();
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                QueueReader queueReader3 = queueReader2;
                                throw th2;
                            }
                        }
                    }
                }
            }
        };
        SwingUtilities.invokeLater(runnable);
    }

    public void focusGained(FocusEvent e) {
        Object source = e.getSource();
        if (source instanceof ReplPane) {
            this.pane = (ReplPane) source;
        } else {
            this.pane = null;
        }
        this.pane = source instanceof ReplPane ? (ReplPane) source : null;
    }

    public void focusLost(FocusEvent focusEvent) {
        FocusEvent focusEvent2 = focusEvent;
        this.pane = null;
    }

    public void changedUpdate(DocumentEvent e) {
        textValueChanged(e);
    }

    public void insertUpdate(DocumentEvent e) {
        textValueChanged(e);
    }

    public void removeUpdate(DocumentEvent e) {
        textValueChanged(e);
    }

    public synchronized void textValueChanged(DocumentEvent documentEvent) {
        DocumentEvent e = documentEvent;
        synchronized (this) {
            int pos = e.getOffset();
            int delta = getLength() - this.length;
            this.length += delta;
            if (pos < this.outputMark) {
                this.outputMark += delta;
            } else if (pos - delta < this.outputMark) {
                this.outputMark = pos;
            }
            if (this.endMark >= 0) {
                if (pos < this.endMark) {
                    this.endMark += delta;
                } else if (pos - delta < this.endMark) {
                    this.endMark = pos;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void close() {
        this.in_r.appendEOF();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            InterruptedException interruptedException = e;
        }
        this.thread.stop();
        fireDocumentClosed();
        ModuleBody.exitDecrement();
    }

    public void addDocumentCloseListener(DocumentCloseListener documentCloseListener) {
        ArrayList arrayList;
        ArrayList vec;
        DocumentCloseListener listener = documentCloseListener;
        if (this.closeListeners == null) {
            this.closeListeners = listener;
            return;
        }
        if (this.closeListeners instanceof ArrayList) {
            vec = (ArrayList) this.closeListeners;
        } else {
            new ArrayList(10);
            vec = arrayList;
            boolean add = vec.add(this.closeListeners);
            this.closeListeners = vec;
        }
        boolean add2 = vec.add(listener);
    }

    public void removeDocumentCloseListener(DocumentCloseListener documentCloseListener) {
        DocumentCloseListener listener = documentCloseListener;
        if (this.closeListeners instanceof DocumentCloseListener) {
            if (this.closeListeners == listener) {
                this.closeListeners = null;
            }
        } else if (this.closeListeners != null) {
            ArrayList vec = (ArrayList) this.closeListeners;
            int i = vec.size();
            while (true) {
                i--;
                if (i < 0) {
                    break;
                } else if (vec.get(i) == listener) {
                    Object remove = vec.remove(i);
                }
            }
            if (vec.size() == 0) {
                this.closeListeners = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void fireDocumentClosed() {
        if (this.closeListeners instanceof DocumentCloseListener) {
            ((DocumentCloseListener) this.closeListeners).closed(this);
        } else if (this.closeListeners != null) {
            ArrayList vec = (ArrayList) this.closeListeners;
            int i = vec.size();
            while (true) {
                i--;
                if (i >= 0) {
                    ((DocumentCloseListener) vec.get(i)).closed(this);
                } else {
                    return;
                }
            }
        }
    }
}
