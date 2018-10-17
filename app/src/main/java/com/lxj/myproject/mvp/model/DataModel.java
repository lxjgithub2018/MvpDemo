package com.lxj.myproject.mvp.model;

import com.lxj.myproject.base.BaseModel;

/**
 * - @Description:  DataModel类
 * - @Author:  LXJ
 * - @Time:  2018/10/10 17:21
 */
public class DataModel {
    public static BaseModel request(Class cls) {
        // 声明一个空的BaseModel
        BaseModel model = null;
        try {
            //利用反射机制获得对应Model对象的引用
            model = (BaseModel) cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return model;
    }
}
