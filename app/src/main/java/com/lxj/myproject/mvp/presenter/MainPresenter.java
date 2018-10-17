package com.lxj.myproject.mvp.presenter;

import com.lxj.myproject.MainApp;
import com.lxj.myproject.base.BasePresenter;
import com.lxj.myproject.callback.MainMvpCallback;
import com.lxj.myproject.mvp.model.DataModel;
import com.lxj.myproject.mvp.model.MainDataModel;
import com.lxj.myproject.mvp.view.MianView;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * - @Description:  逻辑业务处理
 * - @Author:  LXJ
 * - @Time:  2018/10/9 15:15
 */
public class MainPresenter extends BasePresenter<MianView> {

    public void login() {
        Map params = new HashMap();
        //params.put("city", "101010100");
        //params.put("password", "nn123456");
        postData("https://www.baidu.com", params);
    }

    /**
     * 请求接口
     *
     * @param url
     * @param params
     */
    public void postData(String url, Map params) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        // 调用Model请求数据
        DataModel
                // 设置请求标识token
                .request(MainDataModel.class)
                // 添加请求参数，没有则不添加
                .requestGetAPI(url, params, new MainMvpCallback<String>() {
                    @Override
                    public void onBefore(Request request, int id) {
                        //显示正在加载进度条
                        getView().showLoading();
                    }

                    @Override
                    public void onAfter(int id) {
                        getView().hideLoading();
                    }

                    @Override
                    public void onSuccess(String data) {
                        getView().showData(data);
                    }

                    @Override
                    public void onFailure(String msg) {
                        MainApp.getInstance().showMsg(msg);
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        //TODO
                        getView().showErr(call, e, i);
                    }
                });
    }
}
