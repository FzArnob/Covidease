package org.shaded.apache.http.impl.conn.tsccm;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import org.shaded.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class WaitingThread {
    private boolean aborted;
    private final Condition cond;
    private final RouteSpecificPool pool;
    private Thread waiter;

    public WaitingThread(Condition condition, RouteSpecificPool routeSpecificPool) {
        Throwable th;
        Condition cond2 = condition;
        RouteSpecificPool pool2 = routeSpecificPool;
        if (cond2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Condition must not be null.");
            throw th2;
        }
        this.cond = cond2;
        this.pool = pool2;
    }

    public final Condition getCondition() {
        return this.cond;
    }

    public final RouteSpecificPool getPool() {
        return this.pool;
    }

    public final Thread getThread() {
        return this.waiter;
    }

    public boolean await(Date date) throws InterruptedException {
        boolean success;
        Throwable th;
        Throwable th2;
        Throwable th3;
        StringBuilder sb;
        Date deadline = date;
        if (this.waiter != null) {
            Throwable th4 = th3;
            new StringBuilder();
            new IllegalStateException(sb.append("A thread is already waiting on this object.\ncaller: ").append(Thread.currentThread()).append("\nwaiter: ").append(this.waiter).toString());
            throw th4;
        } else if (this.aborted) {
            Throwable th5 = th2;
            new InterruptedException("Operation interrupted");
            throw th5;
        } else {
            this.waiter = Thread.currentThread();
            if (deadline != null) {
                try {
                    success = this.cond.awaitUntil(deadline);
                } catch (Throwable th6) {
                    Throwable th7 = th6;
                    this.waiter = null;
                    throw th7;
                }
            } else {
                this.cond.await();
                success = true;
            }
            if (this.aborted) {
                Throwable th8 = th;
                new InterruptedException("Operation interrupted");
                throw th8;
            }
            this.waiter = null;
            return success;
        }
    }

    public void wakeup() {
        Throwable th;
        if (this.waiter == null) {
            Throwable th2 = th;
            new IllegalStateException("Nobody waiting on this object.");
            throw th2;
        }
        this.cond.signalAll();
    }

    public void interrupt() {
        this.aborted = true;
        this.cond.signalAll();
    }
}
