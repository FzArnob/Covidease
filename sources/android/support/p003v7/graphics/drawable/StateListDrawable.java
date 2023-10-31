package android.support.p003v7.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.res.TypedArrayUtils;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.graphics.drawable.DrawableContainer;
import android.util.AttributeSet;
import android.util.StateSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.graphics.drawable.StateListDrawable */
class StateListDrawable extends DrawableContainer {
    private static final boolean DEBUG = false;
    private static final String TAG = "StateListDrawable";
    private boolean mMutated;
    private StateListState mStateListState;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    StateListDrawable() {
        this((StateListState) null, (Resources) null);
    }

    public void addState(int[] iArr, Drawable drawable) {
        int[] stateSet = iArr;
        Drawable drawable2 = drawable;
        if (drawable2 != null) {
            int addStateSet = this.mStateListState.addStateSet(stateSet, drawable2);
            boolean onStateChange = onStateChange(getState());
        }
    }

    public boolean isStateful() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        int[] stateSet = iArr;
        boolean changed = super.onStateChange(stateSet);
        int idx = this.mStateListState.indexOfStateSet(stateSet);
        if (idx < 0) {
            idx = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD);
        }
        return selectDrawable(idx) || changed;
    }

    public void inflate(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser parser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        Resources r = resources;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        TypedArray a = TypedArrayUtils.obtainAttributes(r, theme2, attrs, C0425R.styleable.StateListDrawable);
        boolean visible = setVisible(a.getBoolean(C0425R.styleable.StateListDrawable_android_visible, true), true);
        updateStateFromTypedArray(a);
        updateDensity(r);
        a.recycle();
        inflateChildElements(context, r, parser, attrs, theme2);
        boolean onStateChange = onStateChange(getState());
    }

    private void updateStateFromTypedArray(TypedArray typedArray) {
        TypedArray a = typedArray;
        StateListState state = this.mStateListState;
        if (Build.VERSION.SDK_INT >= 21) {
            state.mChangingConfigurations |= a.getChangingConfigurations();
        }
        state.mVariablePadding = a.getBoolean(C0425R.styleable.StateListDrawable_android_variablePadding, state.mVariablePadding);
        state.mConstantSize = a.getBoolean(C0425R.styleable.StateListDrawable_android_constantSize, state.mConstantSize);
        state.mEnterFadeDuration = a.getInt(C0425R.styleable.StateListDrawable_android_enterFadeDuration, state.mEnterFadeDuration);
        state.mExitFadeDuration = a.getInt(C0425R.styleable.StateListDrawable_android_exitFadeDuration, state.mExitFadeDuration);
        state.mDither = a.getBoolean(C0425R.styleable.StateListDrawable_android_dither, state.mDither);
    }

    private void inflateChildElements(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        int type;
        Throwable th;
        StringBuilder sb;
        Context context2 = context;
        Resources r = resources;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Resources.Theme theme2 = theme;
        StateListState state = this.mStateListState;
        int innerDepth = parser.getDepth() + 1;
        while (true) {
            int next2 = parser.next();
            int type2 = next2;
            if (next2 != 1) {
                int depth = parser.getDepth();
                int depth2 = depth;
                if (depth < innerDepth && type2 == 3) {
                    return;
                }
                if (type2 == 2 && depth2 <= innerDepth && parser.getName().equals("item")) {
                    TypedArray a = TypedArrayUtils.obtainAttributes(r, theme2, attrs, C0425R.styleable.StateListDrawableItem);
                    Drawable dr = null;
                    int drawableId = a.getResourceId(C0425R.styleable.StateListDrawableItem_android_drawable, -1);
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
                            Throwable th2 = th;
                            new StringBuilder();
                            new XmlPullParserException(sb.append(parser.getPositionDescription()).append(": <item> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
                            throw th2;
                        } else if (Build.VERSION.SDK_INT >= 21) {
                            dr = Drawable.createFromXmlInner(r, parser, attrs, theme2);
                        } else {
                            dr = Drawable.createFromXmlInner(r, parser, attrs);
                        }
                    }
                    int addStateSet = state.addStateSet(states, dr);
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int[] extractStateSet(AttributeSet attributeSet) {
        int i;
        AttributeSet attrs = attributeSet;
        int j = 0;
        int numAttrs = attrs.getAttributeCount();
        int[] states = new int[numAttrs];
        for (int i2 = 0; i2 < numAttrs; i2++) {
            int stateResId = attrs.getAttributeNameResource(i2);
            switch (stateResId) {
                case 0:
                case 16842960:
                case 16843161:
                    break;
                default:
                    int[] iArr = states;
                    int i3 = j;
                    j++;
                    if (attrs.getAttributeBooleanValue(i2, false)) {
                        i = stateResId;
                    } else {
                        i = -stateResId;
                    }
                    iArr[i3] = i;
                    break;
            }
        }
        return StateSet.trimStateSet(states, j);
    }

    /* access modifiers changed from: package-private */
    public StateListState getStateListState() {
        return this.mStateListState;
    }

    /* access modifiers changed from: package-private */
    public int getStateCount() {
        return this.mStateListState.getChildCount();
    }

    /* access modifiers changed from: package-private */
    public int[] getStateSet(int index) {
        return this.mStateListState.mStateSets[index];
    }

    /* access modifiers changed from: package-private */
    public Drawable getStateDrawable(int index) {
        return this.mStateListState.getChild(index);
    }

    /* access modifiers changed from: package-private */
    public int getStateDrawableIndex(int[] stateSet) {
        return this.mStateListState.indexOfStateSet(stateSet);
    }

    @NonNull
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mStateListState.mutate();
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public StateListState cloneConstantState() {
        StateListState stateListState;
        new StateListState(this.mStateListState, this, (Resources) null);
        return stateListState;
    }

    /* access modifiers changed from: package-private */
    public void clearMutated() {
        super.clearMutated();
        this.mMutated = false;
    }

    /* renamed from: android.support.v7.graphics.drawable.StateListDrawable$StateListState */
    static class StateListState extends DrawableContainer.DrawableContainerState {
        int[][] mStateSets;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        StateListState(android.support.p003v7.graphics.drawable.StateListDrawable.StateListState r9, android.support.p003v7.graphics.drawable.StateListDrawable r10, android.content.res.Resources r11) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r3 = r11
                r4 = r0
                r5 = r1
                r6 = r2
                r7 = r3
                r4.<init>(r5, r6, r7)
                r4 = r1
                if (r4 == 0) goto L_0x0015
                r4 = r0
                r5 = r1
                int[][] r5 = r5.mStateSets
                r4.mStateSets = r5
            L_0x0014:
                return
            L_0x0015:
                r4 = r0
                r5 = r0
                int r5 = r5.getCapacity()
                int[][] r5 = new int[r5][]
                r4.mStateSets = r5
                goto L_0x0014
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.graphics.drawable.StateListDrawable.StateListState.<init>(android.support.v7.graphics.drawable.StateListDrawable$StateListState, android.support.v7.graphics.drawable.StateListDrawable, android.content.res.Resources):void");
        }

        /* access modifiers changed from: package-private */
        public void mutate() {
            int[][] stateSets = new int[this.mStateSets.length][];
            for (int i = this.mStateSets.length - 1; i >= 0; i--) {
                stateSets[i] = this.mStateSets[i] != null ? (int[]) this.mStateSets[i].clone() : null;
            }
            this.mStateSets = stateSets;
        }

        /* access modifiers changed from: package-private */
        public int addStateSet(int[] stateSet, Drawable drawable) {
            int pos = addChild(drawable);
            this.mStateSets[pos] = stateSet;
            return pos;
        }

        /* access modifiers changed from: package-private */
        public int indexOfStateSet(int[] iArr) {
            int[] stateSet = iArr;
            int[][] stateSets = this.mStateSets;
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                if (StateSet.stateSetMatches(stateSets[i], stateSet)) {
                    return i;
                }
            }
            return -1;
        }

        @NonNull
        public Drawable newDrawable() {
            Drawable drawable;
            new StateListDrawable(this, (Resources) null);
            return drawable;
        }

        @NonNull
        public Drawable newDrawable(Resources res) {
            Drawable drawable;
            new StateListDrawable(this, res);
            return drawable;
        }

        public void growArray(int i, int i2) {
            int oldSize = i;
            int newSize = i2;
            super.growArray(oldSize, newSize);
            int[][] newStateSets = new int[newSize][];
            System.arraycopy(this.mStateSets, 0, newStateSets, 0, oldSize);
            this.mStateSets = newStateSets;
        }
    }

    @RequiresApi(21)
    public void applyTheme(@NonNull Resources.Theme theme) {
        super.applyTheme(theme);
        boolean onStateChange = onStateChange(getState());
    }

    /* access modifiers changed from: protected */
    public void setConstantState(@NonNull DrawableContainer.DrawableContainerState drawableContainerState) {
        DrawableContainer.DrawableContainerState state = drawableContainerState;
        super.setConstantState(state);
        if (state instanceof StateListState) {
            this.mStateListState = (StateListState) state;
        }
    }

    StateListDrawable(StateListState state, Resources res) {
        DrawableContainer.DrawableContainerState drawableContainerState;
        new StateListState(state, this, res);
        setConstantState(drawableContainerState);
        boolean onStateChange = onStateChange(getState());
    }

    StateListDrawable(@Nullable StateListState stateListState) {
        StateListState state = stateListState;
        if (state != null) {
            setConstantState(state);
        }
    }
}
