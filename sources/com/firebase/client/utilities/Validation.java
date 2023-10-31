package com.firebase.client.utilities;

import com.firebase.client.FirebaseException;
import com.firebase.client.core.Path;
import com.firebase.client.core.ServerValues;
import com.firebase.client.core.ValidationPath;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.NodeUtilities;
import com.firebase.client.snapshot.PriorityUtilities;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Validation {
    private static final Pattern INVALID_KEY_REGEX = Pattern.compile("[\\[\\]\\.#\\$\\/\\u0000-\\u001F\\u007F]");
    private static final Pattern INVALID_PATH_REGEX = Pattern.compile("[\\[\\]\\.#$]");

    public Validation() {
    }

    private static boolean isValidPathString(String pathString) {
        return !INVALID_PATH_REGEX.matcher(pathString).find();
    }

    public static void validatePathString(String str) throws FirebaseException {
        Throwable th;
        StringBuilder sb;
        String pathString = str;
        if (!isValidPathString(pathString)) {
            Throwable th2 = th;
            new StringBuilder();
            new FirebaseException(sb.append("Invalid Firebase path: ").append(pathString).append(". Firebase paths must not contain '.', '#', '$', '[', or ']'").toString());
            throw th2;
        }
    }

    public static void validateRootPathString(String str) throws FirebaseException {
        String pathString = str;
        if (pathString.startsWith(".info")) {
            validatePathString(pathString.substring(5));
        } else if (pathString.startsWith("/.info")) {
            validatePathString(pathString.substring(6));
        } else {
            validatePathString(pathString);
        }
    }

    private static boolean isWritableKey(String str) {
        String key = str;
        return key != null && key.length() > 0 && (key.equals(".value") || key.equals(".priority") || (!key.startsWith(".") && !INVALID_KEY_REGEX.matcher(key).find()));
    }

    private static boolean isValidKey(String str) {
        String key = str;
        return key.equals(".info") || !INVALID_KEY_REGEX.matcher(key).find();
    }

    public static void validateNullableKey(String str) throws FirebaseException {
        Throwable th;
        StringBuilder sb;
        String key = str;
        if (key != null && !isValidKey(key)) {
            Throwable th2 = th;
            new StringBuilder();
            new FirebaseException(sb.append("Invalid key: ").append(key).append(". Keys must not contain '/', '.', '#', '$', '[', or ']'").toString());
            throw th2;
        }
    }

    private static boolean isWritablePath(Path path) {
        ChildKey front = path.getFront();
        return front == null || !front.asString().startsWith(".");
    }

    public static void validateWritableObject(Object obj) {
        Object object = obj;
        if (object instanceof Map) {
            Map<String, Object> map = (Map) object;
            if (!map.containsKey(ServerValues.NAME_SUBKEY_SERVERVALUE)) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    validateWritableKey(entry.getKey());
                    validateWritableObject(entry.getValue());
                }
            }
        } else if (object instanceof List) {
            for (Object child : (List) object) {
                validateWritableObject(child);
            }
        }
    }

    public static void validateWritableKey(String str) throws FirebaseException {
        Throwable th;
        StringBuilder sb;
        String key = str;
        if (!isWritableKey(key)) {
            Throwable th2 = th;
            new StringBuilder();
            new FirebaseException(sb.append("Invalid key: ").append(key).append(". Keys must not contain '/', '.', '#', '$', '[', or ']'").toString());
            throw th2;
        }
    }

    public static void validateWritablePath(Path path) throws FirebaseException {
        Throwable th;
        StringBuilder sb;
        Path path2 = path;
        if (!isWritablePath(path2)) {
            Throwable th2 = th;
            new StringBuilder();
            new FirebaseException(sb.append("Invalid write location: ").append(path2.toString()).toString());
            throw th2;
        }
    }

    public static Map<Path, Node> parseAndValidateUpdate(Path path, Map<String, Object> update) throws FirebaseException {
        SortedMap<Path, Node> sortedMap;
        Throwable th;
        StringBuilder sb;
        Path path2;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Path path3 = path;
        new TreeMap<>();
        SortedMap<Path, Node> parsedUpdate = sortedMap;
        for (Map.Entry<String, Object> entry : update.entrySet()) {
            new Path(entry.getKey());
            Path updatePath = path2;
            Object newValue = entry.getValue();
            ValidationPath.validateWithObject(path3.child(updatePath), newValue);
            String childName = !updatePath.isEmpty() ? updatePath.getBack().asString() : "";
            if (childName.equals(ServerValues.NAME_SUBKEY_SERVERVALUE) || childName.equals(".value")) {
                Throwable th4 = th2;
                new StringBuilder();
                new FirebaseException(sb2.append("Path '").append(updatePath).append("' contains disallowed child name: ").append(childName).toString());
                throw th4;
            } else if (!childName.equals(".priority") || PriorityUtilities.isValidPriority(NodeUtilities.NodeFromJSON(newValue))) {
                validateWritableObject(newValue);
                Object put = parsedUpdate.put(updatePath, NodeUtilities.NodeFromJSON(newValue));
            } else {
                Throwable th5 = th3;
                new StringBuilder();
                new FirebaseException(sb3.append("Path '").append(updatePath).append("' contains invalid priority ").append("(must be a string, double, ServerValue, or null).").toString());
                throw th5;
            }
        }
        Path prevPath = null;
        for (Path curPath : parsedUpdate.keySet()) {
            Utilities.hardAssert(prevPath == null || prevPath.compareTo(curPath) < 0);
            if (prevPath == null || !prevPath.contains(curPath)) {
                prevPath = curPath;
            } else {
                Throwable th6 = th;
                new StringBuilder();
                new FirebaseException(sb.append("Path '").append(prevPath).append("' is an ancestor of '").append(curPath).append("' in an update.").toString());
                throw th6;
            }
        }
        return parsedUpdate;
    }
}
