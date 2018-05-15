package com.example.weather.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;

/**
 * 打开或关闭软键盘
 * 
 * @author zhy
 * 
 */
public class KeyBoardUtils
{
	/**
	 * 打卡软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void openKeybord(EditText mEditText, Context mContext)
	{
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
	}
//
//	/**
//	 * 关闭软键盘
//	 *
//	 * @param mEditText
//	 *            输入框
//	 * @param mContext
//	 *            上下文
//	 */
//	public static void closeKeybord(EditText mEditText, Context mContext)
//	{
//		InputMethodManager imm = (InputMethodManager) mContext
//				.getSystemService(Context.INPUT_METHOD_SERVICE);
//
//		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
//	}

	/**
	 * 动态隐藏软键盘
	 *
	 * @param view 视图
	 */
	public static void closeKeybord(Activity context, EditText edittext) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
		int sdkInt = Build.VERSION.SDK_INT;
		if (sdkInt >= 11) {
			context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			Class<EditText> cls = EditText.class;
			try {
				Method setSoftInputShownOnFocus = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
				setSoftInputShownOnFocus.setAccessible(true);
			} catch (Exception e) {
				try {
					Method setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
					setSoftInputShownOnFocus.setAccessible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
