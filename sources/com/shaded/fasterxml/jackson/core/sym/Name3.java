package com.shaded.fasterxml.jackson.core.sym;

public final class Name3 extends Name {
    final int mQuad1;
    final int mQuad2;
    final int mQuad3;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Name3(String str, int i, int i2, int i3, int i4) {
        super(str, i);
        this.mQuad1 = i2;
        this.mQuad2 = i3;
        this.mQuad3 = i4;
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
        return i == 3 && iArr2[0] == this.mQuad1 && iArr2[1] == this.mQuad2 && iArr2[2] == this.mQuad3;
    }
}
