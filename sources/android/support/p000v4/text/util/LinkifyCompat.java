package android.support.p000v4.text.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.util.PatternsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: android.support.v4.text.util.LinkifyCompat */
public final class LinkifyCompat {
    private static final Comparator<LinkSpec> COMPARATOR;
    private static final String[] EMPTY_STRING = new String[0];

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.text.util.LinkifyCompat$LinkifyMask */
    public @interface LinkifyMask {
    }

    static {
        Comparator<LinkSpec> comparator;
        new Comparator<LinkSpec>() {
            public int compare(LinkSpec linkSpec, LinkSpec linkSpec2) {
                LinkSpec a = linkSpec;
                LinkSpec b = linkSpec2;
                if (a.start < b.start) {
                    return -1;
                }
                if (a.start > b.start) {
                    return 1;
                }
                if (a.end < b.end) {
                    return 1;
                }
                if (a.end > b.end) {
                    return -1;
                }
                return 0;
            }
        };
        COMPARATOR = comparator;
    }

    public static boolean addLinks(@NonNull Spannable spannable, int i) {
        ArrayList arrayList;
        Spannable text = spannable;
        int mask = i;
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(text, mask);
        }
        if (mask == 0) {
            return false;
        }
        URLSpan[] old = (URLSpan[]) text.getSpans(0, text.length(), URLSpan.class);
        for (int i2 = old.length - 1; i2 >= 0; i2--) {
            text.removeSpan(old[i2]);
        }
        if ((mask & 4) != 0) {
            boolean addLinks = Linkify.addLinks(text, 4);
        }
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        if ((mask & 1) != 0) {
            Pattern pattern = PatternsCompat.AUTOLINK_WEB_URL;
            String[] strArr = new String[3];
            strArr[0] = "http://";
            String[] strArr2 = strArr;
            strArr2[1] = "https://";
            String[] strArr3 = strArr2;
            strArr3[2] = "rtsp://";
            gatherLinks(arrayList2, text, pattern, strArr3, Linkify.sUrlMatchFilter, (Linkify.TransformFilter) null);
        }
        if ((mask & 2) != 0) {
            gatherLinks(arrayList2, text, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[]{"mailto:"}, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
        }
        if ((mask & 8) != 0) {
            gatherMapLinks(arrayList2, text);
        }
        pruneOverlaps(arrayList2, text);
        if (arrayList2.size() == 0) {
            return false;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            LinkSpec link = (LinkSpec) it.next();
            if (link.frameworkAddedSpan == null) {
                applyLink(link.url, link.start, link.end, text);
            }
        }
        return true;
    }

