package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.PopupWindowCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* renamed from: android.support.v7.widget.ListPopupWindow */
public class ListPopupWindow implements ShowableListMenu {
    private static final boolean DEBUG = false;
    static final int EXPAND_LIST_TIMEOUT = 250;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private static Method sClipToWindowEnabledMethod;
    private static Method sGetMaxAvailableHeightMethod;
    private static Method sSetEpicenterBoundsMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private boolean mIsAnimatedFromAnchor;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;

    static {
        Class<PopupWindow> cls = PopupWindow.class;
        try {
            sClipToWindowEnabledMethod = cls.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException e) {
            NoSuchMethodException noSuchMethodException = e;
            int i = Log.i(TAG, "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        Class<PopupWindow> cls2 = PopupWindow.class;
        try {
            Class[] clsArr = new Class[3];
            clsArr[0] = View.class;
            Class[] clsArr2 = clsArr;
            clsArr2[1] = Integer.TYPE;
            Class[] clsArr3 = clsArr2;
            clsArr3[2] = Boolean.TYPE;
            sGetMaxAvailableHeightMethod = cls2.getDeclaredMethod("getMaxAvailableHeight", clsArr3);
        } catch (NoSuchMethodException e2) {
            int i2 = Log.i(TAG, "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
            sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[]{Rect.class});
        } catch (NoSuchMethodException e3) {
            int i3 = Log.i(TAG, "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ListPopupWindow(@NonNull Context context) {
        this(context, (AttributeSet) null, C0425R.attr.listPopupWindowStyle);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, C0425R.attr.listPopupWindowStyle);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        ResizePopupRunnable resizePopupRunnable;
        PopupTouchInterceptor popupTouchInterceptor;
        PopupScrollListener popupScrollListener;
        ListSelectorHider listSelectorHider;
        Rect rect;
        Handler handler;
        PopupWindow popupWindow;
        Context context2 = context;
        AttributeSet attrs = attributeSet;
        int defStyleAttr = i;
        int defStyleRes = i2;
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = 1002;
        this.mIsAnimatedFromAnchor = true;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = Integer.MAX_VALUE;
        this.mPromptPosition = 0;
        new ResizePopupRunnable(this);
        this.mResizePopupRunnable = resizePopupRunnable;
        new PopupTouchInterceptor(this);
        this.mTouchInterceptor = popupTouchInterceptor;
        new PopupScrollListener(this);
        this.mScrollListener = popupScrollListener;
        new ListSelectorHider(this);
        this.mHideSelector = listSelectorHider;
        new Rect();
        this.mTempRect = rect;
        this.mContext = context2;
        new Handler(context2.getMainLooper());
        this.mHandler = handler;
        TypedArray a = context2.obtainStyledAttributes(attrs, C0425R.styleable.ListPopupWindow, defStyleAttr, defStyleRes);
        this.mDropDownHorizontalOffset = a.getDimensionPixelOffset(C0425R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.mDropDownVerticalOffset = a.getDimensionPixelOffset(C0425R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        a.recycle();
        new AppCompatPopupWindow(context2, attrs, defStyleAttr, defStyleRes);
        this.mPopup = popupWindow;
        this.mPopup.setInputMethodMode(1);
    }

    public void setAdapter(@Nullable ListAdapter listAdapter) {
        DataSetObserver dataSetObserver;
        ListAdapter adapter = listAdapter;
        if (this.mObserver == null) {
            new PopupDataSetObserver(this);
            this.mObserver = dataSetObserver;
        } else if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
        }
        this.mAdapter = adapter;
        if (adapter != null) {
            adapter.registerDataSetObserver(this.mObserver);
        }
        if (this.mDropDownList != null) {
            this.mDropDownList.setAdapter(this.mAdapter);
        }
    }

    public void setPromptPosition(int position) {
        int i = position;
        this.mPromptPosition = i;
    }

    public int getPromptPosition() {
        return this.mPromptPosition;
    }

    public void setModal(boolean z) {
        boolean modal = z;
        this.mModal = modal;
        this.mPopup.setFocusable(modal);
    }

    public boolean isModal() {
        return this.mModal;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setForceIgnoreOutsideTouch(boolean forceIgnoreOutsideTouch) {
        boolean z = forceIgnoreOutsideTouch;
        this.mForceIgnoreOutsideTouch = z;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setDropDownAlwaysVisible(boolean dropDownAlwaysVisible) {
        boolean z = dropDownAlwaysVisible;
        this.mDropDownAlwaysVisible = z;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }

    public void setSoftInputMode(int mode) {
        this.mPopup.setSoftInputMode(mode);
    }

    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }

    public void setListSelector(Drawable selector) {
        Drawable drawable = selector;
        this.mDropDownListHighlight = drawable;
    }

    @Nullable
    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public void setBackgroundDrawable(@Nullable Drawable d) {
        this.mPopup.setBackgroundDrawable(d);
    }

    public void setAnimationStyle(@StyleRes int animationStyle) {
        this.mPopup.setAnimationStyle(animationStyle);
    }

    @StyleRes
    public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }

    @Nullable
    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    public void setAnchorView(@Nullable View anchor) {
        View view = anchor;
        this.mDropDownAnchorView = view;
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public void setHorizontalOffset(int offset) {
        int i = offset;
        this.mDropDownHorizontalOffset = i;
    }

    public int getVerticalOffset() {
        if (!this.mDropDownVerticalOffsetSet) {
            return 0;
        }
        return this.mDropDownVerticalOffset;
    }

    public void setVerticalOffset(int offset) {
        this.mDropDownVerticalOffset = offset;
        this.mDropDownVerticalOffsetSet = true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setEpicenterBounds(Rect bounds) {
        Rect rect = bounds;
        this.mEpicenterBounds = rect;
    }

    public void setDropDownGravity(int gravity) {
        int i = gravity;
        this.mDropDownGravity = i;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    public void setWidth(int width) {
        int i = width;
        this.mDropDownWidth = i;
    }

    public void setContentWidth(int i) {
        int width = i;
        Drawable popupBackground = this.mPopup.getBackground();
        if (popupBackground != null) {
            boolean padding = popupBackground.getPadding(this.mTempRect);
            this.mDropDownWidth = this.mTempRect.left + this.mTempRect.right + width;
            return;
        }
        setWidth(width);
    }

    public int getHeight() {
        return this.mDropDownHeight;
    }

    public void setHeight(int i) {
        Throwable th;
        int height = i;
        if (height >= 0 || -2 == height || -1 == height) {
            this.mDropDownHeight = height;
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
        throw th2;
    }

    public void setWindowLayoutType(int layoutType) {
        int i = layoutType;
        this.mDropDownWindowLayoutType = i;
    }

    public void setOnItemClickListener(@Nullable AdapterView.OnItemClickListener clickListener) {
        AdapterView.OnItemClickListener onItemClickListener = clickListener;
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnItemSelectedListener(@Nullable AdapterView.OnItemSelectedListener selectedListener) {
        AdapterView.OnItemSelectedListener onItemSelectedListener = selectedListener;
        this.mItemSelectedListener = onItemSelectedListener;
    }

    public void setPromptView(@Nullable View view) {
        View prompt = view;
        boolean showing = isShowing();
        if (showing) {
            removePromptView();
        }
        this.mPromptView = prompt;
        if (showing) {
            show();
        }
    }

    public void postShow() {
        boolean post = this.mHandler.post(this.mShowDropDownRunnable);
    }

    public void show() {
        int widthSpec;
        int heightSpec;
        int widthSpec2;
        int heightSpec2;
        int height = buildDropDown();
        boolean noInputMethod = isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
        if (!this.mPopup.isShowing()) {
            if (this.mDropDownWidth == -1) {
                widthSpec = -1;
            } else if (this.mDropDownWidth == -2) {
                widthSpec = getAnchorView().getWidth();
            } else {
                widthSpec = this.mDropDownWidth;
            }
            if (this.mDropDownHeight == -1) {
                heightSpec = -1;
            } else if (this.mDropDownHeight == -2) {
                heightSpec = height;
            } else {
                heightSpec = this.mDropDownHeight;
            }
            this.mPopup.setWidth(widthSpec);
            this.mPopup.setHeight(heightSpec);
            setPopupClipToScreenEnabled(true);
            this.mPopup.setOutsideTouchable(!this.mForceIgnoreOutsideTouch && !this.mDropDownAlwaysVisible);
            this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
            if (this.mOverlapAnchorSet) {
                PopupWindowCompat.setOverlapAnchor(this.mPopup, this.mOverlapAnchor);
            }
            if (sSetEpicenterBoundsMethod != null) {
                try {
                    Object invoke = sSetEpicenterBoundsMethod.invoke(this.mPopup, new Object[]{this.mEpicenterBounds});
                } catch (Exception e) {
                    int e2 = Log.e(TAG, "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
            PopupWindowCompat.showAsDropDown(this.mPopup, getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
            this.mDropDownList.setSelection(-1);
            if (!this.mModal || this.mDropDownList.isInTouchMode()) {
                clearListSelection();
            }
            if (!this.mModal) {
                boolean post = this.mHandler.post(this.mHideSelector);
            }
        } else if (ViewCompat.isAttachedToWindow(getAnchorView())) {
            if (this.mDropDownWidth == -1) {
                widthSpec2 = -1;
            } else if (this.mDropDownWidth == -2) {
                widthSpec2 = getAnchorView().getWidth();
            } else {
                widthSpec2 = this.mDropDownWidth;
            }
            if (this.mDropDownHeight == -1) {
                heightSpec2 = noInputMethod ? height : -1;
                if (noInputMethod) {
                    this.mPopup.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
                    this.mPopup.setHeight(0);
                } else {
                    this.mPopup.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
                    this.mPopup.setHeight(-1);
                }
            } else {
                heightSpec2 = this.mDropDownHeight == -2 ? height : this.mDropDownHeight;
            }
            this.mPopup.setOutsideTouchable(!this.mForceIgnoreOutsideTouch && !this.mDropDownAlwaysVisible);
            this.mPopup.update(getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, widthSpec2 < 0 ? -1 : widthSpec2, heightSpec2 < 0 ? -1 : heightSpec2);
        }
    }

    public void dismiss() {
        this.mPopup.dismiss();
        removePromptView();
        this.mPopup.setContentView((View) null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    public void setOnDismissListener(@Nullable PopupWindow.OnDismissListener listener) {
        this.mPopup.setOnDismissListener(listener);
    }

    private void removePromptView() {
        if (this.mPromptView != null) {
            ViewParent parent = this.mPromptView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mPromptView);
            }
        }
    }

    public void setInputMethodMode(int mode) {
        this.mPopup.setInputMethodMode(mode);
    }

    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }

    public void setSelection(int i) {
        int position = i;
        DropDownListView list = this.mDropDownList;
        if (isShowing() && list != null) {
            list.setListSelectionHidden(false);
            list.setSelection(position);
            if (list.getChoiceMode() != 0) {
                list.setItemChecked(position, true);
            }
        }
    }

    public void clearListSelection() {
        DropDownListView list = this.mDropDownList;
        if (list != null) {
            list.setListSelectionHidden(true);
            list.requestLayout();
        }
    }

    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == 2;
    }

    public boolean performItemClick(int i) {
        int position = i;
        if (!isShowing()) {
            return false;
        }
        if (this.mItemClickListener != null) {
            DropDownListView list = this.mDropDownList;
            this.mItemClickListener.onItemClick(list, list.getChildAt(position - list.getFirstVisiblePosition()), position, list.getAdapter().getItemId(position));
        }
        return true;
    }

    @Nullable
    public Object getSelectedItem() {
        if (!isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedItem();
    }

    public int getSelectedItemPosition() {
        if (!isShowing()) {
            return -1;
        }
        return this.mDropDownList.getSelectedItemPosition();
    }

    public long getSelectedItemId() {
        if (!isShowing()) {
            return Long.MIN_VALUE;
        }
        return this.mDropDownList.getSelectedItemId();
    }

    @Nullable
    public View getSelectedView() {
        if (!isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedView();
    }

    @Nullable
    public ListView getListView() {
        return this.mDropDownList;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public DropDownListView createDropDownListView(Context context, boolean hijackFocus) {
        DropDownListView dropDownListView;
        new DropDownListView(context, hijackFocus);
        return dropDownListView;
    }

    /* access modifiers changed from: package-private */
    public void setListItemExpandMax(int max) {
        int i = max;
        this.mListItemExpandMaximum = i;
    }

    public boolean onKeyDown(int i, @NonNull KeyEvent keyEvent) {
        int lookForSelectablePosition;
        int lookForSelectablePosition2;
        int keyCode = i;
        KeyEvent event = keyEvent;
        if (isShowing() && keyCode != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || !isConfirmKey(keyCode))) {
            int curIndex = this.mDropDownList.getSelectedItemPosition();
            boolean below = !this.mPopup.isAboveAnchor();
            ListAdapter adapter = this.mAdapter;
            int firstItem = Integer.MAX_VALUE;
            int lastItem = Integer.MIN_VALUE;
            if (adapter != null) {
                boolean allEnabled = adapter.areAllItemsEnabled();
                if (allEnabled) {
                    lookForSelectablePosition = 0;
                } else {
                    lookForSelectablePosition = this.mDropDownList.lookForSelectablePosition(0, true);
                }
                firstItem = lookForSelectablePosition;
                if (allEnabled) {
                    lookForSelectablePosition2 = adapter.getCount() - 1;
                } else {
                    lookForSelectablePosition2 = this.mDropDownList.lookForSelectablePosition(adapter.getCount() - 1, false);
                }
                lastItem = lookForSelectablePosition2;
            }
            if ((!below || keyCode != 19 || curIndex > firstItem) && (below || keyCode != 20 || curIndex < lastItem)) {
                this.mDropDownList.setListSelectionHidden(false);
                if (this.mDropDownList.onKeyDown(keyCode, event)) {
                    this.mPopup.setInputMethodMode(2);
                    boolean requestFocusFromTouch = this.mDropDownList.requestFocusFromTouch();
                    show();
                    switch (keyCode) {
                        case 19:
                        case 20:
                        case 23:
                        case 66:
                            return true;
                    }
                } else if (!below || keyCode != 20) {
                    if (!below && keyCode == 19 && curIndex == firstItem) {
                        return true;
                    }
                } else if (curIndex == lastItem) {
                    return true;
                }
            } else {
                clearListSelection();
                this.mPopup.setInputMethodMode(1);
                show();
                return true;
            }
        }
        return false;
    }

    public boolean onKeyUp(int i, @NonNull KeyEvent keyEvent) {
        int keyCode = i;
        KeyEvent event = keyEvent;
        if (!isShowing() || this.mDropDownList.getSelectedItemPosition() < 0) {
            return false;
        }
        boolean consumed = this.mDropDownList.onKeyUp(keyCode, event);
        if (consumed && isConfirmKey(keyCode)) {
            dismiss();
        }
        return consumed;
    }

    public boolean onKeyPreIme(int keyCode, @NonNull KeyEvent keyEvent) {
        KeyEvent event = keyEvent;
        if (keyCode == 4 && isShowing()) {
            View anchorView = this.mDropDownAnchorView;
            if (event.getAction() == 0 && event.getRepeatCount() == 0) {
                KeyEvent.DispatcherState state = anchorView.getKeyDispatcherState();
                if (state != null) {
                    state.startTracking(event, this);
                }
                return true;
            } else if (event.getAction() == 1) {
                KeyEvent.DispatcherState state2 = anchorView.getKeyDispatcherState();
                if (state2 != null) {
                    state2.handleUpEvent(event);
                }
                if (event.isTracking() && !event.isCanceled()) {
                    dismiss();
                    return true;
                }
            }
        }
        return false;
    }

    public View.OnTouchListener createDragToOpenListener(View src) {
        View.OnTouchListener onTouchListener;
        new ForwardingListener(this, src) {
            final /* synthetic */ ListPopupWindow this$0;

            {
                this.this$0 = this$0;
            }

            public ListPopupWindow getPopup() {
                return this.this$0;
            }
        };
        return onTouchListener;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: android.support.v7.widget.DropDownListView} */
    /* JADX WARNING: type inference failed for: r11v107, types: [android.widget.LinearLayout] */
    /* JADX WARNING: type inference failed for: r11v112, types: [android.widget.LinearLayout] */
    /* JADX WARNING: type inference failed for: r11v113, types: [android.widget.LinearLayout] */
    /* JADX WARNING: type inference failed for: r11v114, types: [android.widget.LinearLayout] */
    /* JADX WARNING: type inference failed for: r11v115, types: [android.widget.LinearLayout] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int buildDropDown() {
        /*
            r18 = this;
            r0 = r18
            r11 = 0
            r2 = r11
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            if (r11 != 0) goto L_0x018e
            r11 = r0
            android.content.Context r11 = r11.mContext
            r3 = r11
            r11 = r0
            android.support.v7.widget.ListPopupWindow$2 r12 = new android.support.v7.widget.ListPopupWindow$2
            r17 = r12
            r12 = r17
            r13 = r17
            r14 = r0
            r13.<init>(r14)
            r11.mShowDropDownRunnable = r12
            r11 = r0
            r12 = r0
            r13 = r3
            r14 = r0
            boolean r14 = r14.mModal
            if (r14 != 0) goto L_0x016b
            r14 = 1
        L_0x0025:
            android.support.v7.widget.DropDownListView r12 = r12.createDropDownListView(r13, r14)
            r11.mDropDownList = r12
            r11 = r0
            android.graphics.drawable.Drawable r11 = r11.mDropDownListHighlight
            if (r11 == 0) goto L_0x0039
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            r12 = r0
            android.graphics.drawable.Drawable r12 = r12.mDropDownListHighlight
            r11.setSelector(r12)
        L_0x0039:
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            r12 = r0
            android.widget.ListAdapter r12 = r12.mAdapter
            r11.setAdapter(r12)
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            r12 = r0
            android.widget.AdapterView$OnItemClickListener r12 = r12.mItemClickListener
            r11.setOnItemClickListener(r12)
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            r12 = 1
            r11.setFocusable(r12)
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            r12 = 1
            r11.setFocusableInTouchMode(r12)
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            android.support.v7.widget.ListPopupWindow$3 r12 = new android.support.v7.widget.ListPopupWindow$3
            r17 = r12
            r12 = r17
            r13 = r17
            r14 = r0
            r13.<init>(r14)
            r11.setOnItemSelectedListener(r12)
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            r12 = r0
            android.support.v7.widget.ListPopupWindow$PopupScrollListener r12 = r12.mScrollListener
            r11.setOnScrollListener(r12)
            r11 = r0
            android.widget.AdapterView$OnItemSelectedListener r11 = r11.mItemSelectedListener
            if (r11 == 0) goto L_0x0082
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            r12 = r0
            android.widget.AdapterView$OnItemSelectedListener r12 = r12.mItemSelectedListener
            r11.setOnItemSelectedListener(r12)
        L_0x0082:
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            r1 = r11
            r11 = r0
            android.view.View r11 = r11.mPromptView
            r4 = r11
            r11 = r4
            if (r11 == 0) goto L_0x010c
            android.widget.LinearLayout r11 = new android.widget.LinearLayout
            r17 = r11
            r11 = r17
            r12 = r17
            r13 = r3
            r12.<init>(r13)
            r5 = r11
            r11 = r5
            r12 = 1
            r11.setOrientation(r12)
            android.widget.LinearLayout$LayoutParams r11 = new android.widget.LinearLayout$LayoutParams
            r17 = r11
            r11 = r17
            r12 = r17
            r13 = -1
            r14 = 0
            r15 = 1065353216(0x3f800000, float:1.0)
            r12.<init>(r13, r14, r15)
            r6 = r11
            r11 = r0
            int r11 = r11.mPromptPosition
            switch(r11) {
                case 0: goto L_0x017b;
                case 1: goto L_0x016e;
                default: goto L_0x00b5;
            }
        L_0x00b5:
            java.lang.String r11 = "ListPopupWindow"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r17 = r12
            r12 = r17
            r13 = r17
            r13.<init>()
            java.lang.String r13 = "Invalid hint position "
            java.lang.StringBuilder r12 = r12.append(r13)
            r13 = r0
            int r13 = r13.mPromptPosition
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            int r11 = android.util.Log.e(r11, r12)
        L_0x00d9:
            r11 = r0
            int r11 = r11.mDropDownWidth
            if (r11 < 0) goto L_0x0188
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = r11
            r11 = r0
            int r11 = r11.mDropDownWidth
            r7 = r11
        L_0x00e5:
            r11 = r7
            r12 = r8
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r12)
            r9 = r11
            r11 = 0
            r10 = r11
            r11 = r4
            r12 = r9
            r13 = 0
            r11.measure(r12, r13)
            r11 = r4
            android.view.ViewGroup$LayoutParams r11 = r11.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r11 = (android.widget.LinearLayout.LayoutParams) r11
            r6 = r11
            r11 = r4
            int r11 = r11.getMeasuredHeight()
            r12 = r6
            int r12 = r12.topMargin
            int r11 = r11 + r12
            r12 = r6
            int r12 = r12.bottomMargin
            int r11 = r11 + r12
            r2 = r11
            r11 = r5
            r1 = r11
        L_0x010c:
            r11 = r0
            android.widget.PopupWindow r11 = r11.mPopup
            r12 = r1
            r11.setContentView(r12)
        L_0x0113:
            r11 = r0
            android.widget.PopupWindow r11 = r11.mPopup
            android.graphics.drawable.Drawable r11 = r11.getBackground()
            r4 = r11
            r11 = r4
            if (r11 == 0) goto L_0x01b7
            r11 = r4
            r12 = r0
            android.graphics.Rect r12 = r12.mTempRect
            boolean r11 = r11.getPadding(r12)
            r11 = r0
            android.graphics.Rect r11 = r11.mTempRect
            int r11 = r11.top
            r12 = r0
            android.graphics.Rect r12 = r12.mTempRect
            int r12 = r12.bottom
            int r11 = r11 + r12
            r3 = r11
            r11 = r0
            boolean r11 = r11.mDropDownVerticalOffsetSet
            if (r11 != 0) goto L_0x0140
            r11 = r0
            r12 = r0
            android.graphics.Rect r12 = r12.mTempRect
            int r12 = r12.top
            int r12 = -r12
            r11.mDropDownVerticalOffset = r12
        L_0x0140:
            r11 = r0
            android.widget.PopupWindow r11 = r11.mPopup
            int r11 = r11.getInputMethodMode()
            r12 = 2
            if (r11 != r12) goto L_0x01c0
            r11 = 1
        L_0x014b:
            r5 = r11
            r11 = r0
            r12 = r0
            android.view.View r12 = r12.getAnchorView()
            r13 = r0
            int r13 = r13.mDropDownVerticalOffset
            r14 = r5
            int r11 = r11.getMaxAvailableHeight(r12, r13, r14)
            r6 = r11
            r11 = r0
            boolean r11 = r11.mDropDownAlwaysVisible
            if (r11 != 0) goto L_0x0166
            r11 = r0
            int r11 = r11.mDropDownHeight
            r12 = -1
            if (r11 != r12) goto L_0x01c2
        L_0x0166:
            r11 = r6
            r12 = r3
            int r11 = r11 + r12
            r0 = r11
        L_0x016a:
            return r0
        L_0x016b:
            r14 = 0
            goto L_0x0025
        L_0x016e:
            r11 = r5
            r12 = r1
            r13 = r6
            r11.addView(r12, r13)
            r11 = r5
            r12 = r4
            r11.addView(r12)
            goto L_0x00d9
        L_0x017b:
            r11 = r5
            r12 = r4
            r11.addView(r12)
            r11 = r5
            r12 = r1
            r13 = r6
            r11.addView(r12, r13)
            goto L_0x00d9
        L_0x0188:
            r11 = 0
            r8 = r11
            r11 = 0
            r7 = r11
            goto L_0x00e5
        L_0x018e:
            r11 = r0
            android.widget.PopupWindow r11 = r11.mPopup
            android.view.View r11 = r11.getContentView()
            android.view.ViewGroup r11 = (android.view.ViewGroup) r11
            r1 = r11
            r11 = r0
            android.view.View r11 = r11.mPromptView
            r3 = r11
            r11 = r3
            if (r11 == 0) goto L_0x0113
            r11 = r3
            android.view.ViewGroup$LayoutParams r11 = r11.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r11 = (android.widget.LinearLayout.LayoutParams) r11
            r4 = r11
            r11 = r3
            int r11 = r11.getMeasuredHeight()
            r12 = r4
            int r12 = r12.topMargin
            int r11 = r11 + r12
            r12 = r4
            int r12 = r12.bottomMargin
            int r11 = r11 + r12
            r2 = r11
            goto L_0x0113
        L_0x01b7:
            r11 = r0
            android.graphics.Rect r11 = r11.mTempRect
            r11.setEmpty()
            r11 = 0
            r3 = r11
            goto L_0x0140
        L_0x01c0:
            r11 = 0
            goto L_0x014b
        L_0x01c2:
            r11 = r0
            int r11 = r11.mDropDownWidth
            switch(r11) {
                case -2: goto L_0x0203;
                case -1: goto L_0x0224;
                default: goto L_0x01c8;
            }
        L_0x01c8:
            r11 = r0
            int r11 = r11.mDropDownWidth
            r12 = 1073741824(0x40000000, float:2.0)
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r12)
            r7 = r11
        L_0x01d2:
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            r12 = r7
            r13 = 0
            r14 = -1
            r15 = r6
            r16 = r2
            int r15 = r15 - r16
            r16 = -1
            int r11 = r11.measureHeightOfChildrenCompat(r12, r13, r14, r15, r16)
            r8 = r11
            r11 = r8
            if (r11 <= 0) goto L_0x01fd
            r11 = r0
            android.support.v7.widget.DropDownListView r11 = r11.mDropDownList
            int r11 = r11.getPaddingTop()
            r12 = r0
            android.support.v7.widget.DropDownListView r12 = r12.mDropDownList
            int r12 = r12.getPaddingBottom()
            int r11 = r11 + r12
            r9 = r11
            r11 = r2
            r12 = r3
            r13 = r9
            int r12 = r12 + r13
            int r11 = r11 + r12
            r2 = r11
        L_0x01fd:
            r11 = r8
            r12 = r2
            int r11 = r11 + r12
            r0 = r11
            goto L_0x016a
        L_0x0203:
            r11 = r0
            android.content.Context r11 = r11.mContext
            android.content.res.Resources r11 = r11.getResources()
            android.util.DisplayMetrics r11 = r11.getDisplayMetrics()
            int r11 = r11.widthPixels
            r12 = r0
            android.graphics.Rect r12 = r12.mTempRect
            int r12 = r12.left
            r13 = r0
            android.graphics.Rect r13 = r13.mTempRect
            int r13 = r13.right
            int r12 = r12 + r13
            int r11 = r11 - r12
            r12 = -2147483648(0xffffffff80000000, float:-0.0)
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r12)
            r7 = r11
            goto L_0x01d2
        L_0x0224:
            r11 = r0
            android.content.Context r11 = r11.mContext
            android.content.res.Resources r11 = r11.getResources()
            android.util.DisplayMetrics r11 = r11.getDisplayMetrics()
            int r11 = r11.widthPixels
            r12 = r0
            android.graphics.Rect r12 = r12.mTempRect
            int r12 = r12.left
            r13 = r0
            android.graphics.Rect r13 = r13.mTempRect
            int r13 = r13.right
            int r12 = r12 + r13
            int r11 = r11 - r12
            r12 = 1073741824(0x40000000, float:2.0)
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r12)
            r7 = r11
            goto L_0x01d2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.ListPopupWindow.buildDropDown():int");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setOverlapAnchor(boolean overlapAnchor) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = overlapAnchor;
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow$PopupDataSetObserver */
    private class PopupDataSetObserver extends DataSetObserver {
        final /* synthetic */ ListPopupWindow this$0;

        PopupDataSetObserver(ListPopupWindow listPopupWindow) {
            this.this$0 = listPopupWindow;
        }

        public void onChanged() {
            if (this.this$0.isShowing()) {
                this.this$0.show();
            }
        }

        public void onInvalidated() {
            this.this$0.dismiss();
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow$ListSelectorHider */
    private class ListSelectorHider implements Runnable {
        final /* synthetic */ ListPopupWindow this$0;

        ListSelectorHider(ListPopupWindow listPopupWindow) {
            this.this$0 = listPopupWindow;
        }

        public void run() {
            this.this$0.clearListSelection();
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow$ResizePopupRunnable */
    private class ResizePopupRunnable implements Runnable {
        final /* synthetic */ ListPopupWindow this$0;

        ResizePopupRunnable(ListPopupWindow listPopupWindow) {
            this.this$0 = listPopupWindow;
        }

        public void run() {
            if (this.this$0.mDropDownList != null && ViewCompat.isAttachedToWindow(this.this$0.mDropDownList) && this.this$0.mDropDownList.getCount() > this.this$0.mDropDownList.getChildCount() && this.this$0.mDropDownList.getChildCount() <= this.this$0.mListItemExpandMaximum) {
                this.this$0.mPopup.setInputMethodMode(2);
                this.this$0.show();
            }
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow$PopupTouchInterceptor */
    private class PopupTouchInterceptor implements View.OnTouchListener {
        final /* synthetic */ ListPopupWindow this$0;

        PopupTouchInterceptor(ListPopupWindow listPopupWindow) {
            this.this$0 = listPopupWindow;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            View view2 = view;
            MotionEvent event = motionEvent;
            int action = event.getAction();
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (action == 0 && this.this$0.mPopup != null && this.this$0.mPopup.isShowing() && x >= 0 && x < this.this$0.mPopup.getWidth() && y >= 0 && y < this.this$0.mPopup.getHeight()) {
                boolean postDelayed = this.this$0.mHandler.postDelayed(this.this$0.mResizePopupRunnable, 250);
            } else if (action == 1) {
                this.this$0.mHandler.removeCallbacks(this.this$0.mResizePopupRunnable);
            }
            return false;
        }
    }

    /* renamed from: android.support.v7.widget.ListPopupWindow$PopupScrollListener */
    private class PopupScrollListener implements AbsListView.OnScrollListener {
        final /* synthetic */ ListPopupWindow this$0;

        PopupScrollListener(ListPopupWindow listPopupWindow) {
            this.this$0 = listPopupWindow;
        }

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        }

        public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            AbsListView absListView2 = absListView;
            if (scrollState == 1 && !this.this$0.isInputMethodNotNeeded() && this.this$0.mPopup.getContentView() != null) {
                this.this$0.mHandler.removeCallbacks(this.this$0.mResizePopupRunnable);
                this.this$0.mResizePopupRunnable.run();
            }
        }
    }

    private static boolean isConfirmKey(int i) {
        int keyCode = i;
        return keyCode == 66 || keyCode == 23;
    }

    private void setPopupClipToScreenEnabled(boolean z) {
        boolean clip = z;
        if (sClipToWindowEnabledMethod != null) {
            try {
                Object invoke = sClipToWindowEnabledMethod.invoke(this.mPopup, new Object[]{Boolean.valueOf(clip)});
            } catch (Exception e) {
                Exception exc = e;
                int i = Log.i(TAG, "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    private int getMaxAvailableHeight(View view, int i, boolean z) {
        View anchor = view;
        int yOffset = i;
        boolean ignoreBottomDecorations = z;
        if (sGetMaxAvailableHeightMethod != null) {
            try {
                Method method = sGetMaxAvailableHeightMethod;
                PopupWindow popupWindow = this.mPopup;
                Object[] objArr = new Object[3];
                objArr[0] = anchor;
                Object[] objArr2 = objArr;
                objArr2[1] = Integer.valueOf(yOffset);
                Object[] objArr3 = objArr2;
                objArr3[2] = Boolean.valueOf(ignoreBottomDecorations);
                return ((Integer) method.invoke(popupWindow, objArr3)).intValue();
            } catch (Exception e) {
                Exception exc = e;
                int i2 = Log.i(TAG, "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.mPopup.getMaxAvailableHeight(anchor, yOffset);
    }
}
