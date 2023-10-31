package com.google.appinventor.components.runtime;

import android.graphics.Canvas;
import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.util.BoundingBox;
import com.google.appinventor.components.runtime.util.TimerInternal;
import java.util.HashSet;
import java.util.Set;

@SimpleObject
public abstract class Sprite extends VisibleComponent implements AlarmHandler, Deleteable, OnDestroyListener {
    protected static final boolean DEFAULT_ORIGIN_AT_CENTER = false;
    private final Handler androidUIHandler;
    protected final Canvas canvas;
    protected Form form;
    protected double heading;
    protected double headingCos;
    protected double headingRadians;
    protected double headingSin;
    private final TimerInternal hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    protected boolean initialized;
    protected int interval;
    protected boolean originAtCenter;
    protected float speed;
    protected double userHeading;
    private final Set<Sprite> vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    protected boolean visible;
    protected double xCenter;
    protected double xLeft;
    protected double yCenter;
    protected double yTop;
    protected double zLayer;

    /* access modifiers changed from: protected */
    public abstract void onDraw(Canvas canvas2);

    protected Sprite(ComponentContainer componentContainer, Handler handler) {
        Set<Sprite> set;
        TimerInternal timerInternal;
        Throwable th;
        ComponentContainer componentContainer2 = componentContainer;
        Handler handler2 = handler;
        this.initialized = false;
        this.visible = true;
        this.androidUIHandler = handler2;
        if (!(componentContainer2 instanceof Canvas)) {
            Throwable th2 = th;
            new IllegalArgumentError("Sprite constructor called with container ".concat(String.valueOf(componentContainer2)));
            throw th2;
        }
        this.canvas = (Canvas) componentContainer2;
        this.canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this);
        new HashSet();
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = set;
        new TimerInternal(this, true, 100, handler2);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = timerInternal;
        this.form = componentContainer2.$form();
        OriginAtCenter(false);
        Heading(0.0d);
        Enabled(true);
        Interval(100);
        Speed(0.0f);
        Visible(true);
        mo13846Z(1.0d);
        componentContainer2.$form().registerForOnDestroy(this);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected Sprite(com.google.appinventor.components.runtime.ComponentContainer r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            android.os.Handler r4 = new android.os.Handler
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Sprite.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public void Initialize() {
        this.initialized = true;
        this.canvas.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(this);
    }

    @SimpleProperty(description = "Controls whether the %type% moves and can be interacted with through collisions, dragging, touching, and flinging.")
    public boolean Enabled() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Enabled(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled(z);
    }

    @DesignerProperty(defaultValue = "0", editorType = "float")
    @SimpleProperty
    public void Heading(double d) {
        double d2 = d;
        this.userHeading = d2;
        this.heading = -d2;
        this.headingRadians = Math.toRadians(this.heading);
        this.headingCos = Math.cos(this.headingRadians);
        this.headingSin = Math.sin(this.headingRadians);
        registerChange();
    }

    @SimpleProperty(description = "Returns the %type%'s heading in degrees above the positive x-axis.  Zero degrees is toward the right of the screen; 90 degrees is toward the top of the screen.")
    public double Heading() {
        return this.userHeading;
    }

    @SimpleProperty(description = "The interval in milliseconds at which the %type%'s position is updated.  For example, if the interval is 50 and the speed is 10, then every 50 milliseconds the sprite will move 10 pixels in the heading direction.")
    public int Interval() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Interval();
    }

