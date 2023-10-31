package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.VIEWS, description = "", iconName = "images/bottomsheet.png", nonVisible = true, version = 2)
public class MakeroidBottomSheet extends AndroidNonvisibleComponent implements Component {
    private boolean HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy = true;
    private final Context context;
    private BottomSheetDialog hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean showStatusBar = true;
    private boolean tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J = false;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidBottomSheet(com.google.appinventor.components.runtime.ComponentContainer r5) {
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
            r2.tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J = r3
            r2 = r0
            r3 = 1
            r2.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy = r3
            r2 = r0
            r3 = 1
            r2.showStatusBar = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            java.lang.String r2 = "Bottom Sheet Component"
            java.lang.String r3 = "Bottom Sheet Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidBottomSheet.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Register any layout as example a 'horizontal arrangement', that will be later your bottom sheet dialog.")
    public void RegisterLayoutAsDialog(AndroidViewComponent androidViewComponent) {
        BottomSheetDialog bottomSheetDialog;
        try {
            ViewGroup viewGroup = (ViewGroup) androidViewComponent.getView();
            ViewGroup viewGroup2 = viewGroup;
            ((ViewGroup) viewGroup.getParent()).removeView(viewGroup2);
            new BottomSheetDialog(this.context);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = bottomSheetDialog;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setContentView((View) viewGroup2);
            AddListener();
            Update();
        } catch (Exception e) {
            int e2 = Log.e("Bottom Sheet Component", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Register any component as example a 'button', that will be later your bottom sheet dialog.")
    public void RegisterComponentAsDialog(AndroidViewComponent androidViewComponent) {
        BottomSheetDialog bottomSheetDialog;
        try {
            View view = androidViewComponent.getView();
            View view2 = view;
            ((ViewGroup) view.getParent()).removeView(view2);
            new BottomSheetDialog(this.context);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = bottomSheetDialog;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setContentView(view2);
            AddListener();
            Update();
        } catch (Exception e) {
            int e2 = Log.e("Bottom Sheet Component", String.valueOf(e));
        }
    }

    public void AddListener() {
        DialogInterface.OnShowListener onShowListener;
        DialogInterface.OnDismissListener onDismissListener;
        new DialogInterface.OnShowListener(this) {
            private /* synthetic */ MakeroidBottomSheet hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onShow(DialogInterface dialogInterface) {
                DialogInterface dialogInterface2 = dialogInterface;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Opened();
            }
        };
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnShowListener(onShowListener);
        new DialogInterface.OnDismissListener(this) {
            private /* synthetic */ MakeroidBottomSheet hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onDismiss(DialogInterface dialogInterface) {
                DialogInterface dialogInterface2 = dialogInterface;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Closed();
            }
        };
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnDismissListener(onDismissListener);
    }

    public void Update() {
        StringBuilder sb;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow() != null) {
            if (this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().addFlags(2);
            } else {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().clearFlags(2);
            }
            new StringBuilder("Dim Background is set to ");
            int i = Log.i("Bottom Sheet Component", sb.append(this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy).toString());
            if (this.showStatusBar) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().addFlags(2048);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().clearFlags(1024);
                return;
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().addFlags(1024);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWindow().clearFlags(2048);
        }
    }

    @SimpleEvent(description = "Event to detect that the dialog was opened.")
    public void Opened() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Opened", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that the dialog was closed.")
    public void Closed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Closed", new Object[0]);
    }

    @SimpleFunction(description = "Show the bottom sheet dialog.")
    public void ShowDialog() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
        }
    }

    @SimpleFunction(description = "Hide the bottom sheet dialog.")
    public void HideDialog() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.dismiss();
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If set to true the user will see a dark background effect. Else the background have then no dark background effect.")
    public void DimBackground(boolean z) {
        this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy = z;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            Update();
        }
    }

    @SimpleProperty
    public boolean DimBackground() {
        return this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void ShowStatusBar(boolean z) {
        this.showStatusBar = z;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            Update();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The status bar is the topmost bar on the screen. This property reports whether the status bar is visible.")
    public boolean ShowStatusBar() {
        return this.showStatusBar;
    }
}
