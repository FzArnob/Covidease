package gnu.kawa.swingviews;

import gnu.lists.CharBuffer;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Segment;
import javax.swing.undo.UndoableEdit;

public class SwingContent implements AbstractDocument.Content {
    public final CharBuffer buffer;

    public SwingContent(CharBuffer buffer2) {
        this.buffer = buffer2;
    }

    public SwingContent(int i) {
        CharBuffer charBuffer;
        int initialSize = i;
        new CharBuffer(initialSize);
        CharBuffer b = charBuffer;
        b.gapEnd = initialSize - 1;
        b.getArray()[b.gapEnd] = 10;
        this.buffer = b;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SwingContent() {
        this(100);
    }

    public int length() {
        return this.buffer.length();
    }

    public void getChars(int i, int i2, Segment segment) throws BadLocationException {
        Throwable th;
        int where = i;
        int len = i2;
        Segment txt = segment;
        CharBuffer b = this.buffer;
        int start = b.getSegment(where, len);
        if (start < 0) {
            Throwable th2 = th;
            new BadLocationException("invalid offset", where);
            throw th2;
        }
        txt.offset = start;
        txt.array = b.getArray();
        txt.count = len;
    }

    public String getString(int i, int i2) throws BadLocationException {
        String str;
        Throwable th;
        int where = i;
        int len = i2;
        CharBuffer b = this.buffer;
        int start = b.getSegment(where, len);
        if (start < 0) {
            Throwable th2 = th;
            new BadLocationException("invalid offset", where);
            throw th2;
        }
        new String(b.getArray(), start, len);
        return str;
    }

    public UndoableEdit remove(int i, int i2) throws BadLocationException {
        Throwable th;
        GapUndoableEdit gapUndoableEdit;
        String str;
        int where = i;
        int nitems = i2;
        CharBuffer b = this.buffer;
        if (nitems < 0 || where < 0 || where + nitems > b.length()) {
            Throwable th2 = th;
            new BadLocationException("invalid remove", where);
            throw th2;
        }
        b.delete(where, nitems);
        new GapUndoableEdit(where);
        GapUndoableEdit undo = gapUndoableEdit;
        undo.content = this;
        new String(b.getArray(), b.gapEnd - nitems, nitems);
        undo.data = str;
        undo.nitems = nitems;
        undo.isInsertion = false;
        return undo;
    }

    public UndoableEdit insertString(int i, String str, boolean z) throws BadLocationException {
        Throwable th;
        GapUndoableEdit gapUndoableEdit;
        int where = i;
        String str2 = str;
        boolean beforeMarkers = z;
        CharBuffer b = this.buffer;
        if (where < 0 || where > b.length()) {
            Throwable th2 = th;
            new BadLocationException("bad insert", where);
            throw th2;
        }
        b.insert(where, str2, beforeMarkers);
        new GapUndoableEdit(where);
        GapUndoableEdit undo = gapUndoableEdit;
        undo.content = this;
        undo.data = str2;
        undo.nitems = str2.length();
        undo.isInsertion = true;
        return undo;
    }

    public UndoableEdit insertString(int where, String str) throws BadLocationException {
        return insertString(where, str, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.lang.Throwable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.swing.text.Position createPosition(int r9) throws javax.swing.text.BadLocationException {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r3 = r0
            gnu.lists.CharBuffer r3 = r3.buffer
            r2 = r3
            r3 = r1
            if (r3 < 0) goto L_0x0011
            r3 = r1
            r4 = r2
            int r4 = r4.length()
            if (r3 <= r4) goto L_0x001e
        L_0x0011:
            javax.swing.text.BadLocationException r3 = new javax.swing.text.BadLocationException
            r7 = r3
            r3 = r7
            r4 = r7
            java.lang.String r5 = "bad offset to createPosition"
            r6 = r1
            r4.<init>(r5, r6)
            throw r3
        L_0x001e:
            gnu.kawa.swingviews.GapPosition r3 = new gnu.kawa.swingviews.GapPosition
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r2
            r6 = r1
            r4.<init>(r5, r6)
            r0 = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.swingviews.SwingContent.createPosition(int):javax.swing.text.Position");
    }
}
