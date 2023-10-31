package com.firebase.client.core.view;

import com.firebase.client.EventTarget;
import com.firebase.client.core.Context;
import com.firebase.client.utilities.LogWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventRaiser {
    private final EventTarget eventTarget;
    /* access modifiers changed from: private */
    public final LogWrapper logger;

    public EventRaiser(Context context) {
        Context ctx = context;
        this.eventTarget = ctx.getEventTarget();
        this.logger = ctx.getLogger("EventRaiser");
    }

    public void raiseEvents(List<? extends Event> list) {
        ArrayList arrayList;
        Runnable runnable;
        StringBuilder sb;
        List<? extends Event> events = list;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb.append("Raising ").append(events.size()).append(" event(s)").toString());
        }
        new ArrayList(events);
        final ArrayList arrayList2 = arrayList;
        new Runnable(this) {
            final /* synthetic */ EventRaiser this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                StringBuilder sb;
                Iterator i$ = arrayList2.iterator();
                while (i$.hasNext()) {
                    Event event = (Event) i$.next();
                    if (this.this$0.logger.logsDebug()) {
                        LogWrapper access$000 = this.this$0.logger;
                        new StringBuilder();
                        access$000.debug(sb.append("Raising ").append(event.toString()).toString());
                    }
                    event.fire();
                }
            }
        };
        this.eventTarget.postEvent(runnable);
    }
}
