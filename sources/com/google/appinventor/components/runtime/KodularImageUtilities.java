package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.p003v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.Canvas;
import com.google.appinventor.components.runtime.errors.PermissionException;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

@DesignerComponent(category = ComponentCategory.UTILITIES, description = "write in ode", iconName = "images/imageUtilities.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.READ_EXTERNAL_STORAGE")
public class KodularImageUtilities extends AndroidNonvisibleComponent implements Component {
    private Activity activity;
    private final Handler androidUIHandler;
    private Form form;
    private boolean havePermission = false;
    private boolean isCompanion = false;

    static /* synthetic */ boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularImageUtilities kodularImageUtilities, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        kodularImageUtilities.havePermission = z3;
        return z2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularImageUtilities(com.google.appinventor.components.runtime.ComponentContainer r7) {
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
            r2.isCompanion = r3
            r2 = r0
            r3 = 0
            r2.havePermission = r3
            r2 = r0
            android.os.Handler r3 = new android.os.Handler
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.androidUIHandler = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            boolean r2 = r2 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r2 == 0) goto L_0x0039
            r2 = r0
            r3 = 1
            r2.isCompanion = r3
        L_0x0039:
            java.lang.String r2 = "Kodular Image Utilities"
            java.lang.String r3 = "Kodular Image Utilities Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularImageUtilities.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Load a new image from the given path to any component. You can load also images from the internet. Supported components: Image, Buttons, Layouts, Canvas.")
    public void LoadImageAsync(AndroidViewComponent androidViewComponent, String str) {
        C0795a aVar;
        Runnable runnable;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        String str2 = str;
        if (this.havePermission || (!str2.startsWith("http://") && !str2.startsWith("https://") && !str2.startsWith("HTTP://") && !str2.startsWith("HTTPS://"))) {
            new C0795a(this, androidViewComponent2.getView());
            AsyncTask execute = aVar.execute(new String[]{str2});
            return;
        }
        final AndroidViewComponent androidViewComponent3 = androidViewComponent2;
        final String str3 = str2;
        new Runnable(this) {

            /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
            final /* synthetic */ KodularImageUtilities f455hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.f455hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                PermissionResultHandler permissionResultHandler;
                new PermissionResultHandler(this) {
                    private /* synthetic */ C07931 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final void HandlePermissionResponse(String str, boolean z) {
                        String str2 = str;
                        if (z) {
                            boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.f455hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.f455hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoadImageAsync(androidViewComponent3, str3);
                            return;
                        }
                        boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.f455hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, false);
                        KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.f455hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.f455hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "LoadImageAsync", "android.permission.READ_EXTERNAL_STORAGE");
                    }
                };
                KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f455hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).askPermission("android.permission.READ_EXTERNAL_STORAGE", permissionResultHandler);
            }
        };
        boolean post = this.androidUIHandler.post(runnable);
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: com.google.appinventor.components.runtime.KodularImageUtilities$a */
    class C0795a extends AsyncTask<String, Void, Bitmap> {
        private View B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        private /* synthetic */ KodularImageUtilities hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            StringBuilder sb;
            String str = ((String[]) objArr)[0];
            String str2 = str;
            if (str.startsWith("http://") || str2.startsWith("https://") || str2.startsWith("HTTP://") || str2.startsWith("HTTPS://")) {
                return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str2);
            }
            if (str2.startsWith("file:///")) {
                return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(str2.substring(7));
            }
            if (str2.startsWith("/")) {
                return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(str2);
            }
            if (str2.startsWith("/") || str2.startsWith("file:///")) {
                int w = Log.w("Kodular Image Utilities", "The image util was not able to load the given image.");
                return null;
            } else if (!KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                return vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(str2);
            } else {
                new StringBuilder();
                return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(sb.append(Environment.getExternalStorageDirectory().getPath()).append("/Makeroid/assets/").append(str2).toString());
            }
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Drawable drawable;
            Drawable drawable2;
            Bitmap bitmap = (Bitmap) obj;
            if (bitmap == null) {
                int w = Log.w("Kodular Image Utilities", "The result after loading the image was NULL. Please try again and check if the path is an valid image path.");
            } else if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T instanceof AppCompatImageView) {
                ((AppCompatImageView) this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).setImageBitmap(bitmap);
            } else if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T instanceof ImageView) {
                ((ImageView) this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).setImageBitmap(bitmap);
            } else if (!(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T instanceof Button) && !(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T instanceof ScrollView) && !(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T instanceof HorizontalScrollView) && !(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T instanceof ViewGroup) && !(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T instanceof Canvas.CanvasView)) {
                int w2 = Log.w("Kodular Image Utilities", "The image util did not found a supported view to load a image. The supported views are: Image, Buttons, Layouts, Canvas.");
            } else if (Build.VERSION.SDK_INT >= 16) {
                new BitmapDrawable(KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).getResources(), bitmap);
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setBackground(drawable2);
            } else {
                new BitmapDrawable(KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).getResources(), bitmap);
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setBackgroundDrawable(drawable);
            }
        }

        C0795a(KodularImageUtilities kodularImageUtilities, View view) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = kodularImageUtilities;
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = view;
        }

        private static Bitmap B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str) {
            URL url;
            Bitmap bitmap = null;
            InputStream inputStream = null;
            try {
                new URL(str);
                InputStream openStream = url.openStream();
                inputStream = openStream;
                bitmap = BitmapFactory.decodeStream(openStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        int e2 = Log.e("Kodular Image Utilities", String.valueOf(e));
                    }
                }
            } catch (Exception e3) {
                int e4 = Log.e("Kodular Image Utilities", String.valueOf(e3));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e5) {
                        int e6 = Log.e("Kodular Image Utilities", String.valueOf(e5));
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e7) {
                        int e8 = Log.e("Kodular Image Utilities", String.valueOf(e7));
                    }
                }
                throw th2;
            }
            return bitmap;
        }

        private Bitmap wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str) {
            File file;
            new File(str);
            File file2 = file;
            File file3 = file2;
            if (file2.exists()) {
                try {
                    return BitmapFactory.decodeFile(file3.getAbsolutePath());
                } catch (PermissionException e) {
                    PermissionException permissionException = e;
                    boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, false);
                    KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "LoadImageAsync", permissionException);
                } catch (Exception e2) {
                    int e3 = Log.e("Kodular Image Utilities", String.valueOf(e2));
                    return null;
                }
            }
            return null;
        }

        private Bitmap vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(String str) {
            InputStream inputStream = null;
            try {
                InputStream open = KodularImageUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).getAssets().open(str);
                inputStream = open;
                Bitmap decodeStream = BitmapFactory.decodeStream(open);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        int e2 = Log.e("Kodular Image Utilities", String.valueOf(e));
                    }
                }
                return decodeStream;
            } catch (Exception e3) {
                int e4 = Log.e("Kodular Image Utilities", String.valueOf(e3));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e5) {
                        int e6 = Log.e("Kodular Image Utilities", String.valueOf(e5));
                    }
                }
                return null;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e7) {
                        int e8 = Log.e("Kodular Image Utilities", String.valueOf(e7));
                    }
                }
                throw th2;
            }
        }
    }
}