    public static boolean addLinks(@NonNull TextView textView, int i) {
        TextView text = textView;
        int mask = i;
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(text, mask);
        }
        if (mask == 0) {
            return false;
        }
        CharSequence t = text.getText();
        if (!(t instanceof Spannable)) {
            SpannableString s = SpannableString.valueOf(t);
            if (!addLinks((Spannable) s, mask)) {
                return false;
            }
            addLinkMovementMethod(text);
            text.setText(s);
            return true;
        } else if (!addLinks((Spannable) t, mask)) {
            return false;
        } else {
            addLinkMovementMethod(text);
            return true;
        }
    }

    public static void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str) {
        TextView text = textView;
        Pattern pattern2 = pattern;
        String scheme = str;
        if (shouldAddLinksFallbackToFramework()) {
            Linkify.addLinks(text, pattern2, scheme);
        } else {
            addLinks(text, pattern2, scheme, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
        }
    }

    public static void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        TextView text = textView;
        Pattern pattern2 = pattern;
        String scheme = str;
        Linkify.MatchFilter matchFilter2 = matchFilter;
        Linkify.TransformFilter transformFilter2 = transformFilter;
        if (shouldAddLinksFallbackToFramework()) {
            Linkify.addLinks(text, pattern2, scheme, matchFilter2, transformFilter2);
        } else {
            addLinks(text, pattern2, scheme, (String[]) null, matchFilter2, transformFilter2);
        }
    }

    @SuppressLint({"NewApi"})
    public static void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str, @Nullable String[] strArr, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        TextView text = textView;
        Pattern pattern2 = pattern;
        String defaultScheme = str;
        String[] schemes = strArr;
        Linkify.MatchFilter matchFilter2 = matchFilter;
        Linkify.TransformFilter transformFilter2 = transformFilter;
        if (shouldAddLinksFallbackToFramework()) {
            Linkify.addLinks(text, pattern2, defaultScheme, schemes, matchFilter2, transformFilter2);
            return;
        }
        SpannableString spannable = SpannableString.valueOf(text.getText());
        if (addLinks((Spannable) spannable, pattern2, defaultScheme, schemes, matchFilter2, transformFilter2)) {
            text.setText(spannable);
            addLinkMovementMethod(text);
        }
    }

    public static boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String str) {
        Spannable text = spannable;
        Pattern pattern2 = pattern;
        String scheme = str;
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(text, pattern2, scheme);
        }
        return addLinks(text, pattern2, scheme, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
    }

    public static boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String str, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        Spannable spannable2 = spannable;
        Pattern pattern2 = pattern;
        String scheme = str;
        Linkify.MatchFilter matchFilter2 = matchFilter;
        Linkify.TransformFilter transformFilter2 = transformFilter;
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(spannable2, pattern2, scheme, matchFilter2, transformFilter2);
        }
        return addLinks(spannable2, pattern2, scheme, (String[]) null, matchFilter2, transformFilter2);
    }

    @SuppressLint({"NewApi"})
    public static boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String str, @Nullable String[] strArr, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        Spannable spannable2 = spannable;
        Pattern pattern2 = pattern;
        String defaultScheme = str;
        String[] schemes = strArr;
        Linkify.MatchFilter matchFilter2 = matchFilter;
        Linkify.TransformFilter transformFilter2 = transformFilter;
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(spannable2, pattern2, defaultScheme, schemes, matchFilter2, transformFilter2);
        }
        if (defaultScheme == null) {
            defaultScheme = "";
        }
        if (schemes == null || schemes.length < 1) {
            schemes = EMPTY_STRING;
        }
        String[] schemesCopy = new String[(schemes.length + 1)];
        schemesCopy[0] = defaultScheme.toLowerCase(Locale.ROOT);
        for (int index = 0; index < schemes.length; index++) {
            String scheme = schemes[index];
            schemesCopy[index + 1] = scheme == null ? "" : scheme.toLowerCase(Locale.ROOT);
        }
        boolean hasMatches = false;
        Matcher m = pattern2.matcher(spannable2);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            boolean allowed = true;
            if (matchFilter2 != null) {
                allowed = matchFilter2.acceptMatch(spannable2, start, end);
            }
            if (allowed) {
                applyLink(makeUrl(m.group(0), schemesCopy, m, transformFilter2), start, end, spannable2);
                hasMatches = true;
            }
        }
        return hasMatches;
    }

    private static boolean shouldAddLinksFallbackToFramework() {
        return Build.VERSION.SDK_INT >= 28;
    }

    private static void addLinkMovementMethod(@NonNull TextView textView) {
        TextView t = textView;
        MovementMethod m = t.getMovementMethod();
        if ((m == null || !(m instanceof LinkMovementMethod)) && t.getLinksClickable()) {
            t.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private static String makeUrl(@NonNull String str, @NonNull String[] strArr, Matcher matcher, @Nullable Linkify.TransformFilter transformFilter) {
        StringBuilder sb;
        StringBuilder sb2;
        String url = str;
        String[] prefixes = strArr;
        Matcher matcher2 = matcher;
        Linkify.TransformFilter filter = transformFilter;
        if (filter != null) {
            url = filter.transformUrl(matcher2, url);
        }
        boolean hasPrefix = false;
        int i = 0;
        while (true) {
            if (i >= prefixes.length) {
                break;
            } else if (url.regionMatches(true, 0, prefixes[i], 0, prefixes[i].length())) {
                hasPrefix = true;
                if (!url.regionMatches(false, 0, prefixes[i], 0, prefixes[i].length())) {
                    new StringBuilder();
                    url = sb2.append(prefixes[i]).append(url.substring(prefixes[i].length())).toString();
                }
            } else {
                i++;
            }
        }
        if (!hasPrefix && prefixes.length > 0) {
            new StringBuilder();
            url = sb.append(prefixes[0]).append(url).toString();
        }
        return url;
    }

    private static void gatherLinks(ArrayList<LinkSpec> arrayList, Spannable spannable, Pattern pattern, String[] strArr, Linkify.MatchFilter matchFilter, Linkify.TransformFilter transformFilter) {
        LinkSpec linkSpec;
        ArrayList<LinkSpec> links = arrayList;
        Spannable s = spannable;
        String[] schemes = strArr;
        Linkify.MatchFilter matchFilter2 = matchFilter;
        Linkify.TransformFilter transformFilter2 = transformFilter;
        Matcher m = pattern.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            if (matchFilter2 == null || matchFilter2.acceptMatch(s, start, end)) {
                new LinkSpec();
                LinkSpec spec = linkSpec;
                spec.url = makeUrl(m.group(0), schemes, m, transformFilter2);
                spec.start = start;
                spec.end = end;
                boolean add = links.add(spec);
            }
        }
    }

    private static void applyLink(String url, int start, int end, Spannable text) {
        Object obj;
        new URLSpan(url);
        text.setSpan(obj, start, end, 33);
    }

    private static void gatherMapLinks(ArrayList<LinkSpec> arrayList, Spannable s) {
        int start;
        LinkSpec linkSpec;
        StringBuilder sb;
        ArrayList<LinkSpec> links = arrayList;
        String string = s.toString();
        int base = 0;
        while (true) {
            try {
                String findAddress = findAddress(string);
                String address = findAddress;
                if (findAddress != null && (start = string.indexOf(address)) >= 0) {
                    new LinkSpec();
                    LinkSpec spec = linkSpec;
                    int end = start + address.length();
                    spec.start = base + start;
                    spec.end = base + end;
                    string = string.substring(end);
                    base += end;
                    try {
                        String encodedAddress = URLEncoder.encode(address, "UTF-8");
                        LinkSpec linkSpec2 = spec;
                        new StringBuilder();
                        linkSpec2.url = sb.append("geo:0,0?q=").append(encodedAddress).toString();
                        boolean add = links.add(spec);
                    } catch (UnsupportedEncodingException e) {
                        UnsupportedEncodingException unsupportedEncodingException = e;
                    }
                } else {
                    return;
                }
            } catch (UnsupportedOperationException e2) {
                UnsupportedOperationException unsupportedOperationException = e2;
                return;
            }
        }
    }

    private static String findAddress(String str) {
        String addr = str;
        if (Build.VERSION.SDK_INT >= 28) {
            return WebView.findAddress(addr);
        }
        return FindAddress.findAddress(addr);
    }

    private static void pruneOverlaps(ArrayList<LinkSpec> arrayList, Spannable spannable) {
        LinkSpec linkSpec;
        ArrayList<LinkSpec> links = arrayList;
        Spannable text = spannable;
        URLSpan[] urlSpans = (URLSpan[]) text.getSpans(0, text.length(), URLSpan.class);
        for (int i = 0; i < urlSpans.length; i++) {
            new LinkSpec();
            LinkSpec spec = linkSpec;
            spec.frameworkAddedSpan = urlSpans[i];
            spec.start = text.getSpanStart(urlSpans[i]);
            spec.end = text.getSpanEnd(urlSpans[i]);
            boolean add = links.add(spec);
        }
        Collections.sort(links, COMPARATOR);
        int len = links.size();
        int i2 = 0;
        while (i2 < len - 1) {
            LinkSpec a = links.get(i2);
            LinkSpec b = links.get(i2 + 1);
            int remove = -1;
            if (a.start <= b.start && a.end > b.start) {
                if (b.end <= a.end) {
                    remove = i2 + 1;
                } else if (a.end - a.start > b.end - b.start) {
                    remove = i2 + 1;
                } else if (a.end - a.start < b.end - b.start) {
                    remove = i2;
                }
                if (remove != -1) {
                    URLSpan span = links.get(remove).frameworkAddedSpan;
                    if (span != null) {
                        text.removeSpan(span);
                    }
                    LinkSpec remove2 = links.remove(remove);
                    len--;
                }
            }
            i2++;
        }
    }

    private LinkifyCompat() {
    }

    /* renamed from: android.support.v4.text.util.LinkifyCompat$LinkSpec */
    private static class LinkSpec {
        int end;
        URLSpan frameworkAddedSpan;
        int start;
        String url;

        LinkSpec() {
        }
    }
}
