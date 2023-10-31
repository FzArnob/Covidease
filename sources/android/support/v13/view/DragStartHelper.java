package android.support.v13.view;

import android.graphics.Point;
import android.support.p000v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

public class DragStartHelper {
    private boolean mDragging;
    private int mLastTouchX;
    private int mLastTouchY;
    private final OnDragStartListener mListener;
    private final View.OnLongClickListener mLongClickListener;
    private final View.OnTouchListener mTouchListener;
    private final View mView;

    public interface OnDragStartListener {
        boolean onDragStart(View view, DragStartHelper dragStartHelper);
    }

    public DragStartHelper(View view, OnDragStartListener listener) {
        View.OnLongClickListener onLongClickListener;
        View.OnTouchListener onTouchListener;
        new View.OnLongClickListener(this) {
            final /* synthetic */ DragStartHelper this$0;

            {
                this.this$0 = this$0;
            }

            public boolean onLongClick(View v) {
                return this.this$0.onLongClick(v);
            }
        };
        this.mLongClickListener = onLongClickListener;
        new View.OnTouchListener(this) {
            final /* synthetic */ DragStartHelper this$0;

            {
                this.this$0 = this$0;
            }

            public boolean onTouch(View v, MotionEvent event) {
                return this.this$0.onTouch(v, event);
            }
        };
        this.mTouchListener = onTouchListener;
        this.mView = view;
        this.mListener = listener;
    }

    public void attach() {
        this.mView.setOnLongClickListener(this.mLongClickListener);
        this.mView.setOnTouchListener(this.mTouchListener);
    }

    public void detach() {
        this.mView.setOnLongClickListener((View.OnLongClickListener) null);
        this.mView.setOnTouchListener((View.OnTouchListener) null);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        View v = view;
        MotionEvent event = motionEvent;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case 0:
                this.mLastTouchX = x;
                this.mLastTouchY = y;
                break;
            case 1:
            case 3:
                this.mDragging = false;
                break;
            case 2:
                if (MotionEventCompat.isFromSource(event, 8194) && (event.getButtonState() & 1) != 0 && !this.mDragging && !(this.mLastTouchX == x && this.mLastTouchY == y)) {
                    this.mLastTouchX = x;
                    this.mLastTouchY = y;
                    this.mDragging = this.mListener.onDragStart(v, this);
                    return this.mDragging;
                }
        }
        return false;
    }

    public boolean onLongClick(View v) {
        return this.mListener.onDragStart(v, this);
    }

    public void getTouchPosition(Point point) {
        point.set(this.mLastTouchX, this.mLastTouchY);
    }
}
