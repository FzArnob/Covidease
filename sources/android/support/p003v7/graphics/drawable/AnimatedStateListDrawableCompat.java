package android.support.p003v7.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.p000v4.content.res.TypedArrayUtils;
import android.support.p000v4.util.C1647LongSparseArray;
import android.support.p000v4.util.C1651SparseArrayCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.graphics.drawable.DrawableContainer;
import android.support.p003v7.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat */
public class AnimatedStateListDrawableCompat extends StateListDrawable {
    private static final String ELEMENT_ITEM = "item";
    private static final String ELEMENT_TRANSITION = "transition";
    private static final String ITEM_MISSING_DRAWABLE_ERROR = ": <item> tag requires a 'drawable' attribute or child tag defining a drawable";
    private static final String LOGTAG = AnimatedStateListDrawableCompat.class.getSimpleName();
    private static final String TRANSITION_MISSING_DRAWABLE_ERROR = ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable";
    private static final String TRANSITION_MISSING_FROM_TO_ID = ": <transition> tag requires 'fromId' & 'toId' attributes";
    private boolean mMutated;
    private AnimatedStateListState mState;
    private Transition mTransition;
    private int mTransitionFromIndex;
    private int mTransitionToIndex;

    public /* bridge */ /* synthetic */ void addState(int[] iArr, Drawable drawable) {
        super.addState(iArr, drawable);
    }

    @RequiresApi(21)
    public /* bridge */ /* synthetic */ void applyTheme(@NonNull Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @RequiresApi(21)
    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    public /* bridge */ /* synthetic */ void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
    }

    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    public /* bridge */ /* synthetic */ int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @NonNull
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public /* bridge */ /* synthetic */ void getHotspotBounds(@NonNull Rect rect) {
        super.getHotspotBounds(rect);
    }

    public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @RequiresApi(21)
    public /* bridge */ /* synthetic */ void getOutline(@NonNull Outline outline) {
        super.getOutline(outline);
    }

    public /* bridge */ /* synthetic */ boolean getPadding(@NonNull Rect rect) {
        return super.getPadding(rect);
    }

    public /* bridge */ /* synthetic */ void invalidateDrawable(@NonNull Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i) {
        return super.onLayoutDirectionChanged(i);
    }

