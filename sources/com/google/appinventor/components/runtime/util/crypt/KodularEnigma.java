package com.google.appinventor.components.runtime.util.crypt;

import com.firebase.client.core.Constants;
import gnu.bytecode.Access;

public class KodularEnigma {
    public static final StringBuffer[] notches;
    public static final StringBuffer reflector0;
    public static final StringBuffer reflectorB;
    public static final StringBuffer reflectorC;
    public static final StringBuffer rotorI;
    public static final StringBuffer rotorII;
    public static final StringBuffer rotorIII;
    public static final StringBuffer rotorIV;
    public static final StringBuffer rotorV;
    public static final StringBuffer rotorVI;
    public static final StringBuffer rotorVII;
    public static final StringBuffer rotorVIII;
    private StringBuffer B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    public StringBuffer firstRotor;
    public StringBuffer firstRotorT;
    private StringBuffer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    public char[] plugBoard = {'A', 'B', Access.CLASS_CONTEXT, 'D', 'E', Access.FIELD_CONTEXT, 'G', 'H', Access.INNERCLASS_CONTEXT, 'J', 'K', 'L', Access.METHOD_CONTEXT, 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public StringBuffer reflector;
    public StringBuffer secondRotor;
    public StringBuffer secondRotorT;
    public StringBuffer thirdRotor;
    public StringBuffer thirdRotorT;

    static {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        StringBuffer stringBuffer4;
        StringBuffer stringBuffer5;
        StringBuffer stringBuffer6;
        StringBuffer stringBuffer7;
        StringBuffer stringBuffer8;
        StringBuffer stringBuffer9;
        StringBuffer stringBuffer10;
        StringBuffer stringBuffer11;
        StringBuffer stringBuffer12;
        StringBuffer stringBuffer13;
        StringBuffer stringBuffer14;
        StringBuffer stringBuffer15;
        StringBuffer stringBuffer16;
        StringBuffer stringBuffer17;
        StringBuffer stringBuffer18;
        StringBuffer stringBuffer19;
        new StringBuffer("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        rotorI = stringBuffer;
        new StringBuffer("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        rotorII = stringBuffer2;
        new StringBuffer("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        rotorIII = stringBuffer3;
        new StringBuffer("ESOVPZJAYQUIRHXLNFTGKDCMWB");
        rotorIV = stringBuffer4;
        new StringBuffer("VZBRGITYUPSDNHLXAWMJQOFECK");
        rotorV = stringBuffer5;
        new StringBuffer("JPGVOUMFYQBENHZRDKASXLICTW");
        rotorVI = stringBuffer6;
        new StringBuffer("NZJHGRCXMYSWBOUFAIVLPEKQDT");
        rotorVII = stringBuffer7;
        new StringBuffer("JPGVOUMFYQBENHZRDKASXLICTW");
        rotorVIII = stringBuffer8;
        new StringBuffer("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        reflectorB = stringBuffer9;
        new StringBuffer("FVPJIAOYEDRZXWGCTKUQSBNMHL");
        reflectorC = stringBuffer10;
        new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        reflector0 = stringBuffer11;
        StringBuffer[] stringBufferArr = new StringBuffer[8];
        new StringBuffer("Q");
        stringBufferArr[0] = stringBuffer12;
        StringBuffer[] stringBufferArr2 = stringBufferArr;
        new StringBuffer("E");
        stringBufferArr2[1] = stringBuffer13;
        StringBuffer[] stringBufferArr3 = stringBufferArr2;
        new StringBuffer("V");
        stringBufferArr3[2] = stringBuffer14;
        StringBuffer[] stringBufferArr4 = stringBufferArr3;
        new StringBuffer("J");
        stringBufferArr4[3] = stringBuffer15;
        StringBuffer[] stringBufferArr5 = stringBufferArr4;
        new StringBuffer("Z");
        stringBufferArr5[4] = stringBuffer16;
        StringBuffer[] stringBufferArr6 = stringBufferArr5;
        new StringBuffer("Z");
        stringBufferArr6[5] = stringBuffer17;
        StringBuffer[] stringBufferArr7 = stringBufferArr6;
        new StringBuffer("Z");
        stringBufferArr7[6] = stringBuffer18;
        StringBuffer[] stringBufferArr8 = stringBufferArr7;
        new StringBuffer("Z");
        stringBufferArr8[7] = stringBuffer19;
        notches = stringBufferArr8;
    }

    public KodularEnigma(String str, String str2, String str3, String str4) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        String str5 = str;
        String str6 = str2;
        new StringBuffer(reflector0.toString());
        this.firstRotorT = stringBuffer;
        new StringBuffer(reflector0.toString());
        this.secondRotorT = stringBuffer2;
        new StringBuffer(reflector0.toString());
        this.thirdRotorT = stringBuffer3;
        this.firstRotor = getValue(str5)[0];
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = getValue(str5)[1];
        this.secondRotor = getValue(str6)[0];
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = getValue(str6)[1];
        this.thirdRotor = getValue(str3)[0];
        this.reflector = getValue(str4)[0];
    }

    public void setFirstRotor(String str) {
        String str2 = str;
        this.firstRotor = getValue(str2)[0];
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = getValue(str2)[1];
    }

    public void setSecondRotor(String str) {
        String str2 = str;
        this.secondRotor = getValue(str2)[0];
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = getValue(str2)[1];
    }

    public void setThirdRotor(String str) {
        this.thirdRotor = getValue(str)[0];
    }

    public void initialSettings(String str, String str2, String str3) {
        int indexOf = this.firstRotorT.toString().indexOf(str);
        StringBuffer append = this.firstRotorT.append(this.firstRotorT.substring(0, indexOf));
        StringBuffer delete = this.firstRotorT.delete(0, indexOf);
        int indexOf2 = this.secondRotorT.toString().indexOf(str2);
        StringBuffer append2 = this.secondRotorT.append(this.secondRotorT.substring(0, indexOf2));
        StringBuffer delete2 = this.secondRotorT.delete(0, indexOf2);
        int indexOf3 = this.thirdRotorT.toString().indexOf(str3);
        StringBuffer append3 = this.thirdRotorT.append(this.thirdRotorT.substring(0, indexOf3));
        StringBuffer delete3 = this.thirdRotorT.delete(0, indexOf3);
    }

    public void setPlugBoard(char c, char c2) {
        char c3 = c;
        char c4 = c2;
        for (int i = 0; i < this.plugBoard.length; i++) {
            if (this.plugBoard[i] == c3) {
                this.plugBoard[i] = c4;
            } else if (this.plugBoard[i] == c4) {
                this.plugBoard[i] = c3;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setPlugBoard(java.lang.String r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            java.util.StringTokenizer r4 = new java.util.StringTokenizer
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            java.lang.String r7 = " "
            r5.<init>(r6, r7)
            r3 = r4
        L_0x000f:
            r4 = r3
            boolean r4 = r4.hasMoreTokens()
            if (r4 == 0) goto L_0x0054
            r4 = r3
            java.lang.String r4 = r4.nextToken()
            r8 = r4
            r4 = r8
            r5 = r8
            r2 = r5
            int r4 = r4.length()
            r5 = 2
            if (r4 == r5) goto L_0x0029
            r4 = 0
            r0 = r4
        L_0x0028:
            return r0
        L_0x0029:
            r4 = r2
            r5 = 0
            char r4 = r4.charAt(r5)
            r5 = 90
            if (r4 > r5) goto L_0x0051
            r4 = r2
            r5 = 0
            char r4 = r4.charAt(r5)
            r5 = 65
            if (r4 < r5) goto L_0x0051
            r4 = r2
            r5 = 1
            char r4 = r4.charAt(r5)
            r5 = 90
            if (r4 > r5) goto L_0x0051
            r4 = r2
            r5 = 1
            char r4 = r4.charAt(r5)
            r5 = 65
            if (r4 >= r5) goto L_0x000f
        L_0x0051:
            r4 = 0
            r0 = r4
            goto L_0x0028
        L_0x0054:
            java.util.StringTokenizer r4 = new java.util.StringTokenizer
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            java.lang.String r7 = " "
            r5.<init>(r6, r7)
            r1 = r4
        L_0x0061:
            r4 = r1
            boolean r4 = r4.hasMoreTokens()
            if (r4 == 0) goto L_0x008c
            r4 = r1
            java.lang.String r4 = r4.nextToken()
            r8 = r4
            r4 = r8
            r5 = r8
            r2 = r5
            int r4 = r4.length()
            r5 = 2
            if (r4 != r5) goto L_0x0089
            r4 = r0
            r5 = r2
            r6 = 0
            char r5 = r5.charAt(r6)
            r6 = r2
            r7 = 1
            char r6 = r6.charAt(r7)
            r4.setPlugBoard(r5, r6)
            goto L_0x0061
        L_0x0089:
            r4 = 0
            r0 = r4
            goto L_0x0028
        L_0x008c:
            r4 = 1
            r0 = r4
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.crypt.KodularEnigma.setPlugBoard(java.lang.String):boolean");
    }

    public StringBuffer[] getValue(String str) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        StringBuffer stringBuffer4;
        StringBuffer stringBuffer5;
        String str2 = str;
        StringBuffer[] stringBufferArr = new StringBuffer[2];
        if (str2.equals("RotorI") || str2.equals("1")) {
            stringBufferArr[0] = rotorI;
            stringBufferArr[1] = notches[0];
            return stringBufferArr;
        } else if (str2.equals("RotorII") || str2.equals("2")) {
            stringBufferArr[0] = rotorII;
            stringBufferArr[1] = notches[1];
            return stringBufferArr;
        } else if (str2.equals("RotorIII") || str2.equals("3")) {
            stringBufferArr[0] = rotorIII;
            stringBufferArr[1] = notches[2];
            return stringBufferArr;
        } else if (str2.equals("RotorIV") || str2.equals("4")) {
            stringBufferArr[0] = rotorIV;
            stringBufferArr[1] = notches[3];
            return stringBufferArr;
        } else if (str2.equals("RotorV") || str2.equals(Constants.WIRE_PROTOCOL_VERSION)) {
            stringBufferArr[0] = rotorV;
            stringBufferArr[1] = notches[4];
            return stringBufferArr;
        } else if (str2.equals("RotorVI") || str2.equals("6")) {
            stringBufferArr[0] = rotorVI;
            stringBufferArr[1] = notches[5];
            return stringBufferArr;
        } else if (str2.equals("RotorVII") || str2.equals("7")) {
            stringBufferArr[0] = rotorVII;
            stringBufferArr[1] = notches[6];
            return stringBufferArr;
        } else if (str2.equals("RotorVIII") || str2.equals("8")) {
            stringBufferArr[0] = rotorVIII;
            stringBufferArr[1] = notches[7];
            return stringBufferArr;
        } else if (str2.equals("ReflectorB") || str2.equals("B")) {
            stringBufferArr[0] = reflectorB;
            new StringBuffer("");
            stringBufferArr[1] = stringBuffer;
            return stringBufferArr;
        } else if (str2.equals("ReflectorC") || str2.equals("C")) {
            stringBufferArr[0] = reflectorC;
            new StringBuffer("");
            stringBufferArr[1] = stringBuffer2;
            return stringBufferArr;
        } else if (str2.equals("No Reflector") || str2.equals("0")) {
            stringBufferArr[0] = reflector0;
            new StringBuffer("");
            stringBufferArr[1] = stringBuffer3;
            return stringBufferArr;
        } else {
            StringBuffer[] stringBufferArr2 = new StringBuffer[2];
            new StringBuffer("ERROR");
            stringBufferArr2[0] = stringBuffer4;
            StringBuffer[] stringBufferArr3 = stringBufferArr2;
            new StringBuffer("");
            stringBufferArr3[1] = stringBuffer5;
            return stringBufferArr3;
        }
    }

    public char rotorOne(char c) {
        return this.firstRotor.charAt(this.firstRotorT.toString().indexOf(c));
    }

    public char rotorTwo(char c) {
        return this.secondRotor.charAt(this.secondRotorT.toString().indexOf(c));
    }

    public char rotorThree(char c) {
        return this.thirdRotor.charAt(this.thirdRotorT.toString().indexOf(c));
    }

    public char reflector(char c) {
        return this.reflector.charAt(c - 'A');
    }

    public char IrotorOne(char c) {
        return this.firstRotorT.charAt(this.firstRotor.toString().indexOf(c));
    }

    public char IrotorTwo(char c) {
        return this.secondRotorT.charAt(this.secondRotor.toString().indexOf(c));
    }

    public char IrotorThree(char c) {
        return this.thirdRotorT.charAt(this.thirdRotor.toString().indexOf(c));
    }

    public void rotate() {
        StringBuffer stringBuffer;
        StringBuilder sb;
        StringBuffer stringBuffer2;
        StringBuilder sb2;
        new StringBuilder();
        new StringBuffer(sb.append(this.firstRotorT.charAt(0)).toString());
        StringBuffer stringBuffer3 = stringBuffer;
        new StringBuilder();
        new StringBuffer(sb2.append(this.secondRotorT.charAt(0)).toString());
        StringBuffer stringBuffer4 = stringBuffer2;
        StringBuffer append = this.firstRotorT.append(this.firstRotorT.charAt(0));
        StringBuffer delete = this.firstRotorT.delete(0, 1);
        if (stringBuffer3.toString().equals(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.toString())) {
            StringBuffer append2 = this.secondRotorT.append(this.secondRotorT.charAt(0));
            StringBuffer delete2 = this.secondRotorT.delete(0, 1);
            if (stringBuffer4.toString().equals(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.toString())) {
                StringBuffer append3 = this.thirdRotorT.append(this.thirdRotorT.charAt(0));
                StringBuffer delete3 = this.thirdRotorT.delete(0, 1);
            }
        }
    }

    public char plugBoard(char c) {
        return this.plugBoard[c - 'A'];
    }

    public char getFRSetting() {
        return this.firstRotorT.charAt(0);
    }

    public char getSRSetting() {
        return this.secondRotorT.charAt(0);
    }

    public char getTRSetting() {
        return this.thirdRotorT.charAt(0);
    }

    public static String encrypt(String str, int i, int i2, int i3, String str2, String str3) {
        KodularEnigma kodularEnigma;
        StringBuilder sb;
        String sb2;
        StringBuilder sb3;
        String str4 = str;
        String str5 = str3;
        new KodularEnigma(String.valueOf(i), String.valueOf(i2), String.valueOf(i3), str2);
        KodularEnigma kodularEnigma2 = kodularEnigma;
        if (str5 != "") {
            boolean plugBoard2 = kodularEnigma2.setPlugBoard(str5);
        }
        KodularEnigma kodularEnigma3 = kodularEnigma2;
        String upperCase = str4.toUpperCase();
        String str6 = "";
        for (int i4 = 0; i4 < upperCase.length(); i4++) {
            char charAt = upperCase.charAt(i4);
            char c = charAt;
            if (charAt <= 'Z' && c >= 'A') {
                kodularEnigma3.rotate();
                char plugBoard3 = kodularEnigma3.plugBoard(kodularEnigma3.IrotorOne(kodularEnigma3.IrotorTwo(kodularEnigma3.IrotorThree(kodularEnigma3.reflector(kodularEnigma3.rotorThree(kodularEnigma3.rotorTwo(kodularEnigma3.rotorOne(kodularEnigma3.plugBoard(c)))))))));
                new StringBuilder();
                sb2 = sb3.append(str6).append(plugBoard3).toString();
            } else if (c != ' ') {
                return null;
            } else {
                new StringBuilder();
                sb2 = sb.append(str6).append(c).toString();
            }
            str6 = sb2;
        }
        return str6;
    }

    public static boolean pbParser(String str) {
        String str2 = str;
        if (str2.length() <= 0 || str2.equals((Object) null) || str2 == null) {
            return true;
        }
        int i = 0;
        while (i < str2.length() - 1) {
            if (str2.charAt(i) > 'Z' || str2.charAt(i) < 'A') {
                i++;
            } else if (str2.substring(i + 1).indexOf(str2.charAt(i)) != -1) {
                return false;
            }
            i++;
        }
        return true;
    }
}
