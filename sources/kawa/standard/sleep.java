package kawa.standard;

import gnu.math.Quantity;
import gnu.math.Unit;
import kawa.lang.GenericError;

public class sleep {
    public sleep() {
    }

    public static void sleep(Quantity quantity) {
        Throwable th;
        Throwable th2;
        Quantity q = quantity;
        Unit u = q.unit();
        if (u == Unit.Empty || u.dimensions() == Unit.second.dimensions()) {
            double seconds = q.doubleValue();
            long millis = (long) (seconds * 1000.0d);
            try {
                Thread.sleep(millis, (int) ((seconds * 1.0E9d) - (((double) millis) * 1000000.0d)));
            } catch (InterruptedException e) {
                InterruptedException interruptedException = e;
                Throwable th3 = th;
                new GenericError("sleep was interrupted");
                throw th3;
            }
        } else {
            Throwable th4 = th2;
            new GenericError("bad unit for sleep");
            throw th4;
        }
    }
}
