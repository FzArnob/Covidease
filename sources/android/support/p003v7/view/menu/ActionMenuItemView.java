package android.support.p003v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuView;
import android.support.p003v7.widget.ActionMenuView;
import android.support.p003v7.widget.AppCompatTextView;
import android.support.p003v7.widget.ForwardingListener;
import android.support.p003v7.widget.TooltipCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import gnu.expr.Declaration;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.menu.ActionMenuItemView */
public class ActionMenuItemView extends AppCompatTextView implements MenuView.ItemView, View.OnClickListener, ActionMenuView.ActionMenuChildView {
    private static final int MAX_ICON_SIZE = 32;
    private static final String TAG = "ActionMenuItemView";
    private boolean mAllowTextWithIcon;
    private boolean mExpandedFormat;
    private ForwardingListener mForwardingListener;
    private Drawable mIcon;
    MenuItemImpl mItemData;
    MenuBuilder.ItemInvoker mItemInvoker;
    private int mMaxIconSize;
    private int mMinWidth;
    PopupCallback mPopupCallback;
    private int mSavedPaddingLeft;
    private CharSequence mTitle;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ActionMenuItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ActionMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ActionMenuItemView(android.content.Context r13, android.util.AttributeSet r14, int r15) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r7.<init>(r8, r9, r10)
            r7 = r1
            android.content.res.Resources r7 = r7.getResources()
            r4 = r7
            r7 = r0
            r8 = r0
            boolean r8 = r8.shouldAllowTextWithIcon()
            r7.mAllowTextWithIcon = r8
            r7 = r1
            r8 = r2
            int[] r9 = android.support.p003v7.appcompat.C0425R.styleable.ActionMenuItemView
            r10 = r3
            r11 = 0
            android.content.res.TypedArray r7 = r7.obtainStyledAttributes(r8, r9, r10, r11)
            r5 = r7
            r7 = r0
            r8 = r5
            int r9 = android.support.p003v7.appcompat.C0425R.styleable.ActionMenuItemView_android_minWidth
            r10 = 0
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r7.mMinWidth = r8
            r7 = r5
            r7.recycle()
            r7 = r4
            android.util.DisplayMetrics r7 = r7.getDisplayMetrics()
            float r7 = r7.density
            r6 = r7
            r7 = r0
            r8 = 1107296256(0x42000000, float:32.0)
            r9 = r6
            float r8 = r8 * r9
            r9 = 1056964608(0x3f000000, float:0.5)
            float r8 = r8 + r9
            int r8 = (int) r8
            r7.mMaxIconSize = r8
            r7 = r0
            r8 = r0
            r7.setOnClickListener(r8)
            r7 = r0
            r8 = -1
            r7.mSavedPaddingLeft = r8
            r7 = r0
            r8 = 0
            r7.setSaveEnabled(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.view.menu.ActionMenuItemView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mAllowTextWithIcon = shouldAllowTextWithIcon();
        updateTextButtonVisibility();
    }

    private boolean shouldAllowTextWithIcon() {
        Configuration config = getContext().getResources().getConfiguration();
        int widthDp = config.screenWidthDp;
        return widthDp >= 480 || (widthDp >= 640 && config.screenHeightDp >= 480) || config.orientation == 2;
    }