    @DesignerProperty(defaultValue = "100", editorType = "non_negative_integer")
    @SimpleProperty
    public void Interval(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Interval(i);
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float")
    @SimpleProperty(description = "The number of pixels that the %type% should move every interval, if enabled.")
    public void Speed(float f) {
        float f2 = f;
        this.speed = f2;
    }

    @SimpleProperty(description = "The speed at which the %type% moves. The %type% moves this many pixels every interval if enabled.")
    public float Speed() {
        return this.speed;
    }

    @SimpleProperty(description = "Whether the %type% is visible.")
    public boolean Visible() {
        return this.visible;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Visible(boolean z) {
        this.visible = z;
        registerChange();
    }

    /* renamed from: X */
    public double mo10194X() {
        return this.originAtCenter ? this.xCenter : this.xLeft;
    }

    private double hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(double d) {
        return d + ((double) (Width() / 2));
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private void m62hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(double d) {
        double d2 = d;
        if (this.originAtCenter) {
            this.xCenter = d2;
            this.xLeft = d2 - ((double) (Width() / 2));
            return;
        }
        this.xLeft = d2;
        this.xCenter = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(d2);
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    /* renamed from: X */
    public void mo13843X(double d) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(d);
        registerChange();
    }

    private double B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(double d) {
        return d + ((double) (Width() / 2));
    }

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other method in class */
    private void m61B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(double d) {
        double d2 = d;
        if (this.originAtCenter) {
            this.yCenter = d2;
            this.yTop = d2 - ((double) (Width() / 2));
            return;
        }
        this.yTop = d2;
        this.yCenter = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(d2);
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float")
    @SimpleProperty
    /* renamed from: Y */
    public void mo13844Y(double d) {
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(d);
        registerChange();
    }

    /* renamed from: Y */
    public double mo10195Y() {
        return this.originAtCenter ? this.yCenter : this.yTop;
    }

    @DesignerProperty(defaultValue = "1.0", editorType = "float")
    @SimpleProperty
    /* renamed from: Z */
    public void mo13846Z(double d) {
        this.zLayer = d;
        Canvas canvas2 = this.canvas;
        Canvas canvas3 = canvas2;
        canvas2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this);
        canvas3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this);
        canvas3.f355hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleProperty(description = "How the %type% should be layered relative to other Balls and ImageSprites, with higher-numbered layers in front of lower-numbered layers.")
    /* renamed from: Z */
    public double mo13845Z() {
        return this.zLayer;
    }

    /* access modifiers changed from: protected */
    public void OriginAtCenter(boolean z) {
        boolean z2 = z;
        this.originAtCenter = z2;
    }

    /* access modifiers changed from: protected */
    public void postEvent(Sprite sprite, String str, Object... objArr) {
        Runnable runnable;
        final Sprite sprite2 = sprite;
        final String str2 = str;
        final Object[] objArr2 = objArr;
        new Runnable(this) {
            private /* synthetic */ Sprite B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r8;
            }

            public final void run() {
                boolean dispatchEvent = EventDispatcher.dispatchEvent(sprite2, str2, objArr2);
            }
        };
        boolean post = this.androidUIHandler.post(runnable);
    }

    @SimpleEvent
    public void CollidedWith(Sprite sprite) {
        Sprite sprite2 = sprite;
        if (!this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.contains(sprite2)) {
            boolean add = this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.add(sprite2);
            postEvent(this, "CollidedWith", sprite2);
        }
    }

    @SimpleEvent(description = "Event handler called when a %type% is dragged. On all calls, the starting coordinates are where the screen was first touched, and the \"current\" coordinates describe the endpoint of the current line segment. On the first call within a given drag, the \"previous\" coordinates are the same as the starting coordinates; subsequently, they are the \"current\" coordinates from the prior call. Note that the %type% won't actually move anywhere in response to the Dragged event unless MoveTo is explicitly called. For smooth movement, each of its coordinates should be set to the sum of its initial value and the difference between its current and previous values.")
    public void Dragged(float f, float f2, float f3, float f4, float f5, float f6) {
        Object[] objArr = new Object[6];
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
        postEvent(this, "Dragged", objArr6);
    }

    @SimpleEvent(description = "Event handler called when the %type% reaches an edge of the screen. If Bounce is then called with that edge, the %type% will appear to bounce off of the edge it reached. Edge here is represented as an integer that indicates one of eight directions north (1), northeast (2), east (3), southeast (4), south (-1), southwest (-2), west (-3), and northwest (-4).")
    public void EdgeReached(int i) {
        int i2 = i;
        if (i2 != 0 && i2 >= -4 && i2 <= 4) {
            postEvent(this, "EdgeReached", Integer.valueOf(i2));
        }
    }

    @SimpleEvent(description = "Event handler called when a pair of sprites (Balls and ImageSprites) are no longer colliding.")
    public void NoLongerCollidingWith(Sprite sprite) {
        Sprite sprite2 = sprite;
        boolean remove = this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.remove(sprite2);
        postEvent(this, "NoLongerCollidingWith", sprite2);
    }

    @SimpleEvent(description = "Event handler called when the user touches an enabled %type% and then immediately lifts their finger. The provided x and y coordinates are relative to the upper left of the canvas.")
    public void Touched(float f, float f2) {
        Object[] objArr = new Object[2];
        objArr[0] = Float.valueOf(f);
        Object[] objArr2 = objArr;
        objArr2[1] = Float.valueOf(f2);
        postEvent(this, "Touched", objArr2);
    }

    @SimpleEvent(description = "Event handler called when a fling gesture (quick swipe) is made on an enabled %type%. This provides the x and y coordinates of the start of the fling (relative to the upper left of the canvas), the speed (pixels per millisecond), the heading (0-360 degrees), and the x and y velocity components of the fling's vector.")
    public void Flung(float f, float f2, float f3, float f4, float f5, float f6) {
        Object[] objArr = new Object[6];
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
        postEvent(this, "Flung", objArr6);
    }

    @SimpleEvent(description = "Event handler called when the user stops touching an enabled %type% (lifting their finger after a TouchDown event). This provides the x and y coordinates of the touch, relative to the upper left of the canvas.")
    public void TouchUp(float f, float f2) {
        Object[] objArr = new Object[2];
        objArr[0] = Float.valueOf(f);
        Object[] objArr2 = objArr;
        objArr2[1] = Float.valueOf(f2);
        postEvent(this, "TouchUp", objArr2);
    }

    @SimpleEvent(description = "Event handler called when the user begins touching an enabled %type% (placing their finger on a %type% and leaving it there). This provides the x and y coordinates of the touch, relative to the upper left of the canvas.")
    public void TouchDown(float f, float f2) {
        Object[] objArr = new Object[2];
        objArr[0] = Float.valueOf(f);
        Object[] objArr2 = objArr;
        objArr2[1] = Float.valueOf(f2);
        postEvent(this, "TouchDown", objArr2);
    }

    @SimpleFunction(description = "Makes the %type% bounce, as if off a wall. For normal bouncing, the edge argument should be the one returned by EdgeReached.")
    public void Bounce(int i) {
        int i2 = i;
        MoveIntoBounds();
        double d = this.userHeading % 360.0d;
        double d2 = d;
        if (d < 0.0d) {
            d2 += 360.0d;
        }
        if ((i2 == 3 && (d2 < 90.0d || d2 > 270.0d)) || (i2 == -3 && d2 > 90.0d && d2 < 270.0d)) {
            Heading(180.0d - d2);
        } else if ((i2 == 1 && d2 > 0.0d && d2 < 180.0d) || (i2 == -1 && d2 > 180.0d)) {
            Heading(360.0d - d2);
        } else if ((i2 == 2 && d2 > 0.0d && d2 < 90.0d) || ((i2 == -4 && d2 > 90.0d && d2 < 180.0d) || ((i2 == -2 && d2 > 180.0d && d2 < 270.0d) || (i2 == 4 && d2 > 270.0d)))) {
            Heading(d2 + 180.0d);
        }
    }

    @SimpleFunction(description = "Indicates whether a collision has been registered between this %type% and the passed sprite (Ball or ImageSprite).")
    public boolean CollidingWith(Sprite sprite) {
        return this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.contains(sprite);
    }

    @SimpleFunction(description = "Moves the %type% back in bounds if part of it extends out of bounds, having no effect otherwise. If the %type% is too wide to fit on the canvas, this aligns the left side of the %type% with the left side of the canvas. If the %type% is too tall to fit on the canvas, this aligns the top side of the %type% with the top side of the canvas.")
    public void MoveIntoBounds() {
        moveIntoBounds(this.canvas.Width(), this.canvas.Height());
    }

    public void MoveTo(double d, double d2) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(d);
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(d2);
        registerChange();
    }

    @SimpleFunction(description = "Turns the %type% to point towards a designated target sprite (Ball or ImageSprite). The new heading will be parallel to the line joining the centerpoints of the two sprites.")
    public void PointTowards(Sprite sprite) {
        Sprite sprite2 = sprite;
        Heading(-Math.toDegrees(Math.atan2(sprite2.yCenter - this.yCenter, sprite2.xCenter - this.xCenter)));
    }

    @SimpleFunction(description = "Sets the heading of the %type% toward the point with the coordinates (x, y).")
    public void PointInDirection(double d, double d2) {
        Heading(-Math.toDegrees(Math.atan2(d2 - this.yCenter, d - this.xCenter)));
    }

    /* access modifiers changed from: protected */
    public void registerChange() {
        if (!this.initialized) {
            this.canvas.getView().invalidate();
            return;
        }
        int hitEdge = hitEdge();
        int i = hitEdge;
        if (hitEdge != 0) {
            EdgeReached(i);
        }
        this.canvas.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(this);
    }

    /* access modifiers changed from: protected */
    public int hitEdge() {
        if (!this.canvas.ready()) {
            return 0;
        }
        return hitEdge(this.canvas.Width(), this.canvas.Height());
    }

    /* access modifiers changed from: protected */
    public final void moveIntoBounds(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        boolean z = false;
        if (Width() > i3) {
            if (this.xLeft != 0.0d) {
                this.xLeft = 0.0d;
                this.xCenter = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.xLeft);
                z = true;
            }
        } else if (hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO()) {
            this.xLeft = 0.0d;
            this.xCenter = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.xLeft);
            z = true;
        } else if (B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i3)) {
            this.xLeft = (double) (i3 - Width());
            this.xCenter = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.xLeft);
            z = true;
        }
        if (Height() > i4) {
            if (this.yTop != 0.0d) {
                this.yTop = 0.0d;
                this.yCenter = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.yTop);
                z = true;
            }
        } else if (vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R()) {
            this.yTop = 0.0d;
            this.yCenter = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.yTop);
            z = true;
        } else if (wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(i4)) {
            this.yTop = (double) (i4 - Height());
            this.yCenter = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.yTop);
            z = true;
        }
        if (z) {
            registerChange();
        }
    }

    /* access modifiers changed from: protected */
    public void updateCoordinates() {
        this.xLeft += ((double) this.speed) * this.headingCos;
        this.xCenter = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.xLeft);
        this.yTop += ((double) this.speed) * this.headingSin;
        this.yCenter = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.yTop);
    }

    private final boolean hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO() {
        return this.xLeft < 0.0d;
    }

    private final boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(int i) {
        return this.xLeft + ((double) Width()) > ((double) i);
    }

    private final boolean vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R() {
        return this.yTop < 0.0d;
    }

    private final boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(int i) {
        return this.yTop + ((double) Height()) > ((double) i);
    }

    /* access modifiers changed from: protected */
    public int hitEdge(int i, int i2) {
        boolean hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO();
        boolean vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R();
        boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i);
        boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(i2);
        if (!vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R && !wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou && !B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T && !hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) {
            return 0;
        }
        MoveIntoBounds();
        if (hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) {
            if (vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R) {
                return -4;
            }
            if (wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou) {
                return -2;
            }
            return -3;
        } else if (B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T) {
            if (vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R) {
                return 2;
            }
            if (wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou) {
                return 4;
            }
            return 3;
        } else if (vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R) {
            return 1;
        } else {
            if (wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou) {
                return -1;
            }
            return 0;
        }
    }

    public BoundingBox getBoundingBox(int i) {
        BoundingBox boundingBox;
        int i2 = i;
        new BoundingBox(this.xLeft - ((double) i2), this.yTop - ((double) i2), ((this.xLeft + ((double) Width())) - 1.0d) + ((double) i2), ((this.yTop + ((double) Height())) - 1.0d) + ((double) i2));
        return boundingBox;
    }

    public static boolean colliding(Sprite sprite, Sprite sprite2) {
        Sprite sprite3 = sprite;
        Sprite sprite4 = sprite2;
        BoundingBox boundingBox = sprite3.getBoundingBox(1);
        if (!boundingBox.intersectDestructively(sprite4.getBoundingBox(1))) {
            return false;
        }
        double left = boundingBox.getLeft();
        while (true) {
            double d = left;
            if (d <= boundingBox.getRight()) {
                double top = boundingBox.getTop();
                while (true) {
                    double d2 = top;
                    if (d2 > boundingBox.getBottom()) {
                        break;
                    } else if (sprite3.containsPoint(d, d2) && sprite4.containsPoint(d, d2)) {
                        return true;
                    } else {
                        top = d2 + 1.0d;
                    }
                }
            } else {
                return false;
            }
            left = d + 1.0d;
        }
    }

    public boolean intersectsWith(BoundingBox boundingBox) {
        BoundingBox boundingBox2 = getBoundingBox(0);
        BoundingBox boundingBox3 = boundingBox2;
        if (!boundingBox2.intersectDestructively(boundingBox)) {
            return false;
        }
        double left = boundingBox3.getLeft();
        while (true) {
            double d = left;
            if (d < boundingBox3.getRight()) {
                double top = boundingBox3.getTop();
                while (true) {
                    double d2 = top;
                    if (d2 >= boundingBox3.getBottom()) {
                        break;
                    } else if (containsPoint(d, d2)) {
                        return true;
                    } else {
                        top = d2 + 1.0d;
                    }
                }
            } else {
                return false;
            }
            left = d + 1.0d;
        }
    }

    public boolean containsPoint(double d, double d2) {
        double d3 = d;
        double d4 = d2;
        if (d3 < this.xLeft || d3 >= this.xLeft + ((double) Width()) || d4 < this.yTop || d4 >= this.yTop + ((double) Height())) {
            return false;
        }
        return true;
    }

    public void alarm() {
        if (this.initialized && this.speed != 0.0f) {
            updateCoordinates();
            registerChange();
        }
    }

    public HandlesEventDispatching getDispatchDelegate() {
        return this.canvas.$form();
    }

    public void onDestroy() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled(false);
    }

    public void onDelete() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled(false);
        this.canvas.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this);
    }
}
