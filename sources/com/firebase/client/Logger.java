package com.firebase.client;

public interface Logger {

    public enum Level {
    }

    Level getLogLevel();

    void onLogMessage(Level level, String str, String str2, long j);
}
