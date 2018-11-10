package com.ilaoda.p2p.common;

import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

import com.ilaoda.p2p.util.LogUtil;

/**
 * 单例模式
 *
 * 实例化未捕获异常的处理器的操作，就是在系统中的一个单独的线程中完成的。
 * 因此不涉及多线程的问题, 所以使用懒汉式更好。
 *
 * 异常上报到服务器
 *
 * 在Application 中设置下
 *
 * Created by hbh on 2018/1/9.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    // 私有化构造
    private CrashHandler() {
    }

    // 声明该对象, 懒汉式，需要的时候才创建对象
    private static CrashHandler crashHandler = null;

    /**
     * 获取异常处理器的实例
     * @return
     */
    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }


    /**
     * 设置当前异常类来处理异常系统默认的处理异常的处理器
     */
    public void init() {
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

        // 将当前类设为默认的未捕获的异常处理器, 即调用上面的 uncaughtException()
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    /**
     * 当出现未捕获的异常时候，该方法会被调用
     * * 异常友好提示
     * * 上报异常到服务端
     * * 关闭资源
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LogUtil.e("binghua", "发生了未捕获的异常： " + e.getMessage());

        /**
         * 此处的Toast需要在主线程执行，三种方式：
         *  * 采用 Handler
         *  * 采用 runUiThread()
         *  * 采用 Looper
         *
         *  主线程默认开启了Looper, 因此该操作就会在主线程运行
         */
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(UIUtils.getContext(), "出现异常了", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        /**
         * 收集异常信息
         */
        collectionException(e);

        /**
         * 关闭资源
         */
        try {
            Thread.sleep(20000);
            // 1. 从栈中移除当前 Activity
            ActivityManager.getInstance().removeAllActivity();
            // 2. 结束当前进程
            Process.killProcess(android.os.Process.myPid());
            // 3. 退出虚拟机
            System.exit(0);

        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }


    /**
     * 收集异常信息上报
     * 后面换成 Bugly, 此处模拟
     * @param throwable
     */
    public void collectionException(Throwable throwable) {

        // 获取手机参数信息
        final String deviceInfo = Build.DEVICE + ":" +
                Build.VERSION.SDK_INT + ":" +
                Build.MODEL + ":" +
                Build.PRODUCT;

        // 获取异常信息
        final String message = throwable.getMessage();

        /**
         * 开启子线程访问网络
         */
        new Thread() {
            @Override
            public void run() {
                LogUtil.e("TAG", "deviceInfo:" + deviceInfo + ", message:" + message);
            }
        }.start();
    }





}
