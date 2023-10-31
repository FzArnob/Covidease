package org.shaded.apache.http.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;

@Deprecated
public class RefQueueWorker implements Runnable {
    protected final RefQueueHandler refHandler;
    protected final ReferenceQueue<?> refQueue;
    protected volatile Thread workerThread;

    public RefQueueWorker(ReferenceQueue<?> referenceQueue, RefQueueHandler refQueueHandler) {
        Throwable th;
        Throwable th2;
        ReferenceQueue<?> queue = referenceQueue;
        RefQueueHandler handler = refQueueHandler;
        if (queue == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Queue must not be null.");
            throw th3;
        } else if (handler == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Handler must not be null.");
            throw th4;
        } else {
            this.refQueue = queue;
            this.refHandler = handler;
        }
    }

    public void run() {
        if (this.workerThread == null) {
            this.workerThread = Thread.currentThread();
        }
        while (this.workerThread == Thread.currentThread()) {
            try {
                this.refHandler.handleReference(this.refQueue.remove());
            } catch (InterruptedException e) {
                InterruptedException interruptedException = e;
            }
        }
    }

    public void shutdown() {
        Thread wt = this.workerThread;
        if (wt != null) {
            this.workerThread = null;
            wt.interrupt();
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("RefQueueWorker::").append(this.workerThread).toString();
    }
}
