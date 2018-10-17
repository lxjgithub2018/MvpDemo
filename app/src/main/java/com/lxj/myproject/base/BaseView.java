package com.lxj.myproject.base;

import android.content.Context;

import okhttp3.Call;

/**
 * - @Description:  基础view层
 * - @Author:  LXJ
 * - @Time:  2018/10/10 14:11
 */
public interface BaseView {
    /**
     * 显示正在加载view
     */
    void showLoading();

    /**
     * 显示正在加载view
     */
    void showLoading(String msg);

    /**
     * 关闭正在加载view
     */
    void hideLoading();

    /**
     * 显示提示
     *
     * @param msg
     */
    void showToast(String msg);

    /**
     * 显示请求错误提示
     */
    void showErr();

    /**
     * 显示请求错误提示
     */
    void showErr(Call call,Exception e, int i);

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    Context getContext();
}
