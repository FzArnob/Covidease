package org.shaded.apache.http.impl.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.shaded.apache.http.annotation.GuardedBy;
import org.shaded.apache.http.annotation.ThreadSafe;
import org.shaded.apache.http.client.CookieStore;
import org.shaded.apache.http.cookie.Cookie;
import org.shaded.apache.http.cookie.CookieIdentityComparator;

@ThreadSafe
public class BasicCookieStore implements CookieStore {
    @GuardedBy("this")
    private final Comparator<Cookie> cookieComparator;
    @GuardedBy("this")
    private final ArrayList<Cookie> cookies;

    public BasicCookieStore() {
        ArrayList<Cookie> arrayList;
        Comparator<Cookie> comparator;
        new ArrayList<>();
        this.cookies = arrayList;
        new CookieIdentityComparator();
        this.cookieComparator = comparator;
    }

    public synchronized void addCookie(Cookie cookie) {
        Date date;
        Cookie cookie2 = cookie;
        synchronized (this) {
            if (cookie2 != null) {
                Iterator<Cookie> it = this.cookies.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (this.cookieComparator.compare(cookie2, it.next()) == 0) {
                            it.remove();
                            break;
                        }
                    } else {
                        break;
                    }
                }
                new Date();
                if (!cookie2.isExpired(date)) {
                    boolean add = this.cookies.add(cookie2);
                }
            }
        }
    }

    public synchronized void addCookies(Cookie[] cookieArr) {
        Cookie[] cookies2 = cookieArr;
        synchronized (this) {
            if (cookies2 != null) {
                Cookie[] arr$ = cookies2;
                int len$ = arr$.length;
                for (int i$ = 0; i$ < len$; i$++) {
                    addCookie(arr$[i$]);
                }
            }
        }
    }

    public synchronized List<Cookie> getCookies() {
        List<Cookie> unmodifiableList;
        synchronized (this) {
            unmodifiableList = Collections.unmodifiableList(this.cookies);
        }
        return unmodifiableList;
    }

    public synchronized boolean clearExpired(Date date) {
        boolean z;
        Date date2 = date;
        synchronized (this) {
            if (date2 == null) {
                z = false;
            } else {
                boolean removed = false;
                Iterator<Cookie> it = this.cookies.iterator();
                while (it.hasNext()) {
                    if (it.next().isExpired(date2)) {
                        it.remove();
                        removed = true;
                    }
                }
                z = removed;
            }
        }
        return z;
    }

    public String toString() {
        return this.cookies.toString();
    }

    public synchronized void clear() {
        synchronized (this) {
            this.cookies.clear();
        }
    }
}
