package com.lskycity.support.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.Locale;

public final class DeviceUtils {


    public static final String[] BB_SUPPORT_DEVICE = {"Z30", "passport", "classic"};

    public static final String BLACKBERRY = "BlackBerry";

    private static final String HUAWEI = "huawei";

    private static final String HONOR = "honor";

    public static final String SAMSUNG = "SAMSUNG";

    public static final String SONY = "SONY";
    public static final String LGE = "LGE";

    public static boolean isBlackBerryDevice() {
        String model = Build.MODEL;
        String product = Build.PRODUCT;
        String manufacture = Build.MANUFACTURER;

        //first check the model
        for (String bb_model : BB_SUPPORT_DEVICE) {
            if (bb_model.equalsIgnoreCase(model)) {
                return true;
            }
        }

        //then check the product
        if (BLACKBERRY.equalsIgnoreCase(product)) {
            return true;
        }

        //then check the manufacture
        return BLACKBERRY.equalsIgnoreCase(manufacture);
    }

    public static boolean isHuaWeiDevice() {
        String model = Build.MODEL;
        String brand = Build.BRAND;

        return brand.toLowerCase(Locale.US).contains(HUAWEI) || brand.toLowerCase(Locale.US).contains(HONOR) || model.toLowerCase(Locale.US).contains(HUAWEI);
    }

    public static boolean isSamsungDevice() {
        String brand = Build.BRAND;
        return SAMSUNG.equalsIgnoreCase(brand);
    }

    /**
     * @return Serial Number, you may cannot get the right value if you are P,
     *  request read phone states permission first and then using android.os.Build#getSerial()
     */
    @SuppressLint("HardwareIds")
    public static String getSerialNumber(){
        return Build.SERIAL;
    }

    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    @SuppressLint("HardwareIds")
    public static String getIMEI(Context context) {
        return ((TelephonyManager)context.getSystemService(
                Context.TELEPHONY_SERVICE)).getDeviceId();
    }


    @SuppressLint("HardwareIds")
    public static String getAndroidId(Context context) {
        try{
            return android.provider.Settings.Secure.getString(
                    context.getContentResolver(),
                    android.provider.Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            return null;
        }

    }


    public static String getDeviceId(Context context) {
        String deviceId = getAndroidId(context);

        if(TextUtils.isEmpty(deviceId)) {
            deviceId = getSerialNumber();
        }

        if(TextUtils.isEmpty(deviceId)) {
            deviceId = "";
        }
        return deviceId;
    }

    public static boolean isInArc() {
        // https://github.com/google/talkback/blob/master/src/main/java/com/google/android/marvin/talkback/TalkBackService.java#L1779-L1781
        // this is detect arc from google's app
        return Build.DEVICE != null && Build.DEVICE.matches(".+_cheets");
    }


}
