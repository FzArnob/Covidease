package gnu.text;

import java.io.Writer;
import java.lang.ref.WeakReference;

/* compiled from: WriterManager */
class WriterRef extends WeakReference {
    WriterRef next;
    WriterRef prev;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WriterRef(Writer wr) {
        super(wr);
    }
}
