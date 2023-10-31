package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zzez;

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CursorWrapper(android.database.Cursor r13) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r4 = r0
            r5 = r1
            r4.<init>(r5)
            r4 = r0
            r5 = r1
            r2 = r5
            r5 = 0
            r3 = r5
        L_0x000c:
            r5 = r3
            r6 = 10
            if (r5 >= r6) goto L_0x0021
            r5 = r2
            boolean r5 = r5 instanceof android.database.CursorWrapper
            if (r5 == 0) goto L_0x0021
            r5 = r2
            android.database.CursorWrapper r5 = (android.database.CursorWrapper) r5
            android.database.Cursor r5 = r5.getWrappedCursor()
            r2 = r5
            int r3 = r3 + 1
            goto L_0x000c
        L_0x0021:
            r5 = r2
            boolean r5 = r5 instanceof android.database.AbstractWindowedCursor
            if (r5 != 0) goto L_0x005b
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            r10 = r5
            r5 = r10
            r6 = r10
            java.lang.String r7 = "Unknown type: "
            r8 = r2
            java.lang.Class r8 = r8.getClass()
            java.lang.String r8 = r8.getName()
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r10 = r8
            r8 = r10
            r9 = r10
            int r9 = r9.length()
            if (r9 == 0) goto L_0x004c
            java.lang.String r7 = r7.concat(r8)
        L_0x0048:
            r6.<init>(r7)
            throw r5
        L_0x004c:
            java.lang.String r8 = new java.lang.String
            r10 = r7
            r11 = r8
            r7 = r11
            r8 = r10
            r9 = r11
            r10 = r8
            r11 = r9
            r8 = r11
            r9 = r10
            r8.<init>(r9)
            goto L_0x0048
        L_0x005b:
            r5 = r2
            android.database.AbstractWindowedCursor r5 = (android.database.AbstractWindowedCursor) r5
            r4.zzez = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.sqlite.CursorWrapper.<init>(android.database.Cursor):void");
    }

    @KeepForSdk
    public CursorWindow getWindow() {
        return this.zzez.getWindow();
    }

    @KeepForSdk
    public void setWindow(CursorWindow cursorWindow) {
        this.zzez.setWindow(cursorWindow);
    }

    @KeepForSdk
    public void fillWindow(int i, CursorWindow cursorWindow) {
        this.zzez.fillWindow(i, cursorWindow);
    }

    public boolean onMove(int i, int i2) {
        return this.zzez.onMove(i, i2);
    }

    public /* synthetic */ Cursor getWrappedCursor() {
        return this.zzez;
    }
}
