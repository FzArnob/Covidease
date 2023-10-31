package android.support.p003v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.p003v7.app.AlertController;
import android.support.p003v7.appcompat.C0425R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/* renamed from: android.support.v7.app.AlertDialog */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected AlertDialog(@NonNull Context context) {
        this(context, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected AlertDialog(@android.support.annotation.NonNull android.content.Context r11, @android.support.annotation.StyleRes int r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r0
            r4 = r1
            r5 = r1
            r6 = r2
            int r5 = resolveDialogTheme(r5, r6)
            r3.<init>(r4, r5)
            r3 = r0
            android.support.v7.app.AlertController r4 = new android.support.v7.app.AlertController
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r0
            android.content.Context r6 = r6.getContext()
            r7 = r0
            r8 = r0
            android.view.Window r8 = r8.getWindow()
            r5.<init>(r6, r7, r8)
            r3.mAlert = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.app.AlertDialog.<init>(android.content.Context, int):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected AlertDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        this(context, 0);
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
    }

    static int resolveDialogTheme(@NonNull Context context, @StyleRes int i) {
        TypedValue typedValue;
        Context context2 = context;
        int resid = i;
        if (((resid >>> 24) & 255) >= 1) {
            return resid;
        }
        new TypedValue();
        TypedValue outValue = typedValue;
        boolean resolveAttribute = context2.getTheme().resolveAttribute(C0425R.attr.alertDialogTheme, outValue, true);
        return outValue.resourceId;
    }

    public Button getButton(int whichButton) {
        return this.mAlert.getButton(whichButton);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    public void setTitle(CharSequence charSequence) {
        CharSequence title = charSequence;
        super.setTitle(title);
        this.mAlert.setTitle(title);
    }

    public void setCustomTitle(View customTitleView) {
        this.mAlert.setCustomTitle(customTitleView);
    }

    public void setMessage(CharSequence message) {
        this.mAlert.setMessage(message);
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        this.mAlert.setView(view, viewSpacingLeft, viewSpacingTop, viewSpacingRight, viewSpacingBottom);
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setButtonPanelLayoutHint(int layoutHint) {
        this.mAlert.setButtonPanelLayoutHint(layoutHint);
    }

    public void setButton(int whichButton, CharSequence text, Message msg) {
        this.mAlert.setButton(whichButton, text, (DialogInterface.OnClickListener) null, msg, (Drawable) null);
    }

    public void setButton(int whichButton, CharSequence text, DialogInterface.OnClickListener listener) {
        this.mAlert.setButton(whichButton, text, listener, (Message) null, (Drawable) null);
    }

    public void setButton(int whichButton, CharSequence text, Drawable icon, DialogInterface.OnClickListener listener) {
        this.mAlert.setButton(whichButton, text, listener, (Message) null, icon);
    }

    public void setIcon(int resId) {
        this.mAlert.setIcon(resId);
    }

    public void setIcon(Drawable icon) {
        this.mAlert.setIcon(icon);
    }

    public void setIconAttribute(int attrId) {
        TypedValue typedValue;
        new TypedValue();
        TypedValue out = typedValue;
        boolean resolveAttribute = getContext().getTheme().resolveAttribute(attrId, out, true);
        this.mAlert.setIcon(out.resourceId);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAlert.installContent();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int keyCode = i;
        KeyEvent event = keyEvent;
        if (this.mAlert.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        int keyCode = i;
        KeyEvent event = keyEvent;
        if (this.mAlert.onKeyUp(keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    /* renamed from: android.support.v7.app.AlertDialog$Builder */
    public static class Builder {

        /* renamed from: P */
        private final AlertController.AlertParams f31P;
        private final int mTheme;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Builder(@android.support.annotation.NonNull android.content.Context r7) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                r2 = r0
                r3 = r1
                r4 = r1
                r5 = 0
                int r4 = android.support.p003v7.app.AlertDialog.resolveDialogTheme(r4, r5)
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.app.AlertDialog.Builder.<init>(android.content.Context):void");
        }

        public Builder(@NonNull Context context, @StyleRes int i) {
            AlertController.AlertParams alertParams;
            Context context2;
            Context context3 = context;
            int themeResId = i;
            new ContextThemeWrapper(context3, AlertDialog.resolveDialogTheme(context3, themeResId));
            new AlertController.AlertParams(context2);
            this.f31P = alertParams;
            this.mTheme = themeResId;
        }

        @NonNull
        public Context getContext() {
            return this.f31P.mContext;
        }

        public Builder setTitle(@StringRes int titleId) {
            this.f31P.mTitle = this.f31P.mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(@Nullable CharSequence title) {
            this.f31P.mTitle = title;
            return this;
        }

        public Builder setCustomTitle(@Nullable View customTitleView) {
            this.f31P.mCustomTitleView = customTitleView;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            this.f31P.mMessage = this.f31P.mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(@Nullable CharSequence message) {
            this.f31P.mMessage = message;
            return this;
        }

        public Builder setIcon(@DrawableRes int iconId) {
            this.f31P.mIconId = iconId;
            return this;
        }

        public Builder setIcon(@Nullable Drawable icon) {
            this.f31P.mIcon = icon;
            return this;
        }

        public Builder setIconAttribute(@AttrRes int attrId) {
            TypedValue typedValue;
            new TypedValue();
            TypedValue out = typedValue;
            boolean resolveAttribute = this.f31P.mContext.getTheme().resolveAttribute(attrId, out, true);
            this.f31P.mIconId = out.resourceId;
            return this;
        }

        public Builder setPositiveButton(@StringRes int textId, DialogInterface.OnClickListener listener) {
            this.f31P.mPositiveButtonText = this.f31P.mContext.getText(textId);
            this.f31P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
            this.f31P.mPositiveButtonText = text;
            this.f31P.mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButtonIcon(Drawable icon) {
            this.f31P.mPositiveButtonIcon = icon;
            return this;
        }

        public Builder setNegativeButton(@StringRes int textId, DialogInterface.OnClickListener listener) {
            this.f31P.mNegativeButtonText = this.f31P.mContext.getText(textId);
            this.f31P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, DialogInterface.OnClickListener listener) {
            this.f31P.mNegativeButtonText = text;
            this.f31P.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNegativeButtonIcon(Drawable icon) {
            this.f31P.mNegativeButtonIcon = icon;
            return this;
        }

        public Builder setNeutralButton(@StringRes int textId, DialogInterface.OnClickListener listener) {
            this.f31P.mNeutralButtonText = this.f31P.mContext.getText(textId);
            this.f31P.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, DialogInterface.OnClickListener listener) {
            this.f31P.mNeutralButtonText = text;
            this.f31P.mNeutralButtonListener = listener;
            return this;
        }

        public Builder setNeutralButtonIcon(Drawable icon) {
            this.f31P.mNeutralButtonIcon = icon;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.f31P.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.f31P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.f31P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.f31P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(@ArrayRes int itemsId, DialogInterface.OnClickListener listener) {
            this.f31P.mItems = this.f31P.mContext.getResources().getTextArray(itemsId);
            this.f31P.mOnClickListener = listener;
            return this;
        }

        public Builder setItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
            this.f31P.mItems = items;
            this.f31P.mOnClickListener = listener;
            return this;
        }

        public Builder setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener) {
            this.f31P.mAdapter = adapter;
            this.f31P.mOnClickListener = listener;
            return this;
        }

        public Builder setCursor(Cursor cursor, DialogInterface.OnClickListener listener, String labelColumn) {
            this.f31P.mCursor = cursor;
            this.f31P.mLabelColumn = labelColumn;
            this.f31P.mOnClickListener = listener;
            return this;
        }

        public Builder setMultiChoiceItems(@ArrayRes int itemsId, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
            this.f31P.mItems = this.f31P.mContext.getResources().getTextArray(itemsId);
            this.f31P.mOnCheckboxClickListener = listener;
            this.f31P.mCheckedItems = checkedItems;
            this.f31P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
            this.f31P.mItems = items;
            this.f31P.mOnCheckboxClickListener = listener;
            this.f31P.mCheckedItems = checkedItems;
            this.f31P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, DialogInterface.OnMultiChoiceClickListener listener) {
            this.f31P.mCursor = cursor;
            this.f31P.mOnCheckboxClickListener = listener;
            this.f31P.mIsCheckedColumn = isCheckedColumn;
            this.f31P.mLabelColumn = labelColumn;
            this.f31P.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(@ArrayRes int itemsId, int checkedItem, DialogInterface.OnClickListener listener) {
            this.f31P.mItems = this.f31P.mContext.getResources().getTextArray(itemsId);
            this.f31P.mOnClickListener = listener;
            this.f31P.mCheckedItem = checkedItem;
            this.f31P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, DialogInterface.OnClickListener listener) {
            this.f31P.mCursor = cursor;
            this.f31P.mOnClickListener = listener;
            this.f31P.mCheckedItem = checkedItem;
            this.f31P.mLabelColumn = labelColumn;
            this.f31P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, DialogInterface.OnClickListener listener) {
            this.f31P.mItems = items;
            this.f31P.mOnClickListener = listener;
            this.f31P.mCheckedItem = checkedItem;
            this.f31P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, DialogInterface.OnClickListener listener) {
            this.f31P.mAdapter = adapter;
            this.f31P.mOnClickListener = listener;
            this.f31P.mCheckedItem = checkedItem;
            this.f31P.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
            this.f31P.mOnItemSelectedListener = listener;
            return this;
        }

        public Builder setView(int layoutResId) {
            this.f31P.mView = null;
            this.f31P.mViewLayoutResId = layoutResId;
            this.f31P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view) {
            this.f31P.mView = view;
            this.f31P.mViewLayoutResId = 0;
            this.f31P.mViewSpacingSpecified = false;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @Deprecated
        public Builder setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
            this.f31P.mView = view;
            this.f31P.mViewLayoutResId = 0;
            this.f31P.mViewSpacingSpecified = true;
            this.f31P.mViewSpacingLeft = viewSpacingLeft;
            this.f31P.mViewSpacingTop = viewSpacingTop;
            this.f31P.mViewSpacingRight = viewSpacingRight;
            this.f31P.mViewSpacingBottom = viewSpacingBottom;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean useInverseBackground) {
            this.f31P.mForceInverseBackground = useInverseBackground;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setRecycleOnMeasureEnabled(boolean enabled) {
            this.f31P.mRecycleOnMeasure = enabled;
            return this;
        }

        public AlertDialog create() {
            AlertDialog alertDialog;
            new AlertDialog(this.f31P.mContext, this.mTheme);
            AlertDialog dialog = alertDialog;
            this.f31P.apply(dialog.mAlert);
            dialog.setCancelable(this.f31P.mCancelable);
            if (this.f31P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(this.f31P.mOnCancelListener);
            dialog.setOnDismissListener(this.f31P.mOnDismissListener);
            if (this.f31P.mOnKeyListener != null) {
                dialog.setOnKeyListener(this.f31P.mOnKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
