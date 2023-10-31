package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.TintableBackgroundView;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

/* renamed from: android.support.v7.widget.AppCompatSpinner */
public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
    private static final int[] ATTRS_ANDROID_SPINNERMODE = {16843505};
    private static final int MAX_ITEMS_MEASURED = 15;
    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "AppCompatSpinner";
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    DropdownPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    final Rect mTempRect;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatSpinner(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatSpinner(Context context, int mode) {
        this(context, (AttributeSet) null, C0425R.attr.spinnerStyle, mode);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, C0425R.attr.spinnerStyle);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        this(context, attrs, defStyleAttr, mode, (Resources.Theme) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppCompatSpinner(android.content.Context r18, android.util.AttributeSet r19, int r20, int r21, android.content.res.Resources.Theme r22) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = r22
            r10 = r0
            r11 = r1
            r12 = r2
            r13 = r3
            r10.<init>(r11, r12, r13)
            r10 = r0
            android.graphics.Rect r11 = new android.graphics.Rect
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            r10.mTempRect = r11
            r10 = r1
            r11 = r2
            int[] r12 = android.support.p003v7.appcompat.C0425R.styleable.Spinner
            r13 = r3
            r14 = 0
            android.support.v7.widget.TintTypedArray r10 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r10, r11, r12, r13, r14)
            r6 = r10
            r10 = r0
            android.support.v7.widget.AppCompatBackgroundHelper r11 = new android.support.v7.widget.AppCompatBackgroundHelper
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r0
            r12.<init>(r13)
            r10.mBackgroundTintHelper = r11
            r10 = r5
            if (r10 == 0) goto L_0x0120
            r10 = r0
            android.support.v7.view.ContextThemeWrapper r11 = new android.support.v7.view.ContextThemeWrapper
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r1
            r14 = r5
            r12.<init>((android.content.Context) r13, (android.content.res.Resources.Theme) r14)
            r10.mPopupContext = r11
        L_0x004e:
            r10 = r0
            android.content.Context r10 = r10.mPopupContext
            if (r10 == 0) goto L_0x00d8
            r10 = r4
            r11 = -1
            if (r10 != r11) goto L_0x007b
            r10 = 0
            r7 = r10
            r10 = r1
            r11 = r2
            int[] r12 = ATTRS_ANDROID_SPINNERMODE     // Catch:{ Exception -> 0x014c }
            r13 = r3
            r14 = 0
            android.content.res.TypedArray r10 = r10.obtainStyledAttributes(r11, r12, r13, r14)     // Catch:{ Exception -> 0x014c }
            r7 = r10
            r10 = r7
            r11 = 0
            boolean r10 = r10.hasValue(r11)     // Catch:{ Exception -> 0x014c }
            if (r10 == 0) goto L_0x0074
            r10 = r7
            r11 = 0
            r12 = 0
            int r10 = r10.getInt(r11, r12)     // Catch:{ Exception -> 0x014c }
            r4 = r10
        L_0x0074:
            r10 = r7
            if (r10 == 0) goto L_0x007b
            r10 = r7
            r10.recycle()
        L_0x007b:
            r10 = r4
            r11 = 1
            if (r10 != r11) goto L_0x00d8
            android.support.v7.widget.AppCompatSpinner$DropdownPopup r10 = new android.support.v7.widget.AppCompatSpinner$DropdownPopup
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = r0
            r13 = r0
            android.content.Context r13 = r13.mPopupContext
            r14 = r2
            r15 = r3
            r11.<init>(r12, r13, r14, r15)
            r7 = r10
            r10 = r0
            android.content.Context r10 = r10.mPopupContext
            r11 = r2
            int[] r12 = android.support.p003v7.appcompat.C0425R.styleable.Spinner
            r13 = r3
            r14 = 0
            android.support.v7.widget.TintTypedArray r10 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r10, r11, r12, r13, r14)
            r8 = r10
            r10 = r0
            r11 = r8
            int r12 = android.support.p003v7.appcompat.C0425R.styleable.Spinner_android_dropDownWidth
            r13 = -2
            int r11 = r11.getLayoutDimension((int) r12, (int) r13)
            r10.mDropDownWidth = r11
            r10 = r7
            r11 = r8
            int r12 = android.support.p003v7.appcompat.C0425R.styleable.Spinner_android_popupBackground
            android.graphics.drawable.Drawable r11 = r11.getDrawable(r12)
            r10.setBackgroundDrawable(r11)
            r10 = r7
            r11 = r6
            int r12 = android.support.p003v7.appcompat.C0425R.styleable.Spinner_android_prompt
            java.lang.String r11 = r11.getString(r12)
            r10.setPromptText(r11)
            r10 = r8
            r10.recycle()
            r10 = r0
            r11 = r7
            r10.mPopup = r11
            r10 = r0
            android.support.v7.widget.AppCompatSpinner$1 r11 = new android.support.v7.widget.AppCompatSpinner$1
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r0
            r14 = r0
            r15 = r7
            r12.<init>(r13, r14, r15)
            r10.mForwardingListener = r11
        L_0x00d8:
            r10 = r6
            int r11 = android.support.p003v7.appcompat.C0425R.styleable.Spinner_android_entries
            java.lang.CharSequence[] r10 = r10.getTextArray(r11)
            r7 = r10
            r10 = r7
            if (r10 == 0) goto L_0x00ff
            android.widget.ArrayAdapter r10 = new android.widget.ArrayAdapter
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = r1
            r13 = 17367048(0x1090008, float:2.5162948E-38)
            r14 = r7
            r11.<init>(r12, r13, r14)
            r8 = r10
            r10 = r8
            int r11 = android.support.p003v7.appcompat.C0425R.layout.support_simple_spinner_dropdown_item
            r10.setDropDownViewResource(r11)
            r10 = r0
            r11 = r8
            r10.setAdapter((android.widget.SpinnerAdapter) r11)
        L_0x00ff:
            r10 = r6
            r10.recycle()
            r10 = r0
            r11 = 1
            r10.mPopupSet = r11
            r10 = r0
            android.widget.SpinnerAdapter r10 = r10.mTempAdapter
            if (r10 == 0) goto L_0x0117
            r10 = r0
            r11 = r0
            android.widget.SpinnerAdapter r11 = r11.mTempAdapter
            r10.setAdapter((android.widget.SpinnerAdapter) r11)
            r10 = r0
            r11 = 0
            r10.mTempAdapter = r11
        L_0x0117:
            r10 = r0
            android.support.v7.widget.AppCompatBackgroundHelper r10 = r10.mBackgroundTintHelper
            r11 = r2
            r12 = r3
            r10.loadFromAttributes(r11, r12)
            return
        L_0x0120:
            r10 = r6
            int r11 = android.support.p003v7.appcompat.C0425R.styleable.Spinner_popupTheme
            r12 = 0
            int r10 = r10.getResourceId(r11, r12)
            r7 = r10
            r10 = r7
            if (r10 == 0) goto L_0x013e
            r10 = r0
            android.support.v7.view.ContextThemeWrapper r11 = new android.support.v7.view.ContextThemeWrapper
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r1
            r14 = r7
            r12.<init>((android.content.Context) r13, (int) r14)
            r10.mPopupContext = r11
            goto L_0x004e
        L_0x013e:
            r10 = r0
            int r11 = android.os.Build.VERSION.SDK_INT
            r12 = 23
            if (r11 >= r12) goto L_0x014a
            r11 = r1
        L_0x0146:
            r10.mPopupContext = r11
            goto L_0x004e
        L_0x014a:
            r11 = 0
            goto L_0x0146
        L_0x014c:
            r10 = move-exception
            r8 = r10
            java.lang.String r10 = "AppCompatSpinner"
            java.lang.String r11 = "Could not read android:spinnerMode"
            r12 = r8
            int r10 = android.util.Log.i(r10, r11, r12)     // Catch:{ all -> 0x0162 }
            r10 = r7
            if (r10 == 0) goto L_0x007b
            r10 = r7
            r10.recycle()
            goto L_0x007b
        L_0x0162:
            r10 = move-exception
            r9 = r10
            r10 = r7
            if (r10 == 0) goto L_0x016b
            r10 = r7
            r10.recycle()
        L_0x016b:
            r10 = r9
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }

    public Context getPopupContext() {
        if (this.mPopup != null) {
            return this.mPopupContext;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }
        return null;
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        Drawable background = drawable;
        if (this.mPopup != null) {
            this.mPopup.setBackgroundDrawable(background);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(background);
        }
    }

    public void setPopupBackgroundResource(@DrawableRes int resId) {
        setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), resId));
    }

    public Drawable getPopupBackground() {
        if (this.mPopup != null) {
            return this.mPopup.getBackground();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }

    public void setDropDownVerticalOffset(int i) {
        int pixels = i;
        if (this.mPopup != null) {
            this.mPopup.setVerticalOffset(pixels);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(pixels);
        }
    }

    public int getDropDownVerticalOffset() {
        if (this.mPopup != null) {
            return this.mPopup.getVerticalOffset();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    public void setDropDownHorizontalOffset(int i) {
        int pixels = i;
        if (this.mPopup != null) {
            this.mPopup.setHorizontalOffset(pixels);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(pixels);
        }
    }

    public int getDropDownHorizontalOffset() {
        if (this.mPopup != null) {
            return this.mPopup.getHorizontalOffset();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    public void setDropDownWidth(int i) {
        int pixels = i;
        if (this.mPopup != null) {
            this.mDropDownWidth = pixels;
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(pixels);
        }
    }

    public int getDropDownWidth() {
        if (this.mPopup != null) {
            return this.mDropDownWidth;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        ListAdapter listAdapter;
        SpinnerAdapter adapter = spinnerAdapter;
        if (!this.mPopupSet) {
            this.mTempAdapter = adapter;
            return;
        }
        super.setAdapter(adapter);
        if (this.mPopup != null) {
            new DropDownAdapter(adapter, (this.mPopupContext == null ? getContext() : this.mPopupContext).getTheme());
            this.mPopup.setAdapter(listAdapter);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mPopup != null && this.mPopup.isShowing()) {
            this.mPopup.dismiss();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent event = motionEvent;
        if (this.mForwardingListener == null || !this.mForwardingListener.onTouch(this, event)) {
            return super.onTouchEvent(event);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int heightMeasureSpec) {
        int widthMeasureSpec = i;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mPopup != null && View.MeasureSpec.getMode(widthMeasureSpec) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(widthMeasureSpec)), getMeasuredHeight());
        }
    }

    public boolean performClick() {
        if (this.mPopup == null) {
            return super.performClick();
        }
        if (!this.mPopup.isShowing()) {
            this.mPopup.show();
        }
        return true;
    }

    public void setPrompt(CharSequence charSequence) {
        CharSequence prompt = charSequence;
        if (this.mPopup != null) {
            this.mPopup.setPromptText(prompt);
        } else {
            super.setPrompt(prompt);
        }
    }

    public CharSequence getPrompt() {
        return this.mPopup != null ? this.mPopup.getHintText() : super.getPrompt();
    }

    public void setBackgroundResource(@DrawableRes int i) {
        int resId = i;
        super.setBackgroundResource(resId);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        Drawable background = drawable;
        super.setBackgroundDrawable(background);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundDrawable(background);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        ColorStateList tint = colorStateList;
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintList(tint);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        return this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintList() : null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        PorterDuff.Mode tintMode = mode;
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintMode(tintMode);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintMode() : null;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.applySupportBackgroundTint();
        }
    }

    /* access modifiers changed from: package-private */
    public int compatMeasureContentWidth(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        ViewGroup.LayoutParams layoutParams;
        SpinnerAdapter adapter = spinnerAdapter;
        Drawable background = drawable;
        if (adapter == null) {
            return 0;
        }
        int width = 0;
        View itemView = null;
        int itemType = 0;
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int start = Math.max(0, getSelectedItemPosition());
        int end = Math.min(adapter.getCount(), start + 15);
        for (int i = Math.max(0, start - (15 - (end - start))); i < end; i++) {
            int positionType = adapter.getItemViewType(i);
            if (positionType != itemType) {
                itemType = positionType;
                itemView = null;
            }
            itemView = adapter.getView(i, itemView, this);
            if (itemView.getLayoutParams() == null) {
                new ViewGroup.LayoutParams(-2, -2);
                itemView.setLayoutParams(layoutParams);
            }
            itemView.measure(widthMeasureSpec, heightMeasureSpec);
            width = Math.max(width, itemView.getMeasuredWidth());
        }
        if (background != null) {
            boolean padding = background.getPadding(this.mTempRect);
            width += this.mTempRect.left + this.mTempRect.right;
        }
        return width;
    }

    /* renamed from: android.support.v7.widget.AppCompatSpinner$DropDownAdapter */
    private static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(@Nullable SpinnerAdapter spinnerAdapter, @Nullable Resources.Theme theme) {
            SpinnerAdapter adapter = spinnerAdapter;
            Resources.Theme dropDownTheme = theme;
            this.mAdapter = adapter;
            if (adapter instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter) adapter;
            }
            if (dropDownTheme == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 23 && (adapter instanceof ThemedSpinnerAdapter)) {
                ThemedSpinnerAdapter themedAdapter = (ThemedSpinnerAdapter) adapter;
                if (themedAdapter.getDropDownViewTheme() != dropDownTheme) {
                    themedAdapter.setDropDownViewTheme(dropDownTheme);
                }
            } else if (adapter instanceof ThemedSpinnerAdapter) {
                ThemedSpinnerAdapter themedAdapter2 = (ThemedSpinnerAdapter) adapter;
                if (themedAdapter2.getDropDownViewTheme() == null) {
                    themedAdapter2.setDropDownViewTheme(dropDownTheme);
                }
            }
        }

        public int getCount() {
            return this.mAdapter == null ? 0 : this.mAdapter.getCount();
        }

        public Object getItem(int position) {
            return this.mAdapter == null ? null : this.mAdapter.getItem(position);
        }

        public long getItemId(int position) {
            return this.mAdapter == null ? -1 : this.mAdapter.getItemId(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            return getDropDownView(position, convertView, parent);
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            View dropDownView;
            int position = i;
            View convertView = view;
            ViewGroup parent = viewGroup;
            if (this.mAdapter == null) {
                dropDownView = null;
            } else {
                dropDownView = this.mAdapter.getDropDownView(position, convertView, parent);
            }
            return dropDownView;
        }

        public boolean hasStableIds() {
            return this.mAdapter != null && this.mAdapter.hasStableIds();
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            DataSetObserver observer = dataSetObserver;
            if (this.mAdapter != null) {
                this.mAdapter.registerDataSetObserver(observer);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            DataSetObserver observer = dataSetObserver;
            if (this.mAdapter != null) {
                this.mAdapter.unregisterDataSetObserver(observer);
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter adapter = this.mListAdapter;
            if (adapter != null) {
                return adapter.areAllItemsEnabled();
            }
            return true;
        }

        public boolean isEnabled(int i) {
            int position = i;
            ListAdapter adapter = this.mListAdapter;
            if (adapter != null) {
                return adapter.isEnabled(position);
            }
            return true;
        }

        public int getItemViewType(int i) {
            int i2 = i;
            return 0;
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean isEmpty() {
            return getCount() == 0;
        }
    }

    /* renamed from: android.support.v7.widget.AppCompatSpinner$DropdownPopup */
    private class DropdownPopup extends ListPopupWindow {
        ListAdapter mAdapter;
        private CharSequence mHintText;
        private final Rect mVisibleRect;
        final /* synthetic */ AppCompatSpinner this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DropdownPopup(AppCompatSpinner appCompatSpinner, Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            Rect rect;
            AdapterView.OnItemClickListener onItemClickListener;
            AppCompatSpinner appCompatSpinner2 = appCompatSpinner;
            this.this$0 = appCompatSpinner2;
            new Rect();
            this.mVisibleRect = rect;
            setAnchorView(appCompatSpinner2);
            setModal(true);
            setPromptPosition(0);
            final AppCompatSpinner appCompatSpinner3 = appCompatSpinner2;
            new AdapterView.OnItemClickListener(this) {
                final /* synthetic */ DropdownPopup this$1;

                {
                    this.this$1 = this$1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    AdapterView<?> adapterView2 = adapterView;
                    View v = view;
                    int position = i;
                    long j2 = j;
                    this.this$1.this$0.setSelection(position);
                    if (this.this$1.this$0.getOnItemClickListener() != null) {
                        boolean performItemClick = this.this$1.this$0.performItemClick(v, position, this.this$1.mAdapter.getItemId(position));
                    }
                    this.this$1.dismiss();
                }
            };
            setOnItemClickListener(onItemClickListener);
        }

        public void setAdapter(ListAdapter listAdapter) {
            ListAdapter adapter = listAdapter;
            super.setAdapter(adapter);
            this.mAdapter = adapter;
        }

        public CharSequence getHintText() {
            return this.mHintText;
        }

        public void setPromptText(CharSequence hintText) {
            CharSequence charSequence = hintText;
            this.mHintText = charSequence;
        }

        /* access modifiers changed from: package-private */
        public void computeContentWidth() {
            int hOffset;
            Drawable background = getBackground();
            int hOffset2 = 0;
            if (background != null) {
                boolean padding = background.getPadding(this.this$0.mTempRect);
                hOffset2 = ViewUtils.isLayoutRtl(this.this$0) ? this.this$0.mTempRect.right : -this.this$0.mTempRect.left;
            } else {
                Rect rect = this.this$0.mTempRect;
                this.this$0.mTempRect.right = 0;
                rect.left = 0;
            }
            int spinnerPaddingLeft = this.this$0.getPaddingLeft();
            int spinnerPaddingRight = this.this$0.getPaddingRight();
            int spinnerWidth = this.this$0.getWidth();
            if (this.this$0.mDropDownWidth == -2) {
                int contentWidth = this.this$0.compatMeasureContentWidth((SpinnerAdapter) this.mAdapter, getBackground());
                int contentWidthLimit = (this.this$0.getContext().getResources().getDisplayMetrics().widthPixels - this.this$0.mTempRect.left) - this.this$0.mTempRect.right;
                if (contentWidth > contentWidthLimit) {
                    contentWidth = contentWidthLimit;
                }
                setContentWidth(Math.max(contentWidth, (spinnerWidth - spinnerPaddingLeft) - spinnerPaddingRight));
            } else if (this.this$0.mDropDownWidth == -1) {
                setContentWidth((spinnerWidth - spinnerPaddingLeft) - spinnerPaddingRight);
            } else {
                setContentWidth(this.this$0.mDropDownWidth);
            }
            if (ViewUtils.isLayoutRtl(this.this$0)) {
                hOffset = hOffset2 + ((spinnerWidth - spinnerPaddingRight) - getWidth());
            } else {
                hOffset = hOffset2 + spinnerPaddingLeft;
            }
            setHorizontalOffset(hOffset);
        }

        public void show() {
            ViewTreeObserver vto;
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
            PopupWindow.OnDismissListener onDismissListener;
            boolean wasShowing = isShowing();
            computeContentWidth();
            setInputMethodMode(2);
            super.show();
            getListView().setChoiceMode(1);
            setSelection(this.this$0.getSelectedItemPosition());
            if (!wasShowing && (vto = this.this$0.getViewTreeObserver()) != null) {
                new ViewTreeObserver.OnGlobalLayoutListener(this) {
                    final /* synthetic */ DropdownPopup this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void onGlobalLayout() {
                        if (!this.this$1.isVisibleToUser(this.this$1.this$0)) {
                            this.this$1.dismiss();
                            return;
                        }
                        this.this$1.computeContentWidth();
                        DropdownPopup.super.show();
                    }
                };
                ViewTreeObserver.OnGlobalLayoutListener layoutListener = onGlobalLayoutListener;
                vto.addOnGlobalLayoutListener(layoutListener);
                final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener2 = layoutListener;
                new PopupWindow.OnDismissListener(this) {
                    final /* synthetic */ DropdownPopup this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void onDismiss() {
                        ViewTreeObserver vto = this.this$1.this$0.getViewTreeObserver();
                        if (vto != null) {
                            vto.removeGlobalOnLayoutListener(onGlobalLayoutListener2);
                        }
                    }
                };
                setOnDismissListener(onDismissListener);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isVisibleToUser(View view) {
            View view2 = view;
            return ViewCompat.isAttachedToWindow(view2) && view2.getGlobalVisibleRect(this.mVisibleRect);
        }
    }
}
