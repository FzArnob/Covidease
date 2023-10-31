package gnu.kawa.swingviews;

import gnu.mapping.Procedure;
import gnu.mapping.WrappedException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* compiled from: SwingDisplay */
class ProcActionListener implements ActionListener {
    Procedure proc;

    public ProcActionListener(Procedure proc2) {
        this.proc = proc2;
    }

    public void actionPerformed(ActionEvent e) {
        Throwable th;
        try {
            Object apply1 = this.proc.apply1(e);
        } catch (Throwable th2) {
            Throwable ex = th2;
            Throwable th3 = th;
            new WrappedException(ex);
            throw th3;
        }
    }
}
