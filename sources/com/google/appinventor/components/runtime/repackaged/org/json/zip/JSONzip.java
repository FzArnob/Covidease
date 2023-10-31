package com.google.appinventor.components.runtime.repackaged.org.json.zip;

import android.support.p000v4.view.InputDeviceCompat;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public abstract class JSONzip implements None, PostMortem {
    public static final byte[] bcd = {Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_16, Ev3Constants.Opcode.MOVE8_32, Ev3Constants.Opcode.MOVE8_F, Ev3Constants.Opcode.MOVE16_8, Ev3Constants.Opcode.MOVE16_16, Ev3Constants.Opcode.MOVE16_32, Ev3Constants.Opcode.MOVE16_F, Ev3Constants.Opcode.MOVE32_8, Ev3Constants.Opcode.MOVE32_16, Ev3Constants.Opcode.RL32, Ev3Constants.Opcode.RL16, 43, Ev3Constants.Opcode.CP_LT16};
    public static final int end = 256;
    public static final int endOfNumber = bcd.length;
    public static final long int14 = 16384;
    public static final long int4 = 16;
    public static final long int7 = 128;
    public static final int maxSubstringLength = 10;
    public static final int minSubstringLength = 3;
    public static final boolean probe = false;
    public static final int substringLimit = 40;
    public static final int[] twos = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536};
    public static final int zipArrayString = 6;
    public static final int zipArrayValue = 7;
    public static final int zipEmptyArray = 1;
    public static final int zipEmptyObject = 0;
    public static final int zipFalse = 3;
    public static final int zipNull = 4;
    public static final int zipObject = 5;
    public static final int zipTrue = 2;
    protected final Huff namehuff;
    protected final MapKeep namekeep;
    protected final MapKeep stringkeep;
    protected final Huff substringhuff;
    protected final TrieKeep substringkeep;
    protected final MapKeep values;

    protected JSONzip() {
        Huff huff;
        MapKeep mapKeep;
        MapKeep mapKeep2;
        Huff huff2;
        TrieKeep trieKeep;
        MapKeep mapKeep3;
        new Huff(InputDeviceCompat.SOURCE_KEYBOARD);
        this.namehuff = huff;
        new MapKeep(9);
        this.namekeep = mapKeep;
        new MapKeep(11);
        this.stringkeep = mapKeep2;
        new Huff(InputDeviceCompat.SOURCE_KEYBOARD);
        this.substringhuff = huff2;
        new TrieKeep(12);
        this.substringkeep = trieKeep;
        new MapKeep(10);
        this.values = mapKeep3;
        this.namehuff.tick(32, 125);
        this.namehuff.tick(97, 122);
        this.namehuff.tick(256);
        this.namehuff.tick(256);
        this.substringhuff.tick(32, 125);
        this.substringhuff.tick(97, 122);
        this.substringhuff.tick(256);
        this.substringhuff.tick(256);
    }

    /* access modifiers changed from: protected */
    public void begin() {
        this.namehuff.generate();
        this.substringhuff.generate();
    }

    static void log() {
        log("\n");
    }

    static void log(int integer) {
        StringBuffer stringBuffer;
        new StringBuffer();
        log(stringBuffer.append(integer).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).toString());
    }

    static void log(int integer, int width) {
        StringBuffer stringBuffer;
        new StringBuffer();
        log(stringBuffer.append(integer).append(":").append(width).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).toString());
    }

    static void log(String string) {
        System.out.print(string);
    }

    static void logchar(int i, int i2) {
        StringBuffer stringBuffer;
        int integer = i;
        int width = i2;
        if (integer <= 32 || integer > 125) {
            log(integer, width);
            return;
        }
        new StringBuffer();
        log(stringBuffer.append("'").append((char) integer).append("':").append(width).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).toString());
    }

    public boolean postMortem(PostMortem pm) {
        JSONzip that = (JSONzip) pm;
        return this.namehuff.postMortem((PostMortem) that.namehuff) && this.namekeep.postMortem(that.namekeep) && this.stringkeep.postMortem(that.stringkeep) && this.substringhuff.postMortem((PostMortem) that.substringhuff) && this.substringkeep.postMortem(that.substringkeep) && this.values.postMortem(that.values);
    }
}
