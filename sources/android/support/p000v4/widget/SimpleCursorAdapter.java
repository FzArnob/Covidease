package android.support.p000v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* renamed from: android.support.v4.widget.SimpleCursorAdapter */
public class SimpleCursorAdapter extends ResourceCursorAdapter {
    private CursorToStringConverter mCursorToStringConverter;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected int[] mFrom;
    String[] mOriginalFrom;
    private int mStringConversionColumn = -1;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected int[] mTo;
    private ViewBinder mViewBinder;

    /* renamed from: android.support.v4.widget.SimpleCursorAdapter$CursorToStringConverter */
    public interface CursorToStringConverter {
        CharSequence convertToString(Cursor cursor);
    }

    /* renamed from: android.support.v4.widget.SimpleCursorAdapter$ViewBinder */
    public interface ViewBinder {
        boolean setViewValue(View view, Cursor cursor, int i);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SimpleCursorAdapter(android.content.Context r11, int r12, android.database.Cursor r13, java.lang.String[] r14, int[] r15) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r0
            r7 = r1
            r8 = r2
            r9 = r3
            r6.<init>(r7, r8, r9)
            r6 = r0
            r7 = -1
            r6.mStringConversionColumn = r7
            r6 = r0
            r7 = r5
            r6.mTo = r7
            r6 = r0
            r7 = r4
            r6.mOriginalFrom = r7
            r6 = r0
            r7 = r3
            r8 = r4
            r6.findColumns(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.SimpleCursorAdapter.<init>(android.content.Context, int, android.database.Cursor, java.lang.String[], int[]):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SimpleCursorAdapter(android.content.Context r13, int r14, android.database.Cursor r15, java.lang.String[] r16, int[] r17, int r18) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r18
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r6
            r7.<init>((android.content.Context) r8, (int) r9, (android.database.Cursor) r10, (int) r11)
            r7 = r0
            r8 = -1
            r7.mStringConversionColumn = r8
            r7 = r0
            r8 = r5
            r7.mTo = r8
            r7 = r0
            r8 = r4
            r7.mOriginalFrom = r8
            r7 = r0
            r8 = r3
            r9 = r4
            r7.findColumns(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.SimpleCursorAdapter.<init>(android.content.Context, int, android.database.Cursor, java.lang.String[], int[], int):void");
    }

    public void bindView(View view, Context context, Cursor cursor) {
        Throwable th;
        StringBuilder sb;
        View view2 = view;
        Context context2 = context;
        Cursor cursor2 = cursor;
        ViewBinder binder = this.mViewBinder;
        int count = this.mTo.length;
        int[] from = this.mFrom;
        int[] to = this.mTo;
        for (int i = 0; i < count; i++) {
            View v = view2.findViewById(to[i]);
            if (v != null) {
                boolean bound = false;
                if (binder != null) {
                    bound = binder.setViewValue(v, cursor2, from[i]);
                }
                if (!bound) {
                    String text = cursor2.getString(from[i]);
                    if (text == null) {
                        text = "";
                    }
                    if (v instanceof TextView) {
                        setViewText((TextView) v, text);
                    } else if (v instanceof ImageView) {
                        setViewImage((ImageView) v, text);
                    } else {
                        Throwable th2 = th;
                        new StringBuilder();
                        new IllegalStateException(sb.append(v.getClass().getName()).append(" is not a ").append(" view that can be bounds by this SimpleCursorAdapter").toString());
                        throw th2;
                    }
                } else {
                    continue;
                }
            }
        }
    }

    public ViewBinder getViewBinder() {
        return this.mViewBinder;
    }

    public void setViewBinder(ViewBinder viewBinder) {
        ViewBinder viewBinder2 = viewBinder;
        this.mViewBinder = viewBinder2;
    }

    public void setViewImage(ImageView imageView, String str) {
        ImageView v = imageView;
        String value = str;
        try {
            v.setImageResource(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = e;
            v.setImageURI(Uri.parse(value));
        }
    }

    public void setViewText(TextView v, String text) {
        v.setText(text);
    }

    public int getStringConversionColumn() {
        return this.mStringConversionColumn;
    }

    public void setStringConversionColumn(int stringConversionColumn) {
        int i = stringConversionColumn;
        this.mStringConversionColumn = i;
    }

    public CursorToStringConverter getCursorToStringConverter() {
        return this.mCursorToStringConverter;
    }

    public void setCursorToStringConverter(CursorToStringConverter cursorToStringConverter) {
        CursorToStringConverter cursorToStringConverter2 = cursorToStringConverter;
        this.mCursorToStringConverter = cursorToStringConverter2;
    }

    public CharSequence convertToString(Cursor cursor) {
        Cursor cursor2 = cursor;
        if (this.mCursorToStringConverter != null) {
            return this.mCursorToStringConverter.convertToString(cursor2);
        }
        if (this.mStringConversionColumn > -1) {
            return cursor2.getString(this.mStringConversionColumn);
        }
        return super.convertToString(cursor2);
    }

    private void findColumns(Cursor cursor, String[] strArr) {
        Cursor c = cursor;
        String[] from = strArr;
        if (c != null) {
            int count = from.length;
            if (this.mFrom == null || this.mFrom.length != count) {
                this.mFrom = new int[count];
            }
            for (int i = 0; i < count; i++) {
                this.mFrom[i] = c.getColumnIndexOrThrow(from[i]);
            }
            return;
        }
        this.mFrom = null;
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor c = cursor;
        findColumns(c, this.mOriginalFrom);
        return super.swapCursor(c);
    }

    public void changeCursorAndColumns(Cursor cursor, String[] from, int[] to) {
        Cursor c = cursor;
        this.mOriginalFrom = from;
        this.mTo = to;
        findColumns(c, this.mOriginalFrom);
        super.changeCursor(c);
    }
}
