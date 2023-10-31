package android.support.p003v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.p003v7.appcompat.C0425R;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.TooltipPopup */
class TooltipPopup {
    private static final String TAG = "TooltipPopup";
    private final View mContentView;
    private final Context mContext;
    private final WindowManager.LayoutParams mLayoutParams;
    private final TextView mMessageView;
    private final int[] mTmpAnchorPos = new int[2];
    private final int[] mTmpAppPos = new int[2];
    private final Rect mTmpDisplayFrame;

    TooltipPopup(Context context) {
        WindowManager.LayoutParams layoutParams;
        Rect rect;
        new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        new Rect();
        this.mTmpDisplayFrame = rect;
        this.mContext = context;
        this.mContentView = LayoutInflater.from(this.mContext).inflate(C0425R.layout.abc_tooltip, (ViewGroup) null);
        this.mMessageView = (TextView) this.mContentView.findViewById(C0425R.C0427id.message);
        this.mLayoutParams.setTitle(getClass().getSimpleName());
        this.mLayoutParams.packageName = this.mContext.getPackageName();
        this.mLayoutParams.type = 1002;
        this.mLayoutParams.width = -2;
        this.mLayoutParams.height = -2;
        this.mLayoutParams.format = -3;
        this.mLayoutParams.windowAnimations = C0425R.C0428style.Animation_AppCompat_Tooltip;
        this.mLayoutParams.flags = 24;
    }

    /* access modifiers changed from: package-private */
    public void show(View view, int i, int i2, boolean z, CharSequence charSequence) {
        View anchorView = view;
        int anchorX = i;
        int anchorY = i2;
        boolean fromTouch = z;
        CharSequence tooltipText = charSequence;
        if (isShowing()) {
            hide();
        }
        this.mMessageView.setText(tooltipText);
        computePosition(anchorView, anchorX, anchorY, fromTouch, this.mLayoutParams);
        ((WindowManager) this.mContext.getSystemService("window")).addView(this.mContentView, this.mLayoutParams);
    }

    /* access modifiers changed from: package-private */
    public void hide() {
        if (isShowing()) {
            ((WindowManager) this.mContext.getSystemService("window")).removeView(this.mContentView);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isShowing() {
        return this.mContentView.getParent() != null;
    }

    private void computePosition(View view, int i, int i2, boolean z, WindowManager.LayoutParams layoutParams) {
        int offsetX;
        int offsetBelow;
        int offsetAbove;
        int statusBarHeight;
        View anchorView = view;
        int anchorX = i;
        int anchorY = i2;
        boolean fromTouch = z;
        WindowManager.LayoutParams outParams = layoutParams;
        outParams.token = anchorView.getApplicationWindowToken();
        int tooltipPreciseAnchorThreshold = this.mContext.getResources().getDimensionPixelOffset(C0425R.dimen.tooltip_precise_anchor_threshold);
        if (anchorView.getWidth() >= tooltipPreciseAnchorThreshold) {
            offsetX = anchorX;
        } else {
            offsetX = anchorView.getWidth() / 2;
        }
        if (anchorView.getHeight() >= tooltipPreciseAnchorThreshold) {
            int offsetExtra = this.mContext.getResources().getDimensionPixelOffset(C0425R.dimen.tooltip_precise_anchor_extra_offset);
            offsetBelow = anchorY + offsetExtra;
            offsetAbove = anchorY - offsetExtra;
        } else {
            offsetBelow = anchorView.getHeight();
            offsetAbove = 0;
        }
        outParams.gravity = 49;
        int tooltipOffset = this.mContext.getResources().getDimensionPixelOffset(fromTouch ? C0425R.dimen.tooltip_y_offset_touch : C0425R.dimen.tooltip_y_offset_non_touch);
        View appView = getAppRootView(anchorView);
        if (appView == null) {
            int e = Log.e(TAG, "Cannot find app view");
            return;
        }
        appView.getWindowVisibleDisplayFrame(this.mTmpDisplayFrame);
        if (this.mTmpDisplayFrame.left < 0) {
            if (this.mTmpDisplayFrame.top < 0) {
                Resources res = this.mContext.getResources();
                int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId != 0) {
                    statusBarHeight = res.getDimensionPixelSize(resourceId);
                } else {
                    statusBarHeight = 0;
                }
                DisplayMetrics metrics = res.getDisplayMetrics();
                this.mTmpDisplayFrame.set(0, statusBarHeight, metrics.widthPixels, metrics.heightPixels);
            }
        }
        appView.getLocationOnScreen(this.mTmpAppPos);
        anchorView.getLocationOnScreen(this.mTmpAnchorPos);
        int[] iArr = this.mTmpAnchorPos;
        iArr[0] = iArr[0] - this.mTmpAppPos[0];
        int[] iArr2 = this.mTmpAnchorPos;
        iArr2[1] = iArr2[1] - this.mTmpAppPos[1];
        outParams.x = (this.mTmpAnchorPos[0] + offsetX) - (appView.getWidth() / 2);
        int spec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mContentView.measure(spec, spec);
        int tooltipHeight = this.mContentView.getMeasuredHeight();
        int yAbove = ((this.mTmpAnchorPos[1] + offsetAbove) - tooltipOffset) - tooltipHeight;
        int yBelow = this.mTmpAnchorPos[1] + offsetBelow + tooltipOffset;
        if (!fromTouch) {
            if (yBelow + tooltipHeight <= this.mTmpDisplayFrame.height()) {
                outParams.y = yBelow;
            } else {
                outParams.y = yAbove;
            }
        } else if (yAbove >= 0) {
            outParams.y = yAbove;
        } else {
            outParams.y = yBelow;
        }
    }

    private static View getAppRootView(View view) {
        View anchorView = view;
        View rootView = anchorView.getRootView();
        ViewGroup.LayoutParams lp = rootView.getLayoutParams();
        if ((lp instanceof WindowManager.LayoutParams) && ((WindowManager.LayoutParams) lp).type == 2) {
            return rootView;
        }
        Context context = anchorView.getContext();
        while (true) {
            Context context2 = context;
            if (!(context2 instanceof ContextWrapper)) {
                return rootView;
            }
            if (context2 instanceof Activity) {
                return ((Activity) context2).getWindow().getDecorView();
            }
            context = ((ContextWrapper) context2).getBaseContext();
        }
    }
}
