package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p000v4.widget.AutoScrollHelper;
import android.support.p000v4.widget.ListViewAutoScrollHelper;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import gnu.expr.Declaration;
import java.lang.reflect.Field;

/* renamed from: android.support.v7.widget.DropDownListView */
class DropDownListView extends ListView {
    public static final int INVALID_POSITION = -1;
    public static final int NO_POSITION = -1;
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private Field mIsChildViewEnabled;
    private boolean mListSelectionHidden;
    private int mMotionPosition;
    ResolveHoverRunnable mResolveHoverRunnable;
    private ListViewAutoScrollHelper mScrollHelper;
    private int mSelectionBottomPadding = 0;
    private int mSelectionLeftPadding = 0;
    private int mSelectionRightPadding = 0;
    private int mSelectionTopPadding = 0;
    private GateKeeperDrawable mSelector;
    private final Rect mSelectorRect;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DropDownListView(Context context, boolean hijackFocus) {
        super(context, (AttributeSet) null, C0425R.attr.dropDownListViewStyle);
        Rect rect;
        new Rect();
        this.mSelectorRect = rect;
        this.mHijackFocus = hijackFocus;
        setCacheColorHint(0);
        try {
            this.mIsChildViewEnabled = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.mIsChildViewEnabled.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public boolean isInTouchMode() {
        return (this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode();
    }

    public boolean hasWindowFocus() {
        return this.mHijackFocus || super.hasWindowFocus();
    }

    public boolean isFocused() {
        return this.mHijackFocus || super.isFocused();
    }

    public boolean hasFocus() {
        return this.mHijackFocus || super.hasFocus();
    }

    public void setSelector(Drawable drawable) {
        GateKeeperDrawable gateKeeperDrawable;
        Rect rect;
        GateKeeperDrawable gateKeeperDrawable2;
        Drawable sel = drawable;
        if (sel != null) {
            gateKeeperDrawable = gateKeeperDrawable2;
            new GateKeeperDrawable(sel);
        } else {
            gateKeeperDrawable = null;
        }
        this.mSelector = gateKeeperDrawable;
        super.setSelector(this.mSelector);
        new Rect();
        Rect padding = rect;
        if (sel != null) {
            boolean padding2 = sel.getPadding(padding);
        }
        this.mSelectionLeftPadding = padding.left;
        this.mSelectionTopPadding = padding.top;
        this.mSelectionRightPadding = padding.right;
        this.mSelectionBottomPadding = padding.bottom;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        if (this.mResolveHoverRunnable == null) {
            super.drawableStateChanged();
            setSelectorEnabled(true);
            updateSelectorStateCompat();
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        drawSelectorCompat(canvas2);
        super.dispatchDraw(canvas2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        switch (ev.getAction()) {
            case 0:
                this.mMotionPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
                break;
        }
        if (this.mResolveHoverRunnable != null) {
            this.mResolveHoverRunnable.cancel();
        }
        return super.onTouchEvent(ev);
    }

    public int lookForSelectablePosition(int i, boolean z) {
        int position;
        int position2 = i;
        boolean lookDown = z;
        ListAdapter adapter = getAdapter();
        if (adapter == null || isInTouchMode()) {
            return -1;
        }
        int count = adapter.getCount();
        if (!getAdapter().areAllItemsEnabled()) {
            if (lookDown) {
                position = Math.max(0, position2);
                while (position < count && !adapter.isEnabled(position)) {
                    position++;
                }
            } else {
                int position3 = Math.min(position2, count - 1);
                while (position >= 0 && !adapter.isEnabled(position)) {
                    position3 = position - 1;
                }
            }
            if (position < 0 || position >= count) {
                return -1;
            }
            return position;
        } else if (position2 < 0 || position2 >= count) {
            return -1;
        } else {
            return position2;
        }
    }

    public int measureHeightOfChildrenCompat(int i, int i2, int i3, int i4, int i5) {
        int heightMeasureSpec;
        int i6;
        int widthMeasureSpec = i;
        int i7 = i2;
        int i8 = i3;
        int maxHeight = i4;
        int disallowPartialChildPosition = i5;
        int paddingTop = getListPaddingTop();
        int paddingBottom = getListPaddingBottom();
        int listPaddingLeft = getListPaddingLeft();
        int listPaddingRight = getListPaddingRight();
        int reportedDividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return paddingTop + paddingBottom;
        }
        int returnedHeight = paddingTop + paddingBottom;
        int dividerHeight = (reportedDividerHeight <= 0 || divider == null) ? 0 : reportedDividerHeight;
        int prevHeightWithoutPartialChild = 0;
        View child = null;
        int viewType = 0;
        int count = adapter.getCount();
        for (int i9 = 0; i9 < count; i9++) {
            int newType = adapter.getItemViewType(i9);
            if (newType != viewType) {
                child = null;
                viewType = newType;
            }
            child = adapter.getView(i9, child, this);
            ViewGroup.LayoutParams childLp = child.getLayoutParams();
            if (childLp == null) {
                childLp = generateDefaultLayoutParams();
                child.setLayoutParams(childLp);
            }
            if (childLp.height > 0) {
                heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(childLp.height, Declaration.MODULE_REFERENCE);
            } else {
                heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            }
            child.measure(widthMeasureSpec, heightMeasureSpec);
            child.forceLayout();
            if (i9 > 0) {
                returnedHeight += dividerHeight;
            }
            returnedHeight += child.getMeasuredHeight();
            if (returnedHeight >= maxHeight) {
                if (disallowPartialChildPosition < 0 || i9 <= disallowPartialChildPosition || prevHeightWithoutPartialChild <= 0 || returnedHeight == maxHeight) {
                    i6 = maxHeight;
                } else {
                    i6 = prevHeightWithoutPartialChild;
                }
                return i6;
            }
            if (disallowPartialChildPosition >= 0 && i9 >= disallowPartialChildPosition) {
                prevHeightWithoutPartialChild = returnedHeight;
            }
        }
        return returnedHeight;
    }

    private void setSelectorEnabled(boolean z) {
        boolean enabled = z;
        if (this.mSelector != null) {
            this.mSelector.setEnabled(enabled);
        }
    }

    /* renamed from: android.support.v7.widget.DropDownListView$GateKeeperDrawable */
    private static class GateKeeperDrawable extends DrawableWrapper {
        private boolean mEnabled = true;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        GateKeeperDrawable(Drawable drawable) {
            super(drawable);
        }

        /* access modifiers changed from: package-private */
        public void setEnabled(boolean enabled) {
            boolean z = enabled;
            this.mEnabled = z;
        }

        public boolean setState(int[] iArr) {
            int[] stateSet = iArr;
            if (this.mEnabled) {
                return super.setState(stateSet);
            }
            return false;
        }

        public void draw(Canvas canvas) {
            Canvas canvas2 = canvas;
            if (this.mEnabled) {
                super.draw(canvas2);
            }
        }

        public void setHotspot(float f, float f2) {
            float x = f;
            float y = f2;
            if (this.mEnabled) {
                super.setHotspot(x, y);
            }
        }

        public void setHotspotBounds(int i, int i2, int i3, int i4) {
            int left = i;
            int top = i2;
            int right = i3;
            int bottom = i4;
            if (this.mEnabled) {
                super.setHotspotBounds(left, top, right, bottom);
            }
        }

        public boolean setVisible(boolean z, boolean z2) {
            boolean visible = z;
            boolean restart = z2;
            if (this.mEnabled) {
                return super.setVisible(visible, restart);
            }
            return false;
        }
    }

    public boolean onHoverEvent(@NonNull MotionEvent motionEvent) {
        ResolveHoverRunnable resolveHoverRunnable;
        MotionEvent ev = motionEvent;
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(ev);
        }
        int action = ev.getActionMasked();
        if (action == 10 && this.mResolveHoverRunnable == null) {
            new ResolveHoverRunnable(this);
            this.mResolveHoverRunnable = resolveHoverRunnable;
            this.mResolveHoverRunnable.post();
        }
        boolean handled = super.onHoverEvent(ev);
        if (action == 9 || action == 7) {
            int position = pointToPosition((int) ev.getX(), (int) ev.getY());
            if (!(position == -1 || position == getSelectedItemPosition())) {
                View hoveredItem = getChildAt(position - getFirstVisiblePosition());
                if (hoveredItem.isEnabled()) {
                    setSelectionFromTop(position, hoveredItem.getTop() - getTop());
                }
                updateSelectorStateCompat();
            }
        } else {
            setSelection(-1);
        }
        return handled;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.mResolveHoverRunnable = null;
        super.onDetachedFromWindow();
    }

    public boolean onForwardedEvent(MotionEvent motionEvent, int i) {
        ListViewAutoScrollHelper listViewAutoScrollHelper;
        MotionEvent event = motionEvent;
        int activePointerId = i;
        boolean handledEvent = true;
        boolean clearPressedItem = false;
        int actionMasked = event.getActionMasked();
        switch (actionMasked) {
            case 1:
                handledEvent = false;
                break;
            case 2:
                break;
            case 3:
                handledEvent = false;
                break;
        }
        int activeIndex = event.findPointerIndex(activePointerId);
        if (activeIndex < 0) {
            handledEvent = false;
        } else {
            int x = (int) event.getX(activeIndex);
            int y = (int) event.getY(activeIndex);
            int position = pointToPosition(x, y);
            if (position == -1) {
                clearPressedItem = true;
            } else {
                View child = getChildAt(position - getFirstVisiblePosition());
                setPressedItem(child, position, (float) x, (float) y);
                handledEvent = true;
                if (actionMasked == 1) {
                    clickPressedItem(child, position);
                }
            }
        }
        if (!handledEvent || clearPressedItem) {
            clearPressedItem();
        }
        if (handledEvent) {
            if (this.mScrollHelper == null) {
                new ListViewAutoScrollHelper(this);
                this.mScrollHelper = listViewAutoScrollHelper;
            }
            AutoScrollHelper enabled = this.mScrollHelper.setEnabled(true);
            boolean onTouch = this.mScrollHelper.onTouch(this, event);
        } else if (this.mScrollHelper != null) {
            AutoScrollHelper enabled2 = this.mScrollHelper.setEnabled(false);
        }
        return handledEvent;
    }

    private void clickPressedItem(View child, int i) {
        int position = i;
        boolean performItemClick = performItemClick(child, position, getItemIdAtPosition(position));
    }

    /* access modifiers changed from: package-private */
    public void setListSelectionHidden(boolean hideListSelection) {
        boolean z = hideListSelection;
        this.mListSelectionHidden = z;
    }

    private void updateSelectorStateCompat() {
        Drawable selector = getSelector();
        if (selector != null && touchModeDrawsInPressedStateCompat() && isPressed()) {
            boolean state = selector.setState(getDrawableState());
        }
    }

    private void drawSelectorCompat(Canvas canvas) {
        Drawable selector;
        Canvas canvas2 = canvas;
        if (!this.mSelectorRect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(this.mSelectorRect);
            selector.draw(canvas2);
        }
    }

    private void positionSelectorLikeTouchCompat(int i, View sel, float f, float f2) {
        int position = i;
        float x = f;
        float y = f2;
        positionSelectorLikeFocusCompat(position, sel);
        Drawable selector = getSelector();
        if (selector != null && position != -1) {
            DrawableCompat.setHotspot(selector, x, y);
        }
    }

    private void positionSelectorLikeFocusCompat(int i, View view) {
        int position = i;
        View sel = view;
        Drawable selector = getSelector();
        boolean manageState = (selector == null || position == -1) ? false : true;
        if (manageState) {
            boolean visible = selector.setVisible(false, false);
        }
        positionSelectorCompat(position, sel);
        if (manageState) {
            Rect bounds = this.mSelectorRect;
            float x = bounds.exactCenterX();
            float y = bounds.exactCenterY();
            boolean visible2 = selector.setVisible(getVisibility() == 0, false);
            DrawableCompat.setHotspot(selector, x, y);
        }
    }

    private void positionSelectorCompat(int i, View view) {
        int position = i;
        View sel = view;
        Rect selectorRect = this.mSelectorRect;
        selectorRect.set(sel.getLeft(), sel.getTop(), sel.getRight(), sel.getBottom());
        selectorRect.left -= this.mSelectionLeftPadding;
        selectorRect.top -= this.mSelectionTopPadding;
        selectorRect.right += this.mSelectionRightPadding;
        selectorRect.bottom += this.mSelectionBottomPadding;
        try {
            boolean isChildViewEnabled = this.mIsChildViewEnabled.getBoolean(this);
            if (sel.isEnabled() != isChildViewEnabled) {
                this.mIsChildViewEnabled.set(this, Boolean.valueOf(!isChildViewEnabled));
                if (position != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void clearPressedItem() {
        this.mDrawsInPressedState = false;
        setPressed(false);
        drawableStateChanged();
        View motionView = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
        if (motionView != null) {
            motionView.setPressed(false);
        }
        if (this.mClickAnimation != null) {
            this.mClickAnimation.cancel();
            this.mClickAnimation = null;
        }
    }

    private void setPressedItem(View view, int i, float f, float f2) {
        View motionView;
        View child = view;
        int position = i;
        float x = f;
        float y = f2;
        this.mDrawsInPressedState = true;
        if (Build.VERSION.SDK_INT >= 21) {
            drawableHotspotChanged(x, y);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        if (!(this.mMotionPosition == -1 || (motionView = getChildAt(this.mMotionPosition - getFirstVisiblePosition())) == null || motionView == child || !motionView.isPressed())) {
            motionView.setPressed(false);
        }
        this.mMotionPosition = position;
        float childX = x - ((float) child.getLeft());
        float childY = y - ((float) child.getTop());
        if (Build.VERSION.SDK_INT >= 21) {
            child.drawableHotspotChanged(childX, childY);
        }
        if (!child.isPressed()) {
            child.setPressed(true);
        }
        positionSelectorLikeTouchCompat(position, child, x, y);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    private boolean touchModeDrawsInPressedStateCompat() {
        return this.mDrawsInPressedState;
    }

    /* renamed from: android.support.v7.widget.DropDownListView$ResolveHoverRunnable */
    private class ResolveHoverRunnable implements Runnable {
        final /* synthetic */ DropDownListView this$0;

        ResolveHoverRunnable(DropDownListView dropDownListView) {
            this.this$0 = dropDownListView;
        }

        public void run() {
            this.this$0.mResolveHoverRunnable = null;
            this.this$0.drawableStateChanged();
        }

        public void cancel() {
            this.this$0.mResolveHoverRunnable = null;
            boolean removeCallbacks = this.this$0.removeCallbacks(this);
        }

        public void post() {
            boolean post = this.this$0.post(this);
        }
    }
}
