package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.view.PointerIconCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.TextViewCompat;
import android.support.p003v7.view.menu.MenuItemImpl;
import android.support.p003v7.view.menu.MenuView;
import android.support.p003v7.widget.TooltipCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class BottomNavigationItemView extends FrameLayout implements MenuView.ItemView {
    private static final int[] CHECKED_STATE_SET = {16842912};
    public static final int INVALID_ITEM_POSITION = -1;
    private final int defaultMargin;
    private ImageView icon;
    private ColorStateList iconTint;
    private boolean isShifting;
    private MenuItemImpl itemData;
    private int itemPosition;
    private int labelVisibilityMode;
    private final TextView largeLabel;
    private float scaleDownFactor;
    private float scaleUpFactor;
    private float shiftAmount;
    private final TextView smallLabel;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BottomNavigationItemView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BottomNavigationItemView(@NonNull Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BottomNavigationItemView(android.content.Context r10, android.util.AttributeSet r11, int r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r0
            r6 = -1
            r5.itemPosition = r6
            r5 = r0
            android.content.res.Resources r5 = r5.getResources()
            r4 = r5
            r5 = r1
            android.view.LayoutInflater r5 = android.view.LayoutInflater.from(r5)
            int r6 = android.support.design.C0064R.layout.design_bottom_navigation_item
            r7 = r0
            r8 = 1
            android.view.View r5 = r5.inflate(r6, r7, r8)
            r5 = r0
            int r6 = android.support.design.C0064R.C0065drawable.design_bottom_navigation_item_background
            r5.setBackgroundResource(r6)
            r5 = r0
            r6 = r4
            int r7 = android.support.design.C0064R.dimen.design_bottom_navigation_margin
            int r6 = r6.getDimensionPixelSize(r7)
            r5.defaultMargin = r6
            r5 = r0
            r6 = r0
            int r7 = android.support.design.C0064R.C0066id.icon
            android.view.View r6 = r6.findViewById(r7)
            android.widget.ImageView r6 = (android.widget.ImageView) r6
            r5.icon = r6
            r5 = r0
            r6 = r0
            int r7 = android.support.design.C0064R.C0066id.smallLabel
            android.view.View r6 = r6.findViewById(r7)
            android.widget.TextView r6 = (android.widget.TextView) r6
            r5.smallLabel = r6
            r5 = r0
            r6 = r0
            int r7 = android.support.design.C0064R.C0066id.largeLabel
            android.view.View r6 = r6.findViewById(r7)
            android.widget.TextView r6 = (android.widget.TextView) r6
            r5.largeLabel = r6
            r5 = r0
            android.widget.TextView r5 = r5.smallLabel
            r6 = 2
            android.support.p000v4.view.ViewCompat.setImportantForAccessibility(r5, r6)
            r5 = r0
            android.widget.TextView r5 = r5.largeLabel
            r6 = 2
            android.support.p000v4.view.ViewCompat.setImportantForAccessibility(r5, r6)
            r5 = r0
            r6 = 1
            r5.setFocusable(r6)
            r5 = r0
            r6 = r0
            android.widget.TextView r6 = r6.smallLabel
            float r6 = r6.getTextSize()
            r7 = r0
            android.widget.TextView r7 = r7.largeLabel
            float r7 = r7.getTextSize()
            r5.calculateTextScaleFactors(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.internal.BottomNavigationItemView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void initialize(MenuItemImpl menuItemImpl, int i) {
        MenuItemImpl itemData2 = menuItemImpl;
        int i2 = i;
        this.itemData = itemData2;
        setCheckable(itemData2.isCheckable());
        setChecked(itemData2.isChecked());
        setEnabled(itemData2.isEnabled());
        setIcon(itemData2.getIcon());
        setTitle(itemData2.getTitle());
        setId(itemData2.getItemId());
        if (!TextUtils.isEmpty(itemData2.getContentDescription())) {
            setContentDescription(itemData2.getContentDescription());
        }
        TooltipCompat.setTooltipText(this, itemData2.getTooltipText());
        setVisibility(itemData2.isVisible() ? 0 : 8);
    }

    public void setItemPosition(int position) {
        int i = position;
        this.itemPosition = i;
    }

    public int getItemPosition() {
        return this.itemPosition;
    }

    public void setShifting(boolean z) {
        boolean shifting = z;
        if (this.isShifting != shifting) {
            this.isShifting = shifting;
            if (this.itemData != null) {
                setChecked(this.itemData.isChecked());
            }
        }
    }

    public void setLabelVisibilityMode(int i) {
        int mode = i;
        if (this.labelVisibilityMode != mode) {
            this.labelVisibilityMode = mode;
            if (this.itemData != null) {
                setChecked(this.itemData.isChecked());
            }
        }
    }

    public MenuItemImpl getItemData() {
        return this.itemData;
    }

    public void setTitle(CharSequence charSequence) {
        CharSequence title = charSequence;
        this.smallLabel.setText(title);
        this.largeLabel.setText(title);
        if (this.itemData == null || TextUtils.isEmpty(this.itemData.getContentDescription())) {
            setContentDescription(title);
        }
    }

    public void setCheckable(boolean z) {
        boolean z2 = z;
        refreshDrawableState();
    }

    public void setChecked(boolean z) {
        boolean checked = z;
        this.largeLabel.setPivotX((float) (this.largeLabel.getWidth() / 2));
        this.largeLabel.setPivotY((float) this.largeLabel.getBaseline());
        this.smallLabel.setPivotX((float) (this.smallLabel.getWidth() / 2));
        this.smallLabel.setPivotY((float) this.smallLabel.getBaseline());
        switch (this.labelVisibilityMode) {
            case -1:
                if (!this.isShifting) {
                    if (!checked) {
                        setViewLayoutParams(this.icon, this.defaultMargin, 49);
                        setViewValues(this.largeLabel, this.scaleDownFactor, this.scaleDownFactor, 4);
                        setViewValues(this.smallLabel, 1.0f, 1.0f, 0);
                        break;
                    } else {
                        setViewLayoutParams(this.icon, (int) (((float) this.defaultMargin) + this.shiftAmount), 49);
                        setViewValues(this.largeLabel, 1.0f, 1.0f, 0);
                        setViewValues(this.smallLabel, this.scaleUpFactor, this.scaleUpFactor, 4);
                        break;
                    }
                } else {
                    if (checked) {
                        setViewLayoutParams(this.icon, this.defaultMargin, 49);
                        setViewValues(this.largeLabel, 1.0f, 1.0f, 0);
                    } else {
                        setViewLayoutParams(this.icon, this.defaultMargin, 17);
                        setViewValues(this.largeLabel, 0.5f, 0.5f, 4);
                    }
                    this.smallLabel.setVisibility(4);
                    break;
                }
            case 0:
                if (checked) {
                    setViewLayoutParams(this.icon, this.defaultMargin, 49);
                    setViewValues(this.largeLabel, 1.0f, 1.0f, 0);
                } else {
                    setViewLayoutParams(this.icon, this.defaultMargin, 17);
                    setViewValues(this.largeLabel, 0.5f, 0.5f, 4);
                }
                this.smallLabel.setVisibility(4);
                break;
            case 1:
                if (!checked) {
                    setViewLayoutParams(this.icon, this.defaultMargin, 49);
                    setViewValues(this.largeLabel, this.scaleDownFactor, this.scaleDownFactor, 4);
                    setViewValues(this.smallLabel, 1.0f, 1.0f, 0);
                    break;
                } else {
                    setViewLayoutParams(this.icon, (int) (((float) this.defaultMargin) + this.shiftAmount), 49);
                    setViewValues(this.largeLabel, 1.0f, 1.0f, 0);
                    setViewValues(this.smallLabel, this.scaleUpFactor, this.scaleUpFactor, 4);
                    break;
                }
            case 2:
                setViewLayoutParams(this.icon, this.defaultMargin, 17);
                this.largeLabel.setVisibility(8);
                this.smallLabel.setVisibility(8);
                break;
        }
        refreshDrawableState();
        setSelected(checked);
    }

    private void setViewLayoutParams(@NonNull View view, int topMargin, int gravity) {
        View view2 = view;
        FrameLayout.LayoutParams viewParams = (FrameLayout.LayoutParams) view2.getLayoutParams();
        viewParams.topMargin = topMargin;
        viewParams.gravity = gravity;
        view2.setLayoutParams(viewParams);
    }

    private void setViewValues(@NonNull View view, float scaleX, float scaleY, int visibility) {
        View view2 = view;
        view2.setScaleX(scaleX);
        view2.setScaleY(scaleY);
        view2.setVisibility(visibility);
    }

    public void setEnabled(boolean z) {
        boolean enabled = z;
        super.setEnabled(enabled);
        this.smallLabel.setEnabled(enabled);
        this.largeLabel.setEnabled(enabled);
        this.icon.setEnabled(enabled);
        if (enabled) {
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
        } else {
            ViewCompat.setPointerIcon(this, (PointerIconCompat) null);
        }
    }

    public int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (this.itemData != null && this.itemData.isCheckable() && this.itemData.isChecked()) {
            int[] mergeDrawableStates = mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    public void setShortcut(boolean showShortcut, char shortcutKey) {
    }

    public void setIcon(Drawable drawable) {
        Drawable newDrawable;
        Drawable iconDrawable = drawable;
        if (iconDrawable != null) {
            Drawable.ConstantState state = iconDrawable.getConstantState();
            if (state == null) {
                newDrawable = iconDrawable;
            } else {
                newDrawable = state.newDrawable();
            }
            iconDrawable = DrawableCompat.wrap(newDrawable).mutate();
            DrawableCompat.setTintList(iconDrawable, this.iconTint);
        }
        this.icon.setImageDrawable(iconDrawable);
    }

    public boolean prefersCondensedTitle() {
        return false;
    }

    public boolean showsIcon() {
        return true;
    }

    public void setIconTintList(ColorStateList tint) {
        this.iconTint = tint;
        if (this.itemData != null) {
            setIcon(this.itemData.getIcon());
        }
    }

    public void setIconSize(int i) {
        int iconSize = i;
        FrameLayout.LayoutParams iconParams = (FrameLayout.LayoutParams) this.icon.getLayoutParams();
        iconParams.width = iconSize;
        iconParams.height = iconSize;
        this.icon.setLayoutParams(iconParams);
    }

    public void setTextAppearanceInactive(@StyleRes int inactiveTextAppearance) {
        TextViewCompat.setTextAppearance(this.smallLabel, inactiveTextAppearance);
        calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    public void setTextAppearanceActive(@StyleRes int activeTextAppearance) {
        TextViewCompat.setTextAppearance(this.largeLabel, activeTextAppearance);
        calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    public void setTextColor(@Nullable ColorStateList colorStateList) {
        ColorStateList color = colorStateList;
        if (color != null) {
            this.smallLabel.setTextColor(color);
            this.largeLabel.setTextColor(color);
        }
    }

    private void calculateTextScaleFactors(float f, float f2) {
        float smallLabelSize = f;
        float largeLabelSize = f2;
        this.shiftAmount = smallLabelSize - largeLabelSize;
        this.scaleUpFactor = (1.0f * largeLabelSize) / smallLabelSize;
        this.scaleDownFactor = (1.0f * smallLabelSize) / largeLabelSize;
    }

    public void setItemBackground(int i) {
        Drawable backgroundDrawable;
        int background = i;
        if (background == 0) {
            backgroundDrawable = null;
        } else {
            backgroundDrawable = ContextCompat.getDrawable(getContext(), background);
        }
        setItemBackground(backgroundDrawable);
    }

    public void setItemBackground(@Nullable Drawable background) {
        ViewCompat.setBackground(this, background);
    }
}
