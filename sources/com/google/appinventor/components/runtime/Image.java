package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p000v4.widget.ImageViewCompat;
import android.support.p003v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Component for displaying images.  The picture to display, and other aspects of the Image's appearance, can be specified in the Designer or in the Blocks Editor. You can also add gif images.", version = 5)
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.READ_EXTERNAL_STORAGE")
public final class Image extends AndroidViewComponent {
    private static final String LOG_TAG = "Image";
    private final AppCompatImageView appCompatImageView;
    private boolean clickable = false;
    private Context context;
    /* access modifiers changed from: private */
    public Form form;
    private int imageTintColor = 16777215;
    /* access modifiers changed from: private */
    public String picturePath = "";
    private boolean repl = false;
    private double rotationAngle = 0.0d;
    private int scalingMode = 0;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Image(com.google.appinventor.components.runtime.ComponentContainer r10) {
        /*
            r9 = this;
            r1 = r9
            r2 = r10
            r3 = r1
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.<init>(r4)
            r3 = r1
            java.lang.String r4 = ""
            r3.picturePath = r4
            r3 = r1
            r4 = 0
            r3.rotationAngle = r4
            r3 = r1
            r4 = 0
            r3.scalingMode = r4
            r3 = r1
            r4 = 16777215(0xffffff, float:2.3509886E-38)
            r3.imageTintColor = r4
            r3 = r1
            r4 = 0
            r3.clickable = r4
            r3 = r1
            r4 = 0
            r3.repl = r4
            r3 = r1
            r4 = r2
            android.app.Activity r4 = r4.$context()
            r3.context = r4
            r3 = r1
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.$form()
            r3.form = r4
            r3 = r1
            com.google.appinventor.components.runtime.Image$1 r4 = new com.google.appinventor.components.runtime.Image$1
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r8 = r6
            r6 = r8
            r7 = r8
            android.content.Context r7 = r7.context
            r5.<init>(r6, r7)
            r3.appCompatImageView = r4
            r3 = r2
            r4 = r1
            r3.$add(r4)
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.form
            boolean r3 = r3 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r3 == 0) goto L_0x0059
            r3 = r1
            r4 = 1
            r3.repl = r4
        L_0x0059:
            java.lang.String r3 = "Image"
            java.lang.String r4 = "Image component Created"
            int r3 = android.util.Log.d(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Image.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final View getView() {
        return this.appCompatImageView;
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple \"Click\".")
    public final void Click() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple \"Long Click\".")
    public final void LongClick() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Set the component clickable or not clickable.")
    public final void Clickable(boolean z) {
        View.OnClickListener onClickListener;
        View.OnLongClickListener onLongClickListener;
        this.clickable = z;
        if (this.clickable) {
            this.appCompatImageView.setClickable(true);
            if (Build.VERSION.SDK_INT >= 23) {
                this.appCompatImageView.setForeground(getSelectedItemDrawable());
            }
            new View.OnClickListener(this) {
                private /* synthetic */ Image hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onClick(View view) {
                    View view2 = view;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Click();
                }
            };
            this.appCompatImageView.setOnClickListener(onClickListener);
            new View.OnLongClickListener(this) {
                private /* synthetic */ Image hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final boolean onLongClick(View view) {
                    View view2 = view;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LongClick();
                    return true;
                }
            };
            this.appCompatImageView.setOnLongClickListener(onLongClickListener);
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            this.appCompatImageView.setForeground((Drawable) null);
        }
        this.appCompatImageView.setOnClickListener((View.OnClickListener) null);
        this.appCompatImageView.setOnLongClickListener((View.OnLongClickListener) null);
        this.appCompatImageView.setClickable(false);
    }

    @SimpleProperty
    public final boolean Clickable() {
        return this.clickable;
    }

    private Drawable getSelectedItemDrawable() {
        int[] iArr = {16843534};
        TypedArray obtainStyledAttributes = this.context.obtainStyledAttributes(iArr);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final String Picture() {
        return this.picturePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty
    public final void Picture(String str) {
        PermissionResultHandler permissionResultHandler;
        String str2 = str;
        this.picturePath = str2 == null ? "" : str2;
        if (this.picturePath.isEmpty()) {
            this.appCompatImageView.setImageDrawable((Drawable) null);
        } else if (!MediaUtil.isExternalFile(this.picturePath) || !this.form.isDeniedPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            imageHelper(this.picturePath);
        } else {
            new PermissionResultHandler(this) {
                private /* synthetic */ Image hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void HandlePermissionResponse(String str, boolean z) {
                    String str2 = str;
                    if (z) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.imageHelper(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.picturePath);
                    } else {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Picture", str2);
                    }
                }
            };
            this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", permissionResultHandler);
        }
    }

    /* access modifiers changed from: private */
    public void imageHelper(String str) {
        try {
            ViewUtil.setImage(this.appCompatImageView, MediaUtil.getBitmapDrawable(this.form, str));
        } catch (Exception e) {
            this.appCompatImageView.setImageDrawable((Drawable) null);
        }
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float")
    @SimpleProperty
    public final void RotationAngle(double d) {
        double d2 = d;
        if (this.rotationAngle != d2) {
            this.appCompatImageView.setRotation((float) d2);
            this.rotationAngle = d2;
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The angle at which the image picture appears rotated. This rotation does not appear on the designer screen, only on the device.")
    public final double RotationAngle() {
        return this.rotationAngle;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Specifies whether the image should be resized to match the size of the ImageView.")
    public final void ScalePictureToFit(boolean z) {
        if (z) {
            this.appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            this.appCompatImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is a limited form of animation that can attach a small number of motion types to images.  The allowable motions are ScrollRightSlow, ScrollRight, ScrollRightFast, ScrollLeftSlow, ScrollLeft, ScrollLeftFast, and Stop")
    public final void Animation(String str) {
        AnimationUtil.ApplyAnimation(this.appCompatImageView, str);
    }

    @Deprecated
    @SimpleProperty(description = "This property determines how the picture scales according to the Height or Width of the Image. Scale proportionally (0) preserves the picture aspect ratio. Scale to fit (1) matches the Image area, even if the aspect ratio changes.")
    public final void Scaling(int i) {
        Throwable th;
        int i2 = i;
        switch (i2) {
            case 0:
                this.appCompatImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
            case 1:
                this.appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;
            default:
                Throwable th2 = th;
                new IllegalArgumentError("Illegal scaling mode: ".concat(String.valueOf(i2)));
                throw th2;
        }
        this.scalingMode = i2;
    }

    @SimpleProperty
    public final int Scaling() {
        return this.scalingMode;
    }

    @SimpleProperty(description = "Change the image tint color. You can use too alpha values if you want with the 'make a list' block. Do not forget to use the 'make color' block together with the 'make a list' block.")
    public final void ImageTintColor(int i) {
        int i2 = i;
        this.imageTintColor = i2;
        ImageViewCompat.setImageTintMode(this.appCompatImageView, PorterDuff.Mode.SRC_ATOP);
        ImageViewCompat.setImageTintList(this.appCompatImageView, ColorStateList.valueOf(i2));
    }

    @SimpleProperty(description = "Return the image tint color.")
    public final int ImageTintColor() {
        return this.imageTintColor;
    }

    @SimpleFunction(description = "Clear the image tint color.")
    public final void ClearImageTintColor() {
        ImageViewCompat.setImageTintList(this.appCompatImageView, (ColorStateList) null);
    }
}