    public /* bridge */ /* synthetic */ void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
        super.scheduleDrawable(drawable, runnable, j);
    }

    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z) {
        super.setAutoMirrored(z);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    public /* bridge */ /* synthetic */ void setDither(boolean z) {
        super.setDither(z);
    }

    public /* bridge */ /* synthetic */ void setEnterFadeDuration(int i) {
        super.setEnterFadeDuration(i);
    }

    public /* bridge */ /* synthetic */ void setExitFadeDuration(int i) {
        super.setExitFadeDuration(i);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    public /* bridge */ /* synthetic */ void setTintMode(@NonNull PorterDuff.Mode mode) {
        super.setTintMode(mode);
    }

    public /* bridge */ /* synthetic */ void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AnimatedStateListDrawableCompat() {
        this((AnimatedStateListState) null, (Resources) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AnimatedStateListDrawableCompat(@Nullable AnimatedStateListState state, @Nullable Resources res) {
        super((StateListDrawable.StateListState) null);
        DrawableContainer.DrawableContainerState drawableContainerState;
        this.mTransitionToIndex = -1;
        this.mTransitionFromIndex = -1;
        new AnimatedStateListState(state, this, res);
        setConstantState(drawableContainerState);
        boolean onStateChange = onStateChange(getState());
        jumpToCurrentState();
    }

    @Nullable
    public static AnimatedStateListDrawableCompat create(@NonNull Context context, @DrawableRes int i, @Nullable Resources.Theme theme) {
        int type;
        Throwable th;
        Context context2 = context;
        int resId = i;
        Resources.Theme theme2 = theme;
        try {
            Resources res = context2.getResources();
            XmlPullParser parser = res.getXml(resId);
            AttributeSet attrs = Xml.asAttributeSet(parser);
            while (true) {
                int next = parser.next();
                type = next;
                if (next == 2 || type == 1) {
                }
            }
            if (type == 2) {
                return createFromXmlInner(context2, res, parser, attrs, theme2);
            }
            Throwable th2 = th;
            new XmlPullParserException("No start tag found");
            throw th2;
        } catch (XmlPullParserException e) {
            int e2 = Log.e(LOGTAG, "parser error", e);
            return null;
        } catch (IOException e3) {
            int e4 = Log.e(LOGTAG, "parser error", e3);
            return null;
        }
    }

    public static AnimatedStateListDrawableCompat createFromXmlInner(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws IOException, XmlPullParserException {
        AnimatedStateListDrawableCompat animatedStateListDrawableCompat;
        Throwable th;
        StringBuilder sb;
        Context context2 = context;
        Resources resources2 = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        String name = parser.getName();
        if (!name.equals("animated-selector")) {
            Throwable th2 = th;
            new StringBuilder();
            new XmlPullParserException(sb.append(parser.getPositionDescription()).append(": invalid animated-selector tag ").append(name).toString());
            throw th2;
        }
        new AnimatedStateListDrawableCompat();
        AnimatedStateListDrawableCompat asl = animatedStateListDrawableCompat;
        asl.inflate(context2, resources2, parser, attrs, theme2);
        return asl;
    }

    public void inflate(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser parser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        Resources resources2 = resources;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        TypedArray a = TypedArrayUtils.obtainAttributes(resources2, theme2, attrs, C0425R.styleable.AnimatedStateListDrawableCompat);
        boolean visible = setVisible(a.getBoolean(C0425R.styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
        updateStateFromTypedArray(a);
        updateDensity(resources2);
        a.recycle();
        inflateChildElements(context, resources2, parser, attrs, theme2);
        init();
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = z;
        boolean restart = z2;
        boolean changed = super.setVisible(visible, restart);
        if (this.mTransition != null && (changed || restart)) {
            if (visible) {
                this.mTransition.start();
            } else {
                jumpToCurrentState();
            }
        }
        return changed;
    }

    public void addState(@NonNull int[] iArr, @NonNull Drawable drawable, int i) {
        Throwable th;
        int[] stateSet = iArr;
        Drawable drawable2 = drawable;
        int id = i;
        if (drawable2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Drawable must not be null");
            throw th2;
        }
        int addStateSet = this.mState.addStateSet(stateSet, drawable2, id);
        boolean onStateChange = onStateChange(getState());
    }

    public <T extends Drawable & Animatable> void addTransition(int i, int i2, @NonNull T t, boolean z) {
        Throwable th;
        int fromId = i;
        int toId = i2;
        T transition = t;
        boolean reversible = z;
        if (transition == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Transition drawable must not be null");
            throw th2;
        }
        int addTransition = this.mState.addTransition(fromId, toId, transition, reversible);
    }

    public boolean isStateful() {
        return true;
    }

    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        if (this.mTransition != null) {
            this.mTransition.stop();
            this.mTransition = null;
            boolean selectDrawable = selectDrawable(this.mTransitionToIndex);
            this.mTransitionToIndex = -1;
            this.mTransitionFromIndex = -1;
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int[] stateSet = iArr;
        int targetIndex = this.mState.indexOfKeyframe(stateSet);
        boolean changed = targetIndex != getCurrentIndex() && (selectTransition(targetIndex) || selectDrawable(targetIndex));
        Drawable current = getCurrent();
        if (current != null) {
            changed |= current.setState(stateSet);
        }
        return changed;
    }

    private boolean selectTransition(int i) {
        int fromIndex;
        Transition transition;
        Transition transition2;
        Transition transition3;
        Transition transition4;
        int toIndex = i;
        Transition currentTransition = this.mTransition;
        if (currentTransition == null) {
            fromIndex = getCurrentIndex();
        } else if (toIndex == this.mTransitionToIndex) {
            return true;
        } else {
            if (toIndex != this.mTransitionFromIndex || !currentTransition.canReverse()) {
                fromIndex = this.mTransitionToIndex;
                currentTransition.stop();
            } else {
                currentTransition.reverse();
                this.mTransitionToIndex = this.mTransitionFromIndex;
                this.mTransitionFromIndex = toIndex;
                return true;
            }
        }
        this.mTransition = null;
        this.mTransitionFromIndex = -1;
        this.mTransitionToIndex = -1;
        AnimatedStateListState state = this.mState;
        int fromId = state.getKeyframeIdAt(fromIndex);
        int toId = state.getKeyframeIdAt(toIndex);
        if (toId == 0 || fromId == 0) {
            return false;
        }
        int transitionIndex = state.indexOfTransition(fromId, toId);
        if (transitionIndex < 0) {
            return false;
        }
        boolean hasReversibleFlag = state.transitionHasReversibleFlag(fromId, toId);
        boolean selectDrawable = selectDrawable(transitionIndex);
        Drawable d = getCurrent();
        if (d instanceof AnimationDrawable) {
            new AnimationDrawableTransition((AnimationDrawable) d, state.isTransitionReversed(fromId, toId), hasReversibleFlag);
            transition2 = transition4;
        } else if (d instanceof AnimatedVectorDrawableCompat) {
            new AnimatedVectorDrawableTransition((AnimatedVectorDrawableCompat) d);
            transition2 = transition3;
        } else if (!(d instanceof Animatable)) {
            return false;
        } else {
            new AnimatableTransition((Animatable) d);
            transition2 = transition;
        }
        transition2.start();
        this.mTransition = transition2;
        this.mTransitionFromIndex = fromIndex;
        this.mTransitionToIndex = toIndex;
        return true;
    }

    /* renamed from: android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat$Transition */
    private static abstract class Transition {
        public abstract void start();

        public abstract void stop();

        private Transition() {
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Transition(C04311 r4) {
            this();
            C04311 r1 = r4;
        }

        public void reverse() {
        }

        public boolean canReverse() {
            return false;
        }
    }

    /* renamed from: android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat$AnimatableTransition */
    private static class AnimatableTransition extends Transition {

        /* renamed from: mA */
        private final Animatable f39mA;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnimatableTransition(Animatable a) {
            super((C04311) null);
            this.f39mA = a;
        }

        public void start() {
            this.f39mA.start();
        }

        public void stop() {
            this.f39mA.stop();
        }
    }

    /* renamed from: android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat$AnimationDrawableTransition */
    private static class AnimationDrawableTransition extends Transition {
        private final ObjectAnimator mAnim;
        private final boolean mHasReversibleFlag;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnimationDrawableTransition(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super((C04311) null);
            FrameInterpolator frameInterpolator;
            AnimationDrawable ad = animationDrawable;
            boolean reversed = z;
            boolean hasReversibleFlag = z2;
            int frameCount = ad.getNumberOfFrames();
            int fromFrame = reversed ? frameCount - 1 : 0;
            int toFrame = reversed ? 0 : frameCount - 1;
            new FrameInterpolator(ad, reversed);
            FrameInterpolator interp = frameInterpolator;
            int[] iArr = new int[2];
            iArr[0] = fromFrame;
            int[] iArr2 = iArr;
            iArr2[1] = toFrame;
            ObjectAnimator anim = ObjectAnimator.ofInt(ad, "currentIndex", iArr2);
            if (Build.VERSION.SDK_INT >= 18) {
                anim.setAutoCancel(true);
            }
            ObjectAnimator duration = anim.setDuration((long) interp.getTotalDuration());
            anim.setInterpolator(interp);
            this.mHasReversibleFlag = hasReversibleFlag;
            this.mAnim = anim;
        }

        public boolean canReverse() {
            return this.mHasReversibleFlag;
        }

        public void start() {
            this.mAnim.start();
        }

        public void reverse() {
            this.mAnim.reverse();
        }

        public void stop() {
            this.mAnim.cancel();
        }
    }

    /* renamed from: android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat$AnimatedVectorDrawableTransition */
    private static class AnimatedVectorDrawableTransition extends Transition {
        private final AnimatedVectorDrawableCompat mAvd;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnimatedVectorDrawableTransition(AnimatedVectorDrawableCompat avd) {
            super((C04311) null);
            this.mAvd = avd;
        }

        public void start() {
            this.mAvd.start();
        }

        public void stop() {
            this.mAvd.stop();
        }
    }

    private void updateStateFromTypedArray(TypedArray typedArray) {
        TypedArray a = typedArray;
        AnimatedStateListState state = this.mState;
        if (Build.VERSION.SDK_INT >= 21) {
            state.mChangingConfigurations |= a.getChangingConfigurations();
        }
        state.setVariablePadding(a.getBoolean(C0425R.styleable.AnimatedStateListDrawableCompat_android_variablePadding, state.mVariablePadding));
        state.setConstantSize(a.getBoolean(C0425R.styleable.AnimatedStateListDrawableCompat_android_constantSize, state.mConstantSize));
        state.setEnterFadeDuration(a.getInt(C0425R.styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, state.mEnterFadeDuration));
        state.setExitFadeDuration(a.getInt(C0425R.styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, state.mExitFadeDuration));
        setDither(a.getBoolean(C0425R.styleable.AnimatedStateListDrawableCompat_android_dither, state.mDither));
    }

    private void init() {
        boolean onStateChange = onStateChange(getState());
    }

    private void inflateChildElements(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        Context context2 = context;
        Resources resources2 = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int next = parser.next();
            int type = next;
            if (next != 1) {
                int depth = parser.getDepth();
                int depth2 = depth;
                if (depth < innerDepth && type == 3) {
                    return;
                }
                if (type == 2 && depth2 <= innerDepth) {
                    if (parser.getName().equals(ELEMENT_ITEM)) {
                        int parseItem = parseItem(context2, resources2, parser, attrs, theme2);
                    } else if (parser.getName().equals(ELEMENT_TRANSITION)) {
                        int parseTransition = parseTransition(context2, resources2, parser, attrs, theme2);
                    }
                }
            } else {
                return;
            }
        }
    }

    private int parseTransition(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        int next;
        int type;
        Throwable th3;
        StringBuilder sb3;
        Context context2 = context;
        Resources resources2 = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        TypedArray a = TypedArrayUtils.obtainAttributes(resources2, theme2, attrs, C0425R.styleable.AnimatedStateListDrawableTransition);
        int fromId = a.getResourceId(C0425R.styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
        int toId = a.getResourceId(C0425R.styleable.AnimatedStateListDrawableTransition_android_toId, -1);
        Drawable dr = null;
        int drawableId = a.getResourceId(C0425R.styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
        if (drawableId > 0) {
            dr = AppCompatResources.getDrawable(context2, drawableId);
        }
        boolean reversible = a.getBoolean(C0425R.styleable.AnimatedStateListDrawableTransition_android_reversible, false);
        a.recycle();
        if (dr == null) {
            do {
                next = parser.next();
                type = next;
            } while (next == 4);
            if (type != 2) {
                Throwable th4 = th3;
                new StringBuilder();
                new XmlPullParserException(sb3.append(parser.getPositionDescription()).append(TRANSITION_MISSING_DRAWABLE_ERROR).toString());
                throw th4;
            } else if (parser.getName().equals("animated-vector")) {
                dr = AnimatedVectorDrawableCompat.createFromXmlInner(context2, resources2, parser, attrs, theme2);
            } else if (Build.VERSION.SDK_INT >= 21) {
                dr = Drawable.createFromXmlInner(resources2, parser, attrs, theme2);
            } else {
                dr = Drawable.createFromXmlInner(resources2, parser, attrs);
            }
        }
        if (dr == null) {
            Throwable th5 = th2;
            new StringBuilder();
            new XmlPullParserException(sb2.append(parser.getPositionDescription()).append(TRANSITION_MISSING_DRAWABLE_ERROR).toString());
            throw th5;
        } else if (fromId != -1 && toId != -1) {
            return this.mState.addTransition(fromId, toId, dr, reversible);
        } else {
            Throwable th6 = th;
            new StringBuilder();
            new XmlPullParserException(sb.append(parser.getPositionDescription()).append(TRANSITION_MISSING_FROM_TO_ID).toString());
            throw th6;
        }
    }

    private int parseItem(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        Throwable th;
        StringBuilder sb;
        int next;
        int type;
        Throwable th2;
        StringBuilder sb2;
        Context context2 = context;
        Resources resources2 = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        TypedArray a = TypedArrayUtils.obtainAttributes(resources2, theme2, attrs, C0425R.styleable.AnimatedStateListDrawableItem);
        int keyframeId = a.getResourceId(C0425R.styleable.AnimatedStateListDrawableItem_android_id, 0);
        Drawable dr = null;
        int drawableId = a.getResourceId(C0425R.styleable.AnimatedStateListDrawableItem_android_drawable, -1);
        if (drawableId > 0) {
            dr = AppCompatResources.getDrawable(context2, drawableId);
        }
        a.recycle();
        int[] states = extractStateSet(attrs);
        if (dr == null) {
            do {
                next = parser.next();
                type = next;
            } while (next == 4);
            if (type != 2) {
                Throwable th3 = th2;
                new StringBuilder();
                new XmlPullParserException(sb2.append(parser.getPositionDescription()).append(ITEM_MISSING_DRAWABLE_ERROR).toString());
                throw th3;
            } else if (parser.getName().equals("vector")) {
                dr = VectorDrawableCompat.createFromXmlInner(resources2, parser, attrs, theme2);
            } else if (Build.VERSION.SDK_INT >= 21) {
                dr = Drawable.createFromXmlInner(resources2, parser, attrs, theme2);
            } else {
                dr = Drawable.createFromXmlInner(resources2, parser, attrs);
            }
        }
        if (dr != null) {
            return this.mState.addStateSet(states, dr, keyframeId);
        }
        Throwable th4 = th;
        new StringBuilder();
        new XmlPullParserException(sb.append(parser.getPositionDescription()).append(ITEM_MISSING_DRAWABLE_ERROR).toString());
        throw th4;
    }

    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mState.mutate();
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public AnimatedStateListState cloneConstantState() {
        AnimatedStateListState animatedStateListState;
        new AnimatedStateListState(this.mState, this, (Resources) null);
        return animatedStateListState;
    }

    /* access modifiers changed from: package-private */
    public void clearMutated() {
        super.clearMutated();
        this.mMutated = false;
    }

    /* renamed from: android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat$AnimatedStateListState */
    static class AnimatedStateListState extends StateListDrawable.StateListState {
        private static final long REVERSED_BIT = 4294967296L;
        private static final long REVERSIBLE_FLAG_BIT = 8589934592L;
        C1651SparseArrayCompat<Integer> mStateIds;
        C1647LongSparseArray<Long> mTransitions;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        AnimatedStateListState(@android.support.annotation.Nullable android.support.p003v7.graphics.drawable.AnimatedStateListDrawableCompat.AnimatedStateListState r10, @android.support.annotation.NonNull android.support.p003v7.graphics.drawable.AnimatedStateListDrawableCompat r11, @android.support.annotation.Nullable android.content.res.Resources r12) {
            /*
                r9 = this;
                r0 = r9
                r1 = r10
                r2 = r11
                r3 = r12
                r4 = r0
                r5 = r1
                r6 = r2
                r7 = r3
                r4.<init>(r5, r6, r7)
                r4 = r1
                if (r4 == 0) goto L_0x001b
                r4 = r0
                r5 = r1
                android.support.v4.util.LongSparseArray<java.lang.Long> r5 = r5.mTransitions
                r4.mTransitions = r5
                r4 = r0
                r5 = r1
                android.support.v4.util.SparseArrayCompat<java.lang.Integer> r5 = r5.mStateIds
                r4.mStateIds = r5
            L_0x001a:
                return
            L_0x001b:
                r4 = r0
                android.support.v4.util.LongSparseArray r5 = new android.support.v4.util.LongSparseArray
                r8 = r5
                r5 = r8
                r6 = r8
                r6.<init>()
                r4.mTransitions = r5
                r4 = r0
                android.support.v4.util.SparseArrayCompat r5 = new android.support.v4.util.SparseArrayCompat
                r8 = r5
                r5 = r8
                r6 = r8
                r6.<init>()
                r4.mStateIds = r5
                goto L_0x001a
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.graphics.drawable.AnimatedStateListDrawableCompat.AnimatedStateListState.<init>(android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat$AnimatedStateListState, android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat, android.content.res.Resources):void");
        }

        /* access modifiers changed from: package-private */
        public void mutate() {
            this.mTransitions = this.mTransitions.clone();
            this.mStateIds = this.mStateIds.clone();
        }

        /* access modifiers changed from: package-private */
        public int addTransition(int i, int i2, @NonNull Drawable anim, boolean z) {
            int fromId = i;
            int toId = i2;
            boolean reversible = z;
            int pos = super.addChild(anim);
            long keyFromTo = generateTransitionKey(fromId, toId);
            long reversibleBit = 0;
            if (reversible) {
                reversibleBit = 8589934592L;
            }
            this.mTransitions.append(keyFromTo, Long.valueOf(((long) pos) | reversibleBit));
            if (reversible) {
                this.mTransitions.append(generateTransitionKey(toId, fromId), Long.valueOf(((long) pos) | 4294967296L | reversibleBit));
            }
            return pos;
        }

        /* access modifiers changed from: package-private */
        public int addStateSet(@NonNull int[] stateSet, @NonNull Drawable drawable, int id) {
            int index = super.addStateSet(stateSet, drawable);
            this.mStateIds.put(index, Integer.valueOf(id));
            return index;
        }

        /* access modifiers changed from: package-private */
        public int indexOfKeyframe(@NonNull int[] stateSet) {
            int index = super.indexOfStateSet(stateSet);
            if (index >= 0) {
                return index;
            }
            return super.indexOfStateSet(StateSet.WILD_CARD);
        }

        /* access modifiers changed from: package-private */
        public int getKeyframeIdAt(int i) {
            int index = i;
            return index < 0 ? 0 : this.mStateIds.get(index, 0).intValue();
        }

        /* access modifiers changed from: package-private */
        public int indexOfTransition(int fromId, int toId) {
            return (int) this.mTransitions.get(generateTransitionKey(fromId, toId), -1L).longValue();
        }

        /* access modifiers changed from: package-private */
        public boolean isTransitionReversed(int fromId, int toId) {
            return (this.mTransitions.get(generateTransitionKey(fromId, toId), -1L).longValue() & 4294967296L) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean transitionHasReversibleFlag(int fromId, int toId) {
            return (this.mTransitions.get(generateTransitionKey(fromId, toId), -1L).longValue() & 8589934592L) != 0;
        }

        @NonNull
        public Drawable newDrawable() {
            Drawable drawable;
            new AnimatedStateListDrawableCompat(this, (Resources) null);
            return drawable;
        }

        @NonNull
        public Drawable newDrawable(Resources res) {
            Drawable drawable;
            new AnimatedStateListDrawableCompat(this, res);
            return drawable;
        }

        private static long generateTransitionKey(int fromId, int toId) {
            return (((long) fromId) << 32) | ((long) toId);
        }
    }

    /* access modifiers changed from: protected */
    public void setConstantState(@NonNull DrawableContainer.DrawableContainerState drawableContainerState) {
        DrawableContainer.DrawableContainerState state = drawableContainerState;
        super.setConstantState(state);
        if (state instanceof AnimatedStateListState) {
            this.mState = (AnimatedStateListState) state;
        }
    }

    /* renamed from: android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat$FrameInterpolator */
    private static class FrameInterpolator implements TimeInterpolator {
        private int[] mFrameTimes;
        private int mFrames;
        private int mTotalDuration;

        FrameInterpolator(AnimationDrawable d, boolean reversed) {
            int updateFrames = updateFrames(d, reversed);
        }

        /* access modifiers changed from: package-private */
        public int updateFrames(AnimationDrawable animationDrawable, boolean z) {
            AnimationDrawable d = animationDrawable;
            boolean reversed = z;
            int frameCount = d.getNumberOfFrames();
            this.mFrames = frameCount;
            if (this.mFrameTimes == null || this.mFrameTimes.length < frameCount) {
                this.mFrameTimes = new int[frameCount];
            }
            int[] frameTimes = this.mFrameTimes;
            int totalDuration = 0;
            for (int i = 0; i < frameCount; i++) {
                int duration = d.getDuration(reversed ? (frameCount - i) - 1 : i);
                frameTimes[i] = duration;
                totalDuration += duration;
            }
            this.mTotalDuration = totalDuration;
            return totalDuration;
        }

        /* access modifiers changed from: package-private */
        public int getTotalDuration() {
            return this.mTotalDuration;
        }

        public float getInterpolation(float input) {
            float frameElapsed;
            int elapsed = (int) ((input * ((float) this.mTotalDuration)) + 0.5f);
            int frameCount = this.mFrames;
            int[] frameTimes = this.mFrameTimes;
            int remaining = elapsed;
            int i = 0;
            while (i < frameCount && remaining >= frameTimes[i]) {
                remaining -= frameTimes[i];
                i++;
            }
            if (i < frameCount) {
                frameElapsed = ((float) remaining) / ((float) this.mTotalDuration);
            } else {
                frameElapsed = 0.0f;
            }
            return (((float) i) / ((float) frameCount)) + frameElapsed;
        }
    }
}
