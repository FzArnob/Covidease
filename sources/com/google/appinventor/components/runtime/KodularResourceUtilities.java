package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/resourceUtilities.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.WRITE_EXTERNAL_STORAGE")
public class KodularResourceUtilities extends AndroidNonvisibleComponent implements Component {
    private Context context;
    private boolean isCompanion = false;
    private String o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = "";
    private String xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = "";

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularResourceUtilities(com.google.appinventor.components.runtime.ComponentContainer r5) {
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
            r2.isCompanion = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            boolean r2 = r2 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r2 == 0) goto L_0x002e
            r2 = r0
            r3 = 1
            r2.isCompanion = r3
        L_0x002e:
            java.lang.String r2 = "KodularResourceUtilities"
            java.lang.String r3 = "Kodular Resource Utilities Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularResourceUtilities.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "", editorType = "asset")
    @SimpleProperty(description = "Set the file which is used as resource file. The file must be stored in the assets folder.")
    public void ResourceFileFromAsset(String str) {
        String str2 = str;
        if (str2 != null) {
            this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = str2;
        }
    }

    @SimpleProperty(description = "Returns the used resource file from asset.")
    public String ResourceFileFromAsset() {
        return this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set the file which is used as resource file. The file path must be valid to any file you want to use. The path can be too a link to a url.")
    public void ResourceFileFromPath(String str) {
        String str2 = str;
        if (str2 != null) {
            this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = str2;
        }
    }

    @SimpleProperty(description = "Returns the used resource file from path.")
    public String ResourceFileFromPath() {
        return this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt;
    }

    @Deprecated
    @SimpleFunction(description = "This block is deprecated and will be removed soon. Use instead 'Get String From Asset' or 'Get String From Path'.")
    public String GetStringContentByName(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        return "'Get String Content By Name' is deprecated and works not longer. Use instead 'Get String From Asset' or 'Get String From Path' blocks.";
    }

    @SimpleFunction(description = "Get the text from a asset resource file. Make sure you uploaded a file at 'Resource File From Asset' property.")
    public String GetStringFromAsset(String str, String str2) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ResourceFileFromAsset(), this.isCompanion), str, str2);
    }

    @SimpleFunction(description = "Get the text from a path resource file. Make sure you added a file path at 'Resource File From Path' property. The path can be too a link to a url.")
    public String GetStringFromPath(String str, String str2) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ResourceFileFromPath(), this.isCompanion), str, str2);
    }

    private static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, String str2, String str3) {
        StringBuilder sb;
        JSONObject jSONObject;
        StringBuilder sb2;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        if (str4 == null) {
            new StringBuilder("There was a problem to get the string '");
            return sb2.append(str5).append("'. Make sure on your side is all correct.").toString();
        }
        try {
            new JSONObject(str4.replace("\\", ""));
            String string = jSONObject.getString(str5);
            String str7 = string;
            if (string != null) {
                return str7;
            }
            return str6;
        } catch (PermissionException e) {
            PermissionException permissionException = e;
            int e2 = Log.e("KodularResourceUtilities", String.valueOf(permissionException));
            new StringBuilder();
            return sb.append(permissionException.getMessage()).toString();
        } catch (Exception e3) {
            int e4 = Log.e("KodularResourceUtilities", String.valueOf(e3));
            return str6;
        }
    }

    private String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, boolean z) {
        StringBuilder sb;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        Reader reader;
        URL url;
        BufferedReader bufferedReader3;
        Reader reader2;
        BufferedReader bufferedReader4;
        Reader reader3;
        File file;
        String str2 = str;
        boolean z2 = z;
        if (str2.isEmpty()) {
            return "The file name can not be empty.";
        }
        File file2 = null;
        if (z2 && str2.startsWith("file://")) {
            str2 = str2.replace("file://", "");
            new File(str2);
            file2 = file;
        }
        new StringBuilder();
        StringBuilder sb2 = sb;
        if (!z2 || file2 == null) {
            new InputStreamReader(this.context.getAssets().open(str2));
            new BufferedReader(reader);
            bufferedReader = bufferedReader2;
        } else {
            try {
                if (str2.startsWith("http://") || str2.startsWith("https://")) {
                    new URL(str2);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    HttpURLConnection httpURLConnection2 = httpURLConnection;
                    httpURLConnection.setConnectTimeout(60000);
                    if (httpURLConnection2.getResponseCode() != 200) {
                        return null;
                    }
                    new InputStreamReader(httpURLConnection2.getInputStream());
                    new BufferedReader(reader2);
                    bufferedReader = bufferedReader3;
                } else {
                    new FileReader(file2);
                    new BufferedReader(reader3);
                    bufferedReader = bufferedReader4;
                }
            } catch (Exception e) {
                int e2 = Log.e("KodularResourceUtilities", String.valueOf(e));
                return null;
            }
        }
        while (true) {
            String readLine = bufferedReader.readLine();
            String str3 = readLine;
            if (readLine != null) {
                StringBuilder append = sb2.append(str3);
            } else {
                bufferedReader.close();
                return sb2.toString();
            }
        }
    }
}
