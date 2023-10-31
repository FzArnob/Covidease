package android.support.p000v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.annotation.RestrictTo;
import android.support.p000v4.widget.CursorFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

/* renamed from: android.support.v4.widget.CursorAdapter */
public abstract class CursorAdapter extends BaseAdapter implements Filterable, CursorFilter.CursorFilterClient {
    @Deprecated
    public static final int FLAG_AUTO_REQUERY = 1;
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected boolean mAutoRequery;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected ChangeObserver mChangeObserver;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected Context mContext;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected Cursor mCursor;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected CursorFilter mCursorFilter;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected DataSetObserver mDataSetObserver;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected boolean mDataValid;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected FilterQueryProvider mFilterQueryProvider;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected int mRowIDColumn;

    public abstract void bindView(View view, Context context, Cursor cursor);

    public abstract View newView(Context context, Cursor cursor, ViewGroup viewGroup);

    @Deprecated
    public CursorAdapter(Context context, Cursor c) {
        init(context, c, 1);
    }

    public CursorAdapter(Context context, Cursor c, boolean autoRequery) {
        init(context, c, autoRequery ? 1 : 2);
    }

    public CursorAdapter(Context context, Cursor c, int flags) {
        init(context, c, flags);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void init(Context context, Cursor c, boolean autoRequery) {
        init(context, c, autoRequery ? 1 : 2);
    }

    /* access modifiers changed from: package-private */
    public void init(Context context, Cursor cursor, int i) {
        ChangeObserver changeObserver;
        DataSetObserver dataSetObserver;
        Context context2 = context;
        Cursor c = cursor;
        int flags = i;
        if ((flags & 1) == 1) {
            flags |= 2;
            this.mAutoRequery = true;
        } else {
            this.mAutoRequery = false;
        }
        boolean cursorPresent = c != null;
        this.mCursor = c;
        this.mDataValid = cursorPresent;
        this.mContext = context2;
        this.mRowIDColumn = cursorPresent ? c.getColumnIndexOrThrow("_id") : -1;
        if ((flags & 2) == 2) {
            new ChangeObserver(this);
            this.mChangeObserver = changeObserver;
            new MyDataSetObserver(this);
            this.mDataSetObserver = dataSetObserver;
        } else {
            this.mChangeObserver = null;
            this.mDataSetObserver = null;
        }
        if (cursorPresent) {
            if (this.mChangeObserver != null) {
                c.registerContentObserver(this.mChangeObserver);
            }
            if (this.mDataSetObserver != null) {
                c.registerDataSetObserver(this.mDataSetObserver);
            }
        }
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    public int getCount() {
        if (!this.mDataValid || this.mCursor == null) {
            return 0;
        }
        return this.mCursor.getCount();
    }

    public Object getItem(int i) {
        int position = i;
        if (!this.mDataValid || this.mCursor == null) {
            return null;
        }
        boolean moveToPosition = this.mCursor.moveToPosition(position);
        return this.mCursor;
    }

    public long getItemId(int i) {
        int position = i;
        if (!this.mDataValid || this.mCursor == null) {
            return 0;
        }
        if (this.mCursor.moveToPosition(position)) {
            return this.mCursor.getLong(this.mRowIDColumn);
        }
        return 0;
    }

    public boolean hasStableIds() {
        return true;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        int position = i;
        View convertView = view;
        ViewGroup parent = viewGroup;
        if (!this.mDataValid) {
            Throwable th3 = th2;
            new IllegalStateException("this should only be called when the cursor is valid");
            throw th3;
        } else if (!this.mCursor.moveToPosition(position)) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("couldn't move cursor to position ").append(position).toString());
            throw th4;
        } else {
            if (convertView == null) {
                v = newView(this.mContext, this.mCursor, parent);
            } else {
                v = convertView;
            }
            bindView(v, this.mContext, this.mCursor);
            return v;
        }
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        View v;
        int position = i;
        View convertView = view;
        ViewGroup parent = viewGroup;
        if (!this.mDataValid) {
            return null;
        }
        boolean moveToPosition = this.mCursor.moveToPosition(position);
        if (convertView == null) {
            v = newDropDownView(this.mContext, this.mCursor, parent);
        } else {
            v = convertView;
        }
        bindView(v, this.mContext, this.mCursor);
        return v;
    }

