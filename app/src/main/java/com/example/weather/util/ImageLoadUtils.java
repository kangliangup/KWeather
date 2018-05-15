package com.example.weather.util;


import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageLoadUtils {

    // 1.从安卓项目资源加载
    public static void load(Context context, int resourceId, ImageView imageView) {
        Picasso.with(context).load(resourceId).into(imageView);
    }

    //2.从本地文件中加载
    public static void loadResize(Context context, String filePath, ImageView imageView) {
        File file = new File(filePath);
        Picasso.with(context).load(file).resize(DensityUtils.dp2px(context,100),DensityUtils.dp2px(context,100)).into(imageView);
    }

    //2.从本地文件中加载
    public static void load(Context context, String filePath, ImageView imageView) {
        File file = new File(filePath);
//        Picasso.with(context).load(file).memoryPolicy(NO_CACHE, NO_STORE).config(Bitmap.Config.ALPHA_8).into(imageView);
    }

    //3.从网络上加载图片
    public static void load() {
    }


    //3.uri加载图片
    public static void load(Context context, Uri uri, ImageView imageView) {
        Picasso.with(context).load(uri).into(imageView);
    }
}
