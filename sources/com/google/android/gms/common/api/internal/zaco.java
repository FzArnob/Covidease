package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zap;

final class zaco extends zap {
    private final /* synthetic */ zacm zakw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zaco(zacm zacm, Looper looper) {
        super(looper);
        this.zakw = zacm;
    }

    public final void handleMessage(Message message) {
        String str;
        String str2;
        Status status;
        StringBuilder sb;
        Message message2 = message;
        switch (message2.what) {
            case 0:
                PendingResult pendingResult = (PendingResult) message2.obj;
                Object zaf = this.zakw.zado;
                Object obj = zaf;
                synchronized (zaf) {
                    if (pendingResult == null) {
                        try {
                            new Status(13, "Transform returned null");
                            this.zakw.zakp.zad(status);
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            Object obj2 = obj;
                            throw th2;
                        }
                    } else if (pendingResult instanceof zacd) {
                        this.zakw.zakp.zad(((zacd) pendingResult).getStatus());
                    } else {
                        this.zakw.zakp.zaa(pendingResult);
                    }
                    return;
                }
            case 1:
                RuntimeException runtimeException = (RuntimeException) message2.obj;
                String valueOf = String.valueOf(runtimeException.getMessage());
                String str3 = valueOf;
                if (valueOf.length() != 0) {
                    str2 = "Runtime exception on the transformation worker thread: ".concat(str3);
                } else {
                    str2 = str;
                    new String("Runtime exception on the transformation worker thread: ");
                }
                int e = Log.e("TransformedResultImpl", str2);
                throw runtimeException;
            default:
                int i = message2.what;
                new StringBuilder(70);
                int e2 = Log.e("TransformedResultImpl", sb.append("TransformationResultHandler received unknown message type: ").append(i).toString());
                return;
        }
    }
}
