package com.google.appinventor.components.runtime.util.listener;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {
    private final GestureDetector B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private final String LOG_TAG = "OnSwipeTouchListener";

    public OnSwipeTouchListener(Context context) {
        GestureDetector gestureDetector;
        GestureDetector.OnGestureListener onGestureListener;
        new C1224a(this, (byte) 0);
        new GestureDetector(context, onGestureListener);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = gestureDetector;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        View view2 = view;
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.onTouchEvent(motionEvent);
    }

    /* renamed from: com.google.appinventor.components.runtime.util.listener.OnSwipeTouchListener$a */
    final class C1224a extends GestureDetector.SimpleOnGestureListener {
        private /* synthetic */ OnSwipeTouchListener hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        private C1224a(OnSwipeTouchListener onSwipeTouchListener) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = onSwipeTouchListener;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1224a(OnSwipeTouchListener onSwipeTouchListener, byte b) {
            this(onSwipeTouchListener);
            byte b2 = b;
        }

        public final boolean onDown(MotionEvent motionEvent) {
            MotionEvent motionEvent2 = motionEvent;
            return true;
        }

        public final boolean onSingleTapUp(MotionEvent motionEvent) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onClick();
            return super.onSingleTapUp(motionEvent);
        }

        public final boolean onDoubleTap(MotionEvent motionEvent) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onDoubleClick();
            return super.onDoubleTap(motionEvent);
        }

        public final void onLongPress(MotionEvent motionEvent) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onLongClick();
            super.onLongPress(motionEvent);
        }

        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            StringBuilder sb;
            MotionEvent motionEvent3 = motionEvent;
            MotionEvent motionEvent4 = motionEvent2;
            float f3 = f;
            float f4 = f2;
            boolean z = false;
            try {
                float y = motionEvent4.getY() - motionEvent3.getY();
                float x = motionEvent4.getX() - motionEvent3.getX();
                float f5 = x;
                if (Math.abs(x) > Math.abs(y)) {
                    if (Math.abs(f5) > 100.0f && Math.abs(f3) > 100.0f) {
                        if (f5 > 0.0f) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onSwipeRight();
                        } else {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onSwipeLeft();
                        }
                        z = true;
                        return z;
                    }
                } else if (Math.abs(y) > 100.0f && Math.abs(f4) > 100.0f) {
                    if (y > 0.0f) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onSwipeBottom();
                    } else {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onSwipeTop();
                    }
                    z = true;
                }
            } catch (Exception e) {
                new StringBuilder();
                int d = Log.d("OnSwipeTouchListener", sb.append(e.getMessage()).toString());
            }
            return z;
        }
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }

    public void onClick() {
    }

    public void onLongClick() {
    }

    public void onDoubleClick() {
    }
}
