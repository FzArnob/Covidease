package android.arch.lifecycle;

import android.support.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MethodCallsLogger {
    private Map<String, Integer> mCalledMethods;

    public MethodCallsLogger() {
        Map<String, Integer> map;
        new HashMap();
        this.mCalledMethods = map;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean approveCall(String str, int i) {
        String name = str;
        int type = i;
        Integer nullableMask = this.mCalledMethods.get(name);
        int mask = nullableMask != null ? nullableMask.intValue() : 0;
        boolean wasCalled = (mask & type) != 0;
        Integer put = this.mCalledMethods.put(name, Integer.valueOf(mask | type));
        return !wasCalled;
    }
}
