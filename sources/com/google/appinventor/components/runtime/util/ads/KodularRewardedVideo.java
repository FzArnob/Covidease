package com.google.appinventor.components.runtime.util.ads;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.runtime.util.KodularAdsUtil;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;

public class KodularRewardedVideo {
    private String GqEao9b9YWjqJfm0U8G9R1Onjg5BiUMTCoMqRIVO602C1plqYKUzgjm5B5hvlTms = "unity";
    private Context context;
    private OnAdsSwitcherListener hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private C1198a f581hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */
    public boolean wfWoJD9s05zGciiq11jj7TXqW1AmJIE9BV68DWBBkLZSv9NayRoTG3XJb9WJMlyD = false;

    public interface OnAdsSwitcherListener {
        void onAdsClick();

        void onAdsClosed();

        void onAdsCompleted();

        void onAdsError(String str);

        void onAdsReady();
    }

    public KodularRewardedVideo(Context context2) {
        C1198a aVar;
        this.context = context2;
        int hashCode = this.GqEao9b9YWjqJfm0U8G9R1Onjg5BiUMTCoMqRIVO602C1plqYKUzgjm5B5hvlTms.hashCode();
        new C1198a(this, (byte) 0);
        this.f581hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = aVar;
    }

    public void setOnAdsSwitcherListener(OnAdsSwitcherListener onAdsSwitcherListener) {
        OnAdsSwitcherListener onAdsSwitcherListener2 = onAdsSwitcherListener;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = onAdsSwitcherListener2;
    }

    public void updateNetwork(String str) {
        String str2 = str;
        this.GqEao9b9YWjqJfm0U8G9R1Onjg5BiUMTCoMqRIVO602C1plqYKUzgjm5B5hvlTms = str2;
    }

    public void loadAd(boolean z) {
        int hashCode = this.GqEao9b9YWjqJfm0U8G9R1Onjg5BiUMTCoMqRIVO602C1plqYKUzgjm5B5hvlTms.hashCode();
        UnityAds.initialize((Activity) this.context, KodularAdsUtil.UNITY_ADS_GAME_ID, this.f581hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, z);
        this.wfWoJD9s05zGciiq11jj7TXqW1AmJIE9BV68DWBBkLZSv9NayRoTG3XJb9WJMlyD = true;
    }

    public boolean showAd() {
        int hashCode = this.GqEao9b9YWjqJfm0U8G9R1Onjg5BiUMTCoMqRIVO602C1plqYKUzgjm5B5hvlTms.hashCode();
        if (!UnityAds.isReady()) {
            return false;
        }
        UnityAds.show((Activity) this.context);
        return true;
    }

    /* renamed from: com.google.appinventor.components.runtime.util.ads.KodularRewardedVideo$a */
    class C1198a implements IUnityAdsExtendedListener {
        private /* synthetic */ KodularRewardedVideo B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        private C1198a(KodularRewardedVideo kodularRewardedVideo) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = kodularRewardedVideo;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1198a(KodularRewardedVideo kodularRewardedVideo, byte b) {
            this(kodularRewardedVideo);
            byte b2 = b;
        }

        public final void onUnityAdsClick(String str) {
            String str2 = str;
            if (KodularRewardedVideo.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T) != null) {
                KodularRewardedVideo.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).onAdsClick();
            }
        }

        public final void onUnityAdsPlacementStateChanged(String str, UnityAds.PlacementState placementState, UnityAds.PlacementState placementState2) {
        }

        public final void onUnityAdsReady(String str) {
            String str2 = str;
            if (KodularRewardedVideo.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T) != null && KodularRewardedVideo.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T)) {
                boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T2 = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.wfWoJD9s05zGciiq11jj7TXqW1AmJIE9BV68DWBBkLZSv9NayRoTG3XJb9WJMlyD = false;
                KodularRewardedVideo.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).onAdsReady();
            }
        }

        public final void onUnityAdsStart(String str) {
        }

        public final void onUnityAdsFinish(String str, UnityAds.FinishState finishState) {
            String str2 = str;
            UnityAds.FinishState finishState2 = finishState;
            if (KodularRewardedVideo.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T) != null) {
                if (finishState2 == UnityAds.FinishState.COMPLETED) {
                    KodularRewardedVideo.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).onAdsCompleted();
                }
                KodularRewardedVideo.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).onAdsClosed();
            }
        }

        public final void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String str) {
            UnityAds.UnityAdsError unityAdsError2 = unityAdsError;
            String str2 = str;
            if (KodularRewardedVideo.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T) != null) {
                KodularRewardedVideo.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).onAdsError(str2);
            }
        }
    }
}
