package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.TextViewUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Snackbar", iconName = "images/snackbar.png", nonVisible = true, version = 4)
public class MakeroidSnackbar extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "Snackbar";
    private final Activity activity;
    private int backgroundColor = Component.COLOR_DARK_GRAY;
    private int buttonTextColor = -1;
    private int duration = 0;
    private Form form;
    /* access modifiers changed from: private */
    public Snackbar snackbar;
    private int textColor = -1;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidSnackbar(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.duration = r3
            r2 = r0
            r3 = -1
            r2.buttonTextColor = r3
            r2 = r0
            r3 = -12303292(0xffffffffff444444, float:-2.6088314E38)
            r2.backgroundColor = r3
            r2 = r0
            r3 = -1
            r2.textColor = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            java.lang.String r2 = "Snackbar"
            java.lang.String r3 = "Snackbar created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidSnackbar.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "0", editorType = "snackbar_duration")
    @SimpleProperty(userVisible = false)
    public void Duration(int i) {
        int i2 = i;
        this.duration = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "specifies the length of time that the Snackbar is shown - either \"short\" or \"long\".")
    public int Duration() {
        return this.duration;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void ButtonTextColor(int i) {
        int i2 = i;
        this.buttonTextColor = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies the text color for action button.")
    public int ButtonTextColor() {
        return this.buttonTextColor;
    }

    @SimpleFunction(description = "Show Snackbar (message supports HTML formatting)")
    public void Show(String str) {
        showSnackbar(str, (String) null);
    }

    @SimpleFunction(description = "Returns true whether this snackbar is currently being shown.")
    public boolean IsShown() {
        if (this.snackbar != null) {
            return this.snackbar.isShown();
        }
        return false;
    }

    @SimpleFunction(description = "Dismiss the snackbar.")
    public void Dismiss() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
    }

    @SimpleFunction(description = "Show Snackbar with action button (message supports HTML formatting)")
    public void ShowWithButton(String str, String str2) {
        showSnackbar(str, str2);
    }

    private void showSnackbar(String str, String str2) {
        View.OnClickListener onClickListener;
        BaseTransientBottomBar.BaseCallback baseCallback;
        View.OnClickListener onClickListener2;
        String str3 = str;
        String str4 = str2;
        if (str3.isEmpty()) {
            str3 = "Hello world! Snackbar example text";
        }
        if (this.form == null || this.form.coordinatorLayout == null) {
            this.snackbar = Snackbar.make(this.activity.findViewById(16908290), (CharSequence) TextViewUtil.fromHtml(getColoredText(str3)), this.duration);
        } else {
            this.snackbar = Snackbar.make((View) this.form.coordinatorLayout, (CharSequence) TextViewUtil.fromHtml(getColoredText(str3)), this.duration);
        }
        if (str4 != null && !str4.isEmpty()) {
            new View.OnClickListener(this) {
                private /* synthetic */ MakeroidSnackbar hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onClick(View view) {
                    View view2 = view;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Click();
                }
            };
            Snackbar actionTextColor = this.snackbar.setAction((CharSequence) str4, onClickListener2).setActionTextColor(this.buttonTextColor);
        }
        new View.OnClickListener(this) {
            private /* synthetic */ MakeroidSnackbar hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onClick(View view) {
                View view2 = view;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.snackbar.dismiss();
            }
        };
        this.snackbar.getView().setOnClickListener(onClickListener);
        new Snackbar.Callback(this) {
            private /* synthetic */ MakeroidSnackbar hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onDismissed(Snackbar snackbar, int i) {
                Snackbar snackbar2 = snackbar;
                String str = "UNDEFINED";
                switch (i) {
                    case 0:
                        str = "SWIPE";
                        break;
                    case 1:
                        str = "ACTION";
                        break;
                    case 2:
                        str = "TIMEOUT";
                        break;
                    case 3:
                        str = "MANUAL";
                        break;
                    case 4:
                        str = "CONSECUTIVE";
                        break;
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnDismissed(str);
            }

            public final void onShown(Snackbar snackbar) {
                Snackbar snackbar2 = snackbar;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnShown();
            }
        };
        BaseTransientBottomBar addCallback = this.snackbar.addCallback(baseCallback);
        this.snackbar.getView().setBackgroundColor(this.backgroundColor);
        this.snackbar.show();
    }

    @SimpleEvent(description = "Event to detect the snackbar was dismissed. Possible results can be: \"UNDEFINED\", \"ACTION\", \"CONSECUTIVE\", \"MANUAL\", \"SWIPE\" or \"TIMEOUT\". You can find more information at: https://developer.android.com/reference/android/support/design/widget/Snackbar.Callback.html ")
    public void OnDismissed(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnDismissed", str);
    }

    @SimpleEvent(description = "Event to detect the snackbar is shown.")
    public void OnShown() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnShown", new Object[0]);
    }

    @SimpleEvent(description = "User clicked on the action button.")
    public void Click() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty(description = "Specifies the snackbar's background color.")
    public void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
        if (this.snackbar != null) {
            this.snackbar.getView().setBackgroundColor(i2);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the snackbar's background color.")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void TextColor(int i) {
        int i2 = i;
        this.textColor = i2;
        if (i2 == 0) {
            this.textColor = -1;
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TextColor() {
        return this.textColor;
    }

    private String getColoredText(String str) {
        StringBuilder sb;
        String format = String.format("#%06X", new Object[]{Integer.valueOf(16777215 & this.textColor)});
        new StringBuilder("<font color='");
        return sb.append(format).append("'>").append(str).append("</font>").toString();
    }
}
