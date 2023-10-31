package gnu.math;

class MPN {
    MPN() {
    }

    public static int add_1(int[] iArr, int[] iArr2, int i, int y) {
        int[] dest = iArr;
        int[] x = iArr2;
        int size = i;
        long carry = ((long) y) & 4294967295L;
        for (int i2 = 0; i2 < size; i2++) {
            long carry2 = carry + (((long) x[i2]) & 4294967295L);
            dest[i2] = (int) carry2;
            carry = carry2 >> 32;
        }
        return (int) carry;
    }

    public static int add_n(int[] iArr, int[] iArr2, int[] iArr3, int i) {
        int[] dest = iArr;
        int[] x = iArr2;
        int[] y = iArr3;
        int len = i;
        long carry = 0;
        for (int i2 = 0; i2 < len; i2++) {
            long carry2 = carry + (((long) x[i2]) & 4294967295L) + (((long) y[i2]) & 4294967295L);
            dest[i2] = (int) carry2;
            carry = carry2 >>> 32;
        }
        return (int) carry;
    }

    public static int sub_n(int[] iArr, int[] iArr2, int[] iArr3, int i) {
        int[] dest = iArr;
        int[] X = iArr2;
        int[] Y = iArr3;
        int size = i;
        int cy = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int y = Y[i2];
            int x = X[i2];
            int y2 = y + cy;
            int y3 = x - y2;
            cy = ((y2 ^ Integer.MIN_VALUE) < (cy ^ Integer.MIN_VALUE) ? 1 : 0) + ((y3 ^ Integer.MIN_VALUE) > (x ^ Integer.MIN_VALUE) ? 1 : 0);
            dest[i2] = y3;
        }
        return cy;
    }

    public static int mul_1(int[] iArr, int[] iArr2, int i, int y) {
        int[] dest = iArr;
        int[] x = iArr2;
        int len = i;
        long yword = ((long) y) & 4294967295L;
        long carry = 0;
        for (int j = 0; j < len; j++) {
            long carry2 = carry + ((((long) x[j]) & 4294967295L) * yword);
            dest[j] = (int) carry2;
            carry = carry2 >>> 32;
        }
        return (int) carry;
    }

    public static void mul(int[] iArr, int[] iArr2, int i, int[] iArr3, int i2) {
        int[] dest = iArr;
        int[] x = iArr2;
        int xlen = i;
        int[] y = iArr3;
        int ylen = i2;
        dest[xlen] = mul_1(dest, x, xlen, y[0]);
        for (int i3 = 1; i3 < ylen; i3++) {
            long yword = ((long) y[i3]) & 4294967295L;
            long carry = 0;
            for (int j = 0; j < xlen; j++) {
                long carry2 = carry + ((((long) x[j]) & 4294967295L) * yword) + (((long) dest[i3 + j]) & 4294967295L);
                dest[i3 + j] = (int) carry2;
                carry = carry2 >>> 32;
            }
            dest[i3 + xlen] = (int) carry;
        }
    }

    public static long udiv_qrnnd(long j, int i) {
        long q;
        long r;
        long r2;
        long N = j;
        int D = i;
        long a1 = N >>> 32;
        long a0 = N & 4294967295L;
        if (D < 0) {
            long b1 = (long) (D >>> 1);
            long c = N >>> 1;
            if (a1 < b1 || (a1 >> 1) < b1) {
                if (a1 < b1) {
                    q = c / b1;
                    r = c % b1;
                } else {
                    long c2 = (c - (b1 << 32)) ^ -1;
                    q = ((c2 / b1) ^ -1) & 4294967295L;
                    r = (b1 - 1) - (c2 % b1);
                }
                r2 = (2 * r) + (a0 & 1);
                if ((D & 1) != 0) {
                    if (r2 >= q) {
                        r2 -= q;
                    } else if (q - r2 <= (((long) D) & 4294967295L)) {
                        r2 = (r2 - q) + ((long) D);
                        q--;
                    } else {
                        r2 = (r2 - q) + ((long) D) + ((long) D);
                        q -= 2;
                    }
                }
            } else if (a0 >= (((long) (-D)) & 4294967295L)) {
                q = -1;
                r2 = a0 + ((long) D);
            } else {
                q = -2;
                r2 = a0 + ((long) D) + ((long) D);
            }
        } else if (a1 < (((((long) D) - a1) - (a0 >>> 31)) & 4294967295L)) {
            q = N / ((long) D);
            r2 = N % ((long) D);
        } else {
            long c3 = N - (((long) D) << 31);
            r2 = c3 % ((long) D);
            q = (c3 / ((long) D)) - 2147483648L;
        }
        return (r2 << 32) | (q & 4294967295L);
    }

    public static int divmod_1(int[] iArr, int[] iArr2, int len, int i) {
        long r;
        int[] quotient = iArr;
        int[] dividend = iArr2;
        int divisor = i;
        int i2 = len - 1;
        long r2 = (long) dividend[i2];
        if ((r2 & 4294967295L) >= (((long) divisor) & 4294967295L)) {
            r = 0;
        } else {
            int i3 = i2;
            i2--;
            quotient[i3] = 0;
            r = r2 << 32;
        }
        while (i2 >= 0) {
            r = udiv_qrnnd((r & -4294967296L) | (((long) dividend[i2]) & 4294967295L), divisor);
            quotient[i2] = (int) r;
            i2--;
        }
        return (int) (r >> 32);
    }

    public static int submul_1(int[] iArr, int i, int[] iArr2, int i2, int y) {
        int[] dest = iArr;
        int offset = i;
        int[] x = iArr2;
        int len = i2;
        long yl = ((long) y) & 4294967295L;
        int carry = 0;
        int j = 0;
        do {
            long prod = (((long) x[j]) & 4294967295L) * yl;
            int prod_low = ((int) prod) + carry;
            carry = ((prod_low ^ Integer.MIN_VALUE) < (carry ^ Integer.MIN_VALUE) ? 1 : 0) + ((int) (prod >> 32));
            int x_j = dest[offset + j];
            int prod_low2 = x_j - prod_low;
            if ((prod_low2 ^ Integer.MIN_VALUE) > (x_j ^ Integer.MIN_VALUE)) {
                carry++;
            }
            dest[offset + j] = prod_low2;
            j++;
        } while (j < len);
        return carry;
    }

    public static void divide(int[] iArr, int nx, int[] iArr2, int i) {
        int qhat;
        int[] zds = iArr;
        int[] y = iArr2;
        int ny = i;
        int j = nx;
        do {
            if (zds[j] == y[ny - 1]) {
                qhat = -1;
            } else {
                qhat = (int) udiv_qrnnd((((long) zds[j]) << 32) + (((long) zds[j - 1]) & 4294967295L), y[ny - 1]);
            }
            if (qhat != 0) {
                int borrow = submul_1(zds, j - ny, y, ny, qhat);
                long j2 = ((long) zds[j]) & 4294967295L;
                for (long j3 = ((long) borrow) & 4294967295L; j2 - j3 != 0; j3 = 1) {
                    qhat--;
                    long carry = 0;
                    for (int i2 = 0; i2 < ny; i2++) {
                        long carry2 = carry + (((long) zds[(j - ny) + i2]) & 4294967295L) + (((long) y[i2]) & 4294967295L);
                        zds[(j - ny) + i2] = (int) carry2;
                        carry = carry2 >>> 32;
                    }
                    int[] iArr3 = zds;
                    int i3 = j;
                    iArr3[i3] = (int) (((long) iArr3[i3]) + carry);
                    j2 = carry;
                }
            }
            zds[j] = qhat;
            j--;
        } while (j >= ny);
    }

    public static int chars_per_word(int i) {
        int radix = i;
        if (radix < 10) {
            if (radix >= 8) {
                return 10;
            }
            if (radix <= 2) {
                return 32;
            }
            if (radix == 3) {
                return 20;
            }
            if (radix == 4) {
                return 16;
            }
            return 18 - radix;
        } else if (radix < 12) {
            return 9;
        } else {
            if (radix <= 16) {
                return 8;
            }
            if (radix <= 23) {
                return 7;
            }
            if (radix <= 40) {
                return 6;
            }
            if (radix <= 256) {
                return 4;
            }
            return 1;
        }
    }

    public static int count_leading_zeros(int i) {
        int i2 = i;
        if (i2 == 0) {
            return 32;
        }
        int count = 0;
        int i3 = 16;
        while (true) {
            int k = i3;
            if (k <= 0) {
                return count;
            }
            int j = i2 >>> k;
            if (j == 0) {
                count += k;
            } else {
                i2 = j;
            }
            i3 = k >> 1;
        }
    }

    public static int set_str(int[] iArr, byte[] bArr, int i, int i2) {
        int big_base;
        int cy_limb;
        int[] dest = iArr;
        byte[] str = bArr;
        int str_len = i;
        int base = i2;
        int size = 0;
        if ((base & (base - 1)) == 0) {
            int next_bitpos = 0;
            int bits_per_indigit = 0;
            int i3 = base;
            while (true) {
                int i4 = i3 >> 1;
                i3 = i4;
                if (i4 == 0) {
                    break;
                }
                bits_per_indigit++;
            }
            int res_digit = 0;
            int i5 = str_len;
            while (true) {
                i5--;
                if (i5 < 0) {
                    break;
                }
                int inp_digit = str[i5];
                res_digit |= inp_digit << next_bitpos;
                next_bitpos += bits_per_indigit;
                if (next_bitpos >= 32) {
                    int i6 = size;
                    size++;
                    dest[i6] = res_digit;
                    next_bitpos -= 32;
                    res_digit = inp_digit >> (bits_per_indigit - next_bitpos);
                }
            }
            if (res_digit != 0) {
                int i7 = size;
                size++;
                dest[i7] = res_digit;
            }
        } else {
            int indigits_per_limb = chars_per_word(base);
            int str_pos = 0;
            while (str_pos < str_len) {
                int chunk = str_len - str_pos;
                if (chunk > indigits_per_limb) {
                    chunk = indigits_per_limb;
                }
                int i8 = str_pos;
                str_pos++;
                int res_digit2 = str[i8];
                int i9 = base;
                while (true) {
                    big_base = i9;
                    chunk--;
                    if (chunk <= 0) {
                        break;
                    }
                    int i10 = str_pos;
                    str_pos++;
                    res_digit2 = (res_digit2 * base) + str[i10];
                    i9 = big_base * base;
                }
                if (size == 0) {
                    cy_limb = res_digit2;
                } else {
                    cy_limb = mul_1(dest, dest, size, big_base) + add_1(dest, dest, size, res_digit2);
                }
                if (cy_limb != 0) {
                    int i11 = size;
                    size++;
                    dest[i11] = cy_limb;
                }
            }
        }
        return size;
    }

    public static int cmp(int[] iArr, int[] iArr2, int i) {
        int x_word;
        int y_word;
        int[] x = iArr;
        int[] y = iArr2;
        int size = i;
        do {
            size--;
            if (size < 0) {
                return 0;
            }
            x_word = x[size];
            y_word = y[size];
        } while (x_word == y_word);
        return (x_word ^ Integer.MIN_VALUE) > (y_word ^ Integer.MIN_VALUE) ? 1 : -1;
    }

    public static int cmp(int[] x, int i, int[] y, int i2) {
        int xlen = i;
        int ylen = i2;
        return xlen > ylen ? 1 : xlen < ylen ? -1 : cmp(x, y, xlen);
    }

    public static int rshift(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        int[] dest = iArr;
        int[] x = iArr2;
        int x_start = i;
        int len = i2;
        int count = i3;
        int count_2 = 32 - count;
        int low_word = x[x_start];
        int retval = low_word << count_2;
        int i4 = 1;
        while (i4 < len) {
            int high_word = x[x_start + i4];
            dest[i4 - 1] = (low_word >>> count) | (high_word << count_2);
            low_word = high_word;
            i4++;
        }
        dest[i4 - 1] = low_word >>> count;
        return retval;
    }

    public static void rshift0(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        int[] dest = iArr;
        int[] x = iArr2;
        int x_start = i;
        int len = i2;
        int count = i3;
        if (count > 0) {
            int rshift = rshift(dest, x, x_start, len, count);
            return;
        }
        for (int i4 = 0; i4 < len; i4++) {
            dest[i4] = x[i4 + x_start];
        }
    }

    public static long rshift_long(int[] iArr, int i, int i2) {
        int[] x = iArr;
        int len = i;
        int count = i2;
        int wordno = count >> 5;
        int count2 = count & 31;
        int sign = x[len + -1] < 0 ? -1 : 0;
        int w0 = wordno >= len ? sign : x[wordno];
        int wordno2 = wordno + 1;
        int w1 = wordno2 >= len ? sign : x[wordno2];
        if (count2 != 0) {
            int wordno3 = wordno2 + 1;
            w0 = (w0 >>> count2) | (w1 << (32 - count2));
            w1 = (w1 >>> count2) | ((wordno3 >= len ? sign : x[wordno3]) << (32 - count2));
        }
        return (((long) w1) << 32) | (((long) w0) & 4294967295L);
    }

    public static int lshift(int[] iArr, int d_offset, int[] iArr2, int len, int i) {
        int[] dest = iArr;
        int[] x = iArr2;
        int count = i;
        int count_2 = 32 - count;
        int i2 = len - 1;
        int high_word = x[i2];
        int retval = high_word >>> count_2;
        int d_offset2 = d_offset + 1;
        while (true) {
            i2--;
            if (i2 >= 0) {
                int low_word = x[i2];
                dest[d_offset2 + i2] = (high_word << count) | (low_word >>> count_2);
                high_word = low_word;
            } else {
                dest[d_offset2 + i2] = high_word << count;
                return retval;
            }
        }
    }

    static int findLowestBit(int i) {
        int word = i;
        int i2 = 0;
        while ((word & 15) == 0) {
            word >>= 4;
            i2 += 4;
        }
        if ((word & 3) == 0) {
            word >>= 2;
            i2 += 2;
        }
        if ((word & 1) == 0) {
            i2++;
        }
        return i2;
    }

    static int findLowestBit(int[] iArr) {
        int[] words = iArr;
        int i = 0;
        while (words[i] == 0) {
            i++;
        }
        return (32 * i) + findLowestBit(words[i]);
    }

    public static int gcd(int[] iArr, int[] iArr2, int i) {
        int word;
        int[] other_arg;
        int[] odd_arg;
        int[] x = iArr;
        int[] y = iArr2;
        int len = i;
        int i2 = 0;
        while (true) {
            word = x[i2] | y[i2];
            if (word != 0) {
                break;
            }
            i2++;
        }
        int initShiftWords = i2;
        int initShiftBits = findLowestBit(word);
        int len2 = len - initShiftWords;
        rshift0(x, x, initShiftWords, len2, initShiftBits);
        rshift0(y, y, initShiftWords, len2, initShiftBits);
        if ((x[0] & 1) != 0) {
            odd_arg = x;
            other_arg = y;
        } else {
            odd_arg = y;
            other_arg = x;
        }
        while (true) {
            int i3 = 0;
            while (other_arg[i3] == 0) {
                i3++;
            }
            if (i3 > 0) {
                int j = 0;
                while (j < len2 - i3) {
                    other_arg[j] = other_arg[j + i3];
                    j++;
                }
                while (j < len2) {
                    other_arg[j] = 0;
                    j++;
                }
            }
            int i4 = findLowestBit(other_arg[0]);
            if (i4 > 0) {
                int rshift = rshift(other_arg, other_arg, 0, len2, i4);
            }
            int i5 = cmp(odd_arg, other_arg, len2);
            if (i5 == 0) {
                break;
            }
            if (i5 > 0) {
                int sub_n = sub_n(odd_arg, odd_arg, other_arg, len2);
                int[] tmp = odd_arg;
                odd_arg = other_arg;
                other_arg = tmp;
            } else {
                int sub_n2 = sub_n(other_arg, other_arg, odd_arg, len2);
            }
            while (odd_arg[len2 - 1] == 0 && other_arg[len2 - 1] == 0) {
                len2--;
            }
        }
        if (initShiftWords + initShiftBits > 0) {
            if (initShiftBits <= 0) {
                int i6 = len2;
                while (true) {
                    i6--;
                    if (i6 < 0) {
                        break;
                    }
                    x[i6 + initShiftWords] = x[i6];
                }
            } else {
                int sh_out = lshift(x, initShiftWords, x, len2, initShiftBits);
                if (sh_out != 0) {
                    int i7 = len2;
                    len2++;
                    x[i7 + initShiftWords] = sh_out;
                }
            }
            int i8 = initShiftWords;
            while (true) {
                i8--;
                if (i8 < 0) {
                    break;
                }
                x[i8] = 0;
            }
            len2 += initShiftWords;
        }
        return len2;
    }

    public static int intLength(int i) {
        int i2 = i;
        return 32 - count_leading_zeros(i2 < 0 ? i2 ^ -1 : i2);
    }

    public static int intLength(int[] words, int len) {
        int len2 = len - 1;
        return intLength(words[len2]) + (32 * len2);
    }
}
