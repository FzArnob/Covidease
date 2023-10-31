package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.repackaged.org.json.HTTP;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;

@DesignerComponent(category = ComponentCategory.STORAGE, description = "Non-visible component for storing and retrieving files. Use this component to write or read files on your device. The default behaviour is to write files to the private data directory associated with your App. The Companion is special cased to write files to /sdcard/Makeroid/data to facilitate debugging. If the file path starts with a slash (/), then the file is created relative to /sdcard. For example writing a file to /myFile.txt will write the file in /sdcard/myFile.txt.", iconName = "images/file.png", nonVisible = true, version = 5)
@UsesLibraries(libraries = "zip4j.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.WRITE_EXTERNAL_STORAGE, android.permission.READ_EXTERNAL_STORAGE")
public class File extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "FileComponent";
    public static final String NO_ASSETS = "No_Assets";
    private final int BUFFER_LENGTH = 4096;
    /* access modifiers changed from: private */
    public final Activity activity;
    /* access modifiers changed from: private */
    public boolean isCompanion = false;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public File(com.google.appinventor.components.runtime.ComponentContainer r5) {
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
            r3 = 4096(0x1000, float:5.74E-42)
            r2.BUFFER_LENGTH = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            boolean r2 = r2 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r2 == 0) goto L_0x001f
            r2 = r0
            r3 = 1
            r2.isCompanion = r3
        L_0x001f:
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.File.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Saves text to a file. If the filename begins with a slash (/) the file is written to the sdcard. For example writing to /myFile.txt will write the file to /sdcard/myFile.txt. If the filename does not start with a slash, it will be written in the programs private data directory where it will not be accessible to other programs on the phone. There is a special exception for the AI Companion where these files are written to /sdcard/AppInventor/data to facilitate debugging. Note that this block will overwrite a file if it already exists.\n\nIf you want to add content to a file use the append block.")
    public void SaveFile(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (str4.startsWith("/")) {
            FileUtil.checkExternalStorageWriteable();
        }
        Write(str4, str3, false);
    }

    @SimpleFunction(description = "Appends text to the end of a file storage, creating the file if it does not exist. See the help text under SaveFile for information about where files are written.")
    public void AppendToFile(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (str4.startsWith("/")) {
            FileUtil.checkExternalStorageWriteable();
        }
        Write(str4, str3, true);
    }

    @SimpleFunction(description = "Reads text from a file in storage. Prefix the filename with / to read from a specific file on the SD card. for instance /myFile.txt will read the file /sdcard/myFile.txt. To read assets packaged with an application (also works for the Companion) start the filename with // (two slashes). If a filename does not start with a slash, it will be read from the applications private storage (for packaged apps) and from /sdcard/AppInventor/data for the Companion.")
    public void ReadFrom(String str) {
        PermissionResultHandler permissionResultHandler;
        final String str2 = str;
        new PermissionResultHandler(this) {
            final /* synthetic */ File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                InputStream openFile;
                Runnable runnable;
                String str2 = str;
                if (z) {
                    try {
                        if (str2.startsWith("//")) {
                            openFile = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.openAsset(str2.substring(2));
                        } else {
                            String access$000 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AbsoluteFileName(str2);
                            int d = Log.d(File.LOG_TAG, "filepath = ".concat(String.valueOf(access$000)));
                            openFile = FileUtil.openFile(access$000);
                        }
                        final InputStream inputStream = openFile;
                        new Runnable(this) {
                            private /* synthetic */ C06511 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                            {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                            }

                            public final void run() {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AsyncRead(inputStream, str2);
                            }
                        };
                        AsynchUtil.runAsynchronously(runnable);
                    } catch (PermissionException e) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "ReadFrom", e);
                    } catch (FileNotFoundException e2) {
                        int e3 = Log.e(File.LOG_TAG, "FileNotFoundException", e2);
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "ReadFrom", ErrorMessages.ERROR_CANNOT_FIND_FILE, str2);
                    } catch (Exception e4) {
                        int e5 = Log.e(File.LOG_TAG, "IOException", e4);
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "ReadFrom", ErrorMessages.ERROR_CANNOT_FIND_FILE, str2);
                    }
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "ReadFrom", str2);
                }
            }
        };
        this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", permissionResultHandler);
    }

    @SimpleFunction(description = "Deletes a file from storage. Prefix the filename with / to delete a specific file in the SD card, for instance /myFile.txt. will delete the file /sdcard/myFile.txt. If the file does not begin with a /, then the file located in the programs private storage will be deleted. Starting the file with // is an error because assets files cannot be deleted.")
    public void Delete(String str) {
        PermissionResultHandler permissionResultHandler;
        final String str2 = str;
        new PermissionResultHandler(this) {
            private /* synthetic */ File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                java.io.File file;
                PermissionException permissionException;
                String str2 = str;
                if (!z) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Delete", str2);
                } else if (str2.startsWith("//")) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "DeleteFile", ErrorMessages.ERROR_CANNOT_DELETE_ASSET, str2);
                } else {
                    String access$000 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AbsoluteFileName(str2);
                    if (MediaUtil.isExternalFile(str2) && this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.isDeniedPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
                        new PermissionException("android.permission.WRITE_EXTERNAL_STORAGE");
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Delete", permissionException);
                    }
                    new java.io.File(access$000);
                    boolean delete = file.delete();
                }
            }
        };
        this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", permissionResultHandler);
    }

    @SimpleEvent(description = "Event indicating that the contents from the file have been read.")
    public void GotText(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotText", str);
    }

    @SimpleEvent(description = "Event indicating that the contents of the file have been written.")
    public void AfterFileSaved(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterFileSaved", str);
    }

    @SimpleFunction(description = "Move a file. You can not move asset files.")
    public boolean Move(String str, String str2) {
        return getFile(str).renameTo(getFile(str2));
    }

    @SimpleFunction(description = "Copy a file. If input path started with two // (slashes) then it's a asset file. You can not copy a file into the assets directory.")
    public void Copy(String str, String str2) {
        PermissionResultHandler permissionResultHandler;
        final String str3 = str;
        final String str4 = str2;
        new PermissionResultHandler(this) {
            private /* synthetic */ File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                String str2 = str;
                if (z) {
                    try {
                        if (!str3.startsWith("//")) {
                            String copyFile = FileUtil.copyFile(str3, str4);
                        } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isCompanion) {
                            String copyFile2 = FileUtil.copyFile(str3, str4);
                        } else {
                            String writeStreamToFile = FileUtil.writeStreamToFile(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.getAssets().open(str3), str4);
                        }
                    } catch (IOException e) {
                        int e2 = Log.e(File.LOG_TAG, String.valueOf(e));
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Copy", ErrorMessages.ERROR_CANNOT_CREATE_FILE, str4);
                    }
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Copy", str2);
                }
            }
        };
        this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", permissionResultHandler);
    }

    @SimpleFunction(description = "Check whether a file exists. If file path started with two // (slashes) then it means you would check if a asset file exists.")
    public boolean Exists(String str) {
        String str2 = str;
        if (str2.startsWith("//")) {
            return assetsCheck(str2);
        }
        return getFile(str2).exists();
    }

    @SimpleFunction(description = "Get file size")
    public long FileSize(String str) {
        java.io.File file = getFile(str);
        java.io.File file2 = file;
        if (!file.exists() || !file2.isFile()) {
            return -1;
        }
        return file2.length();
    }

    @SimpleFunction(description = "Get total space")
    public long GetTotalSpace(String str) {
        return getFile(str).getTotalSpace();
    }

    @SimpleFunction(description = "Get Free Space")
    public long GetFreeSpace(String str) {
        return getFile(str).getFreeSpace();
    }

    @SimpleFunction(description = "Get file name")
    public String GetFileName(String str) {
        return getFile(str).getName();
    }

    @SimpleFunction(description = "Check whether the path is a file")
    public boolean IsFile(String str) {
        java.io.File file = getFile(str);
        return file.exists() && file.isFile();
    }

    @SimpleFunction(description = "Check whether the path is a directory")
    public boolean IsDirectory(String str) {
        java.io.File file = getFile(str);
        return file.exists() && file.isDirectory();
    }

    @SimpleEvent(description = "Event indicating that the zip file have been created.")
    public void AfterZip() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterZip", new Object[0]);
    }

    @SimpleEvent(description = "Event indicating that the zip file have been created.")
    public void AfterUnzip() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterUnzip", new Object[0]);
    }

    @SimpleEvent(description = "Event indicating that there was any failure on zip or unzip a file.")
    public void OnZipFailure(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnZipFailure", str);
    }

    @SimpleFunction(description = "Create a zip file with or without a password.")
    public void Zip(String str, String str2, String str3) {
        StringBuilder sb;
        ZipParameters zipParameters;
        ZipFile zipFile;
        java.io.File file;
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        try {
            new ZipParameters();
            ZipParameters zipParameters2 = zipParameters;
            ZipParameters zipParameters3 = zipParameters2;
            zipParameters2.setCompressionMethod(8);
            zipParameters3.setCompressionLevel(5);
            if (str6.length() > 0) {
                zipParameters3.setEncryptFiles(true);
                zipParameters3.setEncryptionMethod(99);
                zipParameters3.setAesKeyStrength(3);
                zipParameters3.setPassword(str6);
            }
            new ZipFile(str5);
            ZipFile zipFile2 = zipFile;
            new java.io.File(str4);
            java.io.File file2 = file;
            java.io.File file3 = file2;
            if (file2.isFile()) {
                zipFile2.addFile(file3, zipParameters3);
            } else if (file3.isDirectory()) {
                zipFile2.addFolder(file3, zipParameters3);
            }
            AfterZip();
        } catch (Exception e) {
            Exception exc = e;
            int e2 = Log.e(LOG_TAG, String.valueOf(exc));
            new StringBuilder();
            OnZipFailure(sb.append(exc.getMessage()).toString());
        }
    }

    @SimpleFunction(description = "Unzip a file with or without a password. If you dont need a passwort then let it empty.")
    public void Unzip(String str, String str2, String str3) {
        StringBuilder sb;
        ZipFile zipFile;
        String str4 = str2;
        String str5 = str3;
        try {
            new ZipFile(str);
            ZipFile zipFile2 = zipFile;
            ZipFile zipFile3 = zipFile2;
            if (zipFile2.isEncrypted()) {
                zipFile3.setPassword(str5);
            }
            zipFile3.extractAll(str4);
            AfterUnzip();
        } catch (Exception e) {
            Exception exc = e;
            int e2 = Log.e(LOG_TAG, String.valueOf(exc));
            new StringBuilder();
            OnZipFailure(sb.append(exc.getMessage()).toString());
        }
    }

    @SimpleFunction(description = "Create a new directory.")
    public void CreateDirectory(String str) {
        PermissionResultHandler permissionResultHandler;
        final String str2 = str;
        new PermissionResultHandler(this) {
            private /* synthetic */ File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                java.io.File file;
                StringBuilder sb;
                String str2 = str;
                if (z) {
                    try {
                        new StringBuilder();
                        new java.io.File(sb.append(Environment.getExternalStorageDirectory().getPath()).append(str2).toString());
                        java.io.File file2 = file;
                        java.io.File file3 = file2;
                        if (!file2.exists()) {
                            boolean mkdirs = file3.mkdirs();
                        }
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.DirectoryCreated(true);
                    } catch (PermissionException e) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "CreateDirectory", e);
                    } catch (Exception e2) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.DirectoryCreated(false);
                    }
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "CreateDirectory", str2);
                }
            }
        };
        this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", permissionResultHandler);
    }

    @SimpleEvent(description = "Event indicating that there was a directory created. The return value is 'true' or 'false'.")
    public void DirectoryCreated(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "DirectoryCreated", Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public String AbsoluteFileName(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        java.io.File file;
        StringBuilder sb3;
        String str2 = str;
        if (str2.startsWith("/")) {
            new StringBuilder();
            return sb3.append(Environment.getExternalStorageDirectory().getPath()).append(str2).toString();
        }
        java.io.File filesDir = this.activity.getFilesDir();
        if (this.isCompanion) {
            new StringBuilder();
            new java.io.File(sb2.append(Environment.getExternalStorageDirectory().getPath()).append("/Makeroid/data/").toString());
            java.io.File file2 = file;
            filesDir = file2;
            if (!file2.exists()) {
                boolean mkdirs = filesDir.mkdirs();
            }
        }
        new StringBuilder();
        return sb.append(filesDir.getPath()).append("/").append(str2).toString();
    }

    private void Write(String str, String str2, boolean z) {
        Runnable runnable;
        PermissionResultHandler permissionResultHandler;
        String str3 = str;
        String str4 = str2;
        boolean z2 = z;
        if (!str3.startsWith("//")) {
            final String str5 = str3;
            final boolean z3 = z2;
            final String str6 = str4;
            new Runnable(this) {
                final /* synthetic */ File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
                }

                /* JADX WARNING: type inference failed for: r4v25, types: [java.io.FileOutputStream] */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final void run() {
                    /*
                        r13 = this;
                        r0 = r13
                        r4 = r0
                        com.google.appinventor.components.runtime.File r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                        r5 = r0
                        java.lang.String r5 = r7
                        java.lang.String r4 = r4.AbsoluteFileName(r5)
                        r12 = r4
                        r4 = r12
                        r5 = r12
                        r1 = r5
                        boolean r4 = com.google.appinventor.components.runtime.util.MediaUtil.isExternalFile(r4)
                        if (r4 == 0) goto L_0x0020
                        r4 = r0
                        com.google.appinventor.components.runtime.File r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                        com.google.appinventor.components.runtime.Form r4 = r4.form
                        java.lang.String r5 = "android.permission.WRITE_EXTERNAL_STORAGE"
                        r4.assertPermission(r5)
                    L_0x0020:
                        java.io.File r4 = new java.io.File
                        r12 = r4
                        r4 = r12
                        r5 = r12
                        r6 = r1
                        r5.<init>(r6)
                        r12 = r4
                        r4 = r12
                        r5 = r12
                        r2 = r5
                        boolean r4 = r4.exists()
                        if (r4 != 0) goto L_0x0038
                        r4 = r2
                        boolean r4 = r4.createNewFile()     // Catch:{ IOException -> 0x0078 }
                    L_0x0038:
                        java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00b4 }
                        r12 = r4
                        r4 = r12
                        r5 = r12
                        r6 = r2
                        r7 = r0
                        boolean r7 = r8     // Catch:{ IOException -> 0x00b4 }
                        r5.<init>(r6, r7)     // Catch:{ IOException -> 0x00b4 }
                        r2 = r4
                        java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x00b4 }
                        r12 = r4
                        r4 = r12
                        r5 = r12
                        r6 = r2
                        r5.<init>(r6)     // Catch:{ IOException -> 0x00b4 }
                        r12 = r4
                        r4 = r12
                        r5 = r12
                        r3 = r5
                        r5 = r0
                        java.lang.String r5 = r9     // Catch:{ IOException -> 0x00b4 }
                        r4.write(r5)     // Catch:{ IOException -> 0x00b4 }
                        r4 = r3
                        r4.flush()     // Catch:{ IOException -> 0x00b4 }
                        r4 = r3
                        r4.close()     // Catch:{ IOException -> 0x00b4 }
                        r4 = r2
                        r4.close()     // Catch:{ IOException -> 0x00b4 }
                        r4 = r0
                        com.google.appinventor.components.runtime.File r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ IOException -> 0x00b4 }
                        android.app.Activity r4 = r4.activity     // Catch:{ IOException -> 0x00b4 }
                        com.google.appinventor.components.runtime.File$5$1 r5 = new com.google.appinventor.components.runtime.File$5$1     // Catch:{ IOException -> 0x00b4 }
                        r12 = r5
                        r5 = r12
                        r6 = r12
                        r7 = r0
                        r6.<init>(r7)     // Catch:{ IOException -> 0x00b4 }
                        r4.runOnUiThread(r5)     // Catch:{ IOException -> 0x00b4 }
                    L_0x0077:
                        return
                    L_0x0078:
                        r4 = move-exception
                        r4 = r0
                        boolean r4 = r8
                        if (r4 == 0) goto L_0x0099
                        r4 = r0
                        com.google.appinventor.components.runtime.File r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                        com.google.appinventor.components.runtime.Form r4 = r4.form
                        r5 = r0
                        com.google.appinventor.components.runtime.File r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                        java.lang.String r6 = "AppendTo"
                        r7 = 2103(0x837, float:2.947E-42)
                        r8 = 1
                        java.lang.Object[] r8 = new java.lang.Object[r8]
                        r12 = r8
                        r8 = r12
                        r9 = r12
                        r10 = 0
                        r11 = r1
                        r9[r10] = r11
                        r4.dispatchErrorOccurredEvent(r5, r6, r7, r8)
                        goto L_0x0077
                    L_0x0099:
                        r4 = r0
                        com.google.appinventor.components.runtime.File r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                        com.google.appinventor.components.runtime.Form r4 = r4.form
                        r5 = r0
                        com.google.appinventor.components.runtime.File r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                        java.lang.String r6 = "SaveFile"
                        r7 = 2103(0x837, float:2.947E-42)
                        r8 = 1
                        java.lang.Object[] r8 = new java.lang.Object[r8]
                        r12 = r8
                        r8 = r12
                        r9 = r12
                        r10 = 0
                        r11 = r1
                        r9[r10] = r11
                        r4.dispatchErrorOccurredEvent(r5, r6, r7, r8)
                        goto L_0x0077
                    L_0x00b4:
                        r4 = move-exception
                        r4 = r0
                        boolean r4 = r8
                        if (r4 == 0) goto L_0x00d5
                        r4 = r0
                        com.google.appinventor.components.runtime.File r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                        com.google.appinventor.components.runtime.Form r4 = r4.form
                        r5 = r0
                        com.google.appinventor.components.runtime.File r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                        java.lang.String r6 = "AppendTo"
                        r7 = 2104(0x838, float:2.948E-42)
                        r8 = 1
                        java.lang.Object[] r8 = new java.lang.Object[r8]
                        r12 = r8
                        r8 = r12
                        r9 = r12
                        r10 = 0
                        r11 = r1
                        r9[r10] = r11
                        r4.dispatchErrorOccurredEvent(r5, r6, r7, r8)
                        goto L_0x0077
                    L_0x00d5:
                        r4 = r0
                        com.google.appinventor.components.runtime.File r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                        com.google.appinventor.components.runtime.Form r4 = r4.form
                        r5 = r0
                        com.google.appinventor.components.runtime.File r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                        java.lang.String r6 = "SaveFile"
                        r7 = 2104(0x838, float:2.948E-42)
                        r8 = 1
                        java.lang.Object[] r8 = new java.lang.Object[r8]
                        r12 = r8
                        r8 = r12
                        r9 = r12
                        r10 = 0
                        r11 = r1
                        r9[r10] = r11
                        r4.dispatchErrorOccurredEvent(r5, r6, r7, r8)
                        goto L_0x0077
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.File.C06565.run():void");
                }
            };
            final Runnable runnable2 = runnable;
            final boolean z4 = z2;
            new PermissionResultHandler(this) {
                private /* synthetic */ File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void HandlePermissionResponse(String str, boolean z) {
                    String str2 = str;
                    if (z) {
                        AsynchUtil.runAsynchronously(runnable2);
                    } else {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, z4 ? "AppendToFile" : "SaveFile", str2);
                    }
                }
            };
            this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", permissionResultHandler);
        } else if (z2) {
            this.form.dispatchErrorOccurredEvent(this, "AppendTo", ErrorMessages.ERROR_CANNOT_WRITE_ASSET, str3);
        } else {
            this.form.dispatchErrorOccurredEvent(this, "SaveFile", ErrorMessages.ERROR_CANNOT_WRITE_ASSET, str3);
        }
    }

    private String normalizeNewLines(String str) {
        return str.replaceAll(HTTP.CRLF, "\n");
    }

    /* access modifiers changed from: private */
    public void AsyncRead(InputStream inputStream, String str) {
        InputStreamReader inputStreamReader;
        StringWriter stringWriter;
        Runnable runnable;
        String str2 = str;
        try {
            new InputStreamReader(inputStream);
            InputStreamReader inputStreamReader2 = inputStreamReader;
            new StringWriter();
            StringWriter stringWriter2 = stringWriter;
            char[] cArr = new char[4096];
            while (true) {
                int read = inputStreamReader2.read(cArr, 0, 4096);
                int i = read;
                if (read > 0) {
                    stringWriter2.write(cArr, 0, i);
                } else {
                    final String normalizeNewLines = normalizeNewLines(stringWriter2.toString());
                    new Runnable(this) {
                        private /* synthetic */ File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                        }

                        public final void run() {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotText(normalizeNewLines);
                        }
                    };
                    this.activity.runOnUiThread(runnable);
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            int e2 = Log.e(LOG_TAG, "FileNotFoundException", e);
            this.form.dispatchErrorOccurredEvent(this, "ReadFrom", ErrorMessages.ERROR_CANNOT_FIND_FILE, str2);
        } catch (Exception e3) {
            int e4 = Log.e(LOG_TAG, "Exception", e3);
            this.form.dispatchErrorOccurredEvent(this, "ReadFrom", ErrorMessages.ERROR_CANNOT_READ_FILE, str2);
        }
    }

    private java.io.File getFile(String str) {
        java.io.File file;
        new java.io.File(AbsoluteFileName(str));
        return file;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean assetsCheck(java.lang.String r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            boolean r2 = r2.isCompanion
            if (r2 == 0) goto L_0x0039
            java.io.File r2 = new java.io.File
            r6 = r2
            r2 = r6
            r3 = r6
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r6 = r4
            r4 = r6
            r5 = r6
            r5.<init>()
            java.io.File r5 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r5 = r5.getPath()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "/Makeroid/assets/"
            java.lang.StringBuilder r4 = r4.append(r5)
            r5 = r1
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            boolean r2 = r2.exists()
            r0 = r2
        L_0x0038:
            return r0
        L_0x0039:
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form     // Catch:{ IOException -> 0x0056, all -> 0x005a }
            android.content.res.Resources r2 = r2.getResources()     // Catch:{ IOException -> 0x0056, all -> 0x005a }
            android.content.res.AssetManager r2 = r2.getAssets()     // Catch:{ IOException -> 0x0056, all -> 0x005a }
            r3 = r1
            java.io.InputStream r2 = r2.open(r3)     // Catch:{ IOException -> 0x0056, all -> 0x005a }
            r1 = r2
            r2 = r1
            if (r2 == 0) goto L_0x0051
            r2 = r1
            r2.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0051:
            r2 = 1
            r0 = r2
            goto L_0x0038
        L_0x0054:
            r2 = move-exception
            goto L_0x0051
        L_0x0056:
            r2 = move-exception
            r2 = 0
            r0 = r2
            goto L_0x0038
        L_0x005a:
            r2 = move-exception
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.File.assetsCheck(java.lang.String):boolean");
    }
}
