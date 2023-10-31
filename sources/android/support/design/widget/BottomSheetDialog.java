package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.C0064R;
import android.support.design.widget.BottomSheetBehavior;
import android.support.p000v4.view.AccessibilityDelegateCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p003v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import gnu.expr.Declaration;

public class BottomSheetDialog extends AppCompatDialog {
    private BottomSheetBehavior<FrameLayout> behavior;
    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback;
    boolean cancelable;
    private boolean canceledOnTouchOutside;
    private boolean canceledOnTouchOutsideSet;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BottomSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BottomSheetDialog(@android.support.annotation.NonNull android.content.Context r9, @android.support.annotation.StyleRes int r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r1
            r5 = r1
            r6 = r2
            int r5 = getThemeResId(r5, r6)
            r3.<init>(r4, r5)
            r3 = r0
            r4 = 1
            r3.cancelable = r4
            r3 = r0
            r4 = 1
            r3.canceledOnTouchOutside = r4
            r3 = r0
            android.support.design.widget.BottomSheetDialog$4 r4 = new android.support.design.widget.BottomSheetDialog$4
            r7 = r4
            r4 = r7
            r5 = r7
            r6 = r0
            r5.<init>(r6)
            r3.bottomSheetCallback = r4
            r3 = r0
            r4 = 1
            boolean r3 = r3.supportRequestWindowFeature(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomSheetDialog.<init>(android.content.Context, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BottomSheetDialog(@android.support.annotation.NonNull android.content.Context r10, boolean r11, android.content.DialogInterface.OnCancelListener r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r0
            r5 = r1
            r6 = r2
            r7 = r3
            r4.<init>(r5, r6, r7)
            r4 = r0
            r5 = 1
            r4.cancelable = r5
            r4 = r0
            r5 = 1
            r4.canceledOnTouchOutside = r5
            r4 = r0
            android.support.design.widget.BottomSheetDialog$4 r5 = new android.support.design.widget.BottomSheetDialog$4
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = r0
            r6.<init>(r7)
            r4.bottomSheetCallback = r5
            r4 = r0
            r5 = 1
            boolean r4 = r4.supportRequestWindowFeature(r5)
            r4 = r0
            r5 = r2
            r4.cancelable = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomSheetDialog.<init>(android.content.Context, boolean, android.content.DialogInterface$OnCancelListener):void");
    }

    public void setContentView(@LayoutRes int layoutResId) {
        super.setContentView(wrapInBottomSheet(layoutResId, (View) null, (ViewGroup.LayoutParams) null));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                window.clearFlags(Declaration.PUBLIC_ACCESS);
                window.addFlags(Integer.MIN_VALUE);
            }
            window.setLayout(-1, -1);
        }
    }

    public void setContentView(View view) {
        super.setContentView(wrapInBottomSheet(0, view, (ViewGroup.LayoutParams) null));
    }

    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(wrapInBottomSheet(0, view, params));
    }

    public void setCancelable(boolean z) {
        boolean cancelable2 = z;
        super.setCancelable(cancelable2);
        if (this.cancelable != cancelable2) {
            this.cancelable = cancelable2;
            if (this.behavior != null) {
                this.behavior.setHideable(cancelable2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.behavior != null && this.behavior.getState() == 5) {
            this.behavior.setState(4);
        }
    }

    public void setCanceledOnTouchOutside(boolean z) {
        boolean cancel = z;
        super.setCanceledOnTouchOutside(cancel);
        if (cancel && !this.cancelable) {
            this.cancelable = true;
        }
        this.canceledOnTouchOutside = cancel;
        this.canceledOnTouchOutsideSet = true;
    }

    private View wrapInBottomSheet(int i, View view, ViewGroup.LayoutParams layoutParams) {
        View.OnClickListener onClickListener;
        AccessibilityDelegateCompat accessibilityDelegateCompat;
        View.OnTouchListener onTouchListener;
        int layoutResId = i;
        View view2 = view;
        ViewGroup.LayoutParams params = layoutParams;
        FrameLayout container = (FrameLayout) View.inflate(getContext(), C0064R.layout.design_bottom_sheet_dialog, (ViewGroup) null);
        CoordinatorLayout coordinator = (CoordinatorLayout) container.findViewById(C0064R.C0066id.coordinator);
        if (layoutResId != 0 && view2 == null) {
            view2 = getLayoutInflater().inflate(layoutResId, coordinator, false);
        }
        FrameLayout bottomSheet = (FrameLayout) coordinator.findViewById(C0064R.C0066id.design_bottom_sheet);
        this.behavior = BottomSheetBehavior.from(bottomSheet);
        this.behavior.setBottomSheetCallback(this.bottomSheetCallback);
        this.behavior.setHideable(this.cancelable);
        if (params == null) {
            bottomSheet.addView(view2);
        } else {
            bottomSheet.addView(view2, params);
        }
        new View.OnClickListener(this) {
            final /* synthetic */ BottomSheetDialog this$0;

            {
                this.this$0 = this$0;
            }

            public void onClick(View view) {
                View view2 = view;
                if (this.this$0.cancelable && this.this$0.isShowing() && this.this$0.shouldWindowCloseOnTouchOutside()) {
                    this.this$0.cancel();
                }
            }
        };
        coordinator.findViewById(C0064R.C0066id.touch_outside).setOnClickListener(onClickListener);
        new AccessibilityDelegateCompat(this) {
            final /* synthetic */ BottomSheetDialog this$0;

            {
                this.this$0 = this$0;
            }

            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                AccessibilityNodeInfoCompat info = accessibilityNodeInfoCompat;
                super.onInitializeAccessibilityNodeInfo(host, info);
                if (this.this$0.cancelable) {
                    info.addAction(1048576);
                    info.setDismissable(true);
                    return;
                }
                info.setDismissable(false);
            }

            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                View host = view;
                int action = i;
                Bundle args = bundle;
                if (action != 1048576 || !this.this$0.cancelable) {
                    return super.performAccessibilityAction(host, action, args);
                }
                this.this$0.cancel();
                return true;
            }
        };
        ViewCompat.setAccessibilityDelegate(bottomSheet, accessibilityDelegateCompat);
        new View.OnTouchListener(this) {
            final /* synthetic */ BottomSheetDialog this$0;

            {
                this.this$0 = this$0;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                View view2 = view;
                MotionEvent motionEvent2 = motionEvent;
                return true;
            }
        };
        bottomSheet.setOnTouchListener(onTouchListener);
        return container;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldWindowCloseOnTouchOutside() {
        if (!this.canceledOnTouchOutsideSet) {
            TypedArray a = getContext().obtainStyledAttributes(new int[]{16843611});
            this.canceledOnTouchOutside = a.getBoolean(0, true);
            a.recycle();
            this.canceledOnTouchOutsideSet = true;
        }
        return this.canceledOnTouchOutside;
    }

    private static int getThemeResId(Context context, int i) {
        TypedValue typedValue;
        Context context2 = context;
        int themeId = i;
        if (themeId == 0) {
            new TypedValue();
            TypedValue outValue = typedValue;
            if (context2.getTheme().resolveAttribute(C0064R.attr.bottomSheetDialogTheme, outValue, true)) {
                themeId = outValue.resourceId;
            } else {
                themeId = C0064R.C0068style.Theme_Design_Light_BottomSheetDialog;
            }
        }
        return themeId;
    }
}
