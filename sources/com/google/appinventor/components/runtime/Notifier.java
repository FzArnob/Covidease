package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ElementsUtil;
import com.google.appinventor.components.runtime.util.KodularResourcesUtil;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.KodularUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.shaded.apache.http.protocol.HTTP;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "The Notifier component displays alert dialogs, messages, and temporary alerts, and creates Android log entries through the following methods: <ul><li> ShowMessageDialog: displays a message which the user must dismiss by pressing a button.</li><li> ShowChooseDialog: displays a message two buttons to let the user choose one of two responses, for example, yes or no, after which the AfterChoosing event is raised.</li><li> ShowTextDialog: lets the user enter text in response to the message, after which the AfterTextInput event is raised. <li> ShowPasswordDialog: lets the user enter password in response to the message, after which the AfterTextInput event is raised. <li> ShowAlert: displays a temporary  alert that goes away by itself after a short time.</li><li> ShowProgressDialog: displays an alert with a loading spinner that cannot be dismissed by the user. It can only be dismissed by using the DismissProgressDialog block.</li><li> CustomMessageDialog: New version to display dialogs with icon and in fullscreen mode.</li><li> CustomChooseDialog: New version to display choose dialogs with icon and in fullscreen mode.<li> LightTheme: To display the dialogs in a light or dark theme.</li><li> ShowLightbox: Displays a png or gif file from assets or from web url in a dialog.</li><li> ShowLinearProgress: Shows a progress dialog with a horizontal progress bar.</li><li> ShowSpinningProgress: Shows a spinning progress dialog.</li><li> UpdateProgress: Change the current value of the linear progress dialog.</li><li> ShowRadioListDialog: Shows a radio list dialog with a list of options.</li><li> ShowCheckboxListDialog: Shows a picker dialog with a list of options.</li><li> ShowListPicker: Shows a list picker dialog.</li><li> ShowTextInputDialog: Show a text input dialog.</li><li> ShowImageDialog: Show a image dialog. Animation types like \"*.gif\" are not supported.</li><li> ShowNumberPicker: Shows a number picker dialog that enables the user to select a number from a predefined range.</li><li> ShowWordPicker: Shows a word picker dialog that enables the user to select a number from a predefined word.</li><li> DismissProgressDialog: Dismisses the progress dialog displayed by ShowProgressDialog.</li><li> DismissSpinningProgress: Dismisses the progress dialog displayed by ShowSpinningProgress.</li><li> DismissLinearProgress: Dismisses the progress dialog displayed by ShowLinearProgress.</li><li> LogError: logs an error message to the Android log. </li><li> LogInfo: logs an info message to the Android log.</li><li> LogWarning: logs a warning message to the Android log.</li><li>The messages in the dialogs can be formatted using the following HTML tags:&lt;b&gt;, &lt;big&gt;, &lt;blockquote&gt;, &lt;br&gt;, &lt;cite&gt;, &lt;dfn&gt;, &lt;div&gt;, &lt;em&gt;, &lt;small&gt;, &lt;strong&gt;, &lt;sub&gt;, &lt;sup&gt;, &lt;tt&gt;. &lt;u&gt;</li><li>You can also use the font tag to specify color, for example, &lt;font color=\"blue\"&gt;.  Some of the available color names are aqua, black, blue, fuchsia, green, grey, lime, maroon, navy, olive, purple, red, silver, teal, white, and yellow</li></ul>", iconName = "images/notifier.png", nonVisible = true, version = 11)
@UsesLibraries(libraries = "glide.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class Notifier extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "Notifier";
    private int DIALOG_DARK;
    private int DIALOG_DARK_FULLSCREEN;
    private int DIALOG_DARK_RADIO;
    private int DIALOG_LIGHT;
    private int DIALOG_LIGHT_FULLSCREEN;
    private int DIALOG_LIGHT_RADIO;
    /* access modifiers changed from: private */
    public final Activity activity;
    private int backgroundColor = Component.COLOR_DARK_GRAY;
    private int checkTheme;
    private ComponentContainer container;
    /* access modifiers changed from: private */
    public int currentSelection = -1;
    private int currentTheme;
    private AlertDialog customDialog;
    private float dimAmount = 0.5f;
    private final Handler handler;
    private boolean isRepl;
    private boolean linkify = false;
    private int messageTypeface = 0;
    private String messagefontTypefacePath = "";
    private int msgTheme;
    private int notifierLength = 1;
    private ProgressDialog progress;
    private ProgressDialog progressDialog;
    private ProgressDialog progressl;
    private int radioTheme;
    /* access modifiers changed from: private */
    public int selectedNumber = 0;
    /* access modifiers changed from: private */
    public String selectedWord = "";
    private int textColor = -1;
    private int tglTheme;
    private boolean theme;
    private int titleColor;
    private int titleTypeface = 0;
    private String titlefontTypefacePath = "";
    private int txtTheme;
    private boolean useBackgroundColor = true;

    static /* synthetic */ int access$602(Notifier notifier, int i) {
        int i2 = i;
        int i3 = i2;
        notifier.currentSelection = i3;
        return i2;
    }

    static /* synthetic */ int access$802(Notifier notifier, int i) {
        int i2 = i;
        int i3 = i2;
        notifier.selectedNumber = i3;
        return i2;
    }

    static /* synthetic */ String access$902(Notifier notifier, String str) {
        String str2 = str;
        String str3 = str2;
        notifier.selectedWord = str3;
        return str2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Notifier(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.linkify = r3
            r2 = r0
            r3 = 1
            r2.useBackgroundColor = r3
            r2 = r0
            r3 = 1056964608(0x3f000000, float:0.5)
            r2.dimAmount = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.selectedWord = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.titlefontTypefacePath = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.messagefontTypefacePath = r3
            r2 = r0
            r3 = 1
            r2.notifierLength = r3
            r2 = r0
            r3 = -12303292(0xffffffffff444444, float:-2.6088314E38)
            r2.backgroundColor = r3
            r2 = r0
            r3 = -1
            r2.textColor = r3
            r2 = r0
            r3 = -1
            r2.currentSelection = r3
            r2 = r0
            r3 = 0
            r2.selectedNumber = r3
            r2 = r0
            r3 = 0
            r2.titleTypeface = r3
            r2 = r0
            r3 = 0
            r2.messageTypeface = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            android.os.Handler r3 = new android.os.Handler
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.handler = r3
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            boolean r2 = r2 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r2 == 0) goto L_0x006c
            r2 = r0
            r3 = 1
            r2.isRepl = r3
        L_0x006c:
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.app.Activity r3 = r3.activity
            java.lang.String r4 = "Theme.AppCompat.Light.Dialog.Alert"
            int r3 = com.google.appinventor.components.runtime.util.KodularResourcesUtil.getStyle(r3, r4)
            r2.DIALOG_LIGHT = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.app.Activity r3 = r3.activity
            java.lang.String r4 = "Theme.AppCompat.Light.NoActionBar"
            int r3 = com.google.appinventor.components.runtime.util.KodularResourcesUtil.getStyle(r3, r4)
            r2.DIALOG_LIGHT_FULLSCREEN = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.app.Activity r3 = r3.activity
            java.lang.String r4 = "Theme.AppCompat.Light.DialogWhenLarge"
            int r3 = com.google.appinventor.components.runtime.util.KodularResourcesUtil.getStyle(r3, r4)
            r2.DIALOG_LIGHT_RADIO = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.app.Activity r3 = r3.activity
            java.lang.String r4 = "AppTheme.Dialog.Alert"
            int r3 = com.google.appinventor.components.runtime.util.KodularResourcesUtil.getStyle(r3, r4)
            r2.DIALOG_DARK = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.app.Activity r3 = r3.activity
            java.lang.String r4 = "Theme.AppCompat.NoActionBar"
            int r3 = com.google.appinventor.components.runtime.util.KodularResourcesUtil.getStyle(r3, r4)
            r2.DIALOG_DARK_FULLSCREEN = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.app.Activity r3 = r3.activity
            java.lang.String r4 = "Theme.AppCompat.DialogWhenLarge"
            int r3 = com.google.appinventor.components.runtime.util.KodularResourcesUtil.getStyle(r3, r4)
            r2.DIALOG_DARK_RADIO = r3
            r2 = r0
            r3 = 0
            r2.LightTheme(r3)
            r2 = r0
            r3 = 0
            r2.UseBackgroundColor(r3)
            r2 = r0
            android.app.Activity r2 = r2.activity
            com.google.appinventor.components.runtime.util.TextViewUtil.setContext(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Notifier.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Dismiss a previously displayed ProgressDialog box")
    public final void DismissProgressDialog() {
        dismissProgressDialogHelper(this.progressDialog);
    }

    @SimpleFunction(description = "Dismiss a previously displayed SpinningProgress box")
    public final void DismissSpinningProgress() {
        dismissProgressDialogHelper(this.progress);
    }

    @SimpleFunction(description = "Dismiss a previously displayed LinearProgress box")
    public final void DismissLinearProgress() {
        dismissProgressDialogHelper(this.progressl);
    }

    @SimpleFunction(description = "Show a Message Dialog. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void CustomMessageDialog(String str, String str2, String str3, String str4, boolean z) {
        customMessage(this.activity, str, str2, str3, str4, z);
    }

    public final void customMessage(Activity activity2, String str, String str2, String str3, String str4, boolean z) {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        Activity activity3 = activity2;
        String str5 = str;
        String str6 = str2;
        String str7 = str3;
        String str8 = str4;
        boolean z2 = z;
        if (this.currentTheme == this.DIALOG_LIGHT) {
            if (z2) {
                this.msgTheme = this.DIALOG_LIGHT_FULLSCREEN;
            } else {
                this.msgTheme = this.currentTheme;
            }
        } else if (z2) {
            this.msgTheme = this.DIALOG_DARK_FULLSCREEN;
        } else {
            this.msgTheme = this.currentTheme;
        }
        new AlertDialog.Builder(activity3, this.msgTheme);
        AlertDialog create = builder.create();
        if (!str6.isEmpty()) {
            create.setTitle(str6);
        }
        create.setCancelable(false);
        create.setIcon(iconHelper(str8));
        if (!str5.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str5));
        }
        linkifyIfNeeded(this.linkify, create, str5);
        final String str9 = str6;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AfterMessageDialog(str9);
            }
        };
        create.setButton(-3, str7, onClickListener);
        create.show();
        setDialogHelper(create, z2);
    }

    @SimpleEvent(description = "Event to detect that a user clicked on a button from the \"Show Custom Message Dialog\". Use the \"title\" response to know which dialog the user has clicked.")
    public final void AfterMessageDialog(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterMessageDialog", str);
    }

    @SimpleEvent(description = "Event to detect that a user have done his selection in the CustomChooseDialog.")
    public final void GotCustomChooseDialog(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotCustomChooseDialog", objArr2);
    }

    @SimpleFunction(description = "Shows a dialog box with two buttons, from which the user can choose.  If cancelable is true there will be an additional CANCEL button. Pressing a button will raise the GotCustomChooseDialog event.  The \"choice\" parameter to GotCustomChooseDialog will be the text on the button that was pressed, or \"Cancel\" if the  CANCEL button was pressed. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color. Use the 'Show Custom Dialog' block to the show the created custom dialog. The added component must be visible on screen. After you used this block here it will be removed automatic from screen and will be only visible again after you have shown the custom dialog.")
    public final void CustomChooseDialog(int i, String str, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2) {
        Runnable runnable;
        Runnable runnable2;
        Runnable runnable3;
        int i2 = i;
        String str7 = str3;
        String str8 = str4;
        String str9 = str5;
        final int i3 = i2;
        final String str10 = str7;
        new Runnable(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotCustomChooseDialog(i3, str10);
            }
        };
        final int i4 = i2;
        final String str11 = str8;
        new Runnable(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotCustomChooseDialog(i4, str11);
            }
        };
        final int i5 = i2;
        final String str12 = str9;
        new Runnable(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotCustomChooseDialog(i5, str12);
            }
        };
        customChoose(this.activity, str, str2, str7, str8, str9, str6, z, z2, runnable, runnable2, runnable3, this.linkify);
    }

    public final void customChoose(Activity activity2, String str, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2, Runnable runnable, Runnable runnable2, Runnable runnable3, boolean z3) {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        DialogInterface.OnClickListener onClickListener2;
        DialogInterface.OnClickListener onClickListener3;
        Activity activity3 = activity2;
        String str7 = str;
        String str8 = str2;
        String str9 = str3;
        String str10 = str4;
        String str11 = str5;
        String str12 = str6;
        boolean z4 = z;
        boolean z5 = z2;
        Runnable runnable4 = runnable;
        Runnable runnable5 = runnable2;
        Runnable runnable6 = runnable3;
        boolean z6 = z3;
        if (this.currentTheme == KodularResourcesUtil.getStyle(activity3, "Theme.AppCompat.Light.Dialog.Alert")) {
            if (z5) {
                this.msgTheme = KodularResourcesUtil.getStyle(activity3, "Theme.AppCompat.Light.NoActionBar");
            } else {
                this.msgTheme = this.currentTheme;
            }
        } else if (z5) {
            this.msgTheme = KodularResourcesUtil.getStyle(activity3, "Theme.AppCompat.NoActionBar");
        } else {
            this.msgTheme = this.currentTheme;
        }
        new AlertDialog.Builder(activity3, this.msgTheme);
        AlertDialog create = builder.create();
        if (!str8.isEmpty()) {
            create.setTitle(str8);
        }
        create.setCancelable(false);
        if (!str7.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str7));
        }
        linkifyIfNeeded(z6, create, str7);
        final Runnable runnable7 = runnable4;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                runnable7.run();
            }
        };
        create.setButton(-1, str9, onClickListener);
        final Runnable runnable8 = runnable5;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                runnable8.run();
            }
        };
        create.setButton(-3, str10, onClickListener2);
        if (z4) {
            final Runnable runnable9 = runnable6;
            new DialogInterface.OnClickListener(this) {
                private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogInterface dialogInterface2 = dialogInterface;
                    int i2 = i;
                    runnable9.run();
                }
            };
            create.setButton(-2, str11, onClickListener3);
        }
        create.setIcon(iconHelper(str12));
        create.show();
        setDialogHelper(create, z5);
    }

    @SimpleFunction(description = "Show a alert \"toast\" message.")
    public final void ShowAlert(String str) {
        Runnable runnable;
        final String str2 = str;
        new Runnable(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.toastNow(str2);
            }
        };
        boolean post = this.handler.post(runnable);
    }

    @DesignerProperty(defaultValue = "1", editorType = "toast_length")
    @SimpleProperty(userVisible = false)
    public final void NotifierLength(int i) {
        int i2 = i;
        this.notifierLength = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "specifies the length of time that the alert is shown -- either \"short\" or \"long\".")
    public final int NotifierLength() {
        return this.notifierLength;
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty(description = "Specifies the background color for alerts (not dialogs).")
    public final void BackgroundColor(int i) {
        int i2 = i;
        this.backgroundColor = i2;
    }

    @SimpleProperty
    public final int BackgroundColor() {
        return this.backgroundColor;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies the title text color for dialogs.")
    public final int TitleColor() {
        return this.titleColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public final void TitleColor(int i) {
        int i2 = i;
        this.titleColor = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies the text color for alerts or for dialogs message.")
    public final int TextColor() {
        return this.textColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public final void TextColor(int i) {
        int i2 = i;
        this.textColor = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final int TitleFontTypeface() {
        return this.titleTypeface;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void TitleFontTypeface(int i) {
        int i2 = i;
        this.titleTypeface = i2;
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public final void TitleFontTypefaceImport(String str) {
        String str2 = str;
        this.titlefontTypefacePath = str2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final int TextFontTypeface() {
        return this.messageTypeface;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void TextFontTypeface(int i) {
        int i2 = i;
        this.messageTypeface = i2;
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public final void TextFontTypefaceImport(String str) {
        String str2 = str;
        this.messagefontTypefacePath = str2;
    }

    @SimpleFunction(description = "Writes an error message to the Android system log. See the Google Android documentation for how to access the log.")
    public final void LogError(String str) {
        int e = Log.e(LOG_TAG, str);
    }

    @SimpleFunction(description = "Writes a warning message to the Android log. See the Google Android documentation for how to access the log.")
    public final void LogWarning(String str) {
        int w = Log.w(LOG_TAG, str);
    }

    @SimpleFunction(description = "Writes an information message to the Android log.")
    public final void LogInfo(String str) {
        int i = Log.i(LOG_TAG, str);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Gets the current theme")
    public final void LightTheme(boolean z) {
        boolean z2 = z;
        if (z2) {
            this.currentTheme = this.DIALOG_LIGHT;
        } else {
            this.currentTheme = this.DIALOG_DARK;
        }
        this.theme = z2;
    }

    @SimpleProperty(description = "Sets the current theme")
    public final boolean LightTheme() {
        return this.theme;
    }

    @SimpleFunction(description = "Displays a lightbox. You can use images like \"*.png\" or \"*,gif\" from assets folder or from a web url.")
    public final void ShowLightbox(int i, String str) {
        Runnable runnable;
        final int i2 = i;
        final String str2 = str;
        new Runnable(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.lightbox(i2, str2);
            }
        };
        boolean post = this.handler.post(runnable);
    }

    /* access modifiers changed from: private */
    public void lightbox(int i, String str) {
        Dialog dialog;
        DialogInterface.OnDismissListener onDismissListener;
        ImageView imageView;
        ViewGroup.LayoutParams layoutParams;
        String str2 = str;
        new Dialog(this.activity, 16973832);
        Dialog dialog2 = dialog;
        Dialog dialog3 = dialog2;
        boolean requestWindowFeature = dialog2.requestWindowFeature(1);
        final int i2 = i;
        new DialogInterface.OnDismissListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onDismiss(DialogInterface dialogInterface) {
                DialogInterface dialogInterface2 = dialogInterface;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LightboxClosed(i2);
            }
        };
        dialog3.setOnDismissListener(onDismissListener);
        if (!str2.isEmpty()) {
            new ImageView(this.activity);
            ImageView imageView2 = imageView;
            KodularUtil.LoadPicture((Context) this.activity, str2, imageView2, this.isRepl);
            new RelativeLayout.LayoutParams(-1, -1);
            dialog3.addContentView(imageView2, layoutParams);
        }
        dialog3.show();
    }

    @SimpleEvent(description = "Event to detect that a user have closed the Lightbox.")
    public final void LightboxClosed(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LightboxClosed", Integer.valueOf(i));
    }

    @SimpleFunction(description = "Shows a progress dialog with a horizontal progress bar. Can be dismissed by user if 'cancelable' is set to true. If indeterminate is true, maxValue and the 'UpdateProgress' method will have no effect. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowLinearProgress(String str, String str2, boolean z, boolean z2, int i, String str3) {
        Runnable runnable;
        final String str4 = str;
        final String str5 = str2;
        final boolean z3 = z;
        final boolean z4 = z2;
        final int i2 = i;
        final String str6 = str3;
        new Runnable(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r11;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.linearProgress(str4, str5, z3, z4, i2, str6);
            }
        };
        boolean post = this.handler.post(runnable);
    }

    /* access modifiers changed from: private */
    public void linearProgress(String str, String str2, boolean z, boolean z2, int i, String str3) {
        ProgressDialog progressDialog2;
        DialogInterface.OnDismissListener onDismissListener;
        String str4 = str;
        String str5 = str2;
        boolean z3 = z;
        boolean z4 = z2;
        int i2 = i;
        String str6 = str3;
        if (this.progress != null) {
            DismissSpinningProgress();
        }
        if (this.progressl != null) {
            DismissLinearProgress();
        }
        if (this.progressDialog != null) {
            DismissProgressDialog();
        }
        new ProgressDialog(this.activity, this.currentTheme);
        this.progressl = progressDialog2;
        this.progressl.setProgressStyle(1);
        this.progressl.setIndeterminate(z4);
        this.progressl.setCancelable(z3);
        if (!str4.isEmpty()) {
            this.progressl.setMessage(TextViewUtil.fromHtml(str4));
        }
        if (!str5.isEmpty()) {
            this.progressl.setTitle(TextViewUtil.fromHtml(str5));
        }
        this.progressl.setProgress(0);
        this.progressl.setMax(i2);
        this.progressl.setProgress(0);
        if (z4) {
            this.progressl.setProgressNumberFormat((String) null);
            this.progressl.setProgressPercentFormat((NumberFormat) null);
        }
        this.progressl.setIcon(iconHelper(str6));
        new DialogInterface.OnDismissListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onDismiss(DialogInterface dialogInterface) {
                DialogInterface dialogInterface2 = dialogInterface;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LinearProgressDismissed();
            }
        };
        this.progressl.setOnDismissListener(onDismissListener);
        this.progressl.show();
        setDialogHelper(this.progressl, false);
    }

    @SimpleEvent(description = "Event to detect that the linear progress dialog was dismissed.")
    public final void LinearProgressDismissed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LinearProgressDismissed", new Object[0]);
    }

    @SimpleFunction(description = "Change the current value of the linear progress dialog. Has no effect if \"indeterminate\" is set to true.")
    public final void UpdateProgress(int i) {
        int i2 = i;
        if (this.progressl != null) {
            this.progressl.setProgress(i2);
        }
    }

    @SimpleFunction(description = "Shows a spinning progress dialog which can be dismissed by the user if 'cancelable' is set to true. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowSpinningProgress(String str, String str2, boolean z, String str3) {
        Runnable runnable;
        final String str4 = str;
        final String str5 = str2;
        final boolean z2 = z;
        final String str6 = str3;
        new Runnable(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r9;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.spinningProgress(str4, str5, z2, str6);
            }
        };
        boolean post = this.handler.post(runnable);
    }

    /* access modifiers changed from: private */
    public void spinningProgress(String str, String str2, boolean z, String str3) {
        ProgressDialog progressDialog2;
        StringBuilder sb;
        StringBuilder sb2;
        String str4 = str;
        String str5 = str2;
        boolean z2 = z;
        String str6 = str3;
        if (this.progress != null) {
            DismissSpinningProgress();
        }
        if (this.progressl != null) {
            DismissLinearProgress();
        }
        if (this.progressDialog != null) {
            DismissProgressDialog();
        }
        new ProgressDialog(this.activity, this.currentTheme);
        this.progress = progressDialog2;
        this.progress.setProgressStyle(0);
        this.progress.setIndeterminate(true);
        this.progress.setCancelable(z2);
        this.progress.setIcon(iconHelper(str6));
        if (this.currentTheme == this.DIALOG_LIGHT) {
            if (!str4.isEmpty()) {
                ProgressDialog progressDialog3 = this.progress;
                new StringBuilder();
                progressDialog3.setMessage(sb2.append(TextViewUtil.fromHtml(str4)).toString());
            }
        } else if (!str4.isEmpty()) {
            ProgressDialog progressDialog4 = this.progress;
            new StringBuilder();
            progressDialog4.setMessage(sb.append(TextViewUtil.fromHtml(str4)).toString());
        }
        if (str5 != null && !str5.isEmpty()) {
            this.progress.setTitle(TextViewUtil.fromHtml(str5));
        }
        this.progress.show();
        setDialogHelper(this.progress, false);
    }

    @SimpleFunction(description = "Shows a radio list dialog with a list of options of which only one can be chosen. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowRadioListDialog(int i, String str, YailList yailList, String str2, boolean z, String str3, int i2, String str4, boolean z2) {
        Runnable runnable;
        final int i3 = i;
        final String str5 = str;
        final YailList yailList2 = yailList;
        final String str6 = str2;
        final boolean z3 = z;
        final String str7 = str3;
        final int i4 = i2;
        final String str8 = str4;
        final boolean z4 = z2;
        new Runnable(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r14;
            }

            public final void run() {
                Dialog access$400 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.radioListDialog(i3, str5, yailList2, str6, z3, str7, i4, str8, z4);
                access$400.show();
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setDialogHelper(access$400, z4);
            }
        };
        boolean post = this.handler.post(runnable);
    }

    /* access modifiers changed from: private */
    public Dialog radioListDialog(int i, String str, YailList yailList, String str2, boolean z, String str3, int i2, String str4, boolean z2) {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        DialogInterface.OnClickListener onClickListener2;
        DialogInterface.OnClickListener onClickListener3;
        int i3 = i;
        String str5 = str;
        YailList yailList2 = yailList;
        String str6 = str2;
        boolean z3 = z;
        String str7 = str3;
        int i4 = i2;
        String str8 = str4;
        boolean z4 = z2;
        if (this.currentTheme == this.DIALOG_LIGHT) {
            if (z4) {
                this.radioTheme = this.DIALOG_LIGHT_RADIO;
            } else {
                this.radioTheme = this.currentTheme;
            }
        } else if (z4) {
            this.radioTheme = this.DIALOG_DARK_RADIO;
        } else {
            this.radioTheme = this.currentTheme;
        }
        int i5 = i4 - 1;
        this.currentSelection = i5 + 1;
        new AlertDialog.Builder(this.activity, this.radioTheme);
        AlertDialog.Builder builder2 = builder;
        String[] stringArray = yailList2.toStringArray();
        AlertDialog.Builder cancelable = builder2.setCancelable(z3);
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int access$602 = Notifier.access$602(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i + 1);
            }
        };
        AlertDialog.Builder singleChoiceItems = builder2.setTitle(TextViewUtil.fromHtml(str5)).setSingleChoiceItems(stringArray, i5, onClickListener);
        final int i6 = i3;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.RadioSelection(i6, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.currentSelection);
            }
        };
        AlertDialog.Builder positiveButton = builder2.setPositiveButton(str6, onClickListener2);
        if (z3) {
            final int i7 = i3;
            new DialogInterface.OnClickListener(this) {
                private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogInterface dialogInterface2 = dialogInterface;
                    int i2 = i;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.RadioSelection(i7, -1);
                }
            };
            AlertDialog.Builder negativeButton = builder2.setNegativeButton(TextViewUtil.fromHtml(str7), onClickListener3);
        }
        AlertDialog.Builder icon = builder2.setIcon(iconHelper(str8));
        return builder2.create();
    }

    @SimpleEvent(description = "Event invoked when user has selected an option from the radio button picker. Outputs the index of the selected item. Returns -1 if cancel was pressed.")
    public final void RadioSelection(int i, int i2) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(i2);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "RadioSelection", objArr2);
    }

    @SimpleFunction(description = "Shows a picker dialog with a list of options of whichmore than one can be chosen. Invokes the 'AfterMultiSelection' event. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowCheckboxListDialog(int i, String str, YailList yailList, String str2, String str3, boolean z, String str4, boolean z2) {
        ArrayList arrayList;
        AlertDialog.Builder builder;
        DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener;
        DialogInterface.OnClickListener onClickListener;
        DialogInterface.OnClickListener onClickListener2;
        int i2 = i;
        String str5 = str;
        YailList yailList2 = yailList;
        String str6 = str2;
        String str7 = str3;
        boolean z3 = z;
        String str8 = str4;
        boolean z4 = z2;
        if (this.currentTheme == this.DIALOG_LIGHT) {
            if (z4) {
                this.checkTheme = this.DIALOG_LIGHT_FULLSCREEN;
            } else {
                this.checkTheme = this.currentTheme;
            }
        } else if (z4) {
            this.checkTheme = this.DIALOG_DARK_FULLSCREEN;
        } else {
            this.checkTheme = this.currentTheme;
        }
        String[] stringArray = yailList2.toStringArray();
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        new AlertDialog.Builder(this.activity, this.checkTheme);
        final ArrayList arrayList3 = arrayList2;
        new DialogInterface.OnMultiChoiceClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i, boolean z) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                if (z) {
                    boolean add = arrayList3.add(Integer.valueOf(i2 + 1));
                } else if (arrayList3.contains(Integer.valueOf(i2 + 1))) {
                    boolean remove = arrayList3.remove(Integer.valueOf(i2 + 1));
                }
            }
        };
        final ArrayList arrayList4 = arrayList2;
        final int i3 = i2;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.CheckboxSelection(i3, YailList.makeList((List) arrayList4));
            }
        };
        AlertDialog.Builder positiveButton = builder.setTitle(TextViewUtil.fromHtml(str5)).setMultiChoiceItems(stringArray, (boolean[]) null, onMultiChoiceClickListener).setPositiveButton(str6, onClickListener);
        if (z3) {
            final int i4 = i2;
            new DialogInterface.OnClickListener(this) {
                private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogInterface dialogInterface2 = dialogInterface;
                    int i2 = i;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.CheckboxSelection(i4, ElementsUtil.elementsFromString("-1"));
                }
            };
            AlertDialog.Builder negativeButton = positiveButton.setNegativeButton(str7, onClickListener2);
        }
        AlertDialog create = positiveButton.create();
        AlertDialog.Builder cancelable = positiveButton.setCancelable(z3);
        AlertDialog.Builder icon = positiveButton.setIcon(iconHelper(str8));
        AlertDialog show = positiveButton.show();
        setDialogHelper(create, z4);
    }

    @SimpleEvent(description = "Invoked after user has finished selecting items from the Checkbox picker. Returns a list of indices of the selected items in the order of selection. Returns a list having -1 if cancel was pressed.")
    public final void CheckboxSelection(int i, YailList yailList) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = yailList;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "CheckboxSelection", objArr2);
    }

    @SimpleFunction(description = "Shows a list picker dialog. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowListPicker(int i, String str, YailList yailList, String str2, boolean z) {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        int i2 = i;
        String str3 = str;
        YailList yailList2 = yailList;
        String str4 = str2;
        boolean z2 = z;
        if (this.currentTheme == this.DIALOG_LIGHT) {
            if (z2) {
                this.tglTheme = this.DIALOG_LIGHT_FULLSCREEN;
            } else {
                this.tglTheme = this.currentTheme;
            }
        } else if (z2) {
            this.tglTheme = this.DIALOG_DARK_FULLSCREEN;
        } else {
            this.tglTheme = this.currentTheme;
        }
        new AlertDialog.Builder(this.activity, this.tglTheme);
        AlertDialog.Builder builder2 = builder;
        if (!str3.isEmpty()) {
            AlertDialog.Builder title = builder2.setTitle(TextViewUtil.fromHtml(str3));
        }
        String[] stringArray = yailList2.toStringArray();
        AlertDialog.Builder cancelable = builder2.setCancelable(true);
        final int i3 = i2;
        final String[] strArr = stringArray;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ListPickerSelection(i3, strArr[i].toString());
            }
        };
        AlertDialog.Builder items = builder2.setItems(stringArray, onClickListener);
        AlertDialog.Builder icon = builder2.setIcon(iconHelper(str4));
        AlertDialog create = builder2.create();
        create.show();
        setDialogHelper(create, z2);
    }

    @SimpleEvent(description = "Event to get the picked list selection from the List Picker.")
    public final void ListPickerSelection(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ListPickerSelection", objArr2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01b4  */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Show a text input dialog. Possible input types are: \"1= Normal text\", \"2= Password text\", \"3= Person name\", \"4= Email adress\", \"5|6= Number\", \"7= Password number\" or \"8= datetime\". You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void ShowTextInputDialog(int r26, java.lang.String r27, java.lang.String r28, java.lang.String r29, java.lang.String r30, boolean r31, boolean r32, int r33, java.lang.String r34, java.lang.String r35, int r36, int r37) {
        /*
            r25 = this;
            r1 = r25
            r2 = r26
            r3 = r27
            r4 = r28
            r5 = r29
            r6 = r30
            r7 = r31
            r8 = r32
            r9 = r33
            r10 = r34
            r11 = r35
            r12 = r36
            r13 = r37
            r15 = r1
            int r15 = r15.currentTheme
            r16 = r1
            r0 = r16
            int r0 = r0.DIALOG_LIGHT
            r16 = r0
            r0 = r16
            if (r15 != r0) goto L_0x0131
            r15 = r7
            if (r15 == 0) goto L_0x011e
            r15 = r1
            r24 = r15
            r15 = r24
            r16 = r24
            r0 = r16
            int r0 = r0.DIALOG_LIGHT_FULLSCREEN
            r16 = r0
            r0 = r16
            r15.txtTheme = r0
        L_0x003d:
            android.app.AlertDialog$Builder r15 = new android.app.AlertDialog$Builder
            r24 = r15
            r15 = r24
            r16 = r24
            r17 = r1
            r0 = r17
            android.app.Activity r0 = r0.activity
            r17 = r0
            r18 = r1
            r0 = r18
            int r0 = r0.txtTheme
            r18 = r0
            r16.<init>(r17, r18)
            android.app.AlertDialog r15 = r15.create()
            r14 = r15
            r15 = r3
            boolean r15 = r15.isEmpty()
            if (r15 != 0) goto L_0x006e
            r15 = r14
            r16 = r3
            android.text.Spanned r16 = com.google.appinventor.components.runtime.util.TextViewUtil.fromHtml(r16)
            r15.setTitle(r16)
        L_0x006e:
            android.widget.EditText r15 = new android.widget.EditText
            r24 = r15
            r15 = r24
            r16 = r24
            r17 = r1
            r0 = r17
            android.app.Activity r0 = r0.activity
            r17 = r0
            r16.<init>(r17)
            r3 = r15
            r15 = r9
            r16 = 1
            r0 = r16
            if (r15 == r0) goto L_0x01ac
            r15 = r9
            r16 = 2
            r0 = r16
            if (r15 != r0) goto L_0x015a
            r15 = r3
            r16 = 129(0x81, float:1.81E-43)
            r15.setInputType(r16)
        L_0x0096:
            r15 = r4
            if (r15 == 0) goto L_0x00a6
            r15 = r4
            boolean r15 = r15.isEmpty()
            if (r15 != 0) goto L_0x00a6
            r15 = r3
            r16 = r4
            r15.setText(r16)
        L_0x00a6:
            r15 = r6
            if (r15 == 0) goto L_0x00b6
            r15 = r6
            boolean r15 = r15.isEmpty()
            if (r15 != 0) goto L_0x00b6
            r15 = r3
            r16 = r6
            r15.setHint(r16)
        L_0x00b6:
            r15 = r3
            r16 = r12
            r15.setTextColor(r16)
            r15 = r3
            r16 = r13
            r15.setHintTextColor(r16)
            r15 = r14
            r16 = r3
            r15.setView(r16)
            r15 = r14
            r16 = -1
            r17 = r10
            com.google.appinventor.components.runtime.Notifier$15 r18 = new com.google.appinventor.components.runtime.Notifier$15
            r24 = r18
            r18 = r24
            r19 = r24
            r20 = r1
            r21 = r3
            r22 = r2
            r19.<init>(r20, r21, r22)
            r15.setButton(r16, r17, r18)
            r15 = r8
            if (r15 == 0) goto L_0x01b4
            r15 = r14
            r16 = -2
            r17 = r11
            com.google.appinventor.components.runtime.Notifier$16 r18 = new com.google.appinventor.components.runtime.Notifier$16
            r24 = r18
            r18 = r24
            r19 = r24
            r20 = r1
            r21 = r3
            r22 = r2
            r23 = r11
            r19.<init>(r20, r21, r22, r23)
            r15.setButton(r16, r17, r18)
            r15 = r14
            r16 = 1
            r15.setCancelable(r16)
        L_0x0105:
            r15 = r14
            r16 = r1
            r17 = r5
            android.graphics.drawable.Drawable r16 = r16.iconHelper(r17)
            r15.setIcon(r16)
            r15 = r14
            r15.show()
            r15 = r1
            r16 = r14
            r17 = r7
            r15.setDialogHelper(r16, r17)
            return
        L_0x011e:
            r15 = r1
            r24 = r15
            r15 = r24
            r16 = r24
            r0 = r16
            int r0 = r0.currentTheme
            r16 = r0
            r0 = r16
            r15.txtTheme = r0
            goto L_0x003d
        L_0x0131:
            r15 = r7
            if (r15 == 0) goto L_0x0147
            r15 = r1
            r24 = r15
            r15 = r24
            r16 = r24
            r0 = r16
            int r0 = r0.DIALOG_DARK_FULLSCREEN
            r16 = r0
            r0 = r16
            r15.txtTheme = r0
            goto L_0x003d
        L_0x0147:
            r15 = r1
            r24 = r15
            r15 = r24
            r16 = r24
            r0 = r16
            int r0 = r0.currentTheme
            r16 = r0
            r0 = r16
            r15.txtTheme = r0
            goto L_0x003d
        L_0x015a:
            r15 = r9
            r16 = 3
            r0 = r16
            if (r15 != r0) goto L_0x0169
            r15 = r3
            r16 = 97
            r15.setInputType(r16)
            goto L_0x0096
        L_0x0169:
            r15 = r9
            r16 = 4
            r0 = r16
            if (r15 != r0) goto L_0x0178
            r15 = r3
            r16 = 33
            r15.setInputType(r16)
            goto L_0x0096
        L_0x0178:
            r15 = r9
            r16 = 5
            r0 = r16
            if (r15 == r0) goto L_0x0186
            r15 = r9
            r16 = 6
            r0 = r16
            if (r15 != r0) goto L_0x018e
        L_0x0186:
            r15 = r3
            r16 = 12290(0x3002, float:1.7222E-41)
            r15.setInputType(r16)
            goto L_0x0096
        L_0x018e:
            r15 = r9
            r16 = 7
            r0 = r16
            if (r15 != r0) goto L_0x019d
            r15 = r3
            r16 = 12306(0x3012, float:1.7244E-41)
            r15.setInputType(r16)
            goto L_0x0096
        L_0x019d:
            r15 = r9
            r16 = 8
            r0 = r16
            if (r15 != r0) goto L_0x01ac
            r15 = r3
            r16 = 4
            r15.setInputType(r16)
            goto L_0x0096
        L_0x01ac:
            r15 = r3
            r16 = 1
            r15.setInputType(r16)
            goto L_0x0096
        L_0x01b4:
            r15 = r14
            r16 = 0
            r15.setCancelable(r16)
            goto L_0x0105
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Notifier.ShowTextInputDialog(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, int, java.lang.String, java.lang.String, int, int):void");
    }

    @SimpleEvent(description = "Event to detect that a user have done his text input in the \"Show Text Input Dialog\".")
    public final void GotTextInputFromDialog(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotTextInputFromDialog", objArr2);
    }

    @SimpleFunction(description = "Show a image dialog. Animation types like \"*.gif\" are not supported. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowImageDialog(int i, String str, String str2, String str3) {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        ImageView imageView;
        int i2 = i;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        this.checkTheme = this.currentTheme;
        new AlertDialog.Builder(this.activity, this.checkTheme);
        AlertDialog create = builder.create();
        if (!str4.isEmpty()) {
            create.setTitle(str4);
        } else {
            boolean requestWindowFeature = create.requestWindowFeature(1);
        }
        create.setCancelable(false);
        final int i3 = i2;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ImageDialogClosed(i3);
            }
        };
        create.setButton(-1, str6, onClickListener);
        if (!str5.isEmpty()) {
            new ImageView(this.activity);
            ImageView imageView2 = imageView;
            KodularUtil.LoadPicture((Context) this.activity, str5, imageView2, this.isRepl);
            create.setView(imageView2, 0, 0, 0, 0);
        }
        create.show();
        setDialogHelper(create, false);
    }

    @SimpleEvent(description = "Event to detect that the user has watched the image dialog.")
    public final void ImageDialogClosed(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ImageDialogClosed", Integer.valueOf(i));
    }

    @SimpleFunction(description = "Shows a number picker dialog that enables the user to select a number from a predefined range. You can use the \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowNumberPicker(int i, String str, String str2, String str3, int i2, int i3, int i4) {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        DialogInterface.OnClickListener onClickListener2;
        NumberPicker numberPicker;
        NumberPicker.OnValueChangeListener onValueChangeListener;
        int i5 = i;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        this.selectedNumber = i8;
        new AlertDialog.Builder(this.activity);
        AlertDialog create = builder.create();
        if (!str4.isEmpty()) {
            create.setTitle(str4);
        }
        create.setCancelable(false);
        if (str5.isEmpty()) {
            str5 = "OK";
        }
        final int i9 = i5;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.NumberPickerSelection(i9, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.selectedNumber);
            }
        };
        create.setButton(-1, str5, onClickListener);
        if (str6.isEmpty()) {
            str6 = HTTP.CONN_CLOSE;
        }
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
            }
        };
        create.setButton(-2, str6, onClickListener2);
        new NumberPicker(this.activity);
        NumberPicker numberPicker2 = numberPicker;
        NumberPicker numberPicker3 = numberPicker2;
        numberPicker2.setMinValue(i6);
        numberPicker3.setMaxValue(i7);
        numberPicker3.setValue(i8);
        numberPicker3.setWrapSelectorWheel(true);
        new NumberPicker.OnValueChangeListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onValueChange(NumberPicker numberPicker, int i, int i2) {
                NumberPicker numberPicker2 = numberPicker;
                int i3 = i;
                int access$802 = Notifier.access$802(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i2);
            }
        };
        numberPicker3.setOnValueChangedListener(onValueChangeListener);
        create.setView(numberPicker3, 30, 30, 30, 30);
        create.show();
        setDialogHelper(create, false);
    }

    @SimpleEvent(description = "Event to detect that the user has selected a number from the number picker dialog.")
    public final void NumberPickerSelection(int i, int i2) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(i2);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "NumberPickerSelection", objArr2);
    }

    @SimpleFunction(description = "Shows a word picker dialog that enables the user to select a number from a predefined word. You can use the \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowWordPicker(int i, String str, String str2, String str3, YailList yailList) {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        DialogInterface.OnClickListener onClickListener2;
        NumberPicker numberPicker;
        NumberPicker.OnValueChangeListener onValueChangeListener;
        int i2 = i;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        String[] stringArray = yailList.toStringArray();
        new AlertDialog.Builder(this.activity);
        AlertDialog create = builder.create();
        if (!str4.isEmpty()) {
            create.setTitle(str4);
        }
        create.setCancelable(false);
        if (str5.isEmpty()) {
            str5 = "OK";
        }
        final int i3 = i2;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.WordPickerSelection(i3, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.selectedWord);
            }
        };
        create.setButton(-1, str5, onClickListener);
        if (str6.isEmpty()) {
            str6 = HTTP.CONN_CLOSE;
        }
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
            }
        };
        create.setButton(-2, str6, onClickListener2);
        new NumberPicker(this.activity);
        NumberPicker numberPicker2 = numberPicker;
        NumberPicker numberPicker3 = numberPicker2;
        numberPicker2.setMinValue(0);
        numberPicker3.setMaxValue(stringArray.length - 1);
        numberPicker3.setDisplayedValues(stringArray);
        numberPicker3.setWrapSelectorWheel(true);
        final String[] strArr = stringArray;
        new NumberPicker.OnValueChangeListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onValueChange(NumberPicker numberPicker, int i, int i2) {
                NumberPicker numberPicker2 = numberPicker;
                int i3 = i;
                String access$902 = Notifier.access$902(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, strArr[i2]);
            }
        };
        numberPicker3.setOnValueChangedListener(onValueChangeListener);
        create.setView(numberPicker3, 30, 30, 30, 30);
        create.show();
        setDialogHelper(create, false);
    }

    @SimpleEvent(description = "Event to detect that the user has selected a word from the word picker dialog.")
    public final void WordPickerSelection(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "WordPickerSelection", objArr2);
    }

    @SimpleFunction(description = "Show the custom dialog. Dont forget that you have first to create the custom dialog.")
    public final void ShowCustomDialog() {
        if (this.customDialog != null) {
            this.customDialog.show();
            setDialogHelper(this.customDialog, false);
        }
    }

    @SimpleFunction(description = "Use this block to dismiss the created custom dialog.the custom dialog.")
    public final void DismissCustomDialog() {
        if (this.customDialog != null) {
            this.customDialog.dismiss();
        }
    }

    @SimpleFunction(description = "Show whatever you want in a dialog. You can use as example arrangements, or images or whatever you want. Your chosen layout will be then removed from the screen and only visible in custom dialog. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color. Please make sure the layout you want to use is visible.")
    public final void CreateCustomDialog(AndroidViewComponent androidViewComponent, String str, String str2, String str3, boolean z) {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        DialogInterface.OnClickListener onClickListener2;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        boolean z2 = z;
        this.checkTheme = this.currentTheme;
        new AlertDialog.Builder(this.activity, this.checkTheme);
        this.customDialog = builder.create();
        if (!str4.isEmpty()) {
            this.customDialog.setTitle(str4);
        }
        this.customDialog.setCancelable(z2);
        if (!str5.isEmpty()) {
            final String str7 = str5;
            new DialogInterface.OnClickListener(this) {
                private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogInterface dialogInterface2 = dialogInterface;
                    int i2 = i;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.CustomDialogSelection(str7);
                }
            };
            this.customDialog.setButton(-1, str5, onClickListener2);
        }
        if (!str6.isEmpty()) {
            final String str8 = str6;
            new DialogInterface.OnClickListener(this) {
                private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogInterface dialogInterface2 = dialogInterface;
                    int i2 = i;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.CustomDialogSelection(str8);
                }
            };
            this.customDialog.setButton(-2, str6, onClickListener);
        }
        try {
            ViewGroup viewGroup = (ViewGroup) androidViewComponent2.getView();
            ViewGroup viewGroup2 = viewGroup;
            ((ViewGroup) viewGroup.getParent()).removeView(viewGroup2);
            this.customDialog.setView(viewGroup2, 0, 0, 0, 0);
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    @SimpleEvent(description = "Event to detect that the user has pressed a button from the custom dialog. It returns then the text of the button that was pressed.")
    public final void CustomDialogSelection(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "CustomDialogSelection", str);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If enabled the dialog will use the color from the \"Background Color\"-option. Else it will use the theme (light or dark).")
    public final void UseBackgroundColor(boolean z) {
        boolean z2 = z;
        this.useBackgroundColor = z2;
    }

    @SimpleProperty
    public final boolean UseBackgroundColor() {
        return this.useBackgroundColor;
    }

    @DesignerProperty(defaultValue = "0.5", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(description = "Set the amount of dim behind the dialog window. Use '0.0' for no dim and '1.0' for full dim.")
    public final void DimAmount(float f) {
        float f2 = f;
        this.dimAmount = f2;
    }

    @SimpleProperty
    public final float DimAmount() {
        return this.dimAmount;
    }

    public static void aboutThisApp(Activity activity2, String str, String str2, String str3, int i, boolean z) {
        int style;
        DialogInterface.OnClickListener onClickListener;
        Activity activity3 = activity2;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        int i2 = i;
        AlertDialog.Builder builder = r11;
        Activity activity4 = activity3;
        if (z) {
            style = KodularResourcesUtil.getStyle(activity3, "Theme.AppCompat.Light.Dialog.Alert");
        } else {
            style = KodularResourcesUtil.getStyle(activity3, "AppTheme.Dialog.Alert");
        }
        AlertDialog.Builder builder2 = new AlertDialog.Builder(activity4, style);
        AlertDialog create = builder.create();
        if (!str5.isEmpty()) {
            create.setTitle(str5);
        }
        create.setCancelable(false);
        if (!str4.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str4));
        }
        new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
            }
        };
        create.setButton(-3, str6, onClickListener);
        create.show();
        try {
            ((TextView) create.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
        if (create.getWindow() != null) {
            create.getWindow().getDecorView().getBackground().setColorFilter(i2, PorterDuff.Mode.SRC_ATOP);
        }
    }

    @SimpleFunction(description = "Show a Message Dialog.")
    public final void ShowMessageDialog(String str, String str2, String str3) {
        oneButtonAlert(this.activity, str, str2, str3, this.linkify);
    }

    public static void oneButtonAlert(Activity activity2, String str, String str2, String str3) {
        oneButtonAlert(activity2, str, str2, str3, false);
    }

    public static void oneButtonAlert(Activity activity2, String str, String str2, String str3, boolean z) {
        oneButtonAlert(activity2, str, str2, str3, (Runnable) null, z);
    }

    public static void oneButtonAlert(Activity activity2, String str, String str2, String str3, Runnable runnable) {
        oneButtonAlert(activity2, str, str2, str3, runnable, false);
    }

    public static void oneButtonAlert(Activity activity2, String str, String str2, String str3, Runnable runnable, boolean z) {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        Runnable runnable2 = runnable;
        boolean z2 = z;
        int i = Log.i(LOG_TAG, "One button alert ".concat(String.valueOf(str4)));
        new AlertDialog.Builder(activity2);
        AlertDialog create = builder.create();
        if (!str5.isEmpty()) {
            create.setTitle(str5);
        }
        create.setCancelable(false);
        if (!str4.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str4));
        }
        final Runnable runnable3 = runnable2;
        new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                if (runnable3 != null) {
                    runnable3.run();
                }
            }
        };
        create.setButton(-3, str6, onClickListener);
        linkifyIfNeeded(z2, create, str4);
        create.show();
    }

    @SimpleFunction(description = "Shows a dialog box with two buttons, from which the user can choose.  If cancelable is true there will be an additional CANCEL button. Pressing a button will raise the AfterChoosing event.  The \"choice\" parameter to AfterChoosing will be the text on the button that was pressed, or \"Cancel\" if the  CANCEL button was pressed.")
    public final void ShowChooseDialog(String str, String str2, String str3, String str4, boolean z) {
        Runnable runnable;
        Runnable runnable2;
        Runnable runnable3;
        String str5 = str3;
        String str6 = str4;
        final String str7 = str5;
        new Runnable(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AfterChoosing(str7);
            }
        };
        final String str8 = str6;
        new Runnable(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AfterChoosing(str8);
            }
        };
        new Runnable(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AfterChoosing(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.activity.getString(17039360));
            }
        };
        twoButtonDialog(this.activity, str, str2, str5, str6, z, runnable, runnable2, runnable3, this.linkify);
    }

    public final void twoButtonDialog(Activity activity2, String str, String str2, String str3, String str4, boolean z, Runnable runnable, Runnable runnable2, Runnable runnable3, boolean z2) {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        DialogInterface.OnClickListener onClickListener2;
        DialogInterface.OnClickListener onClickListener3;
        Activity activity3 = activity2;
        String str5 = str;
        String str6 = str2;
        String str7 = str3;
        String str8 = str4;
        boolean z3 = z;
        Runnable runnable4 = runnable;
        Runnable runnable5 = runnable2;
        Runnable runnable6 = runnable3;
        boolean z4 = z2;
        int i = Log.i(LOG_TAG, "ShowChooseDialog: ".concat(String.valueOf(str5)));
        new AlertDialog.Builder(activity3);
        AlertDialog create = builder.create();
        if (!str6.isEmpty()) {
            create.setTitle(str6);
        }
        create.setCancelable(false);
        if (!str5.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str5));
        }
        linkifyIfNeeded(z4, create, str5);
        final Runnable runnable7 = runnable4;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                runnable7.run();
            }
        };
        create.setButton(-1, str7, onClickListener);
        final Runnable runnable8 = runnable5;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                runnable8.run();
            }
        };
        create.setButton(-3, str8, onClickListener2);
        if (z3) {
            String string = activity3.getString(17039360);
            final Runnable runnable9 = runnable6;
            new DialogInterface.OnClickListener(this) {
                private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogInterface dialogInterface2 = dialogInterface;
                    int i2 = i;
                    runnable9.run();
                }
            };
            create.setButton(-2, string, onClickListener3);
        }
        create.show();
    }

    @SimpleFunction(description = "Shows a dialog box where the user can enter text, after which the AfterTextInput event will be raised.  If cancelable is true there will be an additional CANCEL button. Entering text will raise the AfterTextInput event.  The \"response\" parameter to AfterTextInput will be the text that was entered, or \"Cancel\" if the CANCEL button was pressed.")
    public final void ShowTextDialog(String str, String str2, boolean z) {
        textInputDialog(str, str2, z, false);
    }

    @SimpleFunction(description = "Shows a dialog box where the user can enter password (input is masked), after which the AfterTextInput event will be raised.  If cancelable is true there will be an additional CANCEL button. Entering password will raise the AfterTextInput event.  The \"response\" parameter to AfterTextInput will be the entered password, or \"Cancel\" if CANCEL button was pressed.")
    public final void ShowPasswordDialog(String str, String str2, boolean z) {
        textInputDialog(str, str2, z, true);
    }

    private void textInputDialog(String str, String str2, boolean z, boolean z2) {
        AlertDialog.Builder builder;
        EditText editText;
        DialogInterface.OnClickListener onClickListener;
        DialogInterface.OnClickListener onClickListener2;
        String str3 = str;
        String str4 = str2;
        boolean z3 = z;
        boolean z4 = z2;
        new AlertDialog.Builder(this.activity);
        AlertDialog create = builder.create();
        if (!str4.isEmpty()) {
            create.setTitle(TextViewUtil.fromHtml(str4));
        }
        if (!str3.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str3));
        }
        linkifyIfNeeded(this.linkify, create, str3);
        new EditText(this.activity);
        EditText editText2 = editText;
        if (z4) {
            editText2.setInputType(129);
        }
        create.setView(editText2);
        create.setCancelable(false);
        final EditText editText3 = editText2;
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hideKeyboardHelper(editText3);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AfterTextInput(editText3.getText().toString());
            }
        };
        create.setButton(-1, "OK", onClickListener);
        if (z3) {
            String string = this.activity.getString(17039360);
            final EditText editText4 = editText2;
            final String str5 = string;
            new DialogInterface.OnClickListener(this) {
                private /* synthetic */ Notifier hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialogInterface dialogInterface2 = dialogInterface;
                    int i2 = i;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hideKeyboardHelper(editText4);
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AfterTextInput(str5);
                }
            };
            create.setButton(-2, string, onClickListener2);
        }
        create.show();
    }

    @SimpleEvent(description = "Event to detect that a user have done his text input in the \"Show Text Dialog\".")
    public final void AfterTextInput(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterTextInput", str);
    }

    @SimpleFunction(description = "Shows a dialog box with an optional title and message (use empty strings if they are not wanted). This dialog box contains a spinning artifact to indicate that the program is working. It cannot be canceled by the user but must be dismissed by the App Inventor Program by using the DismissProgressDialog block.")
    public final void ShowProgressDialog(String str, String str2) {
        progressDialogHelper(str, str2);
    }

    @SimpleEvent(description = "Event to detect that a user have done his selection.")
    public final void AfterChoosing(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterChoosing", str);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true will attempt to make text clickable where possible; e.g. hyperlinks, phone numbers. Can not be used for Progress Dialogs. ")
    public final void Linkify(boolean z) {
        boolean z2 = z;
        this.linkify = z2;
    }

    @SimpleProperty(description = "Returns status of Linkify property")
    public final boolean Linkify() {
        return this.linkify;
    }

    private static void linkifyIfNeeded(boolean z, AlertDialog alertDialog, String str) {
        TextView textView;
        AlertDialog alertDialog2 = alertDialog;
        String str2 = str;
        if (z) {
            new TextView(alertDialog2.getContext());
            TextView textView2 = textView;
            int DpToPixels = KodularUnitUtil.DpToPixels(alertDialog2.getContext(), 15);
            int i = DpToPixels;
            int i2 = i;
            int i3 = i;
            int i4 = DpToPixels;
            textView2.setPadding(i2, i3, i4, i4);
            textView2.setText(TextViewUtil.linkifyMessage(str2));
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
            alertDialog2.setMessage((CharSequence) null);
            alertDialog2.setView(textView2);
        }
    }

    /* access modifiers changed from: private */
    public void toastNow(String str) {
        StringBuilder sb;
        String format = String.format("#%06X", new Object[]{Integer.valueOf(16777215 & this.textColor)});
        new StringBuilder("<font color='");
        String sb2 = sb.append(format).append("'>").append(str).append("</font>").toString();
        int round = Math.round(50.0f * this.activity.getResources().getDisplayMetrics().density);
        Toast makeText = Toast.makeText(this.activity, TextViewUtil.fromHtml(sb2), this.notifierLength);
        Toast toast = makeText;
        makeText.setGravity(81, 0, round);
        try {
            View view = toast.getView();
            view.getBackground().setColorFilter(this.backgroundColor, PorterDuff.Mode.SRC_ATOP);
            toast.setView(view);
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
        toast.show();
    }

    private void dismissProgressDialogHelper(ProgressDialog progressDialog2) {
        ProgressDialog progressDialog3 = progressDialog2;
        if (progressDialog3 != null) {
            progressDialog3.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void setDialogHelper(Dialog dialog, boolean z) {
        Dialog dialog2 = dialog;
        boolean z2 = z;
        if (this.useBackgroundColor) {
            try {
                if (dialog2.getWindow() != null) {
                    dialog2.getWindow().getDecorView().getBackground().setColorFilter(this.backgroundColor, PorterDuff.Mode.SRC_ATOP);
                }
            } catch (Exception e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        if (dialog2.getWindow() != null) {
            dialog2.getWindow().setDimAmount(this.dimAmount);
        }
        try {
            TextView textView = (TextView) dialog2.findViewById(16908299);
            TextView textView2 = textView;
            if (textView != null) {
                textView2.setMovementMethod(LinkMovementMethod.getInstance());
            }
        } catch (Exception e3) {
            int e4 = Log.e(LOG_TAG, String.valueOf(e3));
        }
        try {
            TextView textView3 = (TextView) dialog2.findViewById(16908299);
            TextView textView4 = textView3;
            if (textView3 != null) {
                messageFontHelper(textView4);
                textView4.setTextColor(this.textColor);
            }
        } catch (Exception e5) {
            int e6 = Log.e(LOG_TAG, String.valueOf(e5));
        }
        try {
            TextView textView5 = (TextView) dialog2.findViewById(this.activity.getResources().getIdentifier("alertTitle", "id", "android"));
            TextView textView6 = textView5;
            if (textView5 != null) {
                titleFontHelper(textView6);
                textView6.setTextColor(this.titleColor);
            }
        } catch (Exception e7) {
            int e8 = Log.e(LOG_TAG, String.valueOf(e7));
        }
    }

    private void titleFontHelper(TextView textView) {
        TextView textView2 = textView;
        if (!this.titlefontTypefacePath.isEmpty()) {
            TextViewUtil.setCustomFontTypeface(this.container.$form(), textView2, this.titlefontTypefacePath, false, false);
        } else {
            TextViewUtil.setFontTypeface(textView2, this.titleTypeface, false, false);
        }
    }

    private void messageFontHelper(TextView textView) {
        TextView textView2 = textView;
        if (!this.messagefontTypefacePath.isEmpty()) {
            TextViewUtil.setCustomFontTypeface(this.container.$form(), textView2, this.messagefontTypefacePath, false, false);
        } else {
            TextViewUtil.setFontTypeface(textView2, this.messageTypeface, false, false);
        }
    }

    private void progressDialogHelper(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (this.progressDialog != null) {
            DismissProgressDialog();
        }
        this.progressDialog = ProgressDialog.show(this.activity, str4, str3);
        this.progressDialog.setCancelable(false);
    }

    /* access modifiers changed from: private */
    public void hideKeyboardHelper(View view) {
        View view2 = view;
        if (view2 != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.activity.getSystemService("input_method");
            InputMethodManager inputMethodManager2 = inputMethodManager;
            if (inputMethodManager != null) {
                boolean hideSoftInputFromWindow = inputMethodManager2.hideSoftInputFromWindow(view2.getWindowToken(), 0);
            }
        }
    }

    private Drawable iconHelper(String str) {
        Drawable drawable;
        String str2 = str;
        if (str2.isEmpty()) {
            return null;
        }
        try {
            Drawable drawable2 = drawable;
            new BitmapDrawable(this.activity.getResources(), MediaUtil.getBitmapDrawable(this.container.$form(), str2).getBitmap());
            return drawable2;
        } catch (Exception e) {
            return null;
        }
    }
}
