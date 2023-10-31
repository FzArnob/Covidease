package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.p003v7.widget.AppCompatImageView;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.shaded.apache.http.client.methods.HttpGet;

public class KodularUtil {
    private static String LOG_TAG = "KodularUtil";
    public static String REQUEST_ERROR_MESSAGE = "";
    public static boolean REQUEST_SUCCESS = false;
    private static BitmapDrawable wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    static /* synthetic */ BitmapDrawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BitmapDrawable bitmapDrawable) {
        BitmapDrawable bitmapDrawable2 = bitmapDrawable;
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = bitmapDrawable2;
        return bitmapDrawable2;
    }

    private KodularUtil() {
    }

    public static String GET_REQUEST(String str) {
        StringBuilder sb;
        URL url;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb2;
        String str2 = "";
        try {
            new URL(str);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            HttpsURLConnection httpsURLConnection2 = httpsURLConnection;
            httpsURLConnection.setRequestMethod(HttpGet.METHOD_NAME);
            int responseCode = httpsURLConnection2.getResponseCode();
            new InputStreamReader(httpsURLConnection2.getInputStream());
            new BufferedReader(reader);
            BufferedReader bufferedReader2 = bufferedReader;
            while (true) {
                String readLine = bufferedReader2.readLine();
                String str3 = readLine;
                if (readLine != null) {
                    new StringBuilder();
                    str2 = sb2.append(str2).append(str3).toString();
                } else {
                    bufferedReader2.close();
                    REQUEST_SUCCESS = true;
                    return str2;
                }
            }
        } catch (Exception e) {
            Exception exc = e;
            int e2 = Log.e(LOG_TAG, String.valueOf(exc));
            REQUEST_SUCCESS = false;
            new StringBuilder();
            String sb3 = sb.append(exc.getMessage()).toString();
            REQUEST_ERROR_MESSAGE = sb3;
            return sb3;
        }
    }

    public static void LoadPicture(Context context, String str, AppCompatImageView appCompatImageView, boolean z) {
        File file;
        StringBuilder sb;
        File file2;
        File file3;
        Context context2 = context;
        String str2 = str;
        AppCompatImageView appCompatImageView2 = appCompatImageView;
        boolean z2 = z;
        try {
            if (str2.startsWith("http://") || str2.startsWith("https://")) {
                Target<GlideDrawable> into = Glide.with(context2).load(str2).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView2);
            } else if (str2.startsWith("file:///")) {
                new File(str2.substring(7));
                Target<GlideDrawable> into2 = Glide.with(context2).load(Uri.fromFile(file3)).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView2);
            } else if (str2.startsWith("/")) {
                new File(str2);
                Target<GlideDrawable> into3 = Glide.with(context2).load(Uri.fromFile(file2)).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView2);
            } else if (str2.startsWith("/")) {
                Target<GlideDrawable> into4 = Glide.with(context2).load(str2).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView2);
            } else if (z2) {
                new StringBuilder();
                new File(sb.append(Environment.getExternalStorageDirectory().getPath()).append("/Makeroid/assets/").append(str2).toString());
                Target<GlideDrawable> into5 = Glide.with(context2).load(file).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView2);
            } else {
                Target<GlideDrawable> into6 = Glide.with(context2).load(Uri.parse(Form.ASSETS_PREFIX.concat(String.valueOf(str2)))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(appCompatImageView2);
            }
            appCompatImageView2.setAdjustViewBounds(true);
            appCompatImageView2.requestLayout();
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static void LoadPicture(Context context, String str, ImageView imageView, boolean z) {
        File file;
        StringBuilder sb;
        File file2;
        File file3;
        Context context2 = context;
        String str2 = str;
        ImageView imageView2 = imageView;
        boolean z2 = z;
        try {
            if (str2.startsWith("http://") || str2.startsWith("https://")) {
                Target<GlideDrawable> into = Glide.with(context2).load(str2).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView2);
            } else if (str2.startsWith("file:///")) {
                new File(str2.substring(7));
                Target<GlideDrawable> into2 = Glide.with(context2).load(Uri.fromFile(file3)).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView2);
            } else if (str2.startsWith("/")) {
                new File(str2);
                Target<GlideDrawable> into3 = Glide.with(context2).load(Uri.fromFile(file2)).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView2);
            } else if (str2.startsWith("/")) {
                Target<GlideDrawable> into4 = Glide.with(context2).load(str2).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView2);
            } else if (z2) {
                new StringBuilder();
                new File(sb.append(Environment.getExternalStorageDirectory().getPath()).append("/Makeroid/assets/").append(str2).toString());
                Target<GlideDrawable> into5 = Glide.with(context2).load(file).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView2);
            } else {
                Target<GlideDrawable> into6 = Glide.with(context2).load(Uri.parse(Form.ASSETS_PREFIX.concat(String.valueOf(str2)))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(imageView2);
            }
            imageView2.setAdjustViewBounds(true);
            imageView2.requestLayout();
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static void LoadPicture(Context context, String str, CircleImageView circleImageView, boolean z) {
        File file;
        StringBuilder sb;
        File file2;
        File file3;
        Context context2 = context;
        String str2 = str;
        CircleImageView circleImageView2 = circleImageView;
        boolean z2 = z;
        try {
            if (str2.startsWith("http://") || str2.startsWith("https://")) {
                Target<GlideDrawable> into = Glide.with(context2).load(str2).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView2);
            } else if (str2.startsWith("file:///")) {
                new File(str2.substring(7));
                Target<GlideDrawable> into2 = Glide.with(context2).load(Uri.fromFile(file3)).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView2);
            } else if (str2.startsWith("/")) {
                new File(str2);
                Target<GlideDrawable> into3 = Glide.with(context2).load(Uri.fromFile(file2)).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView2);
            } else if (str2.startsWith("/")) {
                Target<GlideDrawable> into4 = Glide.with(context2).load(str2).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView2);
            } else if (z2) {
                new StringBuilder();
                new File(sb.append(Environment.getExternalStorageDirectory().getPath()).append("/Makeroid/assets/").append(str2).toString());
                Target<GlideDrawable> into5 = Glide.with(context2).load(file).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView2);
            } else {
                Target<GlideDrawable> into6 = Glide.with(context2).load(Uri.parse(Form.ASSETS_PREFIX.concat(String.valueOf(str2)))).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into(circleImageView2);
            }
            circleImageView2.requestLayout();
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public static BitmapDrawable getBitmap(Form form, String str) {
        Target target;
        Target target2;
        Target target3;
        File file;
        StringBuilder sb;
        Target target4;
        File file2;
        Target target5;
        File file3;
        Target target6;
        Form form2 = form;
        String str2 = str;
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = null;
        boolean z = false;
        if (form2 instanceof ReplForm) {
            z = true;
        }
        if (str2.startsWith("http://") || str2.startsWith("https://")) {
            new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable bitmapDrawable;
                    GlideAnimation glideAnimation2 = glideAnimation;
                    new BitmapDrawable((Bitmap) obj);
                    BitmapDrawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = KodularUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bitmapDrawable);
                }
            };
            Target into = Glide.with(form2.$context()).load(str2).asBitmap().into(target);
        } else if (str2.startsWith("file:///")) {
            new File(str2.substring(7));
            new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable bitmapDrawable;
                    GlideAnimation glideAnimation2 = glideAnimation;
                    new BitmapDrawable((Bitmap) obj);
                    BitmapDrawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = KodularUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bitmapDrawable);
                }
            };
            Target into2 = Glide.with(form2.$context()).load(Uri.fromFile(file3)).asBitmap().into(target6);
        } else if (str2.startsWith("/")) {
            new File(str2);
            new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable bitmapDrawable;
                    GlideAnimation glideAnimation2 = glideAnimation;
                    new BitmapDrawable((Bitmap) obj);
                    BitmapDrawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = KodularUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bitmapDrawable);
                }
            };
            Target into3 = Glide.with(form2.$context()).load(Uri.fromFile(file2)).asBitmap().into(target5);
        } else if (str2.startsWith("/")) {
            new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable bitmapDrawable;
                    GlideAnimation glideAnimation2 = glideAnimation;
                    new BitmapDrawable((Bitmap) obj);
                    BitmapDrawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = KodularUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bitmapDrawable);
                }
            };
            Target into4 = Glide.with(form2.$context()).load(str2).asBitmap().into(target2);
        } else if (z) {
            new StringBuilder();
            new File(sb.append(Environment.getExternalStorageDirectory().getPath()).append("/Makeroid/assets/").append(str2).toString());
            new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable bitmapDrawable;
                    GlideAnimation glideAnimation2 = glideAnimation;
                    new BitmapDrawable((Bitmap) obj);
                    BitmapDrawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = KodularUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bitmapDrawable);
                }
            };
            Target into5 = Glide.with(form2.$context()).load(file).asBitmap().into(target4);
        } else {
            new SimpleTarget<Bitmap>() {
                public final /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    BitmapDrawable bitmapDrawable;
                    GlideAnimation glideAnimation2 = glideAnimation;
                    new BitmapDrawable((Bitmap) obj);
                    BitmapDrawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = KodularUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bitmapDrawable);
                }
            };
            Target into6 = Glide.with(form2.$context()).load(Uri.parse(Form.ASSETS_PREFIX.concat(String.valueOf(str2)))).asBitmap().into(target3);
        }
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }
}
