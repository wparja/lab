package com.wparja.deviceowner.DPMImp;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.util.Log;

import com.wparja.deviceowner.IDevicePolicyManager;

public class DPMImp implements IDevicePolicyManager {

    private Context mContext;
    private PackageManager mPackageManager;

    public DPMImp(Context context) {
        mContext = context;
        mPackageManager = mContext.getPackageManager();
        setDefaultHome();
    }

    @Override
    public void setDefaultHome() {
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("android.intent.action.MAIN");
//        filter.addCategory("android.intent.category.HOME");
//        filter.addCategory("android.intent.category.DEFAULT");
//        ComponentName component = new ComponentName(mContext.getPackageName(), SplashActivity.class.getName());
//        ComponentName[] components = new ComponentName[] {new ComponentName("com.android.launcher", "com.android.launcher.Launcher"), component};
//        mPackageManager.clearPackagePreferredActivities("com.android.launcher");
//        mPackageManager.addPreferredActivity(filter, IntentFilter.MATCH_CATEGORY_EMPTY, components, component);
        try {
            Runtime.getRuntime().exec("pm disable --user 0 com.android.launcher");
        } catch (Exception e) {
            Log.d("Tag", e.toString());
        }

    }

    @Override
    public void removeKioskModePackages() {
        removeLauncher();
    }

    @Override
    public void setTime(long recTime) {

    }

    @Override
    public void uninstallPackage(Context context, String packageName) {
        try {
            Runtime.getRuntime().exec("pm uninstall " + packageName);
        } catch (Exception e) {
            Log.d("Tag", e.toString());
        }
    }

    @Override
    public void removeLauncher() {
        mPackageManager.clearPackagePreferredActivities(mContext.getPackageName());
        try {
            Runtime.getRuntime().exec("pm enable com.android.launcher");
            Runtime.getRuntime().exec("pm enable-user --user 0 com.android.launcher");
        } catch (Exception e) {
            Log.d("Tag", e.toString());
        }
    }

    @Override
    public void stopLockTask(Activity activity) {
        try {
            Runtime.getRuntime().exec("pm enable-user --user 0 com.android.systemui");
        } catch (Exception e) {
            Log.d("Tag", e.toString());
        }
    }

    @Override
    public void startLockTask(Activity activity) {
        try {
            Runtime.getRuntime().exec("pm disable-user --user 0 com.android.systemui");
        } catch (Exception e) {
            Log.d("Tag", e.toString());
        }
    }

    @Override
    public void setLockTaskPackagesWithDeviceOwner() {

    }

    @Override
    public void setLockTaskPackagesWithoutDeviceOwner() {

    }

    @Override
    public void setLockTaskPackages() {

    }

    @Override
    public void clearTaskPackages() {

    }

    @Override
    public void reboot() {
        PowerManager powerManager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        powerManager.reboot(null);
    }
}
