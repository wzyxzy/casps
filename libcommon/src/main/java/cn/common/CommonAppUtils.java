package cn.common;

import android.content.Context;
import android.util.Log;

import cn.common.http.HttpRxUtils;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/11/14.
 */

public class CommonAppUtils {
    public static void onCreate(Context context, boolean debug) {
        JPushInterface.init(context);
        JPushInterface.setDebugMode(debug);
        Log.d("RegistrationID ",JPushInterface.getRegistrationID(context));
//        MobclickAgent.setScenarioType(context, EScenarioType.E_UM_NORMAl);
        HttpRxUtils.setDebugMode(debug);
    }
}
