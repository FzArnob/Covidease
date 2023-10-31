package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.NougatUtil;
import java.io.File;
import java.util.Date;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "A component to take a picture using the device's camera. After the picture is taken, the name of the file on the phone containing the picture is available as an argument to the AfterPicture event. The file name can be used, for example, to set the Picture property of an Image component.", iconName = "images/camera.png", nonVisible = true, version = 4)
@UsesPermissions(permissionNames = "android.permission.WRITE_EXTERNAL_STORAGE, android.permission.READ_EXTERNAL_STORAGE, android.permission.CAMERA , android.permission.FLASHLIGHT")
public class Camera extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
    private boolean KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2;
    private final ComponentContainer container;
    private boolean f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ;
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private final Activity hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private android.hardware.Camera f350hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    private int requestCode;
    private String sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = "";

    /* renamed from: sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb  reason: collision with other field name */
    private boolean f351sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Camera(com.google.appinventor.components.runtime.ComponentContainer r7) {
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
            r2.f350hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = r3
            r2 = r0
            r3 = 0
            r2.havePermission = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = 0
            r2.f351sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.app.Activity r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            java.lang.String r4 = "android.hardware.camera.flash"
            boolean r3 = r3.hasSystemFeature(r4)
            r2.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 = r3
            r2 = r0
            r3 = 0
            r2.UseFront(r3)
            java.lang.String r2 = "Camera"
            java.lang.String r3 = "Camera Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Camera.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @Deprecated
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean UseFront() {
        return this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ;
    }

    @Deprecated
    @SimpleProperty(description = "Specifies whether the front-facing camera should be used (when available). If the device does not have a front-facing camera, this option will be ignored and the camera will open normally.")
    public void UseFront(boolean z) {
        boolean z2 = z;
        this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ = z2;
    }

    @SimpleFunction(description = "Take a picture with the camera of your device.")
    public void TakePicture() {
        File file;
        StringBuilder sb;
        File file2;
        StringBuilder sb2;
        Date date;
        Intent intent;
        BulkPermissionRequest bulkPermissionRequest;
        if (!this.havePermission) {
            Form form = this.form;
            BulkPermissionRequest bulkPermissionRequest2 = bulkPermissionRequest;
            String[] strArr = new String[2];
            strArr[0] = "android.permission.CAMERA";
            String[] strArr2 = strArr;
            strArr2[1] = "android.permission.WRITE_EXTERNAL_STORAGE";
            new BulkPermissionRequest(this, this, "TakePicture", strArr2) {
                private /* synthetic */ Camera hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r10;
                }

                public final void onGranted() {
                    boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.havePermission = true;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TakePicture();
                }
            };
            form.askPermission(bulkPermissionRequest2);
            return;
        }
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState)) {
            int i = Log.i("Camera", "External storage is available and writable");
            new StringBuilder();
            new File(sb.append(Environment.getExternalStorageDirectory()).append("/Pictures").toString());
            File file3 = file;
            File file4 = file3;
            if (!file3.exists()) {
                boolean mkdir = file4.mkdir();
            }
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            new StringBuilder("/Pictures/kodular_");
            new Date();
            new File(externalStorageDirectory, sb2.append(date.getTime()).append(".jpg").toString());
            File file5 = file2;
            this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = file5.getAbsolutePath();
            Uri packageUri = NougatUtil.getPackageUri(this.form, file5);
            new Intent("android.media.action.IMAGE_CAPTURE");
            Intent intent2 = intent;
            Intent intent3 = intent2;
            Intent putExtra = intent2.putExtra("output", packageUri);
            if (Build.VERSION.SDK_INT <= 22) {
                intent3.setClipData(ClipData.newRawUri("", packageUri));
                Intent addFlags = intent3.addFlags(2);
                Intent addFlags2 = intent3.addFlags(1);
            }
            if (this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ) {
                Intent putExtra2 = intent3.putExtra("android.intent.extras.CAMERA_FACING", 1);
            }
            if (this.requestCode == 0) {
                this.requestCode = this.form.registerForActivityResult(this);
            }
            this.container.$context().startActivityForResult(intent3, this.requestCode);
        } else if ("mounted_ro".equals(externalStorageState)) {
            this.form.dispatchErrorOccurredEvent(this, "TakePicture", ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_READONLY, new Object[0]);
        } else {
            this.form.dispatchErrorOccurredEvent(this, "TakePicture", ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_NOT_AVAILABLE, new Object[0]);
        }
    }

    public void resultReturned(int i, int i2, Intent intent) {
        StringBuilder sb;
        File file;
        StringBuilder sb2;
        Intent intent2;
        int i3 = i;
        int i4 = i2;
        Intent intent3 = intent;
        new StringBuilder("Returning result. Request code = ");
        int i5 = Log.i("Camera", sb.append(i3).append(", result code = ").append(i4).toString());
        if (i3 != this.requestCode || i4 != -1) {
            return;
        }
        if (!this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb.isEmpty()) {
            new File(this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb);
            File file2 = file;
            File file3 = file2;
            if (file2.exists()) {
                Uri packageUri = NougatUtil.getPackageUri(this.form, file3);
                if (packageUri != null) {
                    new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                    Intent intent4 = intent2;
                    Intent data = intent4.setData(packageUri);
                    this.container.$context().getApplicationContext().sendBroadcast(intent4);
                }
                new StringBuilder("Calling Camera.AfterPicture with image path ");
                int i6 = Log.i("Camera", sb2.append(this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb).toString());
                AfterPicture(this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb);
            } else {
                int i7 = Log.i("CameraComponent", "Couldn't find an image file from the Camera result");
                this.form.dispatchErrorOccurredEvent(this, "TakePicture", 201, new Object[0]);
            }
            this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = "";
            return;
        }
        int i8 = Log.i("CameraComponent", "Couldn't find an image file from the Camera result");
        this.form.dispatchErrorOccurredEvent(this, "TakePicture", 201, new Object[0]);
    }

    @SimpleEvent(description = "Returns the taken picture.")
    public void AfterPicture(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterPicture", str);
    }

    @SimpleFunction(description = "Toggle the flash of your device to on or off.")
    public void ToggleLight() {
        StringBuilder sb;
        SurfaceTexture surfaceTexture;
        PermissionResultHandler permissionResultHandler;
        if (!this.havePermission) {
            new PermissionResultHandler(this) {
                private /* synthetic */ Camera hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void HandlePermissionResponse(String str, boolean z) {
                    String str2 = str;
                    if (z) {
                        boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.havePermission = true;
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ToggleLight();
                        return;
                    }
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "ToggleLight", "android.permission.CAMERA");
                }
            };
            this.form.askPermission("android.permission.CAMERA", permissionResultHandler);
        } else if (this.f351sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb) {
            if (this.f350hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                this.f350hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stopPreview();
                this.f350hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.release();
                this.f350hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            }
            this.f351sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = false;
        } else {
            try {
                this.f350hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = android.hardware.Camera.open();
                Camera.Parameters parameters = this.f350hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getParameters();
                parameters.setFlashMode("torch");
                this.f350hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setParameters(parameters);
                new SurfaceTexture(0);
                this.f350hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPreviewTexture(surfaceTexture);
                this.f350hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startPreview();
                this.f351sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = true;
            } catch (PermissionException e) {
                PermissionException permissionException = e;
                int e2 = Log.e("Camera", String.valueOf(permissionException));
                new StringBuilder();
                this.form.dispatchPermissionDeniedEvent((Component) this, "ToggleLight", sb.append(permissionException.getMessage()).toString());
            } catch (Exception e3) {
                int e4 = Log.e("Camera", String.valueOf(e3));
            }
        }
    }

    @SimpleFunction(description = "Returns true if your device has a flash.")
    public boolean HasFlash() {
        return this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2;
    }
}
