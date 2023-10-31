package com.google.appinventor.components.runtime;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Firebase Storage", iconName = "images/firebaseDB.png", nonVisible = true, version = 2)
@UsesLibraries(libraries = "firebase-auth.jar, firebase-auth.aar,firebase-auth-interop.jar, firebase-auth-interop.aar,firebase-core.jar, firebase-core.aar,firebase-common.jar, firebase-common.aar,firebase-storage.jar, firebase-storage.aar,firebase-storage-common.jar, firebase-storage-common.aar,play-services-auth.jar, play-services-auth.aar,play-services-auth-api-phone.jar, play-services-auth-api-phone.aar,play-services-auth-base.jar, play-services-auth-base.aar,play-services-base.jar, play-services-base.aar,play-services-basement.jar, play-services-basement.aar,play-services-flags.jar, play-services-flags.aar,play-services-tasks.jar, play-services-tasks.aar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE, android.permission.WAKE_LOCK, com.google.android.c2dm.permission.RECEIVE")
public class KodularFirebaseStorage extends AndroidNonvisibleComponent {
    private Context context;
    private FirebaseStorage hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = FirebaseStorage.getInstance();

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private StorageReference f454hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularFirebaseStorage(com.google.appinventor.components.runtime.ComponentContainer r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            com.google.firebase.storage.FirebaseStorage r3 = com.google.firebase.storage.FirebaseStorage.getInstance()
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r4 = r2
            r2 = r4
            r3 = r4
            com.google.firebase.storage.FirebaseStorage r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.firebase.storage.StorageReference r3 = r3.getReference()
            r2.f454hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularFirebaseStorage.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Try to upload a file to Firebase Storage")
    public void UploadFile(String str, String str2) {
        StringBuilder sb;
        File file;
        InputStream inputStream;
        OnProgressListener onProgressListener;
        OnFailureListener onFailureListener;
        OnSuccessListener onSuccessListener;
        String str3 = str;
        String str4 = str2;
        try {
            new File(str3);
            File file2 = file;
            new FileInputStream(file2);
            InputStream inputStream2 = inputStream;
            StorageReference child = this.f454hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.child(str4);
            final String str5 = str3;
            final String str6 = str4;
            final File file3 = file2;
            new OnProgressListener<UploadTask.TaskSnapshot>(this) {
                private /* synthetic */ KodularFirebaseStorage hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
                }

                public final /* synthetic */ void onProgress(Object obj) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UploadProgress(str5, str6, ((UploadTask.TaskSnapshot) obj).getBytesTransferred(), file3.length());
                }
            };
            final String str7 = str3;
            new OnFailureListener(this) {
                private /* synthetic */ KodularFirebaseStorage hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void onFailure(@NonNull Exception exc) {
                    StringBuilder sb;
                    KodularFirebaseStorage kodularFirebaseStorage = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    String str = str7;
                    new StringBuilder();
                    kodularFirebaseStorage.UploadFailed(str, sb.append(exc.getMessage()).toString());
                }
            };
            final StorageReference storageReference = child;
            final String str8 = str3;
            final String str9 = str4;
            new OnSuccessListener<UploadTask.TaskSnapshot>(this) {
                final /* synthetic */ KodularFirebaseStorage hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
                }

                public final /* synthetic */ void onSuccess(Object obj) {
                    OnCompleteListener onCompleteListener;
                    Object obj2 = obj;
                    new OnCompleteListener<Uri>(this) {
                        private /* synthetic */ C07801 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void onComplete(@NonNull Task<Uri> task) {
                            Task<Uri> task2 = task;
                            if (task2.isSuccessful()) {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UploadSuccess(str8, str9, task2.getResult().toString());
                            } else {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UploadSuccess(str8, str9, "");
                            }
                        }
                    };
                    Task addOnCompleteListener = storageReference.getDownloadUrl().addOnCompleteListener(onCompleteListener);
                }
            };
            StorageTask addOnSuccessListener = child.putStream(inputStream2).addOnProgressListener(onProgressListener).addOnFailureListener(onFailureListener).addOnSuccessListener(onSuccessListener);
        } catch (FileNotFoundException e) {
            new StringBuilder();
            UploadFailed(str3, sb.append(e.getMessage()).toString());
        }
    }

    @SimpleFunction(description = "Try to download a file from Firebase Storage")
    public void DownloadFile(String str, String str2) {
        File file;
        File file2;
        File file3;
        OnSuccessListener onSuccessListener;
        OnFailureListener onFailureListener;
        String str3 = str;
        String str4 = str2;
        StorageReference child = this.f454hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.child(str3);
        new File(str4);
        File file4 = file;
        File file5 = file4;
        if (file4.exists() || file5.mkdirs()) {
            new File(str3);
            new File(str4, file3.getName());
            File file6 = file2;
            final String str5 = str3;
            final File file7 = file6;
            new OnSuccessListener<FileDownloadTask.TaskSnapshot>(this) {
                private /* synthetic */ KodularFirebaseStorage hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final /* synthetic */ void onSuccess(Object obj) {
                    Object obj2 = obj;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.DownloadSuccess(str5, file7.getAbsolutePath());
                }
            };
            final String str6 = str3;
            new OnFailureListener(this) {
                private /* synthetic */ KodularFirebaseStorage hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void onFailure(@NonNull Exception exc) {
                    StringBuilder sb;
                    KodularFirebaseStorage kodularFirebaseStorage = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    String str = str6;
                    new StringBuilder();
                    kodularFirebaseStorage.DownloadFailed(str, sb.append(exc.getMessage()).toString());
                }
            };
            StorageTask addOnFailureListener = child.getFile(file6).addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener);
            return;
        }
        DownloadFailed(str3, "Could not create a folder to store the download");
    }

    @SimpleFunction(description = "Try to delete a file from Firebase Storage")
    public void DeleteFile(String str) {
        OnSuccessListener onSuccessListener;
        OnFailureListener onFailureListener;
        String str2 = str;
        final String str3 = str2;
        new OnSuccessListener<Void>(this) {
            private /* synthetic */ KodularFirebaseStorage hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final /* synthetic */ void onSuccess(Object obj) {
                Object obj2 = obj;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.DeleteSuccess(str3);
            }
        };
        final String str4 = str2;
        new OnFailureListener(this) {
            private /* synthetic */ KodularFirebaseStorage hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onFailure(@NonNull Exception exc) {
                StringBuilder sb;
                KodularFirebaseStorage kodularFirebaseStorage = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                String str = str4;
                new StringBuilder();
                kodularFirebaseStorage.DeleteFailed(str, sb.append(exc.getMessage()).toString());
            }
        };
        Task addOnFailureListener = this.f454hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.child(str2).delete().addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener);
    }

    @SimpleFunction(description = "Pause all the active uploads")
    public void PauseUploads() {
        for (UploadTask pause : this.f454hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveUploadTasks()) {
            boolean pause2 = pause.pause();
        }
    }

    @SimpleFunction(description = "Resume all the active uploads")
    public void ResumeUploads() {
        for (UploadTask resume : this.f454hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveUploadTasks()) {
            boolean resume2 = resume.resume();
        }
    }

    @SimpleFunction(description = "Pause all the active downloads")
    public void PauseDownloads() {
        for (FileDownloadTask pause : this.f454hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveDownloadTasks()) {
            boolean pause2 = pause.pause();
        }
    }

    @SimpleFunction(description = "Resume all the active downloads")
    public void ResumeDownloads() {
        for (FileDownloadTask resume : this.f454hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveDownloadTasks()) {
            boolean resume2 = resume.resume();
        }
    }

    @SimpleFunction(description = "Get the name of the Storage Bucket")
    public String GetBucket() {
        return this.f454hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBucket();
    }

    @SimpleEvent(description = "Triggers when the file was successfully uploaded")
    public void UploadSuccess(String str, String str2, String str3) {
        Object[] objArr = new Object[3];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = str3;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UploadSuccess", objArr3);
    }

    @SimpleEvent(description = "Triggers when the file could not be uploaded")
    public void UploadFailed(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UploadFailed", objArr2);
    }

    @SimpleEvent(description = "Triggers when the file upload progress changed")
    public void UploadProgress(String str, String str2, long j, long j2) {
        Object[] objArr = new Object[4];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = Long.valueOf(j);
        Object[] objArr4 = objArr3;
        objArr4[3] = Long.valueOf(j2);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UploadProgress", objArr4);
    }

    @SimpleEvent(description = "Triggers when the file was successfully downloaded")
    public void DownloadSuccess(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "DownloadSuccess", objArr2);
    }

    @SimpleEvent(description = "Triggers when the file could not be downloaded")
    public void DownloadFailed(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "DownloadFailed", objArr2);
    }

    @SimpleEvent(description = "Triggers when the file was successfully deleted")
    public void DeleteSuccess(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "DeleteSuccess", str);
    }

    @SimpleEvent(description = "Triggers when the file could not be deleted")
    public void DeleteFailed(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "DeleteFailed", objArr2);
    }
}
