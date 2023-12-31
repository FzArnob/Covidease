package android.support.transition;

import android.os.IBinder;

class WindowIdApi14 implements WindowIdImpl {
    private final IBinder mToken;

    WindowIdApi14(IBinder token) {
        this.mToken = token;
    }

    public boolean equals(Object obj) {
        Object o = obj;
        return (o instanceof WindowIdApi14) && ((WindowIdApi14) o).mToken.equals(this.mToken);
    }

    public int hashCode() {
        return this.mToken.hashCode();
    }
}
