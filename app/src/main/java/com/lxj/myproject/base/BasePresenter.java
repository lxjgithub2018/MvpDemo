package com.lxj.myproject.base;

/**
 * - @Description:  基础presenter层
 * - @Author:  LXJ
 * - @Time:  2018/10/10 14:20
 */
public class BasePresenter<V extends BaseView> {
    /**
     * 绑定的view
     */
    private V mvpView;

    /**
     * 绑定view，一般在初始化中调用该方法
     */
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView() {
        this.mvpView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached(){
        return mvpView != null;
    }

    /**
     * 获取连接的view
     */
    public V getView(){
        return mvpView;
    }
}
