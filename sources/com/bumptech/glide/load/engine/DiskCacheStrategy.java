package com.bumptech.glide.load.engine;

public enum DiskCacheStrategy {
    ;
    
    private final boolean cacheResult;
    private final boolean cacheSource;

    private DiskCacheStrategy(boolean cacheSource2, boolean cacheResult2) {
        String str = r9;
        int i = r10;
        this.cacheSource = cacheSource2;
        this.cacheResult = cacheResult2;
    }

    public boolean cacheSource() {
        return this.cacheSource;
    }

    public boolean cacheResult() {
        return this.cacheResult;
    }
}
