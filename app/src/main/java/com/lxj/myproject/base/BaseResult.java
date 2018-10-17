package com.lxj.myproject.base;


/**
 * Created by li on 2018/8/7.
 */

public class BaseResult<T> {
    /**
     * code : OK
     * msg : 注册成功
     * data : 返回数据
     */
    private String code;
    private String msg;
    protected T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean checkCode() {
        switch (code) {
            case "OK":
                return true;
            default:
                return false;
        }
    }
}
