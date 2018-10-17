package com.lxj.myproject.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lxj.myproject.R;
import com.lxj.myproject.base.BaseFragment;
import com.lxj.myproject.base.BasePresenter;
import com.lxj.myproject.mvp.presenter.MainPresenter;
import com.lxj.myproject.mvp.view.MianView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by li on 2018/10/11.
 */
public class AFragment extends BaseFragment implements MianView {
    MainPresenter presenter;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.successful_btn)
    Button successfulBtn;
    @BindView(R.id.failure_btn)
    Button failureBtn;
    @BindView(R.id.abnormal_btn)
    Button abnormalBtn;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_a;
    }

    @Override
    public void initContentView(View viewContent) {
        unbinder = ButterKnife.bind(this, viewContent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void initPresenter() {
        presenter = new MainPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showData(String data) {
        text.setText(data);
    }

    @OnClick({R.id.successful_btn, R.id.failure_btn, R.id.abnormal_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.successful_btn:
                presenter.login();
                break;
            case R.id.failure_btn:
                presenter.login();
                break;
            case R.id.abnormal_btn:
                presenter.login();
                break;
        }
    }
}
