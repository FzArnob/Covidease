package org.shaded.apache.http.impl.conn.tsccm;

import org.shaded.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class WaitingThreadAborter {
    private boolean aborted;
    private WaitingThread waitingThread;

    public WaitingThreadAborter() {
    }

    public void abort() {
        this.aborted = true;
        if (this.waitingThread != null) {
            this.waitingThread.interrupt();
        }
    }

    public void setWaitingThread(WaitingThread waitingThread2) {
        WaitingThread waitingThread3 = waitingThread2;
        this.waitingThread = waitingThread3;
        if (this.aborted) {
            waitingThread3.interrupt();
        }
    }
}
