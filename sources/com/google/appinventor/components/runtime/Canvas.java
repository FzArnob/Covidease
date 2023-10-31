package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.collect.Sets;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.BoundingBox;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.MultiTouchDetector;
import com.google.appinventor.components.runtime.util.PaintUtil;
import com.google.appinventor.components.runtime.util.PolyUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@DesignerComponent(category = ComponentCategory.ANIMATION, description = "<p>A two-dimensional touch-sensitive rectangular panel on which drawing can be done and sprites can be moved.</p> <p>The <code>BackgroundColor</code>, <code>PaintColor</code>, <code>BackgroundImage</code>, <code>Width</code>, and <code>Height</code> of the Canvas can be set in either the Designer or in the Blocks Editor.  The <code>Width</code> and <code>Height</code> are measured in pixels and must be positive.</p><p>Any location on the Canvas can be specified as a pair of (X, Y) values, where <ul> <li>X is the number of pixels away from the left edge of the Canvas</li><li>Y is the number of pixels away from the top edge of the Canvas</li></ul>.</p> <p>There are events to tell when and where a Canvas has been touched or a <code>Sprite</code> (<code>ImageSprite</code> or <code>Ball</code>) has been dragged.  There are also methods for drawing points, lines, and circles.</p>", version = 14)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE")
public final class Canvas extends AndroidViewComponent implements ComponentContainer {
    private int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private final Set<ExtensionGestureDetector> f352B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = Sets.newHashSet();
    private boolean PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    private boolean bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = false;
    private int backgroundColor;
    private String backgroundImagePath = "";
    private int fontTypeface = 0;
    private final Activity hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final Paint f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final GestureDetector f354hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    final CanvasView f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final C0609b f356hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final MultiTouchDetector f357hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private PolyUtil f358hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    private int vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

    /* renamed from: vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R  reason: collision with other field name */
    private final List<Sprite> f359vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    /* access modifiers changed from: private */
    public boolean zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;

    public interface ExtensionGestureDetector {
        boolean onTouchEvent(MotionEvent motionEvent);
    }

