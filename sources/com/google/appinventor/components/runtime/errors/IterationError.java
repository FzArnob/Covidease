package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.runtime.util.ErrorMessages;

public class IterationError extends DispatchableError {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IterationError(int i, Object[] objArr) {
        super(i, objArr);
    }

    public int getIndex() {
        return ((Integer) getArguments()[0]).intValue();
    }

    public String getExpected() {
        return (String) getArguments()[1];
    }

    public String getFound() {
        return (String) getArguments()[2];
    }

    public static DispatchableError fromError(int i, DispatchableError dispatchableError) {
        DispatchableError dispatchableError2;
        DispatchableError dispatchableError3;
        DispatchableError dispatchableError4;
        int i2 = i;
        DispatchableError dispatchableError5 = dispatchableError;
        switch (dispatchableError5.getErrorCode()) {
            case ErrorMessages.ERROR_INVALID_POINT /*3405*/:
                new IterationError(ErrorMessages.ERROR_INVALID_POINT_AT_INDEX, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i2, dispatchableError5.getArguments()));
                return dispatchableError4;
            case ErrorMessages.ERROR_INVALID_NUMBER_OF_VALUES_IN_POINT /*3409*/:
                new IterationError(ErrorMessages.ERROR_INVALID_NUMBER_OF_VALUES_IN_POINT_AT_INDEX, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i2, dispatchableError5.getArguments()));
                return dispatchableError2;
            case ErrorMessages.ERROR_INVALID_TYPE /*3410*/:
                new IterationError(ErrorMessages.ERROR_INVALID_TYPE_AT_INDEX, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i2, dispatchableError5.getArguments()));
                return dispatchableError3;
            default:
                return dispatchableError5;
        }
    }

    private static Object[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i, Object[] objArr) {
        Object[] objArr2 = objArr;
        Object[] objArr3 = new Object[(objArr2.length + 1)];
        Object[] objArr4 = objArr3;
        objArr3[0] = Integer.valueOf(i);
        System.arraycopy(objArr2, 0, objArr4, 1, objArr2.length);
        return objArr4;
    }
}
