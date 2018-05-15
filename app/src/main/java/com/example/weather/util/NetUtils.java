package com.example.weather.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;


/**
 * 跟网络相关的工具类
 *
 * @author zhy
 */
public class NetUtils {
    private NetUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否连接
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager con = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != con) {
            NetworkInfo info = con.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * 判断wifi是否可用
     */
    public static boolean isWifiEnable(Context context) {
        WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (null != wm) {
            WifiInfo wifiInfo = wm.getConnectionInfo();
            int ipAddress = wifiInfo == null ? 0 : wifiInfo.getIpAddress();
            if (wm.isWifiEnabled() && ipAddress != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Context context) {
//        Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
        Intent wifiSettingsIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        context.startActivity(wifiSettingsIntent);
//        ((Activity)context).startActivityForResult(wifiSettingsIntent, 0);
    }
    /**
     * 获取当前的网络状态 ：没有网络0：WIFI网络1：3G网络2：2G网络3
     *
     * @param context
     * @return
     */
    public static int getAPNType(Context context) {
        int netType = 0;
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = 1;// wifi
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            int nSubType = networkInfo.getSubtype();
            TelephonyManager mTelephony = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
                    && !mTelephony.isNetworkRoaming()) {
                netType = 2;// 3G
            } else {
                netType = 3;// 2G
            }
        }
        return netType;
    }

}
