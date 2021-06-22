package com.wparja.deviceowner.DPMImp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.wparja.deviceowner.IDevicePolicyManager;
import com.wparja.deviceowner.MainActivity;

@SuppressLint("NewApi")
public class DPMImp implements IDevicePolicyManager {

    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mAdminComponent;
    private Context context;

    @Override
    public void setDefaultHome() {
        // Create an intent filter to specify the Home category.
        IntentFilter filter = new IntentFilter(Intent.ACTION_MAIN);
        filter.addCategory(Intent.CATEGORY_HOME);
        filter.addCategory(Intent.CATEGORY_DEFAULT);

        // Set the activity as the preferred option for the device.
        ComponentName activity = new ComponentName(context, MainActivity.class);
        mDevicePolicyManager.addPersistentPreferredActivity(mAdminComponent, filter, activity);
    }

    @Override
    public void removeKioskModePackages() {

    }

    @Override
    public void setTime(long recTime) {

    }

    @Override
    public void uninstallPackage(Context context, String packageName) {

    }

    @Override
    public void removeLauncher() {
        mDevicePolicyManager.clearPackagePersistentPreferredActivities(mAdminComponent, context.getPackageName());
    }

    @Override
    public void stopLockTask(Activity activity) {
        activity.stopLockTask();
    }

    @Override
    public void startLockTask(Activity activity) {
        activity.startLockTask();
    }

    @Override
    public void setLockTaskPackagesWithDeviceOwner() {

    }

    @Override
    public void setLockTaskPackagesWithoutDeviceOwner() {

    }

    @Override
    public void setLockTaskPackages() {
        if (mDevicePolicyManager.isDeviceOwnerApp(context.getPackageName()) == true) {
            String[] packages = new String[]{ context.getPackageName() };
            mDevicePolicyManager.setLockTaskPackages(mAdminComponent, packages);
        }
    }

    @Override
    public void clearTaskPackages() {
        if (mDevicePolicyManager.isDeviceOwnerApp(context.getPackageName()) == true) {
            String[] packages = new String[]{};
            mDevicePolicyManager.setLockTaskPackages(mAdminComponent, packages);
        }
    }

    @Override
    public void reboot() {

    }
}
