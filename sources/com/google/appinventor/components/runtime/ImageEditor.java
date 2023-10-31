package com.google.appinventor.components.runtime;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.lang.reflect.Array;

@SimpleObject
@DesignerComponent(category = ComponentCategory.ANIMATION, description = "Non-visible component that allows users to edit images.", iconName = "images/imageEditor.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.READ_EXTERNAL_STORAGE, android.permission.WRITE_EXTERNAL_STORAGE")
public class ImageEditor extends AndroidNonvisibleComponent implements Component {
    private String Kv1J8U7LN9Ew5Fg3SGq5PZTyUq5afJMc801ng97H4mT8uP4jFrbS1BuSDErmLQPa = "NewImage.png";
    private ComponentContainer container;
    /* access modifiers changed from: private */
    public boolean havePermission = false;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ImageEditor(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "NewImage.png"
            r2.Kv1J8U7LN9Ew5Fg3SGq5PZTyUq5afJMc801ng97H4mT8uP4jFrbS1BuSDErmLQPa = r3
            r2 = r0
            r3 = 0
            r2.havePermission = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            java.lang.String r2 = "ImageEditor"
            java.lang.String r3 = "ImageEditor Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.ImageEditor.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "NewImage.png", editorType = "string")
    @SimpleProperty(description = "Save the new created image to a folder/ name of your choice.")
    public void SaveNewImageAs(String str) {
        String str2 = str;
        this.Kv1J8U7LN9Ew5Fg3SGq5PZTyUq5afJMc801ng97H4mT8uP4jFrbS1BuSDErmLQPa = str2;
    }

    @SimpleProperty(description = "Save the new created image to a folder/ name of your choice")
    public String SaveNewImageAs() {
        return this.Kv1J8U7LN9Ew5Fg3SGq5PZTyUq5afJMc801ng97H4mT8uP4jFrbS1BuSDErmLQPa;
    }

    public void Initialize() {
        PermissionResultHandler permissionResultHandler;
        new PermissionResultHandler(this) {
            private /* synthetic */ ImageEditor hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                String str2 = str;
                if (z) {
                    boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.havePermission = true;
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Save", str2);
                }
            }
        };
        this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", permissionResultHandler);
    }

