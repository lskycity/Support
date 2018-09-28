package com.lskycity.support.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;

import androidx.annotation.NonNull;

/**
 * Created by liuzhaofeng on 12/7/15.
 *
 */
public class PermissionUtils {

    public static boolean checkPermission(@NonNull Context context, @NonNull String... permissions) {
        for(String p : permissions) {
            if(context.checkPermission(p, Process.myPid(), Process.myUid()) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static boolean verifyPermissions(int[] grantResults) {
        if(grantResults.length < 1){
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


}
