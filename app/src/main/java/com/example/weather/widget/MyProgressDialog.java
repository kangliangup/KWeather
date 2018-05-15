package com.example.weather.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weather.R;


public class MyProgressDialog {

    private static Dialog loadingDialog;

    private static Dialog create(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.view_dialog_progress, null);
        LinearLayout layout =  v.findViewById(R.id.dialog_view);
        TextView tipTextView =  v.findViewById(R.id.tipTextView);
        if (TextUtils.isEmpty(msg)) {
            tipTextView.setVisibility(View.GONE);
        } else {
            tipTextView.setText(msg);
        }

        loadingDialog = new Dialog(context,R.style.myProgressDialog);
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        return loadingDialog;
    }

    public static void dismissDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
    public static void showDialog(Context context, String str) {
        create(context, str);
        loadingDialog.show();
    }
    public static void showDialog(Context context) {
        create(context, "");
        loadingDialog.show();
    }
}
