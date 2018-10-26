package com.sensology.baseproject;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;

import com.sensology.baseproject.config.SDKConfig;
import com.sensology.baseproject.log.LogUtil;

/**
 * Created by ${chenM} on 2018/10/19.
 */
public class MyApplication extends Application {
    private static MyApplication instance;// application对象
    public int screenWidth = 0;//屏幕宽
    public int screenHeight = 0;//屏幕高

    @Override
    public void onCreate() {
        super.onCreate();
        getScreenSize();
        initConfig();
    }

    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        instance = this;
    }

    private void initConfig(){
        SDKConfig.initHttp(getApplicationContext(),environmentConfiguration());
    }

    /**
     * 获取屏幕尺寸
     */
    private void getScreenSize() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenHeight = dm.heightPixels;
        screenWidth = dm.widthPixels;
        LogUtil.w("height:"+screenHeight+"\nwidth:"+screenWidth);
    }

    private String environmentConfiguration() {
        String type = getString(R.string.environment_configuration_testing);
        try {
            type = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA)
                    .metaData.getString("type");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return type;
    }

}
