package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DComplex extends Complex implements Externalizable {
    double imag;
    double real;

    public DComplex() {
    }

    public DComplex(double real2, double imag2) {
        this.real = real2;
        this.imag = imag2;
    }

    /* renamed from: re */
    public RealNum mo17635re() {
        RealNum realNum;
        new DFloNum(this.real);
        return realNum;
    }

    public double doubleValue() {
        return this.real;
    }

    /* renamed from: im */
    public RealNum mo17634im() {
        RealNum realNum;
        new DFloNum(this.imag);
        return realNum;
    }

    public double doubleImagValue() {
        return this.imag;
    }

    public boolean isExact() {
        return false;
    }

    public Complex toExact() {
        Complex complex;
        new CComplex(DFloNum.toExact(this.real), DFloNum.toExact(this.imag));
        return complex;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null || !(obj2 instanceof Complex)) {
            return false;
        }
        Complex y = (Complex) obj2;
        return y.unit() == Unit.Empty && Double.doubleToLongBits(this.real) == Double.doubleToLongBits(y.reValue()) && Double.doubleToLongBits(this.imag) == Double.doubleToLongBits(y.imValue());
    }

    public String toString() {
        String reString;
        StringBuilder sb;
        String imString;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        String sb5;
        StringBuilder sb6;
        String prefix = "";
        if (this.real == Double.POSITIVE_INFINITY) {
            prefix = "#i";
            reString = "1/0";
        } else if (this.real == Double.NEGATIVE_INFINITY) {
            prefix = "#i";
            reString = "-1/0";
        } else if (Double.isNaN(this.real)) {
            prefix = "#i";
            reString = "0/0";
        } else {
            reString = Double.toString(this.real);
        }
        if (Double.doubleToLongBits(this.imag) == 0) {
            new StringBuilder();
            return sb6.append(prefix).append(reString).toString();
        }
        if (this.imag == Double.POSITIVE_INFINITY) {
            prefix = "#i";
            imString = "+1/0i";
        } else if (this.imag == Double.NEGATIVE_INFINITY) {
            prefix = "#i";
            imString = "-1/0i";
        } else if (Double.isNaN(this.imag)) {
            prefix = "#i";
            imString = "+0/0i";
        } else {
            new StringBuilder();
            imString = sb.append(Double.toString(this.imag)).append("i").toString();
            if (imString.charAt(0) != '-') {
                new StringBuilder();
                imString = sb2.append("+").append(imString).toString();
            }
        }
        StringBuilder sb7 = sb3;
        new StringBuilder();
        if (Double.doubleToLongBits(this.real) == 0) {
            sb5 = prefix;
        } else {
            new StringBuilder();
            sb5 = sb4.append(prefix).append(reString).toString();
        }
        return sb7.append(sb5).append(imString).toString();
    }

    public String toString(int radix) {
        StringBuilder sb;
        if (radix == 10) {
            return toString();
        }
        new StringBuilder();
        return sb.append("#d").append(toString()).toString();
    }

    public final Numeric neg() {
        DComplex dComplex;
        new DComplex(-this.real, -this.imag);
        return dComplex;
    }

    public Numeric add(Object obj, int i) {
        DComplex dComplex;
        Throwable th;
        Object y = obj;
        int k = i;
        if (!(y instanceof Complex)) {
            return ((Numeric) y).addReversed(this, k);
        }
        Complex yc = (Complex) y;
        if (yc.dimensions() != Dimensions.Empty) {
            Throwable th2 = th;
            new ArithmeticException("units mis-match");
            throw th2;
        }
        new DComplex(this.real + (((double) k) * yc.reValue()), this.imag + (((double) k) * yc.imValue()));
        return dComplex;
    }

    public Numeric mul(Object obj) {
        DComplex dComplex;
        Object y = obj;
        if (!(y instanceof Complex)) {
            return ((Numeric) y).mulReversed(this);
        }
        Complex yc = (Complex) y;
        if (yc.unit() != Unit.Empty) {
            return Complex.times(this, yc);
        }
        double y_re = yc.reValue();
        double y_im = yc.imValue();
        new DComplex((this.real * y_re) - (this.imag * y_im), (this.real * y_im) + (this.imag * y_re));
        return dComplex;
    }

    public Numeric div(Object obj) {
        Object y = obj;
        if (!(y instanceof Complex)) {
            return ((Numeric) y).divReversed(this);
        }
        Complex yc = (Complex) y;
        return div(this.real, this.imag, yc.doubleValue(), yc.doubleImagValue());
    }

    public static DComplex power(double d, double d2, double d3, double d4) {
        double x_re = d;
        double x_im = d2;
        double y_re = d3;
        double y_im = d4;
        double logr = Math.log(Math.hypot(x_re, x_im));
        double t = Math.atan2(x_im, x_re);
        return Complex.polar(Math.exp((logr * y_re) - (y_im * t)), (y_im * logr) + (y_re * t));
    }

    public static Complex log(double d, double d2) {
        double x_re = d;
        double x_im = d2;
        return make(Math.log(Math.hypot(x_re, x_im)), Math.atan2(x_im, x_re));
    }

    public static DComplex div(double d, double d2, double d3, double d4) {
        double d5;
        double nr;
        double ni;
        DComplex dComplex;
        double x_re = d;
        double x_im = d2;
        double y_re = d3;
        double y_im = d4;
        if (Math.abs(y_re) <= Math.abs(y_im)) {
            double t = y_re / y_im;
            d5 = y_im * (1.0d + (t * t));
            nr = (x_re * t) + x_im;
            ni = (x_im * t) - x_re;
        } else {
            double t2 = y_im / y_re;
            d5 = y_re * (1.0d + (t2 * t2));
            nr = x_re + (x_im * t2);
            ni = x_im - (x_re * t2);
        }
        new DComplex(nr / d5, ni / d5);
        return dComplex;
    }

    public static Complex sqrt(double d, double d2) {
        double ni;
        double nr;
        Complex complex;
        double x_re = d;
        double x_im = d2;
        double r = Math.hypot(x_re, x_im);
        if (r == 0.0d) {
            double d3 = r;
            ni = d3;
            nr = d3;
        } else if (x_re > 0.0d) {
            nr = Math.sqrt(0.5d * (r + x_re));
            ni = (x_im / nr) / 2.0d;
        } else {
            ni = Math.sqrt(0.5d * (r - x_re));
            if (x_im < 0.0d) {
                ni = -ni;
            }
            nr = (x_im / ni) / 2.0d;
        }
        new DComplex(nr, ni);
        return complex;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeDouble(this.real);
        out.writeDouble(this.imag);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.real = in.readDouble();
        this.imag = in.readDouble();
    }
}
