package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@SimpleObject
@DesignerComponent(category = ComponentCategory.VIEWS, description = "", helpUrl = "https://docs.kodular.io/components/layout/surface-view/", iconName = "images/surface.png", version = 2)
@UsesPermissions(permissionNames = "android.permission.WRITE_EXTERNAL_STORAGE, android.permission.READ_EXTERNAL_STORAGE, android.permission.CAMERA, android.permission.FLASHLIGHT")
public final class Surface extends AndroidViewComponent implements Camera.FaceDetectionListener, Camera.PreviewCallback, SurfaceHolder.Callback, ComponentContainer, OnDestroyListener {
    private static final String LOG_TAG = "Surface";
    private final Activity activity;
    private AlignmentUtil alignmentSetter;
    private final Handler androidUIHandler;
    private int cameraID = 0;
    private boolean clickable = false;
    private Context context;
    private boolean faceDetect = false;
    private boolean havePermission = false;
    private int horizontalAlignment = 1;
    private boolean isPause = false;
    private boolean isPlaying = false;
    private boolean isStop = false;
    private final ViewGroup mainLayout;
    private Camera myCamera;

    /* renamed from: pm */
    private PackageManager f50pm;
    private SurfaceHolder sHolder;
    private boolean saveAsFile = false;
    private SurfaceView surfaceView;
    private boolean useBackCamera = true;
    private boolean useFlashlight = false;
    private int verticalAlignment = 1;
    private final LinearLayout viewLayout;

    static /* synthetic */ boolean access$002(Surface surface, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        surface.havePermission = z3;
        return z2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Surface(com.google.appinventor.components.runtime.ComponentContainer r11) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            android.os.Handler r3 = new android.os.Handler
            r9 = r3
            r3 = r9
            r4 = r9
            r4.<init>()
            r2.androidUIHandler = r3
            r2 = r0
            r3 = 0
            r2.clickable = r3
            r2 = r0
            r3 = 0
            r2.faceDetect = r3
            r2 = r0
            r3 = 1
            r2.useBackCamera = r3
            r2 = r0
            r3 = 0
            r2.useFlashlight = r3
            r2 = r0
            r3 = 0
            r2.saveAsFile = r3
            r2 = r0
            r3 = 0
            r2.cameraID = r3
            r2 = r0
            r3 = 0
            r2.isPlaying = r3
            r2 = r0
            r3 = 0
            r2.isPause = r3
            r2 = r0
            r3 = 0
            r2.isStop = r3
            r2 = r0
            r3 = 1
            r2.verticalAlignment = r3
            r2 = r0
            r3 = 1
            r2.horizontalAlignment = r3
            r2 = r0
            r3 = 0
            r2.havePermission = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            android.view.SurfaceView r3 = new android.view.SurfaceView
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = r0
            android.content.Context r5 = r5.context
            r4.<init>(r5)
            r2.surfaceView = r3
            r2 = r0
            r9 = r2
            r2 = r9
            r3 = r9
            android.view.SurfaceView r3 = r3.surfaceView
            android.view.SurfaceHolder r3 = r3.getHolder()
            r2.sHolder = r3
            r2 = r0
            android.view.SurfaceHolder r2 = r2.sHolder
            r3 = r0
            r2.addCallback(r3)
            r2 = r0
            r9 = r2
            r2 = r9
            r3 = r9
            android.content.Context r3 = r3.context
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            r2.f50pm = r3
            r2 = r0
            com.google.appinventor.components.runtime.LinearLayout r3 = new com.google.appinventor.components.runtime.LinearLayout
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = r0
            android.content.Context r5 = r5.context
            r6 = 1
            r7 = 100
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r8 = 100
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r4.<init>(r5, r6, r7, r8)
            r2.viewLayout = r3
            r2 = r0
            com.google.appinventor.components.runtime.LinearLayout r2 = r2.viewLayout
            r3 = 0
            r2.setBaselineAligned(r3)
            r2 = r0
            com.google.appinventor.components.runtime.util.AlignmentUtil r3 = new com.google.appinventor.components.runtime.util.AlignmentUtil
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = r0
            com.google.appinventor.components.runtime.LinearLayout r5 = r5.viewLayout
            r4.<init>(r5)
            r2.alignmentSetter = r3
            r2 = r0
            com.google.appinventor.components.runtime.util.AlignmentUtil r2 = r2.alignmentSetter
            r3 = r0
            int r3 = r3.horizontalAlignment
            r2.setHorizontalAlignment(r3)
            r2 = r0
            com.google.appinventor.components.runtime.util.AlignmentUtil r2 = r2.alignmentSetter
            r3 = r0
            int r3 = r3.verticalAlignment
            r2.setVerticalAlignment(r3)
            r2 = r0
            android.widget.FrameLayout r3 = new android.widget.FrameLayout
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = r0
            android.content.Context r5 = r5.context
            r4.<init>(r5)
            r2.mainLayout = r3
            r2 = r0
            android.view.ViewGroup r2 = r2.mainLayout
            android.view.ViewGroup$LayoutParams r3 = new android.view.ViewGroup$LayoutParams
            r9 = r3
            r3 = r9
            r4 = r9
            r5 = 100
            r6 = 100
            r4.<init>(r5, r6)
            r2.setLayoutParams(r3)
            r2 = r0
            android.view.ViewGroup r2 = r2.mainLayout
            r3 = r0
            android.view.SurfaceView r3 = r3.surfaceView
            r2.addView(r3)
            r2 = r0
            android.view.ViewGroup r2 = r2.mainLayout
            r3 = r0
            com.google.appinventor.components.runtime.LinearLayout r3 = r3.viewLayout
            android.view.ViewGroup r3 = r3.getLayoutManager()
            android.view.ViewGroup$LayoutParams r4 = new android.view.ViewGroup$LayoutParams
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = -1
            r7 = -1
            r5.<init>(r6, r7)
            r2.addView(r3, r4)
            r2 = r1
            r3 = r0
            r2.$add(r3)
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            r3 = r0
            r2.registerForOnDestroy(r3)
            r2 = r0
            r3 = 0
            r2.Clickable(r3)
            r2 = r0
            r3 = 0
            r2.FaceDetection(r3)
            r2 = r0
            r3 = 0
            r2.SavePreviewAsFile(r3)
            r2 = r0
            r3 = 1
            r2.UseBackCamera(r3)
            java.lang.String r2 = "Surface"
            java.lang.String r3 = "Surface Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Surface.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final View getView() {
        return this.mainLayout;
    }

