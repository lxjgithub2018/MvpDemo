package com.lxj.myproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import okhttp3.Call;

/**
 * - @Description:  基础Fragment
 * - @Author:  LXJ
 * - @Time:  2018/10/10 17:08
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    //我们自己的Fragment需要缓存视图
    protected View viewContent;
    private boolean isInit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (viewContent == null) {
            viewContent = inflater.inflate(getLayoutId(), container, false);
            initContentView(viewContent);
        }
        //判断Fragment对应的Activity是否存在这个视图
        ViewGroup parent = (ViewGroup) viewContent.getParent();
        if (parent != null) {
            //如果存在,那么我就干掉,重写添加,这样的方式我们就可以缓存视图
            parent.removeView(viewContent);
        }
        return viewContent;
    }

    /**
     * 获取布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化contentview
     *
     * @param viewContent
     */
    public abstract void initContentView(View viewContent);

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isInit) {
            this.isInit = true;
            initPresenter();
            if (getPresenter() != null) {
                getPresenter().attachView(this);
            }
            initView();
            initEvent();
            initData();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshView();
    }

    @Override
    public void onResume() {
        super.onResume();
        onShow();
    }


    @Override
    public void onPause() {
        onHide();
        super.onPause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
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

    /**
     * 显示
     */
    public void onShow() {
    }

    /**
     * 隐藏
     */
    public void onHide() {
    }

    /**
     * 刷新
     */
    public void refreshView() {
    }

    @Override
    public void showLoading() {
        checkActivityAttached();
        ((BaseActivity)getActivity()).showLoading();
    }

    @Override
    public void showLoading(String msg) {
        checkActivityAttached();
        ((BaseActivity)getActivity()).showLoading(msg);
    }

    @Override
    public void hideLoading() {
        checkActivityAttached();
        ((BaseActivity)getActivity()).hideLoading();
    }

    @Override
    public void showToast(String msg) {
        checkActivityAttached();
        ((BaseActivity)getActivity()).showToast(msg);
    }

    @Override
    public void showErr() {
        checkActivityAttached();
        ((BaseActivity)getActivity()).showErr();
    }

    @Override
    public void showErr(Call call, Exception e, int i) {
        checkActivityAttached();
        ((BaseActivity)getActivity()).showErr(call,e,i);
    }

    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity ! - -.");
        }
    }
}
