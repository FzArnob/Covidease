package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

public final class SignInButtonCreator extends RemoteCreator<ISignInButtonCreator> {
    private static final SignInButtonCreator zapf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private SignInButtonCreator() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View createView(Context context, int i, int i2) throws RemoteCreator.RemoteCreatorException {
        return zapf.zaa(context, i, i2);
    }

    private final View zaa(Context context, int i, int i2) throws RemoteCreator.RemoteCreatorException {
        Throwable th;
        StringBuilder sb;
        SignInButtonConfig signInButtonConfig;
        Context context2 = context;
        int i3 = i;
        int i4 = i2;
        try {
            new SignInButtonConfig(i3, i4, (Scope[]) null);
            SignInButtonConfig signInButtonConfig2 = signInButtonConfig;
            return (View) ObjectWrapper.unwrap(((ISignInButtonCreator) getRemoteCreatorInstance(context2)).newSignInButtonFromConfig(ObjectWrapper.wrap(context2), signInButtonConfig2));
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new StringBuilder(64);
            new RemoteCreator.RemoteCreatorException(sb.append("Could not get button with size ").append(i3).append(" and color ").append(i4).toString(), exc);
            throw th2;
        }
    }

    public final ISignInButtonCreator getRemoteCreator(IBinder iBinder) {
        ISignInButtonCreator iSignInButtonCreator;
        IBinder iBinder2 = iBinder;
        IBinder iBinder3 = iBinder2;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof ISignInButtonCreator) {
            return (ISignInButtonCreator) iInterface;
        }
        new zah(iBinder3);
        return iSignInButtonCreator;
    }

    static {
        SignInButtonCreator signInButtonCreator;
        new SignInButtonCreator();
        zapf = signInButtonCreator;
    }
}
