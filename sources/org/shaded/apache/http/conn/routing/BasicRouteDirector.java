package org.shaded.apache.http.conn.routing;

import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class BasicRouteDirector implements HttpRouteDirector {
    public BasicRouteDirector() {
    }

    public int nextStep(RouteInfo routeInfo, RouteInfo routeInfo2) {
        int step;
        Throwable th;
        RouteInfo plan = routeInfo;
        RouteInfo fact = routeInfo2;
        if (plan == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Planned route may not be null.");
            throw th2;
        }
        if (fact == null || fact.getHopCount() < 1) {
            step = firstStep(plan);
        } else if (plan.getHopCount() > 1) {
            step = proxiedStep(plan, fact);
        } else {
            step = directStep(plan, fact);
        }
        return step;
    }

    /* access modifiers changed from: protected */
    public int firstStep(RouteInfo plan) {
        return plan.getHopCount() > 1 ? 2 : 1;
    }

    /* access modifiers changed from: protected */
    public int directStep(RouteInfo routeInfo, RouteInfo routeInfo2) {
        RouteInfo plan = routeInfo;
        RouteInfo fact = routeInfo2;
        if (fact.getHopCount() > 1) {
            return -1;
        }
        if (!plan.getTargetHost().equals(fact.getTargetHost())) {
            return -1;
        }
        if (plan.isSecure() != fact.isSecure()) {
            return -1;
        }
        if (plan.getLocalAddress() == null || plan.getLocalAddress().equals(fact.getLocalAddress())) {
            return 0;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int proxiedStep(RouteInfo routeInfo, RouteInfo routeInfo2) {
        RouteInfo plan = routeInfo;
        RouteInfo fact = routeInfo2;
        if (fact.getHopCount() <= 1) {
            return -1;
        }
        if (!plan.getTargetHost().equals(fact.getTargetHost())) {
            return -1;
        }
        int phc = plan.getHopCount();
        int fhc = fact.getHopCount();
        if (phc < fhc) {
            return -1;
        }
        for (int i = 0; i < fhc - 1; i++) {
            if (!plan.getHopTarget(i).equals(fact.getHopTarget(i))) {
                return -1;
            }
        }
        if (phc > fhc) {
            return 4;
        }
        if ((fact.isTunnelled() && !plan.isTunnelled()) || (fact.isLayered() && !plan.isLayered())) {
            return -1;
        }
        if (plan.isTunnelled() && !fact.isTunnelled()) {
            return 3;
        }
        if (plan.isLayered() && !fact.isLayered()) {
            return 5;
        }
        if (plan.isSecure() != fact.isSecure()) {
            return -1;
        }
        return 0;
    }
}
