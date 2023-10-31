package gnu.text;

import java.util.HashMap;
import java.util.Vector;

public class Options {
    public static final int BOOLEAN_OPTION = 1;
    public static final int STRING_OPTION = 2;
    public static final String UNKNOWN = "unknown option name";
    OptionInfo first;
    HashMap<String, OptionInfo> infoTable;
    OptionInfo last;
    Options previous;
    HashMap<String, Object> valueTable;

    public Options() {
    }

    public Options(Options previous2) {
        this.previous = previous2;
    }

    public OptionInfo add(String key, int kind, String documentation) {
        return add(key, kind, (Object) null, documentation);
    }

    public OptionInfo add(String str, int i, Object obj, String str2) {
        Throwable th;
        StringBuilder sb;
        OptionInfo optionInfo;
        HashMap<String, OptionInfo> hashMap;
        String key = str;
        int kind = i;
        Object defaultValue = obj;
        String documentation = str2;
        if (this.infoTable == null) {
            new HashMap<>();
            this.infoTable = hashMap;
        } else if (this.infoTable.get(key) != null) {
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("duplicate option key: ").append(key).toString());
            throw th2;
        }
        new OptionInfo();
        OptionInfo info = optionInfo;
        info.key = key;
        info.kind = kind;
        info.defaultValue = defaultValue;
        info.documentation = documentation;
        if (this.first == null) {
            this.first = info;
        } else {
            this.last.next = info;
        }
        this.last = info;
        OptionInfo put = this.infoTable.put(key, info);
        return info;
    }

    static Object valueOf(OptionInfo info, String str) {
        String argument = str;
        if ((info.kind & 1) == 0) {
            return argument;
        }
        if (argument == null || argument.equals("1") || argument.equals("on") || argument.equals("yes") || argument.equals("true")) {
            return Boolean.TRUE;
        }
        if (argument.equals("0") || argument.equals("off") || argument.equals("no") || argument.equals("false")) {
            return Boolean.FALSE;
        }
        return null;
    }

    private void error(String str, SourceMessages sourceMessages) {
        Throwable th;
        String message = str;
        SourceMessages messages = sourceMessages;
        if (messages == null) {
            Throwable th2 = th;
            new RuntimeException(message);
            throw th2;
        }
        messages.error('e', message);
    }

    public void set(String key, Object value) {
        set(key, value, (SourceMessages) null);
    }

    public void set(String str, Object obj, SourceMessages sourceMessages) {
        HashMap<String, Object> hashMap;
        StringBuilder sb;
        StringBuilder sb2;
        String key = str;
        Object value = obj;
        SourceMessages messages = sourceMessages;
        OptionInfo info = getInfo(key);
        if (info == null) {
            new StringBuilder();
            error(sb2.append("invalid option key: ").append(key).toString(), messages);
            return;
        }
        if ((info.kind & 1) != 0) {
            if (value instanceof String) {
                value = valueOf(info, (String) value);
            }
            if (!(value instanceof Boolean)) {
                new StringBuilder();
                error(sb.append("value for option ").append(key).append(" must be boolean or yes/no/true/false/on/off/1/0").toString(), messages);
                return;
            }
        } else if (value == null) {
            value = "";
        }
        if (this.valueTable == null) {
            new HashMap<>();
            this.valueTable = hashMap;
        }
        Object put = this.valueTable.put(key, value);
    }

    public void reset(String str, Object obj) {
        HashMap<String, Object> hashMap;
        String key = str;
        Object oldValue = obj;
        if (this.valueTable == null) {
            new HashMap<>();
            this.valueTable = hashMap;
        }
        if (oldValue == null) {
            Object remove = this.valueTable.remove(key);
        } else {
            Object put = this.valueTable.put(key, oldValue);
        }
    }

    public String set(String str, String str2) {
        HashMap<String, Object> hashMap;
        StringBuilder sb;
        String key = str;
        String argument = str2;
        OptionInfo info = getInfo(key);
        if (info == null) {
            return UNKNOWN;
        }
        Object value = valueOf(info, argument);
        if (value != null || (info.kind & 1) == 0) {
            if (this.valueTable == null) {
                new HashMap<>();
                this.valueTable = hashMap;
            }
            Object put = this.valueTable.put(key, value);
            return null;
        }
        new StringBuilder();
        return sb.append("value of option ").append(key).append(" must be yes/no/true/false/on/off/1/0").toString();
    }

    public OptionInfo getInfo(String str) {
        String key = str;
        OptionInfo info = this.infoTable == null ? null : this.infoTable.get(key);
        if (info == null && this.previous != null) {
            info = this.previous.getInfo(key);
        }
        return info;
    }

    public Object get(String str, Object obj) {
        Throwable th;
        StringBuilder sb;
        String key = str;
        Object defaultValue = obj;
        OptionInfo info = getInfo(key);
        if (info != null) {
            return get(info, defaultValue);
        }
        Throwable th2 = th;
        new StringBuilder();
        new RuntimeException(sb.append("invalid option key: ").append(key).toString());
        throw th2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        if (r4.defaultValue == null) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        r2 = r4.defaultValue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object get(gnu.text.Options.OptionInfo r9, java.lang.Object r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r6 = r0
            r3 = r6
        L_0x0005:
            r6 = r3
            if (r6 == 0) goto L_0x003e
            r6 = r1
            r4 = r6
        L_0x000a:
            r6 = r3
            java.util.HashMap<java.lang.String, java.lang.Object> r6 = r6.valueTable
            if (r6 != 0) goto L_0x0017
            r6 = 0
        L_0x0010:
            r5 = r6
            r6 = r5
            if (r6 == 0) goto L_0x0022
            r6 = r5
            r0 = r6
        L_0x0016:
            return r0
        L_0x0017:
            r6 = r3
            java.util.HashMap<java.lang.String, java.lang.Object> r6 = r6.valueTable
            r7 = r4
            java.lang.String r7 = r7.key
            java.lang.Object r6 = r6.get(r7)
            goto L_0x0010
        L_0x0022:
            r6 = r4
            java.lang.Object r6 = r6.defaultValue
            boolean r6 = r6 instanceof gnu.text.Options.OptionInfo
            if (r6 == 0) goto L_0x0030
            r6 = r4
            java.lang.Object r6 = r6.defaultValue
            gnu.text.Options$OptionInfo r6 = (gnu.text.Options.OptionInfo) r6
            r4 = r6
            goto L_0x000a
        L_0x0030:
            r6 = r4
            java.lang.Object r6 = r6.defaultValue
            if (r6 == 0) goto L_0x0039
            r6 = r4
            java.lang.Object r6 = r6.defaultValue
            r2 = r6
        L_0x0039:
            r6 = r3
            gnu.text.Options r6 = r6.previous
            r3 = r6
            goto L_0x0005
        L_0x003e:
            r6 = r2
            r0 = r6
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.Options.get(gnu.text.Options$OptionInfo, java.lang.Object):java.lang.Object");
    }

    public Object get(OptionInfo key) {
        return get(key, (Object) null);
    }

    public Object getLocal(String key) {
        return this.valueTable == null ? null : this.valueTable.get(key);
    }

    public boolean getBoolean(String key) {
        return ((Boolean) get(key, (Object) Boolean.FALSE)).booleanValue();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return ((Boolean) get(key, (Object) defaultValue ? Boolean.TRUE : Boolean.FALSE)).booleanValue();
    }

    public boolean getBoolean(OptionInfo key, boolean defaultValue) {
        return ((Boolean) get(key, (Object) defaultValue ? Boolean.TRUE : Boolean.FALSE)).booleanValue();
    }

    public boolean getBoolean(OptionInfo key) {
        Object value = get(key, (Object) null);
        return value == null ? false : ((Boolean) value).booleanValue();
    }

    public void pushOptionValues(Vector vector) {
        Vector options = vector;
        int len = options.size();
        int i = 0;
        while (i < len) {
            int i2 = i;
            int i3 = i + 1;
            String key = (String) options.elementAt(i2);
            Object newValue = options.elementAt(i3);
            int i4 = i3;
            int i5 = i3 + 1;
            options.setElementAt(newValue, i4);
            int i6 = i5;
            i = i5 + 1;
            set(key, options.elementAt(i6));
        }
    }

    public void popOptionValues(Vector vector) {
        Vector options = vector;
        int i = options.size();
        while (true) {
            i -= 3;
            if (i >= 0) {
                String key = (String) options.elementAt(i);
                Object oldValue = options.elementAt(i + 1);
                options.setElementAt((Object) null, i + 1);
                reset(key, oldValue);
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<java.lang.String> keys() {
        /*
            r8 = this;
            r0 = r8
            java.util.ArrayList r5 = new java.util.ArrayList
            r7 = r5
            r5 = r7
            r6 = r7
            r6.<init>()
            r1 = r5
            r5 = r0
            r2 = r5
        L_0x000c:
            r5 = r2
            if (r5 == 0) goto L_0x0043
            r5 = r2
            java.util.HashMap<java.lang.String, gnu.text.Options$OptionInfo> r5 = r5.infoTable
            if (r5 == 0) goto L_0x003e
            r5 = r2
            java.util.HashMap<java.lang.String, gnu.text.Options$OptionInfo> r5 = r5.infoTable
            java.util.Set r5 = r5.keySet()
            java.util.Iterator r5 = r5.iterator()
            r3 = r5
        L_0x0020:
            r5 = r3
            boolean r5 = r5.hasNext()
            if (r5 == 0) goto L_0x003e
            r5 = r3
            java.lang.Object r5 = r5.next()
            java.lang.String r5 = (java.lang.String) r5
            r4 = r5
            r5 = r1
            r6 = r4
            boolean r5 = r5.contains(r6)
            if (r5 != 0) goto L_0x003d
            r5 = r1
            r6 = r4
            boolean r5 = r5.add(r6)
        L_0x003d:
            goto L_0x0020
        L_0x003e:
            r5 = r2
            gnu.text.Options r5 = r5.previous
            r2 = r5
            goto L_0x000c
        L_0x0043:
            r5 = r1
            r0 = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.Options.keys():java.util.ArrayList");
    }

    public String getDoc(String str) {
        String key = str;
        OptionInfo info = getInfo(key);
        if (key == null) {
            return null;
        }
        return info.documentation;
    }

    public static final class OptionInfo {
        Object defaultValue;
        String documentation;
        String key;
        int kind;
        OptionInfo next;

        public OptionInfo() {
        }
    }
}
