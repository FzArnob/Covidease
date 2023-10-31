package com.google.appinventor.components.runtime;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/audio.png", nonVisible = true, version = 3)
@UsesPermissions(permissionNames = "android.permission.MODIFY_AUDIO_SETTINGS")
public class Audio extends AndroidNonvisibleComponent implements Component {
    private boolean KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = false;
    private ComponentContainer container;
    private Context context;
    private AudioManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Audio(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.content.Context r3 = r3.context
            java.lang.String r4 = "audio"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.media.AudioManager r3 = (android.media.AudioManager) r3
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            java.lang.String r2 = "Audio"
            java.lang.String r3 = "Audio Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Audio.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleEvent(description = "Event triggered when a error occurred.")
    public void ErrorOccurred(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ErrorOccurred", str);
    }

    @SimpleFunction(description = "Sets the ringer mode to \"vibrate\".")
    public void RingerModeVibrate() {
        StringBuilder sb;
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRingerMode(1);
        } catch (Exception e) {
            Exception exc = e;
            int e2 = Log.e("Audio", String.valueOf(exc));
            new StringBuilder();
            ErrorOccurred(sb.append(exc.getMessage()).toString());
        }
    }

    @SimpleFunction(description = "Sets the ringer mode to \"normal\".")
    public void RingerModeNormal() {
        StringBuilder sb;
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRingerMode(2);
        } catch (Exception e) {
            Exception exc = e;
            int e2 = Log.e("Audio", String.valueOf(exc));
            new StringBuilder();
            ErrorOccurred(sb.append(exc.getMessage()).toString());
        }
    }

    @SimpleFunction(description = "Sets the ringer mode to \"silent\".")
    public void RingerModeSilent() {
        StringBuilder sb;
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRingerMode(0);
        } catch (Exception e) {
            Exception exc = e;
            int e2 = Log.e("Audio", String.valueOf(exc));
            new StringBuilder();
            ErrorOccurred(sb.append(exc.getMessage()).toString());
        }
    }

    @SimpleProperty(description = "Returns the current volume index in percent.")
    public int VolumeRing() {
        int streamVolume = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamVolume(2);
        return (streamVolume * 100) / this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(2);
    }

    @SimpleProperty(description = "Sets the volume index for a particular stream in percent.")
    public void VolumeRing(int i) {
        int i2 = i;
        int i3 = 1;
        if (!this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH) {
            i3 = 0;
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStreamVolume(2, (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(2) * i2) / 100, i3);
    }

    @SimpleProperty(description = "Returns the current volume index in percent.")
    public int VolumeMusic() {
        int streamVolume = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamVolume(3);
        return (streamVolume * 100) / this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(3);
    }

    @SimpleProperty(description = "Sets the volume index for a particular stream in percent.")
    public void VolumeMusic(int i) {
        int i2 = i;
        int i3 = 1;
        if (!this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH) {
            i3 = 0;
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStreamVolume(3, (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(3) * i2) / 100, i3);
    }

    @SimpleProperty(description = "Returns the current volume index in percent.")
    public int VolumeAlarm() {
        int streamVolume = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamVolume(4);
        return (streamVolume * 100) / this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(4);
    }

    @SimpleProperty(description = "Sets the volume index for a particular stream in percent.")
    public void VolumeAlarm(int i) {
        int i2 = i;
        int i3 = 1;
        if (!this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH) {
            i3 = 0;
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStreamVolume(4, (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStreamMaxVolume(4) * i2) / 100, i3);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true you will see system ui.")
    public void ShowUI(boolean z) {
        boolean z2 = z;
        this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = z2;
    }

    @SimpleProperty
    public boolean ShowUI() {
        return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    }

    @SimpleProperty(description = "Returns the current audio mode as string. Possible returns are \"NORMAL\", \"RINGTONE\", \"CALL\" or \"COMMUNICATION\".")
    public String GetAudioMode() {
        String str = "Not found";
        switch (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMode()) {
            case 0:
                str = "NORMAL";
                break;
            case 1:
                str = "RINGTONE";
                break;
            case 2:
                str = "CALL";
                break;
            case 3:
                str = "COMMUNICATION";
                break;
        }
        return str;
    }

    @SimpleFunction(description = "Set whether a component should have sound effects enabled for events such as clicking and touching.")
    public void SoundEffectsEnabled(AndroidViewComponent androidViewComponent, boolean z) {
        androidViewComponent.getView().setSoundEffectsEnabled(z);
    }

    @SimpleFunction(description = "Returns true whether a component should have sound effects enabled for events such as clicking and touching.")
    public boolean IsSoundEffectsEnabled(AndroidViewComponent androidViewComponent) {
        return androidViewComponent.getView().isSoundEffectsEnabled();
    }
}
