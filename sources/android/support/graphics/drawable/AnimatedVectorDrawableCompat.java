package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.p000v4.content.res.ResourcesCompat;
import android.support.p000v4.content.res.TypedArrayUtils;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.util.C1642ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable2Compat {
    private static final String ANIMATED_VECTOR = "animated-vector";
    private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
    private static final String LOGTAG = "AnimatedVDCompat";
    private static final String TARGET = "target";
    private AnimatedVectorDrawableCompatState mAnimatedVectorState;
    ArrayList<Animatable2Compat.AnimationCallback> mAnimationCallbacks;
    private Animator.AnimatorListener mAnimatorListener;
    private ArgbEvaluator mArgbEvaluator;
    AnimatedVectorDrawableDelegateState mCachedConstantStateDelegate;
    final Drawable.Callback mCallback;
    private Context mContext;

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect x0) {
        return super.getPadding(x0);
    }

    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int x0) {
        super.setChangingConfigurations(x0);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int x0, PorterDuff.Mode x1) {
        super.setColorFilter(x0, x1);
    }

    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean x0) {
        super.setFilterBitmap(x0);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float x0, float x1) {
        super.setHotspot(x0, x1);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int x0, int x1, int x2, int x3) {
        super.setHotspotBounds(x0, x1, x2, x3);
    }

    public /* bridge */ /* synthetic */ boolean setState(int[] x0) {
        return super.setState(x0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    AnimatedVectorDrawableCompat() {
        this((Context) null, (AnimatedVectorDrawableCompatState) null, (Resources) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private AnimatedVectorDrawableCompat(@Nullable Context context) {
        this(context, (AnimatedVectorDrawableCompatState) null, (Resources) null);
    }

    private AnimatedVectorDrawableCompat(@Nullable Context context, @Nullable AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState, @Nullable Resources resources) {
        Drawable.Callback callback;
        AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState2;
        Context context2 = context;
        AnimatedVectorDrawableCompatState state = animatedVectorDrawableCompatState;
        Resources res = resources;
        this.mArgbEvaluator = null;
        this.mAnimatorListener = null;
        this.mAnimationCallbacks = null;
        new Drawable.Callback(this) {
            final /* synthetic */ AnimatedVectorDrawableCompat this$0;

            {
                this.this$0 = this$0;
            }

            public void invalidateDrawable(Drawable drawable) {
                Drawable drawable2 = drawable;
                this.this$0.invalidateSelf();
            }

            public void scheduleDrawable(Drawable drawable, Runnable what, long when) {
                Drawable drawable2 = drawable;
                this.this$0.scheduleSelf(what, when);
            }

            public void unscheduleDrawable(Drawable drawable, Runnable what) {
                Drawable drawable2 = drawable;
                this.this$0.unscheduleSelf(what);
            }
        };
        this.mCallback = callback;
        this.mContext = context2;
        if (state != null) {
            this.mAnimatedVectorState = state;
            return;
        }
        new AnimatedVectorDrawableCompatState(context2, state, this.mCallback, res);
        this.mAnimatedVectorState = animatedVectorDrawableCompatState2;
    }

    public Drawable mutate() {
        if (this.mDelegateDrawable != null) {
            Drawable mutate = this.mDelegateDrawable.mutate();
        }
        return this;
    }

    @Nullable
    public static AnimatedVectorDrawableCompat create(@NonNull Context context, @DrawableRes int i) {
        int type;
        Throwable th;
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
        AnimatedVectorDrawableDelegateState animatedVectorDrawableDelegateState;
        Context context2 = context;
        int resId = i;
        if (Build.VERSION.SDK_INT >= 24) {
            new AnimatedVectorDrawableCompat(context2);
            AnimatedVectorDrawableCompat drawable = animatedVectorDrawableCompat;
            drawable.mDelegateDrawable = ResourcesCompat.getDrawable(context2.getResources(), resId, context2.getTheme());
            drawable.mDelegateDrawable.setCallback(drawable.mCallback);
            new AnimatedVectorDrawableDelegateState(drawable.mDelegateDrawable.getConstantState());
            drawable.mCachedConstantStateDelegate = animatedVectorDrawableDelegateState;
            return drawable;
        }
        try {
            XmlPullParser parser = context2.getResources().getXml(resId);
            AttributeSet attrs = Xml.asAttributeSet(parser);
            while (true) {
                int next = parser.next();
                type = next;
                if (next == 2 || type == 1) {
                }
            }
            if (type != 2) {
                Throwable th2 = th;
                new XmlPullParserException("No start tag found");
                throw th2;
            }
            return createFromXmlInner(context2, context2.getResources(), parser, attrs, context2.getTheme());
        } catch (XmlPullParserException e) {
            int e2 = Log.e(LOGTAG, "parser error", e);
            return null;
        } catch (IOException e3) {
            int e4 = Log.e(LOGTAG, "parser error", e3);
            return null;
        }
    }

    public static AnimatedVectorDrawableCompat createFromXmlInner(Context context, Resources r, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws XmlPullParserException, IOException {
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
        new AnimatedVectorDrawableCompat(context);
        AnimatedVectorDrawableCompat drawable = animatedVectorDrawableCompat;
        drawable.inflate(r, parser, attrs, theme);
        return drawable;
    }

    public Drawable.ConstantState getConstantState() {
        Drawable.ConstantState constantState;
        if (this.mDelegateDrawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        new AnimatedVectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
        return constantState;
    }

    public int getChangingConfigurations() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.mAnimatedVectorState.mChangingConfigurations;
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.draw(canvas2);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.draw(canvas2);
        if (this.mAnimatedVectorState.mAnimatorSet.isStarted()) {
            invalidateSelf();
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Rect bounds = rect;
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(bounds);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setBounds(bounds);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int[] state = iArr;
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setState(state);
        }
        return this.mAnimatedVectorState.mVectorDrawable.setState(state);
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        int level = i;
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setLevel(level);
        }
        return this.mAnimatedVectorState.mVectorDrawable.setLevel(level);
    }

    public int getAlpha() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.getAlpha(this.mDelegateDrawable);
        }
        return this.mAnimatedVectorState.mVectorDrawable.getAlpha();
    }

    public void setAlpha(int i) {
        int alpha = i;
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setAlpha(alpha);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setAlpha(alpha);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        ColorFilter colorFilter2 = colorFilter;
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(colorFilter2);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setColorFilter(colorFilter2);
        }
    }

    public void setTint(int i) {
        int tint = i;
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTint(this.mDelegateDrawable, tint);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setTint(tint);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        ColorStateList tint = colorStateList;
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintList(this.mDelegateDrawable, tint);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setTintList(tint);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        PorterDuff.Mode tintMode = mode;
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintMode(this.mDelegateDrawable, tintMode);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setTintMode(tintMode);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = z;
        boolean restart = z2;
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setVisible(visible, restart);
        }
        boolean visible2 = this.mAnimatedVectorState.mVectorDrawable.setVisible(visible, restart);
        return super.setVisible(visible, restart);
    }

    public boolean isStateful() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.isStateful();
        }
        return this.mAnimatedVectorState.mVectorDrawable.isStateful();
    }

    public int getOpacity() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getOpacity();
        }
        return this.mAnimatedVectorState.mVectorDrawable.getOpacity();
    }

    public int getIntrinsicWidth() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicWidth();
        }
        return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicHeight();
        }
        return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
    }

    public boolean isAutoMirrored() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.isAutoMirrored(this.mDelegateDrawable);
        }
        return this.mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
    }

    public void setAutoMirrored(boolean z) {
        boolean mirrored = z;
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setAutoMirrored(this.mDelegateDrawable, mirrored);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setAutoMirrored(mirrored);
        }
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Throwable th;
        Resources res = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        if (this.mDelegateDrawable != null) {
            DrawableCompat.inflate(this.mDelegateDrawable, res, parser, attrs, theme2);
            return;
        }
        int eventType = parser.getEventType();
        int innerDepth = parser.getDepth() + 1;
        while (eventType != 1 && (parser.getDepth() >= innerDepth || eventType != 3)) {
            if (eventType == 2) {
                String tagName = parser.getName();
                if (ANIMATED_VECTOR.equals(tagName)) {
                    TypedArray a = TypedArrayUtils.obtainAttributes(res, theme2, attrs, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE);
                    int drawableRes = a.getResourceId(0, 0);
                    if (drawableRes != 0) {
                        VectorDrawableCompat vectorDrawable = VectorDrawableCompat.create(res, drawableRes, theme2);
                        vectorDrawable.setAllowCaching(false);
                        vectorDrawable.setCallback(this.mCallback);
                        if (this.mAnimatedVectorState.mVectorDrawable != null) {
                            this.mAnimatedVectorState.mVectorDrawable.setCallback((Drawable.Callback) null);
                        }
                        this.mAnimatedVectorState.mVectorDrawable = vectorDrawable;
                    }
                    a.recycle();
                } else if (TARGET.equals(tagName)) {
                    TypedArray a2 = res.obtainAttributes(attrs, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET);
                    String target = a2.getString(0);
                    int id = a2.getResourceId(1, 0);
                    if (id != 0) {
                        if (this.mContext != null) {
                            setupAnimatorsForTarget(target, AnimatorInflaterCompat.loadAnimator(this.mContext, id));
                        } else {
                            a2.recycle();
                            Throwable th2 = th;
                            new IllegalStateException("Context can't be null when inflating animators");
                            throw th2;
                        }
                    }
                    a2.recycle();
                } else {
                    continue;
                }
            }
            eventType = parser.next();
        }
        this.mAnimatedVectorState.setupAnimatorSet();
    }

    public void inflate(Resources res, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        inflate(res, parser, attrs, (Resources.Theme) null);
    }

    public void applyTheme(Resources.Theme theme) {
        Resources.Theme t = theme;
        if (this.mDelegateDrawable != null) {
            DrawableCompat.applyTheme(this.mDelegateDrawable, t);
        }
    }

    public boolean canApplyTheme() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.canApplyTheme(this.mDelegateDrawable);
        }
        return false;
    }

    @RequiresApi(24)
    private static class AnimatedVectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public AnimatedVectorDrawableDelegateState(Drawable.ConstantState state) {
            this.mDelegateState = state;
        }

        public Drawable newDrawable() {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
            new AnimatedVectorDrawableCompat();
            AnimatedVectorDrawableCompat drawableCompat = animatedVectorDrawableCompat;
            drawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable();
            drawableCompat.mDelegateDrawable.setCallback(drawableCompat.mCallback);
            return drawableCompat;
        }

        public Drawable newDrawable(Resources res) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
            new AnimatedVectorDrawableCompat();
            AnimatedVectorDrawableCompat drawableCompat = animatedVectorDrawableCompat;
            drawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(res);
            drawableCompat.mDelegateDrawable.setCallback(drawableCompat.mCallback);
            return drawableCompat;
        }

        public Drawable newDrawable(Resources res, Resources.Theme theme) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
            new AnimatedVectorDrawableCompat();
            AnimatedVectorDrawableCompat drawableCompat = animatedVectorDrawableCompat;
            drawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(res, theme);
            drawableCompat.mDelegateDrawable.setCallback(drawableCompat.mCallback);
            return drawableCompat;
        }

        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }
    }

    private static class AnimatedVectorDrawableCompatState extends Drawable.ConstantState {
        AnimatorSet mAnimatorSet;
        ArrayList<Animator> mAnimators;
        int mChangingConfigurations;
        C1642ArrayMap<Animator, String> mTargetNameMap;
        VectorDrawableCompat mVectorDrawable;

        public AnimatedVectorDrawableCompatState(Context context, AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState, Drawable.Callback callback, Resources resources) {
            ArrayList<Animator> arrayList;
            C1642ArrayMap<Animator, String> arrayMap;
            Context context2 = context;
            AnimatedVectorDrawableCompatState copy = animatedVectorDrawableCompatState;
            Drawable.Callback owner = callback;
            Resources res = resources;
            if (copy != null) {
                this.mChangingConfigurations = copy.mChangingConfigurations;
                if (copy.mVectorDrawable != null) {
                    Drawable.ConstantState cs = copy.mVectorDrawable.getConstantState();
                    if (res != null) {
                        this.mVectorDrawable = (VectorDrawableCompat) cs.newDrawable(res);
                    } else {
                        this.mVectorDrawable = (VectorDrawableCompat) cs.newDrawable();
                    }
                    this.mVectorDrawable = (VectorDrawableCompat) this.mVectorDrawable.mutate();
                    this.mVectorDrawable.setCallback(owner);
                    this.mVectorDrawable.setBounds(copy.mVectorDrawable.getBounds());
                    this.mVectorDrawable.setAllowCaching(false);
                }
                if (copy.mAnimators != null) {
                    int numAnimators = copy.mAnimators.size();
                    new ArrayList<>(numAnimators);
                    this.mAnimators = arrayList;
                    new C1642ArrayMap<>(numAnimators);
                    this.mTargetNameMap = arrayMap;
                    for (int i = 0; i < numAnimators; i++) {
                        Animator anim = copy.mAnimators.get(i);
                        Animator animClone = anim.clone();
                        String targetName = copy.mTargetNameMap.get(anim);
                        animClone.setTarget(this.mVectorDrawable.getTargetByName(targetName));
                        boolean add = this.mAnimators.add(animClone);
                        String put = this.mTargetNameMap.put(animClone, targetName);
                    }
                    setupAnimatorSet();
                }
            }
        }

        public Drawable newDrawable() {
            Throwable th;
            Throwable th2 = th;
            new IllegalStateException("No constant state support for SDK < 24.");
            throw th2;
        }

        public Drawable newDrawable(Resources resources) {
            Throwable th;
            Resources resources2 = resources;
            Throwable th2 = th;
            new IllegalStateException("No constant state support for SDK < 24.");
            throw th2;
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public void setupAnimatorSet() {
            AnimatorSet animatorSet;
            if (this.mAnimatorSet == null) {
                new AnimatorSet();
                this.mAnimatorSet = animatorSet;
            }
            this.mAnimatorSet.playTogether(this.mAnimators);
        }
    }

    private void setupColorAnimator(Animator animator) {
        ArgbEvaluator argbEvaluator;
        List<Animator> childAnimators;
        Animator animator2 = animator;
        if ((animator2 instanceof AnimatorSet) && (childAnimators = ((AnimatorSet) animator2).getChildAnimations()) != null) {
            for (int i = 0; i < childAnimators.size(); i++) {
                setupColorAnimator(childAnimators.get(i));
            }
        }
        if (animator2 instanceof ObjectAnimator) {
            ObjectAnimator objectAnim = (ObjectAnimator) animator2;
            String propertyName = objectAnim.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.mArgbEvaluator == null) {
                    new ArgbEvaluator();
                    this.mArgbEvaluator = argbEvaluator;
                }
                objectAnim.setEvaluator(this.mArgbEvaluator);
            }
        }
    }

    private void setupAnimatorsForTarget(String str, Animator animator) {
        ArrayList<Animator> arrayList;
        C1642ArrayMap<Animator, String> arrayMap;
        String name = str;
        Animator animator2 = animator;
        animator2.setTarget(this.mAnimatedVectorState.mVectorDrawable.getTargetByName(name));
        if (Build.VERSION.SDK_INT < 21) {
            setupColorAnimator(animator2);
        }
        if (this.mAnimatedVectorState.mAnimators == null) {
            AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState = this.mAnimatedVectorState;
            new ArrayList<>();
            animatedVectorDrawableCompatState.mAnimators = arrayList;
            AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState2 = this.mAnimatedVectorState;
            new C1642ArrayMap<>();
            animatedVectorDrawableCompatState2.mTargetNameMap = arrayMap;
        }
        boolean add = this.mAnimatedVectorState.mAnimators.add(animator2);
        String put = this.mAnimatedVectorState.mTargetNameMap.put(animator2, name);
    }

    public boolean isRunning() {
        if (this.mDelegateDrawable != null) {
            return ((AnimatedVectorDrawable) this.mDelegateDrawable).isRunning();
        }
        return this.mAnimatedVectorState.mAnimatorSet.isRunning();
    }

    public void start() {
        if (this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable) this.mDelegateDrawable).start();
        } else if (!this.mAnimatedVectorState.mAnimatorSet.isStarted()) {
            this.mAnimatedVectorState.mAnimatorSet.start();
            invalidateSelf();
        }
    }

    public void stop() {
        if (this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable) this.mDelegateDrawable).stop();
        } else {
            this.mAnimatedVectorState.mAnimatorSet.end();
        }
    }

    @RequiresApi(23)
    private static boolean unregisterPlatformCallback(AnimatedVectorDrawable dr, Animatable2Compat.AnimationCallback callback) {
        return dr.unregisterAnimationCallback(callback.getPlatformCallback());
    }

    public void registerAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        Animator.AnimatorListener animatorListener;
        ArrayList<Animatable2Compat.AnimationCallback> arrayList;
        Animatable2Compat.AnimationCallback callback = animationCallback;
        if (this.mDelegateDrawable != null) {
            registerPlatformCallback((AnimatedVectorDrawable) this.mDelegateDrawable, callback);
        } else if (callback != null) {
            if (this.mAnimationCallbacks == null) {
                new ArrayList<>();
                this.mAnimationCallbacks = arrayList;
            }
            if (!this.mAnimationCallbacks.contains(callback)) {
                boolean add = this.mAnimationCallbacks.add(callback);
                if (this.mAnimatorListener == null) {
                    new AnimatorListenerAdapter(this) {
                        final /* synthetic */ AnimatedVectorDrawableCompat this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void onAnimationStart(Animator animator) {
                            ArrayList arrayList;
                            Animator animator2 = animator;
                            new ArrayList(this.this$0.mAnimationCallbacks);
                            ArrayList arrayList2 = arrayList;
                            int size = arrayList2.size();
                            for (int i = 0; i < size; i++) {
                                ((Animatable2Compat.AnimationCallback) arrayList2.get(i)).onAnimationStart(this.this$0);
                            }
                        }

                        public void onAnimationEnd(Animator animator) {
                            ArrayList arrayList;
                            Animator animator2 = animator;
                            new ArrayList(this.this$0.mAnimationCallbacks);
                            ArrayList arrayList2 = arrayList;
                            int size = arrayList2.size();
                            for (int i = 0; i < size; i++) {
                                ((Animatable2Compat.AnimationCallback) arrayList2.get(i)).onAnimationEnd(this.this$0);
                            }
                        }
                    };
                    this.mAnimatorListener = animatorListener;
                }
                this.mAnimatedVectorState.mAnimatorSet.addListener(this.mAnimatorListener);
            }
        }
    }

    @RequiresApi(23)
    private static void registerPlatformCallback(@NonNull AnimatedVectorDrawable avd, @NonNull Animatable2Compat.AnimationCallback callback) {
        avd.registerAnimationCallback(callback.getPlatformCallback());
    }

    private void removeAnimatorSetListener() {
        if (this.mAnimatorListener != null) {
            this.mAnimatedVectorState.mAnimatorSet.removeListener(this.mAnimatorListener);
            this.mAnimatorListener = null;
        }
    }

    public boolean unregisterAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        Animatable2Compat.AnimationCallback callback = animationCallback;
        if (this.mDelegateDrawable != null) {
            boolean unregisterPlatformCallback = unregisterPlatformCallback((AnimatedVectorDrawable) this.mDelegateDrawable, callback);
        }
        if (this.mAnimationCallbacks == null || callback == null) {
            return false;
        }
        boolean removed = this.mAnimationCallbacks.remove(callback);
        if (this.mAnimationCallbacks.size() == 0) {
            removeAnimatorSetListener();
        }
        return removed;
    }

    public void clearAnimationCallbacks() {
        if (this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable) this.mDelegateDrawable).clearAnimationCallbacks();
            return;
        }
        removeAnimatorSetListener();
        if (this.mAnimationCallbacks != null) {
            this.mAnimationCallbacks.clear();
        }
    }

    public static void registerAnimationCallback(Drawable drawable, Animatable2Compat.AnimationCallback animationCallback) {
        Drawable dr = drawable;
        Animatable2Compat.AnimationCallback callback = animationCallback;
        if (dr != null && callback != null && (dr instanceof Animatable)) {
            if (Build.VERSION.SDK_INT >= 24) {
                registerPlatformCallback((AnimatedVectorDrawable) dr, callback);
            } else {
                ((AnimatedVectorDrawableCompat) dr).registerAnimationCallback(callback);
            }
        }
    }

    public static boolean unregisterAnimationCallback(Drawable drawable, Animatable2Compat.AnimationCallback animationCallback) {
        Drawable dr = drawable;
        Animatable2Compat.AnimationCallback callback = animationCallback;
        if (dr == null || callback == null) {
            return false;
        }
        if (!(dr instanceof Animatable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return unregisterPlatformCallback((AnimatedVectorDrawable) dr, callback);
        }
        return ((AnimatedVectorDrawableCompat) dr).unregisterAnimationCallback(callback);
    }

    public static void clearAnimationCallbacks(Drawable drawable) {
        Drawable dr = drawable;
        if (dr != null && (dr instanceof Animatable)) {
            if (Build.VERSION.SDK_INT >= 24) {
                ((AnimatedVectorDrawable) dr).clearAnimationCallbacks();
            } else {
                ((AnimatedVectorDrawableCompat) dr).clearAnimationCallbacks();
            }
        }
    }
}
