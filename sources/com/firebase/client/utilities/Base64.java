package com.firebase.client.utilities;

import android.support.p000v4.view.InputDeviceCompat;
import android.support.p000v4.view.MotionEventCompat;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int DECODE = 0;
    public static final int DONT_GUNZIP = 4;
    public static final int DO_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    private static final byte EQUALS_SIGN = 61;
    private static final byte EQUALS_SIGN_ENC = -1;
    public static final int GZIP = 2;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = 10;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    private static final String PREFERRED_ENCODING = "US-ASCII";
    public static final int URL_SAFE = 16;
    private static final byte WHITE_SPACE_ENC = -5;
    private static final byte[] _ORDERED_ALPHABET = {Ev3Constants.Opcode.RL16, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_16, Ev3Constants.Opcode.MOVE8_32, Ev3Constants.Opcode.MOVE8_F, Ev3Constants.Opcode.MOVE16_8, Ev3Constants.Opcode.MOVE16_16, Ev3Constants.Opcode.MOVE16_32, Ev3Constants.Opcode.MOVE16_F, Ev3Constants.Opcode.MOVE32_8, Ev3Constants.Opcode.MOVE32_16, Ev3Constants.Opcode.JR_FALSE, Ev3Constants.Opcode.JR_TRUE, Ev3Constants.Opcode.JR_NAN, Ev3Constants.Opcode.CP_LT8, Ev3Constants.Opcode.CP_LT16, Ev3Constants.Opcode.CP_LT32, Ev3Constants.Opcode.CP_LTF, Ev3Constants.Opcode.CP_GT8, Ev3Constants.Opcode.CP_GT16, Ev3Constants.Opcode.CP_GT32, Ev3Constants.Opcode.CP_GTF, Ev3Constants.Opcode.CP_EQ8, Ev3Constants.Opcode.CP_EQ16, Ev3Constants.Opcode.CP_EQ32, Ev3Constants.Opcode.CP_EQF, Ev3Constants.Opcode.CP_NEQ8, Ev3Constants.Opcode.CP_NEQ16, Ev3Constants.Opcode.CP_NEQ32, Ev3Constants.Opcode.CP_NEQF, Ev3Constants.Opcode.CP_LTEQ8, Ev3Constants.Opcode.CP_LTEQ16, Ev3Constants.Opcode.CP_LTEQ32, Ev3Constants.Opcode.CP_LTEQF, Ev3Constants.Opcode.CP_GTEQ8, Ev3Constants.Opcode.CP_GTEQ16, Ev3Constants.Opcode.CP_GTEQ32, Ev3Constants.Opcode.SELECTF, Ev3Constants.Opcode.PORT_CNV_OUTPUT, Ev3Constants.Opcode.PORT_CNV_INPUT, Ev3Constants.Opcode.NOTE_TO_FREQ, Ev3Constants.Opcode.JR_LT8, Ev3Constants.Opcode.JR_LT16, Ev3Constants.Opcode.JR_LT32, Ev3Constants.Opcode.JR_LTF, Ev3Constants.Opcode.JR_GT8, Ev3Constants.Opcode.JR_GT16, Ev3Constants.Opcode.JR_GT32, Ev3Constants.Opcode.JR_GTF, Ev3Constants.Opcode.JR_EQ8, Ev3Constants.Opcode.JR_EQ16, Ev3Constants.Opcode.JR_EQ32, Ev3Constants.Opcode.JR_EQF, Ev3Constants.Opcode.JR_NEQ8, Ev3Constants.Opcode.JR_NEQ16, Ev3Constants.Opcode.JR_NEQ32, Ev3Constants.Opcode.JR_NEQF, Ev3Constants.Opcode.JR_LTEQ8, Ev3Constants.Opcode.JR_LTEQ16, Ev3Constants.Opcode.JR_LTEQ32, Ev3Constants.Opcode.JR_LTEQF, Ev3Constants.Opcode.JR_GTEQ8, Ev3Constants.Opcode.JR_GTEQ16, Ev3Constants.Opcode.JR_GTEQ32};
    private static final byte[] _ORDERED_DECODABET;
    private static final byte[] _STANDARD_ALPHABET = {Ev3Constants.Opcode.JR_FALSE, Ev3Constants.Opcode.JR_TRUE, Ev3Constants.Opcode.JR_NAN, Ev3Constants.Opcode.CP_LT8, Ev3Constants.Opcode.CP_LT16, Ev3Constants.Opcode.CP_LT32, Ev3Constants.Opcode.CP_LTF, Ev3Constants.Opcode.CP_GT8, Ev3Constants.Opcode.CP_GT16, Ev3Constants.Opcode.CP_GT32, Ev3Constants.Opcode.CP_GTF, Ev3Constants.Opcode.CP_EQ8, Ev3Constants.Opcode.CP_EQ16, Ev3Constants.Opcode.CP_EQ32, Ev3Constants.Opcode.CP_EQF, Ev3Constants.Opcode.CP_NEQ8, Ev3Constants.Opcode.CP_NEQ16, Ev3Constants.Opcode.CP_NEQ32, Ev3Constants.Opcode.CP_NEQF, Ev3Constants.Opcode.CP_LTEQ8, Ev3Constants.Opcode.CP_LTEQ16, Ev3Constants.Opcode.CP_LTEQ32, Ev3Constants.Opcode.CP_LTEQF, Ev3Constants.Opcode.CP_GTEQ8, Ev3Constants.Opcode.CP_GTEQ16, Ev3Constants.Opcode.CP_GTEQ32, Ev3Constants.Opcode.PORT_CNV_OUTPUT, Ev3Constants.Opcode.PORT_CNV_INPUT, Ev3Constants.Opcode.NOTE_TO_FREQ, Ev3Constants.Opcode.JR_LT8, Ev3Constants.Opcode.JR_LT16, Ev3Constants.Opcode.JR_LT32, Ev3Constants.Opcode.JR_LTF, Ev3Constants.Opcode.JR_GT8, Ev3Constants.Opcode.JR_GT16, Ev3Constants.Opcode.JR_GT32, Ev3Constants.Opcode.JR_GTF, Ev3Constants.Opcode.JR_EQ8, Ev3Constants.Opcode.JR_EQ16, Ev3Constants.Opcode.JR_EQ32, Ev3Constants.Opcode.JR_EQF, Ev3Constants.Opcode.JR_NEQ8, Ev3Constants.Opcode.JR_NEQ16, Ev3Constants.Opcode.JR_NEQ32, Ev3Constants.Opcode.JR_NEQF, Ev3Constants.Opcode.JR_LTEQ8, Ev3Constants.Opcode.JR_LTEQ16, Ev3Constants.Opcode.JR_LTEQ32, Ev3Constants.Opcode.JR_LTEQF, Ev3Constants.Opcode.JR_GTEQ8, Ev3Constants.Opcode.JR_GTEQ16, Ev3Constants.Opcode.JR_GTEQ32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_16, Ev3Constants.Opcode.MOVE8_32, Ev3Constants.Opcode.MOVE8_F, Ev3Constants.Opcode.MOVE16_8, Ev3Constants.Opcode.MOVE16_16, Ev3Constants.Opcode.MOVE16_32, Ev3Constants.Opcode.MOVE16_F, Ev3Constants.Opcode.MOVE32_8, Ev3Constants.Opcode.MOVE32_16, 43, Ev3Constants.Opcode.INIT_BYTES};
    private static final byte[] _STANDARD_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, Ev3Constants.Opcode.MOVEF_32, -9, -9, -9, Ev3Constants.Opcode.MOVEF_F, Ev3Constants.Opcode.MOVE16_8, Ev3Constants.Opcode.MOVE16_16, Ev3Constants.Opcode.MOVE16_32, Ev3Constants.Opcode.MOVE16_F, Ev3Constants.Opcode.MOVE32_8, Ev3Constants.Opcode.MOVE32_16, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE32_F, Ev3Constants.Opcode.MOVEF_8, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, Ev3Constants.Opcode.OR16, Ev3Constants.Opcode.OR32, 35, Ev3Constants.Opcode.AND8, Ev3Constants.Opcode.AND16, Ev3Constants.Opcode.AND32, 39, Ev3Constants.Opcode.XOR8, Ev3Constants.Opcode.XOR16, Ev3Constants.Opcode.XOR32, 43, Ev3Constants.Opcode.RL8, Ev3Constants.Opcode.RL16, Ev3Constants.Opcode.RL32, Ev3Constants.Opcode.INIT_BYTES, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_16, Ev3Constants.Opcode.MOVE8_32, Ev3Constants.Opcode.MOVE8_F, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] _URL_SAFE_ALPHABET = {Ev3Constants.Opcode.JR_FALSE, Ev3Constants.Opcode.JR_TRUE, Ev3Constants.Opcode.JR_NAN, Ev3Constants.Opcode.CP_LT8, Ev3Constants.Opcode.CP_LT16, Ev3Constants.Opcode.CP_LT32, Ev3Constants.Opcode.CP_LTF, Ev3Constants.Opcode.CP_GT8, Ev3Constants.Opcode.CP_GT16, Ev3Constants.Opcode.CP_GT32, Ev3Constants.Opcode.CP_GTF, Ev3Constants.Opcode.CP_EQ8, Ev3Constants.Opcode.CP_EQ16, Ev3Constants.Opcode.CP_EQ32, Ev3Constants.Opcode.CP_EQF, Ev3Constants.Opcode.CP_NEQ8, Ev3Constants.Opcode.CP_NEQ16, Ev3Constants.Opcode.CP_NEQ32, Ev3Constants.Opcode.CP_NEQF, Ev3Constants.Opcode.CP_LTEQ8, Ev3Constants.Opcode.CP_LTEQ16, Ev3Constants.Opcode.CP_LTEQ32, Ev3Constants.Opcode.CP_LTEQF, Ev3Constants.Opcode.CP_GTEQ8, Ev3Constants.Opcode.CP_GTEQ16, Ev3Constants.Opcode.CP_GTEQ32, Ev3Constants.Opcode.PORT_CNV_OUTPUT, Ev3Constants.Opcode.PORT_CNV_INPUT, Ev3Constants.Opcode.NOTE_TO_FREQ, Ev3Constants.Opcode.JR_LT8, Ev3Constants.Opcode.JR_LT16, Ev3Constants.Opcode.JR_LT32, Ev3Constants.Opcode.JR_LTF, Ev3Constants.Opcode.JR_GT8, Ev3Constants.Opcode.JR_GT16, Ev3Constants.Opcode.JR_GT32, Ev3Constants.Opcode.JR_GTF, Ev3Constants.Opcode.JR_EQ8, Ev3Constants.Opcode.JR_EQ16, Ev3Constants.Opcode.JR_EQ32, Ev3Constants.Opcode.JR_EQF, Ev3Constants.Opcode.JR_NEQ8, Ev3Constants.Opcode.JR_NEQ16, Ev3Constants.Opcode.JR_NEQ32, Ev3Constants.Opcode.JR_NEQF, Ev3Constants.Opcode.JR_LTEQ8, Ev3Constants.Opcode.JR_LTEQ16, Ev3Constants.Opcode.JR_LTEQ32, Ev3Constants.Opcode.JR_LTEQF, Ev3Constants.Opcode.JR_GTEQ8, Ev3Constants.Opcode.JR_GTEQ16, Ev3Constants.Opcode.JR_GTEQ32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_16, Ev3Constants.Opcode.MOVE8_32, Ev3Constants.Opcode.MOVE8_F, Ev3Constants.Opcode.MOVE16_8, Ev3Constants.Opcode.MOVE16_16, Ev3Constants.Opcode.MOVE16_32, Ev3Constants.Opcode.MOVE16_F, Ev3Constants.Opcode.MOVE32_8, Ev3Constants.Opcode.MOVE32_16, Ev3Constants.Opcode.RL16, Ev3Constants.Opcode.SELECTF};
    private static final byte[] _URL_SAFE_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, Ev3Constants.Opcode.MOVEF_32, -9, -9, Ev3Constants.Opcode.MOVE16_8, Ev3Constants.Opcode.MOVE16_16, Ev3Constants.Opcode.MOVE16_32, Ev3Constants.Opcode.MOVE16_F, Ev3Constants.Opcode.MOVE32_8, Ev3Constants.Opcode.MOVE32_16, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE32_F, Ev3Constants.Opcode.MOVEF_8, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, Ev3Constants.Opcode.MOVEF_F, -9, 26, 27, 28, 29, 30, 31, 32, Ev3Constants.Opcode.OR16, Ev3Constants.Opcode.OR32, 35, Ev3Constants.Opcode.AND8, Ev3Constants.Opcode.AND16, Ev3Constants.Opcode.AND32, 39, Ev3Constants.Opcode.XOR8, Ev3Constants.Opcode.XOR16, Ev3Constants.Opcode.XOR32, 43, Ev3Constants.Opcode.RL8, Ev3Constants.Opcode.RL16, Ev3Constants.Opcode.RL32, Ev3Constants.Opcode.INIT_BYTES, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_16, Ev3Constants.Opcode.MOVE8_32, Ev3Constants.Opcode.MOVE8_F, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    static {
        boolean z;
        if (!Base64.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
        byte[] bArr = new byte[InputDeviceCompat.SOURCE_KEYBOARD];
        // fill-array-data instruction
        bArr[0] = -9;
        bArr[1] = -9;
        bArr[2] = -9;
        bArr[3] = -9;
        bArr[4] = -9;
        bArr[5] = -9;
        bArr[6] = -9;
        bArr[7] = -9;
        bArr[8] = -9;
        bArr[9] = -5;
        bArr[10] = -5;
        bArr[11] = -9;
        bArr[12] = -9;
        bArr[13] = -5;
        bArr[14] = -9;
        bArr[15] = -9;
        bArr[16] = -9;
        bArr[17] = -9;
        bArr[18] = -9;
        bArr[19] = -9;
        bArr[20] = -9;
        bArr[21] = -9;
        bArr[22] = -9;
        bArr[23] = -9;
        bArr[24] = -9;
        bArr[25] = -9;
        bArr[26] = -9;
        bArr[27] = -9;
        bArr[28] = -9;
        bArr[29] = -9;
        bArr[30] = -9;
        bArr[31] = -9;
        bArr[32] = -5;
        bArr[33] = -9;
        bArr[34] = -9;
        bArr[35] = -9;
        bArr[36] = -9;
        bArr[37] = -9;
        bArr[38] = -9;
        bArr[39] = -9;
        bArr[40] = -9;
        bArr[41] = -9;
        bArr[42] = -9;
        bArr[43] = -9;
        bArr[44] = -9;
        bArr[45] = 0;
        bArr[46] = -9;
        bArr[47] = -9;
        bArr[48] = 1;
        bArr[49] = 2;
        bArr[50] = 3;
        bArr[51] = 4;
        bArr[52] = 5;
        bArr[53] = 6;
        bArr[54] = 7;
        bArr[55] = 8;
        bArr[56] = 9;
        bArr[57] = 10;
        bArr[58] = -9;
        bArr[59] = -9;
        bArr[60] = -9;
        bArr[61] = -1;
        bArr[62] = -9;
        bArr[63] = -9;
        bArr[64] = -9;
        bArr[65] = 11;
        bArr[66] = 12;
        bArr[67] = 13;
        bArr[68] = 14;
        bArr[69] = 15;
        bArr[70] = 16;
        bArr[71] = 17;
        bArr[72] = 18;
        bArr[73] = 19;
        bArr[74] = 20;
        bArr[75] = 21;
        bArr[76] = 22;
        bArr[77] = 23;
        bArr[78] = 24;
        bArr[79] = 25;
        bArr[80] = 26;
        bArr[81] = 27;
        bArr[82] = 28;
        bArr[83] = 29;
        bArr[84] = 30;
        bArr[85] = 31;
        bArr[86] = 32;
        bArr[87] = 33;
        bArr[88] = 34;
        bArr[89] = 35;
        bArr[90] = 36;
        bArr[91] = -9;
        bArr[92] = -9;
        bArr[93] = -9;
        bArr[94] = -9;
        bArr[95] = 37;
        bArr[96] = -9;
        bArr[97] = 38;
        bArr[98] = 39;
        bArr[99] = 40;
        bArr[100] = 41;
        bArr[101] = 42;
        bArr[102] = 43;
        bArr[103] = 44;
        bArr[104] = 45;
        bArr[105] = 46;
        bArr[106] = 47;
        bArr[107] = 48;
        bArr[108] = 49;
        bArr[109] = 50;
        bArr[110] = 51;
        bArr[111] = 52;
        bArr[112] = 53;
        bArr[113] = 54;
        bArr[114] = 55;
        bArr[115] = 56;
        bArr[116] = 57;
        bArr[117] = 58;
        bArr[118] = 59;
        bArr[119] = 60;
        bArr[120] = 61;
        bArr[121] = 62;
        bArr[122] = 63;
        bArr[123] = -9;
        bArr[124] = -9;
        bArr[125] = -9;
        bArr[126] = -9;
        bArr[127] = -9;
        bArr[128] = -9;
        bArr[129] = -9;
        bArr[130] = -9;
        bArr[131] = -9;
        bArr[132] = -9;
        bArr[133] = -9;
        bArr[134] = -9;
        bArr[135] = -9;
        bArr[136] = -9;
        bArr[137] = -9;
        bArr[138] = -9;
        bArr[139] = -9;
        bArr[140] = -9;
        bArr[141] = -9;
        bArr[142] = -9;
        bArr[143] = -9;
        bArr[144] = -9;
        bArr[145] = -9;
        bArr[146] = -9;
        bArr[147] = -9;
        bArr[148] = -9;
        bArr[149] = -9;
        bArr[150] = -9;
        bArr[151] = -9;
        bArr[152] = -9;
        bArr[153] = -9;
        bArr[154] = -9;
        bArr[155] = -9;
        bArr[156] = -9;
        bArr[157] = -9;
        bArr[158] = -9;
        bArr[159] = -9;
        bArr[160] = -9;
        bArr[161] = -9;
        bArr[162] = -9;
        bArr[163] = -9;
        bArr[164] = -9;
        bArr[165] = -9;
        bArr[166] = -9;
        bArr[167] = -9;
        bArr[168] = -9;
        bArr[169] = -9;
        bArr[170] = -9;
        bArr[171] = -9;
        bArr[172] = -9;
        bArr[173] = -9;
        bArr[174] = -9;
        bArr[175] = -9;
        bArr[176] = -9;
        bArr[177] = -9;
        bArr[178] = -9;
        bArr[179] = -9;
        bArr[180] = -9;
        bArr[181] = -9;
        bArr[182] = -9;
        bArr[183] = -9;
        bArr[184] = -9;
        bArr[185] = -9;
        bArr[186] = -9;
        bArr[187] = -9;
        bArr[188] = -9;
        bArr[189] = -9;
        bArr[190] = -9;
        bArr[191] = -9;
        bArr[192] = -9;
        bArr[193] = -9;
        bArr[194] = -9;
        bArr[195] = -9;
        bArr[196] = -9;
        bArr[197] = -9;
        bArr[198] = -9;
        bArr[199] = -9;
        bArr[200] = -9;
        bArr[201] = -9;
        bArr[202] = -9;
        bArr[203] = -9;
        bArr[204] = -9;
        bArr[205] = -9;
        bArr[206] = -9;
        bArr[207] = -9;
        bArr[208] = -9;
        bArr[209] = -9;
        bArr[210] = -9;
        bArr[211] = -9;
        bArr[212] = -9;
        bArr[213] = -9;
        bArr[214] = -9;
        bArr[215] = -9;
        bArr[216] = -9;
        bArr[217] = -9;
        bArr[218] = -9;
        bArr[219] = -9;
        bArr[220] = -9;
        bArr[221] = -9;
        bArr[222] = -9;
        bArr[223] = -9;
        bArr[224] = -9;
        bArr[225] = -9;
        bArr[226] = -9;
        bArr[227] = -9;
        bArr[228] = -9;
        bArr[229] = -9;
        bArr[230] = -9;
        bArr[231] = -9;
        bArr[232] = -9;
        bArr[233] = -9;
        bArr[234] = -9;
        bArr[235] = -9;
        bArr[236] = -9;
        bArr[237] = -9;
        bArr[238] = -9;
        bArr[239] = -9;
        bArr[240] = -9;
        bArr[241] = -9;
        bArr[242] = -9;
        bArr[243] = -9;
        bArr[244] = -9;
        bArr[245] = -9;
        bArr[246] = -9;
        bArr[247] = -9;
        bArr[248] = -9;
        bArr[249] = -9;
        bArr[250] = -9;
        bArr[251] = -9;
        bArr[252] = -9;
        bArr[253] = -9;
        bArr[254] = -9;
        bArr[255] = -9;
        bArr[256] = -9;
        _ORDERED_DECODABET = bArr;
    }

    private static final byte[] getAlphabet(int i) {
        int options = i;
        if ((options & 16) == 16) {
            return _URL_SAFE_ALPHABET;
        }
        if ((options & 32) == 32) {
            return _ORDERED_ALPHABET;
        }
        return _STANDARD_ALPHABET;
    }

    /* access modifiers changed from: private */
    public static final byte[] getDecodabet(int i) {
        int options = i;
        if ((options & 16) == 16) {
            return _URL_SAFE_DECODABET;
        }
        if ((options & 32) == 32) {
            return _ORDERED_DECODABET;
        }
        return _STANDARD_DECODABET;
    }

    private Base64() {
    }

    /* access modifiers changed from: private */
    public static byte[] encode3to4(byte[] bArr, byte[] threeBytes, int numSigBytes, int options) {
        byte[] b4 = bArr;
        byte[] encode3to4 = encode3to4(threeBytes, 0, numSigBytes, b4, 0, options);
        return b4;
    }

    /* access modifiers changed from: private */
    public static byte[] encode3to4(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int options) {
        byte[] source = bArr;
        int srcOffset = i;
        int numSigBytes = i2;
        byte[] destination = bArr2;
        int destOffset = i3;
        byte[] ALPHABET = getAlphabet(options);
        int inBuff = (numSigBytes > 0 ? (source[srcOffset] << 24) >>> 8 : 0) | (numSigBytes > 1 ? (source[srcOffset + 1] << 24) >>> 16 : 0) | (numSigBytes > 2 ? (source[srcOffset + 2] << 24) >>> 24 : 0);
        switch (numSigBytes) {
            case 1:
                destination[destOffset] = ALPHABET[inBuff >>> 18];
                destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 63];
                destination[destOffset + 2] = 61;
                destination[destOffset + 3] = 61;
                return destination;
            case 2:
                destination[destOffset] = ALPHABET[inBuff >>> 18];
                destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 63];
                destination[destOffset + 2] = ALPHABET[(inBuff >>> 6) & 63];
                destination[destOffset + 3] = 61;
                return destination;
            case 3:
                destination[destOffset] = ALPHABET[inBuff >>> 18];
                destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 63];
                destination[destOffset + 2] = ALPHABET[(inBuff >>> 6) & 63];
                destination[destOffset + 3] = ALPHABET[inBuff & 63];
                return destination;
            default:
                return destination;
        }
    }

    public static void encode(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        ByteBuffer raw = byteBuffer;
        ByteBuffer encoded = byteBuffer2;
        byte[] raw3 = new byte[3];
        byte[] enc4 = new byte[4];
        while (raw.hasRemaining()) {
            int rem = Math.min(3, raw.remaining());
            ByteBuffer byteBuffer3 = raw.get(raw3, 0, rem);
            byte[] encode3to4 = encode3to4(enc4, raw3, rem, 0);
            ByteBuffer put = encoded.put(enc4);
        }
    }

    public static void encode(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        ByteBuffer raw = byteBuffer;
        CharBuffer encoded = charBuffer;
        byte[] raw3 = new byte[3];
        byte[] enc4 = new byte[4];
        while (raw.hasRemaining()) {
            int rem = Math.min(3, raw.remaining());
            ByteBuffer byteBuffer2 = raw.get(raw3, 0, rem);
            byte[] encode3to4 = encode3to4(enc4, raw3, rem, 0);
            for (int i = 0; i < 4; i++) {
                CharBuffer put = encoded.put((char) (enc4[i] & 255));
            }
        }
    }

    public static String encodeObject(Serializable serializableObject) throws IOException {
        return encodeObject(serializableObject, 0);
    }

    public static String encodeObject(Serializable serializable, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        java.io.OutputStream outputStream;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream oos;
        String str;
        String str2;
        GZIPOutputStream gZIPOutputStream;
        ObjectOutputStream objectOutputStream2;
        Throwable th;
        Serializable serializableObject = serializable;
        int options = i;
        if (serializableObject == null) {
            Throwable th2 = th;
            new NullPointerException("Cannot serialize a null object.");
            throw th2;
        }
        ByteArrayOutputStream baos = null;
        java.io.OutputStream b64os = null;
        GZIPOutputStream gzos = null;
        try {
            new ByteArrayOutputStream();
            baos = byteArrayOutputStream;
            new OutputStream(baos, 1 | options);
            b64os = outputStream;
            if ((options & 2) != 0) {
                new GZIPOutputStream(b64os);
                gzos = gZIPOutputStream;
                new ObjectOutputStream(gzos);
                oos = objectOutputStream2;
            } else {
                ObjectOutputStream objectOutputStream3 = objectOutputStream;
                new ObjectOutputStream(b64os);
                oos = objectOutputStream3;
            }
            oos.writeObject(serializableObject);
            try {
                oos.close();
            } catch (Exception e) {
                Exception exc = e;
            }
            try {
                gzos.close();
            } catch (Exception e2) {
                Exception exc2 = e2;
            }
            try {
                b64os.close();
            } catch (Exception e3) {
                Exception exc3 = e3;
            }
            try {
                baos.close();
            } catch (Exception e4) {
                Exception exc4 = e4;
            }
            try {
                String str3 = str2;
                new String(baos.toByteArray(), "US-ASCII");
                return str3;
            } catch (UnsupportedEncodingException e5) {
                UnsupportedEncodingException unsupportedEncodingException = e5;
                new String(baos.toByteArray());
                return str;
            }
        } catch (IOException e6) {
            throw e6;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            ObjectOutputStream objectOutputStream4 = null;
            try {
                objectOutputStream4.close();
            } catch (Exception e7) {
                Exception exc5 = e7;
            }
            GZIPOutputStream gZIPOutputStream2 = null;
            try {
                gZIPOutputStream2.close();
            } catch (Exception e8) {
                Exception exc6 = e8;
            }
            try {
                b64os.close();
            } catch (Exception e9) {
                Exception exc7 = e9;
            }
            try {
                baos.close();
            } catch (Exception e10) {
                Exception exc8 = e10;
            }
            throw th4;
        }
    }

    public static String encodeBytes(byte[] bArr) {
        Throwable th;
        Throwable th2;
        byte[] source = bArr;
        String encoded = null;
        try {
            encoded = encodeBytes(source, 0, source.length, 0);
        } catch (IOException e) {
            IOException ex = e;
            if (!$assertionsDisabled) {
                Throwable th3 = th;
                new AssertionError(ex.getMessage());
                throw th3;
            }
        }
        if ($assertionsDisabled || encoded != null) {
            return encoded;
        }
        Throwable th4 = th2;
        new AssertionError();
        throw th4;
    }

    public static String encodeBytes(byte[] bArr, int options) throws IOException {
        byte[] source = bArr;
        return encodeBytes(source, 0, source.length, options);
    }

    public static String encodeBytes(byte[] source, int off, int len) {
        Throwable th;
        Throwable th2;
        String encoded = null;
        try {
            encoded = encodeBytes(source, off, len, 0);
        } catch (IOException e) {
            IOException ex = e;
            if (!$assertionsDisabled) {
                Throwable th3 = th;
                new AssertionError(ex.getMessage());
                throw th3;
            }
        }
        if ($assertionsDisabled || encoded != null) {
            return encoded;
        }
        Throwable th4 = th2;
        new AssertionError();
        throw th4;
    }

    public static String encodeBytes(byte[] source, int off, int len, int options) throws IOException {
        String str;
        String str2;
        byte[] encoded = encodeBytesToBytes(source, off, len, options);
        try {
            String str3 = str2;
            new String(encoded, "US-ASCII");
            return str3;
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            new String(encoded);
            return str;
        }
    }

    public static byte[] encodeBytesToBytes(byte[] bArr) {
        Throwable th;
        StringBuilder sb;
        byte[] source = bArr;
        byte[] encoded = null;
        try {
            encoded = encodeBytesToBytes(source, 0, source.length, 0);
        } catch (IOException e) {
            IOException ex = e;
            if (!$assertionsDisabled) {
                Throwable th2 = th;
                new StringBuilder();
                new AssertionError(sb.append("IOExceptions only come from GZipping, which is turned off: ").append(ex.getMessage()).toString());
                throw th2;
            }
        }
        return encoded;
    }

    public static byte[] encodeBytesToBytes(byte[] bArr, int i, int i2, int i3) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        OutputStream outputStream;
        GZIPOutputStream gZIPOutputStream;
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        StringBuilder sb2;
        Throwable th4;
        byte[] source = bArr;
        int off = i;
        int len = i2;
        int options = i3;
        if (source == null) {
            Throwable th5 = th4;
            new NullPointerException("Cannot serialize a null array.");
            throw th5;
        } else if (off < 0) {
            Throwable th6 = th3;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Cannot have negative offset: ").append(off).toString());
            throw th6;
        } else if (len < 0) {
            Throwable th7 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Cannot have length offset: ").append(len).toString());
            throw th7;
        } else if (off + len > source.length) {
            Throwable th8 = th;
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(off);
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(len);
            Object[] objArr3 = objArr2;
            objArr3[2] = Integer.valueOf(source.length);
            new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", objArr3));
            throw th8;
        } else if ((options & 2) != 0) {
            ByteArrayOutputStream baos = null;
            GZIPOutputStream gzos = null;
            OutputStream b64os = null;
            try {
                new ByteArrayOutputStream();
                baos = byteArrayOutputStream;
                new OutputStream(baos, 1 | options);
                b64os = outputStream;
                new GZIPOutputStream(b64os);
                gzos = gZIPOutputStream;
                gzos.write(source, off, len);
                gzos.close();
                try {
                    gzos.close();
                } catch (Exception e) {
                    Exception exc = e;
                }
                try {
                    b64os.close();
                } catch (Exception e2) {
                    Exception exc2 = e2;
                }
                try {
                    baos.close();
                } catch (Exception e3) {
                    Exception exc3 = e3;
                }
                return baos.toByteArray();
            } catch (IOException e4) {
                throw e4;
            } catch (Throwable th9) {
                Throwable th10 = th9;
                try {
                    gzos.close();
                } catch (Exception e5) {
                    Exception exc4 = e5;
                }
                try {
                    b64os.close();
                } catch (Exception e6) {
                    Exception exc5 = e6;
                }
                try {
                    baos.close();
                } catch (Exception e7) {
                    Exception exc6 = e7;
                }
                throw th10;
            }
        } else {
            boolean breakLines = (options & 8) != 0;
            int encLen = ((len / 3) * 4) + (len % 3 > 0 ? 4 : 0);
            if (breakLines) {
                encLen += encLen / 76;
            }
            byte[] outBuff = new byte[encLen];
            int d = 0;
            int e8 = 0;
            int len2 = len - 2;
            int lineLength = 0;
            while (d < len2) {
                byte[] encode3to4 = encode3to4(source, d + off, 3, outBuff, e8, options);
                lineLength += 4;
                if (breakLines && lineLength >= 76) {
                    outBuff[e8 + 4] = 10;
                    e8++;
                    lineLength = 0;
                }
                d += 3;
                e8 += 4;
            }
            if (d < len) {
                byte[] encode3to42 = encode3to4(source, d + off, len - d, outBuff, e8, options);
                e8 += 4;
            }
            if (e8 > outBuff.length - 1) {
                return outBuff;
            }
            byte[] finalOut = new byte[e8];
            System.arraycopy(outBuff, 0, finalOut, 0, e8);
            return finalOut;
        }
    }

    /* access modifiers changed from: private */
    public static int decode4to3(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        byte[] source = bArr;
        int srcOffset = i;
        byte[] destination = bArr2;
        int destOffset = i2;
        int options = i3;
        if (source == null) {
            Throwable th5 = th4;
            new NullPointerException("Source array was null.");
            throw th5;
        } else if (destination == null) {
            Throwable th6 = th3;
            new NullPointerException("Destination array was null.");
            throw th6;
        } else if (srcOffset < 0 || srcOffset + 3 >= source.length) {
            Throwable th7 = th;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(source.length);
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(srcOffset);
            new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", objArr2));
            throw th7;
        } else if (destOffset < 0 || destOffset + 2 >= destination.length) {
            Throwable th8 = th2;
            Object[] objArr3 = new Object[2];
            objArr3[0] = Integer.valueOf(destination.length);
            Object[] objArr4 = objArr3;
            objArr4[1] = Integer.valueOf(destOffset);
            new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", objArr4));
            throw th8;
        } else {
            byte[] DECODABET = getDecodabet(options);
            if (source[srcOffset + 2] == 61) {
                destination[destOffset] = (byte) ((((DECODABET[source[srcOffset]] & 255) << 18) | ((DECODABET[source[srcOffset + 1]] & 255) << 12)) >>> 16);
                return 1;
            } else if (source[srcOffset + 3] == 61) {
                int outBuff = ((DECODABET[source[srcOffset]] & 255) << 18) | ((DECODABET[source[srcOffset + 1]] & 255) << 12) | ((DECODABET[source[srcOffset + 2]] & 255) << 6);
                destination[destOffset] = (byte) (outBuff >>> 16);
                destination[destOffset + 1] = (byte) (outBuff >>> 8);
                return 2;
            } else {
                int outBuff2 = ((DECODABET[source[srcOffset]] & 255) << 18) | ((DECODABET[source[srcOffset + 1]] & 255) << 12) | ((DECODABET[source[srcOffset + 2]] & 255) << 6) | (DECODABET[source[srcOffset + 3]] & 255);
                destination[destOffset] = (byte) (outBuff2 >> 16);
                destination[destOffset + 1] = (byte) (outBuff2 >> 8);
                destination[destOffset + 2] = (byte) outBuff2;
                return 3;
            }
        }
    }

    public static byte[] decode(byte[] bArr) throws IOException {
        byte[] source = bArr;
        return decode(source, 0, source.length, 0);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) throws IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        StringBuilder sb;
        Throwable th4;
        byte[] source = bArr;
        int off = i;
        int len = i2;
        int options = i3;
        if (source == null) {
            Throwable th5 = th4;
            new NullPointerException("Cannot decode null source array.");
            throw th5;
        } else if (off < 0 || off + len > source.length) {
            Throwable th6 = th;
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(source.length);
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(off);
            Object[] objArr3 = objArr2;
            objArr3[2] = Integer.valueOf(len);
            new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", objArr3));
            throw th6;
        } else if (len == 0) {
            return new byte[0];
        } else {
            if (len < 4) {
                Throwable th7 = th3;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Base64-encoded string must have at least four characters, but length specified was ").append(len).toString());
                throw th7;
            }
            byte[] DECODABET = getDecodabet(options);
            byte[] outBuff = new byte[((len * 3) / 4)];
            int outBuffPosn = 0;
            byte[] b4 = new byte[4];
            int b4Posn = 0;
            int i4 = off;
            while (i4 < off + len) {
                byte sbiDecode = DECODABET[source[i4] & 255];
                if (sbiDecode >= -5) {
                    if (sbiDecode >= -1) {
                        int i5 = b4Posn;
                        b4Posn++;
                        b4[i5] = source[i4];
                        if (b4Posn > 3) {
                            outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn, options);
                            b4Posn = 0;
                            if (source[i4] == 61) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                    i4++;
                } else {
                    Throwable th8 = th2;
                    Object[] objArr4 = new Object[2];
                    objArr4[0] = Integer.valueOf(source[i4] & 255);
                    Object[] objArr5 = objArr4;
                    objArr5[1] = Integer.valueOf(i4);
                    new IOException(String.format("Bad Base64 input character decimal %d in array position %d", objArr5));
                    throw th8;
                }
            }
            byte[] out = new byte[outBuffPosn];
            System.arraycopy(outBuff, 0, out, 0, outBuffPosn);
            return out;
        }
    }

    public static byte[] decode(String s) throws IOException {
        return decode(s, 0);
    }

    public static byte[] decode(String str, int i) throws IOException {
        byte[] bytes;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        GZIPInputStream gZIPInputStream;
        Throwable th;
        String s = str;
        int options = i;
        if (s == null) {
            Throwable th2 = th;
            new NullPointerException("Input string was null.");
            throw th2;
        }
        try {
            bytes = s.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            bytes = s.getBytes();
        }
        byte[] bytes2 = decode(bytes, 0, bytes.length, options);
        boolean dontGunzip = (options & 4) != 0;
        if (bytes2 != null && bytes2.length >= 4 && !dontGunzip && 35615 == ((bytes2[0] & 255) | ((bytes2[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
            ByteArrayInputStream bais = null;
            GZIPInputStream gzis = null;
            ByteArrayOutputStream baos = null;
            byte[] buffer = new byte[2048];
            try {
                new ByteArrayOutputStream();
                baos = byteArrayOutputStream;
                new ByteArrayInputStream(bytes2);
                bais = byteArrayInputStream;
                new GZIPInputStream(bais);
                gzis = gZIPInputStream;
                while (true) {
                    int read = gzis.read(buffer);
                    int length = read;
                    if (read < 0) {
                        break;
                    }
                    baos.write(buffer, 0, length);
                }
                bytes2 = baos.toByteArray();
                try {
                    baos.close();
                } catch (Exception e2) {
                    Exception exc = e2;
                }
                try {
                    gzis.close();
                } catch (Exception e3) {
                    Exception exc2 = e3;
                }
                try {
                    bais.close();
                } catch (Exception e4) {
                    Exception exc3 = e4;
                }
            } catch (IOException e5) {
                IOException iOException = e5;
                try {
                    baos.close();
                } catch (Exception e6) {
                    Exception exc4 = e6;
                }
                try {
                    gzis.close();
                } catch (Exception e7) {
                    Exception exc5 = e7;
                }
                try {
                    bais.close();
                } catch (Exception e8) {
                    Exception exc6 = e8;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                try {
                    baos.close();
                } catch (Exception e9) {
                    Exception exc7 = e9;
                }
                try {
                    gzis.close();
                } catch (Exception e10) {
                    Exception exc8 = e10;
                }
                try {
                    bais.close();
                } catch (Exception e11) {
                    Exception exc9 = e11;
                }
                throw th4;
            }
        }
        return bytes2;
    }

    public static Object decodeToObject(String encodedObject) throws IOException, ClassNotFoundException {
        return decodeToObject(encodedObject, 0, (ClassLoader) null);
    }

    public static Object decodeToObject(String encodedObject, int options, ClassLoader classLoader) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        ObjectInputStream ois;
        ObjectInputStream objectInputStream2;
        ClassLoader loader = classLoader;
        ByteArrayInputStream bais = null;
        try {
            new ByteArrayInputStream(decode(encodedObject, options));
            bais = byteArrayInputStream;
            if (loader == null) {
                new ObjectInputStream(bais);
                ois = objectInputStream2;
            } else {
                ObjectInputStream objectInputStream3 = objectInputStream;
                final ClassLoader classLoader2 = loader;
                new ObjectInputStream(bais) {
                    public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                        ObjectStreamClass streamClass = objectStreamClass;
                        Class<?> c = Class.forName(streamClass.getName(), false, classLoader2);
                        if (c == null) {
                            return super.resolveClass(streamClass);
                        }
                        return c;
                    }
                };
                ois = objectInputStream3;
            }
            Object obj = ois.readObject();
            try {
                bais.close();
            } catch (Exception e) {
                Exception exc = e;
            }
            try {
                ois.close();
            } catch (Exception e2) {
                Exception exc2 = e2;
            }
            return obj;
        } catch (IOException e3) {
            throw e3;
        } catch (ClassNotFoundException e4) {
            throw e4;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                bais.close();
            } catch (Exception e5) {
                Exception exc3 = e5;
            }
            ObjectInputStream objectInputStream4 = null;
            try {
                objectInputStream4.close();
            } catch (Exception e6) {
                Exception exc4 = e6;
            }
            throw th2;
        }
    }

    public static void encodeToFile(byte[] bArr, String str) throws IOException {
        OutputStream outputStream;
        java.io.OutputStream outputStream2;
        Throwable th;
        byte[] dataToEncode = bArr;
        String filename = str;
        if (dataToEncode == null) {
            Throwable th2 = th;
            new NullPointerException("Data to encode was null.");
            throw th2;
        }
        OutputStream bos = null;
        try {
            new FileOutputStream(filename);
            new OutputStream(outputStream2, 1);
            bos = outputStream;
            bos.write(dataToEncode);
            try {
                bos.close();
            } catch (Exception e) {
                Exception exc = e;
            }
        } catch (IOException e2) {
            throw e2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            try {
                bos.close();
            } catch (Exception e3) {
                Exception exc2 = e3;
            }
            throw th4;
        }
    }

    public static void decodeToFile(String str, String filename) throws IOException {
        OutputStream outputStream;
        java.io.OutputStream outputStream2;
        String dataToDecode = str;
        OutputStream bos = null;
        try {
            new FileOutputStream(filename);
            new OutputStream(outputStream2, 0);
            bos = outputStream;
            bos.write(dataToDecode.getBytes("US-ASCII"));
            try {
                bos.close();
            } catch (Exception e) {
                Exception exc = e;
            }
        } catch (IOException e2) {
            throw e2;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                bos.close();
            } catch (Exception e3) {
                Exception exc2 = e3;
            }
            throw th2;
        }
    }

    public static byte[] decodeFromFile(String filename) throws IOException {
        File file;
        InputStream inputStream;
        java.io.InputStream inputStream2;
        java.io.InputStream inputStream3;
        Throwable th;
        StringBuilder sb;
        try {
            new File(filename);
            File file2 = file;
            int length = 0;
            if (file2.length() > 2147483647L) {
                Throwable th2 = th;
                new StringBuilder();
                new IOException(sb.append("File is too big for this convenience method (").append(file2.length()).append(" bytes).").toString());
                throw th2;
            }
            byte[] buffer = new byte[((int) file2.length())];
            new FileInputStream(file2);
            new BufferedInputStream(inputStream3);
            new InputStream(inputStream2, 0);
            InputStream bis = inputStream;
            while (true) {
                int read = bis.read(buffer, length, 4096);
                int numBytes = read;
                if (read < 0) {
                    break;
                }
                length += numBytes;
            }
            byte[] decodedData = new byte[length];
            System.arraycopy(buffer, 0, decodedData, 0, length);
            try {
                bis.close();
            } catch (Exception e) {
                Exception exc = e;
            }
            return decodedData;
        } catch (IOException e2) {
            throw e2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            InputStream inputStream4 = null;
            try {
                inputStream4.close();
            } catch (Exception e3) {
                Exception exc2 = e3;
            }
            throw th4;
        }
    }

    public static String encodeFromFile(String filename) throws IOException {
        File file;
        InputStream inputStream;
        java.io.InputStream inputStream2;
        java.io.InputStream inputStream3;
        String str;
        try {
            new File(filename);
            File file2 = file;
            byte[] buffer = new byte[Math.max((int) ((((double) file2.length()) * 1.4d) + 1.0d), 40)];
            int length = 0;
            new FileInputStream(file2);
            new BufferedInputStream(inputStream3);
            new InputStream(inputStream2, 1);
            InputStream bis = inputStream;
            while (true) {
                int read = bis.read(buffer, length, 4096);
                int numBytes = read;
                if (read < 0) {
                    break;
                }
                length += numBytes;
            }
            String str2 = str;
            new String(buffer, 0, length, "US-ASCII");
            String encodedData = str2;
            try {
                bis.close();
            } catch (Exception e) {
                Exception exc = e;
            }
            return encodedData;
        } catch (IOException e2) {
            throw e2;
        } catch (Throwable th) {
            Throwable th2 = th;
            InputStream inputStream4 = null;
            try {
                inputStream4.close();
            } catch (Exception e3) {
                Exception exc2 = e3;
            }
            throw th2;
        }
    }

    public static void encodeFileToFile(String infile, String str) throws IOException {
        java.io.OutputStream outputStream;
        java.io.OutputStream outputStream2;
        String outfile = str;
        String encoded = encodeFromFile(infile);
        java.io.OutputStream out = null;
        try {
            new FileOutputStream(outfile);
            new BufferedOutputStream(outputStream2);
            out = outputStream;
            out.write(encoded.getBytes("US-ASCII"));
            try {
                out.close();
            } catch (Exception e) {
                Exception exc = e;
            }
        } catch (IOException e2) {
            throw e2;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                out.close();
            } catch (Exception e3) {
                Exception exc2 = e3;
            }
            throw th2;
        }
    }

    public static void decodeFileToFile(String infile, String str) throws IOException {
        java.io.OutputStream outputStream;
        java.io.OutputStream outputStream2;
        String outfile = str;
        byte[] decoded = decodeFromFile(infile);
        java.io.OutputStream out = null;
        try {
            new FileOutputStream(outfile);
            new BufferedOutputStream(outputStream2);
            out = outputStream;
            out.write(decoded);
            try {
                out.close();
            } catch (Exception e) {
                Exception exc = e;
            }
        } catch (IOException e2) {
            throw e2;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                out.close();
            } catch (Exception e3) {
                Exception exc2 = e3;
            }
            throw th2;
        }
    }

    public static class InputStream extends FilterInputStream {
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int numSigBytes;
        private int options;
        private int position;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public InputStream(java.io.InputStream in) {
            this(in, 0);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InputStream(java.io.InputStream in, int i) {
            super(in);
            int options2 = i;
            this.options = options2;
            this.breakLines = (options2 & 8) > 0;
            this.encode = (options2 & 1) > 0;
            this.bufferLength = this.encode ? 4 : 3;
            this.buffer = new byte[this.bufferLength];
            this.position = -1;
            this.lineLength = 0;
            this.decodabet = Base64.getDecodabet(options2);
        }

        public int read() throws IOException {
            Throwable th;
            Throwable th2;
            int b;
            int b2;
            if (this.position < 0) {
                if (this.encode) {
                    byte[] b3 = new byte[3];
                    int numBinaryBytes = 0;
                    for (int i = 0; i < 3 && (b2 = this.in.read()) >= 0; i++) {
                        b3[i] = (byte) b2;
                        numBinaryBytes++;
                    }
                    if (numBinaryBytes <= 0) {
                        return -1;
                    }
                    byte[] access$100 = Base64.encode3to4(b3, 0, numBinaryBytes, this.buffer, 0, this.options);
                    this.position = 0;
                    this.numSigBytes = 4;
                } else {
                    byte[] b4 = new byte[4];
                    int i2 = 0;
                    while (i2 < 4) {
                        do {
                            b = this.in.read();
                            if (b < 0 || this.decodabet[b & 127] > -5) {
                            }
                            b = this.in.read();
                            break;
                        } while (this.decodabet[b & 127] > -5);
                        if (b < 0) {
                            break;
                        }
                        b4[i2] = (byte) b;
                        i2++;
                    }
                    if (i2 == 4) {
                        this.numSigBytes = Base64.decode4to3(b4, 0, this.buffer, 0, this.options);
                        this.position = 0;
                    } else if (i2 == 0) {
                        return -1;
                    } else {
                        Throwable th3 = th2;
                        new IOException("Improperly padded Base64 input.");
                        throw th3;
                    }
                }
            }
            if (this.position < 0) {
                Throwable th4 = th;
                new IOException("Error in Base64 code reading stream.");
                throw th4;
            } else if (this.position >= this.numSigBytes) {
                return -1;
            } else {
                if (!this.encode || !this.breakLines || this.lineLength < 76) {
                    this.lineLength++;
                    byte[] bArr = this.buffer;
                    int i3 = this.position;
                    int i4 = i3 + 1;
                    this.position = i4;
                    int b5 = bArr[i3];
                    if (this.position >= this.bufferLength) {
                        this.position = -1;
                    }
                    return b5 & 255;
                }
                this.lineLength = 0;
                return 10;
            }
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            byte[] dest = bArr;
            int off = i;
            int len = i2;
            int i3 = 0;
            while (true) {
                if (i3 >= len) {
                    break;
                }
                int b = read();
                if (b >= 0) {
                    dest[off + i3] = (byte) b;
                    i3++;
                } else if (i3 == 0) {
                    return -1;
                }
            }
            return i3;
        }
    }

    public static class OutputStream extends FilterOutputStream {

        /* renamed from: b4 */
        private byte[] f279b4;
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int options;
        private int position;
        private boolean suspendEncoding;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public OutputStream(java.io.OutputStream out) {
            this(out, 1);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OutputStream(java.io.OutputStream out, int i) {
            super(out);
            int options2 = i;
            this.breakLines = (options2 & 8) != 0;
            this.encode = (options2 & 1) != 0;
            this.bufferLength = this.encode ? 3 : 4;
            this.buffer = new byte[this.bufferLength];
            this.position = 0;
            this.lineLength = 0;
            this.suspendEncoding = false;
            this.f279b4 = new byte[4];
            this.options = options2;
            this.decodabet = Base64.getDecodabet(options2);
        }

        public void write(int i) throws IOException {
            Throwable th;
            int theByte = i;
            if (this.suspendEncoding) {
                this.out.write(theByte);
            } else if (this.encode) {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                int i3 = i2 + 1;
                this.position = i3;
                bArr[i2] = (byte) theByte;
                if (this.position >= this.bufferLength) {
                    this.out.write(Base64.encode3to4(this.f279b4, this.buffer, this.bufferLength, this.options));
                    this.lineLength += 4;
                    if (this.breakLines && this.lineLength >= 76) {
                        this.out.write(10);
                        this.lineLength = 0;
                    }
                    this.position = 0;
                }
            } else if (this.decodabet[theByte & 127] > -5) {
                byte[] bArr2 = this.buffer;
                int i4 = this.position;
                int i5 = i4 + 1;
                this.position = i5;
                bArr2[i4] = (byte) theByte;
                if (this.position >= this.bufferLength) {
                    this.out.write(this.f279b4, 0, Base64.decode4to3(this.buffer, 0, this.f279b4, 0, this.options));
                    this.position = 0;
                }
            } else if (this.decodabet[theByte & 127] != -5) {
                Throwable th2 = th;
                new IOException("Invalid character in Base64 data.");
                throw th2;
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            byte[] theBytes = bArr;
            int off = i;
            int len = i2;
            if (this.suspendEncoding) {
                this.out.write(theBytes, off, len);
                return;
            }
            for (int i3 = 0; i3 < len; i3++) {
                write(theBytes[off + i3]);
            }
        }

        public void flushBase64() throws IOException {
            Throwable th;
            if (this.position <= 0) {
                return;
            }
            if (this.encode) {
                this.out.write(Base64.encode3to4(this.f279b4, this.buffer, this.position, this.options));
                this.position = 0;
                return;
            }
            Throwable th2 = th;
            new IOException("Base64 input not properly padded.");
            throw th2;
        }

        public void close() throws IOException {
            flushBase64();
            super.close();
            this.buffer = null;
            this.out = null;
        }

        public void suspendEncoding() throws IOException {
            flushBase64();
            this.suspendEncoding = true;
        }

        public void resumeEncoding() {
            this.suspendEncoding = false;
        }
    }
}