    public void setPadding(int i, int t, int r, int b) {
        int l = i;
        this.mSavedPaddingLeft = l;
        super.setPadding(l, t, r, b);
    }

    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public void initialize(MenuItemImpl menuItemImpl, int i) {
        ForwardingListener forwardingListener;
        MenuItemImpl itemData = menuItemImpl;
        int i2 = i;
        this.mItemData = itemData;
        setIcon(itemData.getIcon());
        setTitle(itemData.getTitleForItemView(this));
        setId(itemData.getItemId());
        setVisibility(itemData.isVisible() ? 0 : 8);
        setEnabled(itemData.isEnabled());
        if (itemData.hasSubMenu() && this.mForwardingListener == null) {
            new ActionMenuItemForwardingListener(this);
            this.mForwardingListener = forwardingListener;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent e = motionEvent;
        if (!this.mItemData.hasSubMenu() || this.mForwardingListener == null || !this.mForwardingListener.onTouch(this, e)) {
            return super.onTouchEvent(e);
        }
        return true;
    }

    public void onClick(View view) {
        View view2 = view;
        if (this.mItemInvoker != null) {
            boolean invokeItem = this.mItemInvoker.invokeItem(this.mItemData);
        }
    }

    public void setItemInvoker(MenuBuilder.ItemInvoker invoker) {
        MenuBuilder.ItemInvoker itemInvoker = invoker;
        this.mItemInvoker = itemInvoker;
    }

    public void setPopupCallback(PopupCallback popupCallback) {
        PopupCallback popupCallback2 = popupCallback;
        this.mPopupCallback = popupCallback2;
    }

    public boolean prefersCondensedTitle() {
        return true;
    }

    public void setCheckable(boolean checkable) {
    }

    public void setChecked(boolean checked) {
    }

    public void setExpandedFormat(boolean z) {
        boolean expandedFormat = z;
        if (this.mExpandedFormat != expandedFormat) {
            this.mExpandedFormat = expandedFormat;
            if (this.mItemData != null) {
                this.mItemData.actionFormatChanged();
            }
        }
    }

    private void updateTextButtonVisibility() {
        boolean visible = (!TextUtils.isEmpty(this.mTitle)) & (this.mIcon == null || (this.mItemData.showsTextAsAction() && (this.mAllowTextWithIcon || this.mExpandedFormat)));
        setText(visible ? this.mTitle : null);
        CharSequence contentDescription = this.mItemData.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            setContentDescription(visible ? null : this.mItemData.getTitle());
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.mItemData.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            TooltipCompat.setTooltipText(this, visible ? null : this.mItemData.getTitle());
        } else {
            TooltipCompat.setTooltipText(this, tooltipText);
        }
    }

    public void setIcon(Drawable drawable) {
        Drawable icon = drawable;
        this.mIcon = icon;
        if (icon != null) {
            int width = icon.getIntrinsicWidth();
            int height = icon.getIntrinsicHeight();
            if (width > this.mMaxIconSize) {
                float scale = ((float) this.mMaxIconSize) / ((float) width);
                width = this.mMaxIconSize;
                height = (int) (((float) height) * scale);
            }
            if (height > this.mMaxIconSize) {
                float scale2 = ((float) this.mMaxIconSize) / ((float) height);
                height = this.mMaxIconSize;
                width = (int) (((float) width) * scale2);
            }
            icon.setBounds(0, 0, width, height);
        }
        setCompoundDrawables(icon, (Drawable) null, (Drawable) null, (Drawable) null);
        updateTextButtonVisibility();
    }

    public boolean hasText() {
        return !TextUtils.isEmpty(getText());
    }

    public void setShortcut(boolean showShortcut, char shortcutKey) {
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
        updateTextButtonVisibility();
    }

    public boolean showsIcon() {
        return true;
    }

    public boolean needsDividerBefore() {
        return hasText() && this.mItemData.getIcon() == null;
    }

    public boolean needsDividerAfter() {
        return hasText();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        boolean textVisible = hasText();
        if (textVisible && this.mSavedPaddingLeft >= 0) {
            super.setPadding(this.mSavedPaddingLeft, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int oldMeasuredWidth = getMeasuredWidth();
        int targetWidth = widthMode == Integer.MIN_VALUE ? Math.min(widthSize, this.mMinWidth) : this.mMinWidth;
        if (widthMode != 1073741824 && this.mMinWidth > 0 && oldMeasuredWidth < targetWidth) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(targetWidth, Declaration.MODULE_REFERENCE), heightMeasureSpec);
        }
        if (!textVisible && this.mIcon != null) {
            super.setPadding((getMeasuredWidth() - this.mIcon.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    /* renamed from: android.support.v7.view.menu.ActionMenuItemView$ActionMenuItemForwardingListener */
    private class ActionMenuItemForwardingListener extends ForwardingListener {
        final /* synthetic */ ActionMenuItemView this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ActionMenuItemForwardingListener(android.support.p003v7.view.menu.ActionMenuItemView r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.this$0 = r3
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.view.menu.ActionMenuItemView.ActionMenuItemForwardingListener.<init>(android.support.v7.view.menu.ActionMenuItemView):void");
        }

        public ShowableListMenu getPopup() {
            if (this.this$0.mPopupCallback != null) {
                return this.this$0.mPopupCallback.getPopup();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public boolean onForwardingStarted() {
            if (this.this$0.mItemInvoker == null || !this.this$0.mItemInvoker.invokeItem(this.this$0.mItemData)) {
                return false;
            }
            ShowableListMenu popup = getPopup();
            return popup != null && popup.isShowing();
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2 = parcelable;
        super.onRestoreInstanceState((Parcelable) null);
    }

    /* renamed from: android.support.v7.view.menu.ActionMenuItemView$PopupCallback */
    public static abstract class PopupCallback {
        public abstract ShowableListMenu getPopup();

        public PopupCallback() {
        }
    }
}
