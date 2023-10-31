package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.xml.TextUtils;
import gnu.xml.XName;
import java.util.regex.Pattern;

public class XStringType extends XDataType {
    public static final XStringType ENTITYType;
    public static final XStringType IDREFType;
    public static final XStringType IDType;
    public static final XStringType NCNameType;
    public static final XStringType NMTOKENType;
    public static final XStringType NameType;
    static ClassType XStringType = ClassType.make("gnu.kawa.xml.XString");
    public static final XStringType languageType;
    public static final XStringType normalizedStringType;
    public static final XStringType tokenType;
    Pattern pattern;

    static {
        XStringType xStringType;
        XStringType xStringType2;
        XStringType xStringType3;
        XStringType xStringType4;
        XStringType xStringType5;
        XStringType xStringType6;
        XStringType xStringType7;
        XStringType xStringType8;
        XStringType xStringType9;
        new XStringType("normalizedString", stringType, 39, (String) null);
        normalizedStringType = xStringType;
        new XStringType("token", normalizedStringType, 40, (String) null);
        tokenType = xStringType2;
        new XStringType("language", tokenType, 41, "[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*");
        languageType = xStringType3;
        new XStringType("NMTOKEN", tokenType, 42, "\\c+");
        NMTOKENType = xStringType4;
        new XStringType("Name", tokenType, 43, (String) null);
        NameType = xStringType5;
        new XStringType("NCName", NameType, 44, (String) null);
        NCNameType = xStringType6;
        new XStringType("ID", NCNameType, 45, (String) null);
        IDType = xStringType7;
        new XStringType("IDREF", NCNameType, 46, (String) null);
        IDREFType = xStringType8;
        new XStringType("ENTITY", NCNameType, 47, (String) null);
        ENTITYType = xStringType9;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public XStringType(String name, XDataType base, int typeCode, String str) {
        super(name, XStringType, typeCode);
        String pattern2 = str;
        this.baseType = base;
        if (pattern2 != null) {
            this.pattern = Pattern.compile(pattern2);
        }
    }

    public boolean isInstance(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof XString)) {
            return false;
        }
        XDataType stringType = ((XString) obj2).getStringType();
        while (true) {
            XDataType objType = stringType;
            if (objType == null) {
                return false;
            }
            if (objType == this) {
                return true;
            }
            stringType = objType.baseType;
        }
    }

    public String matches(String str) {
        boolean status;
        StringBuilder sb;
        String sb2;
        String value = str;
        switch (this.typeCode) {
            case 39:
            case 40:
                status = value == TextUtils.replaceWhitespace(value, this.typeCode != 39);
                break;
            case 42:
                status = XName.isNmToken(value);
                break;
            case 43:
                status = XName.isName(value);
                break;
            case 44:
            case 45:
            case 46:
            case 47:
                status = XName.isNCName(value);
                break;
            default:
                status = this.pattern == null || this.pattern.matcher(value).matches();
                break;
        }
        if (status) {
            sb2 = null;
        } else {
            new StringBuilder();
            sb2 = sb.append("not a valid XML ").append(getName()).toString();
        }
        return sb2;
    }

    public Object valueOf(String value) {
        Object obj;
        Throwable th;
        StringBuilder sb;
        String value2 = TextUtils.replaceWhitespace(value, this != normalizedStringType);
        if (matches(value2) != null) {
            Throwable th2 = th;
            new StringBuilder();
            new ClassCastException(sb.append("cannot cast ").append(value2).append(" to ").append(this.name).toString());
            throw th2;
        }
        new XString(value2, this);
        return obj;
    }

    public Object cast(Object obj) {
        Object value = obj;
        if (value instanceof XString) {
            XString xvalue = (XString) value;
            if (xvalue.getStringType() == this) {
                return xvalue;
            }
        }
        return valueOf((String) stringType.cast(value));
    }

    public static XString makeNCName(String value) {
        return (XString) NCNameType.valueOf(value);
    }
}
