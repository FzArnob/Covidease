package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.p000v4.content.CursorLoader;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.AnimationUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "A special-purpose button. When the user taps an image picker, the device's image gallery appears, and the user can choose an image.", version = 11)
@UsesPermissions(permissionNames = "android.permission.READ_EXTERNAL_STORAGE")
public class ImagePicker extends Picker implements ActivityResultListener {
    private Context context;
    /* access modifiers changed from: private */
    public Form form;
    private String pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH = "";

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ImagePicker(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.form = r3
            java.lang.String r2 = "ImagePicker"
            java.lang.String r3 = "ImagePicker Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.ImagePicker.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public void click() {
        PermissionResultHandler permissionResultHandler;
        new PermissionResultHandler(this) {
            private /* synthetic */ ImagePicker hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                String str2 = str;
                if (z) {
                    try {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.BeforePicking();
                        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.requestCode == 0) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.requestCode = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.container.$form().registerForActivityResult(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                        }
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.container.$context().startActivityForResult(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getIntent(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.requestCode);
                        AnimationUtil.ApplyOpenScreenAnimation(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.container.$context(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.container.$form().getOpenAnimType());
                    } catch (PermissionException e) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Open", e);
                    } catch (Exception e2) {
                        int e3 = Log.e("ImagePicker", String.valueOf(e2));
                    }
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Open", str2);
                }
            }
        };
        this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", permissionResultHandler);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Path to the file containing the image that was selected.")
    public String Selection() {
        return this.pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH;
    }

    /* access modifiers changed from: protected */
    public Intent getIntent() {
        Intent intent;
        new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        return intent;
    }

    public void resultReturned(int i, int i2, Intent intent) {
        int i3 = i2;
        Intent intent2 = intent;
        if (i == this.requestCode && i3 == -1) {
            this.pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(intent2.getData());
            AfterPicking(this.pgq50Ta8BOwqZ1x44UiPoTcDoRPiNHTTIb3Jgmceok7eFp2gi0sO4JRUukOMKqHH);
        }
    }

    private String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Uri uri) {
        StringBuilder sb;
        CursorLoader cursorLoader;
        try {
            new CursorLoader(this.context, uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null);
            Cursor loadInBackground = cursorLoader.loadInBackground();
            Cursor cursor = loadInBackground;
            int columnIndexOrThrow = loadInBackground != null ? cursor.getColumnIndexOrThrow("_data") : 0;
            if (cursor != null) {
                boolean moveToFirst = cursor.moveToFirst();
            }
            return cursor != null ? cursor.getString(columnIndexOrThrow) : "ERROR";
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            return sb.append(exc.getMessage()).toString();
        }
    }
}
