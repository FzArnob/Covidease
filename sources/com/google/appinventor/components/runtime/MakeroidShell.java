package com.google.appinventor.components.runtime;

import android.content.Context;
import android.os.Build;
import android.os.UserManager;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "Checks Root status and executes Shell command", iconName = "images/shell.png", nonVisible = true, version = 2)
@UsesPermissions(permissionNames = "android.permission.READ_EXTERNAL_STORAGE,android.permission.WRITE_EXTERNAL_STORAGE")
public class MakeroidShell extends AndroidNonvisibleComponent {
    private Context context;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidShell(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            java.lang.String r2 = "Shell"
            java.lang.String r3 = "Shell component created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidShell.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Returns true if Phone is Rooted")
    public boolean IsRooted() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            return true;
        } catch (PermissionException e) {
            PermissionException permissionException = e;
            this.form.dispatchPermissionDeniedEvent((Component) this, "IsRooted", permissionException);
            int e2 = Log.e("Shell", "Permission exception executing su.", permissionException);
            return false;
        } catch (Exception e3) {
            int e4 = Log.e("Shell", "Exception executing su.", e3);
            return false;
        }
    }

    @SimpleFunction(description = "This returns TRUE if the system user is running the application, and could be a sign of a rooted device. Developed by Cian.")
    public boolean isSystemUser() {
        if (Build.VERSION.SDK_INT >= 23) {
            return ((UserManager) this.context.getSystemService("user")).isSystemUser();
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0019  */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Returns TRUE if one of 6 known root packages or varients is installed. The name of the package is not returned, so the user does not know which package name to change. Developed by Cian.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean CheckForSuperUserAPK() {
        /*
            r6 = this;
            r0 = r6
            r3 = r0
            android.content.Context r3 = r3.context
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            r4 = 0
            java.util.List r3 = r3.getInstalledApplications(r4)
            java.util.Iterator r3 = r3.iterator()
            r1 = r3
        L_0x0012:
            r3 = r1
            boolean r3 = r3.hasNext()
            if (r3 == 0) goto L_0x006f
            r3 = r1
            java.lang.Object r3 = r3.next()
            android.content.pm.ApplicationInfo r3 = (android.content.pm.ApplicationInfo) r3
            r5 = r3
            r3 = r5
            r4 = r5
            r2 = r4
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".noshufou"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x006b
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".yellowes.su"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x006b
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".chainfire.supersu"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x006b
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".koushikdutta.superuser"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x006b
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".thirdparty.superuser"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x006b
            r3 = r2
            java.lang.String r3 = r3.packageName
            java.lang.String r4 = ".chainfire.supersu"
            boolean r3 = r3.contains(r4)
            if (r3 == 0) goto L_0x006e
        L_0x006b:
            r3 = 1
            r0 = r3
        L_0x006d:
            return r0
        L_0x006e:
            goto L_0x0012
        L_0x006f:
            r3 = 0
            r0 = r3
            goto L_0x006d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidShell.CheckForSuperUserAPK():boolean");
    }

    @SimpleFunction(description = "Executes shell commands. To get output, use \"GotOutput\" event block.")
    public void Execute(String str) {
        PermissionResultHandler permissionResultHandler;
        final String str2 = str;
        new PermissionResultHandler(this) {
            private /* synthetic */ MakeroidShell hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                StringBuilder sb;
                BufferedReader bufferedReader;
                Reader reader;
                String str2 = str;
                if (z) {
                    new StringBuilder();
                    StringBuilder sb2 = sb;
                    try {
                        Process exec = Runtime.getRuntime().exec(str2);
                        new InputStreamReader(exec.getInputStream());
                        new BufferedReader(reader);
                        BufferedReader bufferedReader2 = bufferedReader;
                        int waitFor = exec.waitFor();
                        while (true) {
                            String readLine = bufferedReader2.readLine();
                            String str3 = readLine;
                            if (readLine != null) {
                                StringBuilder append = sb2.append(str3).append("\n");
                            } else {
                                bufferedReader2.close();
                                exec.destroy();
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotOutput(sb2.toString());
                                return;
                            }
                        }
                    } catch (PermissionException e) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Execute", e);
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotOutput(sb2.toString());
                    } catch (Exception e2) {
                        int e3 = Log.e("Shell", String.valueOf(e2));
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotOutput(sb2.toString());
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotOutput(sb2.toString());
                        throw th2;
                    }
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Execute", str2);
                }
            }
        };
        this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", permissionResultHandler);
    }

    @SimpleEvent(description = "Read output after executing shell command")
    public void GotOutput(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotOutput", str);
    }
}
