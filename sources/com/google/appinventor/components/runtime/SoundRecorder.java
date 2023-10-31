package com.google.appinventor.components.runtime;

import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
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
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import java.io.File;
import java.io.IOException;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "<p>Multimedia component that records audio.</p>", iconName = "images/soundRecorder.png", nonVisible = true, version = 4)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.RECORD_AUDIO,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public final class SoundRecorder extends AndroidNonvisibleComponent implements MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener, Component {
    private String IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2 = "/Kodular/mySound.3gp";
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private C1022a hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: com.google.appinventor.components.runtime.SoundRecorder$a */
    class C1022a {
        final String BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE;
        final MediaRecorder hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        final /* synthetic */ SoundRecorder f525hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        C1022a(SoundRecorder soundRecorder, String str) throws IOException {
            MediaRecorder mediaRecorder;
            StringBuilder sb;
            SoundRecorder soundRecorder2 = soundRecorder;
            String str2 = str;
            this.f525hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = soundRecorder2;
            this.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE = str2.equals("") ? FileUtil.getRecordingFile("3gp").getAbsolutePath() : str2;
            new MediaRecorder();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = mediaRecorder;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAudioSource(1);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOutputFormat(1);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAudioEncoder(1);
            new StringBuilder("Setting output file to ");
            int i = Log.i("SoundRecorder", sb.append(this.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE).toString());
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOutputFile(this.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE);
            int i2 = Log.i("SoundRecorder", "preparing");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.prepare();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnErrorListener(soundRecorder2);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnInfoListener(soundRecorder2);
        }

        /* access modifiers changed from: package-private */
        public final void stop() {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnErrorListener((MediaRecorder.OnErrorListener) null);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnInfoListener((MediaRecorder.OnInfoListener) null);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.reset();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.release();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SoundRecorder(ComponentContainer componentContainer) {
        super(componentContainer.$form());
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Specifies the path to the file where the recording should be stored. If this property is the empty string, then starting a recording will create a file in an appropriate location.  If the property is not the empty string, it should specify a complete path to a file in an existing directory, including a file name with the extension .3gp.")
    public final String SavedRecording() {
        return this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2;
    }

    @DesignerProperty(defaultValue = "/Kodular/mySound.3gp", editorType = "string")
    @SimpleProperty
    public final void SavedRecording(String str) {
        String str2 = str;
        this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2 = str2;
    }

    @SimpleFunction(description = "Use this block to start the sound recorder.")
    public final void Start() {
        StringBuilder sb;
        File file;
        File file2;
        C1022a aVar;
        StringBuilder sb2;
        Throwable th;
        StringBuilder sb3;
        BulkPermissionRequest bulkPermissionRequest;
        BulkPermissionRequest bulkPermissionRequest2;
        if (!this.havePermission) {
            if (MediaUtil.isExternalFile(this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2)) {
                Form form = this.form;
                BulkPermissionRequest bulkPermissionRequest3 = bulkPermissionRequest2;
                String[] strArr = new String[2];
                strArr[0] = "android.permission.RECORD_AUDIO";
                String[] strArr2 = strArr;
                strArr2[1] = "android.permission.WRITE_EXTERNAL_STORAGE";
                new BulkPermissionRequest(this, this, "Start", strArr2) {
                    private /* synthetic */ SoundRecorder hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r10;
                    }

                    public final void onGranted() {
                        boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.havePermission = true;
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Start();
                    }
                };
                form.askPermission(bulkPermissionRequest3);
                return;
            }
            Form form2 = this.form;
            BulkPermissionRequest bulkPermissionRequest4 = bulkPermissionRequest;
            new BulkPermissionRequest(this, this, "Start", "android.permission.RECORD_AUDIO") {
                private /* synthetic */ SoundRecorder hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r10;
                }

                public final void onGranted() {
                    boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.havePermission = true;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Start();
                }
            };
            form2.askPermission(bulkPermissionRequest4);
        } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            new StringBuilder("Start() called, but already recording to ");
            int i = Log.i("SoundRecorder", sb3.append(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE).toString());
        } else {
            int i2 = Log.i("SoundRecorder", "Start() called");
            if (!MediaUtil.isExternalFile(this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2) || Environment.getExternalStorageState().equals("mounted")) {
                try {
                    new File(this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2);
                    new File(file.getParent());
                    File file3 = file2;
                    File file4 = file3;
                    if (!file3.exists()) {
                        boolean mkdir = file4.mkdir();
                    }
                    C1022a aVar2 = aVar;
                    new C1022a(this, this.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2);
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = aVar2;
                    try {
                        C1022a aVar3 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                        int i3 = Log.i("SoundRecorder", "starting");
                        aVar3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.start();
                        StartedRecording();
                    } catch (IllegalStateException e) {
                        int e2 = Log.e("SoundRecorder", "got IllegalStateException. Are there two recorders running?", e);
                        Throwable th2 = th;
                        new IllegalStateException("Is there another recording running?");
                        throw th2;
                    } catch (Throwable th3) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
                        new StringBuilder();
                        this.form.dispatchErrorOccurredEvent(this, "Start", ErrorMessages.ERROR_SOUND_RECORDER_CANNOT_CREATE, sb2.append(th3.getMessage()).toString());
                    }
                } catch (PermissionException e3) {
                    this.form.dispatchPermissionDeniedEvent((Component) this, "Start", e3);
                } catch (Throwable th4) {
                    new StringBuilder();
                    this.form.dispatchErrorOccurredEvent(this, "Start", ErrorMessages.ERROR_SOUND_RECORDER_CANNOT_CREATE, sb.append(th4.getMessage()).toString());
                }
            } else {
                this.form.dispatchErrorOccurredEvent(this, "Start", ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_NOT_AVAILABLE, new Object[0]);
            }
        }
    }

    public final void onError(MediaRecorder mediaRecorder, int i, int i2) {
        StringBuilder sb;
        MediaRecorder mediaRecorder2 = mediaRecorder;
        int i3 = i;
        int i4 = i2;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null || mediaRecorder2 != this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) {
            int w = Log.w("SoundRecorder", "onError called with wrong recorder. Ignoring.");
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, "onError", ErrorMessages.ERROR_SOUND_RECORDER, new Object[0]);
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            StoppedRecording();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            StoppedRecording();
            throw th2;
        }
    }

    public final void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
        MediaRecorder mediaRecorder2 = mediaRecorder;
        int i3 = i;
        int i4 = i2;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null || mediaRecorder2 != this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) {
            int w = Log.w("SoundRecorder", "onInfo called with wrong recorder. Ignoring.");
            return;
        }
        switch (i3) {
            case 1:
                this.form.dispatchErrorOccurredEvent(this, "recording", ErrorMessages.ERROR_SOUND_RECORDER, new Object[0]);
                break;
            case 800:
                this.form.dispatchErrorOccurredEvent(this, "recording", ErrorMessages.ERROR_SOUND_RECORDER_MAX_DURATION_REACHED, new Object[0]);
                break;
            case ErrorMessages.ERROR_SOUND_RECORDER /*801*/:
                this.form.dispatchErrorOccurredEvent(this, "recording", ErrorMessages.ERROR_SOUND_RECORDER_MAX_FILESIZE_REACHED, new Object[0]);
                break;
            default:
                return;
        }
        try {
            int i5 = Log.i("SoundRecorder", "Recoverable condition while recording. Will attempt to stop normally.");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            StoppedRecording();
        } catch (IllegalStateException e) {
            int i6 = Log.i("SoundRecorder", "SoundRecorder was not in a recording state.", e);
            this.form.dispatchErrorOccurredEventDialog(this, "Stop", ErrorMessages.ERROR_SOUND_RECORDER_ILLEGAL_STOP, new Object[0]);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            StoppedRecording();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            StoppedRecording();
            throw th2;
        }
    }

    @SimpleFunction(description = "Use this block to stop the sound recorder.")
    public final void Stop() {
        StringBuilder sb;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            int i = Log.i("SoundRecorder", "Stop() called, but already stopped.");
            return;
        }
        try {
            int i2 = Log.i("SoundRecorder", "Stop() called");
            int i3 = Log.i("SoundRecorder", "stopping");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stop();
            new StringBuilder("Firing AfterSoundRecorded with ");
            int i4 = Log.i("SoundRecorder", sb.append(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE).toString());
            AfterSoundRecorded(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            StoppedRecording();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            StoppedRecording();
            throw th2;
        }
    }

    @SimpleFunction(description = "Use this block to pause the sound recorder. Works only on Android 6 and above.")
    public final void Pause() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            C1022a aVar = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (Build.VERSION.SDK_INT >= 24) {
                aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pause();
                aVar.f525hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.PausedRecording();
            }
        }
    }

    @SimpleFunction(description = "Use this block to resume the sound recorder. Works only on Android 6 and above.")
    public final void Resume() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            C1022a aVar = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (Build.VERSION.SDK_INT >= 24) {
                aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.resume();
                aVar.f525hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ResumedRecording();
            }
        }
    }

    @SimpleEvent(description = "Provides the location of the newly created sound.")
    public final void AfterSoundRecorded(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterSoundRecorded", str);
    }

    @SimpleEvent(description = "Indicates that the recorder has started, and can be stopped.")
    public final void StartedRecording() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "StartedRecording", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that the recorder has stopped, and can be started again.")
    public final void StoppedRecording() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "StoppedRecording", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that the recorder has paused, and can be resumed again.")
    public final void PausedRecording() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "PausedRecording", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that the recorder has resumed, and can be started again.")
    public final void ResumedRecording() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ResumedRecording", new Object[0]);
    }
}
