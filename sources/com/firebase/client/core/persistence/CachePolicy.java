package com.firebase.client.core.persistence;

public interface CachePolicy {
    public static final CachePolicy NONE;

    long getMaxNumberOfQueriesToKeep();

    float getPercentOfQueriesToPruneAtOnce();

    boolean shouldCheckCacheSize(long j);

    boolean shouldPrune(long j, long j2);

    static {
        CachePolicy cachePolicy;
        new CachePolicy() {
            public boolean shouldPrune(long j, long j2) {
                long j3 = j;
                long j4 = j2;
                return false;
            }

            public boolean shouldCheckCacheSize(long j) {
                long j2 = j;
                return false;
            }

            public float getPercentOfQueriesToPruneAtOnce() {
                return 0.0f;
            }

            public long getMaxNumberOfQueriesToKeep() {
                return Long.MAX_VALUE;
            }
        };
        NONE = cachePolicy;
    }
}
