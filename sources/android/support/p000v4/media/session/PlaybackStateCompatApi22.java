package android.support.p000v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.Iterator;
import java.util.List;

@RequiresApi(22)
/* renamed from: android.support.v4.media.session.PlaybackStateCompatApi22 */
class PlaybackStateCompatApi22 {
    public static Bundle getExtras(Object stateObj) {
        return ((PlaybackState) stateObj).getExtras();
    }

    public static Object newInstance(int state, long position, long bufferedPosition, float speed, long actions, CharSequence errorMessage, long updateTime, List<Object> customActions, long j, Bundle bundle) {
        PlaybackState.Builder builder;
        long activeItemId = j;
        Bundle extras = bundle;
        new PlaybackState.Builder();
        PlaybackState.Builder stateObj = builder;
        PlaybackState.Builder state2 = stateObj.setState(state, position, speed, updateTime);
        PlaybackState.Builder bufferedPosition2 = stateObj.setBufferedPosition(bufferedPosition);
        PlaybackState.Builder actions2 = stateObj.setActions(actions);
        PlaybackState.Builder errorMessage2 = stateObj.setErrorMessage(errorMessage);
        Iterator<Object> it = customActions.iterator();
        while (it.hasNext()) {
            PlaybackState.Builder addCustomAction = stateObj.addCustomAction((PlaybackState.CustomAction) it.next());
        }
        PlaybackState.Builder activeQueueItemId = stateObj.setActiveQueueItemId(activeItemId);
        PlaybackState.Builder extras2 = stateObj.setExtras(extras);
        return stateObj.build();
    }

    private PlaybackStateCompatApi22() {
    }
}
