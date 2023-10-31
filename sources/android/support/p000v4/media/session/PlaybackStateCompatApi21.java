package android.support.p000v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.Iterator;
import java.util.List;

@RequiresApi(21)
/* renamed from: android.support.v4.media.session.PlaybackStateCompatApi21 */
class PlaybackStateCompatApi21 {
    public static int getState(Object stateObj) {
        return ((PlaybackState) stateObj).getState();
    }

    public static long getPosition(Object stateObj) {
        return ((PlaybackState) stateObj).getPosition();
    }

    public static long getBufferedPosition(Object stateObj) {
        return ((PlaybackState) stateObj).getBufferedPosition();
    }

    public static float getPlaybackSpeed(Object stateObj) {
        return ((PlaybackState) stateObj).getPlaybackSpeed();
    }

    public static long getActions(Object stateObj) {
        return ((PlaybackState) stateObj).getActions();
    }

    public static CharSequence getErrorMessage(Object stateObj) {
        return ((PlaybackState) stateObj).getErrorMessage();
    }

    public static long getLastPositionUpdateTime(Object stateObj) {
        return ((PlaybackState) stateObj).getLastPositionUpdateTime();
    }

    public static List<Object> getCustomActions(Object stateObj) {
        return ((PlaybackState) stateObj).getCustomActions();
    }

    public static long getActiveQueueItemId(Object stateObj) {
        return ((PlaybackState) stateObj).getActiveQueueItemId();
    }

    public static Object newInstance(int state, long position, long bufferedPosition, float speed, long actions, CharSequence errorMessage, long updateTime, List<Object> customActions, long j) {
        PlaybackState.Builder builder;
        long activeItemId = j;
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
        return stateObj.build();
    }

    /* renamed from: android.support.v4.media.session.PlaybackStateCompatApi21$CustomAction */
    static final class CustomAction {
        public static String getAction(Object customActionObj) {
            return ((PlaybackState.CustomAction) customActionObj).getAction();
        }

        public static CharSequence getName(Object customActionObj) {
            return ((PlaybackState.CustomAction) customActionObj).getName();
        }

        public static int getIcon(Object customActionObj) {
            return ((PlaybackState.CustomAction) customActionObj).getIcon();
        }

        public static Bundle getExtras(Object customActionObj) {
            return ((PlaybackState.CustomAction) customActionObj).getExtras();
        }

        public static Object newInstance(String action, CharSequence name, int icon, Bundle extras) {
            PlaybackState.CustomAction.Builder builder;
            new PlaybackState.CustomAction.Builder(action, name, icon);
            PlaybackState.CustomAction.Builder customActionObj = builder;
            PlaybackState.CustomAction.Builder extras2 = customActionObj.setExtras(extras);
            return customActionObj.build();
        }

        private CustomAction() {
        }
    }

    private PlaybackStateCompatApi21() {
    }
}
