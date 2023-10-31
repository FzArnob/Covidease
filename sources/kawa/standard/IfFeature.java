package kawa.standard;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.SyntaxForm;

public class IfFeature {
    public IfFeature() {
    }

    public static boolean testFeature(Object obj) {
        Object form = obj;
        if (form instanceof SyntaxForm) {
            form = ((SyntaxForm) form).getDatum();
        }
        if ((form instanceof String) || (form instanceof SimpleSymbol)) {
            return hasFeature(form.toString());
        }
        return false;
    }

    public static boolean hasFeature(String str) {
        StringBuilder sb;
        String name = str;
        if (name == "kawa") {
            return true;
        }
        if (name == "srfi-0") {
            return true;
        }
        if (name == "srfi-4") {
            return true;
        }
        if (name == "srfi-6") {
            return true;
        }
        if (name == "srfi-8") {
            return true;
        }
        if (name == "srfi-9") {
            return true;
        }
        if (name == "srfi-11") {
            return true;
        }
        if (name == "srfi-16") {
            return true;
        }
        if (name == "srfi-17") {
            return true;
        }
        if (name == "srfi-23") {
            return true;
        }
        if (name == "srfi-25") {
            return true;
        }
        if (name == "srfi-26") {
            return true;
        }
        if (name == "srfi-28") {
            return true;
        }
        if (name == "srfi-30") {
            return true;
        }
        if (name == "srfi-39") {
            return true;
        }
        if (name == "in-http-server" || name == "in-servlet") {
            int mflags = ModuleContext.getContext().getFlags();
            if (name == "in-http-server") {
                return (mflags & ModuleContext.IN_HTTP_SERVER) != 0;
            } else if (name == "in-servlet") {
                return (mflags & ModuleContext.IN_SERVLET) != 0;
            }
        }
        new StringBuilder();
        Declaration decl = Compilation.getCurrent().lookup(sb.append("%provide%").append(name).toString().intern(), -1);
        if (decl == null || decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
            return false;
        }
        return true;
    }
}
