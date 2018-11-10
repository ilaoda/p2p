package com.ilaoda.p2p.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ilaoda.p2p.R;
import com.ilaoda.p2p.common.ActivityManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Splash 动画
 *
 * Handler 延时
 */
public class WelcomeActivity extends Activity {


    @BindView(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @BindView(R.id.rl_welcome)
    RelativeLayout rlWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 去掉标题栏 和 全屏显示, setContentView 之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcom);
        ButterKnife.bind(this);

        // 将当前Activity加入到 Stack 栈中统一管理
        ActivityManager.getInstance().add(this);

        // 设置启动动画
        //setAnimation1(); // 方式1
        setAnimation2(); // 方式2
    }

    /**
     * 设置启动动画： 方式一  采用动画监听
     */
    private void setAnimation1() {

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);

        // 关于：Interpolator http://blog.csdn.net/guolin_blog/article/details/44171115
        alphaAnimation.setInterpolator(new AccelerateInterpolator()); // 设置动画变化率，变化快慢
        alphaAnimation.setDuration(3000);

        // 给动画设置监听，当动画执行完之后跳转到 MainActivity
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            // 动画执行结束之后， intent 跳转
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent mainActivityIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);

                // 记得将当前activity finish掉。要么返回退出的时候，就到该页面了
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rlWelcome.startAnimation(alphaAnimation);
    }


    private Handler handler = new Handler();
    /**
     * 设置启动动画： 方式二  采用Handler 延时
     */
    private void setAnimation2() {

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);

        // 关于：Interpolator http://blog.csdn.net/guolin_blog/article/details/44171115
        alphaAnimation.setInterpolator(new AccelerateInterpolator()); // 设置动画变化率???
        alphaAnimation.setDuration(3000);

        /**
         * handler 延时处理
         * 还有一种方式： 发送msg, 见MainActivity中，处理双击返回按键的操作
         */
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainActivityIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);

                // 记得将当前activity finish掉。要么返回退出的时候，就到该页面了
                // finish();

                // finish, 并从栈中 remove Activity
                ActivityManager.getInstance().remove(WelcomeActivity.this);
            }
        }, 3000);

        rlWelcome.startAnimation(alphaAnimation);
    }
}
