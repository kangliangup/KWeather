package com.example.weather.util;


import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class SystemUtil {

    /**
     * 调用系统拨号界面
     */
    public static void call(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 打开系统邮箱
     */
    public static void email(Context context, String email) {
        Uri uri = Uri.parse("mailto:"+email);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        context.startActivity(intent);
    }

    /**
     * 赋值内容到剪贴板
     */
    public static void clipData(Context context, String data){
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(data);
        Toast.makeText(context, "已复制到剪贴板", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取应用详情页面intent
     */
    public static Intent getSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        return localIntent;

    }
}
