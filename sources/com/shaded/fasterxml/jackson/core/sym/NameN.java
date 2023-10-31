package com.shaded.fasterxml.jackson.core.sym;

public final class NameN extends Name {
    final int mQuadLen;
    final int[] mQuads;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NameN(String str, int i, int[] iArr, int i2) {
        super(str, i);
        Throwable th;
        int[] iArr2 = iArr;
        int i3 = i2;
        if (i3 < 3) {
            Throwable th2 = th;
            new IllegalArgumentException("Qlen must >= 3");
            throw th2;
        }
        this.mQuads = iArr2;
        this.mQuadLen = i3;
    }

    public boolean equals(int i) {
        int i2 = i;
        return false;
    }

    public boolean equals(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        return false;
    }

    public boolean equals(int[] iArr, int i) {
        int[] iArr2 = iArr;
        int i2 = i;
        if (i2 != this.mQuadLen) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (iArr2[i3] != this.mQuads[i3]) {
                return false;
            }
        }
        return true;
    }
}
