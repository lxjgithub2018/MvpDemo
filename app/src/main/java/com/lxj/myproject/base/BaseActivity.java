package com.lxj.myproject.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.noober.background.BackgroundLibrary;

import okhttp3.Call;

/**
 * - @Description:  基础Activity
 * - @Author:  LXJ
 * - @Time:  2018/10/10 14:33
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    //提示弹框
    private ProgressDialog mProgressDialog;

    /**
     * 获取当前context
     *
     * @return
     */
    public Activity getActivity() {
        return this;
    }

    /**
     * 获取布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化contentView
     *
     * @param savedInstanceState
     */
    public abstract void initContentView(Bundle savedInstanceState);

    /**
     * 获取Presenter实例，子类实现
     *
     * @return
     */
    public abstract BasePresenter getPresenter();

    /**
     * 初始化Presenter的实例，子类实现
     */
    public abstract void initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BackgroundLibrary.inject(getActivity());
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initContentView(savedInstanceState);
        initView();
        initEvent();
        initData();
        initPresenter();
        if (getPresenter() != null) {
            getPresenter().attachView(this);
        }
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("请求中...");
        mProgressDialog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void showLoading(String msg) {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(msg);
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErr() {
        showToast("错误提示");
    }

    @Override
    public void showErr(Call call,Exception e, int i) {
        showToast(e.toString());
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }
}