    public final Activity $context() {
        return this.activity;
    }

    public final Form $form() {
        return this.container.$form();
    }

    public final void $add(AndroidViewComponent androidViewComponent) {
        this.viewLayout.add(androidViewComponent);
    }

    public final void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        setChildWidth(androidViewComponent, i, 0);
    }

    public final void setChildWidth(AndroidViewComponent androidViewComponent, int i, int i2) {
        Runnable runnable;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i3 = i;
        int i4 = i2;
        int Width = this.container.$form().Width();
        int i5 = Width;
        if (Width == 0 && i4 < 2) {
            final AndroidViewComponent androidViewComponent3 = androidViewComponent2;
            final int i6 = i3;
            final int i7 = i4;
            new Runnable(this) {

                /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                private /* synthetic */ Surface f528hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f528hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
                }

                public final void run() {
                    this.f528hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setChildWidth(androidViewComponent3, i6, i7 + 1);
                }
            };
            boolean postDelayed = this.androidUIHandler.postDelayed(runnable, 100);
        }
        if (i3 <= -1000) {
            i3 = (i5 * (-(i3 + 1000))) / 100;
        }
        androidViewComponent2.setLastWidth(i3);
        ViewUtil.setChildWidthForVerticalLayout(androidViewComponent2.getView(), i3);
    }

    public final void setChildHeight(AndroidViewComponent androidViewComponent, int i) {
        Runnable runnable;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i2 = i;
        int Height = this.container.$form().Height();
        int i3 = Height;
        if (Height == 0) {
            final AndroidViewComponent androidViewComponent3 = androidViewComponent2;
            final int i4 = i2;
            new Runnable(this) {

                /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
                private /* synthetic */ Surface f529hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.f529hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void run() {
                    this.f529hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setChildHeight(androidViewComponent3, i4);
                }
            };
            boolean postDelayed = this.androidUIHandler.postDelayed(runnable, 100);
        }
        if (i2 <= -1000) {
            i2 = (i3 * (-(i2 + 1000))) / 100;
        }
        androidViewComponent2.setLastHeight(i2);
        ViewUtil.setChildHeightForVerticalLayout(androidViewComponent2.getView(), i2);
    }

    @SimpleProperty
    public final void Width(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Width(i2);
    }

    @SimpleProperty
    public final void Height(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Height(i2);
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        SurfaceHolder surfaceHolder2 = surfaceHolder;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (this.isPlaying) {
            StartPreview();
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        SurfaceHolder surfaceHolder2 = surfaceHolder;
        if (this.myCamera != null) {
            try {
                this.myCamera.setPreviewDisplay(this.sHolder);
            } catch (Throwable th) {
                int e = Log.e(LOG_TAG, String.valueOf(th));
            }
        }
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    public final void onPreviewFrame(byte[] bArr, Camera camera) {
        YuvImage yuvImage;
        File file;
        StringBuilder sb;
        OutputStream outputStream;
        Rect rect;
        byte[] bArr2 = bArr;
        Camera camera2 = camera;
        if (this.saveAsFile) {
            try {
                if (this.myCamera == null || this.myCamera.getParameters() == null) {
                    GotPreview("");
                    return;
                }
                Camera.Parameters parameters = this.myCamera.getParameters();
                Camera.Size previewSize = parameters.getPreviewSize();
                new YuvImage(bArr2, parameters.getPreviewFormat(), previewSize.width, previewSize.height, (int[]) null);
                YuvImage yuvImage2 = yuvImage;
                new StringBuilder();
                new File(sb.append(Environment.getExternalStorageDirectory().getPath()).append("/preMak.jpg").toString());
                File file2 = file;
                new FileOutputStream(file2);
                new Rect(0, 0, yuvImage2.getWidth(), yuvImage2.getHeight());
                boolean compressToJpeg = yuvImage2.compressToJpeg(rect, 100, outputStream);
                GotPreview(file2.getAbsolutePath());
            } catch (PermissionException e) {
                this.container.$form().dispatchPermissionDeniedEvent((Component) this, LOG_TAG, e);
            } catch (FileNotFoundException e2) {
                int e3 = Log.e(LOG_TAG, String.valueOf(e2));
            }
        }
    }

    public final void onFaceDetection(Camera.Face[] faceArr, Camera camera) {
        StringBuilder sb;
        Camera.Face[] faceArr2 = faceArr;
        Camera camera2 = camera;
        FaceDetected(String.valueOf(faceArr2.length));
        new StringBuilder("Faces Detected = ");
        int i = Log.i(LOG_TAG, sb.append(String.valueOf(faceArr2.length)).toString());
    }

    @SimpleFunction(description = "Take a picture from the camera preview.")
    public final void TakePicture() {
        Runnable runnable;
        if (!this.havePermission) {
            new Runnable(this) {
                final /* synthetic */ Surface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    PermissionResultHandler permissionResultHandler;
                    new PermissionResultHandler(this) {
                        private /* synthetic */ C10273 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void HandlePermissionResponse(String str, boolean z) {
                            String str2 = str;
                            if (z) {
                                boolean access$002 = Surface.access$002(this, true);
                                this.loadTakePicture();
                                return;
                            }
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$form().dispatchPermissionDeniedEvent((Component) this, "TakePicture", "android.permission.CAMERA");
                        }
                    };
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$form().askPermission("android.permission.CAMERA", permissionResultHandler);
                }
            };
            $form().runOnUiThread(runnable);
            return;
        }
        loadTakePicture();
    }

    /* access modifiers changed from: private */
    public void loadTakePicture() {
        Camera.PictureCallback pictureCallback;
        if (this.myCamera != null) {
            new Camera.PictureCallback(this) {
                private /* synthetic */ Surface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onPictureTaken(byte[] bArr, Camera camera) {
                    File file;
                    StringBuilder sb;
                    FileOutputStream fileOutputStream;
                    byte[] bArr2 = bArr;
                    Camera camera2 = camera;
                    FileOutputStream fileOutputStream2 = null;
                    try {
                        new StringBuilder();
                        new File(sb.append(Environment.getExternalStorageDirectory().getPath()).append("/takenPicture.jpg").toString());
                        File file2 = file;
                        new FileOutputStream(file2);
                        FileOutputStream fileOutputStream3 = fileOutputStream;
                        fileOutputStream2 = fileOutputStream3;
                        fileOutputStream3.write(bArr2);
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.PictureCreated(file2.getAbsolutePath());
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.StartPreview();
                        try {
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                        } catch (Exception e) {
                            int e2 = Log.e(Surface.LOG_TAG, String.valueOf(e));
                        }
                    } catch (PermissionException e3) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$form().dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, Surface.LOG_TAG, e3);
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.flush();
                                fileOutputStream2.close();
                            } catch (Exception e4) {
                                int e5 = Log.e(Surface.LOG_TAG, String.valueOf(e4));
                            }
                        }
                    } catch (Exception e6) {
                        int e7 = Log.e(Surface.LOG_TAG, String.valueOf(e6));
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.flush();
                                fileOutputStream2.close();
                            } catch (Exception e8) {
                                int e9 = Log.e(Surface.LOG_TAG, String.valueOf(e8));
                            }
                        }
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.flush();
                                fileOutputStream2.close();
                            } catch (Exception e10) {
                                int e11 = Log.e(Surface.LOG_TAG, String.valueOf(e10));
                            }
                        }
                        throw th2;
                    }
                }
            };
            try {
                this.myCamera.takePicture((Camera.ShutterCallback) null, (Camera.PictureCallback) null, pictureCallback);
            } catch (Exception e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
            }
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If enabled you will open the back camera for the camera preview, else you open the front camera. The block detect automatic if there is a device camera available or not.")
    public final void UseBackCamera(boolean z) {
        if (z) {
            if (this.f50pm.hasSystemFeature("android.hardware.camera")) {
                this.cameraID = 0;
                this.useBackCamera = true;
            }
        } else if (this.f50pm.hasSystemFeature("android.hardware.camera.front")) {
            this.cameraID = 1;
            this.useBackCamera = false;
        }
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled you can detect how many faces are in the front of the camera.")
    public final void FaceDetection(boolean z) {
        boolean z2 = z;
        this.faceDetect = z2;
    }

    @SimpleProperty(description = "Turn on or off the device flashlight. The function knows automatic if there is a flash/ torch available.")
    public final void Flashlight(boolean z) {
        boolean z2 = z;
        Camera.Parameters parameters = this.myCamera.getParameters();
        if (z2) {
            try {
                parameters.setFlashMode("torch");
            } catch (Exception e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
            }
        } else {
            try {
                parameters.setFlashMode("off");
            } catch (Exception e3) {
                int e4 = Log.e(LOG_TAG, String.valueOf(e3));
            }
        }
        this.useFlashlight = z2;
        this.myCamera.setParameters(parameters);
    }

    @SimpleFunction(description = "Start the camera preview.")
    public final void StartPreview() {
        Runnable runnable;
        if (!this.havePermission) {
            new Runnable(this) {
                final /* synthetic */ Surface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    PermissionResultHandler permissionResultHandler;
                    new PermissionResultHandler(this) {
                        private /* synthetic */ C10305 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void HandlePermissionResponse(String str, boolean z) {
                            String str2 = str;
                            if (z) {
                                boolean access$002 = Surface.access$002(this, true);
                                this.loadStartPreview();
                                return;
                            }
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$form().dispatchPermissionDeniedEvent((Component) this, "StartPreview", "android.permission.CAMERA");
                        }
                    };
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.$form().askPermission("android.permission.CAMERA", permissionResultHandler);
                }
            };
            $form().runOnUiThread(runnable);
            return;
        }
        loadStartPreview();
    }

    /* access modifiers changed from: private */
    public void loadStartPreview() {
        Camera.Parameters parameters;
        if (this.myCamera != null) {
            try {
                Camera.Parameters parameters2 = this.myCamera.getParameters();
                parameters2.setFocusMode("continuous-picture");
                this.myCamera.setParameters(parameters2);
                this.myCamera.setDisplayOrientation(previewHelper());
                this.myCamera.startPreview();
                this.isPlaying = true;
                this.isPause = false;
                this.isStop = false;
            } catch (PermissionException e) {
                $form().dispatchPermissionDeniedEvent((Component) this, "StartPreview", e);
            } catch (Exception e2) {
                int e3 = Log.e(LOG_TAG, String.valueOf(e2));
            }
        } else {
            this.myCamera = Camera.open(this.cameraID);
            this.myCamera.setDisplayOrientation(previewHelper());
            try {
                parameters = this.myCamera.getParameters();
                parameters.setFocusMode("continuous-picture");
            } catch (Exception e4) {
                int e5 = Log.e(LOG_TAG, String.valueOf(e4));
            } catch (Throwable th) {
                int e6 = Log.e(LOG_TAG, String.valueOf(th));
            }
            this.myCamera.setParameters(parameters);
            this.myCamera.setPreviewDisplay(this.sHolder);
            this.myCamera.setPreviewCallback(this);
            this.myCamera.startPreview();
            this.isPlaying = true;
            this.isPause = false;
            this.isStop = false;
            if (this.faceDetect) {
                this.myCamera.setFaceDetectionListener(this);
                this.myCamera.startFaceDetection();
            }
        }
    }

    @SimpleFunction(description = "Pause the camera preview.")
    public final void PausePreview() {
        if (this.myCamera != null) {
            this.myCamera.stopPreview();
            this.isPlaying = false;
            this.isPause = true;
            this.isStop = false;
            PreviewPaused();
        }
    }

    @SimpleFunction(description = "Stop the camera preview.")
    public final void StopPreview() {
        if (this.myCamera != null) {
            this.myCamera.stopFaceDetection();
            this.myCamera.setPreviewCallback((Camera.PreviewCallback) null);
            this.myCamera.stopPreview();
            this.myCamera.release();
            this.myCamera = null;
            this.isPlaying = false;
            this.isPause = false;
            this.isStop = true;
            PreviewStoped();
        }
    }

    private void DestroySurface() {
        if (this.myCamera != null) {
            this.myCamera.stopFaceDetection();
            this.myCamera.setPreviewCallback((Camera.PreviewCallback) null);
            this.myCamera.stopPreview();
            this.myCamera.release();
            this.myCamera = null;
        }
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Enable this block to enable the \"Got Preview\" event to get a image file from the camera preview.")
    public final void SavePreviewAsFile(boolean z) {
        boolean z2 = z;
        this.saveAsFile = z2;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Set the surface view component clickable or not clickable.")
    public final void Clickable(boolean z) {
        View.OnClickListener onClickListener;
        View.OnLongClickListener onLongClickListener;
        boolean z2 = z;
        this.clickable = z2;
        if (z2) {
            this.surfaceView.setClickable(true);
            new View.OnClickListener(this) {
                private /* synthetic */ Surface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onClick(View view) {
                    View view2 = view;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Clicked();
                }
            };
            this.surfaceView.setOnClickListener(onClickListener);
            new View.OnLongClickListener(this) {
                private /* synthetic */ Surface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final boolean onLongClick(View view) {
                    View view2 = view;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LongClicked();
                    return true;
                }
            };
            this.surfaceView.setOnLongClickListener(onLongClickListener);
            return;
        }
        this.surfaceView.setOnClickListener((View.OnClickListener) null);
        this.surfaceView.setOnLongClickListener((View.OnLongClickListener) null);
        this.surfaceView.setClickable(false);
    }

    @SimpleEvent(description = "Event to detect clicks on the camera preview.")
    public final void Clicked() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Clicked", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect long clicks on the camera preview.")
    public final void LongClicked() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LongClicked", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that the user has taken a picture from the preview.")
    public final void PictureCreated(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "PictureCreated", str);
    }

    @SimpleEvent(description = "Event to get the response from the camera preview as image file. The picture is by default in landscape mode. This event will only work if the option \"Save Preview As File\" is enabled/ true.")
    public final void GotPreview(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotPreview", str);
    }

    @SimpleEvent(description = "Event to detect that the user paused the camera preview.")
    public final void PreviewPaused() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "PreviewPaused", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that the user stopped the camera preview.")
    public final void PreviewStoped() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "PreviewStoped", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that there are faces in the front of the camera.")
    public final void FaceDetected(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "FaceDetected", str);
    }

    @SimpleProperty(description = "Returns Face Detection property status.")
    public final boolean FaceDetection() {
        return this.faceDetect;
    }

    @SimpleProperty(description = "Returns Flashlight property status.")
    public final boolean Flashlight() {
        return this.useFlashlight;
    }

    @SimpleProperty(description = "Returns Use Back Camera property status.")
    public final boolean UseBackCamera() {
        return this.useBackCamera;
    }

    @DesignerProperty(defaultValue = "1", editorType = "horizontal_alignment")
    @SimpleProperty
    public final void AlignHorizontal(int i) {
        int i2 = i;
        try {
            this.alignmentSetter.setHorizontalAlignment(i2);
            this.horizontalAlignment = i2;
        } catch (IllegalArgumentException e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "HorizontalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_HORIZONTAL_ALIGNMENT, Integer.valueOf(i2));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the arrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.")
    public final int AlignHorizontal() {
        return this.horizontalAlignment;
    }

    @DesignerProperty(defaultValue = "1", editorType = "vertical_alignment")
    @SimpleProperty
    public final void AlignVertical(int i) {
        int i2 = i;
        try {
            this.alignmentSetter.setVerticalAlignment(i2);
            this.verticalAlignment = i2;
        } catch (IllegalArgumentException e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "VerticalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, Integer.valueOf(i2));
        }
    }

    public final void onDestroy() {
        try {
            DestroySurface();
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public final int previewHelper() {
        int i = 0;
        Camera.Parameters parameters = this.myCamera.getParameters();
        Display defaultDisplay = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        Display display = defaultDisplay;
        if (defaultDisplay.getRotation() == 0) {
            i = 90;
        }
        if (display.getRotation() == 1) {
            i = 0;
        }
        if (display.getRotation() == 2) {
            i = 270;
        }
        if (display.getRotation() == 3) {
            i = 180;
        }
        return i;
    }
}
