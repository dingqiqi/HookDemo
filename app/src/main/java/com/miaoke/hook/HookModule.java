package com.miaoke.hook;

import android.util.Log;

import com.miaoke.hook.utils.DeviceUtils;
import com.miaoke.hook.utils.InstallApkUtils;
import com.miaoke.hook.utils.LocationUtils;
import com.miaoke.hook.utils.MacUtils;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookModule implements IXposedHookLoadPackage {
    private static final String TAG = "DqqHook";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpParam) {
        if (lpParam == null) {
            return;
        }

        Log.e(TAG, "加载应用:" + lpParam.packageName);

        DeviceUtils.hookDeviceId(TAG, lpParam);

        MacUtils.hookMac(TAG, lpParam);

        LocationUtils.hookLocation(TAG, lpParam);

        InstallApkUtils.hookInstallApk(TAG, lpParam);
    }

}
