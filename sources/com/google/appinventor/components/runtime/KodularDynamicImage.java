package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p003v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic image in Arrangements.", iconName = "images/image.png", nonVisible = true, version = 2)
public final class KodularDynamicImage extends AndroidNonvisibleComponent {
    private final String LOG_TAG = "KodularDynamicImage";
    private Context context;
    /* access modifiers changed from: private */
    public Form form;
    private List<KodularDynamicModel> kodularDynamicModelList;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularDynamicImage(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "KodularDynamicImage"
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularDynamicImage.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Create a new image component dynamically. Use for width/height '-1' for wrap content or '-2' for fill parent.")
    public final void CreateImage(int i, AndroidViewComponent androidViewComponent, String str, int i2, int i3) {
        AppCompatImageView appCompatImageView;
        Object obj;
        int i4 = i;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        String str2 = str;
        int i5 = i2;
        int i6 = i3;
        try {
            new AppCompatImageView(this.context);
            AppCompatImageView appCompatImageView2 = appCompatImageView;
            AppCompatImageView appCompatImageView3 = appCompatImageView2;
            appCompatImageView2.setLayoutParams(linearLayoutParams(i5, i6));
            new KodularDynamicModel(i4, appCompatImageView3, androidViewComponent2);
            boolean add = this.kodularDynamicModelList.add(obj);
            ((LinearLayout) ((ViewGroup) androidViewComponent2.getView()).getChildAt(0)).addView(appCompatImageView3);
            imagePermissionHelper(appCompatImageView3, str2, "CreateImage");
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicImage", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Update a image component with the given id.")
    public final void UpdateImage(int i, String str) {
        String str2 = str;
        try {
            AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
            AppCompatImageView appCompatImageView2 = appCompatImageView;
            if (appCompatImageView != null) {
                imagePermissionHelper(appCompatImageView2, str2, "UpdateImage");
            }
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicImage", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Remove a image component with the given id.")
    public final void DeleteImage(int i) {
        int i2 = i;
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i2, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((AppCompatImageView) KodularDynamicUtil.getObjectById(i2, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i2, this.kodularDynamicModelList);
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicImage", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the image referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetImageById(int i) {
        KodularDynamicUtil.ComponentReturnHelper componentReturnHelper;
        AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        AppCompatImageView appCompatImageView2 = appCompatImageView;
        if (appCompatImageView == null) {
            return null;
        }
        new KodularDynamicUtil.ComponentReturnHelper(appCompatImageView2);
        return componentReturnHelper;
    }

    @SimpleFunction(description = "Specifies whether a image component with the given id should be resized to match the size of the ImageView.")
    public final void ScalePictureToFit(int i, boolean z) {
        boolean z2 = z;
        try {
            AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
            AppCompatImageView appCompatImageView2 = appCompatImageView;
            if (appCompatImageView == null) {
                return;
            }
            if (z2) {
                appCompatImageView2.setScaleType(ImageView.ScaleType.FIT_XY);
            } else {
                appCompatImageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicImage", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Specifies the angle of a image component with the given id.")
    public final void RotationAngle(int i, double d) {
        double d2 = d;
        try {
            AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
            AppCompatImageView appCompatImageView2 = appCompatImageView;
            if (appCompatImageView != null) {
                appCompatImageView2.setRotation((float) d2);
            }
        } catch (Exception e) {
            int e2 = Log.e("KodularDynamicImage", String.valueOf(e));
        }
    }

    private void imagePermissionHelper(AppCompatImageView appCompatImageView, String str, String str2) {
        PermissionResultHandler permissionResultHandler;
        AppCompatImageView appCompatImageView2 = appCompatImageView;
        String str3 = str;
        String str4 = str2;
        if (!MediaUtil.isExternalFile(str3) || !this.form.isDeniedPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            setImage(appCompatImageView2, str3);
            return;
        }
        final AppCompatImageView appCompatImageView3 = appCompatImageView2;
        final String str5 = str3;
        final String str6 = str4;
        new PermissionResultHandler(this) {

            /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
            private /* synthetic */ KodularDynamicImage f452hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.f452hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                String str2 = str;
                if (z) {
                    this.f452hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setImage(appCompatImageView3, str5);
                } else {
                    this.f452hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.f452hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str6, str2);
                }
            }
        };
        this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", permissionResultHandler);
    }

    /* access modifiers changed from: private */
    public void setImage(AppCompatImageView appCompatImageView, String str) {
        AppCompatImageView appCompatImageView2 = appCompatImageView;
        try {
            ViewUtil.setImage(appCompatImageView2, MediaUtil.getBitmapDrawable(this.form, str));
        } catch (Exception e) {
            appCompatImageView2.setImageDrawable((Drawable) null);
        }
    }

    @SimpleFunction(description = "Update the Width of a image component.")
    public final void SetWidth(int i, int i2) {
        int i3 = i2;
        AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        AppCompatImageView appCompatImageView2 = appCompatImageView;
        if (appCompatImageView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) appCompatImageView2.getLayoutParams();
            layoutParams.width = KodularUnitUtil.DpToPixels(this.context, i3);
            appCompatImageView2.setLayoutParams(layoutParams);
            appCompatImageView2.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Width of a image component.")
    public final int GetWidth(int i) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        AppCompatImageView appCompatImageView2 = appCompatImageView;
        if (appCompatImageView != null) {
            return appCompatImageView2.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Height of a image component.")
    public final void SetHeight(int i, int i2) {
        int i3 = i2;
        AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        AppCompatImageView appCompatImageView2 = appCompatImageView;
        if (appCompatImageView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) appCompatImageView2.getLayoutParams();
            layoutParams.height = KodularUnitUtil.DpToPixels(this.context, i3);
            appCompatImageView2.setLayoutParams(layoutParams);
            appCompatImageView2.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Height of a image component.")
    public final int GetHeight(int i) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        AppCompatImageView appCompatImageView2 = appCompatImageView;
        if (appCompatImageView != null) {
            return appCompatImageView2.getHeight();
        }
        return 0;
    }

    private LinearLayout.LayoutParams linearLayoutParams(int i, int i2) {
        LinearLayout.LayoutParams layoutParams;
        int i3 = i;
        int i4 = i2;
        int DpToPixels = KodularUnitUtil.DpToPixels(this.context, i3);
        int DpToPixels2 = KodularUnitUtil.DpToPixels(this.context, i4);
        if (i3 == -1) {
            DpToPixels = -2;
        } else if (i3 == -2) {
            DpToPixels = -1;
        }
        if (i4 == -1) {
            DpToPixels2 = -2;
        } else if (i4 == -2) {
            DpToPixels2 = -1;
        }
        new LinearLayout.LayoutParams(DpToPixels, DpToPixels2);
        return layoutParams;
    }
}
