package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class SafeKeyGenerator {
    private final LruCache<Key, String> loadIdToSafeHash;

    SafeKeyGenerator() {
        LruCache<Key, String> lruCache;
        new LruCache<>(1000);
        this.loadIdToSafeHash = lruCache;
    }

    /* JADX INFO: finally extract failed */
    public String getSafeKey(Key key) {
        Key key2 = key;
        LruCache<Key, String> lruCache = this.loadIdToSafeHash;
        LruCache<Key, String> lruCache2 = lruCache;
        synchronized (lruCache) {
            try {
                String safeKey = this.loadIdToSafeHash.get(key2);
                if (safeKey == null) {
                    try {
                        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                        key2.updateDiskCacheKey(messageDigest);
                        safeKey = Util.sha256BytesToHex(messageDigest.digest());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e2) {
                        e2.printStackTrace();
                    }
                    LruCache<Key, String> lruCache3 = this.loadIdToSafeHash;
                    LruCache<Key, String> lruCache4 = lruCache3;
                    synchronized (lruCache3) {
                        try {
                            String put = this.loadIdToSafeHash.put(key2, safeKey);
                        } catch (Throwable th) {
                            while (true) {
                                Throwable th2 = th;
                                LruCache<Key, String> lruCache5 = lruCache4;
                                throw th2;
                            }
                        }
                    }
                }
                return safeKey;
            } catch (Throwable th3) {
                while (true) {
                    Throwable th4 = th3;
                    LruCache<Key, String> lruCache6 = lruCache2;
                    throw th4;
                }
            }
        }
    }
}
