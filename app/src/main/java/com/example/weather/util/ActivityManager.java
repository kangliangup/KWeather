package com.example.weather.util;

import android.app.Activity;

import java.util.Stack;

/**
 * 界面控制类 包括activity的加入 和 移除
 */
public class ActivityManager {
    private static Stack<Activity> mActivityStack;
    private static ActivityManager mAppManager;

    private ActivityManager() {
    }

    /**
     * 单一实例
     */
    public static ActivityManager getInstance() {
        if (mAppManager == null) {
            mAppManager = new ActivityManager();
        }
        return mAppManager;
    }

    /**
     * 添加Activity到堆�?
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 获取栈顶Activity（堆栈中�?���?��压入的）
     */
    public Activity getTopActivity() {
        Activity activity = mActivityStack.lastElement();
        return activity;
    }

    /**
     * 结束栈顶Activity（堆栈中�?���?��压入的）
     */
    public void killTopActivity() {
        Activity activity = mActivityStack.lastElement();
        killActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 移除指定的Activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 只留最后一个元素 清除其他的
     */
    public void keepSelf(Class<?> cls) {
        for (int i = 0, size = mActivityStack.size() - 1; i < size; i++) {
            if (mActivityStack.get(i).getClass().equals(cls)) {
                mActivityStack.get(i).finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        // for (Activity activity : mActivityStack) {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Class<?> cls2 = mActivityStack.get(i).getClass();
            if (cls2.equals(cls)) {
                killActivity(mActivityStack.get(i));
                break;
            }
        }
    }

    /**
     * 结束指定类名的Activity(所有)
     */
    public void killActivitysAll(Class<?> cls) {
        // for (Activity activity : mActivityStack) {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Class<?> cls2 = mActivityStack.get(i).getClass();
            if (cls2.equals(cls)) {
                killActivity(mActivityStack.get(i));
            }
        }
    }

    /**
     * 保留指定的Activity
     */
    public void getActivity(Class<?> cls) {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            Class<?> cls2 = mActivityStack.get(i).getClass();
            if (!cls2.equals(cls) && null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        // mActivityStack.clear();
    }

    /**
     * 栈中是否有这个activity
     */
    public boolean hasActivity(Class<?> cls) {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            Class<?> cls2 = mActivityStack.get(i).getClass();
            if (cls2.equals(cls)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 结束所有的Activity
     */
    public void killAllActivity() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

//	/**
//	 * 保留第一个Activity 或者MainAcivity
//	 */
//	public void killotherActivity() {
//		for (int i = 1, size = mActivityStack.size(); i < size; i++) {
//			Class<?> cls2 = mActivityStack.get(i).getClass();
//			if (null != mActivityStack.get(i)
//					|| !MainAcivity.class.equals(cls2)) {
//				mActivityStack.get(i).finish();
//			}
//		}
//	}

    /**
     * 移除最后一个activity
     */
    public void killLastActivity() {
        if (mActivityStack.size() > 1) {
            mActivityStack.remove(mActivityStack.size() - 1);
        }
    }

//	/**
//	 * 退出登录
//	 */
//	public void logOut() {
//		try {
//			BankAppConstant.tokenid = "";
//			killAllActivity();
//		} catch (Exception e) {
//			System.out.println(111);
//		}
//	}
//
//	/**
//	 * �?��应用程序
//	 */
//	public void AppExit(Context context) {
//		try {
//			BankAppConstant.tokenid = "";
//			killAllActivity();
//			BankAppApplaication	app = (BankAppApplaication)context.getApplicationContext();
//			if ("single".equals(app.appType)) {
//				 ActivityManager activityMgr = (ActivityManager) context
//				 .getSystemService(Context.ACTIVITY_SERVICE);
//				 activityMgr.killBackgroundProcesses(context.getPackageName());
//				 Stack<Activity> Stack = mActivityStack;
//				 System.exit(0);
//			}
//		} catch (Exception e) {
//			System.out.println(111);
//		}
//	}
}
