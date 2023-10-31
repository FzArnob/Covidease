package com.firebase.client.utilities;

public class OffsetClock implements Clock {
    private final Clock baseClock;
    private long offset = 0;

    public OffsetClock(Clock baseClock2, long offset2) {
        this.baseClock = baseClock2;
        this.offset = offset2;
    }

    public void setOffset(long offset2) {
        long j = offset2;
        this.offset = j;
    }

    public long millis() {
        return this.baseClock.millis() + this.offset;
    }
}
