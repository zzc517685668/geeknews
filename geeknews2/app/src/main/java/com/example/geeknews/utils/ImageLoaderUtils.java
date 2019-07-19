package com.example.geeknews.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.geeknews.base.BaseApplication;

public class ImageLoaderUtils {

    public static void showImg(String url, ImageView img) {
        if (!Constants.isNoIMg) {
            Glide.with(BaseApplication.getInstance()).load(url).into(img);
        }else{
            img.setImageResource(0);
        }
    }
}
