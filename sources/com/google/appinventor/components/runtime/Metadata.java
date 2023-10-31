package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.util.Log;
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
import org.shaded.apache.http.protocol.HTTP;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "Metadata Component to Read the Meta Data of a File", iconName = "images/metadata.png", nonVisible = true, version = 2)
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.READ_EXTERNAL_STORAGE, android.permission.WRITE_EXTERNAL_STORAGE")
public class Metadata extends AndroidNonvisibleComponent implements Component {
    private ComponentContainer container;
    private Context context;
    private String hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s;
    private MediaMetadataRetriever hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    static /* synthetic */ String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Metadata metadata, String str) {
        String str2 = str;
        String str3 = str2;
        metadata.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s = str3;
        return str2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Metadata(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
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
            r2 = r0
            android.media.MediaMetadataRetriever r3 = new android.media.MediaMetadataRetriever
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            java.lang.String r2 = "KodularMetadata"
            java.lang.String r3 = "Metadata Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Metadata.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "", editorType = "asset")
    @SimpleProperty
    public void File(String str) {
        Runnable runnable;
        final String str2 = str;
        new Runnable(this) {
            final /* synthetic */ Metadata hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                PermissionResultHandler permissionResultHandler;
                new PermissionResultHandler(this) {
                    private /* synthetic */ C09301 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final void HandlePermissionResponse(String str, boolean z) {
                        StringBuilder sb;
                        StringBuilder sb2;
                        String str2 = str;
                        if (z) {
                            if (str2 == null) {
                                String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "");
                            } else {
                                String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str2);
                            }
                            if (Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).length() > 0) {
                                try {
                                    Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setDataSource(Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
                                } catch (PermissionException e) {
                                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "File", e);
                                } catch (Exception e2) {
                                    new StringBuilder();
                                    int e3 = Log.e("KodularMetadata", sb2.append(e2.getMessage()).toString());
                                }
                            } else {
                                new StringBuilder("No valid file path: ");
                                int i = Log.i("KodularMetadata", sb.append(Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)).toString());
                            }
                        } else {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "File", "android.permission.READ_EXTERNAL_STORAGE");
                        }
                    }
                };
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", permissionResultHandler);
            }
        };
        this.form.runOnUiThread(runnable);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The name of the file.")
    public String File() {
        return this.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s;
    }

    @SimpleFunction(description = "Get the Album from the file.")
    public String Album() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(1, "Album");
    }

    @SimpleFunction(description = "Get the Artist from the file.")
    public String Artist() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(2, "Artist");
    }

    @SimpleFunction(description = "Get the Author from the file.")
    public String Author() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(3, "Author");
    }

    @SimpleFunction(description = "Get the Bitrate from the file.")
    public String Bitrate() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(20, "Bitrate");
    }

    @SimpleFunction(description = "Get the Track Number from the file.")
    public String TrackNumber() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(0, "Track Number");
    }

    @SimpleFunction(description = "Get the Composer from the file.")
    public String Composer() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(4, "Composer");
    }

    @SimpleFunction(description = "Get the Date from the file.")
    public String Date() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(5, HTTP.DATE_HEADER);
    }

    @SimpleFunction(description = "Get the Disc Number from the file.")
    public String DiscNumber() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(14, "Disc Number");
    }

    @SimpleFunction(description = "Get the Duration from the file.")
    public String Duration() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(9, "Duration");
    }

    @SimpleFunction(description = "Get the Genre from the file.")
    public String Genre() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(6, "Genre");
    }

    @SimpleFunction(description = "Get the Has Audio from the file.")
    public String HasAudio() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(16, "Has Audio");
    }

    @SimpleFunction(description = "Get the Has Video from the file.")
    public String HasVideo() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(17, "Has Video");
    }

    @SimpleFunction(description = "Get the Location from the file.")
    public String Location() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(23, "Location");
    }

    @SimpleFunction(description = "Get the Mimetype from the file.")
    public String Mimetype() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(12, "Mimetype");
    }

    @SimpleFunction(description = "Get the Title from the file.")
    public String Title() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(7, "Title");
    }

    @SimpleFunction(description = "Get the Video Height from the file.")
    public String VideoHeight() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(19, "Video Height");
    }

    @SimpleFunction(description = "Get the Video Width from the file.")
    public String VideoWidth() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(18, "Video Width");
    }

    @SimpleFunction(description = "Get the Video Rotation from the file.")
    public String VideoRotation() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(24, "Video Rotation");
    }

    @SimpleFunction(description = "Get the Writer from the file.")
    public String Writer() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(11, "Writer");
    }

    @SimpleFunction(description = "Get the Year from the file.")
    public String Year() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(8, "Year");
    }

    @SimpleFunction(description = "Get a custom metadata item from the file\nyou can find a list of ids on https://developer.android.com/reference/android/media/MediaMetadataRetriever.html")
    public String CustomItem(int i) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i, "Custom");
    }

    @SimpleFunction(description = "This method finds the optional graphic or album/cover art associated associated with the data source. If there are more than one pictures, (any) one of them is returned.")
    public String EmbeddedPicture() {
        try {
            byte[] embeddedPicture = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getEmbeddedPicture();
            byte[] bArr = embeddedPicture;
            if (embeddedPicture != null) {
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
            }
        } catch (Exception e) {
            int e2 = Log.e("KodularMetadata", String.valueOf(e));
        }
        NoMetadata("EmbeddedPicture");
        return "";
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
            java.lang.String r7 = r7.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s     // Catch:{ PermissionException -> 0x007e, Exception -> 0x00ac }
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
            java.lang.String r4 = "KodularMetadata"
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
            java.lang.String r6 = "EmbeddedPicture"
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
            java.lang.String r4 = "KodularMetadata"
            r5 = r3
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r4 = android.util.Log.e(r4, r5)
            goto L_0x009a
        L_0x00ac:
            r4 = move-exception
            r3 = r4
            java.lang.String r4 = "KodularMetadata"
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
            java.lang.String r4 = "KodularMetadata"
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
            java.lang.String r4 = "KodularMetadata"
            r5 = r2
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r4 = android.util.Log.e(r4, r5)
            goto L_0x00e6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Metadata.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.graphics.Bitmap):java.lang.String");
    }

    private String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i, String str) {
        int i2 = i;
        String str2 = str;
        if (this.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s != null) {
            try {
                String extractMetadata = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.extractMetadata(i2);
                String str3 = extractMetadata;
                if (extractMetadata != null) {
                    return str3;
                }
            } catch (PermissionException e) {
                this.form.dispatchPermissionDeniedEvent((Component) this, str2, e);
            } catch (Exception e2) {
                int e3 = Log.e("KodularMetadata", String.valueOf(e2));
            }
        }
        NoMetadata(str2);
        return "";
    }

    @SimpleEvent(description = "Triggers when there is no metadata found in the file.")
    public void NoMetadata(String str) {
        StringBuilder sb;
        String str2 = str;
        if (str2 == null) {
            str2 = "";
        }
        StringBuilder sb2 = sb;
        new StringBuilder("There was no ");
        Object[] objArr = new Object[2];
        objArr[0] = sb2.append(str2.isEmpty() ? "type" : str2).append(" found in the metadata of the file").toString();
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "NoMetadata", objArr2);
    }
}
