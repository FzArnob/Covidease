package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.view.ScaleGestureDetector;
import com.google.appinventor.components.runtime.Canvas;

public class MultiTouchDetector {
    /* access modifiers changed from: private */
    public Canvas B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    public class MyOnScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private /* synthetic */ MultiTouchDetector B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        public MyOnScaleGestureListener(MultiTouchDetector multiTouchDetector) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = multiTouchDetector;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.Scale((double) scaleGestureDetector.getScaleFactor());
            return true;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            ScaleGestureDetector scaleGestureDetector2 = scaleGestureDetector;
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }
    }

    public class MyPinchZoomDetector extends ScaleGestureDetector implements Canvas.ExtensionGestureDetector {
        private /* synthetic */ MultiTouchDetector B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        MyPinchZoomDetector(MultiTouchDetector multiTouchDetector, Context context, ScaleGestureDetector.OnScaleGestureListener onScaleGestureListener) {
            super(context, onScaleGestureListener);
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = multiTouchDetector;
        }
    }

    public MultiTouchDetector(Canvas canvas) {
        Canvas.ExtensionGestureDetector extensionGestureDetector;
        ScaleGestureDetector.OnScaleGestureListener onScaleGestureListener;
        Canvas canvas2 = canvas;
        if (canvas2 != null) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = canvas2;
            new MyOnScaleGestureListener(this);
            new MyPinchZoomDetector(this, canvas2.getContext(), onScaleGestureListener);
            canvas2.registerCustomGestureDetector(extensionGestureDetector);
        }
    }
}
