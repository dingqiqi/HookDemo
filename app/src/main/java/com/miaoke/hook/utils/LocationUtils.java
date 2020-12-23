package com.miaoke.hook.utils;

import android.location.LocationManager;
import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class LocationUtils {

    public static void hookLocation(final String TAG, XC_LoadPackage.LoadPackageParam lpParam) {
        XposedHelpers.findAndHookMethod(
                LocationManager.class.getName(),
                lpParam.classLoader,
                "getLastKnownLocation",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.i(TAG, "--------> 调用LocationManager.getLastKnownLocation(" + param.args[0] + ")获取地址\n");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.i(TAG, StackUtils.getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );
    }
}
