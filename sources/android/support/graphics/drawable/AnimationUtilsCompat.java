package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.animation.FastOutLinearInInterpolator;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AnimationUtilsCompat {
    public static Interpolator loadInterpolator(Context context, int i) throws Resources.NotFoundException {
        Resources.NotFoundException notFoundException;
        StringBuilder sb;
        Resources.NotFoundException notFoundException2;
        StringBuilder sb2;
        Interpolator interpolator;
        Interpolator interpolator2;
        Interpolator interpolator3;
        Context context2 = context;
        int id = i;
        if (Build.VERSION.SDK_INT >= 21) {
            return AnimationUtils.loadInterpolator(context2, id);
        }
        XmlResourceParser parser = null;
        if (id == 17563663) {
            try {
                Interpolator interpolator4 = interpolator3;
                new FastOutLinearInInterpolator();
                Interpolator interpolator5 = interpolator4;
                if (0 != 0) {
                    XmlResourceParser xmlResourceParser = null;
                    xmlResourceParser.close();
                }
                return interpolator5;
            } catch (XmlPullParserException e) {
                XmlPullParserException ex = e;
                new StringBuilder();
                new Resources.NotFoundException(sb2.append("Can't load animation resource ID #0x").append(Integer.toHexString(id)).toString());
                Resources.NotFoundException rnf = notFoundException2;
                Throwable initCause = rnf.initCause(ex);
                throw rnf;
            } catch (IOException e2) {
                IOException ex2 = e2;
                new StringBuilder();
                new Resources.NotFoundException(sb.append("Can't load animation resource ID #0x").append(Integer.toHexString(id)).toString());
                Resources.NotFoundException rnf2 = notFoundException;
                Throwable initCause2 = rnf2.initCause(ex2);
                throw rnf2;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (parser != null) {
                    parser.close();
                }
                throw th2;
            }
        } else if (id == 17563661) {
            Interpolator interpolator6 = interpolator2;
            new FastOutSlowInInterpolator();
            Interpolator interpolator7 = interpolator6;
            if (0 != 0) {
                XmlResourceParser xmlResourceParser2 = null;
                xmlResourceParser2.close();
            }
            return interpolator7;
        } else if (id == 17563662) {
            Interpolator interpolator8 = interpolator;
            new LinearOutSlowInInterpolator();
            Interpolator interpolator9 = interpolator8;
            if (0 != 0) {
                XmlResourceParser xmlResourceParser3 = null;
                xmlResourceParser3.close();
            }
            return interpolator9;
        } else {
            parser = context2.getResources().getAnimation(id);
            Interpolator createInterpolatorFromXml = createInterpolatorFromXml(context2, context2.getResources(), context2.getTheme(), parser);
            if (parser != null) {
                parser.close();
            }
            return createInterpolatorFromXml;
        }
    }

    private static Interpolator createInterpolatorFromXml(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Throwable th;
        StringBuilder sb;
        Interpolator interpolator;
        Interpolator interpolator2;
        Interpolator interpolator3;
        Interpolator interpolator4;
        Interpolator interpolator5;
        Interpolator interpolator6;
        Interpolator interpolator7;
        Interpolator interpolator8;
        Interpolator interpolator9;
        Interpolator interpolator10;
        Context context2 = context;
        Resources resources2 = resources;
        Resources.Theme theme2 = theme;
        XmlPullParser parser = xmlPullParser;
        Interpolator interpolator11 = null;
        int depth = parser.getDepth();
        while (true) {
            int next = parser.next();
            int type = next;
            if ((next != 3 || parser.getDepth() > depth) && type != 1) {
                if (type == 2) {
                    AttributeSet attrs = Xml.asAttributeSet(parser);
                    String name = parser.getName();
                    if (name.equals("linearInterpolator")) {
                        new LinearInterpolator();
                        interpolator11 = interpolator10;
                    } else if (name.equals("accelerateInterpolator")) {
                        new AccelerateInterpolator(context2, attrs);
                        interpolator11 = interpolator9;
                    } else if (name.equals("decelerateInterpolator")) {
                        new DecelerateInterpolator(context2, attrs);
                        interpolator11 = interpolator8;
                    } else if (name.equals("accelerateDecelerateInterpolator")) {
                        new AccelerateDecelerateInterpolator();
                        interpolator11 = interpolator7;
                    } else if (name.equals("cycleInterpolator")) {
                        new CycleInterpolator(context2, attrs);
                        interpolator11 = interpolator6;
                    } else if (name.equals("anticipateInterpolator")) {
                        new AnticipateInterpolator(context2, attrs);
                        interpolator11 = interpolator5;
                    } else if (name.equals("overshootInterpolator")) {
                        new OvershootInterpolator(context2, attrs);
                        interpolator11 = interpolator4;
                    } else if (name.equals("anticipateOvershootInterpolator")) {
                        new AnticipateOvershootInterpolator(context2, attrs);
                        interpolator11 = interpolator3;
                    } else if (name.equals("bounceInterpolator")) {
                        new BounceInterpolator();
                        interpolator11 = interpolator2;
                    } else if (name.equals("pathInterpolator")) {
                        new PathInterpolatorCompat(context2, attrs, parser);
                        interpolator11 = interpolator;
                    } else {
                        Throwable th2 = th;
                        new StringBuilder();
                        new RuntimeException(sb.append("Unknown interpolator name: ").append(parser.getName()).toString());
                        throw th2;
                    }
                }
            }
        }
        return interpolator11;
    }

    private AnimationUtilsCompat() {
    }
}
