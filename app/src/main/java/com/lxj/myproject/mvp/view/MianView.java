package com.lxj.myproject.mvp.view;

import com.lxj.myproject.base.BaseView;

/**
 - @Description:  具体UI逻辑操作
 - @Author:  LXJ
 - @Time:  2018/10/9 15:15
 */

public interface MianView extends BaseView {
    /**
     * 当数据请求成功后，调用此接口显示数据
     * @param data 数据源
     */
    void showData(String data);
}
