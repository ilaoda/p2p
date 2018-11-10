package com.ilaoda.p2p.common;

import android.content.Context;
import android.os.Handler;
import android.view.View;

/**
 * Created by hbh on 2018/1/8.
 *
 * 提供一些UI相关的通用方法
 */

public class UIUtils {

    /**
     * 获取 Context
     * @return
     */
    public static Context getContext() {
        return MyApplication.context;
    }

    /**
     * 获取 Handler
     * @return
     */
    public static Handler getHandler() {
        return MyApplication.handler;
    }


    /**
     * 给定 colorId, 返回指定颜色的 十六进制
     * @param colorId
     * @return
     */
    public static int getColor(int colorId) {
        return getContext().getResources().getColor(colorId);
    }


    /**
     * 给定一个layout布局的id，返回一个View 对象
     * @param layoutId  R.layout.fragment_invest
     * @return 返回指定布局的View 视图
     */
    public static View getView(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    /**
     * dp 转 px
     * @param dp
     * @return
     */
    public static float dp2px(int dp) {
        // 获取手机密度， 即1, 1.5, 2, 3 等
        // density = adb shell wm density / 160
        float density = getContext().getResources().getDisplayMetrics().density;
        // +0.5 为了四舍五入
        return (float) ((dp * density) + 0.5);
    }


    /**
     * px 转 dp
     * @param px
     * @return
     */
    public static float px2dp(int px) {

        // 获取手机密度， 即1, 1.5, 2, 3 等
        // density = adb shell wm density / 160
        float density = getContext().getResources().getDisplayMetrics().density;
        // +0.5 为了四舍五入
        return (float) ((px / density) + 0.5);
    }
}
