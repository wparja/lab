package com.wparja.deviceowner;

import android.app.Activity;
import android.content.Context;

public interface IDevicePolicyManager {
    void setDefaultHome();
    void removeKioskModePackages();
    void setTime(long recTime);
    void uninstallPackage(Context context, String packageName);
    void removeLauncher();
    void stopLockTask(Activity activity);
    void startLockTask(Activity activity);
    void setLockTaskPackagesWithDeviceOwner();
    void setLockTaskPackagesWithoutDeviceOwner();
    void setLockTaskPackages();
    void clearTaskPackages();
    void reboot();
}
