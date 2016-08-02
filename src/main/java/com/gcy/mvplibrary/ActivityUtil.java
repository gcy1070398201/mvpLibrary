package com.gcy.mvplibrary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;
/**
 * actvity相关帮助类
 * 
 * @author admin
 * 
 */
public class ActivityUtil {

	/**
	 * Toast提示
	 * 
	 * @param message
	 */
	public static void showToast(Context context, String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
	}
	/**
	 * 检测网络状态
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean NetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是wifi
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * TextView是否为空
	 * 
	 * @param tv
	 * @return 是空返回true,否则返回false
	 */
	public static boolean isEmptyTextView(TextView tv) {
		if (tv == null) {
			return true;
		}
		if ("".equals(tv.getText().toString())) {
			return true;
		} else {
			return false;
		}
	}
	/*
	 * 获取手机信息
	 */
	public static String getPhoneInfo(Context context, int type) throws NameNotFoundException {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		switch (type) {
		case 1:// 设备唯一标识
			return telephonyManager.getDeviceId();
		case 2:// 系统版本号
			return android.os.Build.VERSION.RELEASE;
		case 3:// 设备型号
			return android.os.Build.MODEL;
		case 4:// 应用程序版本号
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		default:
			return "";
		}
	}

	/**
	 * 验证邮箱格式
	 * */
	public static boolean isEmail(String email) {
		String str = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();

	}

	/**
	 * 验证手机号码格式
	 * */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|8[025-9])\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		if (str.length() > 9) {
			Pattern p1 = null;
			Matcher m = null;
			p1 = Pattern.compile("^0(10|2[0-5789]|\\d{3})\\d{7,8}$"); // 验证带区号的
			m = p1.matcher(str);
			return m.matches();
		} else {
			return false;
		}
	}
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
