package com.wml.baiduyunlive.modle;

/**
 * Created by 王苗亮 on 2017/2/20.
 * 所有的类都是本类的子类
 */

public class BaseBean <T>{
    public int error_code;
    public String reason;
    public T result;

    public String getStatus() {
        return reason;
    }

    public T getData() {
        return result;
    }

    public int getCode(){return error_code;}

    @Override
    public String toString() {
        return "HttpResult{" +
                "status='" + reason + '\'' +
                ", data=" + result +
                '}';
    }
}
