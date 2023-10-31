package com.google.appinventor.components.runtime.util;

import android.os.Handler;
import com.google.appinventor.components.runtime.AlarmHandler;

public final class TimerInternal implements Runnable {
    private boolean enabled;
    private Handler handler;
    private AlarmHandler hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int interval;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TimerInternal(com.google.appinventor.components.runtime.AlarmHandler r12, boolean r13, int r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            android.os.Handler r8 = new android.os.Handler
            r10 = r8
            r8 = r10
            r9 = r10
            r9.<init>()
            r4.<init>(r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.TimerInternal.<init>(com.google.appinventor.components.runtime.AlarmHandler, boolean, int):void");
    }

    public TimerInternal(AlarmHandler alarmHandler, boolean z, int i, Handler handler2) {
        boolean z2 = z;
        int i2 = i;
        Handler handler3 = handler2;
        this.handler = handler3;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = alarmHandler;
        this.enabled = z2;
        this.interval = i2;
        if (z2) {
            boolean postDelayed = handler3.postDelayed(this, (long) i2);
        }
    }

    public final int Interval() {
        return this.interval;
    }

    public final void Interval(int i) {
        int i2 = i;
        this.interval = i2;
        if (this.enabled) {
            this.handler.removeCallbacks(this);
            boolean postDelayed = this.handler.postDelayed(this, (long) i2);
        }
    }

    public final boolean Enabled() {
        return this.enabled;
    }

    public final void Enabled(boolean z) {
        boolean z2 = z;
        if (this.enabled) {
            this.handler.removeCallbacks(this);
        }
        this.enabled = z2;
        if (z2) {
            boolean postDelayed = this.handler.postDelayed(this, (long) this.interval);
        }
    }

    public final void run() {
        if (this.enabled) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.alarm();
            if (this.enabled) {
                boolean postDelayed = this.handler.postDelayed(this, (long) this.interval);
            }
        }
    }
}
