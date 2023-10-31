package android.support.p000v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v4.widget.ResourceCursorAdapter */
public abstract class ResourceCursorAdapter extends CursorAdapter {
    private int mDropDownLayout;
    private LayoutInflater mInflater;
    private int mLayout;

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ResourceCursorAdapter(android.content.Context r11, int r12, android.database.Cursor r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r0
            r5 = r1
            r6 = r3
            r4.<init>(r5, r6)
            r4 = r0
            r5 = r0
            r6 = r2
            r8 = r5
            r9 = r6
            r5 = r9
            r6 = r8
            r7 = r9
            r6.mDropDownLayout = r7
            r4.mLayout = r5
            r4 = r0
            r5 = r1
            java.lang.String r6 = "layout_inflater"
            java.lang.Object r5 = r5.getSystemService(r6)
            android.view.LayoutInflater r5 = (android.view.LayoutInflater) r5
            r4.mInflater = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.ResourceCursorAdapter.<init>(android.content.Context, int, android.database.Cursor):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ResourceCursorAdapter(android.content.Context r12, int r13, android.database.Cursor r14, boolean r15) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r0
            r6 = r1
            r7 = r3
            r8 = r4
            r5.<init>((android.content.Context) r6, (android.database.Cursor) r7, (boolean) r8)
            r5 = r0
            r6 = r0
            r7 = r2
            r9 = r6
            r10 = r7
            r6 = r10
            r7 = r9
            r8 = r10
            r7.mDropDownLayout = r8
            r5.mLayout = r6
            r5 = r0
            r6 = r1
            java.lang.String r7 = "layout_inflater"
            java.lang.Object r6 = r6.getSystemService(r7)
            android.view.LayoutInflater r6 = (android.view.LayoutInflater) r6
            r5.mInflater = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.ResourceCursorAdapter.<init>(android.content.Context, int, android.database.Cursor, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ResourceCursorAdapter(android.content.Context r12, int r13, android.database.Cursor r14, int r15) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r0
            r6 = r1
            r7 = r3
            r8 = r4
            r5.<init>((android.content.Context) r6, (android.database.Cursor) r7, (int) r8)
            r5 = r0
            r6 = r0
            r7 = r2
            r9 = r6
            r10 = r7
            r6 = r10
            r7 = r9
            r8 = r10
            r7.mDropDownLayout = r8
            r5.mLayout = r6
            r5 = r0
            r6 = r1
            java.lang.String r7 = "layout_inflater"
            java.lang.Object r6 = r6.getSystemService(r7)
            android.view.LayoutInflater r6 = (android.view.LayoutInflater) r6
            r5.mInflater = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.ResourceCursorAdapter.<init>(android.content.Context, int, android.database.Cursor, int):void");
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        Context context2 = context;
        Cursor cursor2 = cursor;
        return this.mInflater.inflate(this.mLayout, parent, false);
    }

    public View newDropDownView(Context context, Cursor cursor, ViewGroup parent) {
        Context context2 = context;
        Cursor cursor2 = cursor;
        return this.mInflater.inflate(this.mDropDownLayout, parent, false);
    }

    public void setViewResource(int layout) {
        int i = layout;
        this.mLayout = i;
    }

    public void setDropDownViewResource(int dropDownLayout) {
        int i = dropDownLayout;
        this.mDropDownLayout = i;
    }
}