    public View newDropDownView(Context context, Cursor cursor, ViewGroup parent) {
        return newView(context, cursor, parent);
    }

    public void changeCursor(Cursor cursor) {
        Cursor old = swapCursor(cursor);
        if (old != null) {
            old.close();
        }
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor newCursor = cursor;
        if (newCursor == this.mCursor) {
            return null;
        }
        Cursor oldCursor = this.mCursor;
        if (oldCursor != null) {
            if (this.mChangeObserver != null) {
                oldCursor.unregisterContentObserver(this.mChangeObserver);
            }
            if (this.mDataSetObserver != null) {
                oldCursor.unregisterDataSetObserver(this.mDataSetObserver);
            }
        }
        this.mCursor = newCursor;
        if (newCursor != null) {
            if (this.mChangeObserver != null) {
                newCursor.registerContentObserver(this.mChangeObserver);
            }
            if (this.mDataSetObserver != null) {
                newCursor.registerDataSetObserver(this.mDataSetObserver);
            }
            this.mRowIDColumn = newCursor.getColumnIndexOrThrow("_id");
            this.mDataValid = true;
            notifyDataSetChanged();
        } else {
            this.mRowIDColumn = -1;
            this.mDataValid = false;
            notifyDataSetInvalidated();
        }
        return oldCursor;
    }

    public CharSequence convertToString(Cursor cursor) {
        Cursor cursor2 = cursor;
        return cursor2 == null ? "" : cursor2.toString();
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        CharSequence constraint = charSequence;
        if (this.mFilterQueryProvider != null) {
            return this.mFilterQueryProvider.runQuery(constraint);
        }
        return this.mCursor;
    }

    public Filter getFilter() {
        CursorFilter cursorFilter;
        if (this.mCursorFilter == null) {
            new CursorFilter(this);
            this.mCursorFilter = cursorFilter;
        }
        return this.mCursorFilter;
    }

    public FilterQueryProvider getFilterQueryProvider() {
        return this.mFilterQueryProvider;
    }

    public void setFilterQueryProvider(FilterQueryProvider filterQueryProvider) {
        FilterQueryProvider filterQueryProvider2 = filterQueryProvider;
        this.mFilterQueryProvider = filterQueryProvider2;
    }

    /* access modifiers changed from: protected */
    public void onContentChanged() {
        if (this.mAutoRequery && this.mCursor != null && !this.mCursor.isClosed()) {
            this.mDataValid = this.mCursor.requery();
        }
    }

    /* renamed from: android.support.v4.widget.CursorAdapter$ChangeObserver */
    private class ChangeObserver extends ContentObserver {
        final /* synthetic */ CursorAdapter this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        ChangeObserver(android.support.p000v4.widget.CursorAdapter r7) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                r2 = r0
                r3 = r1
                r2.this$0 = r3
                r2 = r0
                android.os.Handler r3 = new android.os.Handler
                r5 = r3
                r3 = r5
                r4 = r5
                r4.<init>()
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.widget.CursorAdapter.ChangeObserver.<init>(android.support.v4.widget.CursorAdapter):void");
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            boolean z2 = z;
            this.this$0.onContentChanged();
        }
    }

    /* renamed from: android.support.v4.widget.CursorAdapter$MyDataSetObserver */
    private class MyDataSetObserver extends DataSetObserver {
        final /* synthetic */ CursorAdapter this$0;

        MyDataSetObserver(CursorAdapter cursorAdapter) {
            this.this$0 = cursorAdapter;
        }

        public void onChanged() {
            this.this$0.mDataValid = true;
            this.this$0.notifyDataSetChanged();
        }

        public void onInvalidated() {
            this.this$0.mDataValid = false;
            this.this$0.notifyDataSetInvalidated();
        }
    }
}
