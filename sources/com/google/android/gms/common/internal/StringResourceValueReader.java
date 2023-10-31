package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.C0554R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.appinventor.components.common.PropertyTypeConstants;
import javax.annotation.Nullable;

@KeepForSdk
public class StringResourceValueReader {
    private final Resources zzeu;
    private final String zzev = this.zzeu.getResourcePackageName(C0554R.string.common_google_play_services_unknown_issue);

    public StringResourceValueReader(Context context) {
        Context context2 = context;
        Object checkNotNull = Preconditions.checkNotNull(context2);
        this.zzeu = context2.getResources();
    }

    @KeepForSdk
    @Nullable
    public String getString(String str) {
        int identifier = this.zzeu.getIdentifier(str, PropertyTypeConstants.PROPERTY_TYPE_STRING, this.zzev);
        int i = identifier;
        if (identifier == 0) {
            return null;
        }
        return this.zzeu.getString(i);
    }
}
