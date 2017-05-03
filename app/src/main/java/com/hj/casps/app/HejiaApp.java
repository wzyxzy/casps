package com.hj.casps.app;

import android.support.multidex.MultiDexApplication;

import com.hj.casps.BuildConfig;
import com.hj.casps.util.ActivityUtils;

import cn.common.CommonAppUtils;

/**
 * Created by 鑫 Administrator on 2017/4/17.
 */

public class HejiaApp extends MultiDexApplication {
    private ApiList apilist;

    public ApiList getApiList() {
        if (apilist == null) {
            apilist = (ApiList) DataFilePersistenceUtils.readObj(Constants.ApiListFileName);
        }
        return apilist;
    }


    public void setApiList(ApiList apiList) {
        this.apilist = apiList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CommonAppUtils.onCreate(this, BuildConfig.DEBUG);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                ex.printStackTrace();
                ActivityUtils.exit();
            }
        });
    }
}
