package android.support.transition;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransitionValues {
    final ArrayList<Transition> mTargetedTransitions;
    public final Map<String, Object> values;
    public View view;

    public TransitionValues() {
        Map<String, Object> map;
        ArrayList<Transition> arrayList;
        new HashMap();
        this.values = map;
        new ArrayList<>();
        this.mTargetedTransitions = arrayList;
    }

    public boolean equals(Object obj) {
        Object other = obj;
        if (!(other instanceof TransitionValues) || this.view != ((TransitionValues) other).view || !this.values.equals(((TransitionValues) other).values)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (31 * this.view.hashCode()) + this.values.hashCode();
    }

    public String toString() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        new StringBuilder();
        String returnValue = sb.append("TransitionValues@").append(Integer.toHexString(hashCode())).append(":\n").toString();
        new StringBuilder();
        String returnValue2 = sb2.append(returnValue).append("    view = ").append(this.view).append("\n").toString();
        new StringBuilder();
        String returnValue3 = sb3.append(returnValue2).append("    values:").toString();
        for (String s : this.values.keySet()) {
            new StringBuilder();
            returnValue3 = sb4.append(returnValue3).append("    ").append(s).append(": ").append(this.values.get(s)).append("\n").toString();
        }
        return returnValue3;
    }
}
