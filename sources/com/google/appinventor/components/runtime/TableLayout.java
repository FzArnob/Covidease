package com.google.appinventor.components.runtime;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public class TableLayout implements Layout {
    private final Handler handler;
    private final android.widget.TableLayout layoutManager;
    private int numColumns;
    private int numRows;

    TableLayout(Context context, int i, int i2) {
        android.widget.TableLayout tableLayout;
        Handler handler2;
        TableRow tableRow;
        ViewGroup.LayoutParams layoutParams;
        Context context2 = context;
        int i3 = i;
        int i4 = i2;
        new android.widget.TableLayout(context2);
        this.layoutManager = tableLayout;
        this.numColumns = i3;
        this.numRows = i4;
        new Handler();
        this.handler = handler2;
        for (int i5 = 0; i5 < i4; i5++) {
            new TableRow(context2);
            TableRow tableRow2 = tableRow;
            for (int i6 = 0; i6 < i3; i6++) {
                tableRow2.addView(newEmptyCellView(), i6, newEmptyCellLayoutParams());
            }
            new TableLayout.LayoutParams();
            this.layoutManager.addView(tableRow2, i5, layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public int getNumColumns() {
        return this.numColumns;
    }

    /* access modifiers changed from: package-private */
    public void setNumColumns(int i) {
        int i2 = i;
        if (i2 > this.numColumns) {
            Context context = this.layoutManager.getContext();
            for (int i3 = 0; i3 < this.numRows; i3++) {
                TableRow tableRow = (TableRow) this.layoutManager.getChildAt(i3);
                for (int i4 = this.numColumns; i4 < i2; i4++) {
                    tableRow.addView(newEmptyCellView(), i4, newEmptyCellLayoutParams());
                }
            }
            this.numColumns = i2;
        } else if (i2 < this.numColumns) {
            for (int i5 = 0; i5 < this.numRows; i5++) {
                ((TableRow) this.layoutManager.getChildAt(i5)).removeViews(i2, this.numColumns - i2);
            }
            this.numColumns = i2;
        }
    }

    /* access modifiers changed from: package-private */
    public int getNumRows() {
        return this.numRows;
    }

    /* access modifiers changed from: package-private */
    public void setNumRows(int i) {
        TableRow tableRow;
        ViewGroup.LayoutParams layoutParams;
        int i2 = i;
        if (i2 > this.numRows) {
            Context context = this.layoutManager.getContext();
            for (int i3 = this.numRows; i3 < i2; i3++) {
                new TableRow(context);
                TableRow tableRow2 = tableRow;
                for (int i4 = 0; i4 < this.numColumns; i4++) {
                    tableRow2.addView(newEmptyCellView(), i4, newEmptyCellLayoutParams());
                }
                new TableLayout.LayoutParams();
                this.layoutManager.addView(tableRow2, i3, layoutParams);
            }
            this.numRows = i2;
        } else if (i2 < this.numRows) {
            this.layoutManager.removeViews(i2, this.numRows - i2);
            this.numRows = i2;
        }
    }

    public ViewGroup getLayoutManager() {
        return this.layoutManager;
    }

    public void add(AndroidViewComponent androidViewComponent) {
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        androidViewComponent2.getView().setLayoutParams(newCellLayoutParams());
        addChildLater(androidViewComponent2);
    }

    private void addChildLater(AndroidViewComponent androidViewComponent) {
        Runnable runnable;
        final AndroidViewComponent androidViewComponent2 = androidViewComponent;
        new Runnable(this) {
            private /* synthetic */ TableLayout B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r6;
            }

            public final void run() {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.addChild(androidViewComponent2);
            }
        };
        boolean post = this.handler.post(runnable);
    }

    /* access modifiers changed from: private */
    public void addChild(AndroidViewComponent androidViewComponent) {
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int Row = androidViewComponent2.Row();
        int Column = androidViewComponent2.Column();
        if (Row == -1 || Column == -1) {
            addChildLater(androidViewComponent2);
        } else if (Row < 0 || Row >= this.numRows) {
            int e = Log.e("TableLayout", "Child has illegal Row property: ".concat(String.valueOf(androidViewComponent2)));
        } else if (Column < 0 || Column >= this.numColumns) {
            int e2 = Log.e("TableLayout", "Child has illegal Column property: ".concat(String.valueOf(androidViewComponent2)));
        } else {
            TableRow tableRow = (TableRow) this.layoutManager.getChildAt(Row);
            tableRow.removeViewAt(Column);
            View view = androidViewComponent2.getView();
            tableRow.addView(view, Column, view.getLayoutParams());
        }
    }

    private View newEmptyCellView() {
        View view;
        new TextView(this.layoutManager.getContext());
        return view;
    }

    private static TableRow.LayoutParams newEmptyCellLayoutParams() {
        TableRow.LayoutParams layoutParams;
        TableRow.LayoutParams layoutParams2 = layoutParams;
        new TableRow.LayoutParams(0, 0);
        return layoutParams2;
    }

    private static TableRow.LayoutParams newCellLayoutParams() {
        TableRow.LayoutParams layoutParams;
        TableRow.LayoutParams layoutParams2 = layoutParams;
        new TableRow.LayoutParams();
        return layoutParams2;
    }
}
