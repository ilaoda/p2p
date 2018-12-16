package com.ilaoda.p2p.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import java.util.logging.LogRecord;

/**
 * Created by hbh on 2018/1/8.
 *
 * 提供一些全局性的变量，供整个项目中的使用。
 */

public class MyApplication extends Application {

    // 上下文对象
    public static Context context;

    // handler
    public static Handler handler;

    // 主线程, 因为当前Application是在主线程，因此该 mainThread 就是在主线程中
    // 可以用来判断当前线程是否在主线程， 让某个程序在主线程执行等功能；
    public static Thread mainThread;

    // 主线程id
    public static int mainThreadId;

    /**
     * 实例化以上变量
     */
    @Override
    public void onCreate() {
        super.onCreate();

        context = this.getApplicationContext();

        handler = new Handler();

        // 实例化当前 Application 为主线程
        // 需要在清单文件 <Application android:name=".common.MyApplication" 配置
        mainThread = Thread.currentThread();

        // 获取当前线程的 id
        mainThreadId = android.os.Process.myTid();

        // 在应用刚一启动，整个过程中，都设置未捕获异常的处理器
        CrashHandler.getInstance().init();


    }
}
