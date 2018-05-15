package com.example.weather.util;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * 获取验证码的禁止点击计时器
 * @author Administrator
 *
 */
public class CodeTime extends CountDownTimer {
	private Button getcode;
//	private CgupayApplaication app;

	/**
	 * 获取验证码的禁止点击计时器
	 * @param millisInFuture		计时时长
	 * @param countDownInterval		每次减少的时长
	 * @param button				
	 */
	public CodeTime(long millisInFuture, long countDownInterval, Button textview) {
		super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
		getcode = textview;
//		app = (CgupayApplaication) button.getContext().getApplicationContext();
	}

	@Override
	public void onFinish() {// 计时完毕时触发
//		app.verifyCode = true;
		getcode.setText("重新获取验证码");
		getcode.setEnabled(true);
//		getcode.setEnabled(app.verifyCode);
	}

	@Override
	public void onTick(long millisUntilFinished) {// 计时过程显示
//		app.verifyCode = false;
//		app.millisUntilFinished = millisUntilFinished;
		getcode.setText(millisUntilFinished / 1000 + "秒后可重新获取");
		getcode.setEnabled(false);
//		getcode.setEnabled(app.verifyCode);
	}
}
