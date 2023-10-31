package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic space in Arrangements.", iconName = "images/space.png", nonVisible = true, version = 2)
public final class KodularDynamicSpace extends AndroidNonvisibleComponent {
    private final String LOG_TAG = "KodularDynamicSpace";
    private Context context;
    private Form form;
    private List<KodularDynamicModel> kodularDynamicModelList;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularDynamicSpace(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "KodularDynamicSpace"
            r2.LOG_TAG = r3
            r2 = r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.kodularDynamicModelList = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularDynamicSpace.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Create a new space component dynamically. Use for width/height '-1' for wrap content or '-2' for fill parent.")
    public final void CreateSpace(int i, AndroidViewComponent androidViewComponent, int i2, int i3) {
        Space space;
        ViewGroup.LayoutParams layoutParams;
        Object obj;
        int i4 = i;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i5 = i2;
        int i6 = i3;
        try {
            new Space(this.context);
            Space space2 = space;
            Space space3 = space2;
            Space space4 = space2;
            int i7 = i6;
            int i8 = i5;
            int DpToPixels = KodularUnitUtil.DpToPixels(this.context, i8);
            int DpToPixels2 = KodularUnitUtil.DpToPixels(this.context, i7);
            if (i8 == -1) {
                DpToPixels = -2;
            } else if (i8 == -2) {
                DpToPixels = -1;
            }
            if (i7 == -1) {
                DpToPixels2 = -2;
            } else if (i7 == -2) {
                DpToPixels2 = -1;
            }
            new LinearLayout.LayoutParams(DpToPixels, DpToPixels2);
            space3.setLayoutParams(layoutParams);
            new KodularDynamicModel(i4, space4, androidViewComponent2);
            boolean add = this.kodularDynamicModelList.add(obj);
            ((LinearLayout) ((ViewGroup) androidViewComponent2.getView()).getChildAt(0)).addView(space4);
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicSpace", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Remove a space component with the given id.")
    public final void DeleteSpace(int i) {
        int i2 = i;
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i2, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((Space) KodularDynamicUtil.getObjectById(i2, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i2, this.kodularDynamicModelList);
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicSpace", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the space referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetSpaceById(int i) {
        KodularDynamicUtil.ComponentReturnHelper componentReturnHelper;
        Space space = (Space) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Space space2 = space;
        if (space == null) {
            return null;
        }
        new KodularDynamicUtil.ComponentReturnHelper(space2);
        return componentReturnHelper;
    }

    @SimpleFunction(description = "Update the Width of a space component.")
    public final void SetWidth(int i, int i2) {
        int i3 = i2;
        Space space = (Space) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Space space2 = space;
        if (space != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) space2.getLayoutParams();
            layoutParams.width = KodularUnitUtil.DpToPixels(this.context, i3);
            space2.setLayoutParams(layoutParams);
            space2.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Width of a space component.")
    public final int GetWidth(int i) {
        Space space = (Space) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Space space2 = space;
        if (space != null) {
            return space2.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Height of a space component.")
    public final void SetHeight(int i, int i2) {
        int i3 = i2;
        Space space = (Space) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Space space2 = space;
        if (space != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) space2.getLayoutParams();
            layoutParams.height = KodularUnitUtil.DpToPixels(this.context, i3);
            space2.setLayoutParams(layoutParams);
            space2.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Height of a space component.")
    public final int GetHeight(int i) {
        Space space = (Space) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        Space space2 = space;
        if (space != null) {
            return space2.getHeight();
        }
        return 0;
    }
}
