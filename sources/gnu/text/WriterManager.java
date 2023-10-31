package gnu.text;

import java.io.Writer;
import java.lang.reflect.Method;

public class WriterManager implements Runnable {
    public static final WriterManager instance;
    WriterRef first;

    public WriterManager() {
    }

    static {
        WriterManager writerManager;
        new WriterManager();
        instance = writerManager;
    }

    public synchronized WriterRef register(Writer writer) {
        WriterRef writerRef;
        WriterRef writerRef2;
        Writer port = writer;
        synchronized (this) {
            new WriterRef(port);
            WriterRef ref = writerRef;
            WriterRef first2 = this.first;
            if (first2 != null) {
                ref.next = first2.next;
                first2.prev = ref;
            }
            this.first = ref;
            writerRef2 = ref;
        }
        return writerRef2;
    }

    public synchronized void unregister(Object obj) {
        Object key = obj;
        synchronized (this) {
            if (key != null) {
                WriterRef ref = (WriterRef) key;
                WriterRef next = ref.next;
                WriterRef prev = ref.prev;
                if (next != null) {
                    next.prev = prev;
                }
                if (prev != null) {
                    prev.next = next;
                }
                if (ref == this.first) {
                    this.first = next;
                }
            }
        }
    }

    public synchronized void run() {
        synchronized (this) {
            for (WriterRef ref = this.first; ref != null; ref = ref.next) {
                Object port = ref.get();
                if (port != null) {
                    try {
                        ((Writer) port).close();
                    } catch (Exception e) {
                        Exception exc = e;
                    }
                }
            }
            this.first = null;
        }
    }

    public boolean registerShutdownHook() {
        Object obj;
        try {
            Runtime runtime = Runtime.getRuntime();
            Method method = runtime.getClass().getDeclaredMethod("addShutdownHook", new Class[]{Thread.class});
            new Thread(this);
            Object invoke = method.invoke(runtime, new Object[]{obj});
            return true;
        } catch (Throwable th) {
            Throwable th2 = th;
            return false;
        }
    }
}
