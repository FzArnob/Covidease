package com.google.appinventor.components.runtime.repackaged.org.json.zip;

import com.google.appinventor.components.runtime.repackaged.org.json.JSONArray;
import com.google.appinventor.components.runtime.repackaged.org.json.JSONException;
import com.google.appinventor.components.runtime.repackaged.org.json.JSONObject;
import com.google.appinventor.components.runtime.repackaged.org.json.Kim;
import java.io.UnsupportedEncodingException;

public class Decompressor extends JSONzip {
    BitReader bitreader;

    public Decompressor(BitReader bitreader2) {
        this.bitreader = bitreader2;
    }

    private boolean bit() throws JSONException {
        Throwable th;
        try {
            return this.bitreader.bit();
        } catch (Throwable th2) {
            Throwable e = th2;
            Throwable th3 = th;
            new JSONException(e);
            throw th3;
        }
    }

    private Object getAndTick(Keep keep, BitReader bitReader) throws JSONException {
        Throwable th;
        Throwable th2;
        Keep keep2 = keep;
        BitReader bitreader2 = bitReader;
        try {
            int integer = bitreader2.read(keep2.bitsize());
            Object value = keep2.value(integer);
            if (integer >= keep2.length) {
                Throwable th3 = th2;
                new JSONException("Deep error.");
                throw th3;
            }
            keep2.tick(integer);
            return value;
        } catch (Throwable th4) {
            Throwable e = th4;
            Throwable th5 = th;
            new JSONException(e);
            throw th5;
        }
    }

    public boolean pad(int factor) throws JSONException {
        Throwable th;
        try {
            return this.bitreader.pad(factor);
        } catch (Throwable th2) {
            Throwable e = th2;
            Throwable th3 = th;
            new JSONException(e);
            throw th3;
        }
    }

    private int read(int width) throws JSONException {
        Throwable th;
        try {
            return this.bitreader.read(width);
        } catch (Throwable th2) {
            Throwable e = th2;
            Throwable th3 = th;
            new JSONException(e);
            throw th3;
        }
    }

    private JSONArray readArray(boolean z) throws JSONException {
        JSONArray jSONArray;
        boolean stringy = z;
        new JSONArray();
        JSONArray jsonarray = jSONArray;
        JSONArray put = jsonarray.put(stringy ? readString() : readValue());
        while (true) {
            if (bit()) {
                JSONArray put2 = jsonarray.put(stringy ? readString() : readValue());
            } else if (!bit()) {
                return jsonarray;
            } else {
                JSONArray put3 = jsonarray.put(stringy ? readValue() : readString());
            }
        }
    }

    private Object readJSON() throws JSONException {
        Object obj;
        Object obj2;
        switch (read(3)) {
            case 0:
                new JSONObject();
                return obj2;
            case 1:
                new JSONArray();
                return obj;
            case 2:
                return Boolean.TRUE;
            case 3:
                return Boolean.FALSE;
            case 5:
                return readObject();
            case 6:
                return readArray(true);
            case 7:
                return readArray(false);
            default:
                return JSONObject.NULL;
        }
    }

    private String readName() throws JSONException {
        Kim kim;
        byte[] bytes = new byte[65536];
        int length = 0;
        if (bit()) {
            return getAndTick(this.namekeep, this.bitreader).toString();
        }
        while (true) {
            int c = this.namehuff.read(this.bitreader);
            if (c == 256) {
                break;
            }
            bytes[length] = (byte) c;
            length++;
        }
        if (length == 0) {
            return "";
        }
        new Kim(bytes, length);
        Kim kim2 = kim;
        this.namekeep.register(kim2);
        return kim2.toString();
    }

    private JSONObject readObject() throws JSONException {
        JSONObject jSONObject;
        new JSONObject();
        JSONObject jsonobject = jSONObject;
        do {
            JSONObject put = jsonobject.put(readName(), !bit() ? readString() : readValue());
        } while (bit());
        return jsonobject;
    }

    private String readString() throws JSONException {
        Kim kim;
        Kim kim2;
        Kim kim3;
        int thru = 0;
        int previousFrom = -1;
        int previousThru = 0;
        if (bit()) {
            return getAndTick(this.stringkeep, this.bitreader).toString();
        }
        byte[] bytes = new byte[65536];
        boolean one = bit();
        this.substringkeep.reserve();
        while (true) {
            if (one) {
                int from = thru;
                thru = ((Kim) getAndTick(this.substringkeep, this.bitreader)).copy(bytes, from);
                if (previousFrom != -1) {
                    new Kim(bytes, previousFrom, previousThru + 1);
                    this.substringkeep.registerOne(kim3);
                }
                previousFrom = from;
                previousThru = thru;
                one = bit();
            } else {
                while (true) {
                    int c = this.substringhuff.read(this.bitreader);
                    if (c == 256) {
                        break;
                    }
                    bytes[thru] = (byte) c;
                    thru++;
                    if (previousFrom != -1) {
                        new Kim(bytes, previousFrom, previousThru + 1);
                        this.substringkeep.registerOne(kim2);
                        previousFrom = -1;
                    }
                }
                if (!bit()) {
                    break;
                }
                one = true;
            }
        }
        if (thru == 0) {
            return "";
        }
        new Kim(bytes, thru);
        Kim kim4 = kim;
        this.stringkeep.register(kim4);
        this.substringkeep.registerMany(kim4);
        return kim4.toString();
    }

    private Object readValue() throws JSONException {
        Throwable th;
        String str;
        int i;
        Throwable th2;
        switch (read(2)) {
            case 0:
                Integer num = r11;
                if (!bit()) {
                    i = 4;
                } else {
                    i = !bit() ? 7 : 14;
                }
                Integer num2 = new Integer(read(i));
                return num;
            case 1:
                byte[] bytes = new byte[256];
                int length = 0;
                while (true) {
                    int c = read(4);
                    if (c == endOfNumber) {
                        try {
                            new String(bytes, 0, length, "US-ASCII");
                            Object value = JSONObject.stringToValue(str);
                            this.values.register(value);
                            return value;
                        } catch (UnsupportedEncodingException e) {
                            UnsupportedEncodingException e2 = e;
                            Throwable th3 = th;
                            new JSONException((Throwable) e2);
                            throw th3;
                        }
                    } else {
                        bytes[length] = bcd[c];
                        length++;
                    }
                }
            case 2:
                return getAndTick(this.values, this.bitreader);
            case 3:
                return readJSON();
            default:
                Throwable th4 = th2;
                new JSONException("Impossible.");
                throw th4;
        }
    }

    public Object unzip() throws JSONException {
        begin();
        return readJSON();
    }
}
