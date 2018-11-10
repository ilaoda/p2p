package com.ilaoda.p2p.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ilaoda.p2p.R;
import com.ilaoda.p2p.common.ActivityManager;
import com.ilaoda.p2p.common.UIUtils;
import com.ilaoda.p2p.fragment.HomeFragemnt;
import com.ilaoda.p2p.fragment.InvestFragment;
import com.ilaoda.p2p.fragment.MeFragment;
import com.ilaoda.p2p.fragment.MoreFragment;
import com.ilaoda.p2p.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    // 声明四个fragment
    private HomeFragemnt homeFragemnt;
    private InvestFragment investFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;

    private FragmentTransaction fragmentTransaction;



    /**
     * 通过 butterknife 初始化 View
     */
    @BindView(R.id.iv_main_home)
    ImageView ivMainHome;
    @BindView(R.id.tv_main_home)
    TextView tvMainHome;
    @BindView(R.id.ll_main_home)
    LinearLayout llMainHome;
    @BindView(R.id.iv_main_invest)
    ImageView ivMainInvest;
    @BindView(R.id.tv_main_invest)
    TextView tvMainInvest;
    @BindView(R.id.ll_main_invest)
    LinearLayout llMainInvest;
    @BindView(R.id.iv_main_me)
    ImageView ivMainMe;
    @BindView(R.id.tv_main_me)
    TextView tvMainMe;
    @BindView(R.id.ll_main_me)
    LinearLayout llMainMe;
    @BindView(R.id.iv_main_more)
    ImageView ivMainMore;
    @BindView(R.id.tv_main_more)
    TextView tvMainMore;
    @BindView(R.id.ll_main_more)
    LinearLayout llMainMore;
    @BindView(R.id.fl_main_content)
    FrameLayout flMainContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);  // 自动生成

        // 将当前Activity加入到 Stack 栈中统一管理
        ActivityManager.getInstance().add(this);

        // 默认显示首页
        setSelectFragment(0);

        // 模拟异常
        /*String str = null;
        // if("abc".equals(str)) {

         if(str.equals("abc")) {

         }*/
    }


    /**
     * 给 4个 Tab 设置点击事件
     * 采用 ButterKnife 中的注解
     *
     * 当Activity 运行时，根据点击的 Tab, 采用动态的加载 fragement
     * 要用到事物
     *
     * replace() == 先 remove, 再 add, 不推荐。 应当用隐藏 hide()
     * 避免多次点击后：重新实例化耗内存、 联网加载数据、 慢
     */
    @OnClick({R.id.ll_main_home, R.id.ll_main_invest, R.id.ll_main_me, R.id.ll_main_more})
    public void showTab(View view) {
        //Toast.makeText(this, "点击了 Tab", Toast.LENGTH_SHORT).show();

        switch (view.getId()) {
            case R.id.ll_main_home:
                setSelectFragment(0);
                break;

            case R.id.ll_main_invest:
                setSelectFragment(1);
                break;

            case R.id.ll_main_me:
                setSelectFragment(2);
                break;

            case R.id.ll_main_more:
                setSelectFragment(3);
                break;

            default:
                break;
        }

    }

    /**
     * 显示参数所给定的 Fragment
     * 注意： 只有当事务 commit 之后, Fragment的生命周期才开始执行。
     * 单纯 实例化 Fragment 对象是不走生命周期的。
     *
     * @param i
     */
    private void setSelectFragment(int i) {

        // 1. 获取FragmentManager
        FragmentManager fragmentManager = getFragmentManager();

        // 2. 获取 transaction
        fragmentTransaction = fragmentManager.beginTransaction();

        // 当选中其中一tab之后，该tab的图标和颜色需要变化，其他的需要恢复其他，
        // 而且其他fragment得隐藏
        // 隐藏所有 fragment
        hideAllFragments();

        // 先将其他tab恢复为默认
        resetAllTabs();

        switch (i) {
            case 0:
                if(homeFragemnt == null){ // 当没有被创建的时候才去创建
                    // 3. 实例化响应的 Fragment
                    homeFragemnt = new HomeFragemnt();

                    // 4. 将framgment(里面已经有布局), 加入到fragment的布局容器中
                    fragmentTransaction.add(R.id.fl_main_content, homeFragemnt);
                }
                // 5. 将加入进去的Fragment 显示出来
                fragmentTransaction.show(homeFragemnt);

                // 当前tab改变图标和颜色
                ivMainHome.setImageResource(R.drawable.bottom02);
                // tvMainHome.setTextColor(getResources().getColor(R.color.tab_selected_color));
                tvMainHome.setTextColor(UIUtils.getColor(R.color.tab_selected_color));
                break;

            case 1:
                if(investFragment == null){ // 当没有被创建的时候才去创建
                    // 3. 实例化响应的 Fragment
                    investFragment = new InvestFragment();

                    // 4. 将framgment(里面已经有布局), 加入到fragment的布局容器中
                    fragmentTransaction.add(R.id.fl_main_content, investFragment);
                }
                // 5. 将加入进去的Fragment 显示出来
                fragmentTransaction.show(investFragment);

                // 当前tab改变图标和颜色
                ivMainInvest.setImageResource(R.drawable.bottom04);
                //tvMainInvest.setTextColor(getResources().getColor(R.color.tab_selected_color));
                tvMainInvest.setTextColor(UIUtils.getColor(R.color.tab_selected_color));
                break;

            case 2:
                if(meFragment == null){ // 当没有被创建的时候才去创建
                    // 3. 实例化响应的 Fragment
                    meFragment = new MeFragment();

                    // 4. 将framgment(里面已经有布局), 加入到fragment的布局容器中
                    fragmentTransaction.add(R.id.fl_main_content, meFragment);
                }
                // 5. 将加入进去的Fragment 显示出来
                fragmentTransaction.show(meFragment);

                // 当前tab改变图标和颜色
                ivMainMe.setImageResource(R.drawable.bottom06);
                //tvMainMe.setTextColor(getResources().getColor(R.color.tab_selected_color));
                tvMainMe.setTextColor(UIUtils.getColor(R.color.tab_selected_color));
                break;

            case 3:
                if(moreFragment == null){ // 当没有被创建的时候才去创建
                    // 3. 实例化响应的 Fragment
                    moreFragment = new MoreFragment();

                    // 4. 将framgment(里面已经有布局), 加入到fragment的布局容器中
                    fragmentTransaction.add(R.id.fl_main_content, moreFragment);
                }
                // 5. 将加入进去的Fragment 显示出来
                fragmentTransaction.show(moreFragment);

                // 当前tab改变图标和颜色
                ivMainMore.setImageResource(R.drawable.bottom08);
                //tvMainMore.setTextColor(getResources().getColor(R.color.tab_selected_color));
                tvMainMore.setTextColor(UIUtils.getColor(R.color.tab_selected_color));
                break;

            default:
                break;
        }

        // 6. 提交事务, 这个时候，framgment 的声明周期才开始执行
        fragmentTransaction.commit();
    }

    /**
     * 在显示选中的Fragment之前，隐藏所有的 Fragment, 必选重叠
     */
    private void hideAllFragments() {
        if(homeFragemnt != null) {
            fragmentTransaction.hide(homeFragemnt);
        }

        if(investFragment != null) {
            fragmentTransaction.hide(investFragment);
        }

        if(meFragment != null) {
            fragmentTransaction.hide(meFragment);
        }

        if(moreFragment != null) {
            fragmentTransaction.hide(moreFragment);
        }
    }


    /**
     * 恢复所有的tabs, 其实可以用selector去实现
     */
    private void resetAllTabs() {
        ivMainHome.setImageResource(R.drawable.bottom01);
        ivMainInvest.setImageResource(R.drawable.bottom03);
        ivMainMe.setImageResource(R.drawable.bottom05);
        ivMainMore.setImageResource(R.drawable.bottom07);
        /*
        tvMainHome.setTextColor(getResources().getColor(R.color.tab_normal_color));
        tvMainInvest.setTextColor(getResources().getColor(R.color.tab_normal_color));
        tvMainMe.setTextColor(getResources().getColor(R.color.tab_normal_color));
        tvMainMore.setTextColor(getResources().getColor(R.color.tab_normal_color));
        */

        tvMainHome.setTextColor(UIUtils.getColor(R.color.tab_normal_color));
        tvMainInvest.setTextColor(UIUtils.getColor(R.color.tab_normal_color));
        tvMainMe.setTextColor(UIUtils.getColor(R.color.tab_normal_color));
        tvMainMore.setTextColor(UIUtils.getColor(R.color.tab_normal_color));
    }

    private boolean flag = true;
    private static final int WHAT_RESET_BACK = 1;
    /**
     * Handler 用来作为 双击返回退出，的延迟flag的改变
     */
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            LogUtil.i("binghua", "handler: " + Thread.currentThread().getName());
            switch (msg.what) {
                case WHAT_RESET_BACK:
                    flag = true;  // 复原
            }
        }
    };

    /**
     * 处理两次点击，退出应用
     * * 此处采用 Handler 延迟处理
     * * 还可以采用记录两次点击时间差
     * @param keyCode
     * @param event
     * @return true
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // 第一次点击走这个
        // 如果第二次的点击超过了2s, 再将flag改为true
        if(keyCode == KeyEvent.KEYCODE_BACK && flag) {

            LogUtil.i("binghua", "onKeyUp: " + Thread.currentThread().getName());
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            flag = false;

            // 发送延迟消息，改变 flag 的值, 如过2s内还没点击，即超过2s去点击就去执行这个。
            handler.sendEmptyMessageDelayed(WHAT_RESET_BACK, 2000);
            return true;
        }

        // super.onKeyUp(keyCode, event) 当keyCode == KeyEvent.KEYCODE_BACK, 不能轻易让别人调用
        // 只有两次点击在 2s 以内，才调用这个方法
        // 第二次点击走这个
        return super.onKeyUp(keyCode, event);
    }


    /**
     * 避免 Handler 内存泄露，移除所有未被执行的msg
     */
    @Override
    protected void onDestroy() {

        super.onDestroy();

        // 方式一： 移除指定id的所有消息
        handler.removeMessages(WHAT_RESET_BACK);

        // 方式二： 移除所有未被执行的msg
        handler.removeCallbacksAndMessages(null);
    }
}