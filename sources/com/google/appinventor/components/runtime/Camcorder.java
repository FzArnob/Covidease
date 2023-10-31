package com.google.appinventor.components.runtime;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "A component to record a video using the device's camcorder.After the video is recorded, the name of the file on the phone containing the clip is available as an argument to the AfterRecording event. The file name can be used, for example, to set the source property of a VideoPlayer component.", iconName = "images/camcorder.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.WRITE_EXTERNAL_STORAGE, android.permission.READ_EXTERNAL_STORAGE,android.permission.CAMERA")
public class Camcorder extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
    private final ComponentContainer container;
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private int requestCode;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Camcorder(com.google.appinventor.components.runtime.ComponentContainer r5) {
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
            r2.havePermission = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Camcorder.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction
    public void RecordVideo() {
        Intent intent;
        Runnable runnable;
        String externalStorageState = Environment.getExternalStorageState();
        if (!this.havePermission) {
            new Runnable(this) {
                final /* synthetic */ Camcorder B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

                {
                    this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r6;
                }

                public final void run() {
                    PermissionResultHandler permissionResultHandler;
                    new PermissionResultHandler(this) {
                        private /* synthetic */ C06041 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void HandlePermissionResponse(String str, boolean z) {
                            String str2 = str;
                            if (z) {
                                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = this.havePermission = true;
                                this.RecordVideo();
                                return;
                            }
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.form.dispatchPermissionDeniedEvent((Component) this, "RecordVideo", "android.permission.CAMERA");
                        }
                    };
                    this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.form.askPermission("android.permission.CAMERA", permissionResultHandler);
                }
            };
            this.form.runOnUiThread(runnable);
        } else if ("mounted".equals(externalStorageState)) {
            int i = Log.i("CamcorderComponent", "External storage is available and writable");
            if (this.requestCode == 0) {
                this.requestCode = this.form.registerForActivityResult(this);
            }
            new Intent("android.media.action.VIDEO_CAPTURE");
            this.container.$context().startActivityForResult(intent, this.requestCode);
        } else if ("mounted_ro".equals(externalStorageState)) {
            this.form.dispatchErrorOccurredEvent(this, "RecordVideo", ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_READONLY, new Object[0]);
        } else {
            this.form.dispatchErrorOccurredEvent(this, "RecordVideo", ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_NOT_AVAILABLE, new Object[0]);
        }
    }

    public void resultReturned(int i, int i2, Intent intent) {
        StringBuilder sb;
        StringBuilder sb2;
        int i3 = i;
        int i4 = i2;
        Intent intent2 = intent;
        new StringBuilder("Returning result. Request code = ");
        int i5 = Log.i("CamcorderComponent", sb.append(i3).append(", result code = ").append(i4).toString());
        if (i3 != this.requestCode || i4 != -1) {
            int i6 = Log.i("CamcorderComponent", "No clip filed return; request failed");
            this.form.dispatchErrorOccurredEvent(this, "TakeVideo", ErrorMessages.ERROR_CAMCORDER_NO_CLIP_RETURNED, new Object[0]);
        } else if (intent2 == null || intent2.getData() == null) {
            int i7 = Log.i("CamcorderComponent", "Couldn't find a clip file from the Camcorder result");
            this.form.dispatchErrorOccurredEvent(this, "TakeVideo", ErrorMessages.ERROR_CAMCORDER_NO_CLIP_RETURNED, new Object[0]);
        } else {
            Uri data = intent2.getData();
            new StringBuilder("Calling Camcorder.AfterPicture with clip path ");
            int i8 = Log.i("CamcorderComponent", sb2.append(data.toString()).toString());
            AfterRecording(data.toString());
        }
    }

    @SimpleEvent
    public void AfterRecording(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterRecording", str);
    }
}
