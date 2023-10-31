package android.support.p000v4.widget;

import android.database.Cursor;
import android.widget.Filter;

/* renamed from: android.support.v4.widget.CursorFilter */
class CursorFilter extends Filter {
    CursorFilterClient mClient;

    /* renamed from: android.support.v4.widget.CursorFilter$CursorFilterClient */
    interface CursorFilterClient {
        void changeCursor(Cursor cursor);

        CharSequence convertToString(Cursor cursor);

        Cursor getCursor();

        Cursor runQueryOnBackgroundThread(CharSequence charSequence);
    }

    CursorFilter(CursorFilterClient client) {
        this.mClient = client;
    }

    public CharSequence convertResultToString(Object resultValue) {
        return this.mClient.convertToString((Cursor) resultValue);
    }

    /* access modifiers changed from: protected */
    public Filter.FilterResults performFiltering(CharSequence constraint) {
        Filter.FilterResults filterResults;
        Cursor cursor = this.mClient.runQueryOnBackgroundThread(constraint);
        new Filter.FilterResults();
        Filter.FilterResults results = filterResults;
        if (cursor != null) {
            results.count = cursor.getCount();
            results.values = cursor;
        } else {
            results.count = 0;
            results.values = null;
        }
        return results;
    }

    /* access modifiers changed from: protected */
    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        CharSequence charSequence2 = charSequence;
        Filter.FilterResults results = filterResults;
        Cursor oldCursor = this.mClient.getCursor();
        if (results.values != null && results.values != oldCursor) {
            this.mClient.changeCursor((Cursor) results.values);
        }
    }
}
