package com.miaoke.hook.utils;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class InstallApkUtils {

    public static void hookInstallApk(final String TAG, XC_LoadPackage.LoadPackageParam lpParam) {
        XposedHelpers.findAndHookMethod(
                android.content.pm.PackageManager.class.getName(),
                lpParam.classLoader,
                "getInstalledPackages",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.i(TAG, "-----------> 调用PackageManager.getInstalledPackages获取了" + param.args[0] + " \n");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.i(TAG, StackUtils.getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                android.content.pm.PackageManager.class.getName(),
                lpParam.classLoader,
                "getInstalledApplications",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.i(TAG, "-----------> 调用PackageManager.getInstalledApplications获取了" + param.args[1] + " \n");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.i(TAG, StackUtils.getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                android.app.ActivityManager.class.getName(),
                lpParam.classLoader,
                "getRunningAppProcesses",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.i(TAG, "-----------> 调用PackageManager.getRunningAppProcesses获取了安装列表\n");
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
