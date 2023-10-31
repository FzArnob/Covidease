package android.support.p003v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.NestedScrollView;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* renamed from: android.support.v7.app.AlertController */
class AlertController {
    ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final View.OnClickListener mButtonHandler;
    private final int mButtonIconDimen;
    Button mButtonNegative;
    private Drawable mButtonNegativeIcon;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    private Drawable mButtonNeutralIcon;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint = 0;
    private int mButtonPanelSideLayout;
    Button mButtonPositive;
    private Drawable mButtonPositiveIcon;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    int mCheckedItem = -1;
    private final Context mContext;
    private View mCustomTitleView;
    final AppCompatDialog mDialog;
    Handler mHandler;
    private Drawable mIcon;
    private int mIconId = 0;
    private ImageView mIconView;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    NestedScrollView mScrollView;
    private boolean mShowTitle;
    int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified = false;
    private int mViewSpacingTop;
    private final Window mWindow;

    /* renamed from: android.support.v7.app.AlertController$ButtonHandler */
    private static final class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialog) {
            WeakReference<DialogInterface> weakReference;
            new WeakReference<>(dialog);
            this.mDialog = weakReference;
        }

        public void handleMessage(Message message) {
            Message msg = message;
            switch (msg.what) {
                case -3:
                case -2:
                case -1:
                    ((DialogInterface.OnClickListener) msg.obj).onClick((DialogInterface) this.mDialog.get(), msg.what);
                    return;
                case 1:
                    ((DialogInterface) msg.obj).dismiss();
                    return;
                default:
                    return;
            }
        }
    }

    private static boolean shouldCenterSingleButton(Context context) {
        TypedValue typedValue;
        new TypedValue();
        TypedValue outValue = typedValue;
        boolean resolveAttribute = context.getTheme().resolveAttribute(C0425R.attr.alertDialogCenterButtons, outValue, true);
        return outValue.data != 0;
    }

    public AlertController(Context context, AppCompatDialog appCompatDialog, Window window) {
        View.OnClickListener onClickListener;
        Handler handler;
        Context context2 = context;
        AppCompatDialog di = appCompatDialog;
        new View.OnClickListener(this) {
            final /* synthetic */ AlertController this$0;

            {
                this.this$0 = this$0;
            }

            public void onClick(View view) {
                Message m;
                View v = view;
                if (v == this.this$0.mButtonPositive && this.this$0.mButtonPositiveMessage != null) {
                    m = Message.obtain(this.this$0.mButtonPositiveMessage);
                } else if (v == this.this$0.mButtonNegative && this.this$0.mButtonNegativeMessage != null) {
                    m = Message.obtain(this.this$0.mButtonNegativeMessage);
                } else if (v != this.this$0.mButtonNeutral || this.this$0.mButtonNeutralMessage == null) {
                    m = null;
                } else {
                    m = Message.obtain(this.this$0.mButtonNeutralMessage);
                }
                if (m != null) {
                    m.sendToTarget();
                }
                this.this$0.mHandler.obtainMessage(1, this.this$0.mDialog).sendToTarget();
            }
        };
        this.mButtonHandler = onClickListener;
        this.mContext = context2;
        this.mDialog = di;
        this.mWindow = window;
        new ButtonHandler(di);
        this.mHandler = handler;
        TypedArray a = context2.obtainStyledAttributes((AttributeSet) null, C0425R.styleable.AlertDialog, C0425R.attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = a.getResourceId(C0425R.styleable.AlertDialog_android_layout, 0);
        this.mButtonPanelSideLayout = a.getResourceId(C0425R.styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.mListLayout = a.getResourceId(C0425R.styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = a.getResourceId(C0425R.styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = a.getResourceId(C0425R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = a.getResourceId(C0425R.styleable.AlertDialog_listItemLayout, 0);
        this.mShowTitle = a.getBoolean(C0425R.styleable.AlertDialog_showTitle, true);
        this.mButtonIconDimen = a.getDimensionPixelSize(C0425R.styleable.AlertDialog_buttonIconDimen, 0);
        a.recycle();
        boolean supportRequestWindowFeature = di.supportRequestWindowFeature(1);
    }

    static boolean canTextInput(View view) {
        View v = view;
        if (v.onCheckIsTextEditor()) {
            return true;
        }
        if (!(v instanceof ViewGroup)) {
            return false;
        }
        ViewGroup vg = (ViewGroup) v;
        int i = vg.getChildCount();
        while (i > 0) {
            i--;
            if (canTextInput(vg.getChildAt(i))) {
                return true;
            }
        }
        return false;
    }

    public void installContent() {
        this.mDialog.setContentView(selectContentView());
        setupView();
    }

    private int selectContentView() {
        if (this.mButtonPanelSideLayout == 0) {
            return this.mAlertDialogLayout;
        }
        if (this.mButtonPanelLayoutHint == 1) {
            return this.mButtonPanelSideLayout;
        }
        return this.mAlertDialogLayout;
    }

    public void setTitle(CharSequence charSequence) {
        CharSequence title = charSequence;
        this.mTitle = title;
        if (this.mTitleView != null) {
            this.mTitleView.setText(title);
        }
    }

    public void setCustomTitle(View customTitleView) {
        View view = customTitleView;
        this.mCustomTitleView = view;
    }

    public void setMessage(CharSequence charSequence) {
        CharSequence message = charSequence;
        this.mMessage = message;
        if (this.mMessageView != null) {
            this.mMessageView.setText(message);
        }
    }

    public void setView(int layoutResId) {
        this.mView = null;
        this.mViewLayoutResId = layoutResId;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view) {
        this.mView = view;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        this.mView = view;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = viewSpacingLeft;
        this.mViewSpacingTop = viewSpacingTop;
        this.mViewSpacingRight = viewSpacingRight;
        this.mViewSpacingBottom = viewSpacingBottom;
    }

    public void setButtonPanelLayoutHint(int layoutHint) {
        int i = layoutHint;
        this.mButtonPanelLayoutHint = i;
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        Throwable th;
        int whichButton = i;
        CharSequence text = charSequence;
        DialogInterface.OnClickListener listener = onClickListener;
        Message msg = message;
        Drawable icon = drawable;
        if (msg == null && listener != null) {
            msg = this.mHandler.obtainMessage(whichButton, listener);
        }
        switch (whichButton) {
            case -3:
                this.mButtonNeutralText = text;
                this.mButtonNeutralMessage = msg;
                this.mButtonNeutralIcon = icon;
                return;
            case -2:
                this.mButtonNegativeText = text;
                this.mButtonNegativeMessage = msg;
                this.mButtonNegativeIcon = icon;
                return;
            case -1:
                this.mButtonPositiveText = text;
                this.mButtonPositiveMessage = msg;
                this.mButtonPositiveIcon = icon;
                return;
            default:
                Throwable th2 = th;
                new IllegalArgumentException("Button does not exist");
                throw th2;
        }
    }

    public void setIcon(int i) {
        int resId = i;
        this.mIcon = null;
        this.mIconId = resId;
        if (this.mIconView == null) {
            return;
        }
        if (resId != 0) {
            this.mIconView.setVisibility(0);
            this.mIconView.setImageResource(this.mIconId);
            return;
        }
        this.mIconView.setVisibility(8);
    }

    public void setIcon(Drawable drawable) {
        Drawable icon = drawable;
        this.mIcon = icon;
        this.mIconId = 0;
        if (this.mIconView == null) {
            return;
        }
        if (icon != null) {
            this.mIconView.setVisibility(0);
            this.mIconView.setImageDrawable(icon);
            return;
        }
        this.mIconView.setVisibility(8);
    }

    public int getIconAttributeResId(int attrId) {
        TypedValue typedValue;
        new TypedValue();
        TypedValue out = typedValue;
        boolean resolveAttribute = this.mContext.getTheme().resolveAttribute(attrId, out, true);
        return out.resourceId;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public Button getButton(int whichButton) {
        switch (whichButton) {
            case -3:
                return this.mButtonNeutral;
            case -2:
                return this.mButtonNegative;
            case -1:
                return this.mButtonPositive;
            default:
                return null;
        }
    }

    public boolean onKeyDown(int i, KeyEvent event) {
        int i2 = i;
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(event);
    }

    public boolean onKeyUp(int i, KeyEvent event) {
        int i2 = i;
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(event);
    }

    @Nullable
    private ViewGroup resolvePanel(@Nullable View view, @Nullable View view2) {
        View customPanel = view;
        View defaultPanel = view2;
        if (customPanel == null) {
            if (defaultPanel instanceof ViewStub) {
                defaultPanel = ((ViewStub) defaultPanel).inflate();
            }
            return (ViewGroup) defaultPanel;
        }
        if (defaultPanel != null) {
            ViewParent parent = defaultPanel.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(defaultPanel);
            }
        }
        if (customPanel instanceof ViewStub) {
            customPanel = ((ViewStub) customPanel).inflate();
        }
        return (ViewGroup) customPanel;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x012a, code lost:
        if (r2.mListView != null) goto L_0x012c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupView() {
        /*
            r24 = this;
            r2 = r24
            r19 = r2
            r0 = r19
            android.view.Window r0 = r0.mWindow
            r19 = r0
            int r20 = android.support.p003v7.appcompat.C0425R.C0427id.parentPanel
            android.view.View r19 = r19.findViewById(r20)
            r3 = r19
            r19 = r3
            int r20 = android.support.p003v7.appcompat.C0425R.C0427id.topPanel
            android.view.View r19 = r19.findViewById(r20)
            r4 = r19
            r19 = r3
            int r20 = android.support.p003v7.appcompat.C0425R.C0427id.contentPanel
            android.view.View r19 = r19.findViewById(r20)
            r5 = r19
            r19 = r3
            int r20 = android.support.p003v7.appcompat.C0425R.C0427id.buttonPanel
            android.view.View r19 = r19.findViewById(r20)
            r6 = r19
            r19 = r3
            int r20 = android.support.p003v7.appcompat.C0425R.C0427id.customPanel
            android.view.View r19 = r19.findViewById(r20)
            android.view.ViewGroup r19 = (android.view.ViewGroup) r19
            r7 = r19
            r19 = r2
            r20 = r7
            r19.setupCustomContent(r20)
            r19 = r7
            int r20 = android.support.p003v7.appcompat.C0425R.C0427id.topPanel
            android.view.View r19 = r19.findViewById(r20)
            r8 = r19
            r19 = r7
            int r20 = android.support.p003v7.appcompat.C0425R.C0427id.contentPanel
            android.view.View r19 = r19.findViewById(r20)
            r9 = r19
            r19 = r7
            int r20 = android.support.p003v7.appcompat.C0425R.C0427id.buttonPanel
            android.view.View r19 = r19.findViewById(r20)
            r10 = r19
            r19 = r2
            r20 = r8
            r21 = r4
            android.view.ViewGroup r19 = r19.resolvePanel(r20, r21)
            r11 = r19
            r19 = r2
            r20 = r9
            r21 = r5
            android.view.ViewGroup r19 = r19.resolvePanel(r20, r21)
            r12 = r19
            r19 = r2
            r20 = r10
            r21 = r6
            android.view.ViewGroup r19 = r19.resolvePanel(r20, r21)
            r13 = r19
            r19 = r2
            r20 = r12
            r19.setupContent(r20)
            r19 = r2
            r20 = r13
            r19.setupButtons(r20)
            r19 = r2
            r20 = r11
            r19.setupTitle(r20)
            r19 = r7
            if (r19 == 0) goto L_0x01e5
            r19 = r7
            int r19 = r19.getVisibility()
            r20 = 8
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x01e5
            r19 = 1
        L_0x00ae:
            r14 = r19
            r19 = r11
            if (r19 == 0) goto L_0x01e9
            r19 = r11
            int r19 = r19.getVisibility()
            r20 = 8
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x01e9
            r19 = 1
        L_0x00c4:
            r15 = r19
            r19 = r13
            if (r19 == 0) goto L_0x01ed
            r19 = r13
            int r19 = r19.getVisibility()
            r20 = 8
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x01ed
            r19 = 1
        L_0x00da:
            r16 = r19
            r19 = r16
            if (r19 != 0) goto L_0x00f9
            r19 = r12
            if (r19 == 0) goto L_0x00f9
            r19 = r12
            int r20 = android.support.p003v7.appcompat.C0425R.C0427id.textSpacerNoButtons
            android.view.View r19 = r19.findViewById(r20)
            r17 = r19
            r19 = r17
            if (r19 == 0) goto L_0x00f9
            r19 = r17
            r20 = 0
            r19.setVisibility(r20)
        L_0x00f9:
            r19 = r15
            if (r19 == 0) goto L_0x01f1
            r19 = r2
            r0 = r19
            android.support.v4.widget.NestedScrollView r0 = r0.mScrollView
            r19 = r0
            if (r19 == 0) goto L_0x0114
            r19 = r2
            r0 = r19
            android.support.v4.widget.NestedScrollView r0 = r0.mScrollView
            r19 = r0
            r20 = 1
            r19.setClipToPadding(r20)
        L_0x0114:
            r19 = 0
            r17 = r19
            r19 = r2
            r0 = r19
            java.lang.CharSequence r0 = r0.mMessage
            r19 = r0
            if (r19 != 0) goto L_0x012c
            r19 = r2
            r0 = r19
            android.widget.ListView r0 = r0.mListView
            r19 = r0
            if (r19 == 0) goto L_0x0136
        L_0x012c:
            r19 = r11
            int r20 = android.support.p003v7.appcompat.C0425R.C0427id.titleDividerNoCustom
            android.view.View r19 = r19.findViewById(r20)
            r17 = r19
        L_0x0136:
            r19 = r17
            if (r19 == 0) goto L_0x0141
            r19 = r17
            r20 = 0
            r19.setVisibility(r20)
        L_0x0141:
            r19 = r2
            r0 = r19
            android.widget.ListView r0 = r0.mListView
            r19 = r0
            r0 = r19
            boolean r0 = r0 instanceof android.support.p003v7.app.AlertController.RecycleListView
            r19 = r0
            if (r19 == 0) goto L_0x0162
            r19 = r2
            r0 = r19
            android.widget.ListView r0 = r0.mListView
            r19 = r0
            android.support.v7.app.AlertController$RecycleListView r19 = (android.support.p003v7.app.AlertController.RecycleListView) r19
            r20 = r15
            r21 = r16
            r19.setHasDecor(r20, r21)
        L_0x0162:
            r19 = r14
            if (r19 != 0) goto L_0x019b
            r19 = r2
            r0 = r19
            android.widget.ListView r0 = r0.mListView
            r19 = r0
            if (r19 == 0) goto L_0x020c
            r19 = r2
            r0 = r19
            android.widget.ListView r0 = r0.mListView
            r19 = r0
        L_0x0178:
            r17 = r19
            r19 = r17
            if (r19 == 0) goto L_0x019b
            r19 = r15
            if (r19 == 0) goto L_0x0216
            r19 = 1
        L_0x0184:
            r20 = r16
            if (r20 == 0) goto L_0x021a
            r20 = 2
        L_0x018a:
            r19 = r19 | r20
            r18 = r19
            r19 = r2
            r20 = r12
            r21 = r17
            r22 = r18
            r23 = 3
            r19.setScrollIndicators(r20, r21, r22, r23)
        L_0x019b:
            r19 = r2
            r0 = r19
            android.widget.ListView r0 = r0.mListView
            r19 = r0
            r17 = r19
            r19 = r17
            if (r19 == 0) goto L_0x01e4
            r19 = r2
            r0 = r19
            android.widget.ListAdapter r0 = r0.mAdapter
            r19 = r0
            if (r19 == 0) goto L_0x01e4
            r19 = r17
            r20 = r2
            r0 = r20
            android.widget.ListAdapter r0 = r0.mAdapter
            r20 = r0
            r19.setAdapter(r20)
            r19 = r2
            r0 = r19
            int r0 = r0.mCheckedItem
            r19 = r0
            r18 = r19
            r19 = r18
            r20 = -1
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x01e4
            r19 = r17
            r20 = r18
            r21 = 1
            r19.setItemChecked(r20, r21)
            r19 = r17
            r20 = r18
            r19.setSelection(r20)
        L_0x01e4:
            return
        L_0x01e5:
            r19 = 0
            goto L_0x00ae
        L_0x01e9:
            r19 = 0
            goto L_0x00c4
        L_0x01ed:
            r19 = 0
            goto L_0x00da
        L_0x01f1:
            r19 = r12
            if (r19 == 0) goto L_0x0141
            r19 = r12
            int r20 = android.support.p003v7.appcompat.C0425R.C0427id.textSpacerNoTitle
            android.view.View r19 = r19.findViewById(r20)
            r17 = r19
            r19 = r17
            if (r19 == 0) goto L_0x0141
            r19 = r17
            r20 = 0
            r19.setVisibility(r20)
            goto L_0x0141
        L_0x020c:
            r19 = r2
            r0 = r19
            android.support.v4.widget.NestedScrollView r0 = r0.mScrollView
            r19 = r0
            goto L_0x0178
        L_0x0216:
            r19 = 0
            goto L_0x0184
        L_0x021a:
            r20 = 0
            goto L_0x018a
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.app.AlertController.setupView():void");
    }

    private void setScrollIndicators(ViewGroup viewGroup, View view, int i, int i2) {
        AbsListView.OnScrollListener onScrollListener;
        Runnable runnable;
        NestedScrollView.OnScrollChangeListener onScrollChangeListener;
        Runnable runnable2;
        ViewGroup contentPanel = viewGroup;
        View content = view;
        int indicators = i;
        int mask = i2;
        View indicatorUp = this.mWindow.findViewById(C0425R.C0427id.scrollIndicatorUp);
        View indicatorDown = this.mWindow.findViewById(C0425R.C0427id.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            ViewCompat.setScrollIndicators(content, indicators, mask);
            if (indicatorUp != null) {
                contentPanel.removeView(indicatorUp);
            }
            if (indicatorDown != null) {
                contentPanel.removeView(indicatorDown);
                return;
            }
            return;
        }
        if (indicatorUp != null && (indicators & 1) == 0) {
            contentPanel.removeView(indicatorUp);
            indicatorUp = null;
        }
        if (indicatorDown != null && (indicators & 2) == 0) {
            contentPanel.removeView(indicatorDown);
            indicatorDown = null;
        }
        if (indicatorUp != null || indicatorDown != null) {
            View top = indicatorUp;
            View bottom = indicatorDown;
            if (this.mMessage != null) {
                final View view2 = top;
                final View view3 = bottom;
                new NestedScrollView.OnScrollChangeListener(this) {
                    final /* synthetic */ AlertController this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onScrollChange(NestedScrollView v, int i, int i2, int i3, int i4) {
                        int i5 = i;
                        int i6 = i2;
                        int i7 = i3;
                        int i8 = i4;
                        AlertController.manageScrollIndicators(v, view2, view3);
                    }
                };
                this.mScrollView.setOnScrollChangeListener(onScrollChangeListener);
                final View view4 = top;
                final View view5 = bottom;
                new Runnable(this) {
                    final /* synthetic */ AlertController this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        AlertController.manageScrollIndicators(this.this$0.mScrollView, view4, view5);
                    }
                };
                boolean post = this.mScrollView.post(runnable2);
            } else if (this.mListView != null) {
                final View view6 = top;
                final View view7 = bottom;
                new AbsListView.OnScrollListener(this) {
                    final /* synthetic */ AlertController this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                    }

                    public void onScroll(AbsListView v, int i, int i2, int i3) {
                        int i4 = i;
                        int i5 = i2;
                        int i6 = i3;
                        AlertController.manageScrollIndicators(v, view6, view7);
                    }
                };
                this.mListView.setOnScrollListener(onScrollListener);
                final View view8 = top;
                final View view9 = bottom;
                new Runnable(this) {
                    final /* synthetic */ AlertController this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        AlertController.manageScrollIndicators(this.this$0.mListView, view8, view9);
                    }
                };
                boolean post2 = this.mListView.post(runnable);
            } else {
                if (top != null) {
                    contentPanel.removeView(top);
                }
                if (bottom != null) {
                    contentPanel.removeView(bottom);
                }
            }
        }
    }

    private void setupCustomContent(ViewGroup viewGroup) {
        View customView;
        ViewGroup.LayoutParams layoutParams;
        ViewGroup customPanel = viewGroup;
        if (this.mView != null) {
            customView = this.mView;
        } else if (this.mViewLayoutResId != 0) {
            customView = LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, customPanel, false);
        } else {
            customView = null;
        }
        boolean hasCustomView = customView != null;
        if (!hasCustomView || !canTextInput(customView)) {
            this.mWindow.setFlags(131072, 131072);
        }
        if (hasCustomView) {
            FrameLayout custom = (FrameLayout) this.mWindow.findViewById(C0425R.C0427id.custom);
            new ViewGroup.LayoutParams(-1, -1);
            custom.addView(customView, layoutParams);
            if (this.mViewSpacingSpecified) {
                custom.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            }
            if (this.mListView != null) {
                ((LinearLayoutCompat.LayoutParams) customPanel.getLayoutParams()).weight = 0.0f;
                return;
            }
            return;
        }
        customPanel.setVisibility(8);
    }

    private void setupTitle(ViewGroup viewGroup) {
        ViewGroup.LayoutParams lp;
        ViewGroup topPanel = viewGroup;
        if (this.mCustomTitleView != null) {
            new ViewGroup.LayoutParams(-1, -2);
            topPanel.addView(this.mCustomTitleView, 0, lp);
            this.mWindow.findViewById(C0425R.C0427id.title_template).setVisibility(8);
            return;
        }
        this.mIconView = (ImageView) this.mWindow.findViewById(16908294);
        if (!(!TextUtils.isEmpty(this.mTitle)) || !this.mShowTitle) {
            this.mWindow.findViewById(C0425R.C0427id.title_template).setVisibility(8);
            this.mIconView.setVisibility(8);
            topPanel.setVisibility(8);
            return;
        }
        this.mTitleView = (TextView) this.mWindow.findViewById(C0425R.C0427id.alertTitle);
        this.mTitleView.setText(this.mTitle);
        if (this.mIconId != 0) {
            this.mIconView.setImageResource(this.mIconId);
        } else if (this.mIcon != null) {
            this.mIconView.setImageDrawable(this.mIcon);
        } else {
            this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
            this.mIconView.setVisibility(8);
        }
    }

    private void setupContent(ViewGroup viewGroup) {
        ViewGroup.LayoutParams layoutParams;
        ViewGroup contentPanel = viewGroup;
        this.mScrollView = (NestedScrollView) this.mWindow.findViewById(C0425R.C0427id.scrollView);
        this.mScrollView.setFocusable(false);
        this.mScrollView.setNestedScrollingEnabled(false);
        this.mMessageView = (TextView) contentPanel.findViewById(16908299);
        if (this.mMessageView != null) {
            if (this.mMessage != null) {
                this.mMessageView.setText(this.mMessage);
                return;
            }
            this.mMessageView.setVisibility(8);
            this.mScrollView.removeView(this.mMessageView);
            if (this.mListView != null) {
                ViewGroup scrollParent = (ViewGroup) this.mScrollView.getParent();
                int childIndex = scrollParent.indexOfChild(this.mScrollView);
                scrollParent.removeViewAt(childIndex);
                new ViewGroup.LayoutParams(-1, -1);
                scrollParent.addView(this.mListView, childIndex, layoutParams);
                return;
            }
            contentPanel.setVisibility(8);
        }
    }

    static void manageScrollIndicators(View view, View view2, View view3) {
        View v = view;
        View upIndicator = view2;
        View downIndicator = view3;
        if (upIndicator != null) {
            upIndicator.setVisibility(v.canScrollVertically(-1) ? 0 : 4);
        }
        if (downIndicator != null) {
            downIndicator.setVisibility(v.canScrollVertically(1) ? 0 : 4);
        }
    }

    private void setupButtons(ViewGroup viewGroup) {
        ViewGroup buttonPanel = viewGroup;
        int whichButtons = 0;
        this.mButtonPositive = (Button) buttonPanel.findViewById(16908313);
        this.mButtonPositive.setOnClickListener(this.mButtonHandler);
        if (!TextUtils.isEmpty(this.mButtonPositiveText) || this.mButtonPositiveIcon != null) {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            if (this.mButtonPositiveIcon != null) {
                this.mButtonPositiveIcon.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonPositive.setCompoundDrawables(this.mButtonPositiveIcon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonPositive.setVisibility(0);
            whichButtons = 0 | 1;
        } else {
            this.mButtonPositive.setVisibility(8);
        }
        this.mButtonNegative = (Button) buttonPanel.findViewById(16908314);
        this.mButtonNegative.setOnClickListener(this.mButtonHandler);
        if (!TextUtils.isEmpty(this.mButtonNegativeText) || this.mButtonNegativeIcon != null) {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            if (this.mButtonNegativeIcon != null) {
                this.mButtonNegativeIcon.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonNegative.setCompoundDrawables(this.mButtonNegativeIcon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonNegative.setVisibility(0);
            whichButtons |= 2;
        } else {
            this.mButtonNegative.setVisibility(8);
        }
        this.mButtonNeutral = (Button) buttonPanel.findViewById(16908315);
        this.mButtonNeutral.setOnClickListener(this.mButtonHandler);
        if (!TextUtils.isEmpty(this.mButtonNeutralText) || this.mButtonNeutralIcon != null) {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            if (this.mButtonPositiveIcon != null) {
                this.mButtonPositiveIcon.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonPositive.setCompoundDrawables(this.mButtonPositiveIcon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonNeutral.setVisibility(0);
            whichButtons |= 4;
        } else {
            this.mButtonNeutral.setVisibility(8);
        }
        if (shouldCenterSingleButton(this.mContext)) {
            if (whichButtons == 1) {
                centerButton(this.mButtonPositive);
            } else if (whichButtons == 2) {
                centerButton(this.mButtonNegative);
            } else if (whichButtons == 4) {
                centerButton(this.mButtonNeutral);
            }
        }
        if (!(whichButtons != 0)) {
            buttonPanel.setVisibility(8);
        }
    }

    private void centerButton(Button button) {
        Button button2 = button;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button2.getLayoutParams();
        params.gravity = 1;
        params.weight = 0.5f;
        button2.setLayoutParams(params);
    }

    /* renamed from: android.support.v7.app.AlertController$RecycleListView */
    public static class RecycleListView extends ListView {
        private final int mPaddingBottomNoButtons;
        private final int mPaddingTopNoTitle;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public RecycleListView(Context context) {
            this(context, (AttributeSet) null);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public RecycleListView(android.content.Context r9, android.util.AttributeSet r10) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r4 = r0
                r5 = r1
                r6 = r2
                r4.<init>(r5, r6)
                r4 = r1
                r5 = r2
                int[] r6 = android.support.p003v7.appcompat.C0425R.styleable.RecycleListView
                android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
                r3 = r4
                r4 = r0
                r5 = r3
                int r6 = android.support.p003v7.appcompat.C0425R.styleable.RecycleListView_paddingBottomNoButtons
                r7 = -1
                int r5 = r5.getDimensionPixelOffset(r6, r7)
                r4.mPaddingBottomNoButtons = r5
                r4 = r0
                r5 = r3
                int r6 = android.support.p003v7.appcompat.C0425R.styleable.RecycleListView_paddingTopNoTitle
                r7 = -1
                int r5 = r5.getDimensionPixelOffset(r6, r7)
                r4.mPaddingTopNoTitle = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.app.AlertController.RecycleListView.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        public void setHasDecor(boolean z, boolean z2) {
            boolean hasTitle = z;
            boolean hasButtons = z2;
            if (!hasButtons || !hasTitle) {
                setPadding(getPaddingLeft(), hasTitle ? getPaddingTop() : this.mPaddingTopNoTitle, getPaddingRight(), hasButtons ? getPaddingBottom() : this.mPaddingBottomNoButtons);
            }
        }
    }

    /* renamed from: android.support.v7.app.AlertController$AlertParams */
    public static class AlertParams {
        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem = -1;
        public boolean[] mCheckedItems;
        public final Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public boolean mForceInverseBackground;
        public Drawable mIcon;
        public int mIconAttrId = 0;
        public int mIconId = 0;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public CharSequence mMessage;
        public Drawable mNegativeButtonIcon;
        public DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public Drawable mNeutralButtonIcon;
        public DialogInterface.OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        public DialogInterface.OnClickListener mOnClickListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public AdapterView.OnItemSelectedListener mOnItemSelectedListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public OnPrepareListViewListener mOnPrepareListViewListener;
        public Drawable mPositiveButtonIcon;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure = true;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified = false;
        public int mViewSpacingTop;

        /* renamed from: android.support.v7.app.AlertController$AlertParams$OnPrepareListViewListener */
        public interface OnPrepareListViewListener {
            void onPrepareListView(ListView listView);
        }

        public AlertParams(Context context) {
            Context context2 = context;
            this.mContext = context2;
            this.mCancelable = true;
            this.mInflater = (LayoutInflater) context2.getSystemService("layout_inflater");
        }

        public void apply(AlertController alertController) {
            AlertController dialog = alertController;
            if (this.mCustomTitleView != null) {
                dialog.setCustomTitle(this.mCustomTitleView);
            } else {
                if (this.mTitle != null) {
                    dialog.setTitle(this.mTitle);
                }
                if (this.mIcon != null) {
                    dialog.setIcon(this.mIcon);
                }
                if (this.mIconId != 0) {
                    dialog.setIcon(this.mIconId);
                }
                if (this.mIconAttrId != 0) {
                    dialog.setIcon(dialog.getIconAttributeResId(this.mIconAttrId));
                }
            }
            if (this.mMessage != null) {
                dialog.setMessage(this.mMessage);
            }
            if (!(this.mPositiveButtonText == null && this.mPositiveButtonIcon == null)) {
                dialog.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, (Message) null, this.mPositiveButtonIcon);
            }
            if (!(this.mNegativeButtonText == null && this.mNegativeButtonIcon == null)) {
                dialog.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, (Message) null, this.mNegativeButtonIcon);
            }
            if (!(this.mNeutralButtonText == null && this.mNeutralButtonIcon == null)) {
                dialog.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, (Message) null, this.mNeutralButtonIcon);
            }
            if (!(this.mItems == null && this.mCursor == null && this.mAdapter == null)) {
                createListView(dialog);
            }
            if (this.mView != null) {
                if (this.mViewSpacingSpecified) {
                    dialog.setView(this.mView, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
                } else {
                    dialog.setView(this.mView);
                }
            } else if (this.mViewLayoutResId != 0) {
                dialog.setView(this.mViewLayoutResId);
            }
        }

        private void createListView(AlertController alertController) {
            int layout;
            ListAdapter listAdapter;
            ListAdapter adapter;
            ListAdapter listAdapter2;
            AdapterView.OnItemClickListener onItemClickListener;
            AdapterView.OnItemClickListener onItemClickListener2;
            ListAdapter listAdapter3;
            ListAdapter listAdapter4;
            AlertController dialog = alertController;
            RecycleListView listView = (RecycleListView) this.mInflater.inflate(dialog.mListLayout, (ViewGroup) null);
            if (!this.mIsMultiChoice) {
                if (this.mIsSingleChoice) {
                    layout = dialog.mSingleChoiceItemLayout;
                } else {
                    layout = dialog.mListItemLayout;
                }
                if (this.mCursor != null) {
                    ListAdapter listAdapter5 = listAdapter2;
                    String[] strArr = new String[1];
                    strArr[0] = this.mLabelColumn;
                    new SimpleCursorAdapter(this.mContext, layout, this.mCursor, strArr, new int[]{16908308});
                    adapter = listAdapter5;
                } else if (this.mAdapter != null) {
                    adapter = this.mAdapter;
                } else {
                    new CheckedItemAdapter(this.mContext, layout, 16908308, this.mItems);
                    adapter = listAdapter;
                }
            } else if (this.mCursor == null) {
                final RecycleListView recycleListView = listView;
                new ArrayAdapter<CharSequence>(this, this.mContext, dialog.mMultiChoiceItemLayout, 16908308, this.mItems) {
                    final /* synthetic */ AlertParams this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public View getView(int i, View convertView, ViewGroup parent) {
                        int position = i;
                        View view = super.getView(position, convertView, parent);
                        if (this.this$0.mCheckedItems != null && this.this$0.mCheckedItems[position]) {
                            recycleListView.setItemChecked(position, true);
                        }
                        return view;
                    }
                };
                adapter = listAdapter4;
            } else {
                final RecycleListView recycleListView2 = listView;
                final AlertController alertController2 = dialog;
                new CursorAdapter(this, this.mContext, this.mCursor, false) {
                    private final int mIsCheckedIndex;
                    private final int mLabelIndex;
                    final /* synthetic */ AlertParams this$0;

                    {
                        this.this$0 = this$0;
                        Cursor cursor = getCursor();
                        this.mLabelIndex = cursor.getColumnIndexOrThrow(this.this$0.mLabelColumn);
                        this.mIsCheckedIndex = cursor.getColumnIndexOrThrow(this.this$0.mIsCheckedColumn);
                    }

                    public void bindView(View view, Context context, Cursor cursor) {
                        Context context2 = context;
                        Cursor cursor2 = cursor;
                        ((CheckedTextView) view.findViewById(16908308)).setText(cursor2.getString(this.mLabelIndex));
                        recycleListView2.setItemChecked(cursor2.getPosition(), cursor2.getInt(this.mIsCheckedIndex) == 1);
                    }

                    public View newView(Context context, Cursor cursor, ViewGroup parent) {
                        Context context2 = context;
                        Cursor cursor2 = cursor;
                        return this.this$0.mInflater.inflate(alertController2.mMultiChoiceItemLayout, parent, false);
                    }
                };
                adapter = listAdapter3;
            }
            if (this.mOnPrepareListViewListener != null) {
                this.mOnPrepareListViewListener.onPrepareListView(listView);
            }
            dialog.mAdapter = adapter;
            dialog.mCheckedItem = this.mCheckedItem;
            if (this.mOnClickListener != null) {
                final AlertController alertController3 = dialog;
                new AdapterView.OnItemClickListener(this) {
                    final /* synthetic */ AlertParams this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long j) {
                        AdapterView<?> adapterView2 = adapterView;
                        View view2 = view;
                        long j2 = j;
                        this.this$0.mOnClickListener.onClick(alertController3.mDialog, position);
                        if (!this.this$0.mIsSingleChoice) {
                            alertController3.mDialog.dismiss();
                        }
                    }
                };
                listView.setOnItemClickListener(onItemClickListener2);
            } else if (this.mOnCheckboxClickListener != null) {
                final RecycleListView recycleListView3 = listView;
                final AlertController alertController4 = dialog;
                new AdapterView.OnItemClickListener(this) {
                    final /* synthetic */ AlertParams this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        AdapterView<?> adapterView2 = adapterView;
                        View view2 = view;
                        int position = i;
                        long j2 = j;
                        if (this.this$0.mCheckedItems != null) {
                            this.this$0.mCheckedItems[position] = recycleListView3.isItemChecked(position);
                        }
                        this.this$0.mOnCheckboxClickListener.onClick(alertController4.mDialog, position, recycleListView3.isItemChecked(position));
                    }
                };
                listView.setOnItemClickListener(onItemClickListener);
            }
            if (this.mOnItemSelectedListener != null) {
                listView.setOnItemSelectedListener(this.mOnItemSelectedListener);
            }
            if (this.mIsSingleChoice) {
                listView.setChoiceMode(1);
            } else if (this.mIsMultiChoice) {
                listView.setChoiceMode(2);
            }
            dialog.mListView = listView;
        }
    }

    /* renamed from: android.support.v7.app.AlertController$CheckedItemAdapter */
    private static class CheckedItemAdapter extends ArrayAdapter<CharSequence> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CheckedItemAdapter(Context context, int resource, int textViewResourceId, CharSequence[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        public boolean hasStableIds() {
            return true;
        }

        public long getItemId(int position) {
            return (long) position;
        }
    }
}
