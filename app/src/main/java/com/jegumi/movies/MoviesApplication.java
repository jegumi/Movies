package com.jegumi.movies;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.jegumi.movies.network.ImageCacheManager;

public class MoviesApplication extends Application {

    private static final String TAG = MoviesApplication.class.getName();
    private static final int DISK_IMAGECACHE_SIZE = 1024*1024*10;
    private static final Bitmap.CompressFormat DISK_IMAGECACHE_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
    private static final int DISK_IMAGECACHE_QUALITY = 100;  //PNG is lossless so quality is ignored but must be provided

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        createImageCache();
        initPreferences();
        appContext = this;
    }

    public static Context getAppContext() {
        return appContext;
    }

    private void initPreferences() {
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("accessToken", "");
        editor.commit();
    }

    private void createImageCache(){
        ImageCacheManager.getInstance().init(this,
                this.getPackageCodePath()
                , DISK_IMAGECACHE_SIZE
                , DISK_IMAGECACHE_COMPRESS_FORMAT
                , DISK_IMAGECACHE_QUALITY
                , ImageCacheManager.CacheType.MEMORY);
    }
}
