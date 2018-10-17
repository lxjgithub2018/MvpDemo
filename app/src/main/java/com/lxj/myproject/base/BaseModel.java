package com.lxj.myproject.base;

import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;


/**
 * - @Description: 基础Model层
 * - @Author:  LXJ
 * - @Time:  2018/10/10 17:08
 */
public abstract class BaseModel<T> {
    //数据请求参数
    protected String[] mParams;

    /**
     * 设置数据请求参数
     *
     * @param args 参数数组
     */
    public BaseModel params(String... args) {
        mParams = args;
        return this;
    }

    /**
     * 添加Callback并执行数据请求
     * 具体的数据请求由子类实现
     * @param callback
     */
   /* public  void execute(final BaseMvpCallback<String> callback){
        //模拟网络请求耗时操作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mParams 是从父类得到的请求参数
                switch (mParams[0]) {
                    case "normal":
                        callback.onSuccess("根据参数" + mParams[0] + "的请求网络数据成功");
                        break;
                    case "failure":
                        callback.onFailure("请求失败：参数有误");
                        break;
                    case "error":
                        callback.onError();
                        break;
                    default:
                        break;
                }
                callback.onComplete();
            }
        }, 2000);
    }*/

    /**
     * get请求
     *
     * @param url
     * @param callback
     */
    public void requestGetAPI(String url, Map params, final BaseMvpCallback<String> callback) {
        //这里写具体的网络请求
        OkHttpUtils.get()
                .url(url)
                .addHeader("Authorization", "")
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        callback.onBefore(request, id);
                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        callback.onAfter(id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        callback.onError(call, e, i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Log.i("InfoMessage", ":" + s);
                        callback.onSuccess(s);
                       /*Gson json = new Gson();
                       BaseResult result = json.fromJson(s, BaseResult.class);
                        if (result.checkCode()) {
                            callback.onSuccess(s);
                        } else {
                            callback.onFailure(result.getMsg());
                        }*/
                    }
                });
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public void requestPostAPI(String url, Map params, final BaseMvpCallback<String> callback) {
        //这里写具体的网络请求
        OkHttpUtils.post()
                .url(url)
                .addHeader("Authorization", "")
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        callback.onBefore(request, id);
                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        callback.onAfter(id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        callback.onError(call, e, i);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Log.i("InfoMessage", ":" + s);
                        Gson json = new Gson();
                        BaseResult result = json.fromJson(s, BaseResult.class);
                        if (result.checkCode()) {
                            callback.onSuccess(s);
                        } else {
                            callback.onFailure(result.getMsg());
                        }
                    }
                });
    }
}
