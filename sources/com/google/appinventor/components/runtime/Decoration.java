package com.google.appinventor.components.runtime;

import android.content.Context;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/decoration.png", nonVisible = true, version = 1)
public class Decoration extends AndroidNonvisibleComponent implements Component {
    private ComponentContainer container;
    private Context context;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Decoration(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Decoration.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Margin is a way for a component to enforce some distance from others components. By specifying margin for a component, we say that keep this much distance from this component.")
    public void SetMargin(AndroidViewComponent androidViewComponent, String str) {
        List list;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        String trim = str.trim();
        String str2 = trim;
        if (!trim.isEmpty()) {
            String[] split = str2.split(",");
            new ArrayList();
            List list2 = list;
            for (int i = 0; i < split.length; i++) {
                boolean add = list2.add(Integer.valueOf(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Integer.valueOf(split[i].trim()).intValue())));
            }
            try {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) androidViewComponent2.getView().getLayoutParams();
                if (split.length == 1) {
                    layoutParams.setMargins(((Integer) list2.get(0)).intValue(), ((Integer) list2.get(0)).intValue(), ((Integer) list2.get(0)).intValue(), ((Integer) list2.get(0)).intValue());
                    androidViewComponent2.getView().setLayoutParams(layoutParams);
                } else if (split.length == 2) {
                    layoutParams.setMargins(((Integer) list2.get(1)).intValue(), ((Integer) list2.get(0)).intValue(), ((Integer) list2.get(1)).intValue(), ((Integer) list2.get(0)).intValue());
                    androidViewComponent2.getView().setLayoutParams(layoutParams);
                } else if (split.length == 4) {
                    layoutParams.setMargins(((Integer) list2.get(1)).intValue(), ((Integer) list2.get(0)).intValue(), ((Integer) list2.get(3)).intValue(), ((Integer) list2.get(2)).intValue());
                    androidViewComponent2.getView().setLayoutParams(layoutParams);
                }
            } catch (Exception e) {
                this.form.dispatchErrorOccurredEvent(this, "SetMargin", ErrorMessages.ERROR_INVALID_TYPE, e.getMessage());
            }
        }
    }

    @SimpleFunction(description = "The padding around the component. Padding is the space inside the border, between the border and the actual component content. Use single number like 5 to specify padding for top, left, bottom, righ. You can also use 4 different numbers like 5,0,10,0 for top, left, bottom right.")
    public void SetPadding(AndroidViewComponent androidViewComponent, String str) {
        List list;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        String trim = str.trim();
        String str2 = trim;
        if (!trim.isEmpty()) {
            String[] split = str2.split(",");
            new ArrayList();
            List list2 = list;
            for (int i = 0; i < split.length; i++) {
                boolean add = list2.add(Integer.valueOf(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Integer.valueOf(split[i].trim()).intValue())));
            }
            try {
                if (split.length == 1) {
                    androidViewComponent2.getView().setPadding(((Integer) list2.get(0)).intValue(), ((Integer) list2.get(0)).intValue(), ((Integer) list2.get(0)).intValue(), ((Integer) list2.get(0)).intValue());
                } else if (split.length == 2) {
                    androidViewComponent2.getView().setPadding(((Integer) list2.get(1)).intValue(), ((Integer) list2.get(0)).intValue(), ((Integer) list2.get(1)).intValue(), ((Integer) list2.get(0)).intValue());
                } else if (split.length == 4) {
                    androidViewComponent2.getView().setPadding(((Integer) list2.get(1)).intValue(), ((Integer) list2.get(0)).intValue(), ((Integer) list2.get(3)).intValue(), ((Integer) list2.get(2)).intValue());
                }
            } catch (Exception e) {
                this.form.dispatchErrorOccurredEvent(this, "SetPadding", ErrorMessages.ERROR_INVALID_TYPE, e.getMessage());
            }
        }
    }

    @SimpleFunction(description = "This block allows you to create a rectangle or round shape for the visible component. You can use Color for backgroundColor and borderColor. ")
    public void SetShape(AndroidViewComponent androidViewComponent, int i, int i2, boolean z) {
        try {
            ViewUtil.setShape(androidViewComponent.getView(), i, i2, z);
        } catch (Exception e) {
            this.form.dispatchErrorOccurredEvent(this, "SetShape", ErrorMessages.ERROR_INVALID_TYPE, e.getMessage());
        }
    }

    private int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
        return (int) (((double) i) * ((double) this.context.getResources().getDisplayMetrics().density));
    }
}
