package com.firebase.client.utilities;

import com.firebase.client.FirebaseException;
import com.firebase.client.core.Path;
import com.firebase.client.core.RepoInfo;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;

public class Utilities {
    public Utilities() {
    }

    public static ParsedUrl parseUrl(String url) throws FirebaseException {
        Throwable th;
        Throwable th2;
        URI uri;
        Path path;
        RepoInfo repoInfo;
        ParsedUrl parsedUrl;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Throwable th3;
        String original = url;
        try {
            int schemeOffset = original.indexOf("//");
            if (schemeOffset == -1) {
                Throwable th4 = th3;
                new URISyntaxException(original, "Invalid scheme specified");
                throw th4;
            }
            int pathOffset = original.substring(schemeOffset + 2).indexOf("/");
            if (pathOffset != -1) {
                int pathOffset2 = pathOffset + schemeOffset + 2;
                String[] pathSegments = original.substring(pathOffset2).split("/");
                new StringBuilder();
                StringBuilder builder = sb2;
                for (int i = 0; i < pathSegments.length; i++) {
                    if (!pathSegments[i].equals("")) {
                        StringBuilder append = builder.append("/");
                        StringBuilder append2 = builder.append(URLEncoder.encode(pathSegments[i], "UTF-8"));
                    }
                }
                new StringBuilder();
                original = sb3.append(original.substring(0, pathOffset2)).append(builder.toString()).toString();
            }
            new URI(original);
            URI uri2 = uri;
            String pathString = uri2.getPath().replace("+", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            Validation.validateRootPathString(pathString);
            new Path(pathString);
            Path path2 = path;
            String scheme = uri2.getScheme();
            new RepoInfo();
            RepoInfo repoInfo2 = repoInfo;
            repoInfo2.host = uri2.getHost().toLowerCase();
            int port = uri2.getPort();
            if (port != -1) {
                repoInfo2.secure = scheme.equals("https");
                new StringBuilder();
                RepoInfo repoInfo3 = repoInfo2;
                repoInfo3.host = sb.append(repoInfo3.host).append(":").append(port).toString();
            } else {
                repoInfo2.secure = true;
            }
            repoInfo2.namespace = repoInfo2.host.split("\\.")[0].toLowerCase();
            repoInfo2.internalHost = repoInfo2.host;
            new ParsedUrl();
            ParsedUrl parsedUrl2 = parsedUrl;
            parsedUrl2.path = path2;
            parsedUrl2.repoInfo = repoInfo2;
            return parsedUrl2;
        } catch (URISyntaxException e) {
            URISyntaxException e2 = e;
            Throwable th5 = th2;
            new FirebaseException("Invalid Firebase url specified", e2);
            throw th5;
        } catch (UnsupportedEncodingException e3) {
            UnsupportedEncodingException e4 = e3;
            Throwable th6 = th;
            new FirebaseException("Failed to URLEncode the path", e4);
            throw th6;
        }
    }

    public static String[] splitIntoFrames(String str, int i) {
        ArrayList arrayList;
        String src = str;
        int maxFrameSize = i;
        if (src.length() <= maxFrameSize) {
            return new String[]{src};
        }
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= src.length()) {
                return (String[]) arrayList2.toArray(new String[arrayList2.size()]);
            }
            boolean add = arrayList2.add(src.substring(i3, Math.min(i3 + maxFrameSize, src.length())));
            i2 = i3 + maxFrameSize;
        }
    }

    public static String sha1HexDigest(String str) {
        Throwable th;
        Throwable th2;
        String input = str;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(input.getBytes("UTF-8"));
            return Base64.encodeBytes(md.digest());
        } catch (NoSuchAlgorithmException e) {
            NoSuchAlgorithmException e2 = e;
            Throwable th3 = th2;
            new RuntimeException("Missing SHA-1 MessageDigest provider.", e2);
            throw th3;
        } catch (UnsupportedEncodingException e3) {
            UnsupportedEncodingException unsupportedEncodingException = e3;
            Throwable th4 = th;
            new RuntimeException("UTF-8 encoding is required for Firebase to run!");
            throw th4;
        }
    }

    public static String stringHashV2Representation(String str) {
        StringBuilder sb;
        String value = str;
        String escaped = value;
        if (value.indexOf(92) != -1) {
            escaped = escaped.replace("\\", "\\\\");
        }
        if (value.indexOf(34) != -1) {
            escaped = escaped.replace("\"", "\\\"");
        }
        new StringBuilder();
        return sb.append('\"').append(escaped).append('\"').toString();
    }

    public static String doubleToHashString(double value) {
        StringBuilder sb;
        new StringBuilder(16);
        StringBuilder sb2 = sb;
        byte[] bytes = new byte[8];
        ByteBuffer putDouble = ByteBuffer.wrap(bytes).putDouble(value);
        for (int i = 0; i < 8; i++) {
            StringBuilder append = sb2.append(String.format("%02x", new Object[]{Byte.valueOf(bytes[i])}));
        }
        return sb2.toString();
    }

    public static Integer tryParseInt(String str) {
        String num = str;
        if (num.length() > 11 || num.length() == 0) {
            return null;
        }
        int i = 0;
        boolean negative = false;
        if (num.charAt(0) == '-') {
            if (num.length() == 1) {
                return null;
            }
            negative = true;
            i = 1;
        }
        long number = 0;
        while (i < num.length()) {
            char c = num.charAt(i);
            if (c < '0' || c > '9') {
                return null;
            }
            number = (number * 10) + ((long) (c - '0'));
            i++;
        }
        if (negative) {
            if ((-number) < -2147483648L) {
                return null;
            }
            return Integer.valueOf((int) (-number));
        } else if (number > 2147483647L) {
            return null;
        } else {
            return Integer.valueOf((int) number);
        }
    }

    public static int compareInts(int i, int i2) {
        int i3 = i;
        int j = i2;
        if (i3 < j) {
            return -1;
        }
        if (i3 == j) {
            return 0;
        }
        return 1;
    }

    public static int compareLongs(long j, long j2) {
        long i = j;
        long j3 = j2;
        if (i < j3) {
            return -1;
        }
        if (i == j3) {
            return 0;
        }
        return 1;
    }

    public static <C> C castOrNull(Object obj, Class<C> clazz) {
        Object o = obj;
        if (clazz.isAssignableFrom(o.getClass())) {
            return o;
        }
        return null;
    }

    public static <C> C getOrNull(Object obj, String str, Class<C> cls) {
        Object o = obj;
        String key = str;
        Class<C> clazz = cls;
        if (o == null) {
            return null;
        }
        Object result = ((Map) castOrNull(o, Map.class)).get(key);
        if (result != null) {
            return castOrNull(result, clazz);
        }
        return null;
    }

    public static Long longFromObject(Object obj) {
        Object o = obj;
        if (o instanceof Integer) {
            return Long.valueOf((long) ((Integer) o).intValue());
        }
        if (o instanceof Long) {
            return (Long) o;
        }
        return null;
    }

    public static void hardAssert(boolean condition) {
        hardAssert(condition, "");
    }

    public static void hardAssert(boolean condition, String str) {
        Throwable th;
        StringBuilder sb;
        String message = str;
        if (!condition) {
            Throwable th2 = th;
            new StringBuilder();
            new AssertionError(sb.append("hardAssert failed: ").append(message).toString());
            throw th2;
        }
    }
}