    static /* synthetic */ int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas canvas, int i) {
        int i2 = i;
        int i3 = i2;
        canvas.backgroundColor = i3;
        return i2;
    }

    static /* synthetic */ String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas canvas, String str) {
        String str2 = str;
        String str3 = str2;
        canvas.backgroundImagePath = str3;
        return str2;
    }

    /* renamed from: com.google.appinventor.components.runtime.Canvas$b */
    class C0609b {
        boolean J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = false;
        boolean KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes = false;
        float hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = -1.0f;
        final /* synthetic */ Canvas hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        final List<Sprite> qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
        float vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = -1.0f;
        float vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = -1.0f;
        float wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = -1.0f;

        C0609b(Canvas canvas) {
            List<Sprite> list;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = canvas;
            new ArrayList();
            this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = list;
        }
    }

    public final class CanvasView extends View {
        private Bitmap B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        private Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Bitmap.createBitmap(32, 48, Bitmap.Config.ARGB_8888);

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private android.graphics.Canvas f360hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private BitmapDrawable f361hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private /* synthetic */ Canvas f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        private Bitmap wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

        static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(CanvasView canvasView, String str, int i, int i2, float f) {
            int i3 = i2;
            int i4 = i;
            CanvasView canvasView2 = canvasView;
            CanvasView canvasView3 = canvasView2;
            int save = canvasView2.f360hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.save();
            canvasView3.f360hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.rotate(-f, (float) i4, (float) i3);
            canvasView3.f360hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawText(str, (float) i4, (float) i3, Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(canvasView3.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
            canvasView3.f360hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.restore();
            canvasView3.invalidate();
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CanvasView(Canvas canvas, Context context) {
            super(context);
            android.graphics.Canvas canvas2;
            this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = canvas;
            new android.graphics.Canvas(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            this.f360hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = canvas2;
        }

        /* access modifiers changed from: private */
        public Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
            android.graphics.Canvas canvas;
            setDrawingCacheEnabled(true);
            destroyDrawingCache();
            Bitmap drawingCache = getDrawingCache();
            Bitmap bitmap = drawingCache;
            if (drawingCache == null) {
                int width = getWidth();
                int height = getHeight();
                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                new android.graphics.Canvas(bitmap);
                layout(0, 0, width, height);
                draw(canvas);
            }
            return bitmap;
        }

        public final void onDraw(android.graphics.Canvas canvas) {
            android.graphics.Canvas canvas2 = canvas;
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = null;
            super.onDraw(canvas2);
            canvas2.drawBitmap(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0.0f, 0.0f, (Paint) null);
            for (Sprite onDraw : Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                onDraw.onDraw(canvas2);
            }
            boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T2 = this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = true;
        }

        /* access modifiers changed from: protected */
        public final void onSizeChanged(int i, int i2, int i3, int i4) {
            android.graphics.Canvas canvas;
            Rect rect;
            RectF rectF;
            android.graphics.Canvas canvas2;
            int i5 = i;
            int i6 = i2;
            int i7 = i3;
            int i8 = i4;
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                int width = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth();
                int height = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
                if (i5 != width || i6 != height) {
                    Bitmap bitmap = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    try {
                        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i5, i6, false);
                        Bitmap bitmap2 = createScaledBitmap;
                        if (createScaledBitmap.isMutable()) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = bitmap2;
                            new android.graphics.Canvas(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                            this.f360hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = canvas2;
                        } else {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Bitmap.createBitmap(i5, i6, Bitmap.Config.ARGB_8888);
                            new android.graphics.Canvas(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                            this.f360hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = canvas;
                            new Rect(0, 0, width, height);
                            new RectF(0.0f, 0.0f, (float) i5, (float) i6);
                            this.f360hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawBitmap(bitmap, rect, rectF, (Paint) null);
                        }
                    } catch (Exception e) {
                        int e2 = Log.e("Canvas", String.valueOf(e));
                    }
                    this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
                }
            }
        }

        /* access modifiers changed from: protected */
        public final void onMeasure(int i, int i2) {
            int i3;
            int i4;
            StringBuilder sb;
            int i5 = i;
            int i6 = i2;
            if (this.f361hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                try {
                    Bitmap bitmap = this.f361hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBitmap();
                    i3 = bitmap.getWidth();
                    i4 = bitmap.getHeight();
                } catch (Exception e) {
                    i3 = 32;
                    i4 = 48;
                    new StringBuilder("Error on backgroundDrawable.getBitmap(): ");
                    int e2 = Log.e("Canvas", sb.append(e.getMessage()).toString());
                }
            } else {
                i3 = 32;
                i4 = 48;
            }
            setMeasuredDimension(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i5, i3), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i6, i4));
        }

        private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i, int i2) {
            int i3;
            int i4 = i;
            int i5 = i2;
            int mode = View.MeasureSpec.getMode(i4);
            int size = View.MeasureSpec.getSize(i4);
            if (mode == 1073741824) {
                i3 = size;
            } else {
                i3 = i5;
                if (mode == Integer.MIN_VALUE) {
                    i3 = Math.min(i3, size);
                }
            }
            return i3;
        }

        public final boolean onTouchEvent(MotionEvent motionEvent) {
            BoundingBox boundingBox;
            MotionEvent motionEvent2 = motionEvent;
            this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.container.$form().dontGrabTouchEventsForComponent();
            MotionEvent motionEvent3 = motionEvent2;
            C0609b hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            C0609b bVar = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
            int Width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Width();
            int Height = bVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Height();
            float max = Math.max(0.0f, ((float) ((int) motionEvent3.getX())) / bVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$form().deviceDensity());
            float max2 = Math.max(0.0f, ((float) ((int) motionEvent3.getY())) / bVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$form().deviceDensity());
            new BoundingBox((double) Math.max(0, ((int) max) - 12), (double) Math.max(0, ((int) max2) - 12), (double) Math.min(Width - 1, ((int) max) + 12), (double) Math.min(Height - 1, ((int) max2) + 12));
            BoundingBox boundingBox2 = boundingBox;
            switch (motionEvent3.getAction()) {
                case 0:
                    bVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.clear();
                    bVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = max;
                    bVar.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = max2;
                    bVar.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = max;
                    bVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = max2;
                    bVar.KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes = false;
                    bVar.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = false;
                    for (Sprite sprite : Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                        Sprite sprite2 = sprite;
                        if (sprite.Enabled() && sprite2.Visible() && sprite2.intersectsWith(boundingBox2)) {
                            boolean add = bVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.add(sprite2);
                            sprite2.TouchDown(bVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, bVar.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq);
                        }
                    }
                    bVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TouchDown(bVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, bVar.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq);
                    break;
                case 1:
                    if (!bVar.KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes) {
                        boolean z = false;
                        for (Sprite next : bVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
                            Sprite sprite3 = next;
                            if (next.Enabled() && sprite3.Visible()) {
                                sprite3.Touched(max, max2);
                                sprite3.TouchUp(max, max2);
                                z = true;
                            }
                        }
                        bVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Touched(max, max2, z);
                    } else {
                        for (Sprite next2 : bVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
                            Sprite sprite4 = next2;
                            if (next2.Enabled() && sprite4.Visible()) {
                                sprite4.Touched(max, max2);
                                sprite4.TouchUp(max, max2);
                            }
                        }
                    }
                    bVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TouchUp(max, max2);
                    bVar.KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes = false;
                    bVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = -1.0f;
                    bVar.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = -1.0f;
                    bVar.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = -1.0f;
                    bVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = -1.0f;
                    break;
                case 2:
                    if (bVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou == -1.0f || bVar.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq == -1.0f || bVar.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO == -1.0f || bVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R == -1.0f) {
                        int w = Log.w("Canvas", "In Canvas.MotionEventParser.parse(), an ACTION_MOVE was passed without a preceding ACTION_DOWN: ".concat(String.valueOf(motionEvent3)));
                    }
                    if (bVar.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU || Math.abs(max - bVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou) >= 15.0f || Math.abs(max2 - bVar.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq) >= 15.0f) {
                        bVar.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = true;
                        bVar.KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes = true;
                        if ((max > 0.0f && max <= ((float) Width) && max2 > 0.0f && max2 <= ((float) Height)) || Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                            for (Sprite sprite5 : Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                                if (!bVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.contains(sprite5) && sprite5.Enabled() && sprite5.Visible() && sprite5.intersectsWith(boundingBox2)) {
                                    boolean add2 = bVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.add(sprite5);
                                }
                            }
                            boolean z2 = false;
                            for (Sprite next3 : bVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
                                Sprite sprite6 = next3;
                                if (next3.Enabled() && sprite6.Visible()) {
                                    sprite6.Dragged(bVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, bVar.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq, bVar.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO, bVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, max, max2);
                                    z2 = true;
                                }
                            }
                            bVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Dragged(bVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, bVar.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq, bVar.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO, bVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, max, max2, z2);
                            bVar.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = max;
                            bVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = max2;
                            break;
                        }
                    }
            }
            boolean onTouchEvent = Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).onTouchEvent(motionEvent2);
            for (ExtensionGestureDetector onTouchEvent2 : Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                boolean onTouchEvent3 = onTouchEvent2.onTouchEvent(motionEvent2);
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public final void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str) {
            StringBuilder sb;
            String str2 = str;
            String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str2 == null ? "" : str2);
            this.f361hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
            if (!TextUtils.isEmpty(Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME))) {
                try {
                    this.f361hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = MediaUtil.getBitmapDrawable(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.container.$form(), Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
                } catch (Exception e) {
                    new StringBuilder("Unable to load ");
                    int e2 = Log.e("Canvas", sb.append(Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)).toString());
                }
            }
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R();
            qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE();
        }

        /* JADX WARNING: type inference failed for: r2v11, types: [android.graphics.drawable.Drawable] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R() {
            /*
                r6 = this;
                r0 = r6
                r2 = r0
                com.google.appinventor.components.runtime.Canvas r2 = r2.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                java.lang.String r2 = com.google.appinventor.components.runtime.Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Canvas) r2)
                java.lang.String r3 = ""
                if (r2 == r3) goto L_0x003e
                r2 = r0
                android.graphics.drawable.BitmapDrawable r2 = r2.f361hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                if (r2 == 0) goto L_0x003e
                r2 = r0
                android.graphics.drawable.BitmapDrawable r2 = r2.f361hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                android.graphics.drawable.Drawable$ConstantState r2 = r2.getConstantState()
                android.graphics.drawable.Drawable r2 = r2.newDrawable()
                r5 = r2
                r2 = r5
                r3 = r5
                r1 = r3
                r3 = r0
                com.google.appinventor.components.runtime.Canvas r3 = r3.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                int r3 = com.google.appinventor.components.runtime.Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Canvas) r3)
                if (r3 == 0) goto L_0x003c
                r3 = r0
                com.google.appinventor.components.runtime.Canvas r3 = r3.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                int r3 = com.google.appinventor.components.runtime.Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Canvas) r3)
            L_0x0031:
                android.graphics.PorterDuff$Mode r4 = android.graphics.PorterDuff.Mode.DST_OVER
                r2.setColorFilter(r3, r4)
            L_0x0036:
                r2 = r0
                r3 = r1
                r2.setBackgroundDrawable(r3)
                return
            L_0x003c:
                r3 = -1
                goto L_0x0031
            L_0x003e:
                android.graphics.drawable.ColorDrawable r2 = new android.graphics.drawable.ColorDrawable
                r5 = r2
                r2 = r5
                r3 = r5
                r4 = r0
                com.google.appinventor.components.runtime.Canvas r4 = r4.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                int r4 = com.google.appinventor.components.runtime.Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Canvas) r4)
                if (r4 == 0) goto L_0x0058
                r4 = r0
                com.google.appinventor.components.runtime.Canvas r4 = r4.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                int r4 = com.google.appinventor.components.runtime.Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Canvas) r4)
            L_0x0053:
                r3.<init>(r4)
                r1 = r2
                goto L_0x0036
            L_0x0058:
                r4 = -1
                goto L_0x0053
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Canvas.CanvasView.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R():void");
        }

        private void qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE() {
            this.f360hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawColor(0, PorterDuff.Mode.CLEAR);
            invalidate();
        }

        public final void setBackgroundColor(int i) {
            int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i);
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R();
            qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE();
        }

        /* access modifiers changed from: private */
        public int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(int i, int i2) {
            int i3 = i;
            int i4 = i2;
            if (i3 < 0 || i3 >= this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth() || i4 < 0 || i4 >= this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight()) {
                return 16777215;
            }
            try {
                int pixel = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixel(i3, i4);
                int i5 = pixel;
                if (pixel != 0) {
                    return i5;
                }
                if (this.f361hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                    if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T == null) {
                        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = Bitmap.createScaledBitmap(this.f361hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBitmap(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight(), false);
                    }
                    return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getPixel(i3, i4);
                } else if (Color.alpha(Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) != 0) {
                    return Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                } else {
                    return 16777215;
                }
            } catch (IllegalArgumentException e) {
                int e2 = Log.e("Canvas", "Returning COLOR_NONE (exception) from getBackgroundPixelColor.");
                return 16777215;
            }
        }

        /* access modifiers changed from: private */
        public int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(int i, int i2) {
            int i3 = i;
            int i4 = i2;
            if (i3 < 0 || i3 >= this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth() || i4 < 0 || i4 >= this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight()) {
                return 16777215;
            }
            if (this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou == null) {
                boolean z = false;
                Iterator it = Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f362hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((Sprite) it.next()).Visible()) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (!z) {
                    return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i3, i4);
                }
                this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
            }
            try {
                return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getPixel(i3, i4);
            } catch (IllegalArgumentException e) {
                int e2 = Log.e("Canvas", "Returning COLOR_NONE (exception) from getPixelColor.");
                return 16777215;
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Canvas(com.google.appinventor.components.runtime.ComponentContainer r11) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.fontTypeface = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.backgroundImagePath = r3
            r2 = r0
            r3 = 0
            r2.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = r3
            r2 = r0
            java.util.HashSet r3 = com.google.appinventor.components.runtime.collect.Sets.newHashSet()
            r2.f352B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.appinventor.components.runtime.Canvas$CanvasView r3 = new com.google.appinventor.components.runtime.Canvas$CanvasView
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = r0
            r9 = r5
            r5 = r9
            r6 = r9
            android.app.Activity r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r4.<init>(r5, r6)
            r2.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r0
            android.graphics.Paint r3 = new android.graphics.Paint
            r9 = r3
            r3 = r9
            r4 = r9
            r4.<init>()
            r2.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            android.graphics.Paint r2 = r2.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = 1
            r2.setFlags(r3)
            r2 = r0
            android.graphics.Paint r2 = r2.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = 1073741824(0x40000000, float:2.0)
            r2.setStrokeWidth(r3)
            r2 = r0
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.PaintColor(r3)
            r2 = r0
            r3 = -1
            r2.BackgroundColor(r3)
            r2 = r0
            r3 = 1
            r2.TextAlignment(r3)
            r2 = r0
            r3 = 1096810496(0x41600000, float:14.0)
            r2.FontSize(r3)
            r2 = r1
            android.app.Activity r2 = r2.$context()
            com.google.appinventor.components.runtime.util.TextViewUtil.setContext(r2)
            r2 = r0
            android.graphics.Paint r2 = r2.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r3 = r0
            int r3 = r3.fontTypeface
            r4 = r0
            boolean r4 = r4.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC
            r5 = r0
            boolean r5 = r5.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE
            com.google.appinventor.components.runtime.util.TextViewUtil.setFontTypefaceCanvas(r2, r3, r4, r5)
            r2 = r0
            java.util.LinkedList r3 = new java.util.LinkedList
            r9 = r3
            r3 = r9
            r4 = r9
            r4.<init>()
            r2.f359vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = r3
            r2 = r0
            com.google.appinventor.components.runtime.Canvas$b r3 = new com.google.appinventor.components.runtime.Canvas$b
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = r0
            r4.<init>(r5)
            r2.f356hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            android.view.GestureDetector r3 = new android.view.GestureDetector
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = r0
            android.app.Activity r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.Canvas$a r6 = new com.google.appinventor.components.runtime.Canvas$a
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r0
            r7.<init>(r8)
            r4.<init>(r5, r6)
            r2.f354hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.MultiTouchDetector r3 = new com.google.appinventor.components.runtime.util.MultiTouchDetector
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = r0
            r4.<init>(r5)
            r2.f357hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Canvas.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final View getView() {
        return this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final Activity getContext() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final void registerCustomGestureDetector(ExtensionGestureDetector extensionGestureDetector) {
        boolean add = this.f352B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.add(extensionGestureDetector);
    }

    public final void removeCustomGestureDetector(Object obj) {
        boolean remove = this.f352B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.remove(obj);
    }

    public final boolean ready() {
        return this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
    }

    /* access modifiers changed from: package-private */
    public final void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Sprite sprite) {
        Sprite sprite2 = sprite;
        for (int i = 0; i < this.f359vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.size(); i++) {
            if (this.f359vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.get(i).mo13845Z() > sprite2.mo13845Z()) {
                this.f359vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.add(i, sprite2);
                return;
            }
        }
        boolean add = this.f359vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.add(sprite2);
    }

    /* access modifiers changed from: package-private */
    public final void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Sprite sprite) {
        boolean remove = this.f359vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.remove(sprite);
    }

    public final Activity $context() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final Form $form() {
        return this.container.$form();
    }

    public final void $add(AndroidViewComponent androidViewComponent) {
        Throwable th;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        Throwable th2 = th;
        new UnsupportedOperationException("Canvas.$add() called");
        throw th2;
    }

    public final void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        Throwable th;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Canvas.setChildWidth() called");
        throw th2;
    }

    public final void setChildHeight(AndroidViewComponent androidViewComponent, int i) {
        Throwable th;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Canvas.setChildHeight() called");
        throw th2;
    }

    /* access modifiers changed from: package-private */
    public final void wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(Sprite sprite) {
        this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        findSpriteCollisions(sprite);
    }

    /* access modifiers changed from: protected */
    public final void findSpriteCollisions(Sprite sprite) {
        Sprite sprite2 = sprite;
        for (Sprite next : this.f359vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R) {
            Sprite sprite3 = next;
            if (next != sprite2) {
                if (sprite2.CollidingWith(sprite3)) {
                    if (!sprite2.Visible() || !sprite2.Enabled() || !sprite3.Visible() || !sprite3.Enabled() || !Sprite.colliding(sprite3, sprite2)) {
                        sprite2.NoLongerCollidingWith(sprite3);
                        sprite3.NoLongerCollidingWith(sprite2);
                    }
                } else if (sprite2.Visible() && sprite2.Enabled() && sprite3.Visible() && sprite3.Enabled() && Sprite.colliding(sprite3, sprite2)) {
                    sprite2.CollidedWith(sprite3);
                    sprite3.CollidedWith(sprite2);
                }
            }
        }
    }

    @SimpleProperty
    public final void Width(int i) {
        int i2 = i;
        if (i2 > 0 || i2 == -2 || i2 == -1 || i2 <= -1000) {
            super.Width(i2);
        } else {
            this.container.$form().dispatchErrorOccurredEvent(this, "Width", 1002, new Object[0]);
        }
    }

    @SimpleProperty
    public final void Height(int i) {
        int i2 = i;
        if (i2 > 0 || i2 == -2 || i2 == -1 || i2 <= -1000) {
            super.Height(i2);
        } else {
            this.container.$form().dispatchErrorOccurredEvent(this, "Height", 1003, new Object[0]);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the canvas background.")
    public final int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public final void BackgroundColor(int i) {
        this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The name of a file containing the background image for the canvas")
    public final String BackgroundImage() {
        return this.backgroundImagePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty
    public final void BackgroundImage(String str) {
        this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color in which lines are drawn")
    public final int PaintColor() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty
    public final void PaintColor(int i) {
        int i2 = i;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = i2;
        int i3 = i2;
        Paint paint = this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        switch (i3) {
            case 0:
                PaintUtil.changePaint(paint, -16777216);
                return;
            case 16777215:
                PaintUtil.changePaintTransparent(paint);
                return;
            default:
                PaintUtil.changePaint(paint, i3);
                return;
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font size of text drawn on the canvas.")
    public final float FontSize() {
        return this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTextSize() / $form().deviceDensity();
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
    @SimpleProperty
    public final void FontSize(float f) {
        this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextSize(f * $form().deviceDensity());
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public final void FontTypefaceImport(String str) {
        String str2 = str;
        if (str2 != null) {
            TextViewUtil.setCustomFontTypefaceCanvas(this.container.$form(), this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str2, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void FontTypeface(int i) {
        int i2 = i;
        this.fontTypeface = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final int FontTypeface() {
        return this.fontTypeface;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public final void FontBold(boolean z) {
        boolean z2 = z;
        this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = z2;
        TextViewUtil.setFontTypefaceCanvas(this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, z2, this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final boolean FontBold() {
        return this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public final void FontItalic(boolean z) {
        boolean z2 = z;
        this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = z2;
        TextViewUtil.setFontTypefaceCanvas(this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC, z2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final boolean FontItalic() {
        return this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The width of lines drawn on the canvas.")
    public final float LineWidth() {
        return this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStrokeWidth() / $form().deviceDensity();
    }

    @DesignerProperty(defaultValue = "2.0", editorType = "non_negative_float")
    @SimpleProperty
    public final void LineWidth(float f) {
        this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStrokeWidth(f * $form().deviceDensity());
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Determines the alignment of the text drawn by DrawText() or DrawAngle() with respect to the point specified by that command: point at the left of the text, point at the center of the text, or point at the right of the text.", userVisible = true)
    public final int TextAlignment() {
        return this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    }

    @DesignerProperty(defaultValue = "1", editorType = "textalignment")
    @SimpleProperty(userVisible = true)
    public final void TextAlignment(int i) {
        int i2 = i;
        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = i2;
        switch (i2) {
            case 0:
                this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextAlign(Paint.Align.LEFT);
                return;
            case 1:
                this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextAlign(Paint.Align.CENTER);
                return;
            case 2:
                this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextAlign(Paint.Align.RIGHT);
                return;
            default:
                return;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines whether moves can extend beyond the canvas borders.   Default is false. This should normally be false, and the property is provided for backwards compatibility.", userVisible = true)
    public final boolean ExtendMovesOutsideCanvas() {
        return this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = true)
    public final void ExtendMovesOutsideCanvas(boolean z) {
        boolean z2 = z;
        this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = z2;
    }

    @SimpleEvent
    public final void Touched(float f, float f2, boolean z) {
        Object[] objArr = new Object[3];
        objArr[0] = Float.valueOf(f);
        Object[] objArr2 = objArr;
        objArr2[1] = Float.valueOf(f2);
        Object[] objArr3 = objArr2;
        objArr3[2] = Boolean.valueOf(z);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Touched", objArr3);
    }

    @SimpleEvent
    public final void TouchDown(float f, float f2) {
        Object[] objArr = new Object[2];
        objArr[0] = Float.valueOf(f);
        Object[] objArr2 = objArr;
        objArr2[1] = Float.valueOf(f2);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TouchDown", objArr2);
    }

    @SimpleEvent
    public final void TouchUp(float f, float f2) {
        Object[] objArr = new Object[2];
        objArr[0] = Float.valueOf(f);
        Object[] objArr2 = objArr;
        objArr2[1] = Float.valueOf(f2);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TouchUp", objArr2);
    }

    @SimpleEvent
    public final void Flung(float f, float f2, float f3, float f4, float f5, float f6, boolean z) {
        Object[] objArr = new Object[7];
        objArr[0] = Float.valueOf(f);
        Object[] objArr2 = objArr;
        objArr2[1] = Float.valueOf(f2);
        Object[] objArr3 = objArr2;
        objArr3[2] = Float.valueOf(f3);
        Object[] objArr4 = objArr3;
        objArr4[3] = Float.valueOf(f4);
        Object[] objArr5 = objArr4;
        objArr5[4] = Float.valueOf(f5);
        Object[] objArr6 = objArr5;
        objArr6[5] = Float.valueOf(f6);
        Object[] objArr7 = objArr6;
        objArr7[6] = Boolean.valueOf(z);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Flung", objArr7);
    }

    @SimpleEvent
    public final void Dragged(float f, float f2, float f3, float f4, float f5, float f6, boolean z) {
        Object[] objArr = new Object[7];
        objArr[0] = Float.valueOf(f);
        Object[] objArr2 = objArr;
        objArr2[1] = Float.valueOf(f2);
        Object[] objArr3 = objArr2;
        objArr3[2] = Float.valueOf(f3);
        Object[] objArr4 = objArr3;
        objArr4[3] = Float.valueOf(f4);
        Object[] objArr5 = objArr4;
        objArr5[4] = Float.valueOf(f5);
        Object[] objArr6 = objArr5;
        objArr6[5] = Float.valueOf(f6);
        Object[] objArr7 = objArr6;
        objArr7[6] = Boolean.valueOf(z);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Dragged", objArr7);
    }

    @SimpleFunction(description = "Clears anything drawn on this Canvas but not any background color or image.")
    public final void Clear() {
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    @SimpleFunction
    public final void DrawPoint(int i, int i2) {
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawPoint(((float) i) * $form().deviceDensity(), ((float) i2) * $form().deviceDensity(), this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction
    public final void DrawCircle(int i, int i2, float f, boolean z) {
        Paint paint;
        float deviceDensity = ((float) i) * $form().deviceDensity();
        float deviceDensity2 = ((float) i2) * $form().deviceDensity();
        float deviceDensity3 = f * $form().deviceDensity();
        new Paint(this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        Paint paint2 = paint;
        Paint paint3 = paint2;
        paint2.setStyle(z ? Paint.Style.FILL : Paint.Style.STROKE);
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawCircle(deviceDensity, deviceDensity2, deviceDensity3, paint3);
        this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction
    public final void DrawLine(int i, int i2, int i3, int i4) {
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawLine(((float) i) * $form().deviceDensity(), ((float) i2) * $form().deviceDensity(), ((float) i3) * $form().deviceDensity(), ((float) i4) * $form().deviceDensity(), this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction(description = "Draws a shape on the canvas. pointList should be a list contains sub-lists with two number which represents a coordinate. The first point and last point does not need to be the same. e.g. ((x1 y1) (x2 y2) (x3 y3)) When fill is true, the shape will be filled.")
    public final void DrawShape(YailList yailList, boolean z) {
        Path path;
        Paint paint;
        Throwable th;
        boolean z2 = z;
        try {
            float[][] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList);
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.length == 0) {
                Throwable th2 = th;
                new IllegalArgumentException();
                throw th2;
            }
            float deviceDensity = $form().deviceDensity();
            new Path();
            Path path2 = path;
            Path path3 = path2;
            path2.moveTo(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2[0][0] * deviceDensity, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2[0][1] * deviceDensity);
            for (int i = 1; i < hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.length; i++) {
                path3.lineTo(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2[i][0] * deviceDensity, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2[i][1] * deviceDensity);
            }
            Path path4 = path3;
            path4.close();
            new Paint(this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            Paint paint2 = paint;
            Paint paint3 = paint2;
            paint2.setStyle(z2 ? Paint.Style.FILL : Paint.Style.STROKE);
            CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawPath(path4, paint3);
            this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        } catch (IllegalArgumentException e) {
            $form().dispatchErrorOccurredEvent(this, "DrawShape", 1004, new Object[0]);
        }
    }

    private static float[][] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YailList yailList) throws IllegalArgumentException {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        StringBuilder sb2;
        Throwable th4;
        YailList yailList2 = yailList;
        if (yailList2 == null || yailList2.size() == 0) {
            Throwable th5 = th;
            new IllegalArgumentException();
            throw th5;
        }
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, new int[]{yailList2.size(), 2});
        int i = 0;
        Object[] array = yailList2.toArray();
        Object[] objArr = array;
        int length = array.length;
        int i2 = 0;
        while (i2 < length) {
            Object obj = objArr[i2];
            Object obj2 = obj;
            if (obj instanceof YailList) {
                YailList yailList3 = (YailList) obj2;
                YailList yailList4 = yailList3;
                if (yailList3.size() == 2) {
                    try {
                        fArr[i][0] = Float.parseFloat(yailList4.getString(0));
                        fArr[i][1] = Float.parseFloat(yailList4.getString(1));
                        i++;
                        i2++;
                    } catch (NullPointerException | NumberFormatException e) {
                        RuntimeException runtimeException = e;
                        Throwable th6 = th4;
                        new IllegalArgumentException(runtimeException.fillInStackTrace());
                        throw th6;
                    }
                } else {
                    Throwable th7 = th3;
                    new StringBuilder("length of item YailList(");
                    new IllegalArgumentException(sb2.append(i).append(") is not 2").toString());
                    throw th7;
                }
            } else {
                Throwable th8 = th2;
                new StringBuilder("item(");
                new IllegalArgumentException(sb.append(i).append(") in YailList is not a YailList").toString());
                throw th8;
            }
        }
        return fArr;
    }

    @SimpleFunction(description = "Draw an arc on Canvas, by drawing an arc from a specified oval (specified by left, top, right & bottom). Start angle is 0 when heading to the right, and increase when rotate clockwise. When useCenter is true, a sector will be drawed instead of an arc. When fill is true, a filled arc (or sector) will be drawed instead of just an outline.")
    public final void DrawArc(int i, int i2, int i3, int i4, float f, float f2, boolean z, boolean z2) {
        Paint paint;
        RectF rectF;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        float f3 = f;
        float f4 = f2;
        boolean z3 = z;
        float deviceDensity = $form().deviceDensity();
        new Paint(this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        Paint paint2 = paint;
        Paint paint3 = paint2;
        paint2.setStyle(z2 ? Paint.Style.FILL : Paint.Style.STROKE);
        new RectF(deviceDensity * ((float) i5), deviceDensity * ((float) i6), deviceDensity * ((float) i7), deviceDensity * ((float) i8));
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawArc(rectF, f3, f4, z3, paint3);
        this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction(description = "Draws the specified text relative to the specified coordinates using the values of the FontSize and TextAlignment properties.")
    public final void DrawText(String str, int i, int i2) {
        float deviceDensity = $form().deviceDensity();
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawText(str, ((float) i) * deviceDensity, ((float) i2) * deviceDensity, this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction(description = "Draws the specified text starting at the specified coordinates at the specified angle using the values of the FontSize and TextAlignment properties.")
    public final void DrawTextAtAngle(String str, int i, int i2, float f) {
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str, (int) (((float) i) * $form().deviceDensity()), (int) (((float) i2) * $form().deviceDensity()), f);
    }

    @SimpleFunction(description = "Gets the color of the specified point. This includes the background and any drawn points, lines, or circles but not sprites.")
    public final int GetBackgroundPixelColor(int i, int i2) {
        return this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T((int) (((float) i) * $form().deviceDensity()), (int) (((float) i2) * $form().deviceDensity()));
    }

    @SimpleFunction(description = "Sets the color of the specified point. This differs from DrawPoint by having an argument for color.")
    public final void SetBackgroundPixelColor(int i, int i2, int i3) {
        Paint paint;
        new Paint();
        Paint paint2 = paint;
        PaintUtil.changePaint(paint2, i3);
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawPoint((float) ((int) (((float) i) * $form().deviceDensity())), (float) ((int) (((float) i2) * $form().deviceDensity())), paint2);
        this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction(description = "Gets the color of the specified point.")
    public final int GetPixelColor(int i, int i2) {
        return this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou((int) (((float) i) * $form().deviceDensity()), (int) (((float) i2) * $form().deviceDensity()));
    }

    @SimpleFunction(description = "Saves a picture of this Canvas to the device's external storage. If an error occurs, the Screen's ErrorOccurred event will be called.")
    public final String Save() {
        StringBuilder sb;
        try {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FileUtil.getPictureFile("png"), Bitmap.CompressFormat.PNG, "Save");
        } catch (PermissionException e) {
            this.container.$form().dispatchPermissionDeniedEvent((Component) this, "Save", e);
            return "";
        } catch (FileUtil.FileException e2) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Save", e2.getErrorMessageNumber(), new Object[0]);
            return "";
        } catch (Exception e3) {
            new StringBuilder();
            this.container.$form().dispatchErrorOccurredEvent(this, "Save", ErrorMessages.ERROR_MEDIA_FILE_ERROR, sb.append(e3.getMessage()).toString());
            return "";
        }
    }

    @SimpleFunction(description = "Saves a picture of this Canvas to the device's external storage in the file named fileName. fileName must end with one of .jpg, .jpeg, or .png, which determines the file type.")
    public final String SaveAs(String str) {
        Bitmap.CompressFormat compressFormat;
        StringBuilder sb;
        StringBuilder sb2;
        String str2 = str;
        if (str2.endsWith(".jpg") || str2.endsWith(".jpeg")) {
            compressFormat = Bitmap.CompressFormat.JPEG;
        } else if (str2.endsWith(".png")) {
            compressFormat = Bitmap.CompressFormat.PNG;
        } else if (!str2.contains(".")) {
            new StringBuilder();
            str2 = sb2.append(str2).append(".png").toString();
            compressFormat = Bitmap.CompressFormat.PNG;
        } else {
            this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", ErrorMessages.ERROR_MEDIA_IMAGE_FILE_FORMAT, new Object[0]);
            return "";
        }
        try {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FileUtil.getExternalFile(str2), compressFormat, "SaveAs");
        } catch (PermissionException e) {
            this.container.$form().dispatchPermissionDeniedEvent((Component) this, "SaveAs", e);
            return "";
        } catch (FileUtil.FileException e2) {
            this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", e2.getErrorMessageNumber(), new Object[0]);
            return "";
        } catch (Exception e3) {
            new StringBuilder();
            this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", ErrorMessages.ERROR_MEDIA_FILE_ERROR, sb.append(e3.getMessage()).toString());
            return "";
        }
    }

    @SimpleEvent(description = "This event is invoked when two-finger pinches. ScaleFactor is the ratio of the average distance between two-fingers from current and previous scale event.")
    public final void Scale(double d) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Scale", Double.valueOf(d));
    }

    /* JADX WARNING: type inference failed for: r6v20, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r6v23, types: [java.io.FileOutputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.io.File r17, android.graphics.Bitmap.CompressFormat r18, java.lang.String r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            r15 = r6
            r6 = r15
            r7 = r15
            r8 = r1
            r7.<init>(r8)     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            r4 = r6
            r6 = r0
            com.google.appinventor.components.runtime.Canvas$CanvasView r6 = r6.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            android.graphics.Bitmap r6 = com.google.appinventor.components.runtime.Canvas.CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Canvas.CanvasView) r6)     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            if (r6 != 0) goto L_0x003b
            r6 = r0
            com.google.appinventor.components.runtime.Canvas$CanvasView r6 = r6.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            android.graphics.Bitmap r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME()     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
        L_0x0022:
            r5 = r6
            r6 = r5
            r7 = r2
            r8 = 100
            r9 = r4
            boolean r6 = r6.compress(r7, r8, r9)     // Catch:{ all -> 0x0043 }
            r2 = r6
            r6 = r4
            r6.close()     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            r6 = r2
            if (r6 == 0) goto L_0x006d
            r6 = r1
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            r0 = r6
        L_0x003a:
            return r0
        L_0x003b:
            r6 = r0
            com.google.appinventor.components.runtime.Canvas$CanvasView r6 = r6.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            android.graphics.Bitmap r6 = com.google.appinventor.components.runtime.Canvas.CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Canvas.CanvasView) r6)     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            goto L_0x0022
        L_0x0043:
            r6 = move-exception
            r2 = r6
            r6 = r4
            r6.close()     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            r6 = r2
            throw r6     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
        L_0x004b:
            r6 = move-exception
            r6 = r0
            com.google.appinventor.components.runtime.ComponentContainer r6 = r6.container
            com.google.appinventor.components.runtime.Form r6 = r6.$form()
            r7 = r0
            r8 = r3
            r9 = 707(0x2c3, float:9.91E-43)
            r10 = 1
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r15 = r10
            r10 = r15
            r11 = r15
            r12 = 0
            r13 = r1
            java.lang.String r13 = r13.getAbsolutePath()
            r11[r12] = r13
            r6.dispatchErrorOccurredEvent(r7, r8, r9, r10)
        L_0x0068:
            java.lang.String r6 = ""
            r0 = r6
            goto L_0x003a
        L_0x006d:
            r6 = r0
            com.google.appinventor.components.runtime.ComponentContainer r6 = r6.container     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            com.google.appinventor.components.runtime.Form r6 = r6.$form()     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            r7 = r0
            r8 = r3
            r9 = 1001(0x3e9, float:1.403E-42)
            r10 = 0
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            r6.dispatchErrorOccurredEvent(r7, r8, r9, r10)     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x007f }
            goto L_0x0068
        L_0x007f:
            r6 = move-exception
            r2 = r6
            r6 = r0
            com.google.appinventor.components.runtime.ComponentContainer r6 = r6.container
            com.google.appinventor.components.runtime.Form r6 = r6.$form()
            r7 = r0
            r8 = r3
            r9 = 708(0x2c4, float:9.92E-43)
            r10 = 1
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r15 = r10
            r10 = r15
            r11 = r15
            r12 = 0
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r15 = r13
            r13 = r15
            r14 = r15
            r14.<init>()
            r14 = r2
            java.lang.String r14 = r14.getMessage()
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r11[r12] = r13
            r6.dispatchErrorOccurredEvent(r7, r8, r9, r10)
            goto L_0x0068
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.io.File, android.graphics.Bitmap$CompressFormat, java.lang.String):java.lang.String");
    }

    /* renamed from: com.google.appinventor.components.runtime.Canvas$a */
    class C0608a extends GestureDetector.SimpleOnGestureListener {
        private /* synthetic */ Canvas hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        C0608a(Canvas canvas) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = canvas;
        }

        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            BoundingBox boundingBox;
            MotionEvent motionEvent3 = motionEvent;
            MotionEvent motionEvent4 = motionEvent2;
            float max = (float) Math.max(0, (int) (motionEvent3.getX() / this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$form().deviceDensity()));
            float max2 = (float) Math.max(0, (int) (motionEvent3.getY() / this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$form().deviceDensity()));
            float f3 = f / 1000.0f;
            float f4 = f2 / 1000.0f;
            float f5 = f3;
            float f6 = f5 * f5;
            float f7 = f4;
            float sqrt = (float) Math.sqrt((double) (f6 + (f7 * f7)));
            float f8 = (float) (-Math.toDegrees(Math.atan2((double) f4, (double) f3)));
            new BoundingBox((double) Math.max(0, ((int) max) - 12), (double) Math.max(0, ((int) max2) - 12), (double) Math.min(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Width() - 1, ((int) max) + 12), (double) Math.min(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Height() - 1, ((int) max2) + 12));
            BoundingBox boundingBox2 = boundingBox;
            boolean z = false;
            for (Sprite sprite : Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                Sprite sprite2 = sprite;
                if (sprite.Enabled() && sprite2.Visible() && sprite2.intersectsWith(boundingBox2)) {
                    sprite2.Flung(max, max2, sqrt, f8, f3, f4);
                    z = true;
                }
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Flung(max, max2, sqrt, f8, f3, f4, z);
            return true;
        }
    }

    @SimpleFunction(description = "Creates a polygon with with specified number of sides from a radius.")
    public final void DrawPolygon(float f, float f2, int i, float f3, float f4, float f5, boolean z, boolean z2) {
        Paint paint;
        PolyUtil polyUtil;
        float f6 = f;
        float f7 = f2;
        int i2 = i;
        float f8 = f3;
        float f9 = f4;
        float f10 = f5;
        boolean z3 = z;
        boolean z4 = z2;
        if (this.f358hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            new PolyUtil();
            this.f358hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = polyUtil;
        }
        if (z4) {
            CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        }
        new Paint(this.f353hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        Paint paint2 = paint;
        Paint paint3 = paint2;
        paint2.setStyle(z3 ? Paint.Style.FILL : Paint.Style.STROKE);
        this.f358hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawPolygon(CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), i2, f6 * $form().deviceDensity(), f7 * $form().deviceDensity(), f8 * $form().deviceDensity(), f9 * $form().deviceDensity(), f10, paint3);
        this.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }
}
