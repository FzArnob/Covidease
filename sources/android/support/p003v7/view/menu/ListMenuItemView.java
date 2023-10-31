package android.support.p003v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.menu.ListMenuItemView */
public class ListMenuItemView extends LinearLayout implements MenuView.ItemView, AbsListView.SelectionBoundsAdjuster {
    private static final String TAG = "ListMenuItemView";
    private Drawable mBackground;
    private CheckBox mCheckBox;
    private LinearLayout mContent;
    private boolean mForceShowIcon;
    private ImageView mGroupDivider;
    private boolean mHasListDivider;
    private ImageView mIconView;
    private LayoutInflater mInflater;
    private MenuItemImpl mItemData;
    private int mMenuType;
    private boolean mPreserveIconSpacing;
    private RadioButton mRadioButton;
    private TextView mShortcutView;
    private Drawable mSubMenuArrow;
    private ImageView mSubMenuArrowView;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
    private TextView mTitleView;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ListMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, C0425R.attr.listMenuViewStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ListMenuItemView(android.content.Context r14, android.util.AttributeSet r15, int r16) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r6 = r0
            r7 = r1
            r8 = r2
            r6.<init>(r7, r8)
            r6 = r0
            android.content.Context r6 = r6.getContext()
            r7 = r2
            int[] r8 = android.support.p003v7.appcompat.C0425R.styleable.MenuView
            r9 = r3
            r10 = 0
            android.support.v7.widget.TintTypedArray r6 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r6, r7, r8, r9, r10)
            r4 = r6
            r6 = r0
            r7 = r4
            int r8 = android.support.p003v7.appcompat.C0425R.styleable.MenuView_android_itemBackground
            android.graphics.drawable.Drawable r7 = r7.getDrawable(r8)
            r6.mBackground = r7
            r6 = r0
            r7 = r4
            int r8 = android.support.p003v7.appcompat.C0425R.styleable.MenuView_android_itemTextAppearance
            r9 = -1
            int r7 = r7.getResourceId(r8, r9)
            r6.mTextAppearance = r7
            r6 = r0
            r7 = r4
            int r8 = android.support.p003v7.appcompat.C0425R.styleable.MenuView_preserveIconSpacing
            r9 = 0
            boolean r7 = r7.getBoolean(r8, r9)
            r6.mPreserveIconSpacing = r7
            r6 = r0
            r7 = r1
            r6.mTextAppearanceContext = r7
            r6 = r0
            r7 = r4
            int r8 = android.support.p003v7.appcompat.C0425R.styleable.MenuView_subMenuArrow
            android.graphics.drawable.Drawable r7 = r7.getDrawable(r8)
            r6.mSubMenuArrow = r7
            r6 = r1
            android.content.res.Resources$Theme r6 = r6.getTheme()
            r7 = 0
            r8 = 1
            int[] r8 = new int[r8]
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = 0
            r11 = 16843049(0x1010129, float:2.369439E-38)
            r9[r10] = r11
            int r9 = android.support.p003v7.appcompat.C0425R.attr.dropDownListViewStyle
            r10 = 0
            android.content.res.TypedArray r6 = r6.obtainStyledAttributes(r7, r8, r9, r10)
            r5 = r6
            r6 = r0
            r7 = r5
            r8 = 0
            boolean r7 = r7.hasValue(r8)
            r6.mHasListDivider = r7
            r6 = r4
            r6.recycle()
            r6 = r5
            r6.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.view.menu.ListMenuItemView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        ViewCompat.setBackground(this, this.mBackground);
        this.mTitleView = (TextView) findViewById(C0425R.C0427id.title);
        if (this.mTextAppearance != -1) {
            this.mTitleView.setTextAppearance(this.mTextAppearanceContext, this.mTextAppearance);
        }
        this.mShortcutView = (TextView) findViewById(C0425R.C0427id.shortcut);
        this.mSubMenuArrowView = (ImageView) findViewById(C0425R.C0427id.submenuarrow);
        if (this.mSubMenuArrowView != null) {
            this.mSubMenuArrowView.setImageDrawable(this.mSubMenuArrow);
        }
        this.mGroupDivider = (ImageView) findViewById(C0425R.C0427id.group_divider);
        this.mContent = (LinearLayout) findViewById(C0425R.C0427id.content);
    }

    public void initialize(MenuItemImpl menuItemImpl, int menuType) {
        MenuItemImpl itemData = menuItemImpl;
        this.mItemData = itemData;
        this.mMenuType = menuType;
        setVisibility(itemData.isVisible() ? 0 : 8);
        setTitle(itemData.getTitleForItemView(this));
        setCheckable(itemData.isCheckable());
        setShortcut(itemData.shouldShowShortcut(), itemData.getShortcut());
        setIcon(itemData.getIcon());
        setEnabled(itemData.isEnabled());
        setSubMenuArrowVisible(itemData.hasSubMenu());
        setContentDescription(itemData.getContentDescription());
    }

    private void addContentView(View v) {
        addContentView(v, -1);
    }

    private void addContentView(View view, int i) {
        View v = view;
        int index = i;
        if (this.mContent != null) {
            this.mContent.addView(v, index);
        } else {
            addView(v, index);
        }
    }

    public void setForceShowIcon(boolean forceShow) {
        boolean z = forceShow;
        this.mForceShowIcon = z;
        this.mPreserveIconSpacing = z;
    }

    public void setTitle(CharSequence charSequence) {
        CharSequence title = charSequence;
        if (title != null) {
            this.mTitleView.setText(title);
            if (this.mTitleView.getVisibility() != 0) {
                this.mTitleView.setVisibility(0);
            }
        } else if (this.mTitleView.getVisibility() != 8) {
            this.mTitleView.setVisibility(8);
        }
    }

    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton otherCompoundButton;
        boolean checkable = z;
        if (checkable || this.mRadioButton != null || this.mCheckBox != null) {
            if (this.mItemData.isExclusiveCheckable()) {
                if (this.mRadioButton == null) {
                    insertRadioButton();
                }
                compoundButton = this.mRadioButton;
                otherCompoundButton = this.mCheckBox;
            } else {
                if (this.mCheckBox == null) {
                    insertCheckBox();
                }
                compoundButton = this.mCheckBox;
                otherCompoundButton = this.mRadioButton;
            }
            if (checkable) {
                compoundButton.setChecked(this.mItemData.isChecked());
                if (compoundButton.getVisibility() != 0) {
                    compoundButton.setVisibility(0);
                }
                if (otherCompoundButton != null && otherCompoundButton.getVisibility() != 8) {
                    otherCompoundButton.setVisibility(8);
                    return;
                }
                return;
            }
            if (this.mCheckBox != null) {
                this.mCheckBox.setVisibility(8);
            }
            if (this.mRadioButton != null) {
                this.mRadioButton.setVisibility(8);
            }
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        boolean checked = z;
        if (this.mItemData.isExclusiveCheckable()) {
            if (this.mRadioButton == null) {
                insertRadioButton();
            }
            compoundButton = this.mRadioButton;
        } else {
            if (this.mCheckBox == null) {
                insertCheckBox();
            }
            compoundButton = this.mCheckBox;
        }
        compoundButton.setChecked(checked);
    }

    private void setSubMenuArrowVisible(boolean z) {
        boolean hasSubmenu = z;
        if (this.mSubMenuArrowView != null) {
            this.mSubMenuArrowView.setVisibility(hasSubmenu ? 0 : 8);
        }
    }

    public void setShortcut(boolean showShortcut, char c) {
        char c2 = c;
        int newVisibility = (!showShortcut || !this.mItemData.shouldShowShortcut()) ? 8 : 0;
        if (newVisibility == 0) {
            this.mShortcutView.setText(this.mItemData.getShortcutLabel());
        }
        if (this.mShortcutView.getVisibility() != newVisibility) {
            this.mShortcutView.setVisibility(newVisibility);
        }
    }

    public void setIcon(Drawable drawable) {
        Drawable icon = drawable;
        boolean showIcon = this.mItemData.shouldShowIcon() || this.mForceShowIcon;
        if (!showIcon && !this.mPreserveIconSpacing) {
            return;
        }
        if (this.mIconView != null || icon != null || this.mPreserveIconSpacing) {
            if (this.mIconView == null) {
                insertIconView();
            }
            if (icon != null || this.mPreserveIconSpacing) {
                this.mIconView.setImageDrawable(showIcon ? icon : null);
                if (this.mIconView.getVisibility() != 0) {
                    this.mIconView.setVisibility(0);
                    return;
                }
                return;
            }
            this.mIconView.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        if (this.mIconView != null && this.mPreserveIconSpacing) {
            ViewGroup.LayoutParams lp = getLayoutParams();
            LinearLayout.LayoutParams iconLp = (LinearLayout.LayoutParams) this.mIconView.getLayoutParams();
            if (lp.height > 0 && iconLp.width <= 0) {
                iconLp.width = lp.height;
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void insertIconView() {
        this.mIconView = (ImageView) getInflater().inflate(C0425R.layout.abc_list_menu_item_icon, this, false);
        addContentView(this.mIconView, 0);
    }

    private void insertRadioButton() {
        this.mRadioButton = (RadioButton) getInflater().inflate(C0425R.layout.abc_list_menu_item_radio, this, false);
        addContentView(this.mRadioButton);
    }

    private void insertCheckBox() {
        this.mCheckBox = (CheckBox) getInflater().inflate(C0425R.layout.abc_list_menu_item_checkbox, this, false);
        addContentView(this.mCheckBox);
    }

    public boolean prefersCondensedTitle() {
        return false;
    }

    public boolean showsIcon() {
        return this.mForceShowIcon;
    }

    private LayoutInflater getInflater() {
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(getContext());
        }
        return this.mInflater;
    }

    public void setGroupDividerEnabled(boolean z) {
        boolean groupDividerEnabled = z;
        if (this.mGroupDivider != null) {
            this.mGroupDivider.setVisibility((this.mHasListDivider || !groupDividerEnabled) ? 8 : 0);
        }
    }

    public void adjustListItemSelectionBounds(Rect rect) {
        Rect rect2 = rect;
        if (this.mGroupDivider != null && this.mGroupDivider.getVisibility() == 0) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.mGroupDivider.getLayoutParams();
            rect2.top += this.mGroupDivider.getHeight() + lp.topMargin + lp.bottomMargin;
        }
    }
}
