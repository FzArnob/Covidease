package com.google.appinventor.components.runtime.util;

import android.os.Handler;

public class AsynchUtil {
    public AsynchUtil() {
    }

    public static void runAsynchronously(Runnable runnable) {
        Thread thread;
        new Thread(runnable);
        thread.start();
    }

    public static void runAsynchronously(Handler handler, Runnable runnable, Runnable runnable2) {
        Runnable runnable3;
        Thread thread;
        final Runnable runnable4 = runnable;
        final Runnable runnable5 = runnable2;
        final Handler handler2 = handler;
        new Runnable() {
            public final void run() {
                Runnable runnable;
                runnable4.run();
                if (runnable5 != null) {
                    new Runnable(this) {
                        private /* synthetic */ C11231 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void run() {
                            runnable5.run();
                        }
                    };
                    boolean post = handler2.post(runnable);
                }
            }
        };
        new Thread(runnable3);
        thread.start();
    }
}
