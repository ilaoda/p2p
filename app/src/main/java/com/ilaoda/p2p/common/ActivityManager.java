package com.ilaoda.p2p.common;


import android.app.Activity;

import java.util.Stack;

/**
 *
 * 单例模式
 * 将项目所有的Activity加入到自定义的 栈中统一管理。
 * 包括：增加、删除当前、删除指定、删除所有、栈的大小等
 *
 * Created by hbh on 2018/1/8.
 */

public class ActivityManager {

    /**
     * 单例模式三部曲：
     */

    // 1. 私有化构造，不让外面调用
    private ActivityManager() {

    }

    // 2. 饿汉式创建一个本类对象, 也是私有
    private static ActivityManager activityManager = new ActivityManager();


    // 3. 提供对外公开方法，可以获取到上面的对象
    public static ActivityManager getInstance() {
        return activityManager;
    }

    //---------------------

    /**
     *
     */

    // 获取栈对象，里面保存 Activity
    private Stack<Activity> activityStack = new Stack<>();


    /**
     * 添加 Activity到 栈中
     * @param activity
     */
    public void add(Activity activity) {
        activityStack.add(activity);
    }


    /**
     * 从栈中删除指定的 Activity, 此处通过 getClass 对比。就是说是用一个Activity创建的。
     * @param activity
     */
    public void remove(Activity activity) {
        /*
        // 遍历栈中所有的 Activity
        // 注意此处遍历从未到首，要不会出现顺序问题，导致有些没有被删
        for (int i = 0; i < activityStack.size(); i++) {
            // 获取每个i 对应的Activity
            Activity currentActivity = activityStack.get(i);
            // 如果和传入的Activity 相等，就删除
            if (currentActivity.getClass() == activity.getClass()) {
                // 先销毁当前的 Activity
                currentActivity.finish();
                // 再将栈中的该Acitivity从栈中移除
                activityStack.remove(i);
            }
        }
        */

        // 因此上面的顺序不行，改为倒序的形式。因为后加入的Activity在栈顶，
        // 从后往前遍历并将相等删除，不会导致顺序问题。
        // 遍历栈中所有的 Activity
        // 注意此处遍历从未到首，要不会出现顺序问题，导致有些没有被删
        for(int i = activityStack.size()-1; i>=0; i--) {
            // 获取每个i 对应的Activity
            Activity currentActivity = activityStack.get(i);
            // 如果和传入的Activity 相等，就删除
            if(currentActivity.getClass() == activity.getClass()) {
                // 先销毁当前的 Activity
                currentActivity.finish();
                // 再将栈中的该Acitivity从栈中移除
                activityStack.remove(i);

            }
        }
    }


    /**
     * 删除当前的Activity
     *
     */
    public void removeCurrentActivity() {

        /**
         * 方式一
         */
        /*
        // 获取栈顶的，即当前的Activity
        Activity currentActivity = activityStack.get(activityStack.size() - 1);
        currentActivity.finish();
        activityStack.remove(currentActivity);

        // 或者
        //activityStack.remove(activityStack.size() - 1);
        */

        /**
         * 方式二：
         * activityStack.lastElement()  栈顶的Activity
         */
        Activity lastElementActivity = activityStack.lastElement();
        lastElementActivity.finish();
        activityStack.remove(lastElementActivity);
    }

    /**
     * 删除所有的Activity 从栈中
     */
    public void removeAllActivity() {

        for (int i = activityStack.size()-1; i>=0; i--) {
            // 获取每个Activity
            Activity currentActivity = activityStack.get(i);
            currentActivity.finish();
            activityStack.remove(i);
        }
    }


    /**
     * 返回栈 Stack 的大小，即 里面存放 Activity的个数；
     * @return
     */
    public int activityStackSize() {
        return activityStack.size();
    }




}