    private String SaveUtil(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (this.havePermission) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bitmap2);
        }
        this.form.dispatchPermissionDeniedEvent((Component) this, "Save", "android.permission.WRITE_EXTERNAL_STORAGE");
        return "PERMISSION_DENIED_ERROR";
    }

    /* JADX WARNING: type inference failed for: r5v14, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.graphics.Bitmap r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r4 = 0
            r2 = r4
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r8 = r4
            r4 = r8
            r5 = r8
            r5.<init>()     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r3 = r4
            r4 = r1
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r6 = 0
            r7 = r3
            boolean r4 = r4.compress(r5, r6, r7)     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            java.io.File r4 = new java.io.File     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r8 = r4
            r4 = r8
            r5 = r8
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r8 = r6
            r6 = r8
            r7 = r8
            r7.<init>()     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            java.io.File r7 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            java.lang.String r7 = "/"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r7 = r0
            java.lang.String r7 = r7.Kv1J8U7LN9Ew5Fg3SGq5PZTyUq5afJMc801ng97H4mT8uP4jFrbS1BuSDErmLQPa     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            java.lang.String r6 = r6.toString()     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r5.<init>(r6)     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r1 = r4
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r5.<init>(r6)     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r8 = r4
            r4 = r8
            r5 = r8
            r2 = r5
            r5 = r3
            byte[] r5 = r5.toByteArray()     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r4.write(r5)     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r4 = r2
            r4.flush()     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r4 = r2
            r4.close()     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r4 = r1
            java.lang.String r4 = r4.getAbsolutePath()     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
            r3 = r4
            r4 = r2
            r4.flush()     // Catch:{ Exception -> 0x006f }
            r4 = r2
            r4.close()     // Catch:{ Exception -> 0x006f }
        L_0x006c:
            r4 = r3
            r0 = r4
        L_0x006e:
            return r0
        L_0x006f:
            r4 = move-exception
            r1 = r4
            java.lang.String r4 = "ImageEditor"
            r5 = r1
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r4 = android.util.Log.e(r4, r5)
            goto L_0x006c
        L_0x007e:
            r4 = move-exception
            r3 = r4
            r4 = r0
            com.google.appinventor.components.runtime.Form r4 = r4.form     // Catch:{ all -> 0x00d9 }
            r5 = r0
            java.lang.String r6 = "Save"
            r7 = r3
            r4.dispatchPermissionDeniedEvent((com.google.appinventor.components.runtime.Component) r5, (java.lang.String) r6, (com.google.appinventor.components.runtime.errors.PermissionException) r7)     // Catch:{ all -> 0x00d9 }
            java.lang.String r4 = "PERMISSION_DENIED_ERROR"
            r1 = r4
            r4 = r2
            if (r4 == 0) goto L_0x009a
            r4 = r2
            r4.flush()     // Catch:{ Exception -> 0x009d }
            r4 = r2
            r4.close()     // Catch:{ Exception -> 0x009d }
        L_0x009a:
            r4 = r1
            r0 = r4
            goto L_0x006e
        L_0x009d:
            r4 = move-exception
            r3 = r4
            java.lang.String r4 = "ImageEditor"
            r5 = r3
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r4 = android.util.Log.e(r4, r5)
            goto L_0x009a
        L_0x00ac:
            r4 = move-exception
            r3 = r4
            java.lang.String r4 = "ImageEditor"
            r5 = r3
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x00d9 }
            int r4 = android.util.Log.e(r4, r5)     // Catch:{ all -> 0x00d9 }
            r4 = r2
            if (r4 == 0) goto L_0x00c5
            r4 = r2
            r4.flush()     // Catch:{ Exception -> 0x00ca }
            r4 = r2
            r4.close()     // Catch:{ Exception -> 0x00ca }
        L_0x00c5:
            java.lang.String r4 = "ERROR"
            r0 = r4
            goto L_0x006e
        L_0x00ca:
            r4 = move-exception
            r3 = r4
            java.lang.String r4 = "ImageEditor"
            r5 = r3
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r4 = android.util.Log.e(r4, r5)
            goto L_0x00c5
        L_0x00d9:
            r4 = move-exception
            r1 = r4
            r4 = r2
            if (r4 == 0) goto L_0x00e6
            r4 = r2
            r4.flush()     // Catch:{ Exception -> 0x00e8 }
            r4 = r2
            r4.close()     // Catch:{ Exception -> 0x00e8 }
        L_0x00e6:
            r4 = r1
            throw r4
        L_0x00e8:
            r4 = move-exception
            r2 = r4
            java.lang.String r4 = "ImageEditor"
            r5 = r2
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r4 = android.util.Log.e(r4, r5)
            goto L_0x00e6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.ImageEditor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.graphics.Bitmap):java.lang.String");
    }

    private Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        try {
            return MediaUtil.getBitmapDrawable(this.container.$form(), str).getBitmap();
        } catch (Exception e) {
            int e2 = Log.e("ImageEditor", String.valueOf(e));
            return null;
        }
    }

    @SimpleFunction(description = "Grayscale is a simple image effect that changes colors to grayscale by default.")
    public String GreyscaleEffect(String str) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for (int i = 0; i < width; i++) {
            for (int i2 = 0; i2 < height; i2++) {
                int pixel = bitmap.getPixel(i, i2);
                int i3 = pixel;
                int alpha = Color.alpha(pixel);
                int red = ((Color.red(i3) + Color.green(i3)) + Color.blue(i3)) / 3;
                int i4 = red;
                createBitmap.setPixel(i, i2, Color.argb(alpha, i4, i4, red));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "This changes the contrast of your image. For example: value = 1.0 means normal picture contrast. Below 1.0 like as example 0.7 means dark contrast, above 1.0 as example 1.5 means light contrast.")
    public String SetContrast(String str, float f) {
        ColorMatrix colorMatrix;
        Canvas canvas;
        Paint paint;
        ColorFilter colorFilter;
        float f2 = f;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        ColorMatrix colorMatrix2 = colorMatrix;
        float[] fArr = new float[20];
        fArr[0] = f2;
        float[] fArr2 = fArr;
        fArr2[1] = 0.0f;
        float[] fArr3 = fArr2;
        fArr3[2] = 0.0f;
        float[] fArr4 = fArr3;
        fArr4[3] = 0.0f;
        float[] fArr5 = fArr4;
        fArr5[4] = 0.0f;
        float[] fArr6 = fArr5;
        fArr6[5] = 0.0f;
        float[] fArr7 = fArr6;
        fArr7[6] = f2;
        float[] fArr8 = fArr7;
        fArr8[7] = 0.0f;
        float[] fArr9 = fArr8;
        fArr9[8] = 0.0f;
        float[] fArr10 = fArr9;
        fArr10[9] = 0.0f;
        float[] fArr11 = fArr10;
        fArr11[10] = 0.0f;
        float[] fArr12 = fArr11;
        fArr12[11] = 0.0f;
        float[] fArr13 = fArr12;
        fArr13[12] = f2;
        float[] fArr14 = fArr13;
        fArr14[13] = 0.0f;
        float[] fArr15 = fArr14;
        fArr15[14] = 0.0f;
        float[] fArr16 = fArr15;
        fArr16[15] = 0.0f;
        float[] fArr17 = fArr16;
        fArr17[16] = 0.0f;
        float[] fArr18 = fArr17;
        fArr18[17] = 0.0f;
        float[] fArr19 = fArr18;
        fArr19[18] = 1.0f;
        float[] fArr20 = fArr19;
        fArr20[19] = 0.0f;
        new ColorMatrix(fArr20);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        new Canvas(createBitmap);
        Canvas canvas2 = canvas;
        new Paint();
        Paint paint2 = paint;
        new ColorMatrixColorFilter(colorMatrix2);
        ColorFilter colorFilter2 = paint2.setColorFilter(colorFilter);
        canvas2.drawBitmap(bitmap, 0.0f, 0.0f, paint2);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Flip your image vertical or horizontal. For example: type = 1 (vertical); type = 2 (horizontal).")
    public String FlipPicture(String str, int i) {
        Matrix matrix;
        int i2 = i;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        new Matrix();
        Matrix matrix2 = matrix;
        if (i2 == 1) {
            boolean preScale = matrix2.preScale(1.0f, -1.0f);
        } else if (i2 != 2) {
            return null;
        } else {
            boolean preScale2 = matrix2.preScale(-1.0f, 1.0f);
        }
        return SaveUtil(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix2, true));
    }

    @SimpleFunction(description = "Change the brightness of your image. For example: value = 50 (maximum = 255=100% brightness).")
    public String SetBrightness(String str, int i) {
        int i2 = i;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        for (int i3 = 0; i3 < width; i3++) {
            for (int i4 = 0; i4 < height; i4++) {
                int pixel = bitmap.getPixel(i3, i4);
                int i5 = pixel;
                int alpha = Color.alpha(pixel);
                int red = Color.red(i5);
                int green = Color.green(i5);
                int blue = Color.blue(i5);
                int i6 = red + i2;
                int i7 = i6;
                if (i6 > 255) {
                    i7 = 255;
                } else if (i7 < 0) {
                    i7 = 0;
                }
                int i8 = green + i2;
                int i9 = i8;
                if (i8 > 255) {
                    i9 = 255;
                } else if (i9 < 0) {
                    i9 = 0;
                }
                int i10 = blue + i2;
                int i11 = i10;
                if (i10 > 255) {
                    i11 = 255;
                } else if (i11 < 0) {
                    i11 = 0;
                }
                createBitmap.setPixel(i3, i4, Color.argb(alpha, i7, i9, i11));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "This effect inverts your image.")
    public String InvertEffect(String str) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                int pixel = bitmap.getPixel(i2, i);
                int i3 = pixel;
                createBitmap.setPixel(i2, i, Color.argb(Color.alpha(pixel), 255 - Color.red(i3), 255 - Color.green(i3), 255 - Color.blue(i3)));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Make a new image with a shading filter. For example: shadingColor = green(rgb value).")
    public String ShadingFilter(String str, int i) {
        int i2 = i;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                int[] iArr2 = iArr;
                int i5 = (i3 * width) + i4;
                iArr2[i5] = iArr2[i5] & i2;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        Bitmap bitmap2 = createBitmap;
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return SaveUtil(bitmap2);
    }

    @SimpleFunction(description = "Set a color filter to your image. For example: red = 30; green = 40; blue = 20.")
    public String ColorFilter(String str, double d, double d2, double d3) {
        double d4 = d;
        double d5 = d2;
        double d6 = d3;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        for (int i = 0; i < width; i++) {
            for (int i2 = 0; i2 < height; i2++) {
                int pixel = bitmap.getPixel(i, i2);
                int i3 = pixel;
                createBitmap.setPixel(i, i2, Color.argb(Color.alpha(pixel), (int) (((double) Color.red(i3)) * d4), (int) (((double) Color.green(i3)) * d5), (int) (((double) Color.blue(i3)) * d6)));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Set a sepia toning effect to your image. For example: depth = 20; red = 10; green = 20; blue = 25.")
    public String SepiaToningEffect(String str, int i, double d, double d2, double d3) {
        int i2 = i;
        double d4 = d;
        double d5 = d2;
        double d6 = d3;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        for (int i3 = 0; i3 < width; i3++) {
            for (int i4 = 0; i4 < height; i4++) {
                int pixel = bitmap.getPixel(i3, i4);
                int i5 = pixel;
                int alpha = Color.alpha(pixel);
                int red = (int) ((0.3d * ((double) Color.red(i5))) + (0.59d * ((double) Color.green(i5))) + (0.11d * ((double) Color.blue(i5))));
                int i6 = red;
                int i7 = red;
                int i8 = i7;
                int i9 = i7;
                int i10 = (int) (((double) i6) + (((double) i2) * d4));
                int i11 = i10;
                if (i10 > 255) {
                    i11 = 255;
                }
                int i12 = (int) (((double) i8) + (((double) i2) * d5));
                int i13 = i12;
                if (i12 > 255) {
                    i13 = 255;
                }
                int i14 = (int) (((double) i9) + (((double) i2) * d6));
                int i15 = i14;
                if (i14 > 255) {
                    i15 = 255;
                }
                createBitmap.setPixel(i3, i4, Color.argb(alpha, i11, i13, i15));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Set a round corner to your image. For example: round = 45.")
    public String RoundCorner(String str, float f) {
        Canvas canvas;
        Paint paint;
        Rect rect;
        RectF rectF;
        Xfermode xfermode;
        float f2 = f;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        new Canvas(createBitmap);
        Canvas canvas2 = canvas;
        Canvas canvas3 = canvas2;
        canvas2.drawARGB(0, 0, 0, 0);
        new Paint();
        Paint paint2 = paint;
        Paint paint3 = paint2;
        paint2.setAntiAlias(true);
        paint3.setColor(-16777216);
        new Rect(0, 0, width, height);
        Rect rect2 = rect;
        new RectF(rect2);
        RectF rectF2 = rectF;
        float f3 = f2;
        canvas3.drawRoundRect(rectF2, f3, f3, paint3);
        new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        Xfermode xfermode2 = paint3.setXfermode(xfermode);
        Rect rect3 = rect2;
        canvas3.drawBitmap(bitmap, rect3, rect3, paint3);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Set a highlight effect to your image.")
    public String HighlightEffect(String str) {
        Canvas canvas;
        Paint paint;
        MaskFilter maskFilter;
        Paint paint2;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth() + 96, bitmap.getHeight() + 96, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap);
        Canvas canvas2 = canvas;
        Canvas canvas3 = canvas2;
        canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
        new Paint();
        Paint paint3 = paint;
        new BlurMaskFilter(15.0f, BlurMaskFilter.Blur.NORMAL);
        MaskFilter maskFilter2 = paint3.setMaskFilter(maskFilter);
        int[] iArr = new int[2];
        Bitmap extractAlpha = bitmap.extractAlpha(paint3, iArr);
        new Paint();
        Paint paint4 = paint2;
        paint4.setColor(-1);
        canvas3.drawBitmap(extractAlpha, (float) iArr[0], (float) iArr[1], paint4);
        extractAlpha.recycle();
        canvas3.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Set a gamma effect to your image. For example: red = 5; green = 10; blue = 20.")
    public String GammaEffect(String str, double d, double d2, double d3) {
        double d4 = d;
        double d5 = d2;
        double d6 = d3;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[256];
        int[] iArr2 = new int[256];
        int[] iArr3 = new int[256];
        for (int i = 0; i < 256; i++) {
            iArr[i] = Math.min(255, (int) ((255.0d * Math.pow(((double) i) / 255.0d, 1.0d / d4)) + 0.5d));
            iArr2[i] = Math.min(255, (int) ((255.0d * Math.pow(((double) i) / 255.0d, 1.0d / d5)) + 0.5d));
            iArr3[i] = Math.min(255, (int) ((255.0d * Math.pow(((double) i) / 255.0d, 1.0d / d6)) + 0.5d));
        }
        for (int i2 = 0; i2 < width; i2++) {
            for (int i3 = 0; i3 < height; i3++) {
                int pixel = bitmap.getPixel(i2, i3);
                int i4 = pixel;
                createBitmap.setPixel(i2, i3, Color.argb(Color.alpha(pixel), iArr[Color.red(i4)], iArr2[Color.green(i4)], iArr3[Color.blue(i4)]));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Change the color depth of an image as you wish. For example: bitOffset = 32 (bit) or bitOffset = 16 (bit).")
    public String SetColorDepth(String str, int i) {
        int i2 = i;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        for (int i3 = 0; i3 < width; i3++) {
            for (int i4 = 0; i4 < height; i4++) {
                int pixel = bitmap.getPixel(i3, i4);
                int i5 = pixel;
                int alpha = Color.alpha(pixel);
                int red = Color.red(i5);
                int green = Color.green(i5);
                int blue = Color.blue(i5);
                int i6 = ((red + (i2 / 2)) - ((red + (i2 / 2)) % i2)) - 1;
                int i7 = i6;
                if (i6 < 0) {
                    i7 = 0;
                }
                int i8 = ((green + (i2 / 2)) - ((green + (i2 / 2)) % i2)) - 1;
                int i9 = i8;
                if (i8 < 0) {
                    i9 = 0;
                }
                int i10 = ((blue + (i2 / 2)) - ((blue + (i2 / 2)) % i2)) - 1;
                int i11 = i10;
                if (i10 < 0) {
                    i11 = 0;
                }
                createBitmap.setPixel(i3, i4, Color.argb(alpha, i7, i9, i11));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Change the hue of an image. For example: level = 1 or 2 or 3 or 4 etc.")
    public String HueFilter(String str, int i) {
        int i2 = i;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        float[] fArr = new float[3];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                int i5 = (i3 * width) + i4;
                Color.colorToHSV(iArr[i5], fArr);
                float[] fArr2 = fArr;
                fArr2[0] = fArr2[0] * ((float) i2);
                fArr[0] = (float) Math.max(0.0d, Math.min((double) fArr[0], 360.0d));
                int[] iArr2 = iArr;
                int i6 = i5;
                iArr2[i6] = iArr2[i6] | Color.HSVToColor(fArr);
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Bitmap bitmap2 = createBitmap;
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return SaveUtil(bitmap2);
    }

    @SimpleFunction(description = "Set a watermark effect to an image and change the positon/size or color of the text. For example: text = hello; textSize = 25; textColor = red(rgb value); textAlphaValue = 255 (255= 100% visible,127.5= 50% visible, 0= 0% visible); pointX = 50; pointY = 100; text underline(boolean) = true or false.")
    public String WatermarkEffect(String str, String str2, int i, int i2, boolean z, int i3, int i4, int i5) {
        Canvas canvas;
        Paint paint;
        String str3 = str2;
        int i6 = i;
        int i7 = i2;
        boolean z2 = z;
        int i8 = i3;
        int i9 = i4;
        int i10 = i5;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        new Canvas(createBitmap);
        Canvas canvas2 = canvas;
        Canvas canvas3 = canvas2;
        canvas2.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        new Paint();
        Paint paint2 = paint;
        Paint paint3 = paint2;
        paint2.setColor(i7);
        paint3.setAlpha(i8);
        paint3.setTextSize((float) i6);
        paint3.setAntiAlias(true);
        paint3.setUnderlineText(z2);
        canvas3.drawText(str3, (float) i9, (float) i10, paint3);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "That's a pretty new other cool effect. It changes the tint color of your image. For example: degree = 100.")
    public String TintColorEffect(String str, int i) {
        int i2;
        int i3 = i;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        double d = (3.14159d * ((double) i3)) / 180.0d;
        int sin = (int) (256.0d * Math.sin(d));
        int cos = (int) (256.0d * Math.cos(d));
        for (int i4 = 0; i4 < height; i4++) {
            for (int i5 = 0; i5 < width; i5++) {
                int i6 = (i4 * width) + i5;
                int i7 = (iArr[i6] >> 16) & 255;
                int i8 = (iArr[i6] >> 8) & 255;
                int i9 = iArr[i6] & 255;
                int i10 = (((i7 * 70) - (i8 * 59)) - (i9 * 11)) / 100;
                int i11 = (((i7 * -30) - (i8 * 59)) + (i9 * 89)) / 100;
                int i12 = (((i7 * 30) + (i8 * 59)) + (i9 * 11)) / 100;
                int i13 = ((sin * i11) + (cos * i10)) / 256;
                int i14 = ((cos * i11) - (sin * i10)) / 256;
                int i15 = ((i13 * -51) - (i14 * 19)) / 100;
                int i16 = i12 + i13;
                int i17 = i16;
                int i18 = i16 < 0 ? 0 : i17 > 255 ? 255 : i17;
                int i19 = i12 + i15;
                int i20 = i19;
                int i21 = i19 < 0 ? 0 : i20 > 255 ? 255 : i20;
                int i22 = i12 + i14;
                int i23 = i22;
                if (i22 < 0) {
                    i2 = 0;
                } else {
                    i2 = i23 > 255 ? 255 : i23;
                }
                iArr[i6] = -16777216 | (i18 << 16) | (i21 << 8) | i2;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        Bitmap bitmap2 = createBitmap;
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return SaveUtil(bitmap2);
    }

    @SimpleFunction(description = "Rotate the image to the degree you need it. For example: degree = 100.")
    public String ImageRotation(String str, float f) {
        Matrix matrix;
        float f2 = f;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        new Matrix();
        Matrix matrix2 = matrix;
        boolean postRotate = matrix2.postRotate(f2);
        return SaveUtil(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix2, true));
    }

    @SimpleFunction(description = "Color boost technique is basically based on color filtering, which is to increase the intensity of a single color channel. For example: type = green/ blue or red; percent = 40%.")
    public String ColorBoostEffect(String str, String str2, float f) {
        String str3 = str2;
        float f2 = f;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        for (int i = 0; i < width; i++) {
            for (int i2 = 0; i2 < height; i2++) {
                int pixel = bitmap.getPixel(i, i2);
                int i3 = pixel;
                int alpha = Color.alpha(pixel);
                int red = Color.red(i3);
                int green = Color.green(i3);
                int blue = Color.blue(i3);
                if (str3.equals("red")) {
                    int i4 = (int) (((float) red) * (f2 + 1.0f));
                    red = i4;
                    if (i4 > 255) {
                        red = 255;
                    }
                } else if (str3.equals("green")) {
                    int i5 = (int) (((float) green) * (f2 + 1.0f));
                    green = i5;
                    if (i5 > 255) {
                        green = 255;
                    }
                } else if (str3.equals("blue")) {
                    int i6 = (int) (((float) blue) * (f2 + 1.0f));
                    blue = i6;
                    if (i6 > 255) {
                        blue = 255;
                    }
                }
                createBitmap.setPixel(i, i2, Color.argb(alpha, red, green, blue));
            }
        }
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "This methods creates a new scale center crop image.")
    public String ScaleCenterCrop(String str, int i, int i2) {
        RectF rectF;
        Canvas canvas;
        int i3 = i;
        int i4 = i2;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float max = Math.max(((float) i4) / ((float) width), ((float) i3) / ((float) height));
        float f = max * ((float) width);
        float f2 = max * ((float) height);
        float f3 = (((float) i4) - f) / 2.0f;
        float f4 = (((float) i3) - f2) / 2.0f;
        new RectF(f3, f4, f3 + f, f4 + f2);
        Bitmap createBitmap = Bitmap.createBitmap(i4, i3, bitmap.getConfig());
        new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, (Rect) null, rectF, (Paint) null);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "This methods creates a blur effect.")
    public String BlurEffect(String str, float f, int i) {
        StringBuilder sb;
        StringBuilder sb2;
        float f2 = f;
        int i2 = i;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, Math.round(((float) bitmap.getWidth()) * f2), Math.round(((float) bitmap.getHeight()) * f2), false);
        Bitmap copy = createScaledBitmap.copy(createScaledBitmap.getConfig(), true);
        if (i2 <= 0) {
            return null;
        }
        int width = copy.getWidth();
        int height = copy.getHeight();
        int[] iArr = new int[(width * height)];
        new StringBuilder();
        int e = Log.e("ImageEditor", sb.append(width).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(height).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(iArr.length).toString());
        copy.getPixels(iArr, 0, width, 0, 0, width, height);
        int i3 = width - 1;
        int i4 = height - 1;
        int i5 = width * height;
        int i6 = i2;
        int i7 = i6 + i6 + 1;
        int[] iArr2 = new int[i5];
        int[] iArr3 = new int[i5];
        int[] iArr4 = new int[i5];
        int[] iArr5 = new int[Math.max(width, height)];
        int i8 = (i7 + 1) >> 1;
        int i9 = i8 * i8;
        int i10 = i9;
        int[] iArr6 = new int[(i9 << 8)];
        for (int i11 = 0; i11 < (i10 << 8); i11++) {
            int i12 = i11;
            iArr6[i12] = i12 / i10;
        }
        int i13 = 0;
        int i14 = 0;
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i7, 3});
        int i15 = i2 + 1;
        for (int i16 = 0; i16 < height; i16++) {
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            for (int i26 = -i2; i26 <= i2; i26++) {
                int i27 = iArr[i13 + Math.min(i3, Math.max(i26, 0))];
                int[] iArr8 = iArr7[i26 + i2];
                int[] iArr9 = iArr8;
                iArr8[0] = (i27 >> 16) & 255;
                iArr9[1] = (i27 >> 8) & 255;
                iArr9[2] = i27 & 255;
                int abs = i15 - Math.abs(i26);
                i19 += iArr9[0] * abs;
                i18 += iArr9[1] * abs;
                i17 += iArr9[2] * abs;
                if (i26 > 0) {
                    i25 += iArr9[0];
                    i24 += iArr9[1];
                    i23 += iArr9[2];
                } else {
                    i22 += iArr9[0];
                    i21 += iArr9[1];
                    i20 += iArr9[2];
                }
            }
            int i28 = i2;
            for (int i29 = 0; i29 < width; i29++) {
                iArr2[i13] = iArr6[i19];
                iArr3[i13] = iArr6[i18];
                iArr4[i13] = iArr6[i17];
                int i30 = i19 - i22;
                int i31 = i18 - i21;
                int i32 = i17 - i20;
                int[] iArr10 = iArr7[((i28 - i2) + i7) % i7];
                int i33 = i22 - iArr10[0];
                int i34 = i21 - iArr10[1];
                int i35 = i20 - iArr10[2];
                if (i16 == 0) {
                    int i36 = i29;
                    iArr5[i36] = Math.min(i36 + i2 + 1, i3);
                }
                int i37 = iArr[i14 + iArr5[i29]];
                iArr10[0] = (i37 >> 16) & 255;
                iArr10[1] = (i37 >> 8) & 255;
                iArr10[2] = i37 & 255;
                int i38 = i25 + iArr10[0];
                int i39 = i24 + iArr10[1];
                int i40 = i23 + iArr10[2];
                i19 = i30 + i38;
                i18 = i31 + i39;
                i17 = i32 + i40;
                i28 = (i28 + 1) % i7;
                int[] iArr11 = iArr7[i28 % i7];
                i22 = i33 + iArr11[0];
                i21 = i34 + iArr11[1];
                i20 = i35 + iArr11[2];
                i25 = i38 - iArr11[0];
                i24 = i39 - iArr11[1];
                i23 = i40 - iArr11[2];
                i13++;
            }
            i14 += width;
        }
        for (int i41 = 0; i41 < width; i41++) {
            int i42 = 0;
            int i43 = 0;
            int i44 = 0;
            int i45 = 0;
            int i46 = 0;
            int i47 = 0;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = (-i2) * width;
            for (int i52 = -i2; i52 <= i2; i52++) {
                int max = Math.max(0, i51) + i41;
                int[] iArr12 = iArr7[i52 + i2];
                int[] iArr13 = iArr12;
                iArr12[0] = iArr2[max];
                iArr13[1] = iArr3[max];
                iArr13[2] = iArr4[max];
                int abs2 = i15 - Math.abs(i52);
                i44 += iArr2[max] * abs2;
                i43 += iArr3[max] * abs2;
                i42 += iArr4[max] * abs2;
                if (i52 > 0) {
                    i50 += iArr13[0];
                    i49 += iArr13[1];
                    i48 += iArr13[2];
                } else {
                    i47 += iArr13[0];
                    i46 += iArr13[1];
                    i45 += iArr13[2];
                }
                if (i52 < i4) {
                    i51 += width;
                }
            }
            int i53 = i41;
            int i54 = i2;
            for (int i55 = 0; i55 < height; i55++) {
                iArr[i53] = (-16777216 & iArr[i53]) | (iArr6[i44] << 16) | (iArr6[i43] << 8) | iArr6[i42];
                int i56 = i44 - i47;
                int i57 = i43 - i46;
                int i58 = i42 - i45;
                int[] iArr14 = iArr7[((i54 - i2) + i7) % i7];
                int i59 = i47 - iArr14[0];
                int i60 = i46 - iArr14[1];
                int i61 = i45 - iArr14[2];
                if (i41 == 0) {
                    int i62 = i55;
                    iArr5[i62] = Math.min(i62 + i15, i4) * width;
                }
                int i63 = i41 + iArr5[i55];
                iArr14[0] = iArr2[i63];
                iArr14[1] = iArr3[i63];
                iArr14[2] = iArr4[i63];
                int i64 = i50 + iArr14[0];
                int i65 = i49 + iArr14[1];
                int i66 = i48 + iArr14[2];
                i44 = i56 + i64;
                i43 = i57 + i65;
                i42 = i58 + i66;
                i54 = (i54 + 1) % i7;
                int[] iArr15 = iArr7[i54];
                i47 = i59 + iArr15[0];
                i46 = i60 + iArr15[1];
                i45 = i61 + iArr15[2];
                i50 = i64 - iArr15[0];
                i49 = i65 - iArr15[1];
                i48 = i66 - iArr15[2];
                i53 += width;
            }
        }
        new StringBuilder();
        int e2 = Log.e("ImageEditor", sb2.append(width).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(height).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(i5).toString());
        copy.setPixels(iArr, 0, width, 0, 0, width, height);
        return SaveUtil(copy);
    }

    @SimpleFunction(description = "This methods creates a pixelate image effect. Use as example as pixelation Amount '1' for a hugh pixel effect and '99' for a almost not visible pixel effect.")
    public String Pixelate(String str, int i) {
        int i2 = i;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "";
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        return SaveUtil(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bitmap, (width / 100) * i2, (height / 100) * i2), width, height));
    }

    private static Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Bitmap bitmap, int i, int i2) {
        Matrix matrix;
        Bitmap bitmap2 = bitmap;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        new Matrix();
        Matrix matrix2 = matrix;
        Matrix matrix3 = matrix2;
        boolean postScale = matrix2.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap2, 0, 0, width, height, matrix3, false);
    }

    @SimpleFunction(description = "This methods creates a new side by side horizontal image.")
    public String MergeTwoImages(String str, String str2) {
        int width;
        int height;
        Canvas canvas;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str2);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null || hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 == null) {
            return "";
        }
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth() > hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getWidth()) {
            width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth() + hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getWidth();
        } else {
            width = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getWidth() + hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getWidth();
        }
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight() > hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getHeight()) {
            height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
        } else {
            height = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.getHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap);
        Canvas canvas2 = canvas;
        canvas2.drawBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0.0f, 0.0f, (Paint) null);
        canvas2.drawBitmap(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2, (float) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth(), 0.0f, (Paint) null);
        return SaveUtil(createBitmap);
    }

    @SimpleFunction(description = "Return true if image is in landscape format, else return false.")
    public boolean isLandscape(String str) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && bitmap.getWidth() > bitmap.getHeight();
    }

    @SimpleFunction(description = "Return true if image is in portrait format, else return false.")
    public boolean isPortrait(String str) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && bitmap.getWidth() < bitmap.getHeight();
    }

    @SimpleFunction(description = "Return true if image is in square format (means as high as wide or as wide as high), else return false.")
    public boolean isSquare(String str) {
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str);
        Bitmap bitmap = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && bitmap.getWidth() == bitmap.getHeight();
    }
}
