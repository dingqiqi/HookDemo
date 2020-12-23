package com.miaoke.hook.utils;

import android.net.wifi.WifiInfo;
import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MacUtils {

    public static void hookMac(final String TAG, XC_LoadPackage.LoadPackageParam lpParam) {
        XposedHelpers.findAndHookMethod(
                android.net.wifi.WifiInfo.class.getName(),
                lpParam.classLoader,
                "getMacAddress",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.i(TAG, "--------> 调用WifiInfo.getMacAddress()\n");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.i(TAG, StackUtils.getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                "android.os.SystemProperties",
                lpParam.classLoader,
                "get",
                String.class,
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        // SystemProperties.get("wifi.interface", "wlan0");
                        if (param.args[0].toString().contains("wifi") || param.args[1].toString().contains("wifi")) {
                            Log.i(TAG, "-----------> 调用SystemProperties.get(" + param.args[0] + ", " + param.args[1] + ")\n");
                        }
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        if (param.args[0].toString().contains("wifi") || param.args[1].toString().contains("wifi")) {
                            Log.i(TAG, StackUtils.getMethodStack());
                        }
                        super.afterHookedMethod(param);
                    }
                }
        );


        XposedHelpers.findAndHookMethod(
                Runtime.getRuntime().getClass().getName(),
                lpParam.classLoader,
                "exec",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        if (param.args[0].toString().contains("net")) {
                            Log.i(TAG, "-----------> 调用Runtime.exec(" + param.args[0] + ")\n");
                        }
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        if (param.args[0].toString().contains("net")) {
                            Log.i(TAG, StackUtils.getMethodStack());
                        }
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                java.net.NetworkInterface.class.getName(),
                lpParam.classLoader,
                "getHardwareAddress",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.i(TAG, "-----------> 调用NetworkInterface.getHardwareAddress()" + "\n");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.i(TAG, StackUtils.getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                WifiInfo.class.getName(),
                lpParam.classLoader,
                "getMacAddress",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.i(TAG, "-----------> 调用WifiInfo.getMacAddress()" + "\n");
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
