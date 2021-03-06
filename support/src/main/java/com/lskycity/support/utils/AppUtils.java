package com.lskycity.support.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

/**
 * Created by zhaofliu on 1/2/17.
 *
 */

public class AppUtils {

    /**
     * using BuildConfig.VERSION_NAME instead.
     *
     * */
    @Deprecated
    public static String getVersionName(@NonNull Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * using BuildConfig.VERSION_CODE instead.
     *
     * */
    public static int getVersionCode(@NonNull Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isDeviceProtected(@NonNull Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        if(keyguardManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return keyguardManager.isDeviceSecure();
        } else {
            return keyguardManager.isKeyguardSecure();
        }
        // No way to detect whether the device below API level 16 is secured by PIN or password
    }

    public static void unlockScreen(@NonNull Activity context, boolean dismissKeyguard) {
        Window window = context.getWindow();
        if (dismissKeyguard) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }

    /**
     * scan activity from context.
     *
     * @param cont a context
     *
     * @return Activity if find or null if not found
     * */
    public static Activity scanForActivity(Context cont) {

        if (cont instanceof Activity)
            return (Activity)cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper)cont).getBaseContext());

        return null;
    }

    /**
     * find Activity from view
     *
     * @param view view already attached to activity.
     * @return Activity if this view attached in a activity, null if not
     * */
    public static Activity getActivity(@NonNull View view) {
        return scanForActivity(view.getContext());
    }

    public int getNavigationBarHeight(@NonNull Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    public int getStatusBarHeight(@NonNull Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("status_bar_height",
                "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

}
