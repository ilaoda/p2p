package com.ilaoda.p2p.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilaoda.p2p.R;
import com.ilaoda.p2p.common.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hbh on 2018/1/6.
 */

public class MoreFragment extends Fragment {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_common)
    TextView tvTitleCommon;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    Unbinder unbinder;

    /**
     * Fragement 创建加载布局时候调用
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // View view = View.inflate(getActivity(), R.layout.fragment_invest, null);
        View view = UIUtils.getView(R.layout.fragment_invest);
        unbinder = ButterKnife.bind(this, view);

        initTitle();

        return view;
    }


    /**
     * 初始化 Title
     */
    private void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        tvTitleCommon.setText("更多");
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
