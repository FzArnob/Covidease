package gnu.kawa.swingviews;

import javax.swing.text.BadLocationException;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

/* compiled from: SwingContent */
class GapUndoableEdit extends AbstractUndoableEdit {
    SwingContent content;
    String data;
    boolean isInsertion;
    int nitems;
    int startOffset;

    GapUndoableEdit(int offset) {
        this.startOffset = offset;
    }

    private void doit(boolean isInsertion2) throws BadLocationException {
        if (isInsertion2) {
            UndoableEdit insertString = this.content.insertString(this.startOffset, this.data);
        } else {
            UndoableEdit remove = this.content.remove(this.startOffset, this.nitems);
        }
    }

    public void undo() throws CannotUndoException {
        Throwable th;
        GapUndoableEdit.super.undo();
        try {
            doit(!this.isInsertion);
        } catch (BadLocationException e) {
            BadLocationException badLocationException = e;
            Throwable th2 = th;
            new CannotUndoException();
            throw th2;
        }
    }

    public void redo() throws CannotUndoException {
        Throwable th;
        GapUndoableEdit.super.redo();
        try {
            doit(this.isInsertion);
        } catch (BadLocationException e) {
            BadLocationException badLocationException = e;
            Throwable th2 = th;
            new CannotRedoException();
            throw th2;
        }
    }
}
